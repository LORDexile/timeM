package com.arcon.ui.view;

import com.arcon.lib.Constants;

import javax.swing.*;

public class OptionsFrame extends JFrame{
    private JPanel panelMain;
    private JPanel panelMenu;
    private JPanel panelContextMain;
    private JPanel panelContextChangeUser;

    private JButton buttonMenuChangeUser;
    private JButton buttonMenuPrice;
    private JButton buttonMenuDiscounts;
    private JButton buttonMenuUsers;
    private JButton buttonMenuProgram;

    private JLabel labelUserName;
    private JLabel labelUserType;
    private JButton buttonChangeUser;

    public OptionsFrame() {

        setSize(800, 500);
        setContentPane(this.panelMain);
        setLocationRelativeTo(null);
        setTitle(Constants.PROGRAM_TITLE);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setResizable(true);
        setVisible(false);
        pack();
        setSize(800, 500);

    }

    public JPanel getPanelContextChangeUser() {
        return panelContextChangeUser;
    }

    public JButton getButtonMenuChangeUser() {
        return buttonMenuChangeUser;
    }

    public JButton getButtonMenuPrice() {
        return buttonMenuPrice;
    }

    public JButton getButtonMenuDiscounts() {
        return buttonMenuDiscounts;
    }

    public JButton getButtonMenuUsers() {
        return buttonMenuUsers;
    }

    public JButton getButtonMenuProgram() {
        return buttonMenuProgram;
    }

    public JLabel getLabelUserName() {
        return labelUserName;
    }

    public JLabel getLabelUserType() {
        return labelUserType;
    }

    public JButton getButtonChangeUser() {
        return buttonChangeUser;
    }

}
