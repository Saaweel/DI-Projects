package com.saaweel.controllers;

import com.saaweel.App;
import org.example.api.APICallback;
import org.example.api.ChatAPIClient;
import org.example.api.model.Chat;
import org.example.api.model.Error;
import org.example.api.model.User;

import java.io.IOException;
import java.util.List;

public class Main {
    private ChatAPIClient chatApi;
    public void initialize() {
        chatApi = new ChatAPIClient();

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
                loadMainInfo(user);
            }
        }).start();


    }

    private void loadMainInfo(User user) {
        chatApi.getAllChatsFromUser(user.getId(), new APICallback() {
            @Override
            public void onSuccess(Object response) {
                if (response instanceof List) {
                    List<Chat> chats = (List<Chat>) response;

                    System.out.println(chats);
                }
            }

            @Override
            public void onError(Object response) {
                if (response instanceof Error) {
                    Error error = (Error) response;
                    App.showNotification("Error al cargar los chats", error.getError());
                }
            }
        });
    }
}