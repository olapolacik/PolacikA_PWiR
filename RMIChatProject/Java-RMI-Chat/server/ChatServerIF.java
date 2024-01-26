package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.RemoteRef;

/**
 * @author Aleksandra Połacik
 */

public interface ChatServerIF extends Remote {
		
    /**
     * Metoda do aktualizacji czatu, przekazuje wiadomość do wszystkich klientów
     * 
     * @param userName     Nazwa użytkownika, który wysłał wiadomość
     * @param chatMessage  Treść wiadomości
     * @throws RemoteException
     */
    public void updateChat(String userName, String chatMessage) throws RemoteException;

    /**
     * Metoda do przekazania identyfikatora zdalnego klienta
     * 
     * @param ref Referencja zdalna klienta
     * @throws RemoteException
     */
    public void passIDentity(RemoteRef ref) throws RemoteException;

    /**
     * Metoda do rejestracji nowego klienta w czacie
     * 
     * @param details Tablica zawierająca szczegóły nowego klienta (nazwa, hostname, RMI service name)
     * @throws RemoteException
     */
    public void registerListener(String[] details) throws RemoteException;

    /**
     * Metoda do opuszczenia czatu przez użytkownika
     * 
     * @param userName Nazwa użytkownika, który opuszcza czat
     * @throws RemoteException
     */
    public void leaveChat(String userName) throws RemoteException;

    /**
     * Metoda do wysyłania prywatnych wiadomości do wybranych klientów
     * 
     * @param privateGroup   Indeksy (z wektora chatters) klientów, do których ma być wysłana wiadomość
     * @param privateMessage Treść prywatnej wiadomości
     * @throws RemoteException
     */
    public void sendPM(int[] privateGroup, String privateMessage) throws RemoteException;
}
