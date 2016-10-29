package com.arcon.ui.model;

import com.arcon.lib.Constants;
import java.util.Date;

public class Card{
    private int operationId;
    private long id;
    private String userName;
    private int price = 0;
    private double discount = 0;
    private Date enterTime;
    private Date exitTime;
    private String enterTimeString;
    private String exitTimeString;
    private long totalTime;

    public Card(int operationId, long id, String userName, int price, double discount, String enterTime, String exitTime) {
        this.operationId = operationId;
        this.id = id;
        this.userName = userName;
        this.price = price;
        this.discount = discount;
        enterTimeString = enterTime;
        exitTimeString = exitTime;
    }

    public Card(long id, Date date) {
        this.id = id;
        enterTime = date;
    }

    public void setExitTime(){
        exitTime = new Date();
        //set time in seconds
        totalTime = (exitTime.getTime() - enterTime.getTime()) / 1000;
    }

    public void setDiscount(double discount) {
        if (discount >= 0.0 && discount <= 100.0) {
            this.discount = discount;
        }
    }

    public int getOperationId() {
        return operationId;
    }

    public long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public int getPrice() {
        if(price == 0) {
            if (discount != 0.0) {
                price = (int) (totalTime * Constants.PRICE_SEC - (totalTime * Constants.PRICE_SEC * this.discount / 100));
                return price;
            }
            price = (int) (totalTime * Constants.PRICE_SEC);
        }
        return  price;
    }

    public double getDiscount() {
        return discount;
    }

    public Date getEnterTime() {
        return enterTime;
    }

    public Date getExitTime() {
        return exitTime;
    }

    public String getEnterTimeString() {
        return enterTimeString;
    }

    public String getExitTimeString() {
        return exitTimeString;
    }

    public String getTotalTime() {
        long sek = totalTime;
        int hour = 0;
        int min = 0;
        System.out.println("Время в сек: " + sek);
        while (sek >= 60) {
            sek -= 60;
            min++;
        }
        while (min >= 60){
            min -= 60;
            hour++;
        }

        return hour + "h " + min + "m " + sek + "s";
    }


}