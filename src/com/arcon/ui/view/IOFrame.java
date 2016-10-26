package com.arcon.ui.view;

import javax.swing.*;

public class IOFrame extends JFrame{
    private JPanel panelMainBorder;
    private JPanel panelMainCenter;
    private JTextField textFieldCash;
    private JTextArea textAreaComment;
    private JPasswordField passwordFieldPassword;
    private JButton buttonPerform;
    private JButton buttonCancel;
    private JRadioButton radioButtonInput;
    private JRadioButton radioButtonOutput;
    private ButtonGroup buttonGroupIO = new ButtonGroup();

    private JLabel labelTransactionMethod;

    public IOFrame() {
        setSize(430,200);
        setContentPane(this.panelMainBorder);
        setLocationRelativeTo(null);
        setTitle("Input / Output transactions");
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setResizable(true);

        buttonGroupIO.add(radioButtonInput);
        buttonGroupIO.add(radioButtonOutput);

        pack();
        setSize(430,200);
    }

    public JTextField getTextFieldCash() {
        return textFieldCash;
    }

    public JTextArea getTextAreaComment() {
        return textAreaComment;
    }

    public JPasswordField getPasswordFieldPassword() {
        return passwordFieldPassword;
    }

    public JButton getButtonPerform() {
        return buttonPerform;
    }

    public JButton getButtonCancel() {
        return buttonCancel;
    }

    public JRadioButton getRadioButtonOutput() {
        return radioButtonOutput;
    }

    public JRadioButton getRadioButtonInput() {
        return radioButtonInput;
    }

    public ButtonGroup getButtonGroupIO() {
        return buttonGroupIO;
    }

    public JLabel getLabelTransactionMethod() {
        return labelTransactionMethod;
    }
}
