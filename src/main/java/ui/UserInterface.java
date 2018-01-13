package ui;

import java.awt.BorderLayout;
import java.awt.Container;

import pages.Page;
import ui.views.BottomInfoView;
import ui.views.Panel;
import ui.views.SideMenuView;
import ui.views.Window;

public class UserInterface implements Runnable {
    private Window window;
    private Page[] pages;
    private final static String appName = "Netflix Statistix";

    public UserInterface(Page[] pages){
        this.pages = pages;
    }

    @Override public void run() {
        window = new Window(appName);
        createComponents(window.getContentPane());
        window.showWindow();
    }

    private void createComponents(Container container) {
        container.add(new BottomInfoView(appName, "Informatica 1A - Marc, Björn, Thomas"), BorderLayout.SOUTH);

        Panel contentView = new Panel();
        container.add(contentView);

        SideMenuView sideMenuView = new SideMenuView();
        for(Page page : pages) {
            page.setContentView(contentView);
            sideMenuView.addMenuItem(page.getTitle(), actionEvent -> {
                page.showPage();
                window.setTitle(appName + " - " + page.getTitle());
            });
        }
        container.add(sideMenuView, BorderLayout.WEST);
    }
}