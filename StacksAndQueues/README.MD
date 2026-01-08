# Stacks & Queues

## Stack

A **Stack** is a linear data structure that follows the **LIFO (Last In, First Out)** principle. The element inserted last is the first one to be removed.

### Real-World Analogy
- Stack of plates
- Undo operation in text editors
- Browser back button history

### Stack Operations

| Operation | Description | Time Complexity |
|-----------|-------------|-----------------|
| `push(x)` | Insert element x at the top | O(1) |
| `pop()` | Remove and return the top element | O(1) |
| `peek()` / `top()` | Return the top element without removing | O(1) |
| `isEmpty()` | Check if stack is empty | O(1) |
| `size()` | Return the number of elements | O(1) |

### Stack Implementation in Java

```java
import java.util.Stack;

Stack<Integer> stack = new Stack<>();

// Push elements
stack.push(10);
stack.push(20);
stack.push(30);

// Peek top element
System.out.println(stack.peek());  // Output: 30

// Pop element
System.out.println(stack.pop());   // Output: 30

// Check if empty
System.out.println(stack.isEmpty()); // Output: false

// Get size
System.out.println(stack.size());  // Output: 2
```

---

## Queue

A **Queue** is a linear data structure that follows the **FIFO (First In, First Out)** principle. The element inserted first is the first one to be removed.

### Real-World Analogy
- People standing in a line
- Printer job queue
- Task scheduling in OS

### Queue Operations

| Operation | Description | Time Complexity |
|-----------|-------------|-----------------|
| `enqueue(x)` / `offer(x)` | Insert element x at the rear | O(1) |
| `dequeue()` / `poll()` | Remove and return the front element | O(1) |
| `peek()` / `front()` | Return the front element without removing | O(1) |
| `isEmpty()` | Check if queue is empty | O(1) |
| `size()` | Return the number of elements | O(1) |

### Queue Implementation in Java

```java
import java.util.LinkedList;
import java.util.Queue;

Queue<Integer> queue = new LinkedList<>();

// Enqueue elements
queue.offer(10);
queue.offer(20);
queue.offer(30);

// Peek front element
System.out.println(queue.peek());  // Output: 10

// Dequeue element
System.out.println(queue.poll());  // Output: 10

// Check if empty
System.out.println(queue.isEmpty()); // Output: false

// Get size
System.out.println(queue.size());  // Output: 2
```

---

## Types of Queues

### 1. Simple Queue
- Basic FIFO structure
- Insertion at rear, deletion at front

### 2. Circular Queue
- Last position connects back to first position
- Efficient use of memory

### 3. Deque (Double-Ended Queue)
- Insertion and deletion at both ends
- Can act as both stack and queue

```java
import java.util.ArrayDeque;
import java.util.Deque;

Deque<Integer> deque = new ArrayDeque<>();

// Add at front and rear
deque.addFirst(10);
deque.addLast(20);

// Remove from front and rear
deque.removeFirst();
deque.removeLast();
```

### 4. Priority Queue
- Elements are dequeued based on priority
- Not strictly FIFO

```java
import java.util.PriorityQueue;

PriorityQueue<Integer> pq = new PriorityQueue<>(); // Min-Heap by default

pq.offer(30);
pq.offer(10);
pq.offer(20);

System.out.println(pq.poll()); // Output: 10 (smallest first)
```

---

## Stack vs Queue

| Feature | Stack | Queue |
|---------|-------|-------|
| Principle | LIFO | FIFO |
| Insertion | Top only | Rear only |
| Deletion | Top only | Front only |
| Use Cases | Recursion, Backtracking, Expression Evaluation | BFS, Scheduling, Buffering |

---

## Common Problems

### Problems

- [Implement Stack Using Arrays](StacksAndQueues/Implement_Stack_Using_Arrays.java)
- [Implement Queue Using Arrays](./Implement%20Queue%20Using%20Arrays/)


### Stack Problems
- Valid Parentheses
- Next Greater Element
- Stock Span Problem
- Evaluate Postfix Expression
- Min Stack

### Queue Problems
- Implement Queue using Stacks
- Implement Stack using Queues
- Sliding Window Maximum
- First Non-Repeating Character in Stream
- Rotting Oranges (BFS)
