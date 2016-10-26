package com.arcon.ui.controller;

import com.arcon.ui.view.IOFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        buttonCancel.addActionListener(new buttonCancelActionListener());
    }

    private void cancelAction() {
        textFieldCash.setText("");
        textFieldCash.setEnabled(false);
        textAreaComment.setText("");
        textAreaComment.setEnabled(false);
        passwordFieldPassword.setText("");
        passwordFieldPassword.setEnabled(false);
        buttonPerform.setEnabled(false);

        ioFrame.setVisible(false);
    }

    private class buttonCancelActionListener implements ActionListener {
        /**
         * Invoked when an action occurs.
         *
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            cancelAction();
        }
    }
}
