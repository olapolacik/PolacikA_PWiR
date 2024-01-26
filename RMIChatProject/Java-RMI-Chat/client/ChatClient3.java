package client;
import java.net.MalformedURLException;
import java.rmi.ConnectException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.JOptionPane;

import server.ChatServerIF;

/**
 * @author Aleksandra Połacik
 * 
 * Klasa reprezentująca klienta czatu z implementacją RMI.
 */
public class ChatClient3  extends UnicastRemoteObject implements ChatClient3IF {
    private static final long serialVersionUID = 7468891722773409712L;
    ClientRMIGUI chatGUI;
    private String hostName = "localhost";
    private String serviceName = "chatGrupowy";
    private String clientServiceName;
    private String name;
    protected ChatServerIF serverIF;
    protected boolean connectionProblem = false;

    /**
     * Konstruktor klasy.
     * @param aChatGUI Obiekt interfejsu użytkownika.
     * @param userName Nazwa użytkownika.
     * @throws RemoteException
     */
    public ChatClient3(ClientRMIGUI aChatGUI, String userName) throws RemoteException {
        super();
        this.chatGUI = aChatGUI;
        this.name = userName;
        this.clientServiceName = "ClientListenService_" + userName;
    }

    /**
     * Rozpoczyna działanie klienta, rejestruje nasłuchującą usługę i nawiązuje połączenie z serwerem.
     * @throws RemoteException
     */
    public void startClient() throws RemoteException {        
        String[] details = {name, hostName, clientServiceName};    

        try {
            Naming.rebind("rmi://" + hostName + "/" + clientServiceName, this);
            serverIF = (ChatServerIF) Naming.lookup("rmi://" + hostName + "/" + serviceName);    
        } 
        catch (ConnectException  e) {
            JOptionPane.showMessageDialog(
                    chatGUI.frame, "Serwer jest niedostępny\nProszę spróbować później",
                    "Problem z połączeniem", JOptionPane.ERROR_MESSAGE);
            connectionProblem = true;
            e.printStackTrace();
        }
        catch (NotBoundException | MalformedURLException me) {
            connectionProblem = true;
            me.printStackTrace();
        }
        if (!connectionProblem) {
            registerWithServer(details);
        }    
        System.out.println("Serwer nasłuchujący klienta RMI jest uruchomiony...\n");
    }

    /**
     * Rejestruje klienta na serwerze.
     * @param details Szczegóły rejestracji (nazwa, host, nazwa usługi klienta).
     */
    public void registerWithServer(String[] details) {        
        try {
            serverIF.passIDentity(this.ref); 
            serverIF.registerListener(details);            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Odbiera wiadomość od serwera czatu.
     * Jest to metoda RMI klienta, którą serwer używa do wysyłania wiadomości do klienta.
     */
    @Override
    public void messageFromServer(String message) throws RemoteException {
        System.out.println(message);
        chatGUI.textArea.append(message);
        // Ustawia pozycję kursora GUI na koniec ostatnio dodanego tekstu, tj. przewija do dołu
        chatGUI.textArea.setCaretPosition(chatGUI.textArea.getDocument().getLength());
    }

    /**
     * Metoda do aktualizacji wyświetlania użytkowników aktualnie podłączonych do serwera.
     */
    @Override
    public void updateUserList(String[] currentUsers) throws RemoteException {
        if (currentUsers.length < 2) {
            chatGUI.privateMsgButton.setEnabled(false);
        }
        chatGUI.userPanel.remove(chatGUI.clientPanel);
        chatGUI.setClientPanel(currentUsers);
        chatGUI.clientPanel.repaint();
        chatGUI.clientPanel.revalidate();
    }
}
