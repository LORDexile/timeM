package com.arcon;

import com.arcon.ui.controller.*;
import com.arcon.ui.view.IOFrame;
import com.arcon.ui.view.LogFrame;
import com.arcon.ui.view.OptionsFrame;

public class Main {

    public static MainFrameController mainFrameController;
    public static LoginFrameController loginFrameController;
    public static IOFrameController ioFrameController;
    public static OptionsFrameController optionsFrameController;
    public static LogFrameController logFrameController;

    public static void main(String[] args) {

        loginFrameController = new LoginFrameController();
        loginFrameController.showLoginFrameWindow();

        mainFrameController = new MainFrameController();

        ioFrameController = new IOFrameController();

        logFrameController = new LogFrameController();

        optionsFrameController = new OptionsFrameController();
    }
}