package com.example.unorpproject.model;

public class Variables {
    private static User user;

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        Variables.user = user;
    }
}
