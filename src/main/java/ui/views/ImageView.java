package ui.views;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

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
}
