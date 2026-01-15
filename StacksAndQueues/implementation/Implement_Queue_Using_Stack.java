package StacksAndQueues.implementation;

import java.util.Stack;

class StackQueue{
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    public StackQueue() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void push(int x) {
        while(!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }

        stack1.push(x);
        while(!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
    }

    public int pop(){
        if(stack1.isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        return stack1.pop();
    }

    public int peek(){
        if(stack1.isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        return stack1.peek();
    }

    public boolean empty(){
        return stack1.isEmpty();
    }
}


public class Implement_Queue_Using_Stack {
    public static void main(String[] args) {
        
        StackQueue queue = new StackQueue();
        String[] commands = { "StackQueue", "push", "push", "peek", "pop", "empty" };
        int[][] values = { {}, {1}, {2}, {}, {}, {} };

        for(int i = 0; i < commands.length; i++) {
            String command = commands[i];
            switch(command) {
                case "StackQueue":
                    queue = new StackQueue();
                    System.out.println("Queue initialized.");
                    break;
                case "push":
                    queue.push(values[i][0]);
                    System.out.println("Pushed: " + values[i][0]);
                    break;
                case "pop":
                    System.out.println("Popped: " + queue.pop());
                    break;
                case "peek":
                    System.out.println("Front element: " + queue.peek());
                    break;
                case "empty":
                    System.out.println("Is queue empty? " + queue.empty());
                    break;
            }
        }
    }
}
