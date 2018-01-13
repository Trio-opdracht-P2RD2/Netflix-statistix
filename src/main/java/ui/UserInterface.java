package ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import ui.views.BottomInfoView;
import ui.views.SideMenuView;

public class UserInterface implements Runnable {
    private HashMap<String, ActionListener> menuItems;

    public UserInterface(HashMap<String, ActionListener> menuItems){
        this.menuItems = menuItems;
    }

    @Override public void run() {
        JFrame frame = new JFrame("Netflix Statistix");
        frame.setPreferredSize(new Dimension(1000, 800));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void createComponents(Container container) {
        SideMenuView sideMenuView = new SideMenuView();
        menuItems.forEach(sideMenuView::addMenuItem);
        container.add(sideMenuView);
        container.add(new BottomInfoView("Netflix Statistix", "Informatica 1A - Marc, Björn, Thomas"), BorderLayout.SOUTH);
    }
}