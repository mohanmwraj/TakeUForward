#include <iostream>
#include <vector>
using namespace std;

/*
    For weight graphs,
    vector<vector<pair<int, int>>> adjacencyList(n);
    adjacencyList[u].push_back({v, weight});
    adjacencyList[v].push_back({u, weight});
    

*/

int main(){

    int n, m;
    cin >> n >> m;
    vector<vector<int>> adjacencyList(n);
    for (int i = 0; i < m; i++) {
        int u, v;
        cin >> u >> v;
        adjacencyList[u].push_back(v);
        adjacencyList[v].push_back(u);
    }
    
    for (int i = 0; i < n; i++) {
        cout << "Node " << i << ": ";
        for (int neighbor : adjacencyList[i]) {
            cout << neighbor << " ";
        }
        cout << endl;
    }

    return 0;
}