package com.arcon.ui.model;

public class Discount {
    private double discount;
    private String comment;

    public Discount(double discount, String comment) {
        this.discount = discount;
        this.comment = comment;
    }

    public double getDiscount() {
        return discount;
    }

    public String getComment() {
        return comment;
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
