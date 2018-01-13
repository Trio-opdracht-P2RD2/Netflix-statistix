package nl.trio_opdracht.netflix_statistix.ui.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

import nl.trio_opdracht.netflix_statistix.ui.FontType;

public class Button extends JButton implements ViewPadding, ViewText {
    private String fontName;
    private FontType fontType;
    private int fontSize;

    public Button(){
        super();
        init();
    }

    public Button(String text){
        super(text);
        init();
    }

    private void init(){
        fontName = "Arial";
        fontType = FontType.NORMAL;
        fontSize = 16;
        updateFont();

        setBackground(Color.LIGHT_GRAY);
        addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent mouseEvent) {
                setBackground(getBackground().darker());
            }

            @Override public void mouseExited(MouseEvent mouseEvent) {
                setBackground(getBackground().brighter());
            }
        });
        setFocusPainted(false);

        setPadding(2, 2, 2, 2);
    }

    public void setTextSize(int size){
        fontSize = size;
        updateFont();
    }

    public void setFontType(FontType type){
        fontType = type;
        updateFont();
    }

    public void setFontName(String name){
        fontName = name;
        updateFont();
    }

    private void updateFont(){
        setFont(new Font(fontName, fontType == FontType.BOLD ? Font.BOLD : fontType == FontType.ITALIC ? Font.ITALIC : Font.PLAIN, fontSize));
    }

    public void setPadding(int left, int top, int right, int bottom){
        setBorder(new EmptyBorder(top, right, bottom, left));
    }
}
