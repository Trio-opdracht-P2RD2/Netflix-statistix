package nl.trio_opdracht.netflix_statistix.ui;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.Color;

public class ColorToolsTest {
    @Test public void testIsColorDarkWithBlackReturnsTrue(){
        Assertions.assertTrue(ColorTools.isColorDark(Color.BLACK));
    }

    @Test public void testIsColorDarkWithWhiteReturnsFalse(){
        Assertions.assertFalse(ColorTools.isColorDark(Color.WHITE));
    }
}