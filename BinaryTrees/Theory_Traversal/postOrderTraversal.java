package BinaryTrees.Theory_Traversal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/*
    Given the root of a binary tree, return the postorder traversal of its nodes' values.
    root = [1,null,2,3]
    Output: [3,2,1]

    root = [5, 1, 2, 8, null, 4, 5, null, 6]

    
                 5
               /   \
              1     2
             / \   / \
            8  null 4  5
           /  \ 
          null 6 

    
    Output: [6, 8, 1, 4, 5, 2, 5]
*/

public class postOrderTraversal {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);

        System.out.println(recursivePostOrderTraversal(root));
        System.out.println(iterativePostOrderTraversal(root));
    }

    /*
    
        Approach: Recursive Postorder Traversal
        - The function calls itself to traverse the left subtree, then traverses the right subtree, 
        and finally processes the current node.
        - This method is elegant and closely follows the definition of postorder traversal. 

        Postorder Traversal: Left -> Right -> Node
         - Traverse the left subtree first, then traverse the right subtree, and finally visit the node.
         - This traversal method is useful for deleting a tree or getting a postfix expression of an expression tree.

         Time Complexity: O(n) - Each node is visited once.
         Space Complexity: O(h) - The maximum space used by the recursion stack is proportional to the height of the tree, 
         which can be O(n) in the worst case (skewed tree) and O(log n) in the best case (balanced tree).
    
    */
    public static List<Integer> recursivePostOrderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postOrderHelper(root, result);
        return result;
    }

    private static void postOrderHelper(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        postOrderHelper(node.left, result); // Traverse left subtree
        postOrderHelper(node.right, result); // Traverse right subtree
        result.add(node.val); // Process the current node last
    }

    /*
        Approach: Iterative Postorder Traversal
        - Use a stack to simulate the recursive call stack.
         - The iterative postorder traversal can be more complex than preorder or inorder because you need to 
         ensure that you process the current node after both subtrees have been processed. 
         - One common approach is to use two stacks: one for processing nodes and another for storing the 
         order of nodes to be visited. 
         - Alternatively, you can modify the tree structure temporarily to keep track of visited nodes.

        A key observation is that if we traverst the tree in root -> right -> left order and then
        result will be a reverse of postorder traversal. This means that if we store nodes in this reversed order
        and then reverse the list at the end, we get the desired postorder traversal.


         Time Complexity: O(n) - Each node is visited once.
         Space Complexity: O(h) - The maximum space used by the stack is proportional to the height of the tree, 
         which can be O(n) in the worst case (skewed tree) and O(log n) in the best case (balanced tree).
    
     */
    
    
    private static List<Integer> iterativePostOrderTraversal(TreeNode root) {
       
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node.val); // Add the current node's value to the result list

            // Push left child first so that right child is processed first
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }

        // Reverse the result list to get the correct postorder traversal
        Collections.reverse(result);
        return result;


    }
    
}

/*

> Why is postorder traversal useful?
postorder traversal is particularly useful in scenarios where you need to process the children of a node before processing the node itself. For example:
1. Deleting a tree: When you want to delete a binary tree, you need to delete the children before deleting the parent node. Postorder traversal ensures that you visit the children before the parent, making it ideal for this task.
2. Expression trees: In expression trees, postorder traversal can be used to generate a postfix expression (Reverse Polish Notation) from an infix expression. This is useful for evaluating expressions without the need for parentheses.
3. File system traversal: When traversing a file system, you may want to process all files and subdirectories before processing the parent directory. Postorder traversal allows you to achieve this.

> How does postorder traversal behave for a skewed tree?
In a skewed tree (where each node has only one child), the postorder traversal will
visit the nodes in a linear order. For example, if the tree is skewed to the right (like a linked list), 
the postorder traversal will visit the nodes from the bottom up, starting from the leaf node and ending with the root node. 
If the tree is skewed to the left, it will also visit the nodes from the bottom up, but in reverse order compared to a 
right-skewed tree.

> How would you modify this traversal for an n-ary tree?

For an n-ary tree, the postorder traversal would still follow the same principle of visiting the children before the node,
but instead of just two children (left and right), you would need to iterate through all the children of the node.
The recursive approach would involve visiting the current node after recursively calling the traversal function for each child in order.
The iterative approach would involve using a stack to keep track of the nodes, and when popping a
node, you would push all of its children onto the stack in reverse order (so that the first child is processed last, ensuring that the node itself is processed after all its children).

> How would you modify this traversal to return nodes level by level?

To modify the postorder traversal to return nodes level by level, you would need to use a breadth-first approach
instead of a depth-first approach. This can be achieved using a queue instead of a stack.
You would enqueue the root node first, and then for each node dequeued, you would enqueue all of its children. 
You would also need to keep track of the levels of the nodes, which
can be done by using a marker (like null) to indicate the end of a level or by keeping track of the number of nodes 
at each level.


*/