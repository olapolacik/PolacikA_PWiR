package kolkokrzyzyk;

import javax.swing.JButton;

/**
 *
 * @author Ola
 */

public interface LogikaGry {
    void wykonajRuch(int indeks);
    //boolean czyGraSieSkonczyla();
    JButton getButton(int index);
}
