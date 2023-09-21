import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator {
    private JPanel panel;
    private JButton a7Button;
    private JButton a9Button;
    private JButton a8Button;
    private JButton a1Button;
    private JButton a2Button;
    private JButton a3Button;
    private JButton a4Button;
    private JButton a5Button;
    private JButton a6Button;
    private JLabel result;

    public Calculator() {
        a7Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                result.setText("7");
            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }

    public JButton getA7Button() {
        return a7Button;
    }

    public JButton getA9Button() {
        return a9Button;
    }

    public JButton getA8Button() {
        return a8Button;
    }

    public JButton getA1Button() {
        return a1Button;
    }

    public JButton getA2Button() {
        return a2Button;
    }

    public JButton getA3Button() {
        return a3Button;
    }

    public JButton getA4Button() {
        return a4Button;
    }

    public JButton getA5Button() {
        return a5Button;
    }

    public JButton getA6Button() {
        return a6Button;
    }
}
