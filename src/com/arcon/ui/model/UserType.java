package com.arcon.ui.model;

public enum  UserType {
    ADMIN,
    MODERATOR,
    USER,
    INDEFINED;

    public static UserType toUserType(String userType){
        if (userType.equals("ADMIN")){
            return ADMIN;
        } else if (userType.equals("MODERATOR")){
            return MODERATOR;
        } else if (userType.equals("USER")){
            return USER;
        }
        return INDEFINED;
    }
}