package TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private JFrame frame;
    private JButton[] buttons;
    private PrintWriter out;
    private Scanner in;
    private char playerSymbol;

    public Client(char playerSymbol) {
        this.playerSymbol = playerSymbol;
        frame = new JFrame("Kółko i Krzyżyk: " + playerSymbol);
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(3, 3));
        buttons = new JButton[9];

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            final int index = i;
            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buttonClicked(index);
                }
            });
            panel.add(buttons[i]);
        }

        frame.getContentPane().add(panel);
        frame.setVisible(true);

        connectToServer();
    }

    private void connectToServer() {
        try {
            Socket socket = new Socket("localhost", 12345);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new Scanner(socket.getInputStream());

            Thread listenerThread = new Thread(new ServerListener());
            listenerThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void buttonClicked(int index) {
        out.println(index);
    }

    private void updateBoard(String board) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 9; i++) {
                    buttons[i].setText(String.valueOf(board.charAt(i)));
                }
            }
        });
    }

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
            // Tutaj dodaj kod do zresetowania gry, np. zrestartowanie planszy
            // lub utworzenie nowej instancji gry.
            resetGame();
        } else {
            frame.dispose();
        }
    }

    private void resetGame() {
        // Tutaj dodaj kod do zresetowania gry, np. zrestartowanie planszy.
        // Możesz również zainicjować nową instancję gry, jeśli to konieczne.
        // frame.dispose(); // W zależności od potrzeb
    }

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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Client('X');
            }
        });


        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() { new Client('O'); }
        });

    }
}
