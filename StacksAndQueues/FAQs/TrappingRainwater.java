package StacksAndQueues.FAQs;

/*
    Given an array of non-negative integers representing an elevation map where the width of each bar is 1, 
    compute how much water it can trap after raining.

*/
class TrappingRainwaterHelper1 {
    /*
        Approach: Brute Force

        Summation of min(left_max, right_max) - height[i] for each index i

        Traversing to the left and righ to get the maximum for every height of ground is inefficient. Two auxiliary arrays 
        can be used to store the left max and right max for every index. The difference between this water level 
        and the height of ground at that index will give the amount of water that can be trapped at that index.

        Prefix Max Array: An array that stores the maximum height to the left of every index.
        Suffix Max Array: An array that stores the maximum height to the right of every index

        Time Complexity: O(n)
        Space Complexity: O(n)
    
    */
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int n = height.length;
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];

        leftMax[0] = height[0];
        findPrefixMax(height, n, leftMax);

        rightMax[n - 1] = height[n - 1];
        findSuffixMax(height, n, rightMax);

        int totalWater = 0;
        for (int i = 0; i < n; i++) {
            totalWater += Math.min(leftMax[i], rightMax[i]) - height[i];
        }

        return totalWater;
    }

    private void findPrefixMax(int[] height, int n, int[] leftMax) {
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }
    }

    private void findSuffixMax(int[] height, int n, int[] rightMax) {
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }
    }
}

class TrappingRainwaterHelper2 {
    /*
        Approach: Two Pointers

        Instead of using extra space for prefix max and suffix max arrays, 
        two pointers can be used to keep track of the left max and right max
        while traversing the array from both ends.

        It is impossible to find out the left and right barriers before hand, but a clever way to
        traverse the array from both ends. The left and right maximum heights (barriers) can be maintained
        and based on these values the water on top of current height can be calculated using the formula:
        water = min(left_max, right_max) - height[i]


        Time Complexity: O(n)
        Space Complexity: O(1)
    */
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int left = 0;
        int right = height.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        int totalWater = 0;

        while (left <= right) {
            if (height[left] <= height[right]) {
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    totalWater += leftMax - height[left];
                }
                left++;
            } else {
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    totalWater += rightMax - height[right];
                }
                right--;
            }
        }

        return totalWater;
    }
}

public class TrappingRainwater {
    public static void main(String[] args) {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};

        TrappingRainwaterHelper1 helper1 = new TrappingRainwaterHelper1();
        int result1 = helper1.trap(height);
        System.out.println("Total water trapped (Brute Force): " + result1);

        TrappingRainwaterHelper2 helper2 = new TrappingRainwaterHelper2();
        int result2 = helper2.trap(height);
        System.out.println("Total water trapped (Two Pointers): " + result2);
    }
}
