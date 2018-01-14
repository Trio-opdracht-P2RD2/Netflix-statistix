package nl.trio_opdracht.netflix_statistix.ui.views;

import java.awt.GridLayout;

public class BottomInfoView extends ContainerView {
    public BottomInfoView(String appName, String authors){
        super(new GridLayout(1, 2));
        add(new TextView(appName));
        add(new TextView(authors));
        setPadding(2, 2, 2, 2);
    }
}
