package server;

import client.ChatClient3IF;

/**
 * Klasa reprezentująca użytkownika (członka czatu).
 */
public class ChatMember {

    /** Nazwa użytkownika */
    public String name;

    /** Referencja do interfejsu zdalnego klienta */
    public ChatClient3IF client;

    /**
     * Konstruktor klasy Chatter.
     *
     * @param name   Nazwa użytkownika
     * @param client Referencja do interfejsu zdalnego klienta
     */
    public ChatMember(String name, ChatClient3IF client) {
        this.name = name;
        this.client = client;
    }

    /**
     * Metoda zwracająca nazwę użytkownika.
     *
     * @return Nazwa użytkownika
     */
    public String getName() {
        return name;
    }

    /**
     * Metoda zwracająca referencję do interfejsu zdalnego klienta.
     *
     * @return Referencja do interfejsu zdalnego klienta
     */
    public ChatClient3IF getClient() {
        return client;
    }
}
