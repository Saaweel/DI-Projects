package com.saaweel;

import org.example.api.model.User;

public final class UserSession {
    private static UserSession instance;

    private String user;

    private UserSession(String user) {
        this.user = user;
    }

    public static UserSession getInstance() {
        return instance;
    }

    public static UserSession setInstance(String user) {
        if (instance != null) {
            return instance;
        }

        instance = new UserSession(user);

        return instance;
    }

    public String getUserName() {
        return user;
    }

    public void cleanUserSession() {
        user = null;
    }
}