module com.saaweel.informaciondecontacto {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.opencsv;


    opens com.saaweel.informaciondecontacto to javafx.fxml;
    exports com.saaweel.informaciondecontacto;
}