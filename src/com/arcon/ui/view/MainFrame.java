package com.arcon.ui.view;


import com.arcon.lib.Constants;

import javax.swing.*;

public class MainFrame extends JFrame{

    private JPanel mainPanel;
    private JTabbedPane tabbedPane1;
    private JPanel tabPanelMain;
    private JPanel tabPanelIO;
    private JPanel tabPanelOptions;
    private JPanel tabPanelLog;
    private JTextField textFieldCard;
    private JButton buttonPerformCard;
    private JRadioButton radioButtonInput;
    private JRadioButton radioButtonOutput;
    private JTextField textFieldCash;
    private JButton buttonIOAction;
    private JTextArea textAreaComment;
    private JPasswordField passwordFieldPassword;
    private JButton buttonCangeUser;
    private JButton changePriceButton;


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


}
