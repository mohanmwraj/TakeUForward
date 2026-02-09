package BinaryTrees.Theory_Traversal;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

/*
    Given the root of a binary tree, return the inorder traversal of its nodes' values.
    root = [1,null,2,3]
    Output: [1,3,2]

    root = [5, 1, 2, 8, null, 4, 5, null, 6]
    Output: [8, 6, 1, 5, 4, 2, 5]

*/

/*
    Approach: Recursive Inorder Traversal
    - The function calls itself to traverse the left subtree, then processes the current node, 
    and finally traverses the right subtree.
    - This method is elegant and closely follows the definition of inorder traversal.  

    Inorder Traversal: Left -> Node -> Right
     - Traverse the left subtree first, then visit the node, and finally traverse the right subtree.
     - This traversal method is commonly used to retrieve values from a binary search tree in sorted order.

        Time Complexity: O(n) - Each node is visited once.
        Space Complexity: O(h) - The maximum space used by the call stack is proportional to the height of the tree,
        which can be O(n) in the worst case (skewed tree) and O(log n) in the best case (balanced tree).

*/

/*
    can this traversal be modified for an n-ary tree?
    Yes, the inorder traversal can be modified for an n-ary tree, but it would require a different approach 
    since n-ary trees do not have a strict left and right child structure like  binary trees. In an n-ary tree, each node can have multiple children, 
    so the traversal would need to be defined in a way that specifies the order of visiting the children.
    One common approach for an n-ary tree is to visit the node first, then recursively visit each of its children in order.
    This would be a pre-order traversal for an n-ary tree. If you want to define an inorder-like traversal for an n-ary tree, 
    you could choose to visit the node after visiting a certain number of its children, but this would be less common and may not have a standard definition.

    How would you use inorder traversal to reconstruct a BST?
    To reconstruct a binary search tree (BST) from its inorder traversal, you can follow these steps:
    1. The inorder traversal of a BST will give you the values in sorted order. So, you can use the sorted list of values to reconstruct the BST.
    2. Start with the middle element of the sorted list as the root of the BST. This will ensure that the tree is balanced.
    3. Recursively repeat the process for the left half of the list to construct the left subtree and for the right half of the list to construct the right subtree.
    4. Continue this process until all elements have been used to construct the BST.

    How would you traverse the tree in a depth-first manner but modify the order?
    Modify the order by adjusting recursion or stack logic to change precedence of left, root or right processing.

    
*/

public class InorderTraversal {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);

        System.out.println(recursiveInorderTraversal(root));
        System.out.println(iterativeInorderTraversal(root));
    }

    public static List<Integer> recursiveInorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorderHelper(root, result);
        return result;
    }

    private static void inorderHelper(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        inorderHelper(node.left, result);
        result.add(node.val);
        inorderHelper(node.right, result);
    }

    /*
        Approach: Iterative Inorder Traversal using Stack
         - Use a stack to keep track of nodes while traversing the tree.
         - Start from the root and keep pushing left children onto the stack until you reach a null node.
         - Pop a node from the stack, visit it, and then move to its right child.
         - Repeat this process until the stack is empty and all nodes have been visited.

         Time Complexity: O(n) - Each node is visited once.
        Space Complexity: O(h) - The maximum space used by the stack is proportional to the height of the tree, 
        which can be O(n) in the worst case (skewed tree) and O(log n) in the best case (balanced tree).

    */

    public static List<Integer> iterativeInorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            result.add(current.val);
            current = current.right;
        }

        return result;
    }
}
