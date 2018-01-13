package nl.trio_opdracht.netflix_statistix.ui.views;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Window extends JFrame {
    public Window(String title){
        super(title);
        setPreferredSize(new Dimension(1000, 800));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void showWindow(){
        pack();
        setVisible(true);
    }
}
