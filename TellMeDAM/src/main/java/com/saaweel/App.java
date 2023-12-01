package com.saaweel;

import javafx.application.Application;
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

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("main"));
        stage.setScene(scene);

        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("appIcon.png")));
        stage.getIcons().add(icon);

        stage.setTitle("TellMeDAM");

        stage.setMaximized(true);

        stage.show();
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