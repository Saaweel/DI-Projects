import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculadora");

        frame.getContentPane().add(new Calculator().getPanel());
        frame.setSize(400, 800);

        frame.setVisible(true);
    }
}