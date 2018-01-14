package nl.trio_opdracht.netflix_statistix.ui.views;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.SwingConstants;

import nl.trio_opdracht.netflix_statistix.ColorTools;
import nl.trio_opdracht.netflix_statistix.ui.FontType;

import static nl.trio_opdracht.netflix_statistix.Configuration.backgroundColor;

public class SideMenuView extends ContainerView {
    public SideMenuView(){
        super(BoxLayout.Y_AXIS);
        setBackgroundColor(ColorTools.isColorDark(backgroundColor) ? backgroundColor.darker().darker() : backgroundColor.darker());
        getJComponent().setPreferredSize(new Dimension(350, getJComponent().getPreferredSize().height));
    }

    public void addMenuItem(String title, Button.OnClickListener onClickListener){
        Button button = new Button(title);
        button.setOnClickListener(buttonView -> {
            getChildren().forEach(view -> view.setBackground(ColorTools.isColorDark(backgroundColor) ? backgroundColor.darker().darker() : backgroundColor.darker()));
            buttonView.setBackgroundColor(button.isHovered() ? backgroundColor.darker() : backgroundColor);

            onClickListener.onClick(buttonView);
        });
        button.setPadding(25, 20, 25, 20);
        button.getJComponent().setMaximumSize(new Dimension(Integer.MAX_VALUE, button.getJComponent().getMinimumSize().height));
        button.getJComponent().setHorizontalAlignment(SwingConstants.LEFT);
        button.setBackgroundColor(ColorTools.isColorDark(backgroundColor) ? backgroundColor.darker().darker() : backgroundColor.darker());
        button.setFontType(FontType.BOLD);
        addChild(button);
    }
}
