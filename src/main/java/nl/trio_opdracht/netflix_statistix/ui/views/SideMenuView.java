package nl.trio_opdracht.netflix_statistix.ui.views;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;

import nl.trio_opdracht.netflix_statistix.ui.FontType;

public class SideMenuView extends ContainerView {
    public SideMenuView(){
        super(BoxLayout.Y_AXIS);
        setBackground(Color.LIGHT_GRAY);
    }

    public void addMenuItem(String title, ActionListener onClickListener){
        Button button = new Button(title);
        button.addActionListener(onClickListener);
        button.setPadding(25, 20, 25, 20);
        button.setBackground(Color.LIGHT_GRAY);
        button.setFontType(FontType.BOLD);
        add(button);
    }
}
