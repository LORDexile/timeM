package com.arcon.ui.controller;

import com.arcon.Main;
import com.arcon.db.DBConnect;
import com.arcon.lib.Constants;
import com.arcon.ui.model.Discount;
import com.arcon.ui.model.TableDiscountsModel;
import com.arcon.ui.model.UserType;
import com.arcon.ui.view.OptionsFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
    private JButton buttonChangeExistUser;
    private JButton buttonDeleteExistUser;

    private JLabel labelPrice;
    private JButton buttonPriceChangePerform;

    private JTable tableDiscounts;
    private JButton buttonAddDiscount;
    private JButton buttonDeleteDiscount;
    private JButton buttonSwitchDiscount;

    private Discount currentDiscount;


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
        buttonChangeExistUser = optionsFrame.getButtonChangeExistUser();
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

    private void setContextChangeUserPanel(){
        labelUserName.setText("<html><font color='red'>" + Constants.getUserName() + "</font></html>");
        labelUserType.setText("<html><font color='red'>" + Constants.getUserType() + "</font></html>");
        if(Constants.getUserType().equals(UserType.ADMIN.toString())){
            scrollPaneUsers.setVisible(true);
            buttonChangeExistUser.setVisible(true);
            buttonAddNewUser.setVisible(true);
            buttonDeleteExistUser.setVisible(true);
        }else{
            if(scrollPaneUsers.isVisible()){
                scrollPaneUsers.setVisible(false);
                buttonChangeExistUser.setVisible(false);
                buttonAddNewUser.setVisible(false);
                buttonDeleteExistUser.setVisible(false);
            }
        }
    }

    private void setContextPricePanel(){
        labelPrice.setText((String.valueOf(Constants.PRICE)));
    }

    private void showContextPanel(String panel){
        CardLayout cl = (CardLayout)(panelContextMain.getLayout());
        cl.show(panelContextMain, panel);
    }

    private void setContextDiscountsTableModel(){
        DBConnect connect = DBConnect.getInstance();
        connect.openConnect();
        TableDiscountsModel tableDiscountsModel = new TableDiscountsModel(connect.getDiscountList());
        connect.closeConnect();

        tableDiscounts.setModel(tableDiscountsModel);
        currentDiscount = null;
    }

    private void changeGlobalPrice(){

        JComponent[] components = new JComponent[6];

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
            setContextDiscountsTableModel();
            showContextPanel("CardDiscounts");
        }
    }

    private class buttonAddDiscountActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(Constants.getUserType().equals(UserType.ADMIN.toString())) {
                addDiscount();
                setContextDiscountsTableModel();
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
                    setContextDiscountsTableModel();
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
                    setContextDiscountsTableModel();
                }else{
                    JOptionPane.showMessageDialog(null, "Select item in the table!", "Select error", JOptionPane.INFORMATION_MESSAGE);
                }
            }else {
                JOptionPane.showMessageDialog(null, "Only Administrator can switch activity of discounts.", "Access error", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
