package com.arcon.ui.controller;

import com.arcon.Main;
import com.arcon.db.DBConnect;
import com.arcon.ui.view.LoginFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class LoginFrameController{

    private LoginFrame loginFrame;

    private JTextField textFieldLogin;
    private JPasswordField passwordFieldPassword;
    private JButton buttonMainOK;


    public LoginFrameController() {
        initComponents();
        initListeners();
    }


    public void showLoginFrameWindow(){
        loginFrame.setVisible(true);

    }

    //initialize component`s
    private void initComponents() {
        loginFrame = new LoginFrame();

        buttonMainOK = loginFrame.getButtonMainOK();
        textFieldLogin = loginFrame.getTextFieldLogin();
        passwordFieldPassword = loginFrame.getPasswordFieldPassword();
    }

    //initialize listeners
    private void initListeners() {
        buttonMainOK.addActionListener(new buttonMainOKListener());
        textFieldLogin.addKeyListener(new textFieldLoginListener());
        passwordFieldPassword.addKeyListener(new passwordFieldListener());
    }

    //verification user login\password
    private void verification () {
        String msg = "";
        int userVerify;
        String login = textFieldLogin.getText();
        String pass = passwordFieldPassword.getText();

        DBConnect connect = DBConnect.getInstance();
        connect.openConnect();
        userVerify = connect.verifyUser(login, pass);
        connect.closeConnect();

        switch (userVerify) {
            case 0:
                msg = "Пользователь " + login + " не существует.";
                break;
            case 1:
                msg = "Пароль не подходит";
                break;
            case 2:
                loginFrame.setVisible(false);
                Main.mainFrameController.showMainFrameWindow();
                break;
        }

        if (!msg.equals("")) {
            JOptionPane.showMessageDialog(null, msg, "Внимание!", JOptionPane.ERROR_MESSAGE);
        }

        passwordFieldPassword.setText("");
        passwordFieldPassword.requestFocus();
    }

    //press button to do verification
    private class buttonMainOKListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            verification();
        }
    }

    //press Enter on textFieldLogin to set focus on passwordTextPassword
    private class textFieldLoginListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getExtendedKeyCode() == 10) {
                passwordFieldPassword.requestFocus();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }

    //press Enter on passwordTextPassword to do verification
    private class passwordFieldListener implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {

        }
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getExtendedKeyCode() == 10) {
                verification();
            }
        }
        @Override
        public void keyReleased(KeyEvent e) {

        }
    }
}
