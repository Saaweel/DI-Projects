package com.saaweel;

import javafx.scene.control.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Objects;

public class PrimaryController {
    public TreeView<String> fileExplorer;

    public TabPane tabExplorer;

    public MenuItem saveButton;

    private FileTreeItem loadFiles(File dir) {
        FileTreeItem parent = new FileTreeItem(dir);

        for (File file: Objects.requireNonNull(dir.listFiles())) {
            if (file.isDirectory()) {
                parent.getChildren().add(loadFiles(file));
            } else {
                parent.getChildren().add(new FileTreeItem(file));
            }
        }

        return parent;
    }

    public void initialize() {
        FileTreeItem root = loadFiles(new File(System.getProperty("user.dir")));
        fileExplorer.setRoot(root);

        fileExplorer.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            FileTreeItem selected = (FileTreeItem) newValue;

            if (selected != null) {
                File file = new File(selected.getPath());

                if (file.isFile()) {

                    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                        StringBuilder content = new StringBuilder();

                        String line;

                        while ((line = br.readLine()) != null) {
                            content.append(line).append("\n");
                        }

                        Tab newTab = new Tab(file.getName(), new TextArea(content.toString()));
                        newTab.setId(file.getPath());
                        tabExplorer.getTabs().add(newTab);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        saveButton.setOnAction(e -> {
            Tab selectedTab = tabExplorer.getSelectionModel().selectedItemProperty().get();

            if (selectedTab != null) {
                File file = new File(selectedTab.getId());

                try (FileWriter fileWriter = new FileWriter(file)) {
                    TextArea textArea = (TextArea) tabExplorer.getSelectionModel().selectedItemProperty().get().getContent();

                    fileWriter.write(textArea.getText());

                    new Alert(Alert.AlertType.CONFIRMATION, "El fichero ha sido guardado").show();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
