package com.saaweel.controllers;

import com.saaweel.App;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.stage.Stage;
import org.example.api.APICallback;
import org.example.api.UserAPIClient;
import org.example.api.model.Error;
import org.example.api.model.User;

import java.io.IOException;
import java.util.prefs.Preferences;

public class PersonalData {
    private Stage stage;
    public MFXTextField userField;
    public MFXTextField emailField;
    public MFXTextField photoUrlField;
    public MFXPasswordField passwordField;

    public void sendData() throws IOException, InterruptedException {
        String user = userField.getText();
        String email = emailField.getText();
        String photoUrl = photoUrlField.getText();
        String pass = passwordField.getText();

        if (!user.isEmpty() && !email.isEmpty() && !pass.isEmpty()) {
            UserAPIClient userApi = new UserAPIClient();

            userApi.updateUser(App.getMyUser().getId(), user, pass, email, photoUrl, new APICallback() {
                @Override
                public void onSuccess(Object response) {
                    if (response instanceof User) {
                        User newUser = (User) response;

                        newUser.setPassword(pass);

                        Preferences preferences = Preferences.userNodeForPackage(App.class);
                        preferences.put("UserEmail", newUser.getEmail());
                        preferences.put("UserPass", newUser.getPassword());

                        App.setMyUser(newUser);

                        stage.close();
                    }
                }

                @Override
                public void onError(Object response) {
                    if (response instanceof Error) {
                        Error error = (Error) response;
                        App.showNotification("Error al actualizar los datos", error.getError());
                    }
                }
            });
        }
    }

    public void initialize() {
        User user = App.getMyUser();

        userField.setText(user.getUsername());
        emailField.setText(user.getEmail());
        photoUrlField.setText(user.getPhotourl());
        passwordField.setText(user.getPassword());
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
