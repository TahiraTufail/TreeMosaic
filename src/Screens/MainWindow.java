package Screens;

import javax.swing.*;

public class MainWindow extends JFrame {
    static ScreenManager screenManager = new ScreenManager(1000,650);
    MainWindow(){
        this.setTitle("TreeMosaic");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.add(screenManager);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new MainWindow();
    }
    
}
