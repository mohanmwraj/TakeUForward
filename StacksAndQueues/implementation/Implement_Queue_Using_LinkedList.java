package StacksAndQueues.implementation;

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class LinkedListQueue {
    private Node front;
    private Node rear;
    private int size;

    public LinkedListQueue() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    public void enqueue(int data) {
        Node newNode = new Node(data);
        if (rear == null) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
    }

    public int dequeue() {
        if (front == null) {
            throw new IllegalStateException("Queue is empty");
        }
        int data = front.data;
        Node temp = front;

        front = front.next;
        temp = null;

        if (front == null) {
            rear = null;
        }
        size--;
        return data;
    }

    public int peek() {
        if (front == null) {
            throw new IllegalStateException("Queue is empty");
        }
        return front.data;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
}


public class Implement_Queue_Using_LinkedList {
    public static void main(String[] args) {
        LinkedListQueue queue = new LinkedListQueue();

        String[] operations = {"LinkedListQueue", "enqueue",
                                 "enqueue", "dequeue", "peek", 
                                 "dequeue", "isEmpty"};
        int[] inputs = {0, 10, 20, 0, 0, 0, 0};

        for (int i = 0; i < operations.length; i++) {
            String operation = operations[i];
            switch (operation) {
                case "LinkedListQueue":
                    queue = new LinkedListQueue();
                    System.out.println("Queue initialized.");
                    break;
                case "enqueue":
                    queue.enqueue(inputs[i]);
                    System.out.println("Enqueued: " + inputs[i]);
                    break;
                case "dequeue":
                    int dequeuedValue = queue.dequeue();
                    System.out.println("Dequeued: " + dequeuedValue);
                    break;
                case "peek":
                    int frontValue = queue.peek();
                    System.out.println("Front element: " + frontValue);
                    break;
                case "isEmpty":
                    boolean empty = queue.isEmpty();
                    System.out.println("Is queue empty? " + empty);
                    break;
                default:
                    System.out.println("Invalid operation");
            }
        }
    }
}
