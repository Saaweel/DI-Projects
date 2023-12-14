package com.saaweel;

import javafx.scene.control.Label;

public class DigimonController {
    public Label id;
    public Label name;

    public void setData(Digimon digimon) {
        id.setText("ID: " + digimon.getId());
        name.setText("Name: " + digimon.getName());
    }
}
