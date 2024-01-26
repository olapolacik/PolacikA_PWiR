package design;

import java.awt.Color;

public class CustomColor {

    public static Color createColor(String redHex, String greenHex, String blueHex) {
        int red = Integer.parseInt(redHex, 16);
        int green = Integer.parseInt(greenHex, 16);
        int blue = Integer.parseInt(blueHex, 16);

        return new Color(red, green, blue);
    }


    // Na przykład, dla koloru o wartości "#FF5481" można by użyć metody createColor("#FF5481");
    public static Color createColor(String hexColor) {
        // Pobierz składowe koloru z formatu szesnastkowego "#RRGGBB"
        int red = Integer.parseInt(hexColor.substring(1, 3), 16);
        int green = Integer.parseInt(hexColor.substring(3, 5), 16);
        int blue = Integer.parseInt(hexColor.substring(5, 7), 16);

        return new Color(red, green, blue);
    }
}
