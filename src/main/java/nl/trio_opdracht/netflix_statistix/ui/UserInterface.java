package nl.trio_opdracht.netflix_statistix.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;

import nl.trio_opdracht.netflix_statistix.Configuration;
import nl.trio_opdracht.netflix_statistix.pages.Page;
import nl.trio_opdracht.netflix_statistix.ui.views.BottomInfoView;
import nl.trio_opdracht.netflix_statistix.ui.views.ContainerView;
import nl.trio_opdracht.netflix_statistix.ui.views.SideMenuView;
import nl.trio_opdracht.netflix_statistix.ui.views.Window;

/**
 * This class showns the UI of the app. It runs on a seperate thread (implements Runnable)
 */
public class UserInterface implements Runnable {
    private Window window; // The app Window
    private Page[] pages; // The pages of the app. Every page has a content view and a title shown in a button.

    /**
     * Initialises the UI class.
     * @param pages The pages that the app wil show.
     */
    public UserInterface(Page... pages){
        this.pages = pages;
    }

    /**
     * Shows the UI Window.
     */
    @Override public void run() {
        window = new Window(Configuration.appName, "appicon.png"); // Create a new Window, with the appname as title and an icon.
        createComponents(window.getContentPane()); // Adds the content to the Window
        window.addWindowListener(new WindowAdapter() {
            @Override public void windowClosing(WindowEvent we) {
                pages[0].closeSqlConnection();
            }
        });// When the user closes the Window, close the SQLConnection aswell.
        window.showWindow();
    }

    /**
     * Adds the content to the Window
     * @param container The container of the Window
     */
    private void createComponents(Container container) {
        container.add(new BottomInfoView(Configuration.appName, Configuration.authors, Configuration.authorsLong).getJComponent(), BorderLayout.SOUTH);

        ContainerView contentView = new ContainerView(BoxLayout.Y_AXIS);
        contentView.setPadding(25, 25, 25, 25);
        contentView.setBackgroundColor(Configuration.backgroundColor);
        container.add(contentView.getJComponent());

        SideMenuView sideMenuView = new SideMenuView();
        // Add the pages to the menu and let them show when clicked
        for(Page page : pages) {
            page.setContentView(contentView);
            sideMenuView.addMenuItem(page.getTitle(), actionEvent -> {
                page.showPage();
                window.setTitle(Configuration.appName + " - " + page.getTitle());
            });
        }
        container.add(sideMenuView.getJComponent(), BorderLayout.WEST);
        ((JButton) sideMenuView.getChildren().get(0)).doClick();
    }
}