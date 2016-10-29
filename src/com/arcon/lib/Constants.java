package com.arcon.lib;

public final class Constants {
    private static String USER_NAME;
    private static String USER_TYPE;
    public static final String PRODUCT_VERSION = "0.3.0";
    public static final String PROGRAM_TITLE = " TimeManager - ver: " + PRODUCT_VERSION;

    public static final double PRICE = 2000.0;
    public static final double PRICE_SEC = PRICE / 3600;

    //local DB
    public static final String DB_URL= "jdbc:sqlite:src/com/arcon/db/localDB.db";
    public static final String DB_DRIVER = "org.sqlite.JDBC";

    //remote DB
    public static final String REMOTE_DB_USER = "admin";
    public static final String REMOTE_DB_PASSWORD = "root";
    public static final String REMOTE_DB_URL= "jdbc:mysql://localhost:3306/test";
    public static final String REMOTE_DB_DRIVER = "com.mysql.jdbc.Driver";

    public static void setUserName(String userName) {
        USER_NAME = userName;
    }

    public static void setUserType(String userType) {
        USER_TYPE = userType;
    }

    public static String getUserName() {
        return USER_NAME;
    }

    public static String getUserType() {
        return USER_TYPE;
    }

    public static String getProgramTitle() {
        return "[" + USER_NAME + "]" + PROGRAM_TITLE;
    }
}
