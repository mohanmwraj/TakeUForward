package StacksAndQueues.monotonoicStack;
import java.util.Stack;
package StacksAndQueues.monotonoicStack;

import java.util.Stack;

/*
    Given an integer array nums, determine the range of a subarray, defined as the difference between
    the largest and smallest element in the subarray. Return the sum of all subarray ranges of nums.

    A subarray is a contiguous non-empty sequence of elements within an array.

*/


class subArrayRangeSolution {
    /*
        Approach: Brute Force
        - Generate all possible subarrays of the given array.
        - For each subarray, find the maximum and minimum elements.
        - Calculate the range (max - min) for each subarray and keep a running sum of these ranges.
        - Time Complexity: O(n^3) - Generating all subarrays takes O(n^2) and finding the max and min for each subarray takes O(n). 
        - Space Complexity: O(1) - No extra space is used.
    */

    public long approach1(int[] nums) {
        int n = nums.length;
        long totalRangeSum = 0;

        for (int i = 0; i < n; i++) {
            int subarrayMax = Integer.MIN_VALUE;
            int subarrayMin = Integer.MAX_VALUE;
            for (int j = i; j < n; j++) {

                subarrayMax = Math.max(subarrayMax, nums[j]);
                subarrayMin = Math.min(subarrayMin, nums[j]);
                
                totalRangeSum += (subarrayMax - subarrayMin);
            }
        }

        return totalRangeSum;
    }

    /*
        Approach 2: Using Contribution Technique

        - Range = Σ(max - min) = Σ(subarry max) - Σ(subarry min)
    
    */

    // Finding Next Smaller Element (NSE)
    private int[] findNSE(int[] arr, int n) {
        int[] nextSmaller = new int[n];
        Stack<Integer> st = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty() && arr[st.peek()] > arr[i]) {
                st.pop();
            }
            nextSmaller[i] = st.isEmpty() ? n : st.peek();
            st.push(i);
        }

        return nextSmaller;
    }

    // Finding the next greater element (NGE)
    private int[] findNGE(int[] arr, int n) {
        int[] nextGreater = new int[n];
        Stack<Integer> st = new Stack<>();  
        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty() && arr[st.peek()] <= arr[i]) {
                st.pop();
            }
            nextGreater[i] = st.isEmpty() ? n : st.peek();
            st.push(i);
        }
        
        return nextGreater;
    }

    // Finding previous smaller or equal element (PSEE)
    private int[] findPSEE(int[] arr, int n) {
        int[] prevSmallerEqual = new int[n];
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && arr[st.peek()] > arr[i]) {
                st.pop();
            }
            prevSmallerEqual[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }

        return prevSmallerEqual;
    }

    // Finding previous greater or equal element (PGEE)
    private int[] findPGEE(int[] arr, int n) {
        int[] prevGreaterEqual = new int[n];
        Stack<Integer> st = new Stack<>();  
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && arr[st.peek()] <= arr[i]) {
                st.pop();
            }
            prevGreaterEqual[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }
    
        return prevGreaterEqual;
    }

    // Find the sum of minimum value in each subarray
    private long sumOfSubarrayMins(int[] arr) {
        int n = arr.length;
        int[] nextSmaller = findNSE(arr, n);
        int[] prevSmallerEqual = findPSEE(arr, n);

        long sum = 0;
        for (int i = 0; i < n; i++) {
            long left = i - prevSmallerEqual[i];
            long right = nextSmaller[i] - i;
            sum += (arr[i] * left * right);
        }

        return sum;
    }

    // Find the sum of maximum value in each subarray
    private long sumOfSubarrayMaxs(int[] arr) {
        int n = arr.length;
        int[] nextGreater = findNGE(arr, n);
        int[] prevGreaterEqual = findPGEE(arr, n);
        long sum = 0;
        for (int i = 0; i < n; i++) {
            long left = i - prevGreaterEqual[i];
            long right = nextGreater[i] - i;
            sum += (arr[i] * left * right);
        }
        return sum;
    }

    public long approach2(int[] nums) {
        long totalMaxSum = sumOfSubarrayMaxs(nums);
        long totalMinSum = sumOfSubarrayMins(nums);
        return totalMaxSum - totalMinSum;
    }
}

public class SumOfSubarrayRanges {
    public static void main(String[] args) {
        subArrayRangeSolution sol = new subArrayRangeSolution();
        int[] nums = {4, -2, -3, 4, 1};//{1,3,2};
        System.out.println("Sum of Subarray Ranges (Brute Force): " + sol.approach1(nums));
        System.out.println("Sum of Subarray Ranges (Contribution Technique): " + sol.approach2(nums));
    }
}