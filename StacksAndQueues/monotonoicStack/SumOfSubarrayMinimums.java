package StacksAndQueues.monotonoicStack;

import java.util.Stack;

/*
    Give an array of integers arr of size n, 
    calculate the sum of minimum value in each contiguous subarray of arr.
    since the result may be large, return the result modulo 10^9 + 7.
*/

class Solution{

    /*
        Approach 1: Brute Force
        - Generate all the subarrays of the given array.
        - For each subarray, find the minimum element.
        - Keep a running sum of all the minimum elements found.
        - Time Complexity: O(n^3) - Generating all subarrays takes O(n^2) and finding the minimum for each subarray takes O(n).
        - Space Complexity: O(1) - No extra space is used.
    
    */

    public int approach1(int[] arr){
        int n = arr.length;
        long sum = 0;
        int mod = (int)1e9 + 7;

        for(int i = 0; i < n; i++){
            for(int j = i; j < n; j++){
                int minVal = Integer.MAX_VALUE;
                for(int k = i; k <= j; k++){
                    minVal = Math.min(minVal, arr[k]);
                }
                sum = (sum + minVal) % mod;
            }
        }

        return (int)sum;
    }

    /*
        Approach 2: Monotonic Stack
        - Use a monotonic stack to efficiently find the previous and next smaller elements for each element in the array.
        - For each element, calculate its contribution to the sum based on the distance to the previous and next smaller elements.
        - Time Complexity: O(n) - Each element is pushed and popped from the stack at most once.
        - Space Complexity: O(n) - The stack can hold up to n elements in the worst case.

        4 6 7 3 7 8 2
               ^
        if you take element 3, subarrays in which 3 is minimum are:
        [3], [7,3], [6,7,3], [4,6,7,3], [3,7], [3,7,8].
        number of subarrays = left * right
        where left = distance to previous smaller element [PSE]
              right = distance to next smaller element [NSE]

        Edge Cases:
        - Empty array: Return 0 as there are no subarrays.
        - Array with all identical elements: Each element will contribute equally to the sum.
        - Array with single element: The sum will be the value of that single element.
        - Array with strictly increasing or decreasing elements: The stack will help efficiently find the PSE and NSE in these scenarios.
        - Large arrays: Ensure the solution handles large inputs efficiently within time limits.
    */

    public int approach2(int[] arr){
        int n = arr.length;
        long sum = 0;
        int mod = (int)1e9 + 7;

        int[] prevSmaller = new int[n];
        int[] nextSmaller = new int[n];

        // Finding Previous Smaller Element (PSE)
        pse(arr, n, prevSmaller);

        // Finding Next Smaller Element (NSE)
        nse(arr, n, nextSmaller);

        // Calculating the sum of subarray minimums
        for(int i = 0; i < n; i++){
            long left = i - prevSmaller[i];
            long right = nextSmaller[i] - i;
            sum = (sum + (arr[i] * left * right) % mod) % mod;
        }

        return (int)sum;
    }

    // Finding Next Smaller Element (NSE)
    private void nse(int[] arr, int n, int[] nextSmaller) {
        
        Stack<Integer> st = new Stack<>();

        for(int i = n - 1; i >= 0; i--){
            while(!st.isEmpty() && arr[st.peek()] >= arr[i]){
                st.pop();
            }
            nextSmaller[i] = st.isEmpty() ? n : st.peek();
            st.push(i);
        }
        
    }

    // Finding Previous Smaller Element (PSE)
    private void pse(int[] arr, int n, int[] prevSmaller) {
        Stack<Integer> st = new Stack<>();
        for(int i = 0; i < n; i++){
            while(!st.isEmpty() && arr[st.peek()] > arr[i]){
                st.pop();
            }
            prevSmaller[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }
    }
}



public class SumOfSubarrayMinimums {
    
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] arr = {3,1,2,4};
        System.out.println("Sum of Subarray Minimums (Brute Force): " + sol.approach1(arr));
        System.out.println("Sum of Subarray Minimums (Monotonic Stack): " + sol.approach2(arr));
    }
}
