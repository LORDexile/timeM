package com.arcon.db;

import com.arcon.lib.Constants;

public class DBConnect{
    private String user;
    private String pass;
    private String url;
    private String driver;

    public DBConnect() {
        this.user = Constants.DB_USER;
        this.pass = Constants.DB_PASSWORD;
        this.url = Constants.DB_URL;
        this.driver = Constants.DB_DRIVER;
    }
}
