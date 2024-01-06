import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class WindaWielowatkowa {
    public static void main(String[] args) {
        int liczbaOsob = 8;
        int liczbaPieter = 5;
        int liczbaWind = 3;

        List<Osoba> ludzie = new ArrayList<>();
        List<Winda> windy = new ArrayList<>();

        for (int i = 0; i < liczbaOsob; i++) {
            ludzie.add(new Osoba("Osoba " + i, liczbaPieter));
        }

        for (int i = 0; i < liczbaWind; i++) {
            windy.add(new Winda("Winda " + i, liczbaPieter));
        }

        for (Osoba osoba : ludzie) {
            new Thread(osoba).start();
        }

        for (Winda winda : windy) {
            new Thread(winda).start();
        }
    }
}

class Osoba implements Runnable {
    private String imie;
    private int aktualnePietro;
    private int liczbaPieter;

    public Osoba(String imie, int liczbaPieter) {
        this.imie = imie;
        this.liczbaPieter = liczbaPieter;
        this.aktualnePietro = new Random().nextInt(liczbaPieter);
    }

    @Override
    public void run() {
        try {
            while (true) {
                int docelowePietro = losujInnePietro(aktualnePietro, liczbaPieter);
                System.out.println(imie + " jest na piętrze " + aktualnePietro + " i chce przejść na piętro " + docelowePietro);
                synchronized (Winda.class) {
                    Winda.zglosWinda(this, docelowePietro);
                }
                Thread.sleep(2000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private int losujInnePietro(int aktualnePietro, int liczbaPieter) {
        int docelowePietro = aktualnePietro;
        while (docelowePietro == aktualnePietro) {
            docelowePietro = new Random().nextInt(liczbaPieter);
        }
        return docelowePietro;
    }

    public int getAktualnePietro() {
        return aktualnePietro;
    }

    public void setAktualnePietro(int aktualnePietro) {
        this.aktualnePietro = aktualnePietro;
    }

    @Override
    public String toString() {
        return imie;
    }
}

class Winda implements Runnable {
    private String nazwa;
    private int aktualnePietro;
    private int liczbaPieter;
    private static List<Osoba> osobyWewnatrz = new ArrayList<>();
    private static List<Integer> zgloszenia = new ArrayList<>();

    public Winda(String nazwa, int liczbaPieter) {
        this.nazwa = nazwa;
        this.liczbaPieter = liczbaPieter;
        this.aktualnePietro = 0;
    }

    @Override
    public void run() {
        try {
            while (true) {
                synchronized (Winda.class) {
                    if (!zgloszenia.isEmpty()) {
                        int docelowePietro = zgloszenia.remove(0);
                        if (aktualnePietro != docelowePietro) {
                            System.out.println(nazwa + " porusza się z piętra " + aktualnePietro + " na piętro " + docelowePietro);
                            Thread.sleep(Math.abs(aktualnePietro - docelowePietro) * 1000);
                            aktualnePietro = docelowePietro;
                            System.out.println(nazwa + " dotarła na piętro " + aktualnePietro);
                            wydzielOsoby();
                        }
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static synchronized void zglosWinda(Osoba osoba, int docelowePietro) {
        osobyWewnatrz.add(osoba);
        zgloszenia.add(docelowePietro);
    }

    private void wydzielOsoby() {
        List<Osoba> osobyDoWydzielenia = new ArrayList<>();
        for (Osoba osoba : osobyWewnatrz) {
            if (osoba.getAktualnePietro() == aktualnePietro) {
                osobyDoWydzielenia.add(osoba);
            }
        }

        for (Osoba osoba : osobyDoWydzielenia) {
            osobyWewnatrz.remove(osoba);
            osoba.setAktualnePietro(aktualnePietro);
            System.out.println(osoba + " wyszła z windy na piętrze " + aktualnePietro);
        }
    }
}
