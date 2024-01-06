package Laboratorium01;

import java.util.Scanner;

public class Zadanie1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Podaj ilość sekund: ");

        // Czas w sekundach podany przez użytkownika
        int totalTimeInSeconds = sc.nextInt();

        // Tworzenie i uruchamianie wątku minutnika
        TimerThread1 timer = new TimerThread1("Minutnik", totalTimeInSeconds);
        timer.start();
    }
}

class TimerThread1 extends Thread {
    private String name;
    private int totalTimeInSeconds;

    public TimerThread1(String name, int totalTimeInSeconds) {
        this.name = name;
        this.totalTimeInSeconds = totalTimeInSeconds;
    }

    @Override
    public void run() {
        System.out.println(name + " rozpoczyna odliczanie...");

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
