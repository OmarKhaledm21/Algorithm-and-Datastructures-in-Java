import java.util.ArrayList;

public class ArrayListGraphs {
    private int V;
    private ArrayList<ArrayList<Integer>> adj;

    public ArrayListGraphs(int V){
        this.V = V;
        adj = new ArrayList<ArrayList<Integer> >(V);
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<Integer>());
        }
    }
    // A utility function to add an edge in an
    // undirected graph
    public void addEdge(int u, int v)
    {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    // A utility function to print the adjacency list
    // representation of graph
    public void printGraph()
    {
        for (int i = 0; i < adj.size(); i++) {
            System.out.println("\nAdjacency list of vertex" + i);
            System.out.print("head");
            for (int j = 0; j < adj.get(i).size(); j++) {
                System.out.print(" -> "+adj.get(i).get(j));
            }
            System.out.println();
        }
    }

    // Driver Code
    public static void main(String[] args)
    {
        // Creating a graph with 5 vertices
        ArrayListGraphs arrayListGraphs = new ArrayListGraphs(5);

        // Adding edges one by one
        arrayListGraphs.addEdge( 0, 1);
        arrayListGraphs.addEdge( 0, 4);
        arrayListGraphs.addEdge( 1, 2);
        arrayListGraphs.addEdge( 1, 3);
        arrayListGraphs.addEdge( 1, 4);
        arrayListGraphs.addEdge( 2, 3);
        arrayListGraphs.addEdge( 3, 4);
        arrayListGraphs.printGraph();
    }
}

/*
Pros: Saves space O(|V|+|E|) . In the worst case, there can be C(V, 2) number of edges in a graph thus consuming O(V^2) space.
Adding a vertex is easier.
Cons: Queries like whether there is an edge from vertex u to vertex v are not efficient and can be done O(V).
* */
