package nl.trio_opdracht.netflix_statistix.ui.views;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ButtonTest {
    private boolean isClicked;
    private boolean onClickListenerWorks;

    @Test public void testClickClicks(){
        isClicked = false;
        Button button = new Button("Test");
        button.getJComponent().addActionListener(actionEvent -> isClicked = true);

        button.click();

        Assertions.assertTrue(isClicked);
    }

    @Test public void testSetOnClickListenerWorks(){
        onClickListenerWorks = false;
        Button button = new Button("Test");
        button.setOnClickListener(view -> onClickListenerWorks = true);

        button.getJComponent().doClick();

        Assertions.assertTrue(onClickListenerWorks);
    }
}