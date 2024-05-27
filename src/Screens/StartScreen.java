package Screens;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StartScreen extends Screen{
    public StartScreen(int width, int height) {
        super(width, height);
        this.init();
    }

    @Override
    public void insert(int val) {

    }

    @Override
    public void delete(int val) {

    }

    @Override
    public void init() {

        ImageIcon image = new ImageIcon("images/no-background.png");
        Image img = image.getImage();
        img = img.getScaledInstance(700,450, Image.SCALE_SMOOTH);
        image = new ImageIcon(img);
        JLabel logo  = new JLabel();
        logo.setIcon(image);
        logo.setBounds(150,-85,700,500);
        this.add(logo);
        JButton bstbutton = new JButton ("BUILD TREE");
        bstbutton.setBounds(350,350,300,70);
        bstbutton.setBackground(new Color(237, 182, 241));
        bstbutton.setForeground(Color.WHITE);
        bstbutton.setFont(new Font("Airal",Font.BOLD,22));
        bstbutton.setVisible(true);
        bstbutton.setFocusable(false);
        bstbutton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                MainWindow.screenManager.changeScreen("bst");

            }
        });
        this.add(bstbutton);




    }
}
