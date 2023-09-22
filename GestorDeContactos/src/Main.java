import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculadora");

        frame.getContentPane().add(new Contactos().getWindow());
        frame.setSize(400, 400);

        frame.setVisible(true);
    }
}