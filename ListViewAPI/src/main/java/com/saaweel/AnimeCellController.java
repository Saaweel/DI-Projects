package com.saaweel;

import com.saaweel.apimodels.Datum;

public class AnimeCellController {
    public void setData(Datum datum) {
        String title = datum.attributes.canonicalTitle;
        String posterImage = datum.attributes.posterImage.medium;
        String synopsis = datum.attributes.synopsis;
        String startDate = datum.attributes.startDate;
        String ageRating = datum.attributes.ageRating;
        int episodeCount = datum.attributes.episodeCount;
        String showType = datum.attributes.showType;


    }
}
