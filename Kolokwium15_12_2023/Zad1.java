package Kolokwium15_12_2023;

/**
 * @author Aleksandra Połacik
 */


class WatekLiczb extends Thread 
{
    private int delay;

    public WatekLiczb(int delay) 
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
                //cyfry od 0 do 9 
                for (int i = 0; i < 10; i++) 
                {
                    System.out.print(i + " ");
                    Thread.sleep(delay * 100); 
                }

                //przejscie do nowej lini
                System.out.println(); 

                //uspienie watku na 1 sekunde
                Thread.sleep(1000);

                //cyfry od 9 do 0
                for (int i = 9; i >= 0; i--) 
                {
                    System.out.print(i + " ");
                    Thread.sleep(delay * 100); // Odczekanie czasu określonego przez parametr delay
                }

                //przejscie do nowej lini
                System.out.println();

                // Odczekanie 1 sekundy
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
