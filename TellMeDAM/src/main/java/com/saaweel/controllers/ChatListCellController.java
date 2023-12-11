package com.saaweel.controllers;

import com.saaweel.App;
import javafx.scene.control.Label;
import org.example.api.model.Chat;

public class ChatListCellController {
    public Label chatName;

    public void setData(Chat chat) {
        if (chat.getUser1_id() != App.getMyUser().getId())
            chatName.setText(chat.getUser1_username());
        else
            chatName.setText(chat.getUser2_username());
    }
}
