package Node;

public class Tree {

   public class Node{
       public int value, height,depth,x,y ;
       public   Node left, right;
       public Node(int value){
           this.value = value;
           this.depth = 0;
           this.height = 0;
       }
   }
   public  Node root;
   public int max_depth,max_height;
   public Tree(){
       this.max_depth = 0;
       this.max_height = 0;
   }
    public int height(Tree.Node node) {
        return (node == null) ? -1 : node.height;
    }
   public void updateDepths(Tree.Node node, int depth) {
        if (node == null) return;
        node.depth = depth;
        if (this.max_depth < depth) {
            this.max_depth = depth;
        }
        updateDepths(node.left, depth + 1);
        updateDepths(node.right, depth + 1);
    }

}
