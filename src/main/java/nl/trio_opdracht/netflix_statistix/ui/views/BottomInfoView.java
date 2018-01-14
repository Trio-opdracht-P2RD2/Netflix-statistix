package nl.trio_opdracht.netflix_statistix.ui.views;

import java.awt.Color;
import java.awt.GridLayout;

public class BottomInfoView extends ContainerView {
    public BottomInfoView(String appName, String authors){
        super(new GridLayout(1, 2));
        setBackground(Color.WHITE);
        add(new TextView(appName));
        add(new TextView(authors));
        setPadding(4, 4, 4, 4);
    }
}
