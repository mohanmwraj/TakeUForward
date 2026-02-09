# Binary Trees - Complete Guide

Binary trees are a fundamental **hierarchical data structure** that allow organization of data in a tree-like, non-linear fashion. Each node can branch out, creating a structure that resembles a tree expanding at each level.

---

## 📌 Table of Contents

- [What is a Binary Tree?](#what-is-a-binary-tree)
- [Key Terminology](#key-terminology)
- [Types of Binary Trees](#types-of-binary-trees)
- [Binary Tree Properties](#binary-tree-properties)
- [Common Operations & Time Complexities](#common-operations--time-complexities)
- [FAANG Interview Essentials](#faang-interview-essentials)

---

## What is a Binary Tree?

> A **Binary Tree** is a hierarchical data structure where each node can have **at most two children**, referred to as the **left child** and the **right child**.

### Basic Structure in Java

```java
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    
    TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}
```

### Visual Example

```
        1          <- Root Node
       / \
      2   3        <- Children of 1
     / \   \
    4   5   6      <- 4, 5 are children of 2; 6 is child of 3
   /
  7                <- Leaf Node
```

---

## Key Terminology

| Term | Definition | Example (from tree above) |
|------|------------|---------------------------|
| **Node** | Contains data and references to left/right children | All numbered elements (1-7) |
| **Root** | Topmost node; entry point for traversal | Node `1` |
| **Parent** | Node with children connected below it | Node `2` is parent of `4` and `5` |
| **Children** | Nodes directly connected below a parent | `4`, `5` are children of `2` |
| **Leaf Node** | Node with no children (terminal nodes) | Nodes `5`, `6`, `7` |
| **Ancestors** | All nodes on path from a node to root | Ancestors of `7`: `4`, `2`, `1` |
| **Descendants** | All nodes in subtree rooted at a node | Descendants of `2`: `4`, `5`, `7` |
| **Siblings** | Nodes sharing the same parent | `2` and `3` are siblings |
| **Height** | Longest path from node to a leaf | Height of tree = 3 |
| **Depth** | Distance from root to the node | Depth of node `4` = 2 |
| **Level** | All nodes at same depth | Level 2: nodes `4`, `5`, `6` |

---

## Types of Binary Trees

### 1. Full Binary Tree (Strict Binary Tree)

> Every node has **exactly 0 or 2 children**. No node has only one child.

```
       1
      / \
     2   3
    / \
   4   5
```

**Properties:**
- If there are `n` leaf nodes, there are `n - 1` internal nodes
- Total nodes = `2n - 1` where n is the number of leaf nodes

---

### 2. Complete Binary Tree

> All levels are completely filled **except possibly the last level**, which is filled from **left to right**.

```
       1
      / \
     2   3
    / \  /
   4  5 6
```

**Properties:**
- Used in **Heap** data structures (Min-Heap, Max-Heap)
- Height = ⌊log₂(n)⌋
- Can be efficiently stored in an array (parent at `i`, left child at `2i+1`, right child at `2i+2`)

---

### 3. Perfect Binary Tree

> All internal nodes have **exactly two children** and all **leaf nodes are at the same level**.

```
       1
      / \
     2   3
    / \ / \
   4  5 6  7
```

**Properties:**
- Number of nodes at level `h` = 2^h
- Total nodes = 2^(h+1) - 1
- Number of leaf nodes = (n + 1) / 2

---

### 4. Balanced Binary Tree

> Heights of left and right subtrees of **every node differ by at most 1**.

```
       1
      / \
     2   3
    / \
   4   5
```
✅ Balanced (height difference ≤ 1)

```
       1
      /
     2
    /
   3
```
❌ Not Balanced (height difference = 2)

**Properties:**
- Height = O(log n)
- Examples: **AVL Trees**, **Red-Black Trees**
- Ensures efficient O(log n) operations

---

### 5. Degenerate (Skewed) Tree

> Each parent has only **one child**, forming a linear structure like a linked list.

```
1                    1
 \                  /
  2       OR       2
   \              /
    3            3
```
(Right Skewed)    (Left Skewed)

**Properties:**
- Height = n - 1 (worst case)
- Operations become O(n) instead of O(log n)
- Represents **worst-case** scenario for BST operations

---

## Binary Tree Properties

### Important Formulas

| Property | Formula |
|----------|---------|
| Max nodes at level `L` | 2^L |
| Max nodes in tree of height `H` | 2^(H+1) - 1 |
| Min height with `N` nodes | ⌊log₂(N)⌋ |
| Max height with `N` nodes | N - 1 |
| Leaf nodes in Full Binary Tree | (N + 1) / 2 |
| Internal nodes with `L` leaves (Full BT) | L - 1 |

### Height vs Depth

```
        1      <- Depth: 0, Height: 3
       / \
      2   3    <- Depth: 1, Height: 2
     / \
    4   5      <- Depth: 2, Height: 1
   /
  6            <- Depth: 3, Height: 0 (Leaf)
```

---

## Common Operations & Time Complexities

| Operation | Balanced Tree | Skewed Tree |
|-----------|---------------|-------------|
| Search | O(log n) | O(n) |
| Insertion | O(log n) | O(n) |
| Deletion | O(log n) | O(n) |
| Traversal | O(n) | O(n) |
| Find Height | O(n) | O(n) |
| Find Depth | O(log n) | O(n) |

---

## FAANG Interview Essentials

### 🔥 Must-Know Traversal Techniques

1. **DFS (Depth-First Search)**
   - **Inorder** (Left → Root → Right) - Gives sorted order in BST
   - **Preorder** (Root → Left → Right) - Used for serialization
   - **Postorder** (Left → Right → Root) - Used for deletion

2. **BFS (Breadth-First Search)**
   - **Level Order Traversal** - Uses Queue

### 📝 Common Interview Patterns

| Pattern | Example Problems |
|---------|------------------|
| **Tree Traversal** | Inorder, Preorder, Postorder, Level Order |
| **Height/Depth** | Max Depth, Min Depth, Balanced Tree Check |
| **Path Problems** | Root to Leaf Sum, Path Sum, Max Path Sum |
| **Construction** | Build from Inorder+Preorder, Serialize/Deserialize |
| **LCA Problems** | Lowest Common Ancestor (BST & BT variations) |
| **View Problems** | Left View, Right View, Top View, Bottom View |
| **Modification** | Invert Tree, Flatten to LinkedList |

### 💡 Key Interview Tips

1. **Recursion is your friend** - Most tree problems have elegant recursive solutions
2. **Always consider edge cases:**
   - Empty tree (`root == null`)
   - Single node tree
   - Skewed trees (all left or all right)
   
3. **Space Complexity matters:**
   - Recursive: O(h) stack space where h = height
   - Iterative with stack/queue: O(n) in worst case

4. **Know when to use which traversal:**
   - Need sorted order from BST? → **Inorder**
   - Need to copy/serialize tree? → **Preorder**
   - Need to delete tree? → **Postorder**
   - Need level-by-level? → **BFS/Level Order**

5. **Morris Traversal** - O(1) space traversal (important for optimization questions)

### 🎯 Top 15 Must-Practice Problems

| # | Problem | Difficulty | Key Concept |
|---|---------|------------|-------------|
| 1 | Maximum Depth of Binary Tree | Easy | Recursion, DFS |
| 2 | Invert Binary Tree | Easy | Recursion |
| 3 | Same Tree | Easy | Comparison |
| 4 | Symmetric Tree | Easy | Mirror check |
| 5 | Level Order Traversal | Medium | BFS |
| 6 | Validate BST | Medium | Inorder, Range check |
| 7 | Lowest Common Ancestor | Medium | Recursion |
| 8 | Binary Tree Right Side View | Medium | BFS/DFS |
| 9 | Construct from Preorder & Inorder | Medium | Divide & Conquer |
| 10 | Serialize and Deserialize | Hard | BFS/DFS |
| 11 | Binary Tree Maximum Path Sum | Hard | DFS, Global max |
| 12 | Diameter of Binary Tree | Medium | Height calculation |
| 13 | Flatten BT to Linked List | Medium | Preorder modification |
| 14 | Vertical Order Traversal | Hard | BFS + Sorting |
| 15 | Morris Traversal | Medium | Threading |

### ⚡ Quick Revision - Tree Types Summary

| Type | Key Property | Use Case |
|------|--------------|----------|
| **Full** | 0 or 2 children | Expression trees |
| **Complete** | Left-filled levels | Heaps, Priority Queues |
| **Perfect** | All leaves same level | Theoretical analysis |
| **Balanced** | Height diff ≤ 1 | AVL, Red-Black Trees |
| **Degenerate** | Single child chain | Worst-case analysis |

---

## Additional Resources

- Practice on: **LeetCode**, **GeeksforGeeks**, **HackerRank**
- Visualize trees: [VisuAlgo](https://visualgo.net/en/bst)
- Time yourself: Aim to solve Easy in 10-15 min, Medium in 20-25 min

---

> 💡 **Pro Tip**: In FAANG interviews, always clarify if it's a Binary Tree or Binary Search Tree (BST). The approaches can differ significantly!

---

*Happy Coding! 🚀*
