package com.arcon.db;

import com.arcon.lib.Constants;

import java.sql.*;

public class DBConnect{
    private static DBConnect instance = new DBConnect();
    private Connection connection = null;
    private Statement statmt = null;
    private ResultSet resSet;

    private String user;
    private String pass;
    private String url;
    private String driver;
    private String remoteURL;
    private String remoteDriver;




    public DBConnect() {
        user = Constants.REMOTE_DB_USER;
        pass = Constants.REMOTE_DB_PASSWORD;
        url = Constants.DB_URL;
        driver = Constants.DB_DRIVER;
        remoteURL = Constants.REMOTE_DB_URL;
        remoteDriver = Constants.REMOTE_DB_DRIVER;
    }

    public void openConnect() {

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try{
            connection = DriverManager.getConnection(url);
            System.out.println("Соеденение установлено:");
        }catch (SQLException e){
            e.getStackTrace();
        }
    }

    public void closeConnect() {

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Соединение завершено");
    }

    public static DBConnect getInstance() {
        return instance;
    }
}
