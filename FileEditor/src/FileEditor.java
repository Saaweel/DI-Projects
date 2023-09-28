import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileEditor {
    private JPanel window;
    private JTextArea textArea;

    public FileEditor(JFrame frame) {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Archivo");

        JMenuItem openFile = new JMenuItem("Abrir");
        JMenuItem saveFile = new JMenuItem("Guardar");

        menu.add(openFile);
        menu.add(saveFile);

        menuBar.add(menu);

        frame.setJMenuBar(menuBar);

        openFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    try {
                        textArea.setText("");
                        Scanner scanner = new Scanner(selectedFile);
                        while (scanner.hasNextLine()) {
                            textArea.append(scanner.nextLine() + "\n");
                        }
                        scanner.close();
                    } catch (Exception ex) {
                        ex.fillInStackTrace();
                    }
                }
            }
        });
        saveFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    try {
                        FileWriter fileWriter = new FileWriter(selectedFile);
                        fileWriter.write(textArea.getText());
                        fileWriter.close();
                    } catch (Exception ex) {
                        ex.fillInStackTrace();
                    }
                }
            }
        });
    }

    public JPanel getWindow() {
        return window;
    }
}
