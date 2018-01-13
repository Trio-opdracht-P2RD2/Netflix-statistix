package nl.trio_opdracht.netflix_statistix.ui.views;

import java.awt.LayoutManager;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import nl.trio_opdracht.netflix_statistix.ui.views.interfaces.ViewPadding;

public class ContainerView extends JPanel implements ViewPadding {
    public ContainerView(){
        super();
    }

    public ContainerView(LayoutManager layoutManager){
        super(layoutManager);
    }

    public ContainerView(int boxLayoutDirection){
        super();
        setLayout(boxLayoutDirection);
    }

    public void setPadding(int left, int top, int right, int bottom){
        setBorder(new EmptyBorder(top, right, bottom, left));
    }

    public void setLayout(int boxLayoutDirection){
        setLayout(new BoxLayout(this, boxLayoutDirection));
    }
}
