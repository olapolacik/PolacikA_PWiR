import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMIServer {
    public static void main(String[] args) {
        try {
            // Utwórz zdalny obiekt
            CalculatorService calculatorService = new CalculatorServiceImpl();

            // Sprawdź, czy obiekt już został wyeksportowany
            if (!(calculatorService instanceof UnicastRemoteObject)) {
                // Wyeksportuj zdalny obiekt
                CalculatorService stub = (CalculatorService) UnicastRemoteObject.exportObject(calculatorService, 0);

                // Zarejestruj zdalny obiekt w rejestrze RMI na porcie 1919
                Registry registry = LocateRegistry.createRegistry(1919);
                registry.rebind("CalculatorService", stub);

                System.out.println("Serwer RMI gotowy...");
            } else {
                System.out.println("Obiekt już został wyeksportowany.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
