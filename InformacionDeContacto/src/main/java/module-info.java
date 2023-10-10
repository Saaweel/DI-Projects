module com.saaweel.informaciondecontacto {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.saaweel.informaciondecontacto to javafx.fxml;
    exports com.saaweel.informaciondecontacto;
}