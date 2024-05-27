package Screens;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Objects;

public class ScreenManager extends JPanel {
    private final HashMap<String,Screen> screens;
    ScreenManager(int width , int height){
        this.setLayout(new CardLayout());
        this.setPreferredSize(new Dimension(width,height));
        this.screens = new HashMap<>();
        this.screens.put("startscreen",null);
        this.screens.put("bst",null);
        this.changeScreen("startscreen");

    }
    void changeScreen(String key){
        if(!this.screens.containsKey(key)) return;

        if(this.screens.get(key)==null){
            if(Objects.equals(key, "startscreen")){
               this.screens.put(key, new StartScreen(1000,650));
            }
            else if (Objects.equals(key, "bst")){
                this.screens.put(key,new BST(1000, 650));
            }
        }
        this.removeAll(); // if the JPanel has anything inside, it will remove it else do nothing
        screens.get(key).refresh(); // to refresh the screen before adding it
        this.add(screens.get(key));
        this.revalidate(); //Used to invalidate and revalidate a screen (Refreshes the ScreenManager)
        this.repaint();
    }
}
