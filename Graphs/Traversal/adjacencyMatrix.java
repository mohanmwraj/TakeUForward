package Graphs.Traversal;

import java.util.Scanner;

/*

Adjacency Matrix is a way to represent a graph using a 2D array of size n * n. The rows and columns of the matrix 
represent the vertices of the graph, and the values in the matrix indicate whether there is an edge between the vertices.
Where n is the number of nodes in the graph. 

The value at matrix[i][j] is typically 1 (or the weight of the edge) if there is an edge from vertex i to vertex j, 
and 0 if there is no edge.

Input: 

    5 6
    1 2
    1 3
    2 4
    3 4
    3 5
    4 5

 Exaplation:
    The first line contains two integers, n and m, where n is the number of vertices and m is the number of edges in the graph. 
    The next m lines each contain two integers u and v, representing an edge between vertices u and v.

    For the given input, we have 5 vertices and 6 edges. The edges are:
    - Edge between vertex 1 and vertex 2
    - Edge between vertex 1 and vertex 3
    - Edge between vertex 2 and vertex 4
    - Edge between vertex 3 and vertex 4
    - Edge between vertex 3 and vertex 5
    - Edge between vertex 4 and vertex 5
    
    The adjacency matrix for this graph would look like this:

        0 1 1 0 0
        1 0 0 1 0
        1 0 0 1 1
        0 1 1 0 1
        0 0 1 1 0
    We define an adjacency matrix of size (n + 1) x (n + 1) to accommodate vertex numbering starting from 1. 
    The matrix is initialized with 0s, and we set the value to 1 for each edge present in the graph.

    Space Complexity: O(n^2) due to the adjacency matrix.

    Weighted Graph Representation:
    In a weighted graph, we can modify the adjacency matrix to store the weights of the edges instead of just 1s and 0s. 
    The value at matrix[i][j] would be the weight of the edge from vertex i to vertex j, and 0 if there
    is no edge. This allows us to represent weighted graphs using an adjacency matrix as well.

    For example, if we have a weighted graph with edges (1-2, weight 3), (1-3, weight 5), (2-4, weight 2), 
    (3-4, weight 4), (3-5, weight 1), and (4-5, weight 6), the adjacency matrix would look like this:

        0 3 5 0 0
        3 0 0 2 0
        5 0 0 4 1
        0 2 4 0 6
        0 0 1 6 0
*/


public class adjacencyMatrix {
    public static void main(String[] args) {
        int n = 5; // number of vertices
        int m = 6; // number of edges

        // Initialize the adjacency matrix
        int[][] adjacencyMatrix = new int[n + 1][n + 1];

        // List of edges (u, v)
        int[][] edges = {
            {1, 2},
            {1, 3},
            {2, 4},
            {3, 4},
            {3, 5},
            {4, 5}
        };

        // Fill the adjacency matrix based on the edges
        for (int i = 0; i < m; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            adjacencyMatrix[u][v] = 1; // Mark the edge from u to v
            adjacencyMatrix[v][u] = 1; // Mark the edge from v to u (since it's an undirected graph)
        }

        // Print the adjacency matrix
        System.out.println("Adjacency Matrix:");
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                System.out.print(adjacencyMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int[][] createAdjacencyMatrix() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // number of vertices
        int m = sc.nextInt(); // number of edges
        int[][] edges = new int[m][2];
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            edges[u][v] = 1;
            edges[v][u] = 1;
        }
        int[][] adjacencyMatrix = new int[n + 1][n + 1];

        for (int i = 0; i < m; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            adjacencyMatrix[u][v] = 1;
            adjacencyMatrix[v][u] = 1;
        }
        sc.close();
        return adjacencyMatrix;
    }
}
