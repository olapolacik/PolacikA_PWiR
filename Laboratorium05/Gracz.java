class Gracz implements Runnable {

    private static boolean isGameOn = true;
    private static String winningPlayer = null;  // Variable to track the winning player
    private String nazwa;
    private int suma = 0;

    public Gracz(String nazwa) {
        this.nazwa = nazwa;
    }

    @Override
    public void run() {
        Talia talia = new Talia();
        while (isGameOn) {
            int karta = talia.losujKarte();
            System.out.println(nazwa + " dobiera kartę: " + karta);
            suma += karta;

            if (suma == 21) {
                System.out.println(nazwa + " osiągnął wynik 21! Wygrywa!");
                winningPlayer = nazwa;  // Set the winning player
                isGameOn = false;
            } else if (suma > 21) {
                System.out.println(nazwa + " przekroczył 21 punktów. Przegrywa.");
                isGameOn = false;
            }

            try {
                Thread.sleep(500); // Symulacja opóźnienia między dobieraniem kart
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (winningPlayer != null && winningPlayer.equals(nazwa)) {
            System.out.println(nazwa + " wygrał grę!");
        }
    }
}