package com.saaweel;

import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.util.Objects;

public class FileTreeItem extends TreeItem<String> {
    private final String path;

    public FileTreeItem(File file) {
        super(file.getName());

        this.path = file.getPath();

        ImageView graphic = new ImageView(new Image(Objects.requireNonNull(getClass().getResource((file.isDirectory() ? "folder" : "file") + ".png")).toString()));

        graphic.setFitHeight(20);
        graphic.setPreserveRatio(true);
        super.setGraphic(graphic);
    }

    public String getPath() {
        return this.path;
    }
}
