package Screens;

import javax.swing.*;
import java.awt.*;

public abstract class Screen extends JPanel {
    public Screen(int width, int height) {
        this.setLayout(null);// pixel by pixel work, X and Y location.
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(new Color(110, 46, 122));
    }

    public abstract void insert(int val);
    public abstract void delete(int val);
    public abstract void init();
    public void refresh() { //This function will be used to refresh the screens
        this.removeAll();
        this.init();
        this.revalidate();
        this.repaint();

    }
}

