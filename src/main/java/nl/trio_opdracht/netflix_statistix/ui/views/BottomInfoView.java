package nl.trio_opdracht.netflix_statistix.ui.views;

import java.awt.GridLayout;

import nl.trio_opdracht.netflix_statistix.Configuration;

/**
 * The static view on the bottom, containing the name of the app and the authors
 */
public class BottomInfoView extends ContainerView {
    public BottomInfoView(String appName, String authors){
        super(new GridLayout(1, 2));
        TextView app = new TextView(appName);
        app.setImageUrl("appicon.png");
        addChild(app);
        addChild(new TextView(authors));
    }

    @Override protected void init(){
        setBackgroundColor(Configuration.backgroundColor.brighter().brighter());
        setPadding(4, 4, 4, 4);
    }
}
