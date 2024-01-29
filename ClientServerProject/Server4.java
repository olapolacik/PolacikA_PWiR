package ClientServerProject;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server4 {

    private char[] board = new char[42];
    private PrintWriter[] clients;
    private int currentPlayerIndex = 0;

    private boolean[] playerTurn = {true, false};

    // Konstruktor serwera
    public Server4() {
        for (int i = 0; i < 42; i++) {
            board[i] = ' ';
        }

        // Inicjalizacja dwóch klientów
        clients = new PrintWriter[2];

        try {
            ServerSocket serverSocket = new ServerSocket(1919);
            System.out.println("Serwer działa...");

            // Wątek monitorujący zwycięzcę
            Thread winnerMonitorThread = new Thread(new WinnerMonitor());
            winnerMonitorThread.start();

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Klient połączony");

                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                Scanner in = new Scanner(socket.getInputStream());

                int playerIndex = findEmptySlot();
                clients[playerIndex] = out;

                // Informacja dla klienta o jego symbolu ('X' lub 'O') i aktualnym graczu
                out.println("SYMBOL:" + (playerIndex == 0 ? 'X' : 'O'));
                out.println("CURRENT_PLAYER:" + (currentPlayerIndex == playerIndex));

                Thread playerThread = new Thread(new PlayerHandler(playerIndex, in));
                playerThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Metoda znajdująca wolny slot dla klienta
    private int findEmptySlot() {
        for (int i = 0; i < clients.length; i++) {
            if (clients[i] == null) {
                return i;
            }
        }
        return -1;
    }

    // Metoda aktualizująca planszę gry
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

    // Metoda sprawdzająca warunki wygranej
    private boolean checkForWin(char symbol) {
        // Sprawdzenie wierszy
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i * 7 + j] == symbol &&
                        board[i * 7 + j + 1] == symbol &&
                        board[i * 7 + j + 2] == symbol &&
                        board[i * 7 + j + 3] == symbol) {
                    return true;
                }
            }
        }

        // Sprawdzenie kolumn
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[j * 7 + i] == symbol &&
                        board[(j + 1) * 7 + i] == symbol &&
                        board[(j + 2) * 7 + i] == symbol &&
                        board[(j + 3) * 7 + i] == symbol) {
                    return true;
                }
            }
        }

        // Sprawdzenie przekątnych "/"
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i * 7 + j] == symbol &&
                        board[(i + 1) * 7 + j + 1] == symbol &&
                        board[(i + 2) * 7 + j + 2] == symbol &&
                        board[(i + 3) * 7 + j + 3] == symbol) {
                    return true;
                }
            }
        }

        // Sprawdzenie przekątnych "\"
        for (int i = 0; i < 3; i++) {
            for (int j = 3; j < 7; j++) {
                if (board[i * 7 + j] == symbol &&
                        board[(i + 1) * 7 + j - 1] == symbol &&
                        board[(i + 2) * 7 + j - 2] == symbol &&
                        board[(i + 3) * 7 + j - 3] == symbol) {
                    return true;
                }
            }
        }

        return false;
    }

    // Metoda sprawdzająca, czy plansza jest pełna
    private boolean isBoardFull() {
        for (char cell : board) {
            if (cell == ' ') {
                return false;
            }
        }
        return true;
    }

    // Metoda resetująca grę
    private void resetGame() {
        for (int i = 0; i < 42; i++) {
            board[i] = ' ';
        }

        for (int i = 0; i < clients.length; i++) {
            if (clients[i] != null) {
                clients[i].println("RESET");
            }
        }

        currentPlayerIndex = 0;
    }

    // Wewnętrzna klasa obsługująca pojedynczego gracza
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
                        String message = in.nextLine();

                        if ("RESET".equals(message)) {
                            resetGame();
                        } else {
                            int move = Integer.parseInt(message);

                            // Warunek sprawdzający, czy ruch jest dozwolony dla danego gracza
                            if (playerTurn[playerIndex]) {
                                updateBoard(move, symbol);
                                // Zablokuj możliwość ruchu obecnego gracza
                                playerTurn[playerIndex] = false;

                                // Przekaż ruch drugiemu graczowi
                                playerTurn[(playerIndex + 1) % 2] = true;
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Wewnętrzna klasa monitorująca zwycięzcę
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

        // Metoda sprawdzająca zwycięzcę i wypisująca informacje
        private void checkForWinnerAndPrint() {
            char winner = checkForWinner();
            if (winner != ' ') {
                System.out.println("Gracz " + winner + " wygrał!");
                resetGame();
            }
        }

        // Metoda sprawdzająca, czy jest zwycięzca
        private char checkForWinner() {
            for (int i = 0; i < 3; i++) {
                if ((board[i] != ' ' && board[i] == board[i + 4] && board[i + 4] == board[i + 8]) ||
                        (board[i * 3] != ' ' && board[i * 3] == board[i * 3 + 1] && board[i * 3 + 1] == board[i * 3 + 2])) {
                    return board[i];
                }
            }

            if ((board[0] != ' ' && board[0] == board[5] && board[5] == board[10]) ||
                    (board[2] != ' ' && board[2] == board[5] && board[5] == board[8])) {
                return board[5];
            }

            return ' ';
        }
    }

    // Metoda uruchamiająca serwer
    public static void main(String[] args) {
        new Server4();
    }
}
