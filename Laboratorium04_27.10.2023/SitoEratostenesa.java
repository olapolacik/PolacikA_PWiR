import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class MyBuffer {
    private volatile boolean isProduct = false;
    private int product;

    synchronized public void setProduct(int value, String name) {
        while (isProduct) {
            try {
                wait();
            } catch (Exception e) {
            }
        }
        product = value;
        isProduct = true;
        System.out.println(name + " wyprodukował: " + value);
        notifyAll();
    }

    synchronized public int getProduct(String name) {
        while (!isProduct) {
            try {
                wait();
            } catch (Exception e) {
            }
        }
        isProduct = false;
        System.out.println(name + " pobrał " + product);
        notifyAll();
        return product;
    }
}

public class SitoEratostenesa {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Podaj zakres liczb: ");
        int range = sc.nextInt();

        MyBuffer buffer = new MyBuffer();

        List<Thread> sieveThreads = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            sieveThreads.add(new Thread(new Sieve(buffer)));
            sieveThreads.get(i).start();
        }

        Thread generatorThread = new Thread(new Generator(buffer, range));
        generatorThread.start();

        try {
            generatorThread.join();
            for (Thread thread : sieveThreads) {
                thread.interrupt();
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Generator implements Runnable {
    private MyBuffer buffer;
    private int range;

    public Generator(MyBuffer buffer, int range) {
        this.buffer = buffer;
        this.range = range;
    }

    @Override
    public void run() {
        for (int i = 2; i <= range; i++) {
            buffer.setProduct(i, "Generator");
        }
    }
}

class Sieve implements Runnable {
    private MyBuffer buffer;

    public Sieve(MyBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        List<Integer> primes = new ArrayList<>();
        while (true) {
            int prime = buffer.getProduct("Sito");
            if (isPrime(prime, primes)) {
                primes.add(prime);
                System.out.println("Liczba pierwsza: " + prime);
            }
        }
    }

    private boolean isPrime(int n, List<Integer> primes) {
        if (n <= 1) {
            return false;
        }
        for (int prime : primes) {
            if (n % prime == 0) {
                return false;
            }
        }
        return true;
    }
}
