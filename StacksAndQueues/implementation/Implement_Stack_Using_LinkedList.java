package StacksAndQueues.implementation;

class Node{
    int val;
    Node next;
    Node(int d){
        val = d;
        next = null;
    }
}

class LinkedListStack{
    private Node head;
    private int size;

    public LinkedListStack() {
        head = null;
        size = 0;
    }

    public void push(int x){
        Node element = new Node(x);
        element.next = head;
        head = element;
        size++;
    }

    public int pop(){
        if(head == null) {
            return -1; // Stack is empty
        }
        int poppedValue = head.val;
        Node temp = head;
        head = head.next;
        temp = null;
        size--;

        return poppedValue;
    }

    public int top(){
        if(head == null) {
            return -1; // Stack is empty
        }
        return head.val;
    }

    public boolean isEmpty(){
        return size == 0;
    }
}

public class Implement_Stack_Using_LinkedList {
    public static void main(String[] args) {
        LinkedListStack stack = new LinkedListStack();
        String[] commands = { "LinkedListStack", "push", "push", "top", "pop", "isEmpty" };
        int[][] values = { {}, {1}, {2}, {}, {}, {} };

        for(int i = 0; i < commands.length; i++) {
            String command = commands[i];
            switch(command) {
                case "LinkedListStack":
                    stack = new LinkedListStack();
                    System.out.println("Stack initialized.");
                    break;
                case "push":
                    stack.push(values[i][0]);
                    System.out.println("Pushed: " + values[i][0]);
                    break;
                case "pop":
                    System.out.println("Popped: " + stack.pop());
                    break;
                case "top":
                    System.out.println("Top element: " + stack.top());
                    break;
                case "isEmpty":
                    System.out.println("Is stack empty? " + stack.isEmpty());
                    break;
            }
        }
    }
}
