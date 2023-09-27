import javax.swing.*;

public class FileEditor {
    private JPanel window;
    private JTextArea textArea;

    public FileEditor(JFrame jframe) {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Archivo");

        JMenuItem openFile = new JMenuItem("Abrir");
        JMenuItem saveFile = new JMenuItem("Guardar");

        menu.add(openFile);
        menu.add(saveFile);

        menuBar.add(menu);

        jframe.setJMenuBar(menuBar);
    }

    public JPanel getWindow() {
        return window;
    }
}
