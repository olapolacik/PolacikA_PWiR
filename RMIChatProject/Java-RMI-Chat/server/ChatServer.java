package server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.RemoteRef;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.Vector;
import client.ChatClient3IF;

/**
 * @author Aleksandra Połacik
 */

public class ChatServer extends UnicastRemoteObject implements ChatServerIF {
    String line = "---------------------------------------------\n";
    private Vector<ChatMember> chatters;
    private static final long serialVersionUID = 1L;

    // Konstruktor klasy
    public ChatServer() throws RemoteException {
        super();
        chatters = new Vector<ChatMember>(10, 1);
    }


    public static void main(String[] args) {
        startRMIRegistry();
        String hostName = "localhost";
        String serviceName = "chatGrupowy";

        if (args.length == 2) {
            hostName = args[0];
            serviceName = args[1];
        }

        try {
            ChatServerIF hello = new ChatServer();
            Naming.rebind("rmi://" + hostName + "/" + serviceName, hello);
            System.out.println("Serwer RMI uruchomiony...");
        } catch (Exception e) {
            System.out.println("Serwer ma problem z polaczeniem, sprobuj ponownie...");
        }
    }

    /**
     * Uruchom rejestr RMI
     */
    public static void startRMIRegistry() {
        try {
            java.rmi.registry.LocateRegistry.createRegistry(1099);
            System.out.println("Serwer RMI gotowy");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    /**
     * Zwróć wiadomość do klienta
     */
    public String sayHello(String ClientName) throws RemoteException {
        System.out.println(ClientName + " wyslal wiadomosc");
        return "Czesc " + ClientName;
    }


    
    /**
     * Wyślij ciąg znaków (najnowszy post, głównie) do wszystkich podłączonych
     * klientów
     */
    public void updateChat(String name, String nextPost) throws RemoteException {
        String message = name + " : " + nextPost + "\n";
        sendToAll(message);
    }


    /**
     * Odbierz nową referencję zdalną klienta
     */
    @Override
    public void passIDentity(RemoteRef ref) throws RemoteException {
        try {
            System.out.println(line + ref.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Odbierz nowego klienta i wyświetl szczegóły w konsoli, przekaż do metody
     * rejestracji
     */
    @Override
    public void registerListener(String[] details) throws RemoteException {
        System.out.println(new Date(System.currentTimeMillis()));
        System.out.println(details[0] + " dolaczyl do sesji chatu");
        System.out.println(details[0] + "'s hostname : " + details[1]);
        System.out.println(details[0] + "'s RMI serwer : " + details[2]);
        registerChatter(details);
    }

    /**
     * Zarejestruj interfejsy klientów i przechowaj je w referencji dla
     * przyszłych wiadomości, tj. wiadomości innych członków sesji czatu.
     * Wyślij testową wiadomość w celu potwierdzenia/testu połączenia.
     * 
     * @param details
     */
    private void registerChatter(String[] details) {
        try {
            ChatClient3IF nextClient = (ChatClient3IF) Naming.lookup("rmi://" + details[1] + "/" + details[2]);

            chatters.addElement(new ChatMember(details[0], nextClient));

            nextClient.messageFromServer("[Server] : Hej! " + details[0] + " możesz rozpocząć rozmowę.\n");

            sendToAll("[Server] : " + details[0] + " dołączył do grupy.\n");

            updateUserList();
        } catch (RemoteException | MalformedURLException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Zaktualizuj wszystkich klientów, zdalnie wywołując ich metodę
     * updateUserList RMI
     */
    private void updateUserList() {
        String[] currentUsers = getUserList();
        for (ChatMember c : chatters) {
            try {
                c.getClient().updateUserList(currentUsers);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Generuj tablicę ciągów znaków obecnych użytkowników
     * 
     * @return
     */
    private String[] getUserList() {
        // generuj tablicę obecnych użytkowników
        String[] allUsers = new String[chatters.size()];
        for (int i = 0; i < allUsers.length; i++) {
            allUsers[i] = chatters.elementAt(i).getName();
        }
        return allUsers;
    }

    /**
     * Wyślij wiadomość do wszystkich użytkowników
     * 
     * @param newMessage
     */
    public void sendToAll(String newMessage) {
        for (ChatMember c : chatters) {
            try {
                c.getClient().messageFromServer(newMessage);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Usuń klienta z listy, powiadom wszystkich
     */
    @Override
    public void leaveChat(String userName) throws RemoteException {

        for (ChatMember c : chatters) {
            if (c.getName().equals(userName)) {
                System.out.println(line + userName + " opuscil chat");
                System.out.println(new Date(System.currentTimeMillis()));
                chatters.remove(c);
                break;
            }
        }
        if (!chatters.isEmpty()) {
            updateUserList();
        }
    }

    /**
     * Metoda do wysyłania prywatnej wiadomości do wybranych klientów. Tablica
     * liczb całkowitych przechowuje indeksy (z wektora chatters) klientów, do
     * których ma zostać wysłana wiadomość.
     */
    @Override
    public void sendPM(int[] privateGroup, String privateMessage) throws RemoteException {
        ChatMember pc;
        for (int i : privateGroup) {
            pc = chatters.elementAt(i);
            pc.getClient().messageFromServer(privateMessage);
        }
    }
}
