package Trees;

import java.util.Scanner;

class AVLTreeNode {
    int element;
    int h;  //for height  
    AVLTreeNode leftChild;
    AVLTreeNode rightChild;

    //default constructor to create null node  
    public AVLTreeNode() {
        leftChild = null;
        rightChild = null;
        element = 0;
        h = 0;
    }

    // parameterized constructor
    public AVLTreeNode(int element) {
        leftChild = null;
        rightChild = null;
        this.element = element;
        h = 0;
    }
}

// create class Trees.ConstructAVLTree for constructing Trees.AVL Tree
class ConstructAVLTree {
    private AVLTreeNode root;

    //Constructor to set null value to the rootNode  
    public ConstructAVLTree() {
        root = null;
    }

    //create removeAll() method to make Trees.AVL Tree empty
    public void removeAll() {
        root = null;
    }

    // create checkEmpty() method to check whether the Trees.AVL Tree is empty or not
    public boolean checkEmpty() {
        return (root == null);
    }

    //create getHeight() method to get the height of the Trees.AVL Tree
    private int getHeight(AVLTreeNode AVLTreeNode) {
        return AVLTreeNode == null ? -1 : AVLTreeNode.h;
    }

    //create getMaxNode() method to get the maximum height from left and right node
    private int getMaxNode(int leftNodeHeight, int rightNodeHeight) {
        return Math.max(leftNodeHeight, rightNodeHeight);
    }

    // create insertElement() to insert element to to the Trees.AVL Tree
    public void insertElement(int element) {
        root = insertElement(element, root);
    }

    //create insertElement() method to insert data in the Trees.AVL Tree recursively
    private AVLTreeNode insertElement(int element, AVLTreeNode parentNode) {
         if (parentNode == null) {
            parentNode = new AVLTreeNode(element);
        } else if (element < parentNode.element) {
            parentNode.leftChild = insertElement(element, parentNode.leftChild);
            if (getHeight(parentNode.leftChild) - getHeight(parentNode.rightChild) > 1)
                if (element < parentNode.leftChild.element) {
                    parentNode = RightRotation(parentNode);
                    System.out.println("RIGHT ROTATION");
                } else {
                    parentNode = LeftRightRotation(parentNode);
                    System.out.println("LEFT RIGHT ROTATION");
                }
        } else if (element > parentNode.element) {
            parentNode.rightChild = insertElement(element, parentNode.rightChild);
            if (getHeight(parentNode.rightChild) - getHeight(parentNode.leftChild) == 2)
                if (element > parentNode.rightChild.element)
                    parentNode = LeftRotation(parentNode);
                else
                    parentNode = RightLeftRotation(parentNode);
        } else {
            System.out.println("Cannot insert duplicate elements!");
        }
        parentNode.h = getMaxNode(getHeight(parentNode.leftChild), getHeight(parentNode.rightChild)) + 1;

        return parentNode;

    }

    public int removeElement(int target) {
        return removeElement(this.root, target);
    }

    private int removeElement(AVLTreeNode root, int target) {

        return 0;
    }

    private AVLTreeNode RightRotation(AVLTreeNode subTreeRoot) {
        AVLTreeNode newSubTreeRoot = subTreeRoot.leftChild;
        subTreeRoot.leftChild = newSubTreeRoot.rightChild;
        newSubTreeRoot.rightChild = subTreeRoot;
        subTreeRoot.h = getMaxNode(getHeight(subTreeRoot.leftChild), getHeight(subTreeRoot.rightChild)) + 1;
        newSubTreeRoot.h = getMaxNode(getHeight(newSubTreeRoot.leftChild), subTreeRoot.h) + 1;

        return newSubTreeRoot;
    }

    private AVLTreeNode LeftRotation(AVLTreeNode subTreeRoot) {
        AVLTreeNode newSubTreeRoot = subTreeRoot.rightChild;
        subTreeRoot.rightChild = newSubTreeRoot.leftChild;
        newSubTreeRoot.leftChild = subTreeRoot;
        subTreeRoot.h = getMaxNode(getHeight(subTreeRoot.leftChild), getHeight(subTreeRoot.rightChild)) + 1;
        newSubTreeRoot.h = getMaxNode(getHeight(newSubTreeRoot.rightChild), subTreeRoot.h) + 1;
        return newSubTreeRoot;
    }

    private AVLTreeNode LeftRightRotation(AVLTreeNode subTreeRoot) {
        subTreeRoot.leftChild = LeftRotation(subTreeRoot.leftChild);
        return RightRotation(subTreeRoot);
    }

    private AVLTreeNode RightLeftRotation(AVLTreeNode subTreeRoot) {
        subTreeRoot.rightChild = RightRotation(subTreeRoot.rightChild);
        return LeftRotation(subTreeRoot);
    }

