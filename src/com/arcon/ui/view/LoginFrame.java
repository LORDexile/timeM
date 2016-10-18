package com.arcon.ui.view;

import javax.swing.*;


public class LoginFrame extends  JFrame{

    private JPanel panelMain;

    private JTextField textFieldLogin;
    private JPasswordField passwordFieldPassword;
    private JButton buttonMainOK;

    //constructor
    public LoginFrame() {
        setSize(250,150);
        setContentPane(this.panelMain);
        setLocationRelativeTo(null);
        setTitle("Пожалуйста Авторизируйтесь!");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setSize(250,150);
    }

    public JButton getButtonMainOK() {
        return buttonMainOK;
    }

    public JTextField getTextFieldLogin() {
        return textFieldLogin;
    }

    public JPasswordField getPasswordFieldPassword() {
        return passwordFieldPassword;
    }
}
