import java.util.Random;

class Talia {

    private int[] talia;
    private static int walet = 2;
    private static int krol = 4;
    private static int dama = 3;
    private static int As = 11;

    public Talia() {
        talia = new int[]{2, 3, 4, 5, 6, 7, 8, 9, 10, walet, dama, krol, As, 2, 3, 4, 5, 6, 7, 8, 9, 10, walet, dama, krol, As,
                2, 3, 4, 5, 6, 7, 8, 9, 10, walet, dama, krol, As, 2, 3, 4, 5, 6, 7, 8, 9, 10, walet, dama, krol, As};
            }

    //mozna wykorzystac liste i metode Shuffle()

    public int losujKarte() {
        Random rand = new Random();
        int index = rand.nextInt(talia.length);
        int karta = talia[index];
        // Usuń wylosowaną kartę z talii
        talia[index] = talia[talia.length - 1];
        talia[talia.length - 1] = 0; // Zeruj ostatni element
        return karta;
    }
}
