package com.saaweel;

import com.saaweel.apimodels.Datum;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AnimeCellController {
    public Label title;
    public Label synopsis;
    public Label startDate;
    public Label ageRating;
    public Label episodes;
    public Label showType;
    public ImageView image;
    public void setData(Datum datum) {
        title.setText(datum.attributes.canonicalTitle);
        synopsis.setText(datum.attributes.synopsis);
        startDate.setText("Start Date: " + datum.attributes.startDate);
        ageRating.setText("Age Rating: " + datum.attributes.ageRating);
        episodes.setText("Episodes: " + datum.attributes.episodeCount);
        showType.setText("Show Type: " + datum.attributes.showType);
        image.setImage(new Image(datum.attributes.posterImage.medium));
    }
}
