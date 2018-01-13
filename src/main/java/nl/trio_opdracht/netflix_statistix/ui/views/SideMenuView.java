package nl.trio_opdracht.netflix_statistix.ui.views;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;

public class SideMenuView extends Panel {
    public SideMenuView(){
        super(BoxLayout.Y_AXIS);
        setBackground(Color.WHITE);
    }

    public void addMenuItem(String title, ActionListener onClickListener){
        Button button = new Button(title);
        button.addActionListener(onClickListener);
        button.setPadding(25, 10, 25, 10);
        add(button);
    }
}
