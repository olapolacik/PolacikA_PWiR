//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.net.Socket;
//import java.util.Scanner;
//
//public class ClientCalc {
//    private JFrame frame;  //utworzenie okienka gui
//    private JButton[] buttons; //przyciski
//    private JTextField textField; // pole tekstowe
//    private PrintWriter out;
//    private Scanner in;
//    private String user;
//
//    public ClientCalc(String user) {
//        this.user = user;
//
//        frame = new JFrame("Kalkulator");
//        frame.setSize(400, 600);
//        frame.setResizable(false);
//
//        ImageIcon icon = new ImageIcon("iconCalc.png");
//        frame.setIconImage(icon.getImage());
//
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        // Dodaj pole tekstowe
//        textField = new JTextField();
//        textField.setFont(new Font("Arial", Font.BOLD, 20));
//        textField.setEditable(false); // Ustawienie, aby pole tekstowe było tylko do odczytu
//
//        // kalkulator prosty 5 kolumn, 4 wiersze
//        JPanel panel = new JPanel(new GridLayout(4, 4));
//
//        buttons = new JButton[16];
//
//        // Dodaj napisy na przyciskach
//        String[] buttonLabels = {
//                "7", "8", "9", "*",
//                "4", "5", "6", "/",
//                "1", "2", "3", "-",
//                "C", ".", "=", "+"
//        };
//
//        for (int i = 0; i < 16; i++) {
//            buttons[i] = new JButton(buttonLabels[i]);
//            buttons[i].setBackground(Color.GRAY);
//            buttons[i].setForeground(Color.BLACK);
//            buttons[i].setFont(new Font("Arial", Font.BOLD, 20));
//            buttons[i].addActionListener(new ButtonClickListener());
//            panel.add(buttons[i]);
//        }
//
//        // Dodaj pole tekstowe do GUI
//        frame.getContentPane().setLayout(new BorderLayout());
//        frame.getContentPane().add(textField, BorderLayout.NORTH);
//        frame.getContentPane().add(panel, BorderLayout.CENTER);
//
//        frame.setVisible(true);
//    }
//
//    // Klasa wewnętrzna obsługująca zdarzenia przycisków
//    private class ButtonClickListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            JButton source = (JButton) e.getSource();
//            String buttonText = source.getText();
//
//            // Obsługa kliknięcia przycisków
//            switch (buttonText) {
//                case "C":
//                    // Wyczyszczenie pola
//                    textField.setText("");
//                    break;
//                case "=":
//                    // Obliczenia i wyświetlenie wyniku
//                    // Tu można dodać kod do wykonania operacji matematycznej
//                    break;
//                default:
//                    // Dodanie tekstu do pola wyniku
//                    textField.setText(textField.getText() + buttonText);
//                    break;
//            }
//        }
//    }
//
//    private void connectToServer() {
//        try {
//            Socket socket = new Socket("LocalHost", 1919);
//            out = new PrintWriter(socket.getOutputStream());
//            in = new Scanner(socket.getInputStream());
//
//            Thread listenerThread = new Thread(new ServerListener());
//            listenerThread.start();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private class ServerListener implements Runnable {
//        @Override
//        public void run() {
//            try {
//                while (true) {
//                    if (in.hasNextLine()) {
//                        String message = in.nextLine();
//                        // Handle received messages from the server
//                    }
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new ClientCalc("user");
//            }
//        });
//    }
//}
