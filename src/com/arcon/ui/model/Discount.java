package com.arcon.ui.model;

public class Discount {
    private double discount;
    private String comment;
    private String userType;

    public Discount(double discount, String comment) {
        this.discount = discount;
        this.comment = comment;
    }

    public Discount(double discount, String comment, String userName) {
        this.discount = discount;
        this.comment = comment;
        this.userType = userName;
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
