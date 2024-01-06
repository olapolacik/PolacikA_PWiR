import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Talia {

    private static int[] talia = {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11,
                                  2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11};

    private static List<Integer> taliaList;

    static {
        taliaList = new ArrayList<>();
        for (int value : talia) {
            taliaList.add(value);
        }
        Collections.shuffle(taliaList);
    }

    public static synchronized int pobierzKarte() {
        if (!taliaList.isEmpty()) {
            return taliaList.remove(0);
        } else {
            return -1; // Zwracamy -1 gdy talia jest pusta
        }
    }
}


