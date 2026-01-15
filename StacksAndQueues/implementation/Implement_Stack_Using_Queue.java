package StacksAndQueues.implementation;

import java.util.LinkedList;
import java.util.Queue;

class QueueStack {
    Queue<Integer> queue = new LinkedList<>();

    public void push(int value) {
        queue.add(value);
        int size = queue.size();

        // Rotate the queue to place the new element at the front
        for (int i = 0; i < size - 1; i++) {
            queue.add(queue.remove());
        }
    }

    public int pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return queue.poll();
    }   

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public int top() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return queue.peek();
    }
}

public class Implement_Stack_Using_Queue {
    public static void main(String[] args) {
        
        String[] commands = {
            "QueueStack",
            "push",
            "push",
            "pop",
            "top",
            "isEmpty"
        };

        int[][] inputs = {
            {},
            {5},
            {10},
            {},
            {},
            {}
        };

        QueueStack stack = null;

        for(int i = 0; i < commands.length; i++) {
            String command = commands[i];
            int[] input = inputs[i];

            switch (command) {
                case "QueueStack":
                    stack = new QueueStack();
                    System.out.println("Initialized QueueStack");
                    break;
                case "push":
                    stack.push(input[0]);
                    System.out.println("Pushed: " + input[0]);
                    break;
                case "pop":
                    int poppedValue = stack.pop();
                    System.out.println("Popped: " + poppedValue);
                    break;
                case "top":
                    int topValue = stack.top();
                    System.out.println("Top: " + topValue);
                    break;
                case "isEmpty":
                    boolean empty = stack.isEmpty();
                    System.out.println("Is Empty: " + empty);
                    break;
                default:
                    System.out.println("Unknown command: " + command);
            }
        }
    }
}
