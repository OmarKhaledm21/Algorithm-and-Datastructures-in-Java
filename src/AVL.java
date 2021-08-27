import java.util.Scanner;

class avlNode
{
    int element;
    int h;  //for height  
    avlNode leftChild;
    avlNode rightChild;

    //default constructor to create null node  
    public avlNode()
    {
        leftChild = null;
        rightChild = null;
        element = 0;
        h = 0;
    }
    // parameterized constructor  
    public avlNode(int element)
    {
        leftChild = null;
        rightChild = null;
        this.element = element;
        h = 0;
    }
}

// create class ConstructAVLTree for constructing AVL Tree  
class ConstructAVLTree
{
    private avlNode root;

    //Constructor to set null value to the rootNode  
    public ConstructAVLTree()
    {
        root = null;
    }

    //create removeAll() method to make AVL Tree empty  
    public void removeAll()
    {
        root = null;
    }

    // create checkEmpty() method to check whether the AVL Tree is empty or not  
    public boolean checkEmpty()
    {
        return (root==null);
    }

    // create insertElement() to insert element to to the AVL Tree  
    public void insertElement(int element)
    {
        root = insertElement(element, root);
    }

    //create getHeight() method to get the height of the AVL Tree  
    private int getHeight(avlNode avlNode)
    {
        return avlNode == null ? -1 : avlNode.h;
    }

    //create maxNode() method to get the maximum height from left and right node  
    private int getMaxHeight(int leftNodeHeight, int rightNodeHeight)
    {
        return Math.max(leftNodeHeight, rightNodeHeight);
    }


    //create insertElement() method to insert data in the AVL Tree recursively   
    private avlNode insertElement(int element, avlNode parentNode)
    {
        //check whether the node is null or not  
        if (parentNode == null)
            parentNode = new avlNode(element);
            //insert a node in case when the given element is lesser than the element of the root node
        else if (element < parentNode.element)
        {
            parentNode.leftChild = insertElement( element, parentNode.leftChild );
            if( getHeight( parentNode.leftChild ) - getHeight( parentNode.rightChild ) == 2 )
                if( element < parentNode.leftChild.element )
                    parentNode = rotateWithLeftChild(parentNode);
                else
                    parentNode = doubleWithLeftChild(parentNode);
        }
        else if( element > parentNode.element )
        {
            parentNode.rightChild = insertElement( element, parentNode.rightChild );
            if( getHeight( parentNode.rightChild ) - getHeight( parentNode.leftChild ) == 2 )
                if( element > parentNode.rightChild.element)
                    parentNode = rotateWithRightChild(parentNode);
                else
                    parentNode = doubleWithRightChild(parentNode);
        }
        else
            ;  // if the element is already present in the tree, we will do nothing   
        parentNode.h = getMaxHeight( getHeight( parentNode.leftChild ), getHeight( parentNode.rightChild ) ) + 1;

        return parentNode;

    }

    // creating rotateWithLeftChild() method to perform rotation of binary tree node with left child        
    private avlNode rotateWithLeftChild(avlNode avlNode2)
    {
        avlNode avlNode1 = avlNode2.leftChild;
        avlNode2.leftChild = avlNode1.rightChild;
        avlNode1.rightChild = avlNode2;
        avlNode2.h = getMaxHeight( getHeight( avlNode2.leftChild ), getHeight( avlNode2.rightChild ) ) + 1;
        avlNode1.h = getMaxHeight( getHeight( avlNode1.leftChild ), avlNode2.h ) + 1;
        return avlNode1;
    }

    // creating rotateWithRightChild() method to perform rotation of binary tree node with right child        
    private avlNode rotateWithRightChild(avlNode avlNode1)
    {
        avlNode avlNode2 = avlNode1.rightChild;
        avlNode1.rightChild = avlNode2.leftChild;
        avlNode2.leftChild = avlNode1;
        avlNode1.h = getMaxHeight( getHeight( avlNode1.leftChild ), getHeight( avlNode1.rightChild ) ) + 1;
        avlNode2.h = getMaxHeight( getHeight( avlNode2.rightChild ), avlNode1.h ) + 1;
        return avlNode2;
    }

    //create doubleWithLeftChild() method to perform double rotation of binary tree node. This method first rotate the left child with its right child, and after that, node3 with the new left child  
    private avlNode doubleWithLeftChild(avlNode avlNode3)
    {
        avlNode3.leftChild = rotateWithRightChild( avlNode3.leftChild );
        return rotateWithLeftChild(avlNode3);
    }

