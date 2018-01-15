package nl.trio_opdracht.netflix_statistix.ui.views;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import nl.trio_opdracht.netflix_statistix.Configuration;

public class Window extends JFrame {
    public Window(String title){
        super(title);

        setPreferredSize(new Dimension(1000, 800));
        setMinimumSize(new Dimension(750, 500));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public Window(String title, String iconPath){
        this(title);
        setIcon(iconPath);
    }

    public void showWindow(){
        pack();
        setVisible(true);
    }

    public void setIcon(String path){
        setIconImage(new ImageIcon(Configuration.projectPath + "\\resources\\" + path).getImage());
    }

    public void addChild(View child){
        add(child.getJComponent());
    }
}
