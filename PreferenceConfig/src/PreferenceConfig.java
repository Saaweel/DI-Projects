import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PreferenceConfig {
    private JPanel window;
    private JTextField nameInput;
    private JCheckBox notificationCheck;
    private JRadioButton opt2;
    private JRadioButton opt1;
    private JSpinner spinner;
    private JButton saveButton;

    public  PreferenceConfig(JFrame frame) {
        // Menu
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu menu = new JMenu("Archivo");
        menuBar.add(menu);

        JMenuItem saveItem = new JMenuItem("Guardar");
        menu.add(saveItem);

        // Option controller
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(opt1);
        buttonGroup.add(opt2);
        opt1.setSelected(true);

        // Spinner controller
        spinner.setModel(new SpinnerNumberModel(1, 1, 10, 1));

        ActionListener savePreferences = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameInput.getText();
                String notify = notificationCheck.isSelected() ? "Activadas" : "Desactivadas";
                String option = opt1.isSelected() ? "Opción 1" : "Opción 2";
                int numElements = (int) spinner.getValue();

                if (!name.isEmpty())
                    JOptionPane.showMessageDialog(window, "Nombre: " + name + "\nNotificaciones: " + notify + "\nOpción seleccionada: " + option + "\nNúmero de elementos: " + numElements);
                else
                    JOptionPane.showMessageDialog(window, "Debes introducir un nombre");
            }
        };

        saveItem.addActionListener(savePreferences);
        saveButton.addActionListener(savePreferences);
    }

    public JPanel getWindow() {
        return window;
    }
}
