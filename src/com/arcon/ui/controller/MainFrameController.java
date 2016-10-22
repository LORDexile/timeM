package com.arcon.ui.controller;

import com.arcon.lib.Constants;
import com.arcon.ui.view.MainFrame;

import javax.swing.*;

public class MainFrameController {

    private MainFrame mainFrame;
    private JTabbedPane tabbedPaneMain;

    public MainFrameController () {
        initComponents();
        initListeners();
    }

    private void initComponents() {
        mainFrame = new MainFrame();

        tabbedPaneMain = mainFrame.getTabbedPaneMain();
        //tabbedPaneMain.
    }

    private void initListeners() {

    }

    public void showMainFrameWindow() {
        mainFrame.setTitle(Constants.getProgramTitle());
        mainFrame.setVisible(true);

    }
}
