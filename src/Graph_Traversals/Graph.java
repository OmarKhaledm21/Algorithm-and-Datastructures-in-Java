package Graph_Traversals;
import java.util.*;

public class Graph {
    private final int Vertices;
    private final LinkedList<Integer>[] adj;

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
        Queue<Integer> queue = new LinkedList<>();

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

        LinkedList<Integer> adjVertices = adj[v];
        int i=0;
        while (i<adjVertices.size()) {
            int n = adjVertices.get(i);
            i++;
            if (!visited[n])
                DFSUtil(n, visited);
        }
    }

    void DFS(int v) {
        boolean[] visited = new boolean[Vertices];
        DFSUtil(v, visited);
    }

    void topologicalSortUtil(int v, boolean[] visited, Stack<Integer> stack) {
        visited[v] = true;
        int i;

        Iterator<Integer> it = adj[v].iterator();
        while (it.hasNext()) {
            i = it.next();
            if (!visited[i])
                topologicalSortUtil(i, visited, stack);
        }

        stack.push(v);
    }

    void topologicalSort() {
        Stack<Integer> stack = new Stack<Integer>();
        boolean[] visited = new boolean[Vertices];
        for (int i = 0; i < Vertices; i++) {
            visited[i] = false;
        }

        for (int i = 0; i < Vertices; i++) {
            if (!visited[i]) {
                topologicalSortUtil(i, visited, stack);
            }
        }

        while (!stack.empty()) {
            System.out.print(stack.pop() + " ");
        }
    }

    public static void main(String[] args) {
/*
        Graph g = new Graph(4);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        System.out.println("Following is Breadth First Traversal ");
        g.BFS(2);

        System.out.println("\nFollowing is Depth First Traversal ");
        g.DFS(2);
*/
        Graph g = new Graph(6);
        g.addEdge(5, 2);
        g.addEdge(5, 0);
        g.addEdge(4, 0);
        g.addEdge(4, 1);
        g.addEdge(2, 3);
        g.addEdge(3, 1);

        System.out.println("Following is a Topological "
                + "sort of the given graph");
        // Function Call
        g.topologicalSort();
    }
}