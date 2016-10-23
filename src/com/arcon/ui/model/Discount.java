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
        return comment;
    }
}
