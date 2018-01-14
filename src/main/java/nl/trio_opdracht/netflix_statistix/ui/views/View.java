package nl.trio_opdracht.netflix_statistix.ui.views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.border.EmptyBorder;

import nl.trio_opdracht.netflix_statistix.Configuration;
import nl.trio_opdracht.netflix_statistix.ui.FontType;

public class View<T extends JComponent> {
    private T view;
    private OnHoverListener onHoverListener;
    private String fontName;
    private FontType fontType;
    private int fontSize;

    public View(T view){
        this.view = view;
        fontName = Configuration.defaultFont;
        fontType = FontType.NORMAL;
        fontSize = 16;
        updateFont();

        getJComponent().addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent mouseEvent) {
                if(onHoverListener != null) onHoverListener.onHoverChanged(View.this, true);
            }

            @Override public void mouseExited(MouseEvent mouseEvent) {
                if(onHoverListener != null) onHoverListener.onHoverChanged(View.this, false);
            }
        });

        init();
    }

    public View(View<T> view){
        this.view = view.getJComponent();
        this.fontName = view.fontName;
        this.fontType = view.fontType;
        this.fontSize = view.fontSize;
        this.onHoverListener = view.onHoverListener;
    }

    protected void init(){}

    public T getJComponent(){
        return view;
    }

    public void setTextSize(int size){
        fontSize = size;
        updateFont();
    }

    public void setFontType(FontType type){
        if(getJComponent() instanceof JButton) System.out.println("setFontType(" + type.toString() + ");");
        fontType = type;
        updateFont();
    }

    public void setFontName(String name){
        fontName = name;
        updateFont();
    }

    private void updateFont(){
        if(getJComponent() instanceof JButton) System.out.println(fontType.toString());
        getJComponent().setFont(new Font(fontName, fontType == FontType.BOLD ? Font.BOLD : fontType == FontType.ITALIC ? Font.ITALIC : Font.PLAIN, fontSize));
        getJComponent().updateUI();
    }

    public void setBackgroundColor(Color color){
        getJComponent().setBackground(color);
    }

    public Color getBackgroundColor(){
        return getJComponent().getBackground();
    }

    public void setTextColor(Color color){
        getJComponent().setForeground(color);
    }

    public void setPadding(int left, int top, int right, int bottom){
        getJComponent().setBorder(new EmptyBorder(top, right, bottom, left));
    }

    public void addChild(View child){
        getJComponent().add(child.getJComponent());
        getJComponent().updateUI();
    }

    public void setHorizontalAlignment(float alignment){
        getJComponent().setAlignmentX(alignment);
    }

    public void setVerticalAlignment(float alignment){
        getJComponent().setAlignmentY(alignment);
    }

    public interface OnHoverListener {
        void onHoverChanged(View view, boolean isHovered);
    }

    public void setOnHoverListener(OnHoverListener listener){
        onHoverListener = listener;
    }

    public ArrayList<JComponent> getChildren(){
        ArrayList<JComponent> children = new ArrayList<>();
        for(Component component : getJComponent().getComponents())
            if(component instanceof JComponent)
                children.add((JComponent) component);
        return children;
    }

    public int getChildCount(){
        return getJComponent().getComponentCount();
    }

    public void removeAllChildren(){
        getJComponent().removeAll();
        getJComponent().updateUI();
    }
}
