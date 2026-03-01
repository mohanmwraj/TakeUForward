package Graphs.Traversal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
    Given an undirected graph with V vertices number from 0 to V - 1,
    the task is to implement both Depth First Search (DFS) and Breadth First Search (BFS) for the graph starting from the 0th vertex. 
   
    Note: The task is to complete the functions DFS() and BFS() which are used to perform DFS and BFS respectively. 
    The functions take an integer V denoting the number of vertices and an adjacency list adj as input 
    and return a list containing the DFS and BFS traversal of the graph starting from the 0th vertex.

    * Use recursion(or stack) for DFS to explore as deep as possible before backtracking. Maintain 
    a visited array to mark visited nodes and avoid cycles. Start from vertex 0, and visit nodes in the order 
    they appear in the adjacency list adj[i].

    * Use a queue for BFS to explore nodes level by level(FIFO order). Begin from vertex 0, mark it as visited, and enqueue it. 
    Then, repeatedly dequeue a vertex, visit its unvisited neighbors in the order they appear in the adjacency list adj[i], 
    mark them as visited, and enqueue them until the queue is empty.

    To find the shortest path in an unweighted graph,
    use BFS, as it guarantees the shortest path in O(V + E). Track parents nodes to reconstruct the path.

*/

public class traversalTechnique {
    
    /*
    
        Breadth First Search (BFS), also known as level order traversal. 
        Breadth First Search (BFS) is a traversal technique that explores all the neighbors of a node before 
        moving to the next level of neighbors. It uses a queue data structure to keep track of the nodes to be explored next.

        Approach:
        1. Initialize a queue and a visited array to keep track of visited nodes.
        2. Start from the 0th vertex, mark it as visited, and enqueue it.
        3. While the queue is not empty:
            a. Dequeue a vertex from the queue and add it to the BFS traversal list.
            b. For each unvisited neighbor of the dequeued vertex, mark it as visited and enqueue it.
        4. Return the BFS traversal list.
    
    */

    public List<Integer> bfsOfGraph(int v, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[v];
        List<Integer> bfsTraversal = new ArrayList<>();

        for(int i = 0; i < v; i++) {
            if(!visited[i]) {
                bfsTraversal.addAll(bfs(i, adj, visited));
            }
        }

        return bfsTraversal;
    }

    private List<Integer> bfs(int start, ArrayList<ArrayList<Integer>> adj, boolean[] visited) {
        List<Integer> traversal = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();

        visited[start] = true;
        queue.offer(start);

        while(!queue.isEmpty()) {
            int vertex = queue.poll();
            traversal.add(vertex);

            for(int neighbor : adj.get(vertex)) {
                if(!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }

        return traversal;
    }

    /*
        Depth First Search (DFS) is a traversal technique that explores as far as possible along each branch before backtracking. 
        It uses a stack data structure (or recursion) to keep track of the nodes to be explored next.

        Approach:
        1. Initialize a visited array to keep track of visited nodes.
        2. Start from the 0th vertex, mark it as visited, and add it to the DFS traversal list.
        3. For each unvisited neighbor of the current vertex, recursively perform DFS on that neighbor.
        4. Return the DFS traversal list.
    
    */

    public List<Integer> dfsOfGraph(int v, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[v];
        List<Integer> dfsTraversal = new ArrayList<>();

        for(int i = 0; i < v; i++) {
            if(!visited[i]) {
                dfs(i, adj, visited, dfsTraversal);
            }
        }

        return dfsTraversal;
    }

    private void dfs(int vertex, ArrayList<ArrayList<Integer>> adj, boolean[] visited, List<Integer> traversal) {
        visited[vertex] = true;
        traversal.add(vertex);

        for(int neighbor : adj.get(vertex)) {
            if(!visited[neighbor]) {
                dfs(neighbor, adj, visited, traversal);
            }
        }
    }
}
