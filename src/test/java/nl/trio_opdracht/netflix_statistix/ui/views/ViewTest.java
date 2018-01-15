package nl.trio_opdracht.netflix_statistix.ui.views;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.Color;

import nl.trio_opdracht.netflix_statistix.ui.ColorTools;

public class ViewTest {
    @Test public void testSetBackgroundColorWithDarkColorMakesTextViewLight(){
        ContainerView containerView = new ContainerView();
        containerView.setBackgroundColor(Color.WHITE);
        TextView textView = new TextView("test");
        textView.setTextColor(Color.BLACK);
        containerView.addChild(textView);

        containerView.setBackgroundColor(Color.BLACK);

        Assertions.assertFalse(ColorTools.isColorDark(textView.getTextColor()));
    }

    @Test public void testAddChildWithDarkBackgroundColorMakesTextViewLight(){
        ContainerView containerView = new ContainerView();
        containerView.setBackgroundColor(Color.BLACK);
        TextView textView = new TextView("test");
        textView.setTextColor(Color.BLACK);

        containerView.addChild(textView);

        Assertions.assertFalse(ColorTools.isColorDark(textView.getTextColor()));
    }
}