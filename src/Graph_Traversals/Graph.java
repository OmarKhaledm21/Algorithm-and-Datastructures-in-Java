package Graph_Traversals;
import java.util.*;

class Graph {
    private int Vertices;
    private LinkedList[] adj;

    Graph(int vertices) {
        Vertices = vertices;
        adj = new LinkedList[vertices];
        for (int i = 0; i< vertices; ++i) {
            adj[i] = new LinkedList();
        }
    }

    void addEdge(int v,int w) {
        adj[v].add(w);
    }

    void BFS(int s) {
        boolean[] visited = new boolean[Vertices];
        LinkedList<Integer> queue = new LinkedList<>();

        visited[s]=true;
        queue.add(s);

        while (queue.size() != 0) {
            s = queue.poll();
            System.out.print(s+" ");

            Iterator<Integer> i = adj[s].listIterator();
            while (i.hasNext()) {
                int n = i.next();
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
    }

    void DFSUtil(int v, boolean[] visited) {
        visited[v] = true;
        System.out.print(v + " ");

        Iterator<Integer> i = adj[v].listIterator();
        while (i.hasNext()) {
            int n = i.next();
            if (!visited[n])
                DFSUtil(n, visited);
        }
    }

    void DFS(int v) {
        boolean[] visited = new boolean[Vertices];
        DFSUtil(v, visited);
    }

    public static void main(String[] args) {
        Graph g = new Graph(4);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        System.out.println("Following is Breadth First Traversal (starting from vertex 2)");

        g.BFS(0);

        System.out.println("\nFollowing is Depth First Traversal " + "(starting from vertex 2)");
        g.DFS(1);


    }
}