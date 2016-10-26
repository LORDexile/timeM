package com.arcon.ui.controller;

import com.arcon.ui.model.ActionType;
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

    private ActionType actionType;
    private boolean isComponentsNotShown = true;

    public IOFrameController() {
        initComponents();
        initListeners();
    }

    public void showIOFrameWindow() {
        ioFrame.setVisible(true);
        showComponents();
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
        buttonPerform.addActionListener(new buttonPerformActionListener());

        radioButtonInput.addActionListener(new radioButtonInputActionListener());
    }

    private void cancelAction() {
        textFieldCash.setText("");
        textAreaComment.setText("");
        passwordFieldPassword.setText("");
        showComponents();

        ioFrame.setVisible(false);
    }

    private void performAction() {
        //Action
    }

    private void showComponents() {
        textFieldCash.setEnabled(isComponentsNotShown);
        textAreaComment.setEnabled(isComponentsNotShown);
        passwordFieldPassword.setEnabled(isComponentsNotShown);
        buttonPerform.setEnabled(isComponentsNotShown);
        isComponentsNotShown = !isComponentsNotShown;
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

    private class buttonPerformActionListener implements ActionListener {
        /**
         * Invoked when an action occurs.
         *
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            performAction();
        }
    }

    private class radioButtonInputActionListener implements ActionListener {
        /**
         * Invoked when an action occurs.
         *
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            actionType = ActionType.MONEY_INPUT;
        }
    }
}
