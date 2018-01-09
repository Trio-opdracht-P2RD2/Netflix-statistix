package ui;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

public class JLargeLabel extends JLabel {
    public JLargeLabel(){
        super();
        init();
    }

    public JLargeLabel(String text){
        super(text);
        init();
    }

    private void init(){
        setTextSize(16);
        setBorder(new EmptyBorder(2, 2, 2, 2));
    }

    public void setTextSize(int size){
        setFont(new Font("Arial", Font.PLAIN, size));
    }
}
