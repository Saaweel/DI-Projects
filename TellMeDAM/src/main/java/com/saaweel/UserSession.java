package com.saaweel;

import org.example.api.model.User;

public final class UserSession {
    private static UserSession instance;

    private User user;

    private UserSession(User user) {
        this.user = user;
    }

    public static UserSession getInstance() {
        return instance;
    }

    public static UserSession setInstance(User user) {
        if (instance != null) {
            return instance;
        }

        instance = new UserSession(user);

        return instance;
    }

    public User getUser() {
        return user;
    }

    public void clearUserSession() {
        user = null;
    }
}