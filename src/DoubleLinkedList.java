class node<T>{
    public T data;
    public node<T> next;
    public node<T> previous;

    public node(){
        this.next=null;
        this.previous=null;
        this.data=null;
    }

    public node(T data){
        this.data = data;
        this.next=null;
        this.previous=null;
    }

    public void printNode(){
        System.out.println("Node data : " + data);
    }
}

public class DoubleLinkedList<T>{
    private node<T> headNode;
    private node<T> tailNode;

    public DoubleLinkedList(){
        headNode=null;
        tailNode=null;
    }

    public boolean isEmpty(){
        return (headNode==null);
    }

    public node<T> getHeadNode(){
        return headNode;
    }

    public node<T> getTailNode(){
        return tailNode;
    }

    public void push_front(T item){
        node<T> newNode = new node<>(item);
        if(isEmpty()){
            headNode = tailNode = newNode;
        }else{
            newNode.next=headNode;
            headNode.previous=newNode;
            headNode=newNode;
        }
    }

    public void push_back(T item){
        node<T> newNode = new node<>(item);
        if(isEmpty()){
            headNode=tailNode=newNode;
        }else{
            tailNode.next = newNode;
            newNode.previous = tailNode;
            tailNode=newNode;
        }
    }

    public boolean find(T item){
        node<T> temp = headNode;
        while(temp!=null){
            if(temp.data==item){
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    public node<T> remove(T item){
        node<T> temp = headNode;
        while(temp != null){
            if(temp.data == item){
                break;
            }
            temp = temp.next;
        }
        if(temp!= null) {
            if (temp == headNode) {
                headNode = headNode.next;
                headNode.previous.next=null;
                headNode.previous=null;
            } else if (temp == tailNode) {
                tailNode = tailNode.previous;
                tailNode.next.previous=null;
                tailNode.next=null;
            } else{
                temp.previous.next = temp.next;
                temp.next.previous = temp.previous;
            }
        }
        return temp;
    }

    public void insertAfter(T key, T item){
        node<T> newNode = new node<>(item);

        if(isEmpty()){
            System.out.println("List is empty item is inserted in front!");
            headNode = tailNode = newNode;
        }else{
            if(headNode.data == key){
                newNode.previous = headNode;
                newNode.next = headNode.next;
                headNode.next.previous=newNode;
                headNode.next=newNode;
            }else if(tailNode.data == key){
                push_back(item);
            }else{
                node<T> temp = headNode;
                while (temp != null){
                    if(temp.data == key){
                        break;
                    }
                    temp = temp.next;
                }
                if(temp!= null){
                    newNode.previous=temp;
                    newNode.next=temp.next;
                    temp.next.previous = newNode;
                    temp.next = newNode;
                }
            }
        }
    }

    public void display(){
        if(!isEmpty()){
            node<T> temp = headNode;
            while(temp != tailNode.next){
                temp.printNode();
                temp = temp.next;
            }
        }else{
            System.out.println("List is empty!");
        }
    }

    public void displayReverse(){
        if(!isEmpty()){
            node<T> temp = tailNode;
            while (temp!=headNode.previous){
                temp.printNode();
                temp = temp.previous;
            }
        }else{
            System.out.println("List is empty!");
        }
    }

    public void clear(){
        if(!isEmpty()){
            while (this.headNode!=null){
                this.headNode = this.headNode.next;
            }
            tailNode=headNode=null;
        }
    }

    public static void main(String[] args) {
        DoubleLinkedList<String> doubleLinkedList = new DoubleLinkedList<>();
        doubleLinkedList.push_front("OMAR");
        doubleLinkedList.push_front("JAKE");
        doubleLinkedList.push_back("JOHN");
        doubleLinkedList.push_back("ROBERT");
        doubleLinkedList.push_back("ROBERTO");
        doubleLinkedList.push_back("KITTY");

        doubleLinkedList.display();
        System.out.println("******************");
        doubleLinkedList.remove("ROBERT");
        doubleLinkedList.insertAfter("OMAR","ANAS");
        doubleLinkedList.displayReverse();

        doubleLinkedList.clear();
        System.out.println("******************");

        doubleLinkedList.display();

        System.out.println("******************");

        doubleLinkedList.displayReverse();


    }
}