    //create getTotalNumberOfNodes() method to get total number of nodes in the Trees.AVL Tree
    public int getTotalNumberOfNodes() {
        return getTotalNumberOfNodes(root);
    }

    private int getTotalNumberOfNodes(AVLTreeNode head) {
        if (head == null)
            return 0;
        else {
            int length = 1;
            length += getTotalNumberOfNodes(head.leftChild);
            length += getTotalNumberOfNodes(head.rightChild);
            return length;
        }
    }

    //create searchElement() method to find an element in the Trees.AVL Tree
    public boolean searchElement(int element) {
        return searchElement(root, element);
    }

    private boolean searchElement(AVLTreeNode head, int element) {
        boolean check = false;
        while ((head != null) && !check) {
            int headElement = head.element;
            if (element < headElement) {
                head = head.leftChild;
            } else if (element > headElement) {
                head = head.rightChild;
            } else {
                check = true;
            }
            //check = searchElement(head, element);
        }
        return check;
    }

    public boolean searchElementRecursive(int target) {
        return searchElementRecursive(this.root, target);
    }

    public boolean searchElementRecursive(AVLTreeNode root, int target) {
        if (root == null) {
            return false;
        } else if (root.element == target) {
            return true;
        } else if (root.element > target) {
            return searchElementRecursive(root.leftChild, target);
        } else {
            return searchElementRecursive(root.rightChild, target);
        }
    }

    // create inorderTraversal() method for traversing Trees.AVL Tree in in-order form
    public void inorderTraversal() {
        inorderTraversal(root);
    }

    private void inorderTraversal(AVLTreeNode head) {
        if (head != null) {
            inorderTraversal(head.leftChild);
            System.out.print(head.element + " ");
            inorderTraversal(head.rightChild);
        }
    }

    // create preorderTraversal() method for traversing Trees.AVL Tree in pre-order form
    public void preorderTraversal() {
        preorderTraversal(root);
    }

    private void preorderTraversal(AVLTreeNode head) {
        if (head != null) {
            System.out.print(head.element + " ");
            preorderTraversal(head.leftChild);
            preorderTraversal(head.rightChild);
        }
    }

    // create postorderTraversal() method for traversing Trees.AVL Tree in post-order form
    public void postorderTraversal() {
        postorderTraversal(root);
    }

    private void postorderTraversal(AVLTreeNode head) {
        if (head != null) {
            postorderTraversal(head.leftChild);
            postorderTraversal(head.rightChild);
            System.out.print(head.element + " ");
        }
    }
}

// create AVLTree class to construct Trees.AVL Tree
public class AVL {
    //main() method starts  
    public static void main(String[] args) {
        //creating Scanner class object to get input from user  
        Scanner sc = new Scanner(System.in);

        // create object of Trees.ConstructAVLTree class object for costructing Trees.AVL Tree
        ConstructAVLTree obj = new ConstructAVLTree();

        // perform operation of Trees.AVL Tree using switch
        do {
            System.out.println("\nSelect an operation:\n");
            System.out.println("1. Insert a node");
            System.out.println("2. Search a node");
            System.out.println("3. Remove a node");
            System.out.println("3. Get total number of nodes in Trees.AVL Tree");
            System.out.println("4. Is Trees.AVL Tree empty?");
            System.out.println("5. Remove all nodes from Trees.AVL Tree");
            System.out.println("6. Display Trees.AVL Tree in Post order");
            System.out.println("7. Display Trees.AVL Tree in Pre order");
            System.out.println("8. Display Trees.AVL Tree in In order");
            System.out.println("9. Exit");
            //get choice from user  
            int ch = sc.nextInt();
            switch (ch) {
                case 1:
                    System.out.println("Please enter an element to insert in Trees.AVL Tree");
                    obj.insertElement(sc.nextInt());
                    break;
                case 2:
                    System.out.println("Enter integer element to search");
                    System.out.println(obj.searchElement(sc.nextInt()));
                    break;
                case 3:
                    System.out.println("Enter the element you want to remove");
                    System.out.println(obj.removeElement(sc.nextInt()));
                    break;
                case 4:
                    System.out.println(obj.getTotalNumberOfNodes());
                    break;
                case 5:
                    System.out.println(obj.checkEmpty());
                    break;
                case 6:
                    obj.removeAll();
                    System.out.println("\nTree Cleared successfully");
                    break;
                case 7:
                    System.out.println("\nDisplay Trees.AVL Tree in Post order");
                    obj.postorderTraversal();
                    break;
                case 8:
                    System.out.println("\nDisplay Trees.AVL Tree in Pre order");
                    obj.preorderTraversal();
                    break;
                case 9:
                    System.out.println("\nDisplay Trees.AVL Tree in In order");
                    obj.inorderTraversal();
                    break;
                case 10:
                    System.out.println("Exit");
                    sc.close();
                    return;
                default:
                    System.out.println("\n ");
                    break;
            }
        } while (true);
    }
}  