

# Graphs

A graph is a **non-linear data structure** consisting of nodes that have data and are connected to other nodes through edges. Graphs have wide-ranging applications in real life — analysis of electrical circuits, finding the shortest routes between two places, building navigation systems like Google Maps, social media platforms using graphs to store data about each user, etc.

```
    A --- B
    |     |
    C --- D
    |
    E
```

> This represents an undirected graph with 5 nodes (A, B, C, D, E) and 5 edges connecting them.

---

## 1. Terminology

### 1.1 Node (Vertex)

- Nodes are the fundamental units of a graph, representing entities or objects. They store the data.
- Nodes are circles represented by numbers (ordering does not matter).
- They are also referred to as **vertices**.

### 1.2 Edges (Links)

- Edges are connections between nodes, representing relationships or interactions between the nodes.
- Edges are represented by a line connecting two nodes; edges can also be termed as a pair of vertices.
- Edges can be of two types:
  - **Directed Edges:** Edges that have a direction, indicating a one-way relationship. Each edge is represented as an ordered pair `(u, v)`, meaning there is a connection from vertex `u` to vertex `v`.
  - **Undirected Edges:** Edges that do not have a direction, indicating a two-way relationship. Each edge is represented as an unordered pair `{u, v}`, meaning there is a mutual connection between vertex `u` and vertex `v`.

---

## 2. Types of Graphs

### 2.1 Directed Graph (Digraph)

A graph where all the edges are directed, i.e., unidirectional in nature. It contains an ordered pair of vertices. Each edge is represented by a directed pair `<u, v>`. Therefore `<u, v>` and `<v, u>` represent **two different edges**.

### 2.2 Undirected Graph

A graph where all the edges are undirected, i.e., bidirectional in nature. The pair of vertices representing any edge is unordered. Thus, the pairs `(u, v)` and `(v, u)` represent the **same edge**.

> **Note:** An undirected graph can be converted to a directed graph by replacing each undirected edge with two directed edges — one from node `u` to `v`, and another from node `v` to `u`.

### 2.3 Weighted Graph

A graph where edges have **weights (costs)** associated with them. Weight may represent distance, time, cost, capacity, etc.

### 2.4 Unweighted Graph

A graph where edges do **not** have weights associated with them.

**Example — Weighted Graph:**

```
    A ---5--- B
    |         |
    3         7
    |         |
    C ---4--- D
```

| Edge | Weight |
|------|--------|
| A-B  | 5      |
| A-C  | 3      |
| B-D  | 7      |
| C-D  | 4      |

> Weighted graphs are commonly used in GPS navigation, where weights represent distances or travel times between locations.

### 2.5 Connected Graph

An undirected graph in which there is a path between every pair of vertices. If the graph is directed and every vertex is reachable from every other vertex, it is called **strongly connected**.

### 2.6 Disconnected Graph

A graph where at least one pair of vertices has no path between them.

### 2.7 Complete Graph

A graph in which there is a unique edge between every pair of vertices. A complete graph with `n` vertices has `n(n-1)/2` edges (undirected) or `n(n-1)` edges (directed).

### 2.8 Bipartite Graph

A graph whose vertices can be divided into **two disjoint sets** `U` and `V` such that every edge connects a vertex in `U` to one in `V`. No edge connects vertices within the same set.

```
Set U: {1, 3}      Set V: {2, 4}

    1 --- 2
    |     |
    3 --- 4
```

> **Key Property:** A graph is bipartite **if and only if** it contains no odd-length cycle. Can be checked using BFS/DFS with 2-coloring.

### 2.9 Directed Acyclic Graph (DAG)

A directed graph with **no cycles**. DAGs are used in:
- Topological sorting
- Scheduling problems
- Dependency resolution (build systems, package managers)
- Longest/shortest path in DAGs

```
A → B → D
↓       ↑
C ------┘
```

### 2.10 Tree

A **connected acyclic undirected graph**. A tree with `n` nodes always has exactly `n - 1` edges. There is exactly **one unique path** between any two nodes.

```
        A
       / \
      B   C
     /
    D
```

### 2.11 Forest

A disjoint collection of trees (an acyclic undirected graph that may be disconnected).

---

## 3. Cycles in a Graph

A graph is said to have a **cycle** if it is possible to start from a node and return to the same node by traversing edges. A graph may or may not contain cycles.

- **Cyclic:** Graph having at least one cycle.
- **Acyclic:** Graph that does not have any cycle.

### Examples

**Cyclic Directed Graph:**

```
A → B
↓   ↓
D ← C
```

