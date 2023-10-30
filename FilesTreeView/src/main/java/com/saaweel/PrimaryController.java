package com.saaweel;

import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Objects;

@SuppressWarnings("unchecked")
public class PrimaryController {
    public TreeView<String> fileExplorer;

    public TextArea fileArea;

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
                        fileArea.clear();

                        String line;

                        while ((line = br.readLine()) != null) {
                            fileArea.appendText(line + "\n");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
