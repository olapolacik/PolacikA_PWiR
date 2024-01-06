package kolkokrzyzyk;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;

public class ClientHandler implements Runnable {
    private ObjectOutputStream outputStream;
    private List<ObjectOutputStream> clients;
    private ObjectInputStream inputStream;
    private Socket clientSocket; // Nowe pole do przechowywania gniazda klienta

    public ClientHandler(ObjectOutputStream outputStream, List<ObjectOutputStream> clients, ObjectInputStream inputStream, Socket clientSocket) {
        this.outputStream = outputStream;
        this.clients = clients;
        this.inputStream = inputStream;
        this.clientSocket = clientSocket;
    }
// ...

@Override
public void run() {
    try {
        // Initialize GUI for this client
        NewJFrame newJFrameInstance = new NewJFrame();
        LogikaGry logikaGry = newJFrameInstance.getLogikaGry();

        while (true) {
            Serializable receivedMessage = (Serializable) inputStream.readObject();

            if (receivedMessage instanceof Integer) {
                int numerPrzycisku = (Integer) receivedMessage;
                logikaGry.wykonajRuch(numerPrzycisku);
            } else if (receivedMessage instanceof String && "END".equals(receivedMessage)) {
                // Jeśli otrzymaliśmy sygnał zakończenia, przerwij pętlę
                break;
            }

            synchronized (clients) {
                Iterator<ObjectOutputStream> iterator = clients.iterator();
                while (iterator.hasNext()) {
                    ObjectOutputStream client = iterator.next();
                    try {
                        client.writeObject(logikaGry);
                        client.flush();
                    } catch (IOException e) {
                        // Handle the exception or remove the client if needed
                        e.printStackTrace();
                        iterator.remove();
                    }
                }
            }
        }
    } catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
    } finally {
        try {
            if (inputStream != null) {
                inputStream.close();
            }
            synchronized (clients) {
                clients.remove(outputStream);
            }
            if (clientSocket != null && !clientSocket.isClosed()) {
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

}
