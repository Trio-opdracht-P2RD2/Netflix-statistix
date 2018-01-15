package nl.trio_opdracht.netflix_statistix.ui;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.awt.Color;

public class ColorToolsTest {
    @Test public void testIsColorDarkWithBlackReturnsTrue(){
        Assert.assertTrue(ColorTools.isColorDark(Color.BLACK));
    }

    @Test public void testIsColorDarkWithWhiteReturnsFalse(){
        Assert.assertFalse(ColorTools.isColorDark(Color.WHITE));
    }
}