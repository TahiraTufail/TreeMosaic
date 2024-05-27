package Screens;

import Node.Tree;

public class AVL {

    Tree tree;
    AVL()
    {
        this.tree = new Tree();
    }


    private int getBalance(Tree.Node node) {
        return (node == null) ? 0 : this.tree.height(node.left) - this.tree.height(node.right);
    }

    private Tree.Node rightRotate( Tree.Node y) {
        if(y== null || y.left ==null)
        {
            return y;
        }
        Tree.Node x = y.left;
        Tree.Node T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = Math.max(this.tree.height(y.left), this.tree.height(y.right)) + 1;
        x.height = Math.max(this.tree.height(x.left), this.tree.height(x.right)) + 1;

        // Update depths
        this.tree.updateDepths(x, 0);

        return x;
    }
    private Tree.Node leftRotate(Tree.Node x) {
        if(x== null || x.right ==null)
        {
            return x;
        }
        Tree.Node y = x.right;
        Tree.Node T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        // Update heights
        x.height = Math.max(this.tree.height(x.left), this.tree.height(x.right)) + 1;
        y.height = Math.max(this.tree.height(y.left), this.tree.height(y.right)) + 1;

        // Update depths
       this.tree.updateDepths(y, 0);

        return y;
    }

    Tree.Node Rotation(Tree.Node node,int value)
    {
            int balance = getBalance(node);

            // Left Left Case
            if (balance > 1 && value < node.left.value) {
                return rightRotate(node);
            }

            // Right Right Case
            if (balance < -1 && value > node.right.value) {
                return leftRotate(node);
            }

            // Left Right Case
            if (balance > 1 && value > node.left.value) {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }

            // Right Left Case
            if (balance < -1 && value < node.right.value) {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }
        return node;
}


}