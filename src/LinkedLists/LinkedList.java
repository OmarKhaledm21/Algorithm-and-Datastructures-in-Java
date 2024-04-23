package LinkedLists;

import java.util.Iterator;
import java.util.Scanner;

class Link{
    public String data;
    public Link next;

    public Link(String data){
        this.data = data;
        next=null;
    }
}

public class LinkedList  {

    public Link firstLink;

    LinkedList(){
        firstLink = null;
    }

    public boolean isEmpty(){
        return (firstLink==null);
    }

    public void insertFirst(String item){
        Link newLink = new Link(item);
        newLink.next=firstLink;
        firstLink=newLink;
    }

    public Link removeFirst(){
        Link linkRef = firstLink;
        if(!isEmpty()){
            firstLink = firstLink.next;
        }else{
            System.out.println("Empty LinkedLists.LinkedList");
        }
        return linkRef;
    }

    public void insertLast(String item){
        Link newLink = new Link(item);
        if(isEmpty()){
            firstLink=newLink;
        }else{
            Link temp = firstLink;
            while(temp.next!=null){
                temp = temp.next;
            }
            temp.next=newLink;
        }
    }

    public Link removeLast(){
        Link temp=firstLink.next;
        Link tail = firstLink;
        if(isEmpty()){
            System.out.println("List is empty!");
            return null;
        }else{
            if(temp == null){
                firstLink=null;
                return tail;
            }else {
                while (temp.next != null) {
                    tail = temp;
                    temp = temp.next;
                }
            }
            tail.next = null;
            return temp;
        }
    }

    public Link find(String item){
        Link temp = firstLink;
        if(!isEmpty()){
            while (temp.data!=item){
                if(temp.next==null){
                    return null;
                }else{
                    temp = temp.next;
                }
            }
        }else{
            System.out.println("Empty List!");
        }
        return temp;
    }

    public Link remove(String item){
        Link curr =firstLink;
        Link prev = firstLink;

        while(!curr.data.equals(item)){
            if(curr.next!=null){
                prev = curr;
                curr= curr.next;
            }else{
                return null;
            }
        }
        if(curr == firstLink){
            firstLink = firstLink.next;
        }else{
            prev.next = curr.next;
        }
        return curr;
    }

    public void display(){
        Link temp =firstLink;
        if(isEmpty()){
            System.out.println("List is empty");
        }else {
            while (temp.next != null) {
                System.out.println(temp.data);
                temp = temp.next;
            }
            System.out.println(temp.data);
        }
    }

    public void clearList(){
        Link temp =firstLink;
        Link tail=null;
        if(isEmpty()){
            System.out.println("List is empty");
        }else {
            while (temp.next != null) {
                tail = temp;
                temp = temp.next;
                tail.next=null;
                firstLink=temp;
            }
            firstLink=null;
        }
    }


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        LinkedList ll = new LinkedList();

        ll.insertFirst("11");
        ll.insertFirst("12");
        ll.insertFirst("13");
        ll.remove("12");
        ll.display();

        System.out.println(ll.find("12"));

        input.close();
    }


}

/*
        ll.insertLast("10");
        ll.insertLast("110");
        ll.insertFirst("231");
        ll.removeLast();
        ll.display();
        System.out.println("*******************");

        ll.insertLast("11011");
        ll.insertFirst("231132");
        ll.display();

        System.out.println("*******************");
        ll.clearList();
        ll.display();

  */
