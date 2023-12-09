package com.saaweel.controllers;

import com.saaweel.App;
import org.example.api.model.User;

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

            User user = App.getMyUser();

            if (user == null) {
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