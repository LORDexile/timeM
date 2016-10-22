package com.arcon.ui.controller;

import com.arcon.ui.view.MainFrame;

import javax.swing.*;

public class MainFrameControllerMain {

    private MainFrame mainFrame;

    private JTextField textFieldCard;
    private JTextField textFieldCash;
    private JButton buttonPerformCard;
    private JLabel jLabelTimeIn;
    private JLabel jLabelTimeOut;
    private JLabel jLabelTimeTotal;
    private JLabel jLabelPrice;
    private JComboBox comboBoxDiscount;

    public MainFrameControllerMain(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        initComponents();
        initListeners();
    }

    private void initComponents() {
        textFieldCard = mainFrame.getTextFieldCard();
        textFieldCash = mainFrame.getTextFieldCash();
        buttonPerformCard = mainFrame.getButtonPerformCard();
        jLabelTimeIn = mainFrame.getjLabelTimeIn();
        jLabelTimeOut = mainFrame.getjLabelTimeOut();
        jLabelTimeTotal = mainFrame.getjLabelTimeTotal();
        jLabelPrice = mainFrame.getjLabelPrice();
        comboBoxDiscount = mainFrame.getComboBoxDiscount();
    }

    private void initListeners() {

    }




}
