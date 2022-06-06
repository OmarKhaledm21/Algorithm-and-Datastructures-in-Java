package Queues;

import java.util.Arrays;

public class QueueArray {

    private String[] queueArray;
    private int queueSize;
    private int front,rear,numOfItems;

    public QueueArray(int queueSize){
        this.queueSize = queueSize;
        queueArray = new String[queueSize];
        numOfItems=0;
        rear=0;
        front=0;
        Arrays.fill(queueArray,"-1");
    }

    public void insert(String item){
        if(numOfItems+1 <=queueSize){
            queueArray[rear++]=item;
            numOfItems++;
        }else{
            System.out.println("Queues.Queue is full");
        }
    }

    public void remove(){
        if(numOfItems>0){
            queueArray[front++]="-1";
            numOfItems--;
        }else{
            System.out.println("Queues.Queue is empty");
        }
    }

    public void getFront(){
        System.out.println("Front item = "+queueArray[front]);
    }

    public void priorityInsert(String item){
        int i;
        if(numOfItems ==0 ){
            insert(item);
        }else{
            for(i = numOfItems-1; i>=0; i--){
                if(Integer.parseInt(item)> Integer.parseInt(queueArray[i])){
                    queueArray[i+1]=queueArray[i];
                }else break;
            }
            queueArray[i+1]=item;
            rear++;
            numOfItems++;
        }
    }

    public void displayTheQueue(){

        for(int n = 0; n < 61; n++)System.out.print("-");

        System.out.println();

        for(int n = 0; n < queueSize; n++){
            System.out.format("| %2s "+ " ", n);
        }

        System.out.println("|");

        for(int n = 0; n < 61; n++)System.out.print("-");

        System.out.println();

        for(int n = 0; n < queueSize; n++){
            if(queueArray[n].equals("-1")) System.out.print("|     ");
            else System.out.print(String.format("| %2s "+ " ", queueArray[n]));
        }

        System.out.println("|");

        for(int n = 0; n < 61; n++)System.out.print("-");
        System.out.println();

        // Number of spaces to put before the F
        int spacesBeforeFront = 3*(2*(front+1)-1);

        for(int k = 1; k < spacesBeforeFront; k++)System.out.print(" ");
        System.out.print("F");

        // Number of spaces to put before the R
        int spacesBeforeRear = (2*(3*rear)-1) - (spacesBeforeFront);

        for(int l = 0; l < spacesBeforeRear; l++)System.out.print(" ");
        System.out.print("R");
        System.out.println("\n");
    }

    public static void main(String[] args) {
        QueueArray queue = new QueueArray(10);
        queue.insert("10");
        queue.insert("15");
        queue.insert("11");

        queue.displayTheQueue();
        System.out.println("********************");

        queue.remove();
        queue.remove();
        queue.displayTheQueue();
        queue.getFront();

        QueueArray queue2 = new QueueArray(5);
        queue2.priorityInsert("10");
        queue2.priorityInsert("15");
        queue2.priorityInsert("11");

        queue2.displayTheQueue();
    }
}
