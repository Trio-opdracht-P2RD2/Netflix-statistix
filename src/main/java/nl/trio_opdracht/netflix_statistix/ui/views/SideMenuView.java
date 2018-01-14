package nl.trio_opdracht.netflix_statistix.ui.views;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.SwingConstants;

import nl.trio_opdracht.netflix_statistix.ui.FontType;

import static nl.trio_opdracht.netflix_statistix.Configuration.backgroundColor;

public class SideMenuView extends ContainerView {
    public SideMenuView(){
        super(BoxLayout.Y_AXIS);
        setBackgroundColor(Color.LIGHT_GRAY);
        getJComponent().setPreferredSize(new Dimension(350, getJComponent().getPreferredSize().height));
    }

    public void addMenuItem(String title, Button.OnClickListener onClickListener){
        Button button = new Button(title);
        button.setOnClickListener(buttonView -> {
            getChildren().forEach(view -> view.setBackground(Color.LIGHT_GRAY));
            buttonView.setBackgroundColor(button.isHovered() ? backgroundColor.darker() : backgroundColor);

            onClickListener.onClick(buttonView);
        });
        button.setPadding(25, 20, 25, 20);
        button.getJComponent().setMaximumSize(new Dimension(Integer.MAX_VALUE, button.getJComponent().getMinimumSize().height));
        button.getJComponent().setHorizontalAlignment(SwingConstants.LEFT);
        button.setBackgroundColor(Color.LIGHT_GRAY);
        button.setFontType(FontType.BOLD);
        addChild(button);
    }
}
