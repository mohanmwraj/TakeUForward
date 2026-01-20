package StacksAndQueues.FAQs;
/*
    Given an array of size n, where each element arr[i] represents the price of a stock on day i,
    calculate the stock span for each day. The stock span is defined as the maximum number of consecutive days
    (including the current day) the price of the stock was less than or equal to its price on the current day.

    Example 1:
    Input: prices = [100, 80, 60, 70, 60, 75, 85]
    Output: [1, 1, 1, 2, 1, 4, 6]

*/

import java.util.Stack;

class StockSpanProblemHelper {
    
    /*
        Approach: Brute Force 

        The brute force to solve the problem is to start counting the days (for each day) where the stock prices 
        were less than or equal to the price to stocks on current day.

        Time Complexity: O(n^2) where n is the number of days.
        Space Complexity: O(1)
    */

    public int[] calculateStockSpanBruteForce(int[] prices) {
        int n = prices.length;
        int[] span = new int[n];

        for (int i = 0; i < n; i++) {
            int currSpan = 0;
            for (int j = i; j >= 0; j--) {
                if (prices[j] <= prices[i]) {
                    currSpan++;
                } else {
                    break;
                }
            }
            span[i] = currSpan;
        }

        return span;
    }



    /*
        Approach:
        1. We can use a stack to keep track of the indices of the days.
        2. For each day, we pop elements from the stack while the price on those days is less than or equal to the current day's price.
        3. If the stack is empty after popping, it means all previous prices were less than or equal to the current price, so the span is the current day index + 1.
        4. If the stack is not empty, the span is the difference between the current day index and the index of the last higher price found in the stack.
        5. Push the current day index onto the stack for future comparisons.

        The stock span is the number of consecutive days the price was less than or equal to the current day's price.
        Hence, to get the stock span for current day, the aim to find the position of previous greater element for current day.
        This will significantly improve the solution by eliminating multiple backward traversals on the given array.

        Finding the previous greater element for each day is similar to the "Next Greater Element" problem, 
        the only difference is that the direction of traversal will be reversed while maintaining the decreasing order
        of elements in the stack(monotonic stack).

        Time Complexity: O(n) where n is the number of days.
        Space Complexity: O(n) for the stack used to store indices.
    
    */
    private int[] findPGE(int[] arr){
        int n = arr.length;
        int[] pge = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] <= arr[i]) {
                stack.pop();
            }
            pge[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        return pge;
    }
    public int[] stockSpan(int[] prices) {
        int n = prices.length;
        int[] pge = findPGE(prices);
        int[] span = new int[n];

        for(int i = 0; i < n; ++i){
            span[i] = i - pge[i];
        }

        return span;
    }
}


public class StockSpanProblem {
    public static void main(String[] args) {
        int[] prices = {100, 80, 60, 70, 60, 75, 85};
        StockSpanProblemHelper helper = new StockSpanProblemHelper();
        int[] span = helper.stockSpan(prices);
        for (int s : span) {
            System.out.print(s + " ");
        }
    }
}