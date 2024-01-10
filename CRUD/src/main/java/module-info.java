module com.saaweel {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.saaweel to javafx.fxml;
    exports com.saaweel;
}