    //create doubleWithRightChild() method to perform double rotation of binary tree node. This method first rotate the right child with its left child and after that node1 with the new right child  
    private avlNode doubleWithRightChild(avlNode avlNode1)
    {
        avlNode1.rightChild = rotateWithLeftChild( avlNode1.rightChild );
        return rotateWithRightChild(avlNode1);
    }

    //create getTotalNumberOfNodes() method to get total number of nodes in the AVL Tree  
    public int getTotalNumberOfNodes()
    {
        return getTotalNumberOfNodes(root);
    }
    private int getTotalNumberOfNodes(avlNode head)
    {
        if (head == null)
            return 0;
        else
        {
            int length = 1;
            length = length + getTotalNumberOfNodes(head.leftChild);
            length = length + getTotalNumberOfNodes(head.rightChild);
            return length;
        }
    }

    //create searchElement() method to find an element in the AVL Tree  
    public boolean searchElement(int element)
    {
        return searchElement(root, element);
    }

    private boolean searchElement(avlNode head, int element)
    {
        boolean check = false;
        while ((head != null) && !check)
        {
            int headElement = head.element;
            if (element < headElement)
                head = head.leftChild;
            else if (element > headElement)
                head = head.rightChild;
            else
            {
                check = true;
                break;
            }
            check = searchElement(head, element);
        }
        return check;
    }
    // create inorderTraversal() method for traversing AVL Tree in in-order form  
    public void inorderTraversal()
    {
        inorderTraversal(root);
    }
    private void inorderTraversal(avlNode head)
    {
        if (head != null)
        {
            inorderTraversal(head.leftChild);
            System.out.print(head.element+" ");
            inorderTraversal(head.rightChild);
        }
    }

    // create preorderTraversal() method for traversing AVL Tree in pre-order form  
    public void preorderTraversal()
    {
        preorderTraversal(root);
    }
    private void preorderTraversal(avlNode head)
    {
        if (head != null)
        {
            System.out.print(head.element+" ");
            preorderTraversal(head.leftChild);
            preorderTraversal(head.rightChild);
        }
    }

    // create postorderTraversal() method for traversing AVL Tree in post-order form  
    public void postorderTraversal()
    {
        postorderTraversal(root);
    }

    private void postorderTraversal(avlNode head)
    {
        if (head != null)
        {
            postorderTraversal(head.leftChild);
            postorderTraversal(head.rightChild);
            System.out.print(head.element+" ");
        }
    }
}

// create AVLTree class to construct AVL Tree  
public class AVL
{
    //main() method starts  
    public static void main(String[] args)
    {
        //creating Scanner class object to get input from user  
        Scanner sc = new Scanner(System.in);

        // create object of ConstructAVLTree class object for costructing AVL Tree  
        ConstructAVLTree obj = new ConstructAVLTree();

        // perform operation of AVL Tree using switch  
        do
        {
            System.out.println("\nSelect an operation:\n");
            System.out.println("1. Insert a node");
            System.out.println("2. Search a node");
            System.out.println("3. Get total number of nodes in AVL Tree");
            System.out.println("4. Is AVL Tree empty?");
            System.out.println("5. Remove all nodes from AVL Tree");
            System.out.println("6. Display AVL Tree in Post order");
            System.out.println("7. Display AVL Tree in Pre order");
            System.out.println("8. Display AVL Tree in In order");
            System.out.println("9. Exit");
            //get choice from user  
            int ch = sc.nextInt();
            switch (ch)
            {
                case 1 :
                    System.out.println("Please enter an element to insert in AVL Tree");
                    obj.insertElement( sc.nextInt() );
                    break;
                case 2 :
                    System.out.println("Enter integer element to search");
                    System.out.println(obj.searchElement( sc.nextInt() ));
                    break;
                case 3 :
                    System.out.println(obj.getTotalNumberOfNodes());
                    break;
                case 4 :
                    System.out.println(obj.checkEmpty());
                    break;
                case 5 :
                    obj.removeAll();
                    System.out.println("\nTree Cleared successfully");
                    break;
                case 6 :
                    System.out.println("\nDisplay AVL Tree in Post order");
                    obj.postorderTraversal();
                    break;
                case 7 :
                    System.out.println("\nDisplay AVL Tree in Pre order");
                    obj.preorderTraversal();
                    break;
                case 8 :
                    System.out.println("\nDisplay AVL Tree in In order");
                    obj.inorderTraversal();
                    break;
                case 9:
                    System.out.println("Exit");
                    sc.close();
                    return;
                default :
                    System.out.println("\n ");
                    break;
            }
        } while (true);
    }
}  