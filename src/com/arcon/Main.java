package com.arcon;

import com.arcon.ui.controller.LoginFrameController;
import com.arcon.ui.view.LoginFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        LoginFrameController loginFrameController = new LoginFrameController();
        loginFrameController.showLoginFrameWindow();
    }
}
