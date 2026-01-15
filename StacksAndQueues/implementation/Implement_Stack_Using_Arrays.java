package StacksAndQueues.implementation;

import java.util.Arrays;
import java.util.List;

class ArrayStack{

    private int[] stackArray;
    private int capacity;
    private int topIndex;

    public ArrayStack(int size){
        this.capacity = size;
        this.stackArray = new int[capacity];
        this.topIndex = -1;
    }

    public ArrayStack(){
        this(1000); // Default size
    }   

    public void push(int value){
        if(topIndex >= capacity - 1){
            throw new StackOverflowError("Stack is full");
        }
        stackArray[++topIndex] = value;
    }

    public int pop(){
        if(isEmpty()){
            throw new IllegalStateException("Stack is empty");
        }
        return stackArray[topIndex--];
    }

    public boolean isEmpty(){
        return topIndex == -1;
    }
}


public class Implement_Stack_Using_Arrays {
    public static void main(String[] args) {
        List<String> commands = Arrays.asList(
            "ArrayStack",
            "push",
            "push",
            "top",
            "pop",
            "isEmpty"
        );

        List<List<Integer>> inputs = Arrays.asList(
            Arrays.asList(),
            Arrays.asList(5),
            Arrays.asList(10),
            Arrays.asList(),
            Arrays.asList(),
            Arrays.asList()
        );

        ArrayStack arrayStack = new ArrayStack();

        for(int i = 0; i < commands.size(); i++){
            String command = commands.get(i);
            List<Integer> input = inputs.get(i);

            switch(command){
                case "ArrayStack":
                    arrayStack = new ArrayStack();
                    System.out.println("Initialized ArrayStack");
                    break;
                case "push":
                    arrayStack.push(input.get(0));
                    System.out.println("Pushed: " + input.get(0));
                    break;
                case "pop":
                    int poppedValue = arrayStack.pop();
                    System.out.println("Popped: " + poppedValue);
                    break;
                case "isEmpty":
                    boolean empty = arrayStack.isEmpty();
                    System.out.println("Is Empty: " + empty);
                    break;
                default:
                    System.out.println("Unknown command: " + command);
            }
        }

    }    
}
