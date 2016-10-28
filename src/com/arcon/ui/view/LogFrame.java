package com.arcon.ui.view;

import javax.swing.*;

public class LogFrame extends JFrame{
    private JPanel panelLogMain;
    private JTabbedPane tabbedPaneTables;
    private JPanel panelLogMainTop;
    private JPanel panelTableCardInUse;
    private JPanel panelTableTransactions;

    private JButton button1;

    private JTable tableCardInUse;
    private JButton buttonCardInUseRefresh;

    private JTable tableTransactions;
    private JButton buttonTransactionsRefresh;

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

    public JButton getButtonCardInUseRefresh() {
        return buttonCardInUseRefresh;
    }

    public JTable getTableCardInUse() {
        return tableCardInUse;
    }

    public JButton getButton1() {
        return button1;
    }

    public JTable getTableTransactions() {
        return tableTransactions;
    }

    public JButton getButtonTransactionsRefresh() {
        return buttonTransactionsRefresh;
    }
}