> Cycle: A → B → C → D → A

**Acyclic Directed Graph (DAG):**

```
A → B → D
↓
C → D
```

> No cycles. All edges flow in one direction without returning to any starting node.

**Cyclic Undirected Graph:**

```
A --- B
|     |
D --- C
```

> Cycle: A — B — C — D — A

**Acyclic Undirected Graph (Tree):**

```
        A
       / \
      B   C
     /
    D
```

> No cycles. Only one path between any two nodes.

---

## 4. Connected Components

It is not necessary that all nodes in a graph are connected to each other. A **component** is a maximal group of nodes that are connected to each other directly or indirectly and have no connection to nodes in any other group.

```
Component 1:        Component 2:        Component 3:
A --- B             E → F               I
|     |             ↓   ↓
C --- D             H ← G
```

- **Component 1:** Undirected graph with nodes A, B, C, D.
- **Component 2:** Directed graph with nodes E, F, G, H forming a cycle.
- **Component 3:** An isolated node I with no connections.

> Nodes within the same component are reachable from one another, but nodes from different components have no path between them.

---

## 5. Path in a Graph

A **path** is a sequence of vertices where each adjacent pair is connected by an edge. A path always contains unique nodes (no node appears twice).

| Type | Description |
|------|-------------|
| **Simple Path** | A path where no vertex is repeated |
| **Closed Path (Cycle)** | A path that starts and ends at the same vertex, with no other repetitions |

### Examples

**Valid Path:**

```
A --- B --- C --- D
```

> A → B → C → D is a valid path.

**Invalid Path:**

```
A --- B     D
      |    /
      C ---
```

> A → B → D is **not** a valid path because there is no direct edge between B and D.

---

## 6. Degree of a Node

The **degree** of a node is the number of edges connected to that node.

### 6.1 In an Undirected Graph

- **Degree** = total number of edges connected to the vertex.
- **Property:** Total degree of graph = `2 × E` (every edge contributes to two nodes).

```
    A --- B
    |  \  |
    |   \ |
    C --- D
```

| Node | Degree | Connected To |
|------|--------|-------------|
| A    | 3      | B, C, D     |
| B    | 2      | A, D        |
| C    | 2      | A, D        |
| D    | 3      | A, B, C     |

> Total degree = 3 + 2 + 2 + 3 = **10** = 2 × 5 edges

### 6.2 In a Directed Graph

- **In-degree:** Number of incoming edges to a vertex.
- **Out-degree:** Number of outgoing edges from a vertex.
- **Property:** Sum of all in-degrees = Sum of all out-degrees = Total number of edges.

```
    A → B
    ↓   ↓
    C → D
    ↑   ↓
    E ← F
```

| Node | In-degree | Out-degree |
|------|-----------|------------|
| A    | 0         | 2          |
| B    | 1         | 1          |
| C    | 1         | 1          |
| D    | 2         | 1          |
| E    | 1         | 1          |
| F    | 1         | 1          |

> Total in-degrees = Total out-degrees = **6** = Number of edges

---

## 7. Graph Representation

### 7.1 Adjacency Matrix

A 2D array of size `V × V` where `V` is the number of vertices. `matrix[i][j] = 1` (or weight) if there is an edge from vertex `i` to vertex `j`, otherwise `0`.

```
Graph:              Adjacency Matrix:
    0 --- 1             0  1  2  3
    |     |         0 [ 0, 1, 1, 0 ]
    2 --- 3         1 [ 1, 0, 0, 1 ]
                    2 [ 1, 0, 0, 1 ]
                    3 [ 0, 1, 1, 0 ]
```

| Property | Value |
|----------|-------|
| Space Complexity | O(V²) |
| Edge lookup | O(1) |
| Add edge | O(1) |
| Find all neighbors | O(V) |
| Best for | Dense graphs |

### 7.2 Adjacency List

An array of lists. Index `i` contains a list of all vertices adjacent to vertex `i`.

```
Graph:              Adjacency List:
    0 --- 1         0 → [1, 2]
    |     |         1 → [0, 3]
    2 --- 3         2 → [0, 3]
                    3 → [1, 2]
```

| Property | Value |
|----------|-------|
| Space Complexity | O(V + E) |
| Edge lookup | O(degree of node) |
| Add edge | O(1) |
| Find all neighbors | O(degree of node) |
| Best for | Sparse graphs (most real-world graphs) |

### 7.3 Edge List

A list of all edges represented as pairs `(u, v)` or triples `(u, v, w)` for weighted graphs.

```
Edges: [(0,1), (0,2), (1,3), (2,3)]
```

