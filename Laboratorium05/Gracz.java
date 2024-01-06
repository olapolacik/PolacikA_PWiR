class Gracz extends Thread {

    private static boolean koniecGry = false;
    private static Object lock = new Object();

    private String nazwaGracza;
    private int sumaPunktow = 0;

    public Gracz(String nazwaGracza) {
        this.nazwaGracza = nazwaGracza;
    }

    @Override
    public void run() {
        while (!koniecGry) {
            synchronized (lock) {
                if (!koniecGry) {
                    int karta = Talia.pobierzKarte();
                    if (karta != -1) {
                        System.out.println(nazwaGracza + " dobiera kartę o wartości: " + karta);
                        sumaPunktow += karta;

                        if (sumaPunktow == 21) {
                            System.out.println(nazwaGracza + " osiągnął/a 21 punktów! Wygrywa!");
                            koniecGry = true;
                        } else if (sumaPunktow > 21) {
                            System.out.println(nazwaGracza + " przekroczył/a 21 punktów. Odpada z gry.");
                            koniecGry = true;
                        }
                    } else {
                        System.out.println("Talia jest pusta. Koniec gry.");
                        koniecGry = true;
                    }
                }
            }

            try {
                Thread.sleep(1000); // Czekamy przed dobieraniem kolejnej karty
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        int liczbaGraczy = 5;

        for (int i = 1; i <= liczbaGraczy; i++) {
            new Gracz("Gracz " + i).start();
        }
    }
}