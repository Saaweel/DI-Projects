package com.saaweel.informaciondecontacto;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import com.opencsv.CSVWriter;

public class Controller {
    public TextField nameInput;
    public TextField emailInput;
    public TextField phoneInput;
    public DatePicker dateInput;
    public ListView<String> preferencesInput;

    public void initialize() {
        dateInput.setConverter(new StringConverter<>() {
            final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });

        preferencesInput.getItems().addAll("Deportes", "Música", "Viajes", "Cine", "Libros");
    }

    public void  clearInputs() {
        nameInput.setText("");
        emailInput.setText("");
        phoneInput.setText("");
        dateInput.setValue(null);
        preferencesInput.getSelectionModel().select(null);
    }

    public void sendContact() {
        String name = nameInput.getText();
        String email = emailInput.getText();
        String phone = phoneInput.getText();

        String preference = preferencesInput.getSelectionModel().getSelectedItem();

        if (!name.isEmpty() && !email.isEmpty() && !phone.isEmpty() && dateInput.getValue() != null && !preference.isEmpty()) {
            String dob = dateInput.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            try (CSVWriter csvWriter = new CSVWriter(new FileWriter("contactos.csv", true))) {
                String [] datos = {name, email, phone, dob, preference};
                csvWriter.writeNext(datos);
                clearInputs();
            } catch (IOException e) {
                e.fillInStackTrace();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Debes completar todos los campos").show();
        }
    }

    public void cancel() {
        Platform.exit();
    }
}
