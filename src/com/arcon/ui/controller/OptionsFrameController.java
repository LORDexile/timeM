package com.arcon.ui.controller;

import com.arcon.lib.Constants;
import com.arcon.ui.view.OptionsFrame;

import javax.swing.*;

public class OptionsFrameController {
    OptionsFrame optionsFrame;

    private JButton buttonMenuChangeUser;
    private JButton buttonMenuPrice;
    private JButton buttonMenuDiscounts;
    private JButton buttonMenuUsers;

    private JLabel labelUserName;
    private JLabel labelUserType;
    private JButton buttonChangeUser;
    private JButton buttonMenuProgram;

    public OptionsFrameController() {
        initComponents();
        initListeners();
    }

    private void initComponents() {
        optionsFrame = new OptionsFrame();

        buttonMenuChangeUser = optionsFrame.getButtonMenuChangeUser();
        labelUserName = optionsFrame.getLabelUserName();
        labelUserType = optionsFrame.getLabelUserType();
        buttonChangeUser = optionsFrame.getButtonChangeUser();
        buttonMenuProgram = optionsFrame.getButtonMenuProgram();
    }

    private void initListeners() {

    }

    public void showOptionsFrameWindow() {
        optionsFrame.setVisible(true);
        setCurrentUserInfo();
    }

    private void setCurrentUserInfo(){
        labelUserName.setText("<html><font color='red'>" + Constants.getUserName() + "</font></html>");
        labelUserType.setText("<html><font color='red'>" + Constants.getUserType() + "</font></html>");
    }
}
