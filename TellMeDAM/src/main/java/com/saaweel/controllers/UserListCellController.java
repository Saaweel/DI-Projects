package com.saaweel.controllers;

import javafx.scene.control.Label;
import org.example.api.model.User;

public class UserListCellController {
    public Label userName;
    public void setData(User user) {
        userName.setText(user.getUsername());
    }
}
