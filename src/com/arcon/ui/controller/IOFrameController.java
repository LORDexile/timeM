package com.arcon.ui.controller;

import com.arcon.ui.view.IOFrame;

import javax.swing.*;

public class IOFrameController {
    private IOFrame ioFrame;

    private JTextField textFieldCash;
    private JTextArea textAreaComment;
    private JPasswordField passwordFieldPassword;

    private JButton buttonPerform;
    private JButton buttonCancel;

    private JRadioButton radioButtonInput;
    private JRadioButton radioButtonOutput;

    public IOFrameController() {
        initComponents();
        initListeners();
    }

    public void showIOFrameWindow() {
        ioFrame.setVisible(true);
    }

    private void initComponents() {
        ioFrame = new IOFrame();
        textFieldCash = ioFrame.getTextFieldCash();
        textAreaComment = ioFrame.getTextAreaComment();
        passwordFieldPassword = ioFrame.getPasswordFieldPassword();
        buttonPerform = ioFrame.getButtonPerform();
        buttonCancel = ioFrame.getButtonCancel();
        radioButtonInput = ioFrame.getRadioButtonInput();
        radioButtonOutput = ioFrame.getRadioButtonOutput();
    }

    private void initListeners() {

    }



}
