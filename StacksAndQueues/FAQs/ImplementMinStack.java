package StacksAndQueues.FAQs;

import java.util.Stack;

/*

    Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.


*/
class MinStack_approach1{
    /*
        In a usual stack data structure, there is no method to get the minimum element present in the stack in constant time.
        this can be overcome, if all the numbers are stored in pairs with their current minimum element.
        This way, the stack will not only able to perform the previous methods in constnat time, but also perform the getmin()
        method in constant time.
    
    */
    
    private Stack<int[]> st;

    public MinStack_approach1() {
        st = new Stack<>();
    }

    public void push(int val) {
        if (st.isEmpty()) {
            st.push(new int[]{val, val});
        } else {
            int currentMin = st.peek()[1];
            st.push(new int[]{val, Math.min(val, currentMin)});
        }
    }

    public void pop() {
        if (!st.isEmpty()) {
            st.pop();
        }
    }

    public int top() {
        if (!st.isEmpty()) {
            return st.peek()[0];
        }
        return -1; // or throw exception
    }

    public int getMin() {
        if (!st.isEmpty()) {
            return st.peek()[1];
        }
        return -1; // or throw exception
    }
}

class MinStack_approach2{
    /*
        Approach 2: 
        Instead of storing pairs or auxiliary stacks, we 
        store a specially calculated value in the main stack that allows us to track the minimum element efficiently.
        This approach uses less space compared to storing pairs or using an auxiliary stack.

        Push Operation:
        When pushing a new value, if it's less than or equal to the current minimum, we push a calculated value (2*val - min) 
        onto the stack and update the minimum.
        if the value is greater than the current minimum, we push it directly onto the stack.
        if the stack is empty, we push the value and set it as the minimum.

        Pop Operation:
        When popping, if the popped value is less than the current minimum, it indicates that this value was a special calculated value.
        We then recalculate the previous minimum using the formula (2*min - poppedValue).   
        if the stack is empty, do nothing. Otherwise, retrieve and pop the top value.
    
    */
    private Stack<Integer> mainStack;
    private int min;

    public MinStack_approach2() {
        mainStack = new Stack<>();
    }

    public void push(int val) {
        if (mainStack.isEmpty()) {
            mainStack.push(val);
            min = val;
        } else if (val <= min) {
            mainStack.push(2 * val - min);
            min = val;
        } else {
            mainStack.push(val);
        }
    }

    public void pop() {
        if (mainStack.isEmpty()) {
            return;
        }
        int topValue = mainStack.pop();
        if (topValue < min) {
            min = 2 * min - topValue;
        }
    }

    public int top() {
        if (!mainStack.isEmpty()) {
            int topValue = mainStack.peek();
            if (topValue < min) {
                return min;
            } else {
                return topValue; // return the top value if minimum is less than the top.
            }
        }
        return -1; // or throw exception
    }

    public int getMin() {
        if (!mainStack.isEmpty()) {
            return min;
        }
        return -1; // or throw exception
    }
}

public class ImplementMinStack {
    public static void main(String[] args) {
        MinStack_approach1 minStack = new MinStack_approach1();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin()); // Returns -3
        minStack.pop();
        System.out.println(minStack.top());    // Returns 0
        System.out.println(minStack.getMin()); // Returns -2

        MinStack_approach2 minStack2 = new MinStack_approach2();
        minStack2.push(-2);
        minStack2.push(0);
        minStack2.push(-3);
        System.out.println(minStack2.getMin()); // Returns -3
        minStack2.pop();
        System.out.println(minStack2.top());    // Returns 0    
    }
}
