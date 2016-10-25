package com.arcon.db;

import com.arcon.ui.model.ActionType;
import com.arcon.ui.model.Card;
import com.arcon.ui.model.Discount;
import com.arcon.ui.model.User;
import com.arcon.lib.Constants;

import java.util.*;
import java.sql.*;
import java.util.Date;

public class DBConnect{
    private static DBConnect instance = new DBConnect();
    private Connection connection = null;
    private Statement statement = null;
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
            statement = connection.createStatement();
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

        if (statement != null) {
            try {
                statement.close();
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
            resSet = statement.executeQuery("SELECT * FROM Users");
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
            resSet = statement.executeQuery("SELECT * FROM Users");
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
                statement.executeUpdate(sql);


            } else System.out.println("login exist");

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public boolean isCardInUse (String id) {

        try {
            resSet = statement.executeQuery("SELECT * FROM CardInUse");

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
            statement.executeUpdate(sql);

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Card readCardInUse(String id) {
        Date date = null;
        try{
        resSet = statement.executeQuery("SELECT * FROM CardInUse");
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

    public void deleteCardInUse(String id) {
        try {
            String sql = "DELETE FROM CardInUse WHERE id=" + id + ";";
            statement.executeUpdate(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void writeCard(Card card) {
        try{
            String sql = "INSERT INTO Cards (Card_id, User, Price, Discount, DATA_in, DATA_out) " +
                    "VALUES ('" + card.getId() +
                    "', '" + Constants.getUserName() +
                    "', '" + card.getPrice() +
                    "', '" + card.getDiscount() +
                    "', '" + card.getEnterTime() +
                    "', '" + card.getExitTime() +
                    "');";
            System.out.println(sql);
            statement.executeUpdate(sql);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setTransaction(int money, ActionType actionType) {

        if (actionType.equals(ActionType.CARD_OUTPUT)) {
            setCardCount(false);
        }

        setMoneyCount(money);

        try{
            String sql = "INSERT INTO money_transactions (number_of_cash, action, user_name, date) " +
                    "VALUES ('" + money +
                    "', '" + actionType.toString() +
                    "', '" + Constants.getUserName() +
                    "', '" + new Date() +
                    "');";

            System.out.println(sql);
            statement.executeUpdate(sql);

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setCardCount(boolean cardAction) {
        int cardCount = getCardCount();

        if (cardAction){
            cardCount += 1;
        }else {
            cardCount -= 1;
        }
        String sql = "UPDATE items set count = " + cardCount + " where id = 'card';";
        try {
            statement.executeUpdate(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void setMoneyCount(int money) {
        money += getMoneyCount();

        String sql = "UPDATE items set count = " + money + " WHERE id = 'money';";
        try {
            statement.executeUpdate(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public int getMoneyCount() {
        try {
            resSet = statement.executeQuery("SELECT * FROM items WHERE id = 'money';");
            return resSet.getInt("count");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return -999999;
    }

    public int getCardCount() {
        try {
            resSet = statement.executeQuery("SELECT * FROM items WHERE id = 'card';");
            return resSet.getInt("count");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return -999999;
    }


    public ArrayList<Discount> getDiscountSet() {
        ArrayList<Discount> list = new ArrayList();

        try {
            resSet = statement.executeQuery("SELECT * FROM Discount");
            while (resSet.next()) {
                if (resSet.getString("UserType").equals(Constants.getUserType())) {
                    list.add(new Discount(resSet.getDouble("Discount"), resSet.getString("Comment")));
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static DBConnect getInstance() {
        return instance;
    }
}
