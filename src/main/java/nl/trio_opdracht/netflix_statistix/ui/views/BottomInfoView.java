package nl.trio_opdracht.netflix_statistix.ui.views;

import java.awt.Color;
import java.awt.GridLayout;

public class BottomInfoView extends ContainerView {
    public BottomInfoView(String appName, String authors){
        super(new GridLayout(1, 2));
        TextView app = new TextView(appName);
        app.setImageUrl("appicon.png");
        addChild(app);
        addChild(new TextView(authors));
    }

    @Override protected void init(){
        setBackgroundColor(Color.WHITE);
        setPadding(4, 4, 4, 4);
    }
}
