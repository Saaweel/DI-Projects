package com.saaweel;

import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;

import java.io.*;
import java.util.Scanner;

public class PrimaryController {
    public BorderPane window;
    public TextArea textViewer;
    public void handleClearButton() {
        textViewer.clear();
    }

    public void openDocument() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecciona un archivo");
        File file = fileChooser.showOpenDialog(window.getScene().getWindow());

        if (file != null && file.exists() && file.isFile()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;

                while ((line = br.readLine()) != null) {
                    textViewer.appendText(line + "\n");
                }
            } catch (Exception e) {
                e.fillInStackTrace();
            }
        }
    }

    public void saveDocument() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecciona un archivo");
        File file = fileChooser.showOpenDialog(window.getScene().getWindow());

        if (file != null && file.exists() && file.isFile()) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                Scanner textAreaReader = new Scanner(textViewer.getText());

                while (textAreaReader.hasNextLine()) {
                    bw.write(textAreaReader.nextLine() + "\n");
                }
            } catch (Exception e) {
                e.fillInStackTrace();
            }
        }
    }
}
