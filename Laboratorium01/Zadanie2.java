package Laboratorium01;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Zadanie2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        try {
            System.out.println("Podaj ilość sekund (liczba całkowita większa niż 0): ");

            // Czas w sekundach podany przez użytkownika
            int totalTimeInSeconds = sc.nextInt();

            if (totalTimeInSeconds <= 0) {
                System.out.println("Podaj liczbę całkowitą większą niż 0.");
                return;
            }

            // Tworzenie i uruchamianie wątków minutników
            TimerThread realTimeTimer = new TimerThread("Minutnik w czasie rzeczywistym", totalTimeInSeconds);
            TimerThread delayedTimer = new TimerThread("Opóźniony minutnik", totalTimeInSeconds, 2000);

            realTimeTimer.start();
            delayedTimer.start();
        } catch (InputMismatchException e) {
            System.out.println("Nieprawidłowy format danych. Podaj liczbę całkowitą.");
        } finally {
            sc.close(); // Ważne, aby zamknąć Scanner
        }
    }
}

class TimerThread extends Thread {
    private String name;
    private int totalTimeInSeconds;
    private int delay;

    public TimerThread(String name, int totalTimeInSeconds) {
        this.name = name;
        this.totalTimeInSeconds = totalTimeInSeconds;
        this.delay = 0;
    }

    public TimerThread(String name, int totalTimeInSeconds, int delay) {
        this.name = name;
        this.totalTimeInSeconds = totalTimeInSeconds;
        this.delay = delay;
    }

    @Override
    public void run() {
        System.out.println(name + " rozpoczyna odliczanie...");

        try {
            Thread.sleep(delay); // Opcjonalne opóźnienie
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int remainingTime = totalTimeInSeconds;
        while (remainingTime > 0) {
            System.out.println(name + ": " + remainingTime + " sek.");
            remainingTime--;

            try {
                Thread.sleep(1000); // Czekaj 1 sekundę
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(name + " zakończył odliczanie.");
    }
}
