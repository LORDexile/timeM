package com.arcon.ui.controller;

import com.arcon.Main;
import com.arcon.db.DBConnect;
import com.arcon.lib.Constants;
import com.arcon.ui.model.TableDiscountsModel;
import com.arcon.ui.model.UserType;
import com.arcon.ui.view.OptionsFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionsFrameController {
    OptionsFrame optionsFrame;

    private JButton buttonMenuChangeUser;
    private JButton buttonMenuPrice;
    private JButton buttonMenuDiscounts;
    private JButton buttonMenuUsers;
    private JButton buttonMenuProgram;

    private JPanel panelContextMain;

    private JLabel labelUserName;
    private JLabel labelUserType;
    private JButton buttonChangeUser;

    private JLabel labelPrice;
    private JTextField textFieldNewPrice;
    private JPasswordField passwordFieldPassword;
    private JButton buttonPriceChangePerform;
    private JLabel jLabelNewPrice;
    private JLabel jLabelPassword;

    private JTable tableDiscounts;
    private JButton buttonAddDiscount;
    private JButton buttonDeleteDiscount;

    private boolean isPriceContextVisible = false;


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

        buttonMenuPrice = optionsFrame.getButtonMenuPrice();
        labelPrice = optionsFrame.getLabelPrice();
        textFieldNewPrice = optionsFrame.getTextFieldNewPrice();
        passwordFieldPassword = optionsFrame.getPasswordFieldPassword();
        buttonPriceChangePerform = optionsFrame.getButtonPriceChangePerform();
        jLabelNewPrice = optionsFrame.getjLabelNewPrice();
        jLabelPassword = optionsFrame.getjLabelPassword();

        buttonMenuDiscounts = optionsFrame.getButtonMenuDiscounts();
        tableDiscounts = optionsFrame.getTableDiscounts();
        buttonAddDiscount = optionsFrame.getButtonAddDiscount();
        buttonDeleteDiscount = optionsFrame.getButtonDeleteDiscount();
    }

    private void initListeners() {

        buttonMenuChangeUser.addActionListener(new buttonMenuChangeUserActionListener());
        buttonMenuPrice.addActionListener(new buttonMenuPriceActionListener());

        buttonChangeUser.addActionListener(new buttonChangeUserActionListener());
        buttonPriceChangePerform.addActionListener(new buttonPriceChangePerformActionListener());

        buttonMenuDiscounts.addActionListener(new buttonMenuDiscountsActionListener());
    }

    public void showOptionsFrameWindow() {
        optionsFrame.setVisible(true);
        setContextChangeUserPanelUserInfo();
    }

    private void setContextChangeUserPanelUserInfo(){
        labelUserName.setText("<html><font color='red'>" + Constants.getUserName() + "</font></html>");
        labelUserType.setText("<html><font color='red'>" + Constants.getUserType() + "</font></html>");
    }

    private void setContextPricePanel(){
        labelPrice.setText((String.valueOf(Constants.PRICE)));
        if (!Constants.getUserType().equals(UserType.ADMIN.toString())){
            setUserTypeContextPrice();
        }
    }

    private void showContextPanel(String panel){
        CardLayout cl = (CardLayout)(panelContextMain.getLayout());
        cl.show(panelContextMain, panel);
    }

    private void setUserTypeContextPrice(){
        jLabelNewPrice.setVisible(isPriceContextVisible);
        textFieldNewPrice.setVisible(isPriceContextVisible);
        jLabelPassword.setVisible(isPriceContextVisible);
        passwordFieldPassword.setVisible(isPriceContextVisible);
        buttonPriceChangePerform.setVisible(isPriceContextVisible);
        isPriceContextVisible = !isPriceContextVisible;
    }

    private void setContextDiscountsTableModel(){
        DBConnect connect = DBConnect.getInstance();
        connect.openConnect();
        TableDiscountsModel tableDiscountsModel = new TableDiscountsModel(connect.getDiscountList());
        connect.closeConnect();

        tableDiscounts.setModel(tableDiscountsModel);
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
            setContextChangeUserPanelUserInfo();
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
            double newPrice;
            try {
                newPrice = Double.parseDouble(textFieldNewPrice.getText());

                DBConnect connect = DBConnect.getInstance();
                connect.openConnect();
                connect.setGlobalPrice(newPrice, passwordFieldPassword.getText());

                JOptionPane.showMessageDialog(null, "Please reboot program!", "", JOptionPane.INFORMATION_MESSAGE);
            }catch (Exception exp){
                JOptionPane.showMessageDialog(null, "input error", "error", JOptionPane.ERROR_MESSAGE);
                textFieldNewPrice.setText("");
                textFieldNewPrice.requestFocus();
            }
            textFieldNewPrice.setText("");
            passwordFieldPassword.setText("");

        }
    }

    private class buttonMenuDiscountsActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            setContextDiscountsTableModel();
            showContextPanel("CardDiscounts");
        }
    }
}
