package Algorithm_Notes;

/**
 * Check if a binary tree is Trees.BST or not
 */
public class BST_Check {
    private treeNode root;
    public BST_Check(treeNode root) {
        this.root = root;
    }
    public boolean isBST_Rec() {
        return isBST_Rec(this.root,Integer.MIN_VALUE,Integer.MAX_VALUE);
    }
    public boolean isBST_Rec(treeNode root, int min, int max) {
        if (root == null) {
            return true;
        }
        if (root.key < min || root.key > max) {
            return false;
        }
        return isBST_Rec(root.left, min, root.key - 1) && isBST_Rec(root.right, root.key + 1, max);
    }
}


class treeNode {
    int key;
    treeNode left;
    treeNode right;

    public treeNode(int key) {
        this.key = key;
        left = null;
        right = null;
    }

    public void clear() {
        key = 0;
        left = right = null;
    }
}

