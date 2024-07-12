package hello.hello_spring.service;

import java.io.IOException;


public class Main2 {

    private class Node{
        private char data;
        private Node next;

        public Node(char data){
            this.data = data;
            this.next = null;
        }

        public String toString(){
            return String.valueOf(this.data);
        }

    }

    class LinkedList{
        private Node head;
        private Node tail;
        private int size = 0;

        public void addFirst(char input){
            Node newNode = new Node(input);
            newNode.next=head;
            head = newNode;
            size++;
            if(head.next == null){
                tail = head;
            }
        }

        public void addLat(char input){
            Node newNode = new Node(input);

            if(size == 0){
                addFirst(input);
            }else{
                tail.next = null;
                tail = newNode;
                size++;
            }

        }

        Node node(int index){
            Node x = head;
            for(int i=0; i < index; i++){
                x = x.next;
            }
            return x;
        }

        public void add(int k, char input){
            if(k==0){
                addFirst(input);
            }else{
                Node temp1 = node(k-1);
                Node temp2 = temp1.next;
                Node newNode = new Node(input);

                temp1.next = newNode;
                newNode.next = temp2;
                size++;
                if(newNode.next ==null){
                    tail = newNode;
                }

            }
        }

        public char removeFirst(){
            Node temp = head;
            head=head.next;
            char returnData = temp.data;
            temp = null;
            size--;
            return returnData;
        }

        public char remove(int k){
            if(k==0){
                return removeFirst();
            }
            Node temp = node(k-1);
            Node todoDeleted = temp.next;
            temp.next = temp.next.next;
            char returnData = todoDeleted.data;
            if(todoDeleted == tail){
                tail = temp;
            }
            todoDeleted = null;
            size--;
            return returnData;
        }


    }
    public static void main(String[] args) throws IOException {

    }
}
