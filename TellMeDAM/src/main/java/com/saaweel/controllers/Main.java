package com.saaweel.controllers;

import com.saaweel.App;
import com.saaweel.ChatListCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.example.api.APICallback;
import org.example.api.ChatAPIClient;
import org.example.api.MessageAPIClient;
import org.example.api.model.Chat;
import org.example.api.model.Error;
import org.example.api.model.Message;
import org.example.api.model.User;

import javax.swing.border.Border;
import java.io.IOException;
import java.util.List;

public class Main {
    public ListView<Chat> chatListView;
    public TextField messageTextField;
    public Label chatName;
    public BorderPane chatPane;
    public VBox chatMessages;
    private ObservableList<Chat> chatList;
    private ChatAPIClient chatApi;
    private MessageAPIClient messageApi;
    public void initialize() {
        chatList = FXCollections.observableArrayList();

        chatListView.setItems(chatList);

        chatListView.setCellFactory(param -> new ChatListCell());

        chatListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                chatPane.setVisible(true);

                chatName.setText(newValue.getUser1_username());

                chatMessages.getChildren().clear();

                try {
                    messageApi.getMessagesFromChat(newValue.getId(), new APICallback() {
                        @Override
                        @SuppressWarnings("unchecked cast")
                        public void onSuccess(Object response) {
                            if (response instanceof List) {
                                List<Message> messages = (List<Message>) response;

                                for (Message message: messages) {
                                    System.out.println(message.getContent());

                                    // TODO
                                }
                            }
                        }

                        @Override
                        public void onError(Object response) {
                            if (response instanceof Error) {
                                Error error = (Error) response;
                                App.showNotification("Error al cargar los mensajes", error.getError());
                            }
                        }
                    });
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        chatApi = new ChatAPIClient();

        messageApi = new MessageAPIClient();

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
        // TODO
    }

    public void handleKeyPress(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER && !messageTextField.getText().isEmpty()) {
            System.out.println(messageTextField.getText());

            // TODO
        }
    }

    private void addMessage(String username, String content) {
        VBox messageVBox = new VBox();
        messageVBox.getStyleClass().add("message");

        Label userLabel = new Label(username);
        userLabel.getStyleClass().add("message-user");

        Label contentLabel = new Label(content);
        contentLabel.getStyleClass().add("message-content");

        messageVBox.getChildren().addAll(userLabel, contentLabel);

        chatMessages.getChildren().add(messageVBox);
    }
}