package com.saaweel.controllers;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;

public class Login {
    private boolean register = false;
    public MFXButton button1;
    public MFXButton button2;
    public MFXTextField userField;
    public MFXTextField emailField;
    public MFXTextField passwordField;

    public void onButton1() {
        if (!register) {
            System.out.println("Login");
        } else {
            System.out.println("Register");
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
