package com.saaweel;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListCell;

public class FilmFXMLListCell extends ListCell<Film> {
    @Override
    protected void updateItem(Film item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (item.getImage().isEmpty()) {
                setText(item.getTitle() + " (" + item.getYear() + ") - " + item.getDirector());
            } else {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("filmlistcell.fxml"));
                    Parent root = loader.load();
                    FilmCellController controller = loader.getController();
                    controller.setFilmData(item.getTitle(), item.getDirector(), item.getYear(), item.getImage());
                    setGraphic(root);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}