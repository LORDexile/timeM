package com.arcon;

import com.arcon.ui.controller.IOFrameController;
import com.arcon.ui.controller.LogFrameController;
import com.arcon.ui.controller.LoginFrameController;
import com.arcon.ui.controller.MainFrameController;
import com.arcon.ui.view.IOFrame;
import com.arcon.ui.view.LogFrame;

public class Main {

    public static MainFrameController mainFrameController;
    public static LoginFrameController loginFrameController;
    public static IOFrameController ioFrameController;
    public static LogFrameController logFrameController;

    public static void main(String[] args) {

        loginFrameController = new LoginFrameController();
        loginFrameController.showLoginFrameWindow();

        mainFrameController = new MainFrameController();

        ioFrameController = new IOFrameController();

        logFrameController = new LogFrameController();

    }
}