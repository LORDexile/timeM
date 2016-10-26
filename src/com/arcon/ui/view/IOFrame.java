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
    private JRadioButton radioButtonOutput;
    private JRadioButton radioButtonInpute;

    public IOFrame() {
        setSize(350,500);
        setContentPane(this.panelMainBorder);
        setLocationRelativeTo(null);
        setTitle("Input / Output transactions");
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        pack();
        setSize(350,500);
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

    public JRadioButton getRadioButtonInpute() {
        return radioButtonInpute;
    }
}
