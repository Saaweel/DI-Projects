package com.saaweel;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListCell;

public class BookCellFactory extends ListCell<Book> {
    @Override
    public void updateItem(Book item, boolean empty) {
        super.updateItem(item, empty);

        if (item != null && !empty) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("book_list_cell.fxml"));
                Parent root = loader.load();
                BookController controller = loader.getController();
                controller.setData(item);
                setText(null);
                setGraphic(root);
                setBackground(null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            setText(null);
            setGraphic(null);
            setBackground(null);
        }
    }
}
