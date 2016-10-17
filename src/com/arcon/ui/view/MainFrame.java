package com.arcon.ui.view;

import com.arcon.lib.Constants;

import javax.swing.*;

public class MainFrame extends JFrame implements Constants{

    private JPanel mainPanel;

    private JPanel panelLeft;
    private JPanel panelRight;
    private JPanel panelCenter;
    private JPanel panelTop;
    private JTextField textFieldCardId;
    private JToolBar toolMenuBar;
    private JButton buttonOk;

    //constructor
    public MainFrame() {
        setSize(1000, 500);
        setContentPane(this.mainPanel);
        setLocationRelativeTo(null);
        setTitle(PROGRAM_TITLE);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(true);
        setVisible(false);
        pack();
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JPanel getPanelLeft() {
        return panelLeft;
    }

    public JPanel getPanelRight() {
        return panelRight;
    }

    public JPanel getPanelCenter() {
        return panelCenter;
    }

    public JPanel getPanelTop() {
        return panelTop;
    }

    public JTextField getTextFieldCardId() {
        return textFieldCardId;
    }

    public JToolBar getToolMenuBar() {
        return toolMenuBar;
    }

    public JButton getButtonOk() {
        return buttonOk;
    }
}
