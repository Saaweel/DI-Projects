import javax.swing.*;
import java.awt.*;

public class Contacto {
    private JPanel window;
    public JLabel title;
    private JTextField nameInput;
    private JTextField emailInput;
    private JTextField phoneInput;
    private JTextField streetInput;
    private JTextField messageInput;
    private JCheckBox newsCheck;
    private JCheckBox spamCheck;
    private JRadioButton maleRadio;
    private JRadioButton femaleRadio;
    private JRadioButton otherRadio;
    private JButton sendButton;

    public Contacto(JFrame frame) {
        /* Menu */
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu menu = new JMenu("Inicio");
        menuBar.add(menu);

        JMenuItem home = new JMenuItem("Inicio");
        menu.add(home);
        JMenuItem services = new JMenuItem("Servicios");
        menu.add(services);
        JMenuItem about = new JMenuItem("Acerca de nosotros");
        menu.add(about);
        JMenuItem contact = new JMenuItem("Contacto");
        menu.add(contact);

        title.setOpaque(true);
        title.setForeground(Color.WHITE);

        // Gender controller
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);
        genderGroup.add(otherRadio);

        sendButton.addActionListener(e -> {
            String name = nameInput.getText();
            String email = emailInput.getText();
            String phone = phoneInput.getText();
            String street = streetInput.getText();
            String message = messageInput.getText();
            boolean news = newsCheck.isSelected();
            boolean spam = spamCheck.isSelected();

            if (!name.isEmpty() && !email.isEmpty() && !phone.isEmpty() && !street.isEmpty() && !message.isEmpty() && (maleRadio.isSelected() || femaleRadio.isSelected() || otherRadio.isSelected())) {
                JOptionPane.showMessageDialog(window,
                        "Nombre: " + name +
                        "\nEmail: " + email +
                        "\nTeléfono: " + phone +
                        "\nDirección: " + street +
                        "\nMensaje: " + message +
                        "\nSuscribirse al boletín: " + (newsCheck.isSelected() ? "Si" : "No") +
                        "\nRecibir promociones: " + (spamCheck.isSelected() ? "Si" : "No") +
                        "\nGénero: " + (maleRadio.isSelected() ? "Masculino" : (femaleRadio.isSelected() ? "Femenino" : "Otro")));
            } else {
                JOptionPane.showMessageDialog(window, "Debes completar todos los campos");
            }
        });
    }

    public JPanel getWindow() {
        return window;
    }
}
