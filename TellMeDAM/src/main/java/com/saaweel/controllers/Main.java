package com.saaweel.controllers;

import com.saaweel.UserSession;
import com.saaweel.App;

import java.io.IOException;

public class Main {
    public void initialize() {
        new Thread(() -> {
            synchronized (App.waitingForSceneLoad) {
                if (App.getScene() == null) {
                    try {
                        App.waitingForSceneLoad.wait(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            UserSession session = UserSession.getInstance();

            if (session == null) {
                try {
                    App.setRoot("login");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                loadMainInfo();
            }
        }).start();
    }

    private void loadMainInfo() {
        System.out.println("Cargar información");
    }
}