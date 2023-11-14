package com.saaweel;

import com.saaweel.apimodels.Datum;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListCell;

public class AnimeFXMLListCell extends ListCell<Datum> {
    @Override
    protected void updateItem(Datum datum, boolean empty) {
        super.updateItem(datum, empty);

        if (datum == null || empty) {
            setText(null);
            setGraphic(null);
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("animelistcell.fxml"));
                Parent root = loader.load();
                AnimeCellController controller = loader.getController();
                controller.setData(datum);
                setGraphic(root);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
