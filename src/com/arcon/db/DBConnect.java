package com.arcon.db;

import com.arcon.lib.Constants;

public class DBConnect implements Constants{
    private String user;
    private String pass;
    private String url;
    private String driver;

    public DBConnect() {
        this.user = DB_USER;
        this.pass = DB_PASSWORD;
        this.url = DB_URL;
        this.driver = DB_DRIVER;
    }
}
