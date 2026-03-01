#include <iostream>
#include <vector>
using namespace std;

class Graph {
    int V; // Number of vertices
    vector<vector<int>> adjList; // Adjacency list representation   
public:
    Graph(int V) {
        this->V = V;
        adjList.resize(V);
    }   
    void addEdge(int u, int v) {
        adjList[u].push_back(v);
        adjList[v].push_back(u); // For undirected graph
    }
    void BFS(int start) {
        vector<bool> visited(V, false);
        vector<int> queue;
        visited[start] = true;

        queue.push_back(start);
        while (!queue.empty()) {
            int vertex = queue.front();
            cout << vertex << " ";
            queue.erase(queue.begin());
            for (int neighbor : adjList[vertex]) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;

                    queue.push_back(neighbor);
                }
            }
        }
    }
    void DFSUtil(int vertex, vector<bool>& visited) {
        visited[vertex] = true;
        cout << vertex << " ";
        for (int neighbor : adjList[vertex]) {          
            if (!visited[neighbor]) {
                DFSUtil(neighbor, visited);
            }
        }
    }
    void DFS(int start) {
        vector<bool> visited(V, false);
        DFSUtil(start, visited);    
    }
};