package Stacks;

import java.util.Arrays;

public class StackArray {

    private String[] stackArray;
    private int stackSize;
    private int topOfStack=-1;

    public StackArray(int stackSize){
        this.stackSize=stackSize;
        stackArray = new String[stackSize];

        Arrays.fill(stackArray,"-1");
    }

    public void displayStack(){
        if(topOfStack!=-1){
            for(int i=topOfStack; i>=0; i--){
                System.out.print(stackArray[i]+" ");
            }
            System.out.println();
        }else{
            System.out.println("Stacks.Stack is empty");
        }
    }

    public void push(String item){
        if(topOfStack+1 < stackSize){
            stackArray[++topOfStack] = item;
        }else{
            System.out.println("Stacks.Stack is full");
        }
    }

    public String pop(){
        if(topOfStack!=-1) {
            String temp = stackArray[topOfStack];
            stackArray[topOfStack--]="-1";
            return temp;
        }
        System.out.println("Stacks.Stack is empty");
        return "";
    }

    public String getTop() {
        if (topOfStack > -1) {
            return stackArray[topOfStack];

        }else{
            return "Stacks.Stack is empty";
        }
    }

    public void clearStack(){
        for(int i=topOfStack; i>=0; i--){
            stackArray[i]="-1";
        }
        topOfStack=-1;
    }

    public void pushMany(String multiple){
        String[] elements = multiple.split(" ");
        for(int i=0; i<elements.length; i++){
            push(elements[i]);
        }
    }

    public void printArray(){
        System.out.println("----------");
        for(int i = 0; i <stackSize ; i++){
            System.out.print("| " + i + " | ");
            System.out.println(stackArray[i] + " |");
            System.out.println("----------");
        }
    }

    public static void main(String[] args) {
        StackArray stack = new StackArray(5);
        stack.push("1");
        stack.push("2");
        stack.push("3");
        stack.push("4");

        stack.displayStack();
        stack.push("27");

        System.out.println(stack.getTop());
        stack.push("33");

        stack.clearStack();
        stack.displayStack();

        stack.push("11");
        System.out.println(stack.pop());
        stack.pop();
        System.out.println();

        stack = new StackArray(15);
        stack.pushMany("A B C D E F G");
        stack.printArray();
    }
}
