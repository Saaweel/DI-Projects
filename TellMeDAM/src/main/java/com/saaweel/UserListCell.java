package com.saaweel;

import com.saaweel.controllers.UserListCellController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListCell;
import org.example.api.model.User;

public class UserListCell extends ListCell<User> {
    @Override
    protected void updateItem(User user, boolean empty) {
        super.updateItem(user, empty);

        if (user == null || empty) {
            setText(null);
            setGraphic(null);
            setBackground(null);
        } else {
            setText(null);
            setBackground(null);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("userlistcell.fxml"));
                Parent root = loader.load();
                UserListCellController controller = loader.getController();
                controller.setData(user);
                setGraphic(root);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}