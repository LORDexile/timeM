package com.arcon.ui.view;

import com.arcon.lib.Constants;

import javax.swing.*;


public class LoginFrame extends  JFrame implements Constants {

    private JPanel panelMain;

    private JTextField textFieldLogin;
    private JPasswordField passwordFieldPassword;
    private JButton buttonMainOK;

    //constructor
    public LoginFrame() {
        setSize(500,500);
        setContentPane(this.panelMain);
        setLocationRelativeTo(null);
        setTitle("Пожалуйста Авторизируйтесь!");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
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
