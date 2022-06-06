package Stacks;

class stackNode<T>{
    T data;
    stackNode<T> next;

    public stackNode(){
        data=null;
        next=null;
    }

    public stackNode(T item){
        data=item;
        next=null;
    }

    public void printNode(){
        System.out.println("Node data : "+this.data);
    }
}

public class Stack<T> {
    stackNode<T> topNode;

    public Stack(){
        topNode = null;
    }

    public boolean isEmpty(){
        return (topNode==null);
    }

    public void push(T item){
        stackNode<T> newNode = new stackNode<>(item);

        if (!isEmpty()) {
            newNode.next = topNode;
        }
        topNode = newNode;
    }

    public stackNode<T> pop(){
        stackNode<T> temp=topNode;
        topNode = topNode.next;
        temp.next=null;
        return temp;
    }

    public stackNode<T> peek(){
        return topNode;
    }

    public void displayStack(){
        stackNode<T> temp = topNode;
        while (temp !=null){
            temp.printNode();
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();

        stack.push(3);
        stack.push(8);
        stack.push(1);
        stack.displayStack();

        System.out.println();
        stack.pop().printNode();
        System.out.println();
        stack.displayStack();
    }
}
