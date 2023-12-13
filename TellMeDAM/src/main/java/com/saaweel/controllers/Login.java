package com.saaweel.controllers;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.scene.layout.VBox;
import org.example.api.APICallback;
import org.example.api.UserAPIClient;

import com.saaweel.App;
import org.example.api.model.User;
import org.example.api.model.Error;

import java.io.IOException;
import java.util.prefs.Preferences;

public class Login {
    private boolean register = false;

    public VBox loginForm;
    public MFXButton button1;
    public MFXButton button2;
    public MFXTextField userField;
    public MFXTextField emailField;
    public MFXTextField passwordField;

    public UserAPIClient userApi;

    public void initialize() {
        userApi = new UserAPIClient();
    }

    public void onButton1() throws IOException, InterruptedException {
        String username = userField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();

        String error = "";

        if (register && (username == null || username.isEmpty())) {
            error += "\n - Usuario";
        }

        if (email == null || email.isEmpty()) {
            error += "\n - Correo electrónico";
        }

        if (password == null || password.isEmpty()) {
            error += "\n - Contraseña";
        }

        if (!error.isEmpty()) {
            error = "Campos inválido: " + error;
            App.showNotification("Debes completar todos los campos", error);
            return;
        }

        if (!email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")) {
            App.showNotification("Email inválido", "El formato correcto es: \nusuario@dominio.com");
            return;
        }

        if (register) {
            userApi.register(email, username, password, new APICallback() {
                @Override
                public void onSuccess(Object response) {
                    if (response instanceof User) {
                        User user = (User) response;

                        joinApp(user);
                    }
                }

                @Override
                public void onError(Object response) {
                    if (response instanceof Error) {
                        Error error = (Error) response;
                        App.showNotification("Error al registrarse", error.getError());
                    }
                }
            });
        } else {
            userApi.login(email, password, new APICallback() {
                @Override
                public void onSuccess(Object response) {
                    if (response instanceof User) {
                        User user = (User) response;

                        joinApp(user);
                    }
                }

                @Override
                public void onError(Object response) {
                    if (response instanceof Error) {
                        Error error = (Error) response;
                        App.showNotification("Error al iniciar sesión", error.getError());
                    }
                }
            });
        }
    }

    public void onButton2() {
        if (register) {
            register = false;

            userField.setVisible(false);
            userField.setManaged(false);

            button1.setText("Iniciar sesión");
            button2.setText("Registrarte");
        } else {
            register = true;

            userField.setVisible(true);
            userField.setManaged(true);

            button1.setText("Registrarte");
            button2.setText("Iniciar sesión");
        }
    }

    private void joinApp(User user) {
        App.setMyUser(user);

        try {
            App.setRoot("main");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Preferences preferences = Preferences.userNodeForPackage(App.class);
        preferences.put("UserEmail", user.getEmail());
        preferences.put("UserPass", user.getPassword());
    }
}
