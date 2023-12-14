package com.saaweel;

import io.github.palexdev.materialfx.controls.MFXIconWrapper;
import io.github.palexdev.materialfx.controls.MFXSimpleNotification;
import io.github.palexdev.materialfx.factories.InsetsFactory;
import io.github.palexdev.mfxresources.fonts.IconDescriptor;
import io.github.palexdev.mfxresources.fonts.MFXFontIcon;
import io.github.palexdev.mfxresources.fonts.fontawesome.FontAwesomeSolid;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

import java.awt.*;
import java.util.Objects;

public class Notification extends MFXSimpleNotification {
    public Notification(String headerText, String text) {
        ImageView icon = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/saaweel/appIcon.png"))));

        icon.setFitHeight(35);
        icon.setFitWidth(35);
        icon.setClip(new Circle(17.5, 17.5, 17.5));

        Label headerLabel = new Label();
        headerLabel.textProperty().bind(new SimpleStringProperty(headerText));

        HBox header = new HBox(10, icon, headerLabel);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(InsetsFactory.of(5, 0, 5, 0));
        header.setMaxWidth(Double.MAX_VALUE);


        Label contentLabel = new Label();
        contentLabel.getStyleClass().add("content");
        StringProperty contentText = new SimpleStringProperty();
        contentText.set(text);
        contentLabel.textProperty().bind(contentText);
        contentLabel.setWrapText(true);
        contentLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        contentLabel.setAlignment(Pos.TOP_LEFT);


        BorderPane container = new BorderPane();
        container.getStyleClass().add("notification");
        container.setTop(header);
        container.setCenter(contentLabel);
        container.getStylesheets().add(ResourceLoader.load("notification.css"));
        container.setPadding(new Insets(10));

        setContent(container);
    }
}
