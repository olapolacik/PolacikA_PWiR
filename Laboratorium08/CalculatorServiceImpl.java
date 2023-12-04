import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

// Implementacja interfejsu i obsługa kalkulatora
public class CalculatorServiceImpl extends UnicastRemoteObject implements CalculatorService {
    public CalculatorServiceImpl() throws RemoteException {
        super();
    }

    public double calculate(double operand1, double operand2, String operator) throws RemoteException {
        double result = 0;
        try {
            switch (operator) {
                case "+":
                    result = operand1 + operand2;
                    break;
                case "-":
                    result = operand1 - operand2;
                    break;
                case "*":
                    result = operand1 * operand2;
                    break;
                case "/":
                    if (operand2 == 0) {
                        throw new ArithmeticException("Nie dziel przez zero!");
                    }
                    result = operand1 / operand2;
                    break;
                default:
                    System.err.println("Błąd: Nieobsługiwany operator");
                    break;
            }
        } catch (ArithmeticException e) {
            System.err.println("Błąd: " + e.getMessage());
        }

        return result;
    }
}
