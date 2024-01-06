package Laboratorium01;

import java.util.Timer;
import java.util.TimerTask;

public class Zegar {
    private int godzina;
    private int minuta;
    private int sekunda;
    private boolean format24h; //true - 24h, false 12h

    public Zegar(){
        this.godzina = 0;
        this.minuta = 0;
        this.sekunda = 0;
        this.format24h = true;
    }

    public void nastaw(int godzina, int minuta, int sekunda){
        if(godzina >= 0 && godzina < 24 && minuta >= 0 && minuta < 60 && sekunda >= 0 && sekunda < 60){
            this.godzina = godzina;
            this.minuta = minuta;
            this.sekunda = sekunda;
        }
        else{
            System.out.println("Niepoprawne wartosci, godziny, minuty, sekundy.");
        }
    }

    public void wypisz(){
        if(format24h){
            System.out.printf("%02d:%02d:%02d%n", godzina, minuta, sekunda);
        }
        else{
            String amPm = (godzina < 12) ? "AM" : "PM";
            int hour12 = (godzina == 0 || godzina == 12) ? 12 : godzina % 12;
            System.out.printf("%02d:%02d:%02d %s%n", hour12, minuta, sekunda, amPm);
        }
    }

    public void format(boolean format24h) {
        this.format24h = format24h;
    }


    public void tykniecie() {
        sekunda++;
        if (sekunda == 60) {
            sekunda = 0;
            minuta++;
            if (minuta == 60) {
                minuta = 0;
                godzina++;
                if (godzina == 24) {
                    godzina = 0;
                }
            }
        }
    }


    public void uruchom5sec() {
        Timer timer = new Timer();
        int seconds = 5;
        timer.scheduleAtFixedRate(new TimerTask() {
            int count = 0;

            @Override
            public void run() {
                tykniecie();
                wypisz();
                count++;
                if (count == seconds) {
                    timer.cancel();
                }
            }
        }, 0, 1000);
    }

    public static void main(String[] args) {
        Zegar zegar = new Zegar();
        System.out.println("---------------ZEGAR-------------------------");
        zegar.nastaw(12, 30, 0);
       // zegar.wypisz();
        zegar.format(true);
        //zegar.tykniecie();
        zegar.wypisz();
        zegar.uruchom5sec();
    }
}
