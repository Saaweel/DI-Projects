package com.saaweel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class PrimaryController {
    public ListView<Digimon> digimonsListView;
    public VBox content;
    public ImageView digimonImage;
    public Label digimonId;
    public Label digimonName;
    public TextField searchInput;

    public void initialize() {
        ObservableList<Digimon> digimonsData = FXCollections.observableArrayList();

        digimonsData.add(new Digimon(1, "Agumon", "https://digimon.shadowsmith.com/img/agumon.jpg"));
        digimonsData.add(new Digimon(2, "Gabumon", "https://digimon.shadowsmith.com/img/gabumon.jpg"));
        digimonsData.add(new Digimon(3, "Patamon", "https://digimon.shadowsmith.com/img/patamon.jpg"));
        digimonsData.add(new Digimon(4, "Togemon", "https://digimon.shadowsmith.com/img/togemon.jpg"));
        digimonsData.add(new Digimon(5, "Kabuterimon", "https://digimon.shadowsmith.com/img/kabuterimon.jpg"));
        digimonsData.add(new Digimon(6, "Devimon", "https://digimon.shadowsmith.com/img/devimon.jpg"));
        digimonsData.add(new Digimon(7, "Greymon", "https://digimon.shadowsmith.com/img/greymon.jpg"));
        digimonsData.add(new Digimon(8, "Ogremon", "https://digimon.shadowsmith.com/img/ogremon.jpg"));
        digimonsData.add(new Digimon(9, "Seadramon", "https://digimon.shadowsmith.com/img/seadramon.jpg"));
        digimonsData.add(new Digimon(10, "WarGreymon", "https://digimon.shadowsmith.com/img/wargreymon.jpg"));

        digimonsListView.setItems(digimonsData);

        digimonsListView.setCellFactory(param -> new DigimonCellFactory());

        digimonsListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                digimonImage.setImage(newValue.getImage());

                digimonId.setText("ID: " + newValue.getId());

                digimonName.setText("Name: " + newValue.getName());

                content.setVisible(true);
            }
        });

        searchInput.textProperty().addListener((observable, oldValue, newValue) -> {
            digimonsListView.setItems(digimonsData.filtered(param -> param.getName().toLowerCase().contains(newValue.toLowerCase())));
        });
    }
}
