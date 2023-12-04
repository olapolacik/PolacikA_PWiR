import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

// Klient RMI
public class RMIClient {
    public static void main(String[] args) {
        try {
            // Znajdź rejestr RMI na porcie 1919
            
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1919);


            // Pobierz referencję do zdalnego obiektu z rejestru
            CalculatorService calculatorService = (CalculatorService) registry.lookup("CalculatorService");

            // Wprowadź dane dla kalkulatora
            double operand1 = 2.0;
            double operand2 = 2.0;
            String operator = "+";

            // Wywołaj zdalną metodę
            double result = calculatorService.calculate(operand1, operand2, operator);

            System.out.println("Wynik: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
