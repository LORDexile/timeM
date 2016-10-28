package com.arcon.ui.model;

public class Transaction {

    int cashNumber;
    String actionType;
    String userName;
    String date;
    String comment;

    public Transaction(int cashNumber, String actionType, String userName, String date, String comment) {
        this.cashNumber = cashNumber;
        this.actionType = actionType;
        this.userName = userName;
        this.date = date;
        this.comment = comment;
    }

    public int getCashNumber() {
        return cashNumber;
    }

    public String getActionType() {
        return actionType;
    }

    public String getUserName() {
        return userName;
    }

    public String getDate() {
        return date;
    }

    public String getComment() {
        return comment;
    }
}
