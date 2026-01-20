package StacksAndQueues.FAQs;

import java.util.Stack;

/*
    Given an array of integers heights representing the histogram's bar height where the width of each bar is 1,
    return the area of the largest rectangle in the histogram.

    Example 1:
    Input: heights = [2,1,5,6,2,3]
    Output: 10
    Explanation: The above is a histogram where width of each bar is 1.
    The largest rectangle is shown in the red area, which has an area = 10 units

*/

class LargestRectangleInaHistogramHelper1{
    /*
        Approach 1: Brute Force

        one way to get the maximum rectangle area is by considering all the rectangles and then 
        finding the maximum area among them.

        The width of a rectangle of current height will depend on the number of rectangles on the left and right having
        a height greater than or equal to the current height.

        The width of the rectange can be found using the concept of previous smaller and next smaller elements.
        width = nse[i] - pse[i] - 1
        area = height[i] * width

        nse = next smaller element
        pse = previous smaller element

        Time Complexity: O(N)
        Space Complexity: O(N)
    
    */

    private int[] findNSE(int[] heights, int n){
        int[] nse = new int[n];
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);

        for(int i = n - 1; i >= 0; i--){
            int currHeight = heights[i];
            while(stack.peek() != -1 && heights[stack.peek()] >= currHeight){
                stack.pop();
            }
            nse[i] = !stack.isEmpty() ? stack.peek() : n;
            stack.push(i);
        }
        return nse;
    }

    private int[] findPSE(int[] heights, int n){
        int[] pse = new int[n];
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);

        for(int i = 0; i < n; i++){
            int currHeight = heights[i];
            while(stack.peek() != -1 && heights[stack.peek()] >= currHeight){
                stack.pop();
            }
            pse[i] = !stack.isEmpty() ? stack.peek() : -1;
            stack.push(i);
        }
        return pse;
    }

    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] nse = findNSE(heights, n);
        int[] pse = findPSE(heights, n);

        int maxArea = 0;
        for(int i = 0; i < n; i++){
            int height = heights[i];
            int width = nse[i] - pse[i] - 1;
            int area = height * width;
            maxArea = Math.max(maxArea, area);
        }
        return maxArea;
    }
}

class LargestRectangleInaHistogramHelper2{
    /*
        Approach 2: Optimized using single stack

        Instead of finding the next smaller and previous smaller elements separately, we can optimize the process
        by using a single stack to keep track of indices of the histogram bars.

        while traversing the heights array, we maintain a stack of indices of bars in increasing order of their heights.
        When we encounter a bar that is shorter than the bar at the index stored at the top of the stack, it means
        we have found the right boundary for the bar at the top of the stack.
        We then pop the top index from the stack and calculate the area of the rectangle that can be formed with
        the height of the popped bar. The width of this rectangle is determined by the current index
        and the index of the new top of the stack after popping.

        Time Complexity: O(N)
        Space Complexity: O(N)
    */

    public int largestRectangleArea1(int[] heights){

        int n = heights.length;
        Stack<Integer> stack = new Stack<>();

        int largestArea = 0;
        int area, nse, pse;

        for(int i = 0; i < n; ++i){
            while(!stack.isEmpty() && 
                        heights[stack.peek()] > heights[i]){
                int ind = heights[stack.pop()];
                pse = stack.isEmpty() ? -1 : stack.peek();
                nse = i;
                area = ind * (nse - pse - 1);
                largestArea = Math.max(largestArea, area);
            }
            stack.push(i);
        }

        while(!stack.isEmpty()){
            int ind = heights[stack.pop()];
            pse = stack.isEmpty() ? -1 : stack.peek();
            nse = n;
            area = ind * (nse - pse - 1);
            largestArea = Math.max(largestArea, area);
        }

        return largestArea;
    }

    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int maxArea = 0;

        for(int i = 0; i < n; i++){
            while(stack.peek() != -1 && heights[stack.peek()] >= heights[i]){
                int height = heights[stack.pop()];
                int width = i - stack.peek() - 1;
                int area = height * width;
                maxArea = Math.max(maxArea, area);
            }
            stack.push(i);
        }

        // Clear remaining bars in stack
        while(stack.peek() != -1){
            int height = heights[stack.pop()];
            int width = n - stack.peek() - 1;
            int area = height * width;
            maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }
}

public class LargestRectangleInaHistogram {
    public static void main(String[] args) {
        int[] heights = {2,1,5,6,2,3};

        LargestRectangleInaHistogramHelper1 helper1 = new LargestRectangleInaHistogramHelper1();
        int maxArea1 = helper1.largestRectangleArea(heights);
        System.out.println("Maximum Area using Approach 1 (Brute Force): " + maxArea1);

        LargestRectangleInaHistogramHelper2 helper2 = new LargestRectangleInaHistogramHelper2();
        int maxArea2 = helper2.largestRectangleArea(heights);
        System.out.println("Maximum Area using Approach 2 (Optimized): " + maxArea2);

        LargestRectangleInaHistogramHelper2 helper3 = new LargestRectangleInaHistogramHelper2();
        int maxArea3 = helper3.largestRectangleArea1(heights);
        System.out.println("Maximum Area using Approach 2 (Optimized - Single Stack): " + maxArea3);
    }
}
