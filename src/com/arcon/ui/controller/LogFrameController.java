package com.arcon.ui.controller;

import com.arcon.db.DBConnect;
import com.arcon.ui.model.Card;
import com.arcon.ui.model.TableCardInUseModel;
import com.arcon.ui.view.LogFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LogFrameController {

    private LogFrame logFrame;
    private TableCardInUseModel tableCardInUseModel;

    private JButton button1;
    private JTable tableCardInUse;
    private JButton buttonCardInUseRefresh;
    private JButton buttonTransactionsRefresh;
    private JTable tableTransactions;

    public LogFrameController() {
        initComponents();
        initListeners();
    }

    public void showLogFrameWindow(){
        logFrame.setVisible(true);
    }

    private void initComponents() {
        logFrame = new LogFrame();

        tableCardInUse = logFrame.getTableCardInUse();
        buttonCardInUseRefresh = logFrame.getButtonCardInUseRefresh();

        tableTransactions = logFrame.getTableTransactions();
        buttonTransactionsRefresh = logFrame.getButtonTransactionsRefresh();
    }

    private void initListeners() {
        buttonCardInUseRefresh.addActionListener(new buttonRefreshActionListener());
    }
    private void setCardInUseModel() {
        List<Card> list;

        DBConnect connect = DBConnect.getInstance();
        connect.openConnect();
        list = connect.getCardInUseList();
        connect.closeConnect();

        tableCardInUseModel = new TableCardInUseModel(list);
        tableCardInUse.setModel(tableCardInUseModel);
    }

    private class buttonRefreshActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            setCardInUseModel();
        }
    }
}
