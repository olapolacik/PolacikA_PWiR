import java.util.Scanner;

public class Minutnik {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Podaj ilosc sekund: ");

        // czas w sekundach podany przez uzytkownika
        int totalTimeInSeconds = sc.nextInt(); 
        
        // Tworzenie i uruchamianie wątków minutników
        TimerThread realTimeTimer = new TimerThread("Minutnik w czasie rzeczywistym", totalTimeInSeconds);
        TimerThread delayedTimer = new TimerThread("Minutnik z opóźnieniem", totalTimeInSeconds, 2000);

        realTimeTimer.start();
        delayedTimer.start();
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
        int remainingTime = totalTimeInSeconds;

        try {
            Thread.sleep(delay); // Opcjonalne opóźnienie
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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
