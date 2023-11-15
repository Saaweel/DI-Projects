package com.saaweel;

import com.saaweel.apimodels.AnimeData;
import com.saaweel.apimodels.Datum;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;

public class PrimaryController {
    private KitsuAPIClient kitsuClient;

    public ListView<Datum> listView;

    public TextField limitInput;

    public TextField offsetInput;

    public void initialize() {
        kitsuClient = new KitsuAPIClient();

        listView.setCellFactory(param -> new AnimeFXMLListCell());

        loadAnimes(10, 0);
    }

    public void search() {
        try {
            if (!limitInput.getText().isEmpty() && !offsetInput.getText().isEmpty()) {
                loadAnimes(Integer.parseInt(limitInput.getText()), Integer.parseInt(offsetInput.getText()));
            }
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Los parámetros introducidos no son válido").show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadAnimes(int limit, int offset) {
        try {
            AnimeData animes = kitsuClient.getAnimes(limit, offset);
            listView.setItems(FXCollections.observableList(animes.data));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
