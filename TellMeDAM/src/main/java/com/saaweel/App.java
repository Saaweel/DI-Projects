package com.saaweel;

import io.github.palexdev.materialfx.controls.MFXNotificationCenter;
import io.github.palexdev.materialfx.controls.cell.MFXNotificationCell;
import io.github.palexdev.materialfx.enums.NotificationPos;
import io.github.palexdev.materialfx.notifications.MFXNotificationCenterSystem;
import io.github.palexdev.materialfx.notifications.MFXNotificationSystem;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import okhttp3.internal.Util;

import java.io.IOException;
import java.util.Objects;

/**
 * JavaFX App
 */
public class App extends Application {
    public static final Object waitingForSceneLoad = new Object();

    private static Scene scene;

    public static void showNotification(String header, String text) {
        MFXNotificationSystem notiSys =MFXNotificationSystem.instance();
        notiSys.initOwner(getScene().getWindow());
        notiSys.setPosition(NotificationPos.TOP_LEFT).publish(new Notification(header, text));
    }

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("main"));
        stage.setScene(scene);

        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("appIcon.png")));
        stage.getIcons().add(icon);

        stage.setTitle("TellMeDAM");

        stage.setMaximized(true);

        stage.show();

        Platform.runLater(() -> {
            MFXNotificationSystem.instance().initOwner(scene.getWindow());
        });
    }

    public static void setRoot(String fxml) throws IOException {
        synchronized (waitingForSceneLoad) {
            scene.setRoot(loadFXML(fxml));

            waitingForSceneLoad.notifyAll();
        }
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static Scene getScene() {
        return scene;
    }

    public static void main(String[] args) {
        launch();
    }

}