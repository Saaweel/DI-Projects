import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Configuración de preferencias");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setContentPane(new PreferenceConfig(frame).getWindow());

        frame.setSize(500, 500);
        frame.setVisible(true);
    }
}