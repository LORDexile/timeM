package com.arcon.db;

import com.arcon.ui.model.Card;
import com.arcon.ui.model.User;
import com.arcon.lib.Constants;

import java.util.Date;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DBConnect{
    private static DBConnect instance = new DBConnect();
    private Connection connection = null;
    private Statement statmt = null;
    private ResultSet resSet;

    private String url;
    private String driver;

    public DBConnect() {
        url = Constants.DB_URL;
        driver = Constants.DB_DRIVER;
    }

    public void openConnect() {

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try{
            connection = DriverManager.getConnection(url);
            statmt = connection.createStatement();
            System.out.println("Соеденение установлено:");
        }catch (SQLException e){
            e.getStackTrace();
        }
    }

    public void closeConnect() {

        if (resSet != null) {
            try {
                resSet.close();
            }catch (SQLException e) {
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
            resSet = statmt.executeQuery("SELECT * FROM Users");
            while (resSet.next()) {
                if (userName.equals(resSet.getString("UserName"))){
                    if (password.equals(resSet.getString("Password"))) {
                        Constants.setUserName(resSet.getString("UserName"));
                        Constants.setUserType(resSet.getString("UserType"));
                        return 2;
                    }else {
                        return 1;
                    }

                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void createNewUser (User user){

        boolean NotExist = true;
        try {
            resSet = statmt.executeQuery("SELECT * FROM Users");
            while (resSet.next()) {
                if (user.getUserName().equals(resSet.getString("UserName"))) {
                    NotExist = false;
                    break;
                }
            }

            if (NotExist) {

                String sql = "INSERT INTO Users (UserName,UserType,Password,Comment) " +
                        "VALUES ('" + user.getUserName() +
                        "', '" + user.getUserType() +
                        "', '" + user.getPassword() +
                        "', '" + user.getComment() +
                        "');";
                System.out.println(sql);
                statmt.executeUpdate(sql);


            } else System.out.println("login exist");

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public boolean isCardInUse (String id) {

        try {
            resSet = statmt.executeQuery("SELECT * FROM CardInUse");

            while (resSet.next()) {
                if (id.equals(resSet.getString("id"))) {
                    return true;
                }
            }
            writeNewCardInUse(id);

        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    private void writeNewCardInUse(String id) {
        try {
            String sql = "INSERT INTO CardInUse (id,EnterTime) " +
                        "VALUES ('" + id +
                        "', '" + new Date().getTime() +
                        "');";
                System.out.println(sql);
                statmt.executeUpdate(sql);

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Card readCardInUSe(String id) {
        Date date = null;
        try{
        resSet = statmt.executeQuery("SELECT * FROM CardInUse");
            while (resSet.next()) {
                if (id.equals(resSet.getString("id"))) {
                    date = new Date(resSet.getLong("EnterTime"));
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return new Card(Long.parseLong(id), date);
    }

    public Map<Double, String> getDiscountSet() {
        Map<Double, String> map = new HashMap();

        try {
            resSet = statmt.executeQuery("SELECT * FROM Discount");
            while (resSet.next()) {
                if (resSet.getString("UserType").equals(Constants.getUserType())) {
                    map.put(resSet.getDouble("Discount"), resSet.getString("Comment"));
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return map;
    }

    public static DBConnect getInstance() {
        return instance;
    }
}
