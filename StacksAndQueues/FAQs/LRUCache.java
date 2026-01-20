package StacksAndQueues.FAQs;

import java.util.Map;

/*
    Implement a Least Recently Used (LRU) Cache with the following operations:
    1. get(key): Retrieve the value of the key if it exists in the cache, otherwise return -1.
    2. put(key, value): Insert or update the value of the key. If the cache reaches its capacity, it should invalidate the least recently used item before inserting the new item.
    3. LRUCache(capacity): Initialize the LRU cache with a positive size capacity.
    The cache should operate in O(1) time complexity for both operations.

*/

/*
    Approach:
    1. We can use a combination of a HashMap and a Doubly Linked List to implement the LRU Cache.
    2. The HashMap will store the keys and their corresponding nodes in the Doubly Linked List for O(1) access.
    3. The Doubly Linked List will maintain the order of usage, with the most recently used items 
    at the front and the least recently used items at the back.
    4. When we access or update an item, we move it to the front of the list.
    5. When we need to evict an item, we remove it from the back of the list.

    6. This way, both get and put operations can be performed in O(1) time.
    7. The Doubly Linked List allows us to efficiently add and remove nodes from both ends.
    8. The HashMap allows us to quickly find nodes in the list based on their keys.
    9. When the cache reaches its capacity, we remove the tail node from the Doubly Linked List 
        and also remove its entry from the HashMap.
    10. When we add a new item, we create a new node and add it to the front of the Doubly Linked List 
        and also add its entry to the HashMap.



    Time Complexity: O(1) for both get and put operations.
    Space Complexity: O(capacity) for storing the cache items.

*/

class Node{
    int key;
    int value;
    Node prev;
    Node next;

    Node(int key, int value) {
        this.key = key;
        this.value = value;
        next = prev = null;
    }
}

public class LRUCache {
    private Map<Integer, Node> cache;
    private int capacity;
    private Node head, tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new java.util.HashMap<>();
        head = new Node(-1, -1); // Dummy head
        tail = new Node(-1, -1); // Dummy tail
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        Node node = cache.get(key);
        int val = node.value;

        deleteNode(node);
        insertAfterHead(node);
        return val;
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            node.value = value;
            deleteNode(node);
            insertAfterHead(node);
        } else {
            if (cache.size() == capacity) {
                Node lru = tail.prev;
                deleteNode(lru);
                cache.remove(lru.key);
            }
            Node newNode = new Node(key, value);
            insertAfterHead(newNode);
            cache.put(key, newNode);
        }
    }

    private void deleteNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void insertAfterHead(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);

        lruCache.put(1, 1); // cache is {1=1}
        lruCache.put(2, 2); // cache is {1=1, 2=2}
        System.out.println(lruCache.get(1)); // return 1

        lruCache.put(3, 3); // evicts key 2, cache is {1=1, 3=3}
        System.out.println(lruCache.get(2)); // return -1 (not found)

        lruCache.put(4, 4); // evicts key 1, cache is {4=4, 3=3}
        System.out.println(lruCache.get(1)); // return -1 (not found)
        System.out.println(lruCache.get(3)); // return 3
        System.out.println(lruCache.get(4)); // return 4
    }
    
}
