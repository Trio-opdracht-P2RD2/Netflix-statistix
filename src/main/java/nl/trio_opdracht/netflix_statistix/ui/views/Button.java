package nl.trio_opdracht.netflix_statistix.ui.views;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JButton;

public class Button extends View<JButton> {
    private OnClickListener onClickListener;

    public Button(){
        super(new JButton());
    }

    public Button(String text){
        super(new JButton(text));
    }

    @Override protected void init(){
        setBackgroundColor(Color.LIGHT_GRAY);
        setOnHoverListener((view, isHovered) -> setBackgroundColor(isHovered ? getBackgroundColor().darker() : getBackgroundColor().brighter())); // Makes the background darker when being hovered
        getJComponent().addActionListener(actionEvent -> {
            if(onClickListener != null) onClickListener.onClick(Button.this);
        });
        getJComponent().setFocusPainted(false);

        setHorizontalAlignment(Component.LEFT_ALIGNMENT);

        setPadding(4, 4, 4, 4);
    }

    /**
     * Notifies the requesting method that the button has been clicked
     */
    public interface OnClickListener {
        void onClick(Button view);
    }

    public void setOnClickListener(OnClickListener listener){
        onClickListener = listener;
    }

    public boolean isHovered(){
        return getJComponent().getModel().isRollover();
    }

    public void click(){
        getJComponent().doClick();
    }
}
