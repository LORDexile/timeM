package com.arcon.db;

import com.arcon.lib.Constants;

import java.io.IOException;
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

    /**
     *
     * @param userName
     * @param password
     * @return
     * 2 - verification passed;
     * <p>
     * 1 - password is`t correct;
     * </p>
     * 0 - User does`t exist.
     *
     */
    public int verifyUser (String userName, String password){
        try {
            statmt = connection.createStatement();
            resSet = statmt.executeQuery("SELECT * FROM Users");
            while (resSet.next()) {
                if (userName.equals(resSet.getString("UserName"))){
                    if (password.equals(resSet.getString("Password"))) {
                        return 2;
                    }else {
                        return 1;
                    }

                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (resSet != null) {
                try {
                    resSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statmt != null) {
                try {
                    statmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }

    public static DBConnect getInstance() {
        return instance;
    }
}
