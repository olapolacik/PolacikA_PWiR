package Laboratorium09.TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client
{

    private JFrame frame;  //utworzenie okienka gui
    private JButton[] buttons; //przyciski
    private PrintWriter out;
    private Scanner in;
    private char player;


    public Client(char player) {
        this.player = player;
        frame = new JFrame("Gracz: " + player);
        frame.setSize(500, 500);
        frame.setResizable(false);

        ImageIcon icon = new ImageIcon("Laboratorium09/TicTacToe/tictactoe.png");
        frame.setIconImage(icon.getImage());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //plansza 3 na 3
        JPanel panel = new JPanel(new GridLayout(3, 3));

        //utworzenie 9 przycisków
        buttons = new JButton[9];

        for (int i = 0; i < 9; i++)
        {
            buttons[i] = new JButton("");
            final int index = i;
            buttons[i].addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
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

    private void buttonClicked(int index) {
        out.println(index);
    }

    private void updateBoard(String board) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 9; i++) {
                    buttons[i].setText(String.valueOf(board.charAt(i)));
                    buttons[i].setForeground(Color.WHITE); // Ustawienie koloru tekstu na biały
                    buttons[i].setText(String.valueOf(board.charAt(i)));
                    buttons[i].setFont(new Font("Arial", Font.BOLD, 30)); // Ponowne ustawienie czcionki

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
