package com.arcon.ui.view;


import com.arcon.lib.Constants;

import javax.swing.*;

public class MainFrame extends JFrame{

    private JPanel mainPanel;
    private JPanel PanelMain;
    private JPanel tabPanelMainSource;

    private JTextField textFieldCard;
    private JButton buttonPerformCard;
    private JComboBox comboBoxDiscount;
    private JCheckBox checkBoxDiscount;
    private JTextField textFieldCash;
    private JLabel jLabelTimeIn;
    private JLabel jLabelTimeOut;
    private JLabel jLabelTimeTotal;
    private JLabel jLabelPrice;
    private JToolBar toolBarMenu;
    private JButton buttonCardInput;
    private JButton buttonIO;
    private JButton buttonOptions;
    private JButton battonLog;



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

    public JButton getButtonPerformCard() {
        return buttonPerformCard;
    }

    public JComboBox getComboBoxDiscount() {
        return comboBoxDiscount;
    }

    public JCheckBox getCheckBoxDiscount() {
        return checkBoxDiscount;
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

    public JToolBar getToolBarMenu() {
        return toolBarMenu;
    }

    public JButton getButtonCardInput() {
        return buttonCardInput;
    }

    public JButton getButtonIO() {
        return buttonIO;
    }

    public JButton getButtonOptions() {
        return buttonOptions;
    }

    public JButton getBattonLog() {
        return battonLog;
    }
}
