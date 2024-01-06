package Kolokwium15_12_2023;


import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Aleksandra Połacik
 */

class Zad3 extends Thread
{
    private int delay;

    private static ReentrantLock lock = new ReentrantLock();

    public Zad3(int delay)
    {
        this.delay = delay;
    }

    @Override
    public void run() 
    {
        try 
        {
            while (true) 
            {
                //blokuje dostep do wypisywania cyfr
                lock.lock(); 

                for (int i = 0; i < 10; i++) 
                {
                    System.out.print(i + " ");
                    Thread.sleep(delay * 100);
                }

                System.out.println(); 

                //unlock odblokowuje dostep do wypisywania cyfr
                lock.unlock(); 

                //uspienie watku na 1 sekunde
                Thread.sleep(1000); 


                //blokuje dostep do wypisywania cyfr
                lock.lock(); 

                for (int i = 9; i >= 0; i--) 
                {
                    System.out.print(i + " ");
                    Thread.sleep(delay * 100);
                }

                System.out.println(); 

                //unlock odblokowuje dostep do wypisywania cyfr
                lock.unlock(); 

                //ponowne odczekanie 1 sekundy
                Thread.sleep(1000); 
            }
        } 
        catch (InterruptedException e) 
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) 
    {
        WatekLiczb watek = new WatekLiczb(1);

        watek.start();
    }
}