| Property | Value |
|----------|-------|
| Space Complexity | O(E) |
| Edge lookup | O(E) |
| Best for | Kruskal's algorithm, simple edge iteration |

> **Interview Tip:** Default to **adjacency list** representation unless the problem specifically needs O(1) edge lookup or the graph is dense.

---

## 8. Graph Traversal Algorithms

### 8.1 Breadth-First Search (BFS)

Explores all vertices at the current depth before moving to the next depth level. Uses a **queue**.

**Algorithm:**
1. Start from source node, mark visited, push to queue.
2. While queue is not empty:
   - Dequeue a node.
   - Process all its unvisited neighbors — mark visited and enqueue them.

| Property | Value |
|----------|-------|
| Time Complexity | O(V + E) |
| Space Complexity | O(V) |
| Data Structure | Queue |
| Finds Shortest Path? | Yes (unweighted graphs) |

**Applications:**
- Shortest path in unweighted graphs
- Level-order traversal
- Connected components
- Bipartite checking (2-coloring)
- Finding all nodes within a distance `k`

```java
void bfs(int source, List<List<Integer>> adj, boolean[] visited) {
    Queue<Integer> queue = new LinkedList<>();
    visited[source] = true;
    queue.offer(source);

    while (!queue.isEmpty()) {
        int node = queue.poll();
        System.out.print(node + " ");

        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                visited[neighbor] = true;
                queue.offer(neighbor);
            }
        }
    }
}
```

### 8.2 Depth-First Search (DFS)

Explores as far as possible along each branch before backtracking. Uses a **stack** (or recursion).

**Algorithm:**
1. Start from source node, mark visited.
2. Recursively visit all unvisited neighbors.

| Property | Value |
|----------|-------|
| Time Complexity | O(V + E) |
| Space Complexity | O(V) (recursion stack) |
| Data Structure | Stack / Recursion |
| Finds Shortest Path? | No |

**Applications:**
- Cycle detection
- Topological sorting
- Connected components / Strongly connected components
- Path finding
- Solving mazes

```java
void dfs(int node, List<List<Integer>> adj, boolean[] visited) {
    visited[node] = true;
    System.out.print(node + " ");

    for (int neighbor : adj.get(node)) {
        if (!visited[neighbor]) {
            dfs(neighbor, adj, visited);
        }
    }
}
```

> **BFS vs DFS:** Use **BFS** when you need the shortest path in unweighted graphs or level-by-level processing. Use **DFS** when you need to explore all paths, detect cycles, or perform topological sorting.

---

## 9. Cycle Detection

### 9.1 Undirected Graph

- **BFS/DFS:** If during traversal we encounter an already-visited node that is **not** the parent of the current node, a cycle exists.
- Time Complexity: O(V + E)

### 9.2 Directed Graph

- **DFS with coloring:** Use three states — `WHITE` (unvisited), `GRAY` (in current DFS path), `BLACK` (fully processed). If we encounter a `GRAY` node, a **back edge** exists → cycle detected.
- **Kahn's Algorithm (BFS):** If topological sort does not include all vertices, the graph has a cycle.
- Time Complexity: O(V + E)

---

## 10. Topological Sort

A linear ordering of vertices in a **DAG** such that for every directed edge `u → v`, vertex `u` comes before `v` in the ordering. Only possible for **Directed Acyclic Graphs**.

### 10.1 DFS-Based (Stack)

1. Perform DFS. After fully processing a node (all descendants visited), push it onto a stack.
2. Pop all elements from the stack → topological order.

### 10.2 Kahn's Algorithm (BFS-Based)

1. Compute in-degree of all vertices.
2. Enqueue all vertices with in-degree `0`.
3. While queue is not empty:
   - Dequeue vertex `u`, add to result.
   - For each neighbor `v` of `u`, decrement in-degree of `v`. If in-degree becomes `0`, enqueue `v`.
4. If result size ≠ V, the graph has a cycle.

| Property | Value |
|----------|-------|
| Time Complexity | O(V + E) |
| Space Complexity | O(V) |

