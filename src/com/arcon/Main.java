package com.arcon;

import com.arcon.lib.Constants;
import com.arcon.ui.controller.*;

public class Main {

    public static MainFrameController mainFrameController;
    public static LoginFrameController loginFrameController;
    public static IOFrameController ioFrameController;
    public static OptionsFrameController optionsFrameController;
    public static LogFrameController logFrameController;

    public static void main(String[] args) {
        Constants.getUserType();//preloading Constant Class

        loginFrameController = new LoginFrameController();
        loginFrameController.showLoginFrameWindow();

        mainFrameController = new MainFrameController();

        ioFrameController = new IOFrameController();

        logFrameController = new LogFrameController();

        optionsFrameController = new OptionsFrameController();
    }
}