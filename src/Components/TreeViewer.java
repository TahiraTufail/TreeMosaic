package Components;

import Node.Tree;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TreeViewer extends JPanel {
    private Tree tree;
    public TreeViewer(int x, int y, int width, int height) {
        this.setLayout(null);
        this.setBounds(x, y, width, height);
        this.setBackground(new Color(231, 176, 247));
    }
    public void setTree(Tree t){
        this.tree = t;
        this.repaint();
    }
    private void drawTree(Graphics2D g, Tree.Node node){
        if(node == null){
            return;
        }
        g.drawOval(node.x, node.y, 50,50);
        g.setColor(new Color(30, 171, 137));
        g.fillOval(node.x, node.y,50,50);
        g.fillOval(node.x, node.y,50,50);
        if(node.left != null ){
            g.drawLine(node.x +25 , node.y + 25, node.left.x+25, node.left.y+25);
            drawTree(g,node.left);
        }
        if(node.right != null ){
            g.drawLine(node.x+25 , node.y+ 25, node.right.x +25, node.right.y+25);
            drawTree(g,node.right);
        }
        g.setColor(Color.white);
        g.setFont(new Font("Arial",Font.BOLD,19));
        g.drawString(Integer.toString(node.value),node.x+15,node.y+25);

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if(this.tree != null){
            drawTree(g2,this.tree.root);
        }
    }
}
