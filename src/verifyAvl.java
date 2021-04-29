public class verifyAvl {
    // returns whether the tree starting at the given AVLNode root
    // satisfies the BST Property, the AVL Balance Condition,
    // and contains the correct height information.
    public static boolean verifyAVL(AVLNode root) {
        if(root == null) {
            return true;
        } else {
            // Note: starts recursion with LONG.MIN_VALUE and LONG.MAX_VALUE,
            // so that the root trivially passes the BST condition.
            return verifyHeights(root) != null && isValidAVL(root, Long.MIN_VALUE, Long.MAX_VALUE);
        }
    }

    // helper used to determine  whether the tree starting at root
    // is a valid AVL tree.
    private static boolean isValidAVL(AVLNode root, long min, long max) {
        if(root == null) {
            return true;
        }
        // 1. checks to see if the node's key satisfies the BST condition
        if(root.key <= min || root.key >= max) {
            return false;
        }
        // 2. recurse to the left and right subtrees and check if they are balanced.
        return isValidAVL(root.left,min, root.key) && isValidAVL(root.right,root.key, max) && isBalanced(root);
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

      // returns the height of the root if the height information in the tree starting at root is correct.
      // returns null if the height information in the tree starting at root is incorrect.
      private static Integer verifyHeights(AVLNode root){
          if(root == null){
              return -1;
          } else {
            Integer leftRecurse = verifyHeights(root.left);
            Integer rightRecurse = verifyHeights(root.right);
            // check if the height info in L and R subtrees are correct
            if(leftRecurse == null || rightRecurse == null){
                return null;
            }
            int foundHeight = 1 + Math.max(leftRecurse, rightRecurse);
            if(foundHeight != root.height){
                // this node has incorrect height information
                return null;
            } else {
                return foundHeight;
            }
        }
      }
}