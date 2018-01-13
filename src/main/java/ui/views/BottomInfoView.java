package ui.views;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class BottomInfoView extends JPanel {
    public BottomInfoView(String appName, String authors){
        super(new GridLayout(1, 2));
        setBackground(Color.WHITE);
        add(new TextView(appName));
        add(new TextView(authors));
        setBorder(new EmptyBorder(2, 2, 2, 2));
    }
}
