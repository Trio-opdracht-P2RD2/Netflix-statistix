package nl.trio_opdracht.netflix_statistix.ui.views;

import java.awt.Component;
import java.awt.LayoutManager;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import nl.trio_opdracht.netflix_statistix.Configuration;

public class ContainerView extends View<JPanel> {
    public ContainerView(){
        super(new JPanel());
    }

    public ContainerView(LayoutManager layoutManager){
        super(new JPanel(layoutManager));
    }

    public ContainerView(int boxLayoutDirection){
        this();
        setLayout(boxLayoutDirection);
    }

    @Override protected void init(){
        setBackgroundColor(Configuration.backgroundColor);
        setHorizontalAlignment(Component.LEFT_ALIGNMENT);
    }

    public void setLayout(int boxLayoutDirection){
        getJComponent().setLayout(new BoxLayout(getJComponent(), boxLayoutDirection));
    }
}
