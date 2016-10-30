package com.arcon.ui.view;

import javax.swing.*;

public class OptionsFrame extends JFrame{
    private JPanel panelMain;
    private JPanel panelMenu;
    private JPanel panelContextMain;
    private JPanel panelContextChangeUser;
    private JPanel panelContextPrice;

    private JButton buttonMenuChangeUser;
    private JButton buttonMenuPrice;
    private JButton buttonMenuDiscounts;
    private JButton buttonMenuUsers;
    private JButton buttonMenuProgram;

    private JLabel labelUserName;
    private JLabel labelUserType;
    private JButton buttonChangeUser;

    private JLabel labelPrice;
    private JTextField textFieldNewPrice;
    private JPasswordField passwordFieldPassword;
    private JButton buttonPriceChangePerform;
    private JLabel jLabelNewPrice;
    private JLabel jLabelPassword;

    public OptionsFrame() {

        setSize(800, 500);
        setContentPane(this.panelMain);
        setLocationRelativeTo(null);
        setTitle("Options");
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setResizable(true);
        setVisible(false);
        pack();
        setSize(800, 500);

    }

    public JPanel getPanelContextMain() {
        return panelContextMain;
    }

    public JButton getButtonMenuChangeUser() {
        return buttonMenuChangeUser;
    }

    public JButton getButtonMenuPrice() {
        return buttonMenuPrice;
    }

    public JButton getButtonMenuDiscounts() {
        return buttonMenuDiscounts;
    }

    public JButton getButtonMenuUsers() {
        return buttonMenuUsers;
    }

    public JButton getButtonMenuProgram() {
        return buttonMenuProgram;
    }

    public JLabel getLabelUserName() {
        return labelUserName;
    }

    public JLabel getLabelUserType() {
        return labelUserType;
    }

    public JButton getButtonChangeUser() {
        return buttonChangeUser;
    }

    public JLabel getLabelPrice() {
        return labelPrice;
    }

    public JTextField getTextFieldNewPrice() {
        return textFieldNewPrice;
    }

    public JPasswordField getPasswordFieldPassword() {
        return passwordFieldPassword;
    }

    public JButton getButtonPriceChangePerform() {
        return buttonPriceChangePerform;
    }

    public JLabel getjLabelPassword() {
        return jLabelPassword;
    }

    public JLabel getjLabelNewPrice() {
        return jLabelNewPrice;
    }
}
