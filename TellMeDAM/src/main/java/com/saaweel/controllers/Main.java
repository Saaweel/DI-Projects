package com.saaweel.controllers;

import com.saaweel.App;
import com.saaweel.ChatListCell;
import com.saaweel.UserListCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import org.example.api.*;
import org.example.api.model.Chat;
import org.example.api.model.Error;
import org.example.api.model.Message;
import org.example.api.model.User;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Main {
    public ListView<Chat> chatListView;
    public TextField messageTextField;
    public Label chatName;
    public BorderPane chatPane;
    public VBox chatMessages;
    public VBox mainFrame;
    public ImageView myPhoto;
    private ObservableList<Chat> chatList;
    private UserAPIClient userApi;
    private ChatAPIClient chatApi;
    private MessageAPIClient messageApi;
    private NotificationAPIClient notyApi;
    private Chat chatOpened;
    public void initialize() {
        myPhoto.setClip(new Circle(17.5, 17.5, 17.5));
        myPhoto.setImage(new Image(App.getMyUser().getPhotourl()));

        chatList = FXCollections.observableArrayList();

        chatListView.setItems(chatList);

        chatListView.setCellFactory(param -> new ChatListCell());

        chatListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                openChatContent(newValue, false);
            }
        });

        userApi = new UserAPIClient();

        chatApi = new ChatAPIClient();

        messageApi = new MessageAPIClient();

        notyApi= new NotificationAPIClient();

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

        notyApi.observeNewMessages(App.getMyUser().getId(), new APICallback() {
            @Override
            @SuppressWarnings("unchecked cast")
            public void onSuccess(Object response) {
                if (response instanceof List) {
                    List<Chat> chats = (List<Chat>) response;

                    if (!chats.isEmpty()) {
                        boolean myChatUpdated = false;

                        StringBuilder stringList = new StringBuilder();

                        for (Chat chat : chats) {
                            boolean founded = false;

                            for (Chat chatFromList : chatList) {
                                if (chatFromList.getId() == chat.getId()) {
                                    founded = true;

                                    if (chatOpened != null && chatOpened.getId() == chat.getId()) {
                                        myChatUpdated = true;

                                        openChatContent(chat, true);
                                    } else {
                                        stringList.append(" - ").append(getNameFromChat(chat)).append("\n");
                                    }

                                    break;
                                }
                            }

                            if (!founded)
                                chatList.add(chat);
                        }

                        int chatsUpdated = chats.size() - (myChatUpdated ? 1 : 0);

                        if (chatsUpdated > 0) {
                            App.showNotification("Actualizaciones de chats", "Tienes actualizaciones en " + chatsUpdated + " chats: \n" + stringList);
                        }
                    }
                }
            }

            @Override
            public void onError(Object response) {
                if (response instanceof Error) {
                    Error error = (Error) response;
                    App.showNotification("Error al obtener las notificaciones", error.getError());
                }
            }
        });
    }

    public void newChat() throws IOException, InterruptedException {
        userApi.getAllUsers(new APICallback() {
            @Override
            @SuppressWarnings("unchecked cast")
            public void onSuccess(Object response) throws IOException {
                if (response instanceof List) {
                    List<User> users = (List<User>) response;

                    ObservableList<User> items = FXCollections.observableArrayList(users);


                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/saaweel/contactswindow.fxml"));
                    ListView<User> root = loader.load();

                    root.setItems(items);

                    root.setCellFactory(param -> new UserListCell());

                    Stage menuStage = new Stage();

                    menuStage.setTitle("Seleccionar contacto");
                    Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/saaweel/appIcon.png")));
                    menuStage.getIcons().add(icon);

                    menuStage.setScene(new Scene(root));
                    menuStage.show();

                    root.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                        if (newValue != null) {
                            openChat(newValue);

                            menuStage.close();
                        }
                    });
                }
            }

            @Override
            public void onError(Object response) {
                if (response instanceof Error) {
                    Error error = (Error) response;
                    App.showNotification("Error al cargar los contactos", error.getError());
                }
            }
        });
    }

    private void openChat(User user) {
        for (Chat chat: chatList) {
            if (user.getId() == chat.getUser1_id() || user.getId() == chat.getUser2_id()) {
                openChatContent(chat, false);

                return;
            }
        }

        chatApi.createChat(App.getMyUser().getId(), user.getId(), new APICallback() {
            @Override
            public void onSuccess(Object response) {
                if (response instanceof Chat) {
                    Chat chat = (Chat) response;

                    chatList.add(chat);

                    openChatContent(chat, false);
                }
            }

            @Override
            public void onError(Object response) {
                if (response instanceof Error) {
                    Error error = (Error) response;
                    App.showNotification("Error al crear un chat", error.getError());
                }
            }
        });
    }

    private void openChatContent(Chat chat, boolean onlyUpdate) {
        chatOpened = chat;

        chatPane.setVisible(true);

        if (chat.getUser1_id() != App.getMyUser().getId())
            chatName.setText(chat.getUser1_username());
        else
            chatName.setText(chat.getUser2_username());

        chatMessages.getChildren().clear();

        if (!onlyUpdate) {
            messageTextField.clear();
            messageTextField.requestFocus();
        }

        try {
            messageApi.getMessagesFromChat(chat.getId(), new APICallback() {
                @Override
                @SuppressWarnings("unchecked cast")
                public void onSuccess(Object response) {
                    if (response instanceof List) {
                        List<Message> messages = (List<Message>) response;

                        messages.sort(Comparator.comparingInt(Message::getId));

                        for (Message message: messages) {
                            addMessage(message.getIdsender(), message.getContent());
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

    public void handleKeyPress(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode() == KeyCode.ENTER && !messageTextField.getText().isEmpty()) {
            messageApi.sendMessageToChat(chatOpened.getId(), messageTextField.getText(), App.getMyUser().getId(), new APICallback() {
                @Override
                public void onSuccess(Object response) {
                    if (response instanceof Message) {
                        Message msg = (Message) response;

                        addMessage(msg.getIdsender(), msg.getContent());
                    }
                }

                @Override
                public void onError(Object response) {
                    if (response instanceof Error) {
                        Error error = (Error) response;
                        App.showNotification("Error al enviar un mensaje", error.getError());
                    }
                }
            });

            messageTextField.clear();
        }
    }

    private void addMessage(int sender, String content) {
        String username = sender == chatOpened.getUser1_id() ? chatOpened.getUser1_username() : chatOpened.getUser2_username();

        VBox messageVBox = new VBox();
        messageVBox.getStyleClass().add("message");

        Label userLabel = new Label(username);
        userLabel.getStyleClass().add("message-user");

        Label contentLabel = new Label(content);
        contentLabel.getStyleClass().add("message-content");

        messageVBox.getChildren().addAll(userLabel, contentLabel);

        chatMessages.getChildren().add(messageVBox);
    }

    private String getNameFromChat(Chat chat) {
        return chat.getUser1_id() != App.getMyUser().getId() ? chat.getUser1_username() : chat.getUser2_username();
    }
}