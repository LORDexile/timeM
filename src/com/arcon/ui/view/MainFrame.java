package com.arcon.ui.view;


import com.arcon.lib.Constants;

import javax.swing.*;

public class MainFrame extends JFrame{

    private JPanel mainPanel;
    private JTabbedPane tabbedPaneMain;
    private JPanel tabPanelMain;
    private JPanel tabPanelIO;
    private JPanel tabPanelOptions;
    private JPanel tabPanelLog;

    private JTextField textFieldCard;
    private JButton buttonPerformCard;
    private JRadioButton radioButtonInput;
    private JRadioButton radioButtonOutput;
    private JTextField textFieldIOCash;
    private JButton buttonIOAction;
    private JTextArea textAreaComment;
    private JPasswordField passwordFieldPassword;
    private JButton buttonCangeUser;
    private JButton changePriceButton;
    private JPanel tabPanelMainSource;
    private JComboBox comboBoxDiscount;
    private JTextField textFieldCash;
    private JLabel jLabelTimeIn;
    private JLabel jLabelTimeOut;
    private JLabel jLabelTimeTotal;
    private JLabel jLabelPrice;


    //constructor
    public MainFrame() {
        setSize(800, 500);
        setContentPane(this.mainPanel);
        setLocationRelativeTo(null);
        setTitle(Constants.PROGRAM_TITLE);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(true);
        setVisible(false);
        pack();
        setSize(800, 500);

    }

    public JTabbedPane getTabbedPaneMain() {
        return tabbedPaneMain;
    }

    public JTextField getTextFieldCard() {
        return textFieldCard;
    }

    public JButton getButtonPerformCard() {
        return buttonPerformCard;
    }

    public JRadioButton getRadioButtonInput() {
        return radioButtonInput;
    }

    public JRadioButton getRadioButtonOutput() {
        return radioButtonOutput;
    }

    public JTextField getTextFieldIOCash() {
        return textFieldIOCash;
    }

    public JButton getButtonIOAction() {
        return buttonIOAction;
    }

    public JTextArea getTextAreaComment() {
        return textAreaComment;
    }

    public JPasswordField getPasswordFieldPassword() {
        return passwordFieldPassword;
    }

    public JButton getButtonCangeUser() {
        return buttonCangeUser;
    }

    public JButton getChangePriceButton() {
        return changePriceButton;
    }

    public JPanel getTabPanelMainSource() {
        return tabPanelMainSource;
    }

    public JComboBox getComboBoxDiscount() {
        return comboBoxDiscount;
    }

    public JTextField getTextFieldCash() {
        return textFieldCash;
    }

    public JLabel getjLabelTimeIn() {
        return jLabelTimeIn;
    }

    public JLabel getjLabelTimeOut() {
        return jLabelTimeOut;
    }

    public JLabel getjLabelTimeTotal() {
        return jLabelTimeTotal;
    }

    public JLabel getjLabelPrice() {
        return jLabelPrice;
    }
}
