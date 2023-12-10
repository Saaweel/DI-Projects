package com.saaweel.controllers;

import com.saaweel.App;
import com.saaweel.ChatListCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.example.api.APICallback;
import org.example.api.ChatAPIClient;
import org.example.api.model.Chat;
import org.example.api.model.Error;
import org.example.api.model.User;

import java.io.IOException;
import java.util.List;

public class Main {
    public ListView<Chat> chatListView;
    public TextField messageTextField;
    private ObservableList<Chat> chatList;
    private ChatAPIClient chatApi;
    public void initialize() {
        chatList = FXCollections.observableArrayList();

        chatListView.setItems(chatList);

        chatListView.setCellFactory(param -> new ChatListCell());

        chatListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                System.out.println("Abrir chat: " + newValue.getId());
            }
        });

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
            @SuppressWarnings("unchecked cast")
            public void onSuccess(Object response) {
                if (response instanceof List) {
                    List<Chat> chats = (List<Chat>) response;

                    chatList.addAll(chats);
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

    public void newChat() {
        System.out.println("Nuevo chat");
    }

    public void handleKeyPress(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            System.out.println("Mensaje");
        }
    }
}