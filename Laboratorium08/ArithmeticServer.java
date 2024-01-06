package Laboratorium08;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ArithmeticServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Serwer nasłuchuje na porcie 12345...");

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)) {

                    // Odczytanie danych od klienta
                    String num1Str = reader.readLine();
                    String num2Str = reader.readLine();
                    String operator = reader.readLine();
                    String numberType = reader.readLine();

                    // Sprawdzenie poprawności danych
                    if (!isValidInput(num1Str) || !isValidInput(num2Str) || !isValidOperator(operator)
                            || !isValidNumberType(numberType)) {
                        System.err.println("Błędne dane wejściowe.");
                        writer.println("NaN");
                        continue;
                    }

                    double num1 = Double.parseDouble(num1Str);
                    double num2 = Double.parseDouble(num2Str);

                    // Wykonanie operacji arytmetycznej
                    double result = performOperation(num1, num2, operator, numberType);

                    // Wysłanie wyniku do klienta
                    writer.println(result);
                } catch (IOException | NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isValidInput(String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isValidOperator(String operator) {
        return operator.equals("add") || operator.equals("subtract") || operator.equals("multiply") || operator.equals("divide");
    }

    private static boolean isValidNumberType(String numberType) {
        return numberType.equals("integer") || numberType.equals("real");
    }

    private static double performOperation(double num1, double num2, String operator, String numberType) {
        if (numberType.equals("integer")) {
            switch (operator) {
                case "add":
                    return (int) num1 + (int) num2;
                case "subtract":
                    return (int) num1 - (int) num2;
                case "multiply":
                    return (int) num1 * (int) num2;
                case "divide":
                    if (num2 == 0) {
                        System.err.println("Dzielenie przez zero!");
                        return Double.NaN;
                    }
                    return (double) (int) num1 / (double) (int) num2;
                default:
                    System.err.println("Nieznane działanie!");
                    return Double.NaN;
            }
        } else {
            // Liczby rzeczywiste
            switch (operator) {
                case "add":
                    return num1 + num2;
                case "subtract":
                    return num1 - num2;
                case "multiply":
                    return num1 * num2;
                case "divide":
                    if (num2 == 0) {
                        System.err.println("Dzielenie przez zero!");
                        return Double.NaN;
                    }
                    return num1 / num2;
                default:
                    System.err.println("Nieznane działanie!");
                    return Double.NaN;
            }
        }
    }
}
