package nl.trio_opdracht.netflix_statistix.ui.views;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.SwingConstants;

import nl.trio_opdracht.netflix_statistix.Configuration;
import nl.trio_opdracht.netflix_statistix.ui.ColorTools;
import nl.trio_opdracht.netflix_statistix.ui.FontType;

public class SideMenuView extends ContainerView {
    public SideMenuView(){
        super(BoxLayout.Y_AXIS);
        setBackgroundColor(ColorTools.isColorDark(Configuration.backgroundColor) ? Configuration.backgroundColor.darker().darker() : Configuration.backgroundColor.darker());
        getJComponent().setPreferredSize(new Dimension(350, getJComponent().getPreferredSize().height));
    }

    public void addMenuItem(String title, Button.OnClickListener onClickListener){
        Button button = new Button(title);
        button.setOnClickListener(buttonView -> {
            getChildren().forEach(view -> view.setBackground(ColorTools.isColorDark(Configuration.backgroundColor) ? Configuration.backgroundColor.darker().darker() : Configuration.backgroundColor.darker()));
            buttonView.setBackgroundColor(button.isHovered() ? Configuration.backgroundColor.darker() : Configuration.backgroundColor);

            onClickListener.onClick(buttonView);
        });
        button.setPadding(25, 20, 25, 20);
        button.getJComponent().setMaximumSize(new Dimension(Integer.MAX_VALUE, button.getJComponent().getMinimumSize().height));
        button.getJComponent().setHorizontalAlignment(SwingConstants.LEFT);
        button.setBackgroundColor(ColorTools.isColorDark(Configuration.backgroundColor) ? Configuration.backgroundColor.darker().darker() : Configuration.backgroundColor.darker());
        button.setFontType(FontType.BOLD);
        addChild(button);
    }
}
