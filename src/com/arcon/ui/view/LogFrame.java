package com.arcon.ui.view;

import javax.swing.*;

public class LogFrame extends JFrame{
    private JPanel panelLogMain;
    private JTabbedPane tabbedPaneTables;
    private JPanel panelLogMainTop;
    private JPanel panelTableCardInUse;
    private JPanel panelTableTransactions;
    private JPanel panelTableCards;
    private JPanel panelTableDiscount;

    private JButton button1;

    private JTable tableCardInUse;
    private JButton buttonCardInUseRefresh;

    private JTable tableTransactions;
    private JButton buttonTransactionsRefresh;

    private JTable tableCards;
    private JButton buttonCardsRefresh;

    private JTable tableDiscounts;
    private JButton buttonDiscountsRefresh;

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

    public JTable getTableCards() {
        return tableCards;
    }

    public JButton getButtonCardsRefresh() {
        return buttonCardsRefresh;
    }

    public JTable getTableDiscounts() {
        return tableDiscounts;
    }

    public JButton getButtonDiscountsRefresh() {
        return buttonDiscountsRefresh;
    }
}
