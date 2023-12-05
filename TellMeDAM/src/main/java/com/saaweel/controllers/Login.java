package com.saaweel.controllers;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.scene.layout.VBox;
import org.example.api.UserAPIClient;

import com.saaweel.App;

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

    public void onButton1() {
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
        }

        if (register) {
            System.out.println("Registro");
        } else {
            System.out.println("Login");
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
}
