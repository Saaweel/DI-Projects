import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class Contactos {
    private JPanel window;
    private JTextField nameInput;
    private JTextField emailInput;
    private JTable contactsTable;
    DefaultTableModel tableModel = new DefaultTableModel();
    private JButton agregarButton;
    private JButton editarButton;
    private JButton eliminarButton;

    public Contactos() {
        contactsTable.setModel(tableModel);
        tableModel.addColumn("Nombre");
        tableModel.addColumn("Email");
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameInput.getText();
                String email = emailInput.getText();

                if (!name.isEmpty() && !email.isEmpty()) {
                    Vector<String> v = new Vector<>();
                    v.add(name);
                    v.add(email);
                    tableModel.addRow(v);
                    nameInput.setText("");
                    emailInput.setText("");
                } else {
                    JOptionPane.showMessageDialog(window, "Debes introducir un nombre y email");
                }
            }
        });
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selected = contactsTable.getSelectedRow();

                if (selected != -1) {
                    String name = nameInput.getText();
                    String email = emailInput.getText();

                    if (!name.isEmpty() && !email.isEmpty()) {
                        tableModel.setValueAt(name, selected, 0);
                        tableModel.setValueAt(email, selected, 1);
                        nameInput.setText("");
                        emailInput.setText("");
                    } else {
                        JOptionPane.showMessageDialog(window, "Debes introducir un nombre y email");
                    }
                } else {
                    JOptionPane.showMessageDialog(window, "Debes seleccionar un contacto");
                }
            }
        });
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selected = contactsTable.getSelectedRow();

                if (selected != -1) {
                    tableModel.removeRow(selected);
                } else {
                    JOptionPane.showMessageDialog(window, "Debes seleccionar un contacto");
                }
            }
        });
    }

    public JPanel getWindow() {
        return window;
    }
}
