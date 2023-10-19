package com.saaweel;

import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.util.Objects;

public class PrimaryController {
    public VBox window;

    public GridPane contentGrid;

    private File currentDir;
    private void loadAbsoluteRoute(String dir) {
        contentGrid.getChildren().clear();

        currentDir = new File(dir);

        System.out.println(currentDir);
        if (currentDir.isDirectory() && currentDir.length() > 0) {
            try {
                File[] files = currentDir.listFiles();
                for (int i = 0; i < Objects.requireNonNull(files).length; i++) {
                    File file = files[i];

                    if (file.isDirectory()) {
                        Button dirButton = new Button(file.getName());

                        dirButton.setOnAction(event -> loadAbsoluteRoute(file.getPath()));

                        contentGrid.add(dirButton, i % 4, i / 4);
                    } else {
                        if (file.getName().endsWith(".png") || file.getName().endsWith(".jpg") || file.getName().endsWith(".jpeg") || file.getName().endsWith(".gif")) {
                            ImageView imageView = new ImageView(file.toURI().toString());
                            imageView.setFitHeight(100);
                            imageView.setFitWidth(100);
                            contentGrid.add(imageView, i % 4, i / 4);
                        } else {
                            Label label = new Label(file.getName());
                            contentGrid.add(label, i % 4, i / 4);
                        }
                    }
                }
            } catch (Exception e) {
                e.fillInStackTrace();
            }

        }


        contentGrid.getChildren().forEach(node -> GridPane.setHalignment(node, HPos.CENTER));
    }

    public void backHandler() {
        try {
            loadAbsoluteRoute(currentDir.getParent());
        } catch (Exception e) {
            System.out.println("Estás en el directorio principal");
        }
    }
    @FXML
    public void initialize() {
        loadAbsoluteRoute(System.getProperty("user.home"));

        contentGrid.maxWidthProperty().bind(window.widthProperty().subtract(20));
        contentGrid.getColumnConstraints().forEach(column -> column.setPercentWidth(25));
    }
}
