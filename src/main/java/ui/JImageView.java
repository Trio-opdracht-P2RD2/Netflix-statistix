package ui;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class JImageView extends JLabel {
    public JImageView(){
        super();
        setHorizontalAlignment(SwingConstants.CENTER);
    }

    public JImageView(String imageUrl){
        this();
        setImageUrl(imageUrl);
    }

    public void setImageUrl(String imageUrl){
        setIcon(new ImageIcon(imageUrl));
    }
}
