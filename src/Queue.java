class queueNode<T>{
    T data;
    queueNode<T> next;

    public queueNode(){
        next=null;
        data=null;
    }

    public queueNode(T data){
        next=null;
        this.data=data;
    }

    public void printNode(){
        System.out.println("Node data = "+this.data);
    }
}

public class Queue<T>{
    private int length;
    private queueNode<T> frontPtr,rearPtr;

    public Queue(){
        length=0;
        frontPtr=null;
        rearPtr=null;
    }

    public boolean isEmpty(){
        return (frontPtr==null);
    }

    public void enqueue(T item){
        queueNode<T> newNode = new queueNode<>(item);
        if(isEmpty()){
            frontPtr=rearPtr=newNode;
        }else{
            rearPtr.next=newNode;
            rearPtr=newNode;
        }
        length++;
    }

    public void dequeue(){
        if(isEmpty()){
            System.out.println("Queue is empty!");
        }else if(length==1){
            frontPtr=rearPtr=null;
        }else{
            queueNode<T> temp = frontPtr;
           frontPtr=frontPtr.next;
            temp.next=null;
        }
        length--;
    }

    public queueNode<T> front(){
        return frontPtr;
    }

    public queueNode<T> rear(){
        return rearPtr;
    }

    public void search(T item){
        queueNode<T> curr =frontPtr;
        while (curr!= null){
            if(curr.data == item){
                System.out.println("Found item!");
                return;
            }
            curr = curr.next;
        }
        System.out.println("Item not found!");
    }

    public void printQueue(){
        if(!isEmpty()) {
            queueNode<T> curr = frontPtr;
            while (curr != null) {
                curr.printNode();
                curr = curr.next;
            }
        }else{
            System.out.println("Queue is empty!");
        }
    }

    public void clearQueue(){
        while (frontPtr!=null){
            dequeue();
        }
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(5);
        queue.enqueue(8);
        queue.dequeue();
        queue.enqueue(9);
        queue.enqueue(10);

        queue.printQueue();

        queue.search(5);
    }


}
