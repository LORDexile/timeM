package com.arcon.ui.model;

public class Discount {
    private double discount;
    private String comment;
    private String userType;
    private int isActive = 1;

    public Discount(double discount, String comment) {
        this.discount = discount;
        this.comment = comment;
    }

    public Discount(double discount, String comment, String userName, int isActive) {
        this.discount = discount;
        this.comment = comment;
        this.userType = userName;
        this.isActive = isActive;
    }

    public double getDiscount() {
        return discount;
    }

    public String getComment() {
        return comment;
    }

    public String getUserType() {
        return userType;
    }

    public boolean isActive() {
        if(isActive == 1) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String msg = "% - ";

        //if comment is empty shows only "discount + %"
        //if not empty shows "discount + "% - " + first word of comment
        if (comment.equals("")){
            msg = "%" + comment;
        }else {
            String[] word = comment.split("\\s");
            msg += word[0];
        }
        return discount + msg;
    }
}
