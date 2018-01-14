package nl.trio_opdracht.netflix_statistix.ui.views;

import java.awt.Component;
import java.awt.LayoutManager;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import static nl.trio_opdracht.netflix_statistix.Configuration.backgroundColor;

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
        setBackgroundColor(backgroundColor);
        setHorizontalAlignment(Component.LEFT_ALIGNMENT);
    }

    public void setLayout(int boxLayoutDirection){
        getJComponent().setLayout(new BoxLayout(getJComponent(), boxLayoutDirection));
    }
}
