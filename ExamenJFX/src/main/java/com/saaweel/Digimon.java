package com.saaweel;

import javafx.scene.image.Image;

public class Digimon {
    private int id;
    private String name;
    private Image image;

    public Digimon(int id, String name, String imageUrl) {
        this.id = id;
        this.name = name;
        this.image = new Image(imageUrl);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Image getImage() {
        return image;
    }

    public void setImageUrl(String imageUrl) {
        this.image = new Image(imageUrl);
    }
}