**Applications:**
- Task scheduling, build systems, course prerequisites
- Detecting cycles in directed graphs (Kahn's)
- Longest path in a DAG

---

## 11. Shortest Path Algorithms

### 11.1 BFS (Unweighted Graphs)

- Works for **unweighted** graphs only.
- Time: O(V + E)

### 11.2 Dijkstra's Algorithm

- Works for graphs with **non-negative** edge weights.
- Uses a **min-heap (priority queue)**.
- Greedy approach: always processes the closest unvisited vertex.

| Property | Value |
|----------|-------|
| Time Complexity | O((V + E) log V) with min-heap |
| Space Complexity | O(V) |
| Negative weights? | **No** |

```java
void dijkstra(int src, List<List<int[]>> adj, int V) {
    int[] dist = new int[V];
    Arrays.fill(dist, Integer.MAX_VALUE);
    dist[src] = 0;

    // {distance, node}
    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
    pq.offer(new int[]{0, src});

    while (!pq.isEmpty()) {
        int[] curr = pq.poll();
        int d = curr[0], u = curr[1];

        if (d > dist[u]) continue;

        for (int[] edge : adj.get(u)) {
            int v = edge[0], w = edge[1];
            if (dist[u] + w < dist[v]) {
                dist[v] = dist[u] + w;
                pq.offer(new int[]{dist[v], v});
            }
        }
    }
}
```

### 11.3 Bellman-Ford Algorithm

- Works for graphs with **negative** edge weights.
- Can detect **negative weight cycles**.
- Relaxes all edges `V - 1` times.

| Property | Value |
|----------|-------|
| Time Complexity | O(V × E) |
| Space Complexity | O(V) |
| Negative weights? | **Yes** |
| Negative cycle detection? | **Yes** |

### 11.4 Floyd-Warshall Algorithm

- Finds shortest paths between **all pairs** of vertices.
- Uses **Dynamic Programming**.

| Property | Value |
|----------|-------|
| Time Complexity | O(V³) |
| Space Complexity | O(V²) |
| Negative weights? | Yes (no negative cycles) |
| Use case | All-pairs shortest path, small/dense graphs |

### 11.5 Shortest Path in a DAG

- Perform **topological sort**, then relax edges in topological order.
- Time: O(V + E) — faster than Dijkstra for DAGs.

> **Interview Decision Guide:**
>
> | Scenario | Algorithm |
> |----------|-----------|
> | Unweighted graph | BFS |
> | Non-negative weights | Dijkstra |
> | Negative weights | Bellman-Ford |
> | All-pairs shortest path | Floyd-Warshall |
> | DAG | Topological Sort + Relaxation |

---

## 12. Minimum Spanning Tree (MST)

A **spanning tree** of an undirected connected graph is a subgraph that is a tree and connects all vertices. The **MST** is the spanning tree with the minimum total edge weight.

**Properties:**
- Has exactly `V - 1` edges.
- A graph can have multiple MSTs with the same total weight.

### 12.1 Prim's Algorithm

- Greedy approach. Start from any node, always add the **minimum weight edge** that connects a visited node to an unvisited node.
- Uses a **min-heap** (priority queue).

| Property | Value |
|----------|-------|
| Time Complexity | O((V + E) log V) with min-heap |
| Best for | Dense graphs |

### 12.2 Kruskal's Algorithm

- Sort all edges by weight. Greedily add edges in increasing order, **skipping** edges that would form a cycle.
- Uses **Union-Find (Disjoint Set Union)** to detect cycles efficiently.

| Property | Value |
|----------|-------|
| Time Complexity | O(E log E) |
| Best for | Sparse graphs |

---

## 13. Disjoint Set Union (Union-Find)

A data structure that tracks a set of elements partitioned into disjoint (non-overlapping) subsets. Supports two main operations:

| Operation | Description | Optimized Complexity |
|-----------|-------------|---------------------|
| `find(x)` | Find the representative (root) of the set containing `x` | O(α(n)) ≈ O(1) |
| `union(x, y)` | Merge the sets containing `x` and `y` | O(α(n)) ≈ O(1) |

**Optimizations:**
- **Path Compression:** During `find()`, make every node point directly to the root.
- **Union by Rank/Size:** Always attach the smaller tree under the root of the larger tree.

```java
class DSU {
    int[] parent, rank;

    DSU(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
    }

    int find(int x) {
        if (parent[x] != x)
            parent[x] = find(parent[x]); // path compression
        return parent[x];
    }

    void union(int x, int y) {
        int px = find(x), py = find(y);
        if (px == py) return;
        if (rank[px] < rank[py]) { int t = px; px = py; py = t; }
        parent[py] = px;
        if (rank[px] == rank[py]) rank[px]++;
    }
}
```

**Applications:**
- Kruskal's MST algorithm
- Cycle detection in undirected graphs
- Connected components (dynamic)
- Network connectivity queries

---

## 14. Strongly Connected Components (SCC)

A **strongly connected component** is a maximal subgraph of a directed graph where every vertex is reachable from every other vertex.

### 14.1 Kosaraju's Algorithm

1. Perform DFS and push nodes to stack in order of finish time.
2. Transpose the graph (reverse all edges).
3. Pop nodes from stack and perform DFS on the transposed graph — each DFS gives one SCC.

- Time: O(V + E)

### 14.2 Tarjan's Algorithm

- Uses a single DFS pass with a stack and discovery/low-link values.
- Time: O(V + E)

**Applications:**
- Detecting mutually reachable groups in social networks
- 2-SAT problem
- Condensation graph (converting SCC to a DAG)

---

## 15. Bridges and Articulation Points

### 15.1 Bridge (Cut Edge)

An edge whose removal **disconnects** the graph (increases the number of connected components).

### 15.2 Articulation Point (Cut Vertex)

A vertex whose removal **disconnects** the graph.

- Both can be found using **Tarjan's algorithm** with DFS timer and low-link values.
- Time: O(V + E)

**Applications:**
- Finding critical connections in a network
- Identifying single points of failure

---

## 16. Important Graph Algorithms Summary

| Algorithm | Problem | Time Complexity | Key Data Structure |
|-----------|---------|----------------|--------------------|
| BFS | Traversal / Shortest path (unweighted) | O(V + E) | Queue |
| DFS | Traversal / Cycle detection / Topo sort | O(V + E) | Stack / Recursion |
| Dijkstra | Shortest path (non-negative weights) | O((V+E) log V) | Min-Heap |
| Bellman-Ford | Shortest path (negative weights) | O(V × E) | Array |
| Floyd-Warshall | All-pairs shortest path | O(V³) | 2D Array |
| Prim's | Minimum Spanning Tree | O((V+E) log V) | Min-Heap |
| Kruskal's | Minimum Spanning Tree | O(E log E) | Union-Find |
| Topological Sort | Linear ordering of DAG | O(V + E) | Stack / Queue |
| Kosaraju's | Strongly Connected Components | O(V + E) | Stack |
| Tarjan's | SCC / Bridges / Articulation Points | O(V + E) | Stack + Timer |
| Union-Find | Dynamic connectivity | O(α(n)) per op | Parent array |

---

## 17. Common FAANG Interview Patterns

### 17.1 Grid/Matrix as Graph

Treat each cell as a node. Each cell has up to 4 (or 8) neighbors. Common problems:
- Number of Islands (BFS/DFS/Union-Find)
- Flood Fill
- Rotting Oranges (multi-source BFS)
- Shortest Path in Binary Matrix (BFS)

### 17.2 Multi-Source BFS

Start BFS from **multiple sources simultaneously** (enqueue all sources initially). Used when you need the shortest distance from **any** source.

Examples: Rotting Oranges, 01 Matrix, Walls and Gates.

### 17.3 Topological Sort Problems

- Course Schedule (can you finish all courses?)
- Course Schedule II (find the order)
- Alien Dictionary (derive character ordering)
- Build order / Dependency resolution

### 17.4 Union-Find Problems

- Number of Connected Components
- Redundant Connection (find the extra edge causing a cycle)
- Accounts Merge
- Making a Large Island

### 17.5 Shortest Path Problems

- Network Delay Time (Dijkstra)
- Cheapest Flights Within K Stops (modified Bellman-Ford / BFS)
- Path with Minimum Effort (Dijkstra on grid)
- Swim in Rising Water (Dijkstra / Binary Search + BFS)

### 17.6 Backtracking on Graphs

- Word Search (DFS + backtracking on grid)
- All Paths From Source to Target (DFS)
- Clone Graph (BFS/DFS + HashMap)

---

## 18. Key Properties & Theorems to Remember

| Property | Details |
|----------|---------|
| Tree | Connected acyclic graph with `V - 1` edges |
| Bipartite check | No odd-length cycle ↔ 2-colorable (BFS/DFS) |
| Euler Path | Visit every **edge** exactly once. Exists if exactly 0 or 2 vertices have odd degree. |
| Euler Circuit | Visit every edge exactly once and return to start. Exists if all vertices have even degree. |
| Hamiltonian Path | Visit every **vertex** exactly once. NP-complete in general. |
| Handshaking Lemma | Sum of degrees = 2 × number of edges |
| Max edges (undirected) | V(V-1)/2 for simple graph |
| Max edges (directed) | V(V-1) for simple graph |
| DAG always has | At least one vertex with in-degree 0 (source) and one with out-degree 0 (sink) |
| Negative cycle | If Bellman-Ford can relax after V-1 iterations → negative cycle exists |