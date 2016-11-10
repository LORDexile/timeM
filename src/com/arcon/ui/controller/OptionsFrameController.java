package com.arcon.ui.controller;

import com.arcon.Main;
import com.arcon.db.DBConnect;
import com.arcon.lib.Constants;
import com.arcon.ui.model.*;
import com.arcon.ui.view.OptionsFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

public class OptionsFrameController {
    OptionsFrame optionsFrame;

    private JButton buttonMenuChangeUser;
    private JButton buttonMenuPrice;
    private JButton buttonMenuDiscounts;
    private JButton buttonMenuProgram;

    private JPanel panelContextMain;

    private JLabel labelUserName;
    private JLabel labelUserType;
    private JButton buttonChangeUser;
    private JScrollPane scrollPaneUsers;
    private JTable tableUsers;
    private JButton buttonAddNewUser;
    private JButton buttonDeleteExistUser;

    private JLabel labelPrice;
    private JButton buttonPriceChangePerform;

    private JTable tableDiscounts;
    private JButton buttonAddDiscount;
    private JButton buttonDeleteDiscount;
    private JButton buttonSwitchDiscount;

    private Discount currentDiscount;
    private User currentUser;

    public OptionsFrameController() {
        initComponents();
        initListeners();
    }

    private void initComponents() {
        optionsFrame = new OptionsFrame();

        panelContextMain = optionsFrame.getPanelContextMain();

        buttonMenuChangeUser = optionsFrame.getButtonMenuChangeUser();
        labelUserName = optionsFrame.getLabelUserName();
        labelUserType = optionsFrame.getLabelUserType();
        buttonChangeUser = optionsFrame.getButtonChangeUser();
        scrollPaneUsers = optionsFrame.getScrollPaneUsers();
        tableUsers = optionsFrame.getTableUsers();
        buttonAddNewUser = optionsFrame.getButtonAddNewUser();
        buttonDeleteExistUser = optionsFrame.getButtonDeleteExistUser();

        buttonMenuPrice = optionsFrame.getButtonMenuPrice();
        labelPrice = optionsFrame.getLabelPrice();
        buttonPriceChangePerform = optionsFrame.getButtonPriceChangePerform();

        buttonMenuDiscounts = optionsFrame.getButtonMenuDiscounts();
        tableDiscounts = optionsFrame.getTableDiscounts();
        buttonAddDiscount = optionsFrame.getButtonAddDiscount();
        buttonDeleteDiscount = optionsFrame.getButtonDeleteDiscount();
        buttonSwitchDiscount = optionsFrame.getButtonSwitchDiscount();
    }

    private void initListeners() {

        buttonMenuChangeUser.addActionListener(new buttonMenuChangeUserActionListener());
        buttonMenuPrice.addActionListener(new buttonMenuPriceActionListener());

        buttonChangeUser.addActionListener(new buttonChangeUserActionListener());
        buttonAddNewUser.addActionListener(new buttonAddNewUserActionListener());
        buttonDeleteExistUser.addActionListener(new buttonDeleteExistUserActionListener());
        tableUsers.addMouseListener(new tableUsersMouseListener());

        buttonPriceChangePerform.addActionListener(new buttonPriceChangePerformActionListener());

        buttonMenuDiscounts.addActionListener(new buttonMenuDiscountsActionListener());
        tableDiscounts.addMouseListener(new tableDiscountsMouseListener());
        buttonAddDiscount.addActionListener(new buttonAddDiscountActionListener());
        buttonDeleteDiscount.addActionListener(new buttonDeleteDiscountActionListener());
        buttonSwitchDiscount.addActionListener(new buttonSwitchDiscountActionListener());
    }

    public void showOptionsFrameWindow() {
        optionsFrame.setVisible(true);
        showContextPanel("CardChangeUser");
        setContextChangeUserPanel();
    }

    private void showContextPanel(String panel){
        CardLayout cl = (CardLayout)(panelContextMain.getLayout());
        cl.show(panelContextMain, panel);
    }

    private void setContextChangeUserPanel(){
        labelUserName.setText("<html><font color='red'>" + Constants.getUserName() + "</font></html>");
        labelUserType.setText("<html><font color='red'>" + Constants.getUserType() + "</font></html>");
        if(Constants.getUserType().equals(UserType.ADMIN.toString())){
            scrollPaneUsers.setVisible(true);
            buttonAddNewUser.setVisible(true);
            buttonDeleteExistUser.setVisible(true);

            setUsersTableModel();
        }else{
            if(scrollPaneUsers.isVisible()){
                scrollPaneUsers.setVisible(false);
                buttonAddNewUser.setVisible(false);
                buttonDeleteExistUser.setVisible(false);
            }
        }
    }

