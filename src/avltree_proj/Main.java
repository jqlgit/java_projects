package avltree_proj;

public class Main {
    public static void main(String[] args) {


        // Create a new empty tree.
        SelfBalancingBST<Integer> avl_bst = new AVLTree<Integer>();

        // Insert 50 random integers.
        // Note how we need to capture the result of insert back into
        // the variable avl_bst because the post insertion root that is
        // returned might change because of the insertion


        for (int j = 48; j > 1; j--){
            avl_bst = avl_bst.remove(j);
        }
        //AVLTree<Integer> cur = (AVLTree<Integer>) avl_bst;
        //cur.printBreadth();

        // Now insert 50 integers in increasing order which would
        // cause a simple BST to become very tall but for our
        // self-balancing tree won't be too bad.

    }
}