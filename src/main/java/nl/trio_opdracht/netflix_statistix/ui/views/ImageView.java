package nl.trio_opdracht.netflix_statistix.ui.views;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class ImageView extends JLabel {
    public ImageView(){
        super();
        setHorizontalAlignment(SwingConstants.CENTER);
    }

    public ImageView(String imageUrl){
        this();
        setImageUrl(imageUrl);
    }

    public void setImageUrl(String imageUrl){
        setIcon(new ImageIcon(imageUrl));
    }

    public void setPadding(int left, int top, int right, int bottom){
        setBorder(new EmptyBorder(top, right, bottom, left));
    }
}
