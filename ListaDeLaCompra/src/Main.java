import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Lista De La Compra");
        frame.getContentPane().add(new MainFrame().getWindow());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        frame.setSize(800, 400);

        frame.setVisible(true);
    }
}