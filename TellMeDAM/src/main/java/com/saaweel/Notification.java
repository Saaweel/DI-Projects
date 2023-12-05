package com.saaweel;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXIconWrapper;
import io.github.palexdev.materialfx.controls.MFXSimpleNotification;
import io.github.palexdev.materialfx.enums.NotificationState;
import io.github.palexdev.materialfx.factories.InsetsFactory;
import io.github.palexdev.mfxresources.fonts.IconDescriptor;
import io.github.palexdev.mfxresources.fonts.MFXFontIcon;
import io.github.palexdev.mfxresources.fonts.fontawesome.FontAwesomeSolid;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;

public class Notification extends MFXSimpleNotification {
    public Notification(String headerText, String text) {

        MFXFontIcon fi = new MFXFontIcon();
        IconDescriptor desc = FontAwesomeSolid.BELL;
        fi.setDescription(desc.getDescription());
        fi.setSize(16);
        MFXIconWrapper icon = new MFXIconWrapper(fi, 32);

        Label headerLabel = new Label();
        headerLabel.textProperty().bind(new SimpleStringProperty(headerText));;

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
