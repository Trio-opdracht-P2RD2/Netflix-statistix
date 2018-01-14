package nl.trio_opdracht.netflix_statistix.ui.views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.Collection;

import javax.swing.JComboBox;

public class DropDown extends View<JComboBox<String>> {
    private OnItemSelectedListener onItemSelectedListener;

    public DropDown(String[] items){
        super(new JComboBox<>(items));
    }

    public DropDown(Collection<String> items){
        this(items.toArray(new String[]{}));
    }

    @Override protected void init(){
        setBackgroundColor(Color.WHITE);
        getJComponent().setMaximumSize(new Dimension(250, 30));
        setHorizontalAlignment(Component.LEFT_ALIGNMENT);
        getJComponent().addActionListener(actionEvent -> {
            if(onItemSelectedListener != null) onItemSelectedListener.onItemSelected(DropDown.this, getSelectedItem(), getJComponent().getSelectedIndex());
        });
    }

    public interface OnItemSelectedListener {
        void onItemSelected(DropDown view, String item, int index);
    }

    public void setOnItemSelectedListener(OnItemSelectedListener listener){
        onItemSelectedListener = listener;
    }

    public void selectByIndex(int index){
        getJComponent().setSelectedIndex(index);
    }

    public String getSelectedItem(){
        return (String) getJComponent().getSelectedItem();
    }
}
