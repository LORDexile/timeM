package com.arcon.ui.controller;

import com.arcon.db.DBConnect;
import com.arcon.lib.Constants;
import com.arcon.ui.view.MainFrame;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class MainFrameController {

    private MainFrame mainFrame;
    private JTabbedPane tabbedPaneMain;
    private JComboBox comboBoxDiscount;

    public MainFrameController () {
        initComponents();
        initListeners();
    }

    private void initComponents() {
        mainFrame = new MainFrame();

        tabbedPaneMain = mainFrame.getTabbedPaneMain();
        comboBoxDiscount = mainFrame.getComboBoxDiscount();
    }

    private void initListeners() {

    }

    public void showMainFrameWindow() {
        mainFrame.setTitle(Constants.getProgramTitle());
        mainFrame.setVisible(true);

    }

    public void updateComponents() {
        Map<Double, String> map = new HashMap<>();

        DBConnect connect = new DBConnect();
        connect.openConnect();
        map = connect.getDiscountSet();
        connect.closeConnect();
        comboBoxDiscount.addItem(0.0);
        for (Map.Entry<Double, String> elem:
        map.entrySet()){
            comboBoxDiscount.addItem(elem.getKey());
        }
    }

}
