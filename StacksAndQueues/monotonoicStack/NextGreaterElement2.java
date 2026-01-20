package StacksAndQueues.monotonoicStack;

import java.util.Arrays;
import java.util.Stack;

/*
    Give a circular integer array nums (i.e., the next element of nums[nums.length - 1] is nums[0]),
    return an array answer where answer[i] is the next greater number of nums[i].
    If it does not exist, return -1 for this number.



*/

class Solution{

    /*
        Appproach 1: Brute Force
        > similar to Next Greater Element 1
        > for each element, we need to traverse the array twice to find the next greater element
        > Handling circular Array:
            one way is to double the array by pushing all the elements of array at the back in order. But this 
            will take extra space.
            another way is to use modulo operator to simulate the circular array. This will double the array hypothetically s
            saving the extra space.
        > time complexity: O(n^2)
        > space complexity: O(1)
    
    */

    public int[] approach1(int[] nums){
        int n = nums.length;
        int[] result = new int[n];
        Arrays.fill(result, -1);

        for(int i=0; i<n; i++){
            for(int j=1; j<n; j++){
                int circularIndex = (i + j) % n; // simulate circular array hypothetical index
                if(nums[circularIndex] > nums[i]){
                    result[i] = nums[circularIndex];
                    break;
                }
            }
        }

        return result;
    }

    /*
        Approach 2: Using Monotonic Stack
        > similar to Next Greater Element 1
        > we will traverse the array twice to simulate the circular array
        > Start traversing the hypothetically doubled array from back using the modulus operator. For the current element,
            pop elements from stack till top is less than or equal to current element.
        > For the first half of the elements, discard the greatest elements if found. For the second half of the elements, if stack is not empty,
            the top element is the next greater element.
        > push the current element onto the stack
        > time complexity: O(n)
        > space complexity: O(n)

        2 10 12 1 11 | 2 10 12 1 11 [double the array hypothetically]
                        ^
                        stack: 11 1 12 10 2 (->top)
        when encounter 11: pop 1,2 from stack, next greater is 12, push 11
                        stack: 11 12 10 2

        when 1 comes later there will be no pop as it is smaller than 11
    */

    public int[] approach2(int[] nums){
        int n = nums.length;
        int[] result = new int[n];
        Arrays.fill(result, -1);

        Stack<Integer> stack = new Stack<>();
        for(int i=2*n-1; i>=0; i--){
            int circularIndex = i % n; // simulate circular array hypothetical index

            while(!stack.isEmpty() && stack.peek() <= nums[circularIndex]){
                stack.pop();
            }

            // for the first half of the traversal, we don't need to set the result
            if(i < n){
                if(!stack.isEmpty()){
                    result[circularIndex] = stack.peek();
                }
            }

            stack.push(nums[circularIndex]);
        }

        return result;
    }
}


public class NextGreaterElement2 {
    public static void main(String[] args) {
        int[] arr = {4,5,2,10,8};

        Solution sol = new Solution();
        int[] result1 = sol.approach1(arr);
        System.out.println("Approach 1 - Brute Force:");
        System.out.println(Arrays.toString(result1));

        int[] result2 = sol.approach2(arr);
        System.out.println("Approach 2 - Monotonic Stack:");
        System.out.println(Arrays.toString(result2));
    }
}
