package com.arcon.ui.model;

import com.arcon.lib.Constants;

import java.util.Date;

public class Card{
    private long id;
    private Date enterTime;
    private Date exitTime;
    private long totalTime;
    private double discount = 0;

    public Card(long id, Date date) {
        this.id = id;
        enterTime = date;
    }

    public void setExitTime() {
        exitTime = new Date();
        //set time in seconds
        totalTime = (exitTime.getTime() - enterTime.getTime()) / 1000;
    }

    public void setDiscount(double discount) {
        if (discount >= 0.0 && discount <= 100.0) {
            this.discount = discount;
        }
    }

    public long getTotalTime() {
        return totalTime;
    }

    public long getId() {
        return id;
    }

    public Date getEnterTime() {
        return enterTime;
    }

    public Date getExitTime() {
        return exitTime;
    }

    public int getPrice() {
        if (discount != 0.0) {
            return (int) (totalTime * Constants.PRICE_SEC * (this.discount / 100));
        }
        return  (int) (totalTime * Constants.PRICE_SEC);
    }

    public double getDiscount() {
        return discount;
    }


}
