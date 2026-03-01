package Graphs.Traversal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
    Given an undirected graph with V vertices number from 0 to V - 1 and E edges,
    the ith edge is represents by[ai, bi], denoting a edge between vertices ai and bi.
    we say two vertices u and v belong to the same component if there is a path from u to v or v to u.

    Find the number of components in the graph.


*/

/*

    - Maintain a boolean visited array to keep track of visited vertices. Start DFS/BFS from an unvisited vertex,
    marking all reachable nodes in that component as visited. 
    - Each time you start a new DFS/BFS, it indicates the discovery of a new component.
    - Another approach is to use a Disjoint Set Union (DSU) or Union-Find data structure to keep track of connected 
    components as you process the edges.
    - Initially each node is its own component. For each edge(a, b), merge the sets containing a and b. The number of 
    unique parents in the DSU structure gives the number of components.

    If the graph was stored as an edge list instead of an adjacency list,
    convert the edge list into an adjacency list before performing DFS/BFS. 
    This can be done by iterating through the edge list and populating the adjacency list accordingly.


    To optimize Union-Find for large graphs, implement path compression and union by rank/size.
    Path compression flattens the structure of the tree whenever find is called, while union by
    rank/size ensures that the smaller tree is always attached under the root of the larger tree, 
    keeping the overall structure flat and efficient.

*/

public class connectedComponents {
    public static void main(String[] args) {
        int V = 5;
        List<List<Integer>> edges = new ArrayList<>();
        edges.add(List.of(0, 1));
        edges.add(List.of(1, 2));
        edges.add(List.of(3, 4));

        Solution solution = new Solution();
        int numberOfComponents = solution.findNumberOfComponents(V, edges);
        System.out.println("Number of components in the graph: " + numberOfComponents);
    }
    
}

class Solution{
    private void dfs(int node, List<Integer>[] adj, boolean[] visited) {
        visited[node] = true;

        for(Integer neighbor : adj[node]) {
            if(!visited[neighbor]) {
                dfs(neighbor, adj, visited);
            }
        }
    }

    private void bfs(int start, List<Integer>[] adj, boolean[] visited) {
        visited[start] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);

        while(!queue.isEmpty()) {
            int node = queue.poll();

            for(Integer neighbor : adj[node]) {
                if(!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }
    }

    public int findNumberOfComponents(int V, List<List<Integer>> edges) {
        int E = edges.size();

        // Convert edge list to adjacency list
        List<Integer>[] adjLs = new ArrayList[V];
        for(int i = 0; i < V; i++) {
            adjLs[i] = new ArrayList<>();
        }

        for(int i = 0; i < E; ++i){
            adjLs[edges.get(i).get(0)].add(edges.get(i).get(1));
            adjLs[edges.get(i).get(1)].add(edges.get(i).get(0));
        }

        boolean[] visited = new boolean[V];
        int count = 0;

        for(int i = 0; i < V; i++) {
            if(!visited[i]) {
                count++;
                // dfs(i, adjLs, visited);
                bfs(i, adjLs, visited);
            }
        }
        return count;
    }
}
/*
    Time Complexity:
    - The time complexity of this approach is O(V + E), where V is the number of vertices and E is the number of edges in the graph. 
      This is because we visit each vertex and edge at most once during the DFS/BFS traversal.

    Space Complexity:
    - The space complexity is O(V) for the visited array and O(V) for the recursion stack in DFS or the queue in BFS, 
      resulting in an overall space complexity of O(V). In the worst case, if the graph is very deep, the recursion stack for DFS can also contribute to space complexity.

*/