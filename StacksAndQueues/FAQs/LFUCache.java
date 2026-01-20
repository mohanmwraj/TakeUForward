package StacksAndQueues.FAQs;
/*
    Design and implement a data structure for a Least Frequently Used (LFU) cache.
    It should support the following operations: get and put.
    1. get(key): Retrieve the value of the key if it exists in the cache, otherwise return -1.
    2. put(key, value): Insert or update the value of the key. 
        If the cache reaches its capacity, it should invalidate the least frequently 
        used item before inserting the new item.
    3. LFUCache(capacity): Initialize the LFU cache with a positive size capacity.
    The cache should operate in O(1) time complexity for both operations.
    Example 1:
    Input:
    LFUCache lfu = new LFUCache(2);
    lfu.put(1, 1);
    lfu.put(2, 2);
    lfu.get(1);       // returns 1
    lfu.put(3, 3);    // evicts key 2
    lfu.get(2);       // returns -1 (not found) 

*/

import java.util.HashMap;
import java.util.Map;

/*
    Approach: 
    1. We can use a combination of HashMaps and a Doubly Linked List to implement the LFU Cache.
    2. One HashMap will store the keys and their corresponding nodes in the Doubly
    Linked List for O(1) access.
    3. Another HashMap will keep track of the frequency of access for each key.
    4. The Doubly Linked List will maintain the order of usage based on frequency,
    with the least frequently used items at the front and the most frequently used items at the back
    5. When we access or update an item, we increase its frequency and move it to the appropriate position in the list.
    6. When we need to evict an item, we remove the least frequently used
    item from the front of the list and also remove its entry from both HashMaps.
    7. When we add a new item, we create a new node with frequency
    1 and add it to the front of the Doubly Linked List and also add its entry to both HashMaps.
    8. This way, both get and put operations can be performed in O(1) time.
    9. The Doubly Linked List allows us to efficiently add and remove nodes from both ends.
    10. The HashMaps allow us to quickly find nodes in the list based on their keys and frequencies.

    Time Complexity: O(1) for both get and put operations.
    Space Complexity: O(capacity) for storing the cache items.

*/

class NodeLFU {
    int key;
    int value;
    int freq;
    NodeLFU prev;
    NodeLFU next;

    NodeLFU(int key, int value) {
        this.key = key;
        this.value = value;
        this.freq = 1;
        next = prev = null;
    }
}

class List{
    int size;
    NodeLFU head, tail;
    List() {
        size = 0;
        head = new NodeLFU(-1, -1); // Dummy head
        tail = new NodeLFU(-1, -1); // Dummy tail
        head.next = tail;
        tail.prev = head;
    }

    void addFront(NodeLFU node) {
        NodeLFU nextNode = head.next;
        head.next = node;
        node.prev = head;
        node.next = nextNode;
        nextNode.prev = node;
        size++;
    }

    void removeNode(NodeLFU node) {
        NodeLFU prevNode = node.prev;
        NodeLFU nextNode = node.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
        size--;
    }
}

public class LFUCache {
    private Map<Integer, NodeLFU> keyNode;
    private Map<Integer, List> freqMap;
    private int capacity;
    private int minFreq;
    private int currSize;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        keyNode = new HashMap<>();
        freqMap = new HashMap<>();
        minFreq = 0;
        currSize = 0;
    }

    //Method to update frequency of data-items
    private void updateNodeFreq(NodeLFU node) {
        keyNode.remove(node.key); // remove from hashmap

        //update the frequency list hashmap
        freqMap.get(node.freq).removeNode(node);

        //if node was the last node having its frequency
        if (node.freq == minFreq && freqMap.get(node.freq).size == 0) {
            minFreq++; // update the minimum frequency
        }

        //Create a dummy list for next higher frequency
        List nextHigherFrequencyList = new List();

        // if list for next higher frequency already exists
        if (freqMap.containsKey(node.freq + 1)) {
            //udpate pointer to already existing list
            nextHigherFrequencyList = freqMap.get(node.freq + 1);
        }

        node.freq++; // increase frequency
        nextHigherFrequencyList.addFront(node);
        freqMap.put(node.freq, nextHigherFrequencyList);
        keyNode.put(node.key, node);
    }

    public int get(int key) {
        if (!keyNode.containsKey(key)) {
            return -1; // Key not found
        }
        NodeLFU node = keyNode.get(key);
        int val = node.value;

        // Update the frequency of the accessed node
        updateNodeFreq(node);
        return val;
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }

        if (keyNode.containsKey(key)) {
            NodeLFU node = keyNode.get(key);
            node.value = value;
            updateNodeFreq(node);
        } else {
            if (currSize == capacity) {
                List minFreqList = freqMap.get(minFreq);
                keyNode.remove(minFreqList.tail.prev.key);
                minFreqList.removeNode(minFreqList.tail.prev);
                currSize--;
            }

            NodeLFU newNode = new NodeLFU(key, value);
            minFreq = 1;
            List list = freqMap.getOrDefault(minFreq, new List());
            list.addFront(newNode);
            freqMap.put(minFreq, list);
            keyNode.put(key, newNode);
            currSize++;
        }
    }

    public static void main(String[] args) {
        LFUCache lfuCache = new LFUCache(2);

        lfuCache.put(1, 1); // cache is {1=1}
        lfuCache.put(2, 2); // cache is {1=1, 2=2}
        System.out.println(lfuCache.get(1)); // return 1

        lfuCache.put(3, 3); // evicts key 2, cache is {1=1, 3=3}
        System.out.println(lfuCache.get(2)); // return -1 (not found)

        lfuCache.put(4, 4); // evicts key 1, cache is {4=4, 3=3}
        System.out.println(lfuCache.get(1)); // return -1 (not found)
        System.out.println(lfuCache.get(3)); // return 3
        System.out.println(lfuCache.get(4)); // return 4
    }
}