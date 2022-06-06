package LinkedLists;

import java.util.Scanner;

class SkipNode{
    int key;
    SkipNode right;
    SkipNode down;

    public SkipNode(int key) {
        this(key, null, null);
    }

    public SkipNode(int key, SkipNode right, SkipNode down) {
        this.key = key;
        this.right = right;
        this.down = down;
    }
}

public class SkipLists {
    private SkipNode header;
    private int infinity;
    private SkipNode bottom = null;
    private SkipNode sentinel = null;


    public SkipLists(int inf) {
        infinity = inf;

        bottom = new SkipNode(0);
        bottom.right = bottom.down = bottom;

        sentinel = new SkipNode(infinity);
        sentinel.right = sentinel;

        header = new SkipNode(infinity, sentinel, bottom);
    }

    public void insert(int key) {
        SkipNode current = header;
        bottom.key = key;

        while (current != bottom) {
            while (current.key < key) {
                current = current.right;
            }
            /*  If gap size is 3 or at bottom level and must insert, then promote middle element */
            if (current.down.right.right.key < current.key) {
                current.right = new SkipNode(current.key, current.right, current.down.right.right);
                current.key = current.down.right.key;
            } else {
                current = current.down;
            }
        }

        if (header.right != sentinel) {
            header = new SkipNode(infinity, sentinel, header);
        }
    }

    public void makeEmpty() {
        header.right = sentinel;
        header.down = bottom;
    }

    public boolean isEmpty() {
        return (header.right == sentinel) && (header.down == bottom);
    }

    private int elementAt(SkipNode target) {
        return target == bottom ? 0 : target.key;
    }

    public void printList() {
        System.out.print("\nSkiplist = ");
        SkipNode current = header;

        while( current.down != bottom ) {
            current = current.down;
        }

        while (current.right != sentinel) {
            System.out.print(current.key +" ");
            current = current.right;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        SkipLists sl = new SkipLists(100_000_000);

        char ch;
        do
        {
            System.out.println("\nSkipList List Operations\n");
            System.out.println("1. insert");
            System.out.println("2. check empty");
            System.out.println("3. clear");

            int choice = scan.nextInt();
            switch (choice)
            {
                case 1 :
                    System.out.println("Enter integer element to insert");
                    sl.insert( scan.nextInt() );
                    break;
                case 2 :
                    System.out.println("Empty status = "+ sl.isEmpty());
                    break;
                case 3 :
                    System.out.println("List cleared\n");
                    sl.makeEmpty();
                    break;
                default :
                    System.out.println("Wrong Entry \n ");
                    break;
            }
            
            sl.printList();
            System.out.println("\nDo you want to continue (Type y or n) \n");
            ch = scan.next().charAt(0);

        } while (ch == 'Y'|| ch == 'y');
    }
}

