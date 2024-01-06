package TicTacToe;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    private char[] board = new char[9];
    private PrintWriter[] clients;
    private int currentPlayerIndex = 0;

    public Server() {
        for (int i = 0; i < 9; i++) {
            board[i] = ' ';
        }

        clients = new PrintWriter[2];

        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Server is running...");

            // Wątek monitorujący zwycięzcę
            Thread winnerMonitorThread = new Thread(new WinnerMonitor());
            winnerMonitorThread.start();

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected");

                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                Scanner in = new Scanner(socket.getInputStream());

                int playerIndex = findEmptySlot();
                clients[playerIndex] = out;

                // Informujemy klienta o jego symbolu ('X' lub 'O') i aktualnym graczu
                out.println("SYMBOL:" + (playerIndex == 0 ? 'X' : 'O'));
                out.println("CURRENT_PLAYER:" + (currentPlayerIndex == playerIndex));

                Thread playerThread = new Thread(new PlayerHandler(playerIndex, in));
                playerThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int findEmptySlot() {
        for (int i = 0; i < clients.length; i++) {
            if (clients[i] == null) {
                return i;
            }
        }
        return -1;
    }

    private synchronized void updateBoard(int index, char symbol) {
        if (board[index] == ' ') {
            board[index] = symbol;

            for (PrintWriter client : clients) {
                if (client != null) {
                    client.println("BOARD:" + new String(board));
                    client.println("CURRENT_PLAYER:" + (currentPlayerIndex == 0));
                }
            }

            if (checkForWin(symbol)) {
                for (PrintWriter client : clients) {
                    if (client != null) {
                        client.println("WINNER:" + symbol);
                    }
                }
            } else if (isBoardFull()) {
                for (PrintWriter client : clients) {
                    if (client != null) {
                        client.println("DRAW");
                    }
                }
                resetGame();
            } else {
                currentPlayerIndex = (currentPlayerIndex + 1) % 2;
            }
        }
    }

    private boolean checkForWin(char symbol) {
        // Sprawdzanie wierszy, kolumn i przekątnych
        for (int i = 0; i < 3; i++) {
            if ((board[i] == symbol && board[i + 3] == symbol && board[i + 6] == symbol) ||
                    (board[i * 3] == symbol && board[i * 3 + 1] == symbol && board[i * 3 + 2] == symbol)) {
                return true;
            }
        }

        // Sprawdzanie przekątnych
        if ((board[0] == symbol && board[4] == symbol && board[8] == symbol) ||
                (board[2] == symbol && board[4] == symbol && board[6] == symbol)) {
            return true;
        }

        return false;
    }

    private boolean isBoardFull() {
        for (char cell : board) {
            if (cell == ' ') {
                return false;
            }
        }
        return true;
    }

    private void resetGame() {
        for (int i = 0; i < 9; i++) {
            board[i] = ' ';
        }

        for (int i = 0; i < clients.length; i++) {
            if (clients[i] != null) {
                clients[i].println("RESET");
            }
        }

        currentPlayerIndex = 0;
    }

    private class PlayerHandler implements Runnable {
        private int playerIndex;
        private Scanner in;

        public PlayerHandler(int playerIndex, Scanner in) {
            this.playerIndex = playerIndex;
            this.in = in;
        }

        @Override
        public void run() {
            try {
                char symbol = (playerIndex == 0) ? 'X' : 'O';

                while (true) {
                    if (in.hasNextLine()) {
                        int move = Integer.parseInt(in.nextLine());
                        updateBoard(move, symbol);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private class WinnerMonitor implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    checkForWinnerAndPrint();
                    Thread.sleep(1000); // Czekaj 1 sekundę przed ponownym sprawdzeniem
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void checkForWinnerAndPrint() {
            char winner = checkForWinner();
            if (winner != ' ') {
                System.out.println("Gracz " + winner + " wygrał!");
                resetGame();
            }
        }

        private char checkForWinner() {
            for (int i = 0; i < 3; i++) {
                if ((board[i] != ' ' && board[i] == board[i + 3] && board[i + 3] == board[i + 6]) ||
                        (board[i * 3] != ' ' && board[i * 3] == board[i * 3 + 1] && board[i * 3 + 1] == board[i * 3 + 2])) {
                    return board[i];
                }
            }

            if ((board[0] != ' ' && board[0] == board[4] && board[4] == board[8]) ||
                    (board[2] != ' ' && board[2] == board[4] && board[4] == board[6])) {
                return board[4];
            }

            return ' ';
        }
    }

    public static void main(String[] args) {
        new Server();
    }
}