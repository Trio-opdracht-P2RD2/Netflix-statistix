package nl.trio_opdracht.netflix_statistix.ui.views;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import nl.trio_opdracht.netflix_statistix.ui.FontType;

public class TextView extends JLabel implements ViewPadding, ViewText {
    private String fontName;
    private FontType fontType;
    private int fontSize;

    public TextView(){
        super();
        init();
    }

    public TextView(String text){
        super(text);
        init();
    }

    private void init(){
        fontName = "Arial";
        fontType = FontType.NORMAL;
        fontSize = 16;
        updateFont();
        setBorder(new EmptyBorder(2, 2, 2, 2));
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
        setBorder(new EmptyBorder(left, top, right, bottom));
    }
}
