import java.util.ArrayList;
import java.util.Scanner;

// considering that you know what are red-black trees here is the implementation in java for insertion and traversal.
// RedBlackTree class. This class contains subclass for node
// as well as all the functionalities of RedBlackTree such as - rotations, insertion and
// inoredr traversal
class redBlackNode {
    int data;
    redBlackNode left;
    redBlackNode right;
    char colour;
    redBlackNode parent;

    redBlackNode(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.colour = 'R';
        this.parent = null;
    }
}

public class RedBlackTree {
    public redBlackNode root;

    public RedBlackTree() {
        root = null;
    }

    redBlackNode rotateLeft(redBlackNode node) {
        redBlackNode x = node.right;
        redBlackNode y = x.left;
        x.left = node;
        node.right = y;
        node.parent = x; // parent resetting is also important.
        if(y!=null)
            y.parent = node;
        return(x);
    }

    redBlackNode rotateRight(redBlackNode node) {
        redBlackNode x = node.left;
        redBlackNode y = x.right;
        x.right = node;
        node.left = y;
        node.parent = x;
        if(y!=null)
            y.parent = node;
        return(x);
    }
    // these are some flags.
    // Respective rotations are performed during traceback.
    // rotations are done if flags are true.
    boolean ll = false;
    boolean rr = false;
    boolean lr = false;
    boolean rl = false;
    // helper function for insertion. Actually this function performs all tasks in single pass only.
    redBlackNode insertHelp(redBlackNode root, int data) {
        // f is true when RED RED conflict is there.
        boolean DoubleRedFlag=false;

        //recursive calls to insert at proper position according to BST properties.
        if(root==null)
            return(new redBlackNode(data));
        else if(data<root.data) {
            root.left = insertHelp(root.left, data);
            root.left.parent = root;
            if(root!=this.root) {
                if(root.colour=='R' && root.left.colour=='R')
                    DoubleRedFlag = true;
            }
        }
        else {
            root.right = insertHelp(root.right,data);
            root.right.parent = root;
            if(root!=this.root)
            {
                if(root.colour=='R' && root.right.colour=='R')
                    DoubleRedFlag = true;
            }
            // at the same time of insertion, we are also assigning parent nodes
            // also we are checking for RED RED conflicts
        }

        // now lets rotate.
        if(this.ll) // for left rotate.
        {
            root = rotateLeft(root);
            root.colour = 'B';
            root.left.colour = 'R';
            this.ll = false;
        }
        else if(this.rr) // for right rotate
        {
            root = rotateRight(root);
            root.colour = 'B';
            root.right.colour = 'R';
            this.rr = false;
        }
        else if(this.rl) // for right and then left
        {
            root.right = rotateRight(root.right);
            root.right.parent = root;
            root = rotateLeft(root);
            root.colour = 'B';
            root.left.colour = 'R';

            this.rl = false;
        }
        else if(this.lr) // for left and then right.
        {
            root.left = rotateLeft(root.left);
            root.left.parent = root;
            root = rotateRight(root);
            root.colour = 'B';
            root.right.colour = 'R';
            this.lr = false;
        }
        // when rotation and recolouring is done flags are reset.
        // Now lets take care of RED RED conflict
        if(DoubleRedFlag) {
            if(root.parent.right == root) // to check which child is the current node of its parent
            {
                if(root.parent.left==null || root.parent.left.colour=='B') // case when parent's sibling is black
                {// perform certaing rotation and recolouring. This will be done while backtracking. Hence setting up respective flags.
                    if(root.left!=null && root.left.colour=='R')
                        this.rl = true;
                    else if(root.right!=null && root.right.colour=='R')
                        this.ll = true;
                }
                else // case when parent's sibling is red
                {
                    root.parent.left.colour = 'B';
                    root.colour = 'B';
                    if(root.parent!=this.root)
                        root.parent.colour = 'R';
                }
            }
            else {
                if(root.parent.right==null || root.parent.right.colour=='B') {
                    if(root.left!=null && root.left.colour=='R')
                        this.rr = true;
                    else if(root.right!=null && root.right.colour=='R')
                        this.lr = true;
                }
                else {
                    root.parent.right.colour = 'B';
                    root.colour = 'B';
                    if(root.parent!=this.root)
                        root.parent.colour = 'R';
                }
            }
            DoubleRedFlag = false;
        }
        return(root);
    }

    public void insert(int data) {
        if(this.root==null) {
            this.root = new redBlackNode(data);
            this.root.colour = 'B';
        }
        else {
            this.root = insertHelp(this.root, data);
        }
    }

    void inorderTraversalHelper(redBlackNode node) {
        if(node!=null) {
            inorderTraversalHelper(node.left);
            System.out.print( node.data+" ");
            inorderTraversalHelper(node.right);
        }
    }

    public void inorderTraversal() {
        inorderTraversalHelper(this.root);
    }

    public static void main(String[] args) {
        // let us try to insert some data into tree and try to visualize the tree as well as traverse.
        RedBlackTree t = new RedBlackTree();
        Scanner scanner = new Scanner(System.in);
        int[] sample = {77,5,23,47,21 ,23 ,99,120,29};
        for(int i=0; i<sample.length; i++){
            t.insert(sample[i]);
        }
        t.inorderTraversal();
    }
}