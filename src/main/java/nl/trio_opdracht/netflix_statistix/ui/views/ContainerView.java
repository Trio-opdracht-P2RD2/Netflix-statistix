package nl.trio_opdracht.netflix_statistix.ui.views;

import java.awt.Component;
import java.awt.LayoutManager;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import nl.trio_opdracht.netflix_statistix.ui.views.interfaces.ViewPadding;

import static nl.trio_opdracht.netflix_statistix.Configuration.backgroundColor;

public class ContainerView extends JPanel implements ViewPadding {
    public ContainerView(){
        super();
        init();
    }

    public ContainerView(LayoutManager layoutManager){
        super(layoutManager);
        init();
    }

    public ContainerView(int boxLayoutDirection){
        this();
        setLayout(boxLayoutDirection);
    }

    private void init(){
        setBackground(backgroundColor);
        setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    public void setPadding(int left, int top, int right, int bottom){
        setBorder(new EmptyBorder(top, right, bottom, left));
    }

    public void setLayout(int boxLayoutDirection){
        setLayout(new BoxLayout(this, boxLayoutDirection));
    }
}
