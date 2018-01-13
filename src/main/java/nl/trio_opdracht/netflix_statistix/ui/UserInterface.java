package nl.trio_opdracht.netflix_statistix.ui;

import java.awt.BorderLayout;
import java.awt.Container;

import nl.trio_opdracht.netflix_statistix.Configuration;
import nl.trio_opdracht.netflix_statistix.pages.Page;
import nl.trio_opdracht.netflix_statistix.ui.views.BottomInfoView;
import nl.trio_opdracht.netflix_statistix.ui.views.ContainerView;
import nl.trio_opdracht.netflix_statistix.ui.views.SideMenuView;
import nl.trio_opdracht.netflix_statistix.ui.views.Window;

public class UserInterface implements Runnable {
    private Window window;
    private Page[] pages;

    public UserInterface(Page... pages){
        this.pages = pages;
    }

    @Override public void run() {
        window = new Window(Configuration.appName);
        createComponents(window.getContentPane());
        window.showWindow();
    }

    private void createComponents(Container container) {
        container.add(new BottomInfoView(Configuration.appName, Configuration.authors), BorderLayout.SOUTH);

        ContainerView contentView = new ContainerView();
        container.add(contentView);

        SideMenuView sideMenuView = new SideMenuView();
        for(Page page : pages) {
            page.setContentView(contentView);
            sideMenuView.addMenuItem(page.getTitle(), actionEvent -> {
                page.showPage();
                window.setTitle(Configuration.appName + " - " + page.getTitle());
            });
        }
        container.add(sideMenuView, BorderLayout.WEST);
    }
}