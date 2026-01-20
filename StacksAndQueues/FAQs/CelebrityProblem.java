package StacksAndQueues.FAQs;

import java.util.Stack;

/*
    A celebrity is a person who is known by everyone but knows no one at a party.
    Given a 2D matrix M where M[i][j] is 1 if person i knows person j, and 0 otherwise,
    find if there is a celebrity in the party. 

    A celebrity is defined as someone who is known by everyone else but does not know anyone.

    Example 1:
    Input: M = [[0, 1, 0],
                [0, 0, 0],
                [0, 1, 0]]
    Output: 1
    Explanation: The person at index 1 is a celebrity as everyone knows them but they know no one.
*/

class CelebrityProblemHelper {
    /*
        Approach: Brute Force
        The brute force approach to solve the celebrity problem is to check each person one by one.
        For each person, we check if they know anyone else and if everyone else knows them.
        If we find such a person, we return their index as the celebrity.
        If no such person is found, we return -1 indicating there is no celebrity.

        Time Complexity: O(n^2) where n is the number of people.
        Space Complexity: O(n) for the auxiliary arrays used to keep track of who knows whom.
    */

    public int findCelebrityBruteForce(int[][] M, int n) {
        int[] knowMe = new int[n];
        int[] iKnow = new int[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (M[i][j] == 1) {
                    iKnow[i]++;
                    knowMe[j]++;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (knowMe[i] == n - 1 && iKnow[i] == 0) {
                return i;
            }
        }

        return -1; // No celebrity found
    }

    /*
        Approach: Better Approach

        1. We can use two pointers to find a potential celebrity.
        2. Start with two pointers, one at the beginning (i = 0) and one at the end (j = n - 1) of the list of people.
        3. If person i knows person j, then i cannot be a celebrity, so we move the i pointer to the right (i++).
        4. If person i does not know person j, then j cannot be a celebrity, so we move the j pointer to the left (j--).
        5. Continue this process until the two pointers meet. The person at this position is a potential celebrity.
        6. Finally, we need to verify if this potential celebrity is indeed a celebrity by checking 
            if everyone knows them and they know no one.
        7. If the potential celebrity meets the criteria, return their index; otherwise, return -1.

        Since there can be only one celebrity, instead of finding the celebrity, the people that are not celebrity can be determined.
        if all such people are found, the remaining person will be the celebrity.
        The two conditions to identify the celebrity are:
        - The celebrity should be known by n-1(everyone) people.
        - The celebrity should not know anyone i.e., 0 people.

        Time Complexity: O(n) where n is the number of people.
        Space Complexity: O(1)
    
    */

    public int findCelebrityBetter(int[][] M, int n) {
        int i = 0;
        int j = n - 1;

        // Find the potential celebrity
        while (i < j) {
            if (M[i][j] == 1) {
                i++; // i knows j, so i cannot be a celebrity
            } else if(M[j][i] == 1) {
                j--; // i does not know j, so j cannot be a celebrity
            } else{
                i++; // if both don't know each other, we can eliminate either
                j--;
            }
        }

        if(i > j) return -1; // No potential celebrity found

        int candidate = i;

        // Verify the candidate
        for (int k = 0; k < n; k++) {
            if (k != candidate) {
                if (M[candidate][k] == 1 || M[k][candidate] == 0) {
                    return -1; // candidate knows someone or someone doesn't know candidate
                }
            }
        }

        return candidate; // candidate is the celebrity
    }

    /*
        Approach: Optimal Approach using Stack

        1. We can use a stack to keep track of potential celebrities.
        2. Push all people onto the stack initially.
        3. While there is more than one person in the stack, pop two people off the stack (A and B).
        4. If A knows B, then A cannot be a celebrity, so push B back onto the stack.
           If A does not know B, then B cannot be a celebrity, so push A back onto the stack.
        5. After processing all people, there will be one person left in the stack who is a potential celebrity.
        6. Finally, we need to verify if this potential celebrity is indeed a celebrity by checking 
            if everyone knows them and they know no one.
        7. If the potential celebrity meets the criteria, return their index; otherwise, return -1.

        Time Complexity: O(n) where n is the number of people.
        Space Complexity: O(n) for the stack used to store people.

    */
    public int findCelebrityOptimal(int[][] M, int n) {
        Stack<Integer> stack = new Stack<>();

        // Push all people onto the stack
        for (int i = 0; i < n; i++) {
            stack.push(i);
        }

        // Find the potential celebrity
        while (stack.size() > 1) {
            int A = stack.pop();
            int B = stack.pop();

            if (M[A][B] == 1) {
                stack.push(B); // A knows B, so A cannot be a celebrity
            } else {
                stack.push(A); // A does not know B, so B cannot be a celebrity
            }
        }

        if (stack.isEmpty()) return -1; // No potential celebrity found

        int candidate = stack.pop();

        // Verify the candidate
        for (int k = 0; k < n; k++) {
            if (k != candidate) {
                if (M[candidate][k] == 1 || M[k][candidate] == 0) {
                    return -1; // candidate knows someone or someone doesn't know candidate
                }
            }
        }

        return candidate; // candidate is the celebrity
    }
}

public class CelebrityProblem {
    public static void main(String[] args) {
        int[][] M = {
            {0, 1, 0},
            {0, 0, 0},
            {0, 1, 0}
        };
        int n = M.length;

        CelebrityProblemHelper helper = new CelebrityProblemHelper();

        int celebrityIndexBruteForce = helper.findCelebrityBruteForce(M, n);
        System.out.println("Celebrity Index (Brute Force): " + celebrityIndexBruteForce);

        int celebrityIndexBetter = helper.findCelebrityBetter(M, n);
        System.out.println("Celebrity Index (Better Approach): " + celebrityIndexBetter);

        int celebrityIndexOptimal = helper.findCelebrityOptimal(M, n);
        System.out.println("Celebrity Index (Optimal Approach): " + celebrityIndexOptimal);
    }
}
