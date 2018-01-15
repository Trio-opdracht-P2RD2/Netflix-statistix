package nl.trio_opdracht.netflix_statistix.ui.views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import nl.trio_opdracht.netflix_statistix.ui.ColorTools;
import nl.trio_opdracht.netflix_statistix.Configuration;
import nl.trio_opdracht.netflix_statistix.ui.FontType;

/**
 * A view that can be shown.
 * @param <T> The kind of view. Can be JButton, JLabel, etc.
 */
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

    /**
     * This method could be overridden by a child class, but doesn't have to. It will always get called after initialised.
     */
    protected void init(){}

    public T getJComponent(){
        return view;
    }

    /**
     * Changes the font size of the text
     * @param size the font size in pixels
     */
    public void setTextSize(int size){
        fontSize = size;
        updateFont();
    }

    /**
     * Changes the kind/type of font
     * @param type The type of font, must be NORMAL, BOLD or ITALIC
     */
    public void setFontType(FontType type){
        fontType = type;
        updateFont();
    }

    /**
     * Sets the name of the font
     * @param name The name of the font, like Segoe UI, Arial, Calibri, etc.
     */
    public void setFontName(String name){
        fontName = name;
        updateFont();
    }

    /**
     * Updates the font when the name, type or size has been changed.
     * This remembers previous settings, so that the fontName and type stays the same when you change the size
     */
    private void updateFont(){
        getJComponent().setFont(new Font(fontName, fontType == FontType.BOLD ? Font.BOLD : fontType == FontType.ITALIC ? Font.ITALIC : Font.PLAIN, fontSize));
        getJComponent().updateUI();
    }

    /**
     * Sets the background color and updates containing TextViews to a white or black color, so that they remain readable.
     * @param color The new background color
     */
    public void setBackgroundColor(Color color){
        getJComponent().setBackground(color);
        setTextColor(ColorTools.isColorDark(color) ? Color.WHITE : Color.BLACK);
        for(Component component : getJComponent().getComponents())
            if(component instanceof JLabel) component.setForeground(ColorTools.isColorDark(color) ? Color.WHITE : Color.BLACK);
    }

    public Color getBackgroundColor(){
        return getJComponent().getBackground();
    }

    public void setTextColor(Color color){
        getJComponent().setForeground(color);
    }

    /**
     * Sets the padding (used in CSS on the web and Java/XML on Android).
     * This increases the width and height of the view, while the width and the height of the content stays the same.
     * You can say it actually adds an empty border with the same color as the component background, which is what we do here.
     * @param left The padding on the left side
     * @param top The padding on the upper side
     * @param right The padding on the right side
     * @param bottom The padding on the bottom side
     */
    public void setPadding(int left, int top, int right, int bottom){
        getJComponent().setBorder(new EmptyBorder(top, right, bottom, left));
    }

    /**
     * Adds a child to the View and updates its color to black or white when it is a TextView, so it remains readable
     * @param child the view to add
     */
    public void addChild(View child){
        if(child instanceof TextView) child.setTextColor(ColorTools.isColorDark(getBackgroundColor()) ? Color.WHITE : Color.BLACK);
        getJComponent().add(child.getJComponent());
        getJComponent().updateUI();
    }

    /**
     * Sets the horizontal alignment of the content in the view
     * @param alignment must be left, center or right
     */
    public void setHorizontalAlignment(float alignment){
        getJComponent().setAlignmentX(alignment);
    }

    /**
     * Sets the vertical alignment of the content in the view
     * @param alignment must be top, center or bottom
     */
    public void setVerticalAlignment(float alignment){
        getJComponent().setAlignmentY(alignment);
    }

    /**
     * Notifies the requesting method that the view has been hovered or that the mouse has exited the view
     */
    public interface OnHoverListener {
        void onHoverChanged(View view, boolean isHovered);
    }

    public void setOnHoverListener(OnHoverListener listener){
        onHoverListener = listener;
    }

    /**
     * Retrieves the children of the view
     * @return an ArrayList of views
     */
    public ArrayList<JComponent> getChildren(){
        ArrayList<JComponent> children = new ArrayList<>();
        for(Component component : getJComponent().getComponents())
            if(component instanceof JComponent)
                children.add((JComponent) component);
        return children;
    }

    /**
     * Gets the amount of children
     * @return the amount of children
     */
    public int getChildCount(){
        return getJComponent().getComponentCount();
    }

    /**
     * Removes all child views
     */
    public void removeAllChildren(){
        getJComponent().removeAll();
        getJComponent().updateUI();
    }

    public void setToolTipText(String text){
        getJComponent().setToolTipText(text);
    }
}
