import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame {
    private JTextField inputText;
    private JButton addButton;
    private JButton removeButton;
    private JList list;
    private JPanel window;
    DefaultListModel<String> listModel = new DefaultListModel<>();

    public MainFrame() {
        list.setModel(listModel);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String result = inputText.getText();

                if (!result.isEmpty()) {
                    listModel.addElement(result);
                    inputText.setText("");
                }
            }
        });
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selected = list.getSelectedIndex();

                if (selected != -1)
                    listModel.remove(selected);
            }
        });
    }

    public JPanel getWindow() {
        return window;
    }
}