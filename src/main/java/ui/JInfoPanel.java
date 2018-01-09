package ui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class JInfoPanel extends JPanel {
    public JInfoPanel(){
        super(new GridLayout(1, 2));
        setBackground(Color.WHITE);
        add(new JLargeLabel("Netflix Statistix"));
        add(new JLargeLabel("Informatica 1A - Marc, Björn, Thomas"));
        setBorder(new EmptyBorder(2, 2, 2, 2));
    }
}
