package com.saaweel;

import io.github.palexdev.materialfx.enums.NotificationPos;
import io.github.palexdev.materialfx.notifications.MFXNotificationSystem;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.example.api.APICallback;
import org.example.api.UserAPIClient;
import org.example.api.model.User;

import java.io.IOException;
import java.util.Objects;
import java.util.prefs.Preferences;

/**
 * JavaFX App
 */
public class App extends Application {
    public static User myUser;
    public static final Object waitingForSceneLoad = new Object();

    private static Scene scene;

    public static void showNotification(String header, String text) {
        MFXNotificationSystem notySys =MFXNotificationSystem.instance();
        notySys.initOwner(getScene().getWindow());
        notySys.setPosition(NotificationPos.TOP_LEFT).publish(new Notification(header, text));
    }

    @Override
    public void start(Stage stage) throws IOException, InterruptedException {
        Preferences preferences = Preferences.userNodeForPackage(App.class);
        String userEmail = preferences.get("UserEmail", "");
        String userPass = preferences.get("UserPass", "");

        showStartingApp(stage);

        if (!userEmail.isEmpty() && !userPass.isEmpty()) {
            UserAPIClient userApi = new UserAPIClient();
            userApi.login(userEmail, userPass, new APICallback() {
                @Override
                public void onSuccess(Object response) {
                    if (response instanceof User) {
                        User user = (User) response;

                        setMyUser(user);
                    }

                    try {
                        startInternal(stage, true);
                    } catch (IOException | InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                @Override
                public void onError(Object response) {
                    preferences.put("UserEmail", "");
                    preferences.put("UserPass", "");

                    try {
                        startInternal(stage, false);
                    } catch (IOException | InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        } else {
            startInternal(stage, false);
        }
    }

    private void showStartingApp(Stage stage) throws IOException {
        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("appIcon.png")));
        stage.getIcons().add(icon);

        stage.setTitle("TellMeDAM");

        stage.setScene(new Scene(loadFXML("starting_app")));

        stage.show();
    }

    private void startInternal(Stage stage, boolean logged) throws IOException, InterruptedException {
        stage.hide();

        scene = new Scene(logged ?loadFXML("main") : loadFXML("login"));
        stage.setScene(scene);

        stage.setMaximized(true);

        stage.show();

        Platform.runLater(() -> MFXNotificationSystem.instance().initOwner(scene.getWindow()));
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

    public static User getMyUser() {
        return myUser;
    }

    public static void setMyUser(User u) {
        myUser = u;
    }
}