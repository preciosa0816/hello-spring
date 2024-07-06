package hello.hello_spring.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;


class LinkedList {

    private Node head;
    private Node tail;
    private int size = 0;

    private class Node {
        private char data;
        private Node next;

        public Node(char input) {
            this.data = input;
            this.next = null;
        }

        public String toString() {
            return String.valueOf(this.data);
        }
    }

    public void addFirst(char input) {
        Node newNode = new Node(input);
        newNode.next = head;
        head = newNode;
        size++;
        if (head.next == null) {
            tail = head;
        }
    }

    public void addLast(char input) {
        Node newNode = new Node(input);
        if (size == 0) {
            addFirst(input);
        } else {
            tail.next = newNode;
            tail = newNode;
            size++;
        }
    }

    Node node(int index) {
        Node x = head;
        for (int i = 0; i < index; i++) {
            x = x.next;
        }
        return x;
    }

    public void add(int k, char input) {
        if (k == 0) {
            addFirst(input);
        } else {
            Node temp1 = node(k - 1);
            Node temp2 = temp1.next;
            Node newNode = new Node(input);
            temp1.next = newNode;
            newNode.next = temp2;
            size++;
            if (newNode.next == null) {
                tail = newNode;
            }
        }
    }

    public String toString() {
        if (head == null) {
            return "";
        }
        Node temp = head;

        StringBuilder sb = new StringBuilder();
        while (temp.next != null) {
            sb.append(temp.data);
            temp = temp.next;
        }
        sb.append(temp.data);
        return sb.toString();
    }

    public char removeFirst() {
        Node temp = head;
        head = head.next;
        char returnData = temp.data;
        temp = null;
        size--;
        return returnData;
    }

    public char remove(int k) {
        if (k == 0) {
            return removeFirst();
        }
        Node temp = node(k - 1);
        Node todoDeleted = temp.next;
        temp.next = temp.next.next;
        char returnData = todoDeleted.data;
        if (todoDeleted == tail) {
            tail = temp;
        }
        todoDeleted = null;
        size--;
        return returnData;
    }


    public int size() {
        return size;
    }


}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String word = br.readLine();
        int count = Integer.parseInt(br.readLine());

        LinkedList test = new LinkedList();
        for (char c : word.toCharArray()) {
            test.addLast(c);
        }

        int cursor = word.length();
        for (int i = 0; i < count; i++) {
            String next = br.readLine();
            if (next.equals("L")) {
                if (cursor > 0) cursor--;
            } else if (next.equals("D")) {
                if (cursor < test.size()) cursor++;
            } else if (next.equals("B")) {
                if (cursor > 0) {
                    test.remove(cursor - 1);
                    cursor--;
                }
            } else if (next.contains("P")) {
                char ch = next.charAt(2);
                test.add(cursor, ch);
                cursor++;
            }

        }

        System.out.println(test);
    }
}
