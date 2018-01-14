package nl.trio_opdracht.netflix_statistix.ui;

import java.awt.Color;

public class ColorTools {
    public static boolean isColorDark(Color color){
        return 1 - (0.299 * color.getRed() + 0.587 * color.getGreen() + 0.114 * color.getBlue()) / 255 >= 0.5;
    }
}
