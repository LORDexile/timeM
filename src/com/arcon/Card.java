package com.arcon;

import com.arcon.lib.Constants;

import java.util.Date;

public class Card implements Constants{
    private long id;
    private Date enterTime;
    private Date exitTime;
    private long time;
    private int price;
    private double discount;
    private int discountPrice;

    public Card(long id) {
        this.id = id;
        enterTime = new Date();
    }

    public void setExitTime() {
        exitTime = new Date();
        //calculate price
        time = (exitTime.getTime() - enterTime.getTime())/1000;
        price = (int)(time * PRICE_SEC);

    }

    public void setDiscount(float discount) {
        if (discount > 0 && discount <= 100) {
            this.discount = discount;
        } else {
            this.discount = 0;
        }
        discountPrice = (int) (price * (this.discount/100));
        
    }

    public long getTime() {
        return time;
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

    public double getDiscount() {
        return discount;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }
}
