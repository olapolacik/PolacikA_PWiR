package kolkokrzyzyk;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Client implements Runnable {
    private Socket clientSocket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private LogikaGryImpl logikaGry;
    private NewJFrame newJFrameInstance;
    private List<ObjectOutputStream> clients = Collections.synchronizedList(new ArrayList<>());


    private volatile boolean running = true;

    public Client(Socket clientSocket, ObjectOutputStream outputStream, List<ObjectOutputStream> clients) {
        this.clientSocket = clientSocket;
        this.outputStream = outputStream;
        this.clients = Collections.synchronizedList(clients);
    }


    
    
    @Override
public void run() {
    try {
        // Initialize GUI
        newJFrameInstance = new NewJFrame();
        logikaGry = (LogikaGryImpl) newJFrameInstance.getLogikaGry();

        while (running) {
            if (inputStream != null && !clientSocket.isClosed()) {
                Serializable receivedMessage = (Serializable) inputStream.readObject();

                if (receivedMessage instanceof Integer) {
                    int numerPrzycisku = (Integer) receivedMessage;
                    logikaGry.wykonajRuch(numerPrzycisku);
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
        }
    } catch (IOException | ClassNotFoundException e) {
        // Handle exceptions if needed
        if (running) {
            e.printStackTrace();
        }
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

    
    
    
    
    
    

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 3333);
             ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream())) {

            List<ObjectOutputStream> clients = Collections.synchronizedList(new ArrayList<>());
            clients.add(outputStream);

            Client client = new Client(socket, outputStream, clients);
            new Thread(client).start();
            
            outputStream.writeObject("Hello from client!");
            outputStream.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
}
