import javax.swing.*;
import java.awt.*;

public class Minutnik {
    public static void main(String[] args) {
        // Tworzenie ramki głównej
        JFrame frame = new JFrame("Przykład GUI Panelu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Tworzenie panelu
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        // Dodawanie komponentów do panelu
        JLabel label = new JLabel("Witaj, to jest etykieta!");
        JButton button = new JButton("To jest przycisk");

        panel.add(label);
        panel.add(button);

        // Dodawanie panelu do ramki
        frame.add(panel);

        // Wyświetlanie ramki
        frame.setVisible(true);
    }
}
