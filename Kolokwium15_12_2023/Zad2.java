package Kolokwium15_12_2023;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author Aleksandra Połacik
*/


class GeneratorLosowychLiczb extends Thread 
{

    private final BlockingQueue<Integer> queue;
    private final Random random = new Random();

    public GeneratorLosowychLiczb(BlockingQueue<Integer> queue) 
    {
        this.queue = queue;
    }

    @Override
    public void run() 
    {
        try 
        {
            while (true) 
            {
                int kat = random.nextInt(361); //[0-360]
                queue.put(kat);
                System.out.println("Wygenerowano kąt -> " + kat);

                Thread.sleep(1000); 
            }
        } 
        catch (InterruptedException e) 
        {
            e.printStackTrace();
        }
    }
}

class Sprawdz extends Thread 
{
    private final BlockingQueue<Integer> queue;


    public Sprawdz(BlockingQueue<Integer> queue) 
    {
        this.queue = queue;
    }


    @Override
    public void run() 
    {
        try 
        {
            while (true) 
            {
                //pobranie 2 katow z kolejki
                int kat1 = queue.take();
                int kat2 = queue.take();

                //wyliczenie wartosci 3 kata
                int kat3 = 180 - kat1 - kat2;

                if (czyMoznaZbudowacTrojkat(kat1, kat2, kat3)) 
                {
                    System.out.println("Mozna zbudowac trójkat: " + kat1 + ", " + kat2 + ", " + kat3);
                } 
                else 
                {
                    System.out.println("Nie można zbudować trójkąta: " + kat1 + "," + kat2 + ", " + kat3);
                }
            }
        } 

        catch (InterruptedException e) 
        {
            e.printStackTrace();
        }
    }

    //suma katow w trojkacie = 180, kat > 0
    private boolean czyMoznaZbudowacTrojkat(int kat1, int kat2, int kat3) 
    {
        return (kat1 + kat2 + kat3 == 180) && (kat1 > 0) && (kat2 > 0) && (kat3 > 0);
    }
}


public class Zad2 
{
    public static void main(String[] args) 
    {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(2);

        GeneratorLosowychLiczb generator = new GeneratorLosowychLiczb(queue);
        Sprawdz sprawdz = new Sprawdz(queue);

        generator.start();
        sprawdz.start();
    }
}
