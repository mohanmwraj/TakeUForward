package StacksAndQueues.implementation;

import java.util.Stack;

public class BalancedParanthesis {

    private boolean isMatched(char open, char close) {
        return (open == '(' && close == ')') ||
               (open == '{' && close == '}') ||
               (open == '[' && close == ']');
    }

    private boolean isValid(String str){
        Stack<Character> stack = new Stack<>();
        
        for(int i = 0; i < str.length(); i++){
            char ch = str.charAt(i);
            
            if(ch == '(' || ch == '{' || ch == '['){
                stack.push(ch);
            } else if(ch == ')' || ch == '}' || ch == ']'){
                if(stack.isEmpty() || !isMatched(stack.pop(), ch)){
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        BalancedParanthesis bp = new BalancedParanthesis();
        String str = "{[()]}";
        if(bp.isValid(str)){
            System.out.println("The parentheses are balanced.");
        } else {
            System.out.println("The parentheses are not balanced.");
        }
    }
    
}
/*
    Time Complexity: O(n) where n is the length of the string.
    Space Complexity: O(n).
*/