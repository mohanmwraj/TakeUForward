package StacksAndQueues.FAQs;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/*
    Given an array and an integer k, find the maximum for each and every contiguous subarray of size k.
    Example:
    Input: arr[] = {1, 3, -1, -3, 5, 3, 6, 7}, k = 3
    Output: {3, 3, 5, 5, 6, 7}
    Explanation: 
    Window position                Maximum
    ---------------               -----     
    [1  3  -1] -3  5  3  6  7       3
     1 [3  -1  -3] 5  3  6  7       3
     1  3 [-1  -3  5] 3  6  7       5
     1  3  -1 [-3  5  3] 6  7       5
     1  3  -1  -3 [5  3  6] 7       6
     1  3  -1  -3  5 [3  6  7]      7

*/

class SlidingWindowMaximumHelper {
    /*
        Approach 1: Brute Force

        A naive approach is to consider all windows of size k and find the maximum element in each window.
        This approach has a time complexity of O(n*k), where n is the number of elements in the array.
        Time Complexity: O((n-k)*k)
        Space Complexity: O(N-k+1) for the result list
    */
    public List<Integer> slidingWindowMaximum_bruteforce(int[] arr, int k) {
        int n = arr.length;
        List<Integer> result = new java.util.ArrayList<>();
        for (int i = 0; i <= n - k; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = i; j < i + k; j++) {
                max = Math.max(max, arr[j]);
            }
            result.add(max);
        }
        return result;
    }

    /*
        Approach 2: Using Deque (Double Ended Queue)(Monotonic Queue)

        We can use a deque to store indices of array elements. The idea is to maintain the deque such that
        the elements in it are always in decreasing order. The front of the deque will always have the index
        of the maximum element for the current window.

        The maximum element in a particular window can be found directly using the concept of monotonic queue, which indicates
        storing elements in a decreasing order.
        This way, the front of the deque will always have the maximum element for the current window which can be retrieved
        from the queue in constant time.
        In order to maintain the decreasting order, we will remove all the elements smaller than the current element from the back of the deque
        before adding the current element's index to the deque.
        But, a queue data structure does not provide pop operation from the front. To overcome this, Deque (Double Ended Queue) can be used,
        which enable efficient insertion and retrieval from both ends of the queue.
        
        Time Complexity: O(n)
        Space Complexity: O(k) for the deque
    */

    public List<Integer> slidingWindowMaximum_deque(int[] arr, int k) {
        int n = arr.length;
        List<Integer> result = new ArrayList<>();
        Deque<Integer> deque = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            // Remove elements not in the current window
            if (!deque.isEmpty() && deque.peekFirst() == i - k) {
                deque.pollFirst();
            }

            // Remove elements smaller than the current element from the back of the deque
            /* Maintain the monotonic decreasing order */
            while (!deque.isEmpty() && arr[deque.peekLast()] < arr[i]) {
                deque.pollLast();
            }

            // Add the current element's index to the deque
            deque.offerLast(i);

            // The front of the deque is the maximum element for the current window
            if (i >= k - 1) {
                result.add(arr[deque.peekFirst()]);
            }
        }

        return result;
    }

}

public class SlidingWindowMaximum {
    
}
