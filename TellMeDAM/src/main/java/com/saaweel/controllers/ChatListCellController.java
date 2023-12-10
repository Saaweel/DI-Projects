package com.saaweel.controllers;

import javafx.scene.control.Label;
import org.example.api.model.Chat;

public class ChatListCellController {
    public Label chatName;

    public void setData(Chat chat) {
        chatName.setText(chat.getUser1_username());
    }
}
