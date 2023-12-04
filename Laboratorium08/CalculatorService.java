import java.rmi.Remote;
import java.rmi.RemoteException;

//Interfejs zdalny CalculatorService

public interface CalculatorService extends Remote {
    double calculate(double operand1, double operand2, String operator) throws RemoteException;
}
