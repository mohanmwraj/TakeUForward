package BinaryTrees.Theory_Traversal;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
    Given the root of a binary tree, return the preorder traversal of its nodes' values.
    root = [1, 4, null, 4, 2]]
    Output: [1, 4, 4, 2]

    root = [5, 1, 2, 8, null, 4, 5, null, 6]
    Output: [5, 1, 8, 6, 2, 4, 5]
*/

public class preOrderTraversal {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);

        System.out.println(recursivePreOrderTraversal(root));
        System.out.println(iterativePreOrderTraversal(root));
    }

    /*
    
        Approach: Recursive Preorder Traversal
        - The function calls itself to process the current node first, then traverses the left subtree, 
        and finally traverses the right subtree.
        - This method is elegant and closely follows the definition of preorder traversal. 

        Preorder Traversal: Node -> Left -> Right
         - Visit the node first, then traverse the left subtree, and finally traverse the right subtree.
         - This traversal method is useful for creating a copy of the tree or getting a prefix expression of an 
         expression tree.

         Time Complexity: O(n) - Each node is visited once.
         Space Complexity: O(h) - The maximum space used by the recursion stack is proportional to the height of the tree, 
         which can be O(n) in the worst case (skewed tree) and O(log n) in the best case (balanced tree).
    
    */
    public static List<Integer> recursivePreOrderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preOrderHelper(root, result);
        return result;
    }

    private static void preOrderHelper(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        result.add(node.val); // Process the current node first
        preOrderHelper(node.left, result); // Traverse left subtree
        preOrderHelper(node.right, result); // Traverse right subtree
    }

    /*
        Approach: Iterative Preorder Traversal
        - Use a stack to simulate the recursive call stack.
        - Start from the root and push it onto the stack.
        - While the stack is not empty, pop a node from the stack, visit it, and then push its right child followed by its left child onto the stack (if they exist).
        - This ensures that the left child is processed before the right child, following the preorder traversal order.
        - This method is efficient and avoids the overhead of recursive function calls.

        Time Complexity: O(n) - Each node is visited once.
        Space Complexity: O(h) - The maximum space used by the stack is proportional to the height of the tree, which can be O(n) in the worst case (skewed tree
        and O(log n) in the best case (balanced tree).

    
    */

    private static List<Integer> iterativePreOrderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            result.add(current.val); // Visit the current node

            // Push right child first so that left child is processed first
            if (current.right != null) {
                stack.push(current.right);
            }
            if (current.left != null) {
                stack.push(current.left);
            }
        }

        return result;
    }
}

/*

> Why is preorder traversal useful?
Preorder traversal is useful for several reasons:
1. It allows you to create a copy of the tree. By visiting the node first, you can easily create a new node with the same value and then recursively copy the left and right subtrees.
2. It is used to get a prefix expression of an expression tree, which can be useful in certain applications like expression evaluation or code generation.
3. It can be used to serialize a binary tree, as it captures the structure of the tree in a way that can be easily reconstructed.

> How does the iterative approach ensure the correct order of traversal?
In the iterative approach, we use a stack to keep track of the nodes to be visited. When we pop a node from the stack, 
we visit it first (which is the "Node" part of the traversal). Then, we push the right child onto the stack before 
the left child. This is because stacks are Last-In-First-Out (LIFO) data structures, so by pushing the right child first, 
we ensure that the left child is processed before the right child when we pop from the stack. 
This maintains the correct order of traversal for preorder (Node -> Left -> Right).

> How would you modify this traversal for an n-ary tree?
For an n-ary tree, the preorder traversal would still follow the same principle of visiting the node first, 
but instead of just two children (left and right), you would need to iterate through
all the children of the node. The recursive approach would involve visiting the current node, 
then recursively calling the traversal function for each child in order. 
The iterative approach would involve using a stack to keep track of the nodes, and when popping a node, 
you would push all of its children onto the stack in reverse order (so that the first child is processed first).

> How would you reconstruct a binary tree from its preorder traversal?
To reconstruct a binary tree from its preorder traversal, you would need additional information, such as the inorder traversal of the tree. The preorder traversal alone does not provide enough information to uniquely reconstruct the tree
because it only gives the order of nodes as they are visited, but does not indicate the structure of the tree (i.e., which nodes are left or right children).
However, if you have both the preorder and inorder traversals, you can reconstruct the tree as follows:
1. The first element of the preorder traversal is the root of the tree.
2. Find the index of the root in the inorder traversal. This index will divide the inorder traversal into the left subtree (elements before the index) and the right subtree (elements after the index).
3. Recursively apply the same process to the left and right subtrees using the corresponding segments of the preorder and inorder traversals.

> How would you traverse the tree in a depth-first manner but modify the order?
To traverse the tree in a depth-first manner but modify the order, you can adjust the sequence of processing the node 
and its children. For example, instead of the standard preorder (Node -> Left -> Right), you could choose to visit the 
right child before the left child (Node -> Right -> Left) or even visit the node after visiting its 
children (Left -> Right -> Node). The key is to change the order in which you process the node and its children 
while still ensuring that you are traversing depth-first.



*/
