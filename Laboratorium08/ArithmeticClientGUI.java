package Laboratorium08;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;




public class ArithmeticClientGUI {

    private JFrame frame;
    private JTextField num1TextField;
    private JTextField num2TextField;
    private JTextField resultTextField;
    private JComboBox<String> operatorComboBox;
    private JComboBox<String> numberTypeComboBox;

    public ArithmeticClientGUI() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Calculator");

        frame.setIconImage(new ImageIcon("Laboratorium08\\iconCalc.png").getImage()); 


        frame.setBounds(100, 100, 400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        

        JLabel lblNum1 = new JLabel("Liczba 1:");
        lblNum1.setBounds(10, 20, 80, 20);
        frame.getContentPane().add(lblNum1);

        num1TextField = new JTextField();
        num1TextField.setBounds(100, 20, 120, 20);
        frame.getContentPane().add(num1TextField);
        num1TextField.setColumns(10);

        JLabel lblNum2 = new JLabel("Liczba 2:");
        lblNum2.setBounds(10, 60, 80, 20);
        frame.getContentPane().add(lblNum2);

        num2TextField = new JTextField();
        num2TextField.setBounds(100, 60, 120, 20);
        frame.getContentPane().add(num2TextField);
        num2TextField.setColumns(10);

        JLabel lblOperator = new JLabel("Operator:");
        lblOperator.setBounds(10, 100, 80, 20);
        frame.getContentPane().add(lblOperator);

        operatorComboBox = new JComboBox<>(new String[]{"add", "subtract", "multiply", "divide"});
        operatorComboBox.setBounds(100, 100, 120, 20);
        frame.getContentPane().add(operatorComboBox);

        JLabel lblNumberType = new JLabel("Typ liczby:");
        lblNumberType.setBounds(10, 140, 80, 20);
        frame.getContentPane().add(lblNumberType);

        numberTypeComboBox = new JComboBox<>(new String[]{"integer", "real"});
        numberTypeComboBox.setBounds(100, 140, 120, 20);
        frame.getContentPane().add(numberTypeComboBox);

        JButton btnCalculate = new JButton("Oblicz");
        btnCalculate.setBounds(10, 180, 100, 30);
        btnCalculate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateButtonClicked();
            }
        });
        frame.getContentPane().add(btnCalculate);

        resultTextField = new JTextField();
        resultTextField.setEditable(false);
        resultTextField.setBounds(120, 185, 100, 20);
        frame.getContentPane().add(resultTextField);
        resultTextField.setColumns(10);
    }

    private void calculateButtonClicked() {
        try (Socket socket = new Socket("localhost", 12345);
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            // Pobieranie danych z interfejsu graficznego
            String num1 = num1TextField.getText();
            String num2 = num2TextField.getText();
            String operator = (String) operatorComboBox.getSelectedItem();
            String numberType = (String) numberTypeComboBox.getSelectedItem();

            // Wysyłanie danych do serwera
            writer.println(num1);
            writer.println(num2);
            writer.println(operator);
            writer.println(numberType);

            // Odbiór wyniku od serwera
            String resultStr = serverReader.readLine();
            double result;

            // Sprawdzenie, czy wynik jest liczbą
            try {
                result = Double.parseDouble(resultStr);
            } catch (NumberFormatException ex) {
                resultTextField.setText("Błąd");
                return;
            }

            resultTextField.setText(String.valueOf(result));

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ArithmeticClientGUI window = new ArithmeticClientGUI();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
