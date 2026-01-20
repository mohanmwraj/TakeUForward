package StacksAndQueues.FAQs;

import java.util.Stack;

/*
    Given a m x n binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

    Example 1:
    Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
    Output: 6

*/

class MaximumRectanglesHelper {
    
    /*
        Approach:
        1. We can solve this problem by treating each row of the matrix as the base of a histogram.
        2. For each row, we can calculate the height of the histogram bars by counting consecutive 1's in each column up to that row.
        3. We can then use a stack-based approach to find the largest rectangle in the histogram for each row.
        4. The maximum area found across all rows will be our answer.

        Time Complexity: O(m * n) where m is the number of rows and n is the number of columns.
        Space Complexity: O(n) for the stack used in calculating the largest rectangle in histogram.
    
    */

    int largestRectangleArea(int[] heights) {
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();
        int n = heights.length;

        for (int i = 0; i < n; i++) {
            int h = (i == n) ? 0 : heights[i];
            while (!stack.isEmpty() && h <= heights[stack.peek()]) {
                int height = heights[stack.pop()];
                int left = stack.isEmpty() ? -1 : stack.peek();
                int width = i - left - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }

        while(!stack.isEmpty()) {
            int height = heights[stack.pop()];
            int left = stack.isEmpty() ? -1 : stack.peek();
            int width = n - left - 1;
            maxArea = Math.max(maxArea, height * width);
        }

        return maxArea;
    }
}

public class MaximumRectangles {
    public static void main(String[] args) {
        char[][] matrix = {
            {'1','0','1','0','0'},
            {'1','0','1','1','1'},
            {'1','1','1','1','1'},
            {'1','0','0','1','0'}
        };

        if (matrix.length == 0) {
            System.out.println(0);
            return;
        }

        int maxArea = 0;
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] heights = new int[cols];
        MaximumRectanglesHelper helper = new MaximumRectanglesHelper();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == '1') {
                    heights[j] += 1;
                } else {
                    heights[j] = 0;
                }
            }
            maxArea = Math.max(maxArea, helper.largestRectangleArea(heights));
        }

        System.out.println(maxArea);
    }
}
