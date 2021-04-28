public class verifyAvl {
    // returns whether the tree starting at the given AVLNode root
    // satisfies the BST Property, the AVL Balance Condition,
    // and contains the correct height information.
    public static boolean verifyAVL(AVLNode root) {
        // starts recursion with LONG.MIN_VALUE and LONG.MAX_VALUE,
        // so that the root trivially passes the BST condition.
        return helper(root,Long.MIN_VALUE,Long.MAX_VALUE);
    }

    // helper used to determine  whether the tree starting at root
    // is a valid AVL tree and contains the correct height information.
    private static boolean helper(AVLNode root, long min, long max) {
        if(root == null) {
            return true;
        }
        // 1. Check to make sure the height of the current node is correct
        if(root.height != getHeight(root)){
            return false;
        }

        // 2. checks to see if the node's key satisfies the BST condition
        if(root.key <= min || root.key >= max) {
            return false;
        }

        // 3. recurse to the left and right subtrees and check if they are balanced.
        return helper(root.left,min, root.key) && helper(root.right,root.key, max) && isBalanced(root);
    }
    // checks whether the left and right subtrees of an AVLNode are balanced. NOTE: Assumes that the
    // height information stored in the nodes are valid.
    private static boolean isBalanced (AVLNode root){
        int leftTreeHeight;
        int rightTreeHeight;
        if(root.left == null){
            leftTreeHeight = 0;
        } else {
            leftTreeHeight= root.left.height;
        }
        if(root.right == null){
            rightTreeHeight = 0;
        } else {
            rightTreeHeight= root.right.height;

        }
        return Math.abs(rightTreeHeight - leftTreeHeight) <= 1;
    }

    // Returns the height of the tree  starting at AVLNode root.
    private static int getHeight(AVLNode root) {
        if(root == null){
            return -1;
        }else{
            return 1 + Math.max(getHeight(root.left), getHeight(root.right));
        }
    }
}