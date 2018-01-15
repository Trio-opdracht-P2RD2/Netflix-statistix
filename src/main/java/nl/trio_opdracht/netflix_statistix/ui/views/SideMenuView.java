package nl.trio_opdracht.netflix_statistix.ui.views;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.SwingConstants;

import nl.trio_opdracht.netflix_statistix.Configuration;
import nl.trio_opdracht.netflix_statistix.ui.ColorTools;
import nl.trio_opdracht.netflix_statistix.ui.FontType;

/**
 * A view that contains (menu) buttons
 */
public class SideMenuView extends ContainerView {
    private static Color getMenuBackgroundColor() {
        return ColorTools.isColorDark(Configuration.backgroundColor) ? Configuration.backgroundColor.darker().darker() : Configuration.backgroundColor.darker();
    }

    public SideMenuView(){
        super(BoxLayout.Y_AXIS);
        setBackgroundColor(getMenuBackgroundColor());
        getJComponent().setPreferredSize(new Dimension(340, getJComponent().getPreferredSize().height)); // Sets the width to 350px
    }

    /**
     * Adds a button to the menu
     * @param title the title of the button
     * @param onClickListener the callback when the button has been clicked
     */
    public void addMenuItem(String title, Button.OnClickListener onClickListener){
        Button button = new Button(title);
        button.setOnClickListener(buttonView -> {
            // Changes the background color of all buttons to default, but makes the color of the selected button equal to the content
            getChildren().forEach(view -> view.setBackground(getMenuBackgroundColor()));
            buttonView.setBackgroundColor(button.isHovered() ? Configuration.backgroundColor.darker() : Configuration.backgroundColor);

            onClickListener.onClick(buttonView); // Calls the callback
        });
        button.setPadding(25, 20, 25, 20);
        button.getJComponent().setMaximumSize(new Dimension(Integer.MAX_VALUE, button.getJComponent().getMinimumSize().height)); // Makes sure the button uses the full width available
        button.getJComponent().setHorizontalAlignment(SwingConstants.LEFT); // Aligns all text to the left side
        button.setBackgroundColor(getMenuBackgroundColor());
        button.setFontType(FontType.BOLD);
        addChild(button);
    }
}
