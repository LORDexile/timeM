package com.arcon;

public class User {

    private String userName;
    private String password;
    private UserType userType;
    private String comment;

    public User(String userName, UserType userType, String password, String comment) {
        this.userName = userName;
        this.userType = userType;
        this.password = password;
        this.comment = comment;

    }

    public String getUserName() {
        return userName;
    }

    public UserType getUserType() {
        return userType;
    }

    public String getPassword() {
        return password;
    }

    public String getComment() {
        return comment;
    }
}
