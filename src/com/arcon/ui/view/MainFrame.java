package com.arcon.ui.view;

import com.arcon.lib.Constants;

import javax.swing.*;

public class MainFrame extends JFrame{

    private JPanel mainPanel;
    private JPanel mainPanelCenter;
    private JPanel tabPanelMainSource;
    private JToolBar toolBarMenu;
    private JPanel mainPanelSouth;

    private JTextField textFieldCard;
    private JButton buttonPerformCard;
    private JComboBox comboBoxDiscount;
    private JCheckBox checkBoxDiscount;
    private JTextField textFieldCash;
    private JLabel jLabelTimeIn;
    private JLabel jLabelTimeOut;
    private JLabel jLabelTimeTotal;
    private JLabel jLabelPrice;
    private JLabel jLabelTotalCards;
    private JLabel jLabelMoney;

    private JLabel labelTextTimeIn;
    private JLabel labelTextTimeOut;
    private JLabel labelTextTimeTotal;
    private JLabel labelTextDiscount;
    private JLabel labelTextPrice;
    private JLabel labelTextCash;
    private JLabel labelTextTotalCard;
    private JLabel labelTextMoney;

    private JButton buttonIO;
    private JButton buttonOptions;
    private JButton buttonLog;
    private JButton buttonCancel;

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

    public JTextField getTextFieldCard() {
        return textFieldCard;
    }

    public JTextField getTextFieldCash() {
        return textFieldCash;
    }

    public JButton getButtonPerformCard() {
        return buttonPerformCard;
    }

    public JButton getButtonCancel() {
        return buttonCancel;
    }

    public JButton getButtonIO() {
        return buttonIO;
    }

    public JButton getButtonOptions() {
        return buttonOptions;
    }

    public JButton getButtonLog() {
        return buttonLog;
    }

    public JComboBox getComboBoxDiscount() {
        return comboBoxDiscount;
    }

    public JCheckBox getCheckBoxDiscount() {
        return checkBoxDiscount;
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

    public JLabel getjLabelTotalCards() {
        return jLabelTotalCards;
    }

    public JLabel getjLabelMoney() {
        return jLabelMoney;
    }

    public JLabel getLabelTextTimeIn() {
        return labelTextTimeIn;
    }

    public JLabel getLabelTextTimeOut() {
        return labelTextTimeOut;
    }

    public JLabel getLabelTextTimeTotal() {
        return labelTextTimeTotal;
    }

    public JLabel getLabelTextDiscount() {
        return labelTextDiscount;
    }

    public JLabel getLabelTextPrice() {
        return labelTextPrice;
    }

    public JLabel getLabelTextCash() {
        return labelTextCash;
    }

    public JLabel getLabelTextTotalCard() {
        return labelTextTotalCard;
    }

    public JLabel getLabelTextMoney() {
        return labelTextMoney;
    }
}