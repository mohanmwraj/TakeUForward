
class ArrayQueue {
    private int[] arr;
    private int front, rear, capacity, size;

    public ArrayQueue(int capacity) {
        this.capacity = capacity;
        arr = new int[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    public ArrayQueue() {
        this(1000); // Default size
    }   

    /*
        Enqueue(push) operation to add an item to the queue
    */
    public void enqueue(int item) {
        if (size == capacity) {
            throw new IllegalStateException("Queue is full");
            /*
                System.exit(1);
            */
        }
        if(rear == -1){
            front = 0;
            rear = 0;
        } else {
            rear = (rear + 1) % capacity;
        }
       // rear = (rear + 1) % capacity;
        arr[rear] = item;
        size++;
    }

    /*
        Dequeue(pop) operation to remove an item from the queue
    */
    public int dequeue() {
        if (size == 0) {
            throw new IllegalStateException("Queue is empty");
        }
        int item = arr[front];

        if(size == 1){
            front = 0;
            rear = -1;
        } else {
            front = (front + 1) % capacity;
        }

     //   front = (front + 1) % capacity;
        size--;
        return item;
    }

    public int peek() {
        if (size == 0) {
            throw new IllegalStateException("Queue is empty");
        }
        return arr[front];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }
}

public class Implement_Queue_Using_Arrays {
    public static void main(String[] args) {
        String[] commands = {
            "ArrayQueue",
            "enqueue",
            "enqueue",
            "peek",
            "dequeue",
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
        ArrayQueue arrayQueue = null;
        for(int i = 0; i < commands.length; i++){
            String command = commands[i];
            int[] input = inputs[i];

            switch(command){
                case "ArrayQueue":
                    arrayQueue = new ArrayQueue();
                    System.out.println("Created ArrayQueue");
                    break;
                case "enqueue":
                    arrayQueue.enqueue(input[0]);
                    System.out.println("Enqueued: " + input[0]);
                    break;
                case "dequeue":
                    int dequeuedValue = arrayQueue.dequeue();
                    System.out.println("Dequeued: " + dequeuedValue);
                    break;
                case "peek":
                    int peekedValue = arrayQueue.peek();
                    System.out.println("Peeked: " + peekedValue);
                    break;
                case "isEmpty":
                    boolean isEmpty = arrayQueue.isEmpty();
                    System.out.println("Is Empty: " + isEmpty);
                    break;
                default:
                    System.out.println("Invalid command");
            }
        }
    }
}
