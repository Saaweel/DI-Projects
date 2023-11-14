package com.saaweel;

import com.saaweel.apimodels.Datum;
import javafx.scene.control.ListCell;

public class AnimeFXMLListCell extends ListCell<Datum> {
    @Override
    protected void updateItem(Datum datum, boolean empty) {
        super.updateItem(datum, empty);

        if (datum == null || empty) {
            setText(null);
            setGraphic(null);
        } else {
            String title = datum.attributes.canonicalTitle;
            String posterImage = datum.attributes.posterImage.medium;
            String synopsis = datum.attributes.synopsis;
            String startDate = datum.attributes.startDate;
            String ageRating = datum.attributes.ageRating;
            int episodeCount = datum.attributes.episodeCount;
            String showType = datum.attributes.showType;
        }
    }
}
