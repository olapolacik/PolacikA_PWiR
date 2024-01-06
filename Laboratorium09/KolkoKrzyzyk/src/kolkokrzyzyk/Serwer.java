package kolkokrzyzyk;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Serwer {
    public static final int PORT = 3333;

    private List<ObjectOutputStream> clients;

    public Serwer() {
        clients = Collections.synchronizedList(new ArrayList<>());
    }

    public void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is running on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected");

                ObjectOutputStream outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
                clients.add(outputStream);

                ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream());
                Thread clientHandler = new Thread(new ClientHandler(outputStream, clients, inputStream, clientSocket));

                clientHandler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Serwer().startServer();
    }
}
