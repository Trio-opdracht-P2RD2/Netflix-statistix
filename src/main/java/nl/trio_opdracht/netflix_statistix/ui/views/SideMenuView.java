package nl.trio_opdracht.netflix_statistix.ui.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.SwingConstants;

import nl.trio_opdracht.netflix_statistix.ui.FontType;

public class SideMenuView extends ContainerView {
    public SideMenuView(){
        super(BoxLayout.Y_AXIS);
        setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(350, getPreferredSize().height));
    }

    public void addMenuItem(String title, ActionListener onClickListener){
        Button button = new Button(title);
        button.addActionListener(onClickListener);
        button.setPadding(25, 20, 25, 20);
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, button.getMinimumSize().height));
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setBackground(Color.LIGHT_GRAY);
        button.setFontType(FontType.BOLD);
        add(button);
    }
}
