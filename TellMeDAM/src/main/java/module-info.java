module com.saaweel {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.fasterxml.jackson.databind;
    requires okhttp3;
    requires com.google.gson;
    requires okhttp3.logging;
    requires java.net.http;
    requires MaterialFX;
    requires mfx.resources;

    opens org.example.api to com.google.gson, javafx.fxml;
    opens org.example.api.model to com.google.gson;
    opens com.saaweel to javafx.fxml;
    exports com.saaweel;
    exports org.example.api;
    exports com.saaweel.controllers;
    opens com.saaweel.controllers to javafx.fxml;
}
