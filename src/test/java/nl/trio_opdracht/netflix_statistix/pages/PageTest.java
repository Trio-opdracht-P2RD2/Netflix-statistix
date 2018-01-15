package nl.trio_opdracht.netflix_statistix.pages;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import nl.trio_opdracht.netflix_statistix.ui.views.ContainerView;
import nl.trio_opdracht.netflix_statistix.ui.views.TextView;

public class PageTest {
    @Test public void testShowPageRemovesAllChildren(){
        EmptyPage page = new EmptyPage(null);
        ContainerView containerView = new ContainerView();
        containerView.addChild(new TextView("Test1"));
        containerView.addChild(new TextView("Test2"));
        page.setContentView(containerView);

        page.showPage();

        Assert.assertEquals(0, page.getContentView().getChildCount());
    }
}