package com.arcon.ui.controller;

import com.arcon.Main;
import com.arcon.lib.Constants;
import com.arcon.ui.view.OptionsFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionsFrameController {
    OptionsFrame optionsFrame;

    private JButton buttonMenuChangeUser;
    private JButton buttonMenuPrice;
    private JButton buttonMenuDiscounts;
    private JButton buttonMenuUsers;
    private JButton buttonMenuProgram;

    private JPanel panelContextMain;

    private JLabel labelUserName;
    private JLabel labelUserType;
    private JButton buttonChangeUser;


    public OptionsFrameController() {
        initComponents();
        initListeners();
    }

    private void initComponents() {
        optionsFrame = new OptionsFrame();

        panelContextMain = optionsFrame.getPanelContextMain();

        buttonMenuChangeUser = optionsFrame.getButtonMenuChangeUser();
        labelUserName = optionsFrame.getLabelUserName();
        labelUserType = optionsFrame.getLabelUserType();
        buttonChangeUser = optionsFrame.getButtonChangeUser();

        buttonMenuPrice = optionsFrame.getButtonMenuPrice();
    }

    private void initListeners() {

        buttonMenuChangeUser.addActionListener(new buttonMenuChangeUserActionListener());
        buttonMenuPrice.addActionListener(new buttonMenuPriceActionListener());

        buttonChangeUser.addActionListener(new buttonChangeUserActionListener());
    }

    public void showOptionsFrameWindow() {
        optionsFrame.setVisible(true);
        setCurrentUserInfo();
    }

    private void setCurrentUserInfo(){
        labelUserName.setText("<html><font color='red'>" + Constants.getUserName() + "</font></html>");
        labelUserType.setText("<html><font color='red'>" + Constants.getUserType() + "</font></html>");
    }

    private void setContextPanel(String panel){
        CardLayout cl = (CardLayout)(panelContextMain.getLayout());
        cl.show(panelContextMain, panel);
    }

    private class buttonChangeUserActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Main.loginFrameController.showLoginFrameWindow();
            optionsFrame.setVisible(false);
        }
    }

    private class buttonMenuChangeUserActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            setContextPanel("Card1");
        }
    }

    private class buttonMenuPriceActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            setContextPanel("Card2");
        }
    }
}
