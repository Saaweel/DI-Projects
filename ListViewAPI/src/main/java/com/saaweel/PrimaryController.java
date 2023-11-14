package com.saaweel;

import com.saaweel.apimodels.AnimeData;
import com.saaweel.apimodels.Datum;
import javafx.collections.FXCollections;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.io.IOException;

public class PrimaryController {

    public ListView<Datum> listView;

    public void initialize() {

        KitsuAPIClient client = new KitsuAPIClient();
        try {
            AnimeData animes = client.getAnimes(10, 10);
            listView.setItems(FXCollections.observableList(animes.data));

            listView.setCellFactory(param -> new AnimeFXMLListCell());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