    private void setContextPricePanel(){
        labelPrice.setText((String.valueOf(Constants.PRICE)));
    }

    private void setContextDiscountsPanel(){
        setDiscountsTableModel();
    }

    private void setUsersTableModel(){

        DBConnect connect = DBConnect.getInstance();
        connect.openConnect();
        TableUsersModel tableUsersModel = new TableUsersModel(connect.getUsersList());
        connect.closeConnect();

        tableUsers.setModel(tableUsersModel);
        currentUser = null;
    }

    private void setDiscountsTableModel(){
        DBConnect connect = DBConnect.getInstance();
        connect.openConnect();
        TableDiscountsModel tableDiscountsModel = new TableDiscountsModel(connect.getDiscountList());
        connect.closeConnect();

        tableDiscounts.setModel(tableDiscountsModel);
        currentDiscount = null;
    }

    private void addNewUser() {
        JComponent[] components = new JComponent[10];

        JLabel labelNewUserLogin = new JLabel("User login:");
        JTextField textFieldNewUserLogin = new JTextField();
        textFieldNewUserLogin.setToolTipText("Enter new user login.");

        JLabel labelNewUserType = new JLabel("User type:");
        JComboBox comboBoxNewUserType = new JComboBox(UserType.values());
        comboBoxNewUserType.setToolTipText("Select the type of new user.");
        comboBoxNewUserType.setSelectedIndex(UserType.values().length - 2);

        JLabel labelNewUserPassword = new JLabel("Password:");
        JTextField textFieldNewUserPassword = new JTextField();
        textFieldNewUserPassword.setToolTipText("Enter new user password. Minimum 6 symbols.");

        JLabel labelNewUserComment = new JLabel("Comment:");
        JTextField textFieldNewUserComment = new JTextField();
        textFieldNewUserComment.setToolTipText("Enter comment.");

        JLabel labelCurrentUserPassword = new JLabel("<html><font color='red'>Administrator</font> password:</html>");
        JPasswordField passwordFieldCurrentUserPassword = new JPasswordField();
        passwordFieldCurrentUserPassword.setToolTipText("<html>Enter <font color='red'>yours</font>(administrator) password.</html>");

        JButton buttonCheck = new JButton("Create");
        buttonCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBConnect connect = DBConnect.getInstance();
                connect.openConnect();

                if(connect.verifyUser(textFieldNewUserLogin.getText(), "") != 0 || textFieldNewUserLogin.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Login exist!", "Login Error", JOptionPane.ERROR_MESSAGE);
                    textFieldNewUserLogin.grabFocus();
                    connect.closeConnect();
                    return;
                }

                if(textFieldNewUserPassword.getText().length() < 6){
                    JOptionPane.showMessageDialog(null, "Incorrect password! minimum 6 symbols!", "Password Error", JOptionPane.ERROR_MESSAGE);
                    textFieldNewUserPassword.grabFocus();
                    return;
                }

                if(connect.verifyUser(Constants.getUserName(), passwordFieldCurrentUserPassword.getText()) != 2){
                    JOptionPane.showMessageDialog(null, "Incorrect current(administrator) password!", "Password Error", JOptionPane.ERROR_MESSAGE);
                    passwordFieldCurrentUserPassword.grabFocus();
                    connect.closeConnect();
                    return;
                }

                connect.createNewUser(new User(textFieldNewUserLogin.getText(),
                        (UserType)comboBoxNewUserType.getSelectedItem(),
                        passwordFieldCurrentUserPassword.getText(),
                        textFieldNewUserComment.getText())
                );

                connect.closeConnect();

                JOptionPane.showMessageDialog(null, "New user created!", "OK", JOptionPane.INFORMATION_MESSAGE);

                textFieldNewUserLogin.setText("");
                textFieldNewUserLogin.grabFocus();
                comboBoxNewUserType.setSelectedIndex(UserType.values().length - 2);
                textFieldNewUserPassword.setText("");
                textFieldNewUserComment.setText("");
                passwordFieldCurrentUserPassword.setText("");

                setUsersTableModel();
            }
        });

        components[0] = labelNewUserLogin;
        components[1] = textFieldNewUserLogin;
        components[2] = labelNewUserType;
        components[3] = comboBoxNewUserType;
        components[4] = labelNewUserPassword;
        components[5] = textFieldNewUserPassword;
        components[6] = labelNewUserComment;
        components[7] = textFieldNewUserComment;
        components[8] = labelCurrentUserPassword;
        components[9] = passwordFieldCurrentUserPassword;

        JOptionPane.showOptionDialog(null, components, "Add new user?", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{buttonCheck}, null);

    }

    private void deleteUser() {
        if (currentUser != null){

            int output = JOptionPane.showConfirmDialog(null, "User: " + currentUser.getUserName(),
                    "Delete this User?", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (output == JOptionPane.YES_OPTION) {
                DBConnect connect = DBConnect.getInstance();
                connect.openConnect();
                connect.deleteUser(currentUser);
                connect.closeConnect();
                currentUser = null;
            }
        }
    }

    private void changeGlobalPrice(){

        JComponent[] components = new JComponent[5];

        JLabel labelOldPrice = new JLabel("<html>Old price: <font color='red'>" + Constants.PRICE + "</font></html>");

        JLabel labelNewPrice = new JLabel("New price:");
        JTextField textFieldNewPrice = new JTextField();
        textFieldNewPrice.setToolTipText("Enter new price per hour. Example:  50  or  45.0");

        JLabel labelPassword = new JLabel("Password:");
        JPasswordField passwordFieldPassword = new JPasswordField();
        passwordFieldPassword.setToolTipText("Enter password to confirm operation.");

        components[0] = labelOldPrice;
        components[1] = labelNewPrice;
        components[2] = textFieldNewPrice;
        components[3] = labelPassword;
        components[4] = passwordFieldPassword;

        int input = JOptionPane.showConfirmDialog(null, components, "Change price?", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (input == JOptionPane.YES_OPTION) {
            double newPrice;
            try {
                newPrice = Double.parseDouble(textFieldNewPrice.getText());

                DBConnect connect = DBConnect.getInstance();
                connect.openConnect();
                connect.setGlobalPrice(newPrice, passwordFieldPassword.getText());
                connect.closeConnect();

                JOptionPane.showMessageDialog(null, "Please reboot program!", "", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception exp) {
                JOptionPane.showMessageDialog(null, "input error", "error", JOptionPane.ERROR_MESSAGE);
                changeGlobalPrice();
            }
        }
    }

    private void addDiscount(){
        JComponent[] components = new JComponent[6];

        JLabel labelDiscount = new JLabel("Discount %:");
        JTextField textFieldDiscount = new JTextField();
        textFieldDiscount.setToolTipText("Enter discount % value.  Example:  5  or  4.2");

        JLabel labelComment = new JLabel("Comment:");
        JTextField textFieldComment = new JTextField();
        textFieldComment.setToolTipText("Enter comment.");

        JLabel labelUserType = new JLabel("User type:");
        JComboBox comboBoxUserType = new JComboBox(UserType.values());
        comboBoxUserType.setToolTipText("Select the type of user, which will be available discount.");

        components[0] = labelDiscount;
        components[1] = textFieldDiscount;
        components[2] = labelComment;
        components[3] = textFieldComment;
        components[4] = labelUserType;
        components[5] = comboBoxUserType;

        int input = JOptionPane.showConfirmDialog(null, components, "New discount:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (input == JOptionPane.YES_OPTION) {
            try {
                double discount = Double.parseDouble(textFieldDiscount.getText());

                DBConnect connect = DBConnect.getInstance();
                connect.openConnect();
                connect.setDiscount(discount, textFieldComment.getText(), (UserType) comboBoxUserType.getSelectedItem());
                connect.closeConnect();

            }catch (Exception e){
                JOptionPane.showMessageDialog(null, "Please input correct discount!" + "\n" + "Example:  5  or  4.2", "Discount enter error", JOptionPane.ERROR_MESSAGE);
                addDiscount();
            }
        }

    }

    private void deleteDiscount(){
        if (currentDiscount != null){

            int output = JOptionPane.showConfirmDialog(null, "Discount: " + currentDiscount.getDiscount() +
                    "  User type: " + currentDiscount.getUserType(),
                    "Delete this discount?", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (output == JOptionPane.YES_OPTION) {
                DBConnect connect = DBConnect.getInstance();
                connect.openConnect();
                connect.deleteDiscount(currentDiscount);
                connect.closeConnect();
                currentDiscount = null;
            }
        }


    }

    private void switchDiscount(){
        if (currentDiscount != null){

            int output = JOptionPane.showConfirmDialog(null, "Discount: " + currentDiscount.getDiscount() +
                            "  User type: " + currentDiscount.getUserType(),
                    "Switch this discount?", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (output == JOptionPane.YES_OPTION) {
                DBConnect connect = DBConnect.getInstance();
                connect.openConnect();
                connect.switchDiscount(currentDiscount);
                connect.closeConnect();
                currentDiscount = null;
            }
        }
    }

    private class buttonChangeUserActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Main.loginFrameController.showLoginFrameWindow();
            optionsFrame.setVisible(false);
        }
    }

    private class buttonMenuChangeUserActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            setContextChangeUserPanel();
            showContextPanel("CardChangeUser");
        }
    }

    private class buttonMenuPriceActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            setContextPricePanel();
            showContextPanel("CardPrice");

        }
    }

    private class buttonPriceChangePerformActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(Constants.getUserType().equals(UserType.ADMIN.toString())){
                changeGlobalPrice();
            }else {
                JOptionPane.showMessageDialog(null, "Only Administrator can change Global Price.", "Access error", JOptionPane.INFORMATION_MESSAGE);
            }

        }
    }

    private class buttonMenuDiscountsActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            setContextDiscountsPanel();
            showContextPanel("CardDiscounts");
        }
    }

    private class buttonAddDiscountActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(Constants.getUserType().equals(UserType.ADMIN.toString())) {
                addDiscount();
                setDiscountsTableModel();
            }else {
                JOptionPane.showMessageDialog(null, "Only Administrator can add new discounts.", "Access error", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private class buttonDeleteDiscountActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(Constants.getUserType().equals(UserType.ADMIN.toString())) {
                if (currentDiscount != null) {
                    deleteDiscount();
                    setDiscountsTableModel();
                }else{
                    JOptionPane.showMessageDialog(null, "Select item in the table!", "Select error", JOptionPane.INFORMATION_MESSAGE);
                }
            }else {
                JOptionPane.showMessageDialog(null, "Only Administrator can delete discounts.", "Access error", JOptionPane.INFORMATION_MESSAGE);
            }

        }
    }

    private class tableDiscountsMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            int row = tableDiscounts.getSelectedRow();
            double discountValue = (double) tableDiscounts.getValueAt(row, 0);
            String commentValue = (String) tableDiscounts.getValueAt(row, 1);
            String userTypeValue = (String) tableDiscounts.getValueAt(row, 2);
            int isActiveValue;
            if (tableDiscounts.getValueAt(row, 3).equals("+")){
                isActiveValue = 1;
            }else{
                isActiveValue = 0;
            }

            currentDiscount = new Discount(discountValue, commentValue, userTypeValue, isActiveValue);

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    private class buttonSwitchDiscountActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(Constants.getUserType().equals(UserType.ADMIN.toString())) {
                if (currentDiscount != null) {
                    switchDiscount();
                    setDiscountsTableModel();
                }else{
                    JOptionPane.showMessageDialog(null, "Select item in the table!", "Select error", JOptionPane.INFORMATION_MESSAGE);
                }
            }else {
                JOptionPane.showMessageDialog(null, "Only Administrator can switch activity of discounts.", "Access error", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private class buttonAddNewUserActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            addNewUser();
        }
    }

    private class buttonDeleteExistUserActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(Constants.getUserType().equals(UserType.ADMIN.toString())) {
                if (currentUser != null) {
                    deleteUser();
                    setUsersTableModel();
                }else{
                    JOptionPane.showMessageDialog(null, "Select item in the table!", "Select error", JOptionPane.INFORMATION_MESSAGE);
                }
            }else {
                JOptionPane.showMessageDialog(null, "Only Administrator can delete users.", "Access error", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private class tableUsersMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            int row = tableUsers.getSelectedRow();
            String userName = (String) tableUsers.getValueAt(row, 0);
            UserType userType = (UserType) tableUsers.getValueAt(row, 1);
            String commentValue = (String) tableUsers.getValueAt(row, 2);

            currentUser = new User(userName, userType, "", commentValue);
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}
