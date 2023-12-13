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
    public MFXPasswordField oldPasswordField;

    public void sendData() throws IOException, InterruptedException {
        String username = userField.getText();
        String email = emailField.getText();
        String photoUrl = photoUrlField.getText();
        String password = passwordField.getText();
        String oldPass = oldPasswordField.getText();

        String error = "";

        if (username == null || username.isEmpty()) {
            error += "\n - Usuario";
        }

        if (email == null || email.isEmpty()) {
            error += "\n - Correo electrónico";
        }

        if (password == null || password.isEmpty()) {
            error += "\n - Nueva contraseña";
        }

        if (oldPass == null || oldPass.isEmpty()) {
            error += "\n - Contraseña actual";
        }

        if (!error.isEmpty()) {
            error = "Campos inválido: " + error;
            App.showNotification("Debes completar todos los campos", error);
            return;
        }

        if (!oldPass.equals(App.getMyUser().getPassword())) {
            App.showNotification("Contraseña actual inválida", "Por motivos de seguridad debes poner tu contraseña actual");
            return;
        }

        if (!email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")) {
            App.showNotification("Email inválido", "El formato correcto es: \nusuario@dominio.com");
            return;
        }

        UserAPIClient userApi = new UserAPIClient();

        userApi.updateUser(App.getMyUser().getId(), username, password, email, photoUrl, new APICallback() {
            @Override
            public void onSuccess(Object response) {
                if (response instanceof User) {
                    User newUser = (User) response;

                    newUser.setPassword(username);

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
