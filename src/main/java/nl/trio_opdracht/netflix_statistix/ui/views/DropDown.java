package nl.trio_opdracht.netflix_statistix.ui.views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Collection;

import javax.swing.JComboBox;
import javax.swing.border.EmptyBorder;

import nl.trio_opdracht.netflix_statistix.ui.FontType;
import nl.trio_opdracht.netflix_statistix.ui.views.interfaces.ViewPadding;
import nl.trio_opdracht.netflix_statistix.ui.views.interfaces.ViewText;

public class DropDown extends JComboBox<String> implements ViewPadding, ViewText {
    private String fontName;
    private FontType fontType;
    private int fontSize;

    public DropDown(String[] items){
        super(items);
        init();
    }

    public DropDown(Collection<String> items){
        this(items.toArray(new String[]{}));
    }

    private void init(){
        fontName = "Arial";
        fontType = FontType.NORMAL;
        fontSize = 16;
        updateFont();

        setBackground(Color.WHITE);
        setMaximumSize(new Dimension(250, 30));
        setAlignmentX(Component.LEFT_ALIGNMENT);
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
