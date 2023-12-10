package com.saaweel;

import com.saaweel.controllers.ChatListCellController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListCell;
import org.example.api.model.Chat;

public class ChatListCell extends ListCell<Chat> {
    @Override
    protected void updateItem(Chat chat, boolean empty) {
        super.updateItem(chat, empty);

        if (chat == null || empty) {
            setText(null);
            setGraphic(null);
            setBackground(null);
        } else {
            setText(null);
            setBackground(null);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("chatlistcell.fxml"));
                Parent root = loader.load();
                ChatListCellController controller = loader.getController();
                controller.setData(chat);
                setGraphic(root);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
