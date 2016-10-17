package com.arcon.ui.controller;

import com.arcon.Main;
import com.arcon.ui.view.MainFrame;

import javax.swing.*;

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
