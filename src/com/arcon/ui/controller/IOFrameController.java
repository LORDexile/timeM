package com.arcon.ui.controller;

import com.arcon.Main;
import com.arcon.db.DBConnect;
import com.arcon.lib.Constants;
import com.arcon.ui.model.ActionType;
import com.arcon.ui.view.IOFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class IOFrameController {
    private IOFrame ioFrame;

    private JTextField textFieldCash;
    private JTextArea textAreaComment;
    private JPasswordField passwordFieldPassword;

    private JButton buttonPerform;
    private JButton buttonCancel;

    private JRadioButton radioButtonInput;
    private JRadioButton radioButtonOutput;
    private ButtonGroup buttonGroupIO;

    private JLabel labelTransactionMethod;

    private ActionType actionType;
    private boolean isComponentsHide = true;
    private boolean isIOMethodSelected = false;

    public IOFrameController() {
        initComponents();
        initListeners();
    }

    public void showIOFrameWindow() {
        cancelAction();
        ioFrame.setVisible(true);
    }

    private void initComponents() {
        ioFrame = new IOFrame();
        textFieldCash = ioFrame.getTextFieldCash();
        textAreaComment = ioFrame.getTextAreaComment();
        passwordFieldPassword = ioFrame.getPasswordFieldPassword();
        buttonPerform = ioFrame.getButtonPerform();
        buttonCancel = ioFrame.getButtonCancel();

        buttonGroupIO = ioFrame.getButtonGroupIO();
        radioButtonInput = ioFrame.getRadioButtonInput();
        radioButtonOutput = ioFrame.getRadioButtonOutput();

        labelTransactionMethod = ioFrame.getLabelTransactionMethod();

    }

    private void initListeners() {
        buttonCancel.addActionListener(new buttonCancelActionListener());
        buttonPerform.addActionListener(new buttonPerformActionListener());

        radioButtonInput.addActionListener(new radioButtonInputActionListener());
        radioButtonOutput.addActionListener(new radioButtonOutputActionListener());

        textFieldCash.addKeyListener(new textFieldCashKeyListener());
    }

    private void cancelAction() {
        if (isIOMethodSelected) {

            textFieldCash.setText("");
            textAreaComment.setText("");
            passwordFieldPassword.setText("");
            labelTransactionMethod.setText("SELECT!");
            isIOMethodSelected = false;
            buttonGroupIO.clearSelection();
            showComponents();
        }

        ioFrame.setVisible(false);

    }

    private void performAction() {
        int cash = Integer.parseInt(textFieldCash.getText());
        String comment = textAreaComment.getText();
        String password = passwordFieldPassword.getText();

        if (cash > 0 && !comment.equals("")) {

            DBConnect connect = DBConnect.getInstance();
            connect.openConnect();

            switch (connect.verifyUser(Constants.getUserName(), password)) {
                case 1:
                    JOptionPane.showMessageDialog(null, "Password incorrect!", "error", JOptionPane.ERROR_MESSAGE);
                    passwordFieldPassword.requestFocus();
                    break;
                case 2:
                    if (radioButtonInput.isSelected()) {
                        connect.setTransaction(cash, actionType, comment);
                    } else if (radioButtonOutput.isSelected()) {
                        cash = -cash;
                        connect.setTransaction(cash, actionType, comment);
                    }
                    cancelAction();
                    break;
            }

            Main.mainFrameController.updateCounters(connect);
            connect.closeConnect();

        }else if (comment.equals("")) {
            JOptionPane.showMessageDialog(null, "comment field must be filled!", "error", JOptionPane.ERROR_MESSAGE);
            textAreaComment.requestFocus();
        }else if(cash < 0) {
            JOptionPane.showMessageDialog(null, "cash field must be positive!", "error", JOptionPane.ERROR_MESSAGE);
            textFieldCash.requestFocus();
        }
    }

    private void showComponents() {
        textFieldCash.setEnabled(isComponentsHide);
        textAreaComment.setEnabled(isComponentsHide);
        passwordFieldPassword.setEnabled(isComponentsHide);
        buttonPerform.setEnabled(isComponentsHide);
        isComponentsHide = !isComponentsHide;
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
            labelTransactionMethod.setText("INPUT transaction");

            if (!isIOMethodSelected) {
                isIOMethodSelected = true;
                showComponents();
            }
        }
    }

    private class radioButtonOutputActionListener implements ActionListener {
        /**
         * Invoked when an action occurs.
         *
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            actionType = ActionType.MONEY_OUTPUT;
            labelTransactionMethod.setText("OUTPUT transaction");

            if (!isIOMethodSelected) {
                isIOMethodSelected = true;
                showComponents();
            }
        }
    }

    //TODO create new class that contains like this methods
    private class textFieldCashKeyListener implements KeyListener {
        /**
         * Invoked when a key has been typed.
         * See the class description for {@link KeyEvent} for a definition of
         * a key typed event.
         *
         * @param e
         */
        @Override
        public void keyTyped(KeyEvent e) {

        }

        /**
         * Invoked when a key has been pressed.
         * See the class description for {@link KeyEvent} for a definition of
         * a key pressed event.
         *
         * @param e
         */
        @Override
        public void keyPressed(KeyEvent e) {

        }

        /**
         * Invoked when a key has been released.
         * See the class description for {@link KeyEvent} for a definition of
         * a key released event.
         *
         * @param e
         */
        @Override
        public void keyReleased(KeyEvent e) {
            char c = e.getKeyChar();
            if(!e.isActionKey()) {
                if (!(c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9')) {

                    String s = textFieldCash.getText();
                    String newS = "";
                    for (int i = 0; i < s.length(); i ++) {
                        if (s.charAt(i) != c) newS += s.charAt(i);
                    }
                    textFieldCash.setText(newS);

                }
            }
        }
    }
}
