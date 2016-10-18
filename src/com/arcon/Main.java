package com.arcon;

import com.arcon.ui.controller.LoginFrameController;
import com.arcon.ui.controller.MainFrameController;

public class Main {

    public static MainFrameController mainFrameController;
    public static LoginFrameController loginFrameController;

    public static void main(String[] args) {

        loginFrameController = new LoginFrameController();
        loginFrameController.showLoginFrameWindow();

        mainFrameController = new MainFrameController();

    }
}
