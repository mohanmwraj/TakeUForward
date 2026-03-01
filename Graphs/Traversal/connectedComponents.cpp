#include <vector>
#include <queue>
using namespace std;

class Solution{
private:
    void dfs(int node, vector<int>* adj, vector<int>& visited) {
        visited[node] = 1;

        for(int neighbor : adj[node]) {
            if(!visited[neighbor]) {
                dfs(neighbor, adj, visited);
            }
        }
    }

    void bfs(int node, vector<int>* adj, vector<int>& visited) {
        visited[node] = 1;
        queue<int> q;
        q.push(node);

        while(!q.empty()) {
            int current = q.front();
            q.pop();

            for(int neighbor : adj[current]) {
                if(!visited[neighbor]) {
                    visited[neighbor] = 1;
                    q.push(neighbor);
                }
            }
        }
    }

public:
    int countComponents(int V, vector<vector<int>> &edges) {
        int E = edges.size();
        vector<int> adjLs[V];

        for(const auto& edge : edges) {
            adjLs[edge[0]].push_back(edge[1]);
            adjLs[edge[1]].push_back(edge[0]);
        }

        vector<int> vis(V, 0);
        int count = 0;

        for(int i = 0; i < V; i++) {
            if(!vis[i]) {
                count++;
                //dfs(i, adjLs, vis);
                bfs(i, adjLs, vis);
            }
        }

        return count;
        // bool* visited = new bool[V]();
        // int count = 0;

        // // Convert edge list to adjacency list
        // vector<int>* adj = new vector<int>[V];


        // for(int i = 0; i < V; i++) {
        //     if(!visited[i]) {
        //         count++;
        //         dfs(i, adj, visited);
        //     }
        // }
        // delete[] visited;
        // delete[] adj;
        // return count;
    }
};

int main() {
    Solution sol;
    int V = 5;
    vector<vector<int>> edges = {{0, 1}, {1, 2}, {3, 4}};
    int result = sol.countComponents(V, edges);
    // Expected output: 2 (There are two connected components: {0, 1, 2} and {3, 4})
    return 0;
}   