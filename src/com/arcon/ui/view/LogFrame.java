package com.arcon.ui.view;

import javax.swing.*;

public class LogFrame extends JFrame{
    private JTabbedPane tabbedPaneTables;
    private JPanel panelLogMain;
    private JPanel panelLogMainTop;
    private JPanel panelTable;
    private JButton button1;
    private JTable tableCardInUse;
    private JButton buttonRefresh;

    public LogFrame() {
        setSize(600,400);
        setContentPane(this.panelLogMain);
        setLocationRelativeTo(null);
        setTitle("log:");
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setResizable(true);
        pack();
        setSize(600,400);
    }

    public JButton getButtonRefresh() {
        return buttonRefresh;
    }

    public JTable getTableCardInUse() {
        return tableCardInUse;
    }

    public JButton getButton1() {
        return button1;
    }
}
