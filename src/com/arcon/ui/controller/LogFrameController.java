package com.arcon.ui.controller;

import com.arcon.db.DBConnect;
import com.arcon.ui.model.*;
import com.arcon.ui.view.LogFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogFrameController {

    private LogFrame logFrame;

    private TableCardInUseModel tableCardInUseModel;
    private TableTransactionsModel tableTransactionsModel;
    private TableCardsModel tableCardsModel;
    private TableDiscountsModel tableDiscountsModel;
    private TableUsersModel tableUsersModel;

    private JButton buttonStatistic;

    private JTable tableCardInUse;
    private JButton buttonCardInUseRefresh;

    private JTable tableTransactions;
    private JButton buttonTransactionsRefresh;

    private JTable tableCards;
    private JButton buttonCardsRefresh;

    private JTable tableDiscounts;
    private JButton buttonDiscountsRefresh;

    private JTable tableUsers;
    private JButton buttonUsersRefresh;

    public LogFrameController() {
        initComponents();
        initListeners();
    }

    private void initComponents() {
        logFrame = new LogFrame();

        tableCardInUse = logFrame.getTableCardInUse();
        buttonCardInUseRefresh = logFrame.getButtonCardInUseRefresh();

        tableTransactions = logFrame.getTableTransactions();
        buttonTransactionsRefresh = logFrame.getButtonTransactionsRefresh();

        tableCards = logFrame.getTableCards();
        buttonCardsRefresh = logFrame.getButtonCardsRefresh();

        tableDiscounts = logFrame.getTableDiscounts();
        buttonDiscountsRefresh = logFrame.getButtonDiscountsRefresh();

        tableUsers = logFrame.getTableUsers();
        buttonUsersRefresh = logFrame.getButtonUsersRefresh();
    }

    private void initListeners() {
        buttonCardInUseRefresh.addActionListener(new buttonRefreshActionListener());
        buttonTransactionsRefresh.addActionListener(new buttonTransactionsRefreshActionListener());
        buttonCardsRefresh.addActionListener(new buttonCardsRefreshActionListener());
        buttonDiscountsRefresh.addActionListener(new buttonDiscountsRefreshActionListener());
        buttonUsersRefresh.addActionListener(new buttonUsersRefreshActionListener());
    }

    public void showLogFrameWindow(){
        logFrame.setVisible(true);
    }

    private void setCardInUseModel() {

        DBConnect connect = DBConnect.getInstance();
        connect.openConnect();
        tableCardInUseModel = new TableCardInUseModel(connect.getCardInUseList());
        connect.closeConnect();

        tableCardInUse.setModel(tableCardInUseModel);
    }

    private void setTransactionsModel() {

        DBConnect connect = DBConnect.getInstance();
        connect.openConnect();
        tableTransactionsModel = new TableTransactionsModel(connect.getTransactionList());
        connect.closeConnect();

        tableTransactions.setModel(tableTransactionsModel);
    }

    private void setCardsModel(){
        DBConnect connect = DBConnect.getInstance();
        connect.openConnect();
        tableCardsModel = new TableCardsModel(connect.getCardsList());
        connect.closeConnect();

        tableCards.setModel(tableCardsModel);
    }

    private void setDiscountsModel() {
        DBConnect connect = DBConnect.getInstance();
        connect.openConnect();
        tableDiscountsModel = new TableDiscountsModel(connect.getDiscountList());
        connect.closeConnect();

        tableDiscounts.setModel(tableDiscountsModel);
    }

    private void setUsersModel() {
        DBConnect connect = DBConnect.getInstance();
        connect.openConnect();
        tableUsersModel = new TableUsersModel(connect.getUsersList());
        connect.closeConnect();

        tableUsers.setModel(tableUsersModel);
    }

    private class buttonRefreshActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            setCardInUseModel();
        }
    }

    private class buttonTransactionsRefreshActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            setTransactionsModel();
        }
    }

    private class buttonCardsRefreshActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            setCardsModel();
        }
    }

    private class buttonDiscountsRefreshActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            setDiscountsModel();
        }
    }

    private class buttonUsersRefreshActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            setUsersModel();
        }
    }
}
