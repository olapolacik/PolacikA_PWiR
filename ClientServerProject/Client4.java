package ClientServerProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client4 {

    private JFrame frame;  
    private JButton[] buttons; 
    private PrintWriter out;
    private Scanner in;
    private char player;

    // Konstruktor klienta
    public Client4(char player) {
        this.player = player;
        frame = new JFrame("Gracz: " + player);
        frame.setSize(600, 600);
        frame.setResizable(false);

        ImageIcon icon = new ImageIcon("Laboratorium09/TicTacToe/tictactoe.png");
        frame.setIconImage(icon.getImage());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Plansza o rozmiarze 6 na 7
        JPanel panel = new JPanel(new GridLayout(6, 7));

        // Utworzenie 42 przycisków
        buttons = new JButton[42];

        for (int i = 0; i < 42; i++) {
            buttons[i] = new JButton("");
            final int index = i;
            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buttonClicked(index);
                }
            });

            buttons[i].setBackground(Color.BLACK);
            buttons[i].setForeground(Color.WHITE);
            buttons[i].setFont(new Font("Arial", Font.BOLD, 30));

            panel.add(buttons[i]);
        }

        frame.getContentPane().add(panel);
        frame.setVisible(true);

        connectToServer();
    }

    // Metoda łącząca klienta z serwerem
    private void connectToServer() {
        try {
            Socket socket = new Socket("Localhost", 1919);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new Scanner(socket.getInputStream());

            Thread listenerThread = new Thread(new ServerListener());
            listenerThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Metoda obsługująca kliknięcie przycisku
    private void buttonClicked(int index) {
        out.println(index);
    }

    // Metoda aktualizująca planszę
    private void updateBoard(String board) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 42; i++) {
                    buttons[i].setText(String.valueOf(board.charAt(i)));
                    buttons[i].setForeground(Color.WHITE); // Ustawienie koloru tekstu na biały
                    buttons[i].setText(String.valueOf(board.charAt(i)));
                    buttons[i].setFont(new Font("Arial", Font.BOLD, 30)); // Ponowne ustawienie czcionki
                }
            }
        });
    }

    // Metoda wyświetlająca informację o zwycięzcy
    private void showWinner(char winnerSymbol) {
        int option = JOptionPane.showOptionDialog(frame,
                "Gracz " + winnerSymbol + " wygrał! Czy chcesz zagrać ponownie?",
                "Koniec gry",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                new Object[]{"Tak", "Nie"},
                "Tak");

        if (option == JOptionPane.YES_OPTION) {
            resetGame();
        } else {
            frame.dispose();
        }
    }

    // Metoda resetująca grę
    private void resetGame() {
        for (int i = 0; i < 42; i++) {
            buttons[i].setText("");
        }

        out.println("RESET"); // Wysłanie wiadomości do serwera o restarcie gry
    }

    // Wewnętrzna klasa nasłuchująca serwera
    private class ServerListener implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    if (in.hasNextLine()) {
                        String message = in.nextLine();

                        if (message.startsWith("BOARD:")) {
                            String board = message.substring(6);
                            updateBoard(board);
                        } else if (message.startsWith("WINNER:")) {
                            char winnerSymbol = message.charAt(7);
                            showWinner(winnerSymbol);
                        } else if (message.equals("RESET")) {
                            // Zresetuj planszę
                            updateBoard("         ");
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Metoda uruchamiająca klienta
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Client4('X');
            }
        });

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Client4('O');
            }
        });
    }
}
