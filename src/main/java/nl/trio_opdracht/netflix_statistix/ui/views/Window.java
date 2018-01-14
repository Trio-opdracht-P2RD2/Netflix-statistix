package nl.trio_opdracht.netflix_statistix.ui.views;

import java.awt.Dimension;
import java.io.File;

import javax.swing.ImageIcon;
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

    public void setIcon(String path){
        setIconImage(new ImageIcon(new File("").getAbsolutePath() + "\\src\\main\\resources\\" + path).getImage());
    }
}
