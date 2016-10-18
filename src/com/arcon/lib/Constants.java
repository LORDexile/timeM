package com.arcon.lib;

public interface Constants {
    String adminLogin = "admin";
    String adminPassword = "root";
    String PRODUCT_VERSION = "0.0.5";
    String PROGRAM_TITLE = "TimeManager - ver:" + PRODUCT_VERSION;

    double PRICE = 2000.0;
    double PRICE_SEC = PRICE / 3600;

    //DB
    String DB_USER = "admin";
    String DB_PASSWORD = "root";
    String DB_URL= "jdbc:mysql://localhost:3306/test";
    String DB_DRIVER = "com.mysql.jdbc.Driver";

}
