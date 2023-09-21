import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("ListaDeLaCompra");
        frame.getContentPane().add(new ListaDeLaCompra().getWindow());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        frame.setSize(800, 400);

        frame.setVisible(true);
    }
}