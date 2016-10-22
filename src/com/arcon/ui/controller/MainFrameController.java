package com.arcon.ui.controller;

import com.arcon.ui.view.MainFrame;

public class MainFrameController {

    private MainFrame mainFrame;



    public MainFrameController () {
        initComponents();
        initListeners();
    }


    private void initComponents() {
        mainFrame = new MainFrame();



    }

    private void initListeners() {

    }

    public void showMainFrameWindow() {
        mainFrame.setVisible(true);
    }
}
