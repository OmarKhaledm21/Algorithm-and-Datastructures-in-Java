package Trees;

import Queues.Queue;

class treeNode{
    int key;
    treeNode left;
    treeNode right;

    public treeNode(int key){
        this.key = key;
        left=null;
        right=null;
    }

    public void clear(){
        key =0;
        left = right = null;
    }
}

public class BST {
    private treeNode root;

    public BST(){
        root=null;
    }

    public treeNode getRoot() {
        return root;
    }

    public boolean isEmpty(){
        return (root==null);
    }

    public boolean search(int data){
        treeNode current = root;
        while (current!=null){
            if(current.key == data){
                return true;
            }else if(current.key > data){
                current = current.left;
            }else{
                current = current.right;
            }
        }
        return false;
    }

    public boolean searchRecursive(int data){
        return searchRecursive(root , data);
    }

    public boolean searchRecursive(treeNode node,int data){
        if(node == null){
            return false;
        }else if(node.key == data){
            return true;
        }else if(node.key > data){
            return searchRecursive(node.left,data);
        }else{
            return searchRecursive(node.right,data);
        }
    }

    public void insert(int data){
        treeNode current=root;
        treeNode tailCurrent=null;
        treeNode newNode = new treeNode(data);

        if(root == null){
            root = newNode;
        }else{
            while (current!=null){
                tailCurrent=current;
                if(current.key == data){
                    System.out.println("Item already in tree!");
                    return;
                }else{
                    if(current.key > data){
                        current = current.left;
                    }else{
                        current = current.right;
                    }
                }
            }
         if(tailCurrent.key > data){
             tailCurrent.left = newNode;
         }else{
             tailCurrent.right = newNode;
         }

        }
    }


    public void levelOrderTraversal(){
        Queue<treeNode> q = new Queue<>();
        if(isEmpty()){
            System.out.println("Tree is empty!");
            return;
        }
        q.enqueue(root);
        while (!q.isEmpty()){
            treeNode cur = q.front();
            System.out.println(cur.key +" ");
            if(cur.left!=null){
                q.enqueue(cur.left);
            }
            if(cur.right!=null){
                q.enqueue(cur.right);
            }
            q.dequeue();
        }
    }

    public void preOrderTraversal(){
        preOrderTraversal(root);
    }

    public void preOrderTraversal(treeNode node){
        if(node!=null){
            System.out.println(node.key +" ");
            preOrderTraversal(node.left);
            preOrderTraversal(node.right);
        }
    }

    public void postOrderTraversal(){
        postOrderTraversal(root);
    }

    public void postOrderTraversal(treeNode node){
        if(node!=null){
            postOrderTraversal(node.left);
            postOrderTraversal(node.right);
            System.out.println(node.key +" ");
        }
    }

    public void inOrderTraversal(){
        inOrderTraversal(root);
    }

    public void inOrderTraversal(treeNode node){
        if (node!=null){
            inOrderTraversal(node.left);
            System.out.println(node.key +" ");
            inOrderTraversal(node.right);
        }
    }

    public int treeHeight(){
        int height = treeHeight(root);
        return height;
    }

    public int treeHeight(treeNode node){
        if(node == null){
            return 0;
        }
        return 1+ Math.max(treeHeight(node.left),treeHeight(node.right));
    }

    public int treeNodeCount(){
        int nodeCount = treeNodeCount(root);
        return nodeCount;
    }

    public int treeNodeCount(treeNode node){
        if(node == null) {
            return 0;
        }else{
            return 1+ treeNodeCount(node.left) + treeNodeCount(node.right);
        }
    }

    public int treeLeavesCount(){
        int leavesCount = treeLeavesCount(root);
        return leavesCount;
    }

    public int treeLeavesCount(treeNode node){
        if(node==null){
            return 0;
        }else if(node.left==null && node.right==null) {
            return 1;
        }else{
            return treeNodeCount(node.right)+treeNodeCount(node.left);
        }
    }

    public void clearTree(){
        if(root==null){
            System.out.println("Tree is empty!");
        }else {
            root = null;
        }
    }



    public boolean remove(int key) {

        // Start at the top of the tree
        treeNode current = root;
        treeNode parent = root;

        // When searching for a Node this will
        // tell us whether to search to the
        // right or left
        boolean isItALeftChild = true;

        // While we haven't found the Node
        // keep looking
        while (current.key != key) {
            parent = current;
            // If we should search to the left
            if (key < current.key) {
                isItALeftChild = true;
                // Shift the focus Node to the left child
                current = current.left;
            } else {
                // Greater than focus node so go to the right
                isItALeftChild = false;
                // Shift the focus Node to the right child
                current = current.right;
            }

            // The node wasn't found
            if (current == null)
                return false;

        }

        // If Node doesn't have children delete it
        if (current.left == null && current.right == null) {
            // If root delete it
            if (current == root){
                root = null;
            }else if (isItALeftChild) {
                parent.left = null;
                // Vice versa for the right child
            } else {
                parent.right = null;
            }
        } else if (current.right == null) {
            if (current == root)
                root = current.left;

                // If focus Node was on the left of parent
                // move the focus Nodes left child up to the
                // parent node

            else if (isItALeftChild)
                parent.left = current.left;

                // Vice versa for the right child
            else
                parent.right = current.left;
        } else if (current.left == null) {

            if (current == root)
                root = current.right;

                // If focus Node was on the left of parent
                // move the focus Nodes right child up to the
                // parent node

            else if (isItALeftChild)
                parent.left = current.right;

                // Vice versa for the left child

            else
                parent.right = current.right;

        }
        // Two children so I need to find the deleted nodes
        // replacement
        else {

            treeNode replacement = getReplacementNode(current);

            // If the current is root replace root
            // with the replacement

            if (current == root)
                root = replacement;

                // If the deleted node was a left child
                // make the replacement the left child

            else if (isItALeftChild)
                parent.left = replacement;

                // Vice versa if it was a right child

            else
                parent.right = replacement;

            replacement.left = current.left;

        }

        return true;

    }

    public treeNode getReplacementNode(treeNode node) {

        treeNode Parent = node;
        treeNode replacement = node;

        treeNode traverserNode = node.right;

        // While there are no more left children

        while (traverserNode != null) {

            Parent = replacement;

            replacement = traverserNode;

            traverserNode = traverserNode.left;

        }

        // If the replacement isn't the right child
        // move the replacement into the parents
        // leftChild slot and move the replaced nodes
        // right child into the replacements rightChild

        if (replacement != node.right) {

            Parent.left = replacement.right;
            replacement.right = node.right;

        }

        return replacement;

    }

    public static void main(String[] args) {
        BST bst = new BST();

        bst.insert(10);
        bst.insert(7);
        bst.insert(9);
        bst.insert(6);
        bst.insert(4);
        bst.insert(22);
        bst.insert(2334);

        bst.levelOrderTraversal();
        System.out.println("**************************");
        bst.preOrderTraversal();
        System.out.println("**************************");
        bst.postOrderTraversal();
        System.out.println("**************************");
        bst.inOrderTraversal();
        System.out.println("**************************");
        //bst.clearTree();
        //bst.levelOrderTraversal();

        //System.out.println(bst.treeHeight());
        bst.remove(10);
        bst.remove(22);
        System.out.println("After removal: ");
        bst.levelOrderTraversal();
        System.out.println("**************************");
        //bst.preOrderTraversal();
        System.out.println("**************************");
        //bst.postOrderTraversal();
        System.out.println("**************************");
       // bst.inOrderTraversal();
        System.out.println("**************************");
    }
}





