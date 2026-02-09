package BinaryTrees.Theory_Traversal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
    Given the root of a binary tree, return the level order traversal of its nodes' values.
    root = [3,9,20,null,null,15,7]
    Output: [[3],[9,20],[15,7]]

    root = [1]
    Output: [[1]]

*/

public class levelOrderTraversal {

    /*
        Approach: Iterative Level Order Traversal using a Queue
            - Use a queue to keep track of nodes at the current level.
            - Start by adding the root node to the queue.
            - While the queue is not empty, process nodes level by level:
                - Determine the number of nodes at the current level (size of the queue).
                - For each node at the current level, dequeue it, add its value to a temporary list, and enqueue its left and right children (if they exist).
                - After processing all nodes at the current level, add the temporary list to the result list.
        
        Time Complexity: O(n) - Each node is visited once.  
        Space Complexity: O(n) - The maximum space used by the queue is proportional to the number of nodes at the 
        largest level of the tree, which can be O(n) in the worst case (skewed tree) and O(log n) in the best case 
        (balanced tree).

    
    
    */
    
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        System.out.println(queueLevelOrderTraversal(root));
    }

    private static List<List<Integer>> queueLevelOrderTraversal(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                currentLevel.add(currentNode.val);

                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }

            result.add(currentLevel);
        }

        return result;
    }
}

/*
> How can you modify the traversal to print the zigzag(spiral) order?
    - To modify the level order traversal to print in zigzag (spiral) order, you can use a boolean flag to keep track of the current direction of traversal (left to right or right to left). 
    - use double ended queue (Deque) instead of a regular queue to facilitate adding nodes in both directions.
     - When processing each level, if the flag indicates left to right, add node values to the temporary list in normal order. If the flag indicates right to left, add node values in reverse order.
     - After processing each level, toggle the flag for the next level.


*/
