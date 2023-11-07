package com.saaweel;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FilmCellController {
    public ImageView filmImage;

    public Label filmLabel;

    public void setFilmData(String title, String director, int year, String image) {
        filmImage.setImage(new Image(image));
        filmLabel.setText(title + " (" + year + ") - " + director);
    }
}