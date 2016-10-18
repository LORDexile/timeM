package com.arcon;

public class User {

    private String userName;
    private String password;
    private UserType accessType;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        accessType = UserType.USER;
    }

    public boolean isPasswordCorrect (String password) {
        if (this.password.equals(password)) {
            return true;
        }
        return false;
    }

    public String getUserName() {
        return userName;
    }

    public UserType getAccessType() {
        return accessType;
    }

}
