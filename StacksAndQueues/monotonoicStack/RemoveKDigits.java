package StacksAndQueues.monotonoicStack;

import java.util.Stack;

/*
    Given a string num representing a non-negative integer num, and an integer k,
    return the smallest possible integer after removing k digits from num.

*/


class RemoveKDigitsSolution {
    /*
        Approach: Monotonic Stack
        Time Complexity: O(n)
        Space Complexity: O(n)

        To get the smallest possible integer, the smaller digits must be kept at the beginning.
        This can be achieved by getting rid of K larget digits using a simple greedy approach 
        that works by processing each digit and keeoing the smallest possible digit, aiming for the smallest resulting number.
        Prioritizing smaller digits for the leftmost positions ensures the resulting number is minimized.
    */

    public String removeKdigits(String num, int k) {
        int n = num.length();
        Stack<Character> st = new Stack<>();

        for(int i = 0; i < n; i++){
            char curr = num.charAt(i);

            while(!st.isEmpty() && k > 0 && st.peek() > curr){
                st.pop();
                k--;
            }

            /*
                To avoid leading zeros, we only push the current digit onto the stack if it's not '0' or if the stack is not empty.
                This ensures that we don't add unnecessary leading zeros to the result.
            */
            if(curr != '0' || !st.isEmpty()){
                st.push(curr);
            }
        }
        // If there are still digits to remove, remove them from the end of the number
        while(!st.isEmpty() && k > 0){
            st.pop();
            k--;
        }

        if(st.isEmpty()) return "0";

        StringBuilder sb = new StringBuilder();
        while(!st.isEmpty()){
            sb.append(st.pop());
        }

        while(sb.length() > 1 && sb.charAt(sb.length() - 1) == '0'){
            sb.deleteCharAt(sb.length() - 1);
        }

        sb.reverse(); if(sb.length() == 0) return "0";
        return sb.toString();
    }
}

public class RemoveKDigits {
    public static void main(String[] args) {
        RemoveKDigitsSolution sol = new RemoveKDigitsSolution();
        String num = "1432219";
        int k = 3;
        System.out.println("Smallest number after removing " + k + " digits: " + sol.removeKdigits(num, k));
    }
}
