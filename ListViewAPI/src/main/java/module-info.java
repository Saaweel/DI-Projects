module com.saaweel {
    requires javafx.controls;
    requires javafx.fxml;
    requires okhttp3;
    requires com.fasterxml.jackson.databind;

    opens com.saaweel.apimodels to com.fasterxml.jackson.databind;
    opens com.saaweel to javafx.fxml;
    exports com.saaweel;
}
