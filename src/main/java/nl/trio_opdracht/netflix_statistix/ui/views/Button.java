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

    public Button(View button){
        super(button);
        if(button instanceof Button) this.onClickListener = ((Button) button).onClickListener;
    }

    @Override protected void init(){
        setBackgroundColor(Color.LIGHT_GRAY);
        setOnHoverListener((view, isHovered) -> setBackgroundColor(isHovered ? getBackgroundColor().darker() : getBackgroundColor().brighter()));
        getJComponent().addActionListener(actionEvent -> {
            if(onClickListener != null) onClickListener.onClick(Button.this);
        });
        getJComponent().setFocusPainted(false);

        setHorizontalAlignment(Component.LEFT_ALIGNMENT);

        setPadding(4, 4, 4, 4);
    }

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
