package nl.trio_opdracht.netflix_statistix.ui.views;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import nl.trio_opdracht.netflix_statistix.Configuration;

public class TextView extends View<JLabel> {
    public TextView(){
        super(new JLabel());
    }

    public TextView(String text){
        super(new JLabel(text));
    }

    @Override protected void init(){
        setHorizontalAlignment(Component.LEFT_ALIGNMENT);
        setPadding(4, 4, 4, 4);
    }

    /**
     * Adds an image to the text from the path and makes sure it doesn't get to large
     * @param path the path to the image, relative to the resources folder
     */
    public void setImageUrl(String path){
        ImageIcon imageIcon = new ImageIcon(Configuration.projectPath + "\\resources\\" + path);
        getJComponent().setIcon(new ImageIcon(imageIcon.getImage().getScaledInstance(imageIcon.getIconWidth() / (imageIcon.getIconHeight() / 30), 30, java.awt.Image.SCALE_SMOOTH)));
    }
}
