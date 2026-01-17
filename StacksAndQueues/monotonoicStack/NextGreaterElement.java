package StacksAndQueues.monotonoicStack;

import java.util.Arrays;
import java.util.Stack;

/*
    Input: [4,5,2,10,8]
    Output: [5,10,10,-1]
    Explanation: For number 4, next greater number is 5.
                 For number 5, next greater number is 10.
                 For number 2, next greater number is 10.       
                 For number 10, there is no next greater number, so output -1.
    
*/

class Solution{
    /*
        Approach1: Brute Force
        > use a loop to pick each element one by one
        > for each element, use another loop to find the next greater element
        > time complexity: O(n^2)
        > space complexity: O(1)
    */
    public int[] approach1(int[] nums){
        int n = nums.length;
        int[] result = new int[n];
        Arrays.fill(result, -1);

        for(int i=0; i<n; i++){
            //result[i] = -1; // default value if no greater element found
            for(int j=i+1; j<n; j++){
                if(nums[j] > nums[i]){
                    result[i] = nums[j];
                    break;
                }
            }
        }
        
        return result;
    }

    /*
        Approach2: Using Monotonic Stack (decreasing stack)
        > use a stack to keep track of elements for which we need to find the next greater element
        > iterate through the array from right to left
        > for each element, pop elements from the stack until we find a greater element or the stack becomes empty
        > if the stack is not empty, the top element is the next greater element
        > push the current element onto the stack
        > time complexity: O(n)
        > space complexity: O(n)


        4 12 5 3 1 2 5 3 1 2 4 6
                        ^
                        stack: 6 4 2 1 (->top)
        when encounter 3: pop 2,1 from stack, next greater is 4, push 3
                        stack: 6 4 3

        when 2 1 comes later there will be no pop as they are smaller than 3
    */

    public int[] approach2(int[] nums){
        int n = nums.length;
        int[] result = new int[n];
        Arrays.fill(result, -1);

        Stack<Integer> stack = new Stack<>();
        for(int i=n-1; i>=0; i--){
            while(!stack.isEmpty() && stack.peek() <= nums[i]){
                stack.pop();
            }

            if(!stack.isEmpty()){
                result[i] = stack.peek();
            }

            stack.push(nums[i]);
        }
        
        return result;
    }

}

public class NextGreaterElement {
    
    public static void main(String[] args) {
        int n = 4;
        int[] arr = {4,5,2,10,8};

        Solution solution = new Solution();
        int[] result1 = solution.approach1(arr);
        System.out.println("Approach1 - Brute Force: " + Arrays.toString(result1));

        int[] result2 = solution.approach2(arr);
        System.out.println("Approach2 - Monotonic Stack: " + Arrays.toString(result2));
    }
}
