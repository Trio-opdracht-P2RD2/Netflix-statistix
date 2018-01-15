package nl.trio_opdracht.netflix_statistix.ui.views;

import javax.swing.SwingConstants;

/**
 * This view just shows an Image
 */
public class ImageView extends TextView {
    public ImageView(){
        super();
    }

    public ImageView(String imageUrl){
        this();
        setImageUrl(imageUrl);
    }

    @Override protected void init() {
        getJComponent().setHorizontalAlignment(SwingConstants.CENTER);
        getJComponent().setVerticalAlignment(SwingConstants.CENTER);
    }
}
