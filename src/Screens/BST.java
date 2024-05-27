package Screens;

import Components.TreeViewer;
import Node.Tree;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BST extends Screen{

    private TreeViewer treeViewer;

    // for checking that auto button is on or off
   private boolean Auto_balancing= false;
     private Tree tree;
     protected AVL AVLTree ;

     //AVL FUCTIONS

    public BST(int width, int height) {
        super(width, height);
        this.AVLTree = new AVL();
        this.treeViewer = new TreeViewer(50, 80, 900, 500);
        this.tree = new Tree();
        init();

    }
    private Tree.Node inserthelper(int value, Tree.Node node, int depth) {

        if (node == null) {
            node = this.tree.new Node(value);
            node.depth = depth;
            if (this.tree.max_depth < node.depth) {
                this.tree.max_depth = node.depth;
            }

        } else if (node.value < value) {
            node.right = inserthelper(value, node.right, depth + 1);
        } else if (node.value > value) {
            node.left = inserthelper(value, node.left, depth + 1);
        }


        node.height = 1 + Math.max(this.tree.height(node.left), this.tree.height(node.right));
        if (this.tree.max_height < node.height) {
            this.tree.max_height = node.height;
        }


        // AVL checking if needed
        if (Auto_balancing){
            node = AVLTree.Rotation(node, value);

            // for displaying nodes after rotation
            this.initTree(this.tree.root,null,0,900);//x,y location
    }


        return node;
    }
    @Override
    public void insert(int val) {

        this.tree.root= this.inserthelper(val,this.tree.root,0);//insert into data structure
        this.initTree(this.tree.root,null,0,900);//x,y location
        this.treeViewer.setTree(this.tree);//displaying tree
    }

    public void initTree(Tree.Node node,Tree.Node parent, int start,int end){
        if(node == null){
            return;
        }
        node.x = ((start+end)/2) - 25;
        if(this.tree.root == node){
            node.y = 10;
            int maxLeaves = (int)(Math.pow(2,this.tree.max_depth)/2);
            int leftScreen = ((start+end)/2) - ((maxLeaves + 1) * 2 + 50 * maxLeaves);
            int rightScreen = ((start+end)/2) + ((maxLeaves + 1) * 2 + 50 * maxLeaves);
            initTree(node.left,node,leftScreen,(start+end)/2);
            initTree(node.right,node,(start+end)/2, rightScreen);
        }
        else  {
            node.y = parent.y +100;
            initTree(node.left,node,start,(start+end)/2);
            initTree(node.right,node,(start+end)/2, end);
        }


    }


    @Override
    public void delete(int val) {

        this.tree.root = deleteHelper(this.tree.root, val);
        this.initTree(this.tree.root,null,0,900);//x,y location
        this.treeViewer.setTree(this.tree);//displaying tree
    }

    private Tree.Node deleteHelper(Tree.Node node, int value){
        if(node == null){
            return null;
        }
        if(node.value > value){
            node.left = deleteHelper(node.left,value);
        }
        else if(node.value < value){
            node.right = deleteHelper(node.right,value);
        }
        else {
           if(node.right == null && node.left == null){//when delete node is a leaf node
               return  null;//
           } else if (node.left == null) { //when delete node has a right or left child.
               return  node.right;
           } else if (node.right == null) {
               return  node.left;
           }
           else {
               node.value = miniValue(node.right);
               node.right = deleteHelper(node.right,node.value);
           }

        }

        // AVL balancing
        if(Auto_balancing) {
            node = AVLTree.Rotation(node, value);

            // for displaying nodes after rotation
            this.initTree(this.tree.root,null,0,900);//x,y location
        }

        return node;
    }

    private int miniValue(Tree.Node node){
        int minValue = node.value;
        while (node.left != null){
            minValue = node.left.value;
            node = node.left;
        }
        return minValue;
    }


    // balance the whole tree when balanced tree button is pressed

    void AVL_balance(Tree.Node node) {
        int i =0;
        do {
            Auto_balancing = true;
            balanceInsert(node);
            i++;
        }while(i<node.height);
        Auto_balancing = false;
    }
    //traverse all node and apply rotation if needed
    private void balanceInsert(Tree.Node node) {
        if (node == null) return;
        balanceInsert(node.left);
        balanceInsert(node.right);
        insert(node.value);
    }


    @Override
    public void init() {
       JTextField input = new JTextField();
       input.setBounds(370,30,100,30);
       input.setBackground(new Color(245, 239, 230));
       this.add(input);

       JButton insert = new JButton("INSERT");
       insert.setBounds(250,30,100,30);
       insert.setBackground(new Color(232, 223, 202));
       insert.addActionListener(e -> {
           String val = input.getText();
           String[] sepvalue = val.split(",");
           try {
               for (String upvalue : sepvalue) {
                   int insertVal = Integer.parseInt(upvalue);
                   this.insert(insertVal);
               }
           }
            catch (Exception ex) {
              ex.printStackTrace();
           }
       });
        this.add(insert);
        this.add(this.treeViewer);

        JButton delete = new JButton("DELETE");
        delete.setBounds(490,30,100,30);
        delete.setBackground(new Color(232, 223, 202));
        delete.addActionListener(e -> {
            String val = input.getText();
            try {
                int delVal = Integer.parseInt(val);
                this.delete(delVal);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        this.add(delete);
        JButton back  = new JButton("BACK");
        back.setBackground(new Color(231, 176, 247));
        back.setBounds(50,20,90,50);
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                MainWindow.screenManager.changeScreen("startscreen");
            }
        });
        this.add(back);
        JToggleButton  AvlButton = new JToggleButton("AUTO ROTATION");
        AvlButton.setBackground(new Color(231, 176, 247));
        AvlButton.setBounds(625,20,155,50);
        AvlButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (AvlButton.isSelected()) {
                    AvlButton.setBackground(new Color(232, 223, 202));
                    AvlButton.setForeground(Color.darkGray);
                    AvlButton.setFocusable(false);
                    Auto_balancing = true;
                } else {
                    AvlButton.setBackground(new Color(231, 176, 247));
                    Auto_balancing = false;
                };
            }
        });
        this.add(AvlButton);

        JButton balanceTree = new JButton("CONVERT IN AVL");
        balanceTree.setBackground(new Color(231, 176, 247));
        balanceTree.setBounds(810,20,140,50);
        balanceTree.setOpaque(true);
        balanceTree.setFocusable(false);
        balanceTree.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                AVL_balance(tree.root);
            }
        });
        this.add(balanceTree);

        JButton refresh = new JButton("REFRESH THE SCREEN");
        refresh.setBounds(750,590,190,40);
        refresh.setBackground(new Color(231, 176, 247));
        refresh.setForeground(Color.BLACK);
        refresh.setVisible(true);
        refresh.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(tree.root!=null){
                    tree.root = null;
                    refresh();
                }


            }
        });
        this.add(refresh);





    }
}
