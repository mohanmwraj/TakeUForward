package Graphs.Traversal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
    Adjacency List:

    This is a node-based representation of a graph where each vertex maintains a list of its adjacent vertices. 
    It is more space-efficient than an adjacency matrix, especially for sparse graphs, 
    as it only stores the edges that exist in the graph.

    ArrayList<ArrayList<Integer>> adj = new ArrrayList<>(n + 1);
    for (int i = 0; i <= n; i++) {
        adj.add(new ArrayList<>());
    }

    For example, for the same graph with 5 vertices and edges (1-2), (1-3), (2-4), (3-4), (3-5), and (4-5), 
    the adjacency list would look like this:

    1: 2 3
    2: 1 4
    3: 1 4 5
    4: 2 3 5
    5: 3 4
    Space Complexity: O(n + m)/O(2*E) where n is the number of vertices and m is the number of edges,
    because we store each vertex and its adjacent vertices in the list.

    Weighted Graph Representation:
    In a weighted graph, we can modify the adjacency list to store pairs of (neighbor, weight) 
    instead of just the neighbor. 
    This can be done using a list of pairs or a list of custom objects that hold both the neighbor 
    and the weight of the edge.

    ArrayList<ArrayList<Integer>> adj = new ArrrayList<>(n + 1);
    for (int i = 0; i <= n; i++) {
        adj.add(new ArrayList<>());
    }

    Since there is no default way to store pairs in Java, we can create a custom class to represent the neighbor and the weight of the edge:
    public class Edge {
        int neighbor;
        int weight;

        public Edge(int neighbor, int weight) {
            this.neighbor = neighbor;
            this.weight = weight;
        }
    }

    Then, we can modify our adjacency list to store instances of the Edge class instead of just integers:
    ArrayList<ArrayList<Edge>> adj = new ArrrayList<>(n + 1);
    for (int i = 0; i <= n; i++) {
        adj.add(new ArrayList<>());
    }

    For example, if we have a weighted graph with edges (1-2, weight 3), (1-3, weight 5), (2-4, weight 2), 
    (3-4, weight 4), (3-5, weight 1), and (4-5, weight 6), the adjacency list would look like this:
    1: (2, 3) (3, 5)
    2: (1, 3) (4, 2)        
    3: (1, 5) (4, 4) (5, 1)
    4: (2, 2) (3, 4) (5, 6)
    5: (3, 1) (4, 6)

*/



public class adjacencyList {
    public static void main(String[] args) {
        int n = 5; // number of vertices
        int m = 6; // number of edges

        // Initialize the adjacency list
        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        // List of edges (u, v)
        int[][] edges = {
            {1, 2},
            {1, 3},
            {2, 4},
            {3, 4},
            {3, 5},
            {4, 5}
        };

        // Build the adjacency list
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adjacencyList.get(u).add(v);
            adjacencyList.get(v).add(u);
        }

        // Print the adjacency list
        for (int i = 1; i <= n; i++) {
            System.out.print(i + ": ");
            for (int neighbor : adjacencyList.get(i)) {
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
    }

    public static List<List<Integer>> createAdjacencyList() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // number of vertices
        int m = sc.nextInt(); // number of edges

        List<List<Integer>> adjacencyList = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            adjacencyList.get(u).add(v);
            adjacencyList.get(v).add(u);
        }
        sc.close();
        return adjacencyList;
    }
}
