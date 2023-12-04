import java.util.Random;

public class Zadanie2 {
    public static void main(String[] args) {
        Random random = new Random();
        NumberHolder numberHolder = new NumberHolder();

        Thread generatorThread = new Thread(() -> {
            while (true) {
                int digit = random.nextInt(10); // Generowanie losowej cyfry (0-9)
                System.out.println("Generated digit: " + digit);
                numberHolder.addDigit(digit);

                if (digit == 0) {
                    // Oczekiwanie na podzielenie liczby przez 10
                    while (!numberHolder.isReadyForDivision()) {
                        try {
                            Thread.sleep(1000); // Opóźnienie w sprawdzaniu
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            }
        });

        Thread assemblerThread = new Thread(() -> {
            StringBuilder currentNumber = new StringBuilder();

            while (true) {
                int digit = numberHolder.getNextDigit();
                System.out.println("Received digit: " + digit);

                if (digit == 0) {
                    numberHolder.setReadyForDivision(true); // Oznaczamy gotowość do podzielenia liczby przez 10
                    if (currentNumber.length() > 0) {
                        System.out.println("Generated number: " + currentNumber.toString());
                        currentNumber.setLength(0); // Resetowanie stanu
                    }
                } else {
                    currentNumber.append(digit);
                }
            }
        });

        generatorThread.start();
        assemblerThread.start();
    }
}

class NumberHolder {
    private int digit;
    private boolean hasNewDigit = false;
    private boolean readyForDivision = false;

    public synchronized void addDigit(int digit) {
        this.digit = digit;
        this.hasNewDigit = true;
        notify();
    }

    public synchronized int getNextDigit() {
        while (!hasNewDigit) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        hasNewDigit = false;
        return digit;
    }

    public synchronized boolean isReadyForDivision() {
        return readyForDivision;
    }

    public synchronized void setReadyForDivision(boolean ready) {
        this.readyForDivision = ready;
    }
}
