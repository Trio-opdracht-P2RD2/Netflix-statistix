package nl.trio_opdracht.netflix_statistix.ui;

import java.awt.Color;

public class ColorTools {
    /**
     * Checks if the color is dark
     * @param color the color to check
     * @return true if the color is dark, false if the color is light/bright
     */
    public static boolean isColorDark(Color color){
        return 1 - (0.299 * color.getRed() + 0.587 * color.getGreen() + 0.114 * color.getBlue()) / 255 >= 0.5;
    }
}
