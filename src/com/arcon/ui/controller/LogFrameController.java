package com.arcon.ui.controller;

import com.arcon.ui.view.LogFrame;

import javax.swing.*;

public class LogFrameController {

    private LogFrame logFrame;
    private JButton button1;
    private JTable tableCardInUse;
    private JButton buttonRefresh;

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

        buttonRefresh = logFrame.getButtonRefresh();
    }

    private void initListeners() {

    }

}
