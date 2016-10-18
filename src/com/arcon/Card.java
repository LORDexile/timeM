package com.arcon;

import java.util.Date;

public class Card {
    private long id;
    private Date enterTime;
    private Date exitTime;
    private int price;
    private float discount;
    private int discountPrice;

    public Card(long id) {
        this.id = id;
        enterTime = new Date();
    }

    public void setExitTime() {
        exitTime = new Date();
        //TO DO code to calculate price
    }

    public void setDiscount(float discount) {
        this.discount = discount;
        //TO DO code to calculate discount
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
        return price;
    }

    public float getDiscount() {
        return discount;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }
}
