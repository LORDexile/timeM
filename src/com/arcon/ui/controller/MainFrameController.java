package com.arcon.ui.controller;

import com.arcon.Main;
import com.arcon.ui.view.MainFrame;

import javax.swing.*;

public class MainFrameController {

    private MainFrame mainFrame;


    private JTextField textFieldCardId;
    private JToolBar toolMenuBar;
    private JButton buttonOk;

    public MainFrameController () {
        initComponents();
        initListeners();
    }


    private void initComponents() {
        mainFrame = new MainFrame();

        textFieldCardId = mainFrame.getTextFieldCardId();
        toolMenuBar = mainFrame.getToolMenuBar();
        buttonOk = mainFrame.getButtonOk();

    }

    private void initListeners() {

    }

    public void showMainFrameWindow() {
        mainFrame.setVisible(true);
    }
}
