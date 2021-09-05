package Graphs_HashMaps_LinkedLists;
import java.util.HashMap;
import java.util.LinkedList;

class Node {
    int n;
    boolean visited;

    Node(int n) {
        this.n = n;
        visited = false;
    }

    void visit() {
        visited = true;
    }

    void unvisit() {
        visited = false;
    }

    public boolean isVisited() {
        return visited;
    }
}

public class Graph {
    // Each node maps to a list of all his neighbors
    private HashMap<Node, LinkedList<Node>> adjacencyMap;
    private boolean directed;

    public Graph(boolean directed) {
        this.directed = directed;
        adjacencyMap = new HashMap<>();
    }

    public void addEdgeHelper(Node a, Node b) {
        LinkedList<Node> tmp = adjacencyMap.get(a);

        if (tmp != null) {
            tmp.remove(b);
        }
        else {
            tmp = new LinkedList<>();
        }
        tmp.add(b);
        adjacencyMap.put(a,tmp);
    }

    public void addEdge(Node source, Node destination) {

        // We make sure that every used node shows up in our .keySet()
        if (!adjacencyMap.containsKey(source)) {
            adjacencyMap.put(source, null);
        }

        if (!adjacencyMap.containsKey(destination)) {
            adjacencyMap.put(destination, null);
        }

        addEdgeHelper(source, destination);

        // If a graph is undirected, we want to add an edge from destination to source as well
        if (!directed) {
            addEdgeHelper(destination, source);
        }
    }

    public void printEdges() {
        for (Node node : adjacencyMap.keySet()) {
            System.out.print("The " + node.n + " has an edge towards: ");
            for (Node neighbor : adjacencyMap.get(node)) {
                System.out.print(neighbor.n + " ");
            }
            System.out.println();
        }
    }

    public boolean hasEdge(Node source, Node destination) {
        return adjacencyMap.containsKey(source) && adjacencyMap.get(source).contains(destination);
    }

    public void resetNodesVisited(){
        for(Node node : adjacencyMap.keySet()){
            node.unvisit();
        }
    }

    void breadthFirstSearch(Node node) {
        if (node == null)
            return;

        // Creating the queue, and adding the first node (step 1)
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            Node currentFirst = queue.removeFirst();

            // In some cases we might have added a particular node more than once before
            // actually visiting that node, so we make sure to check and skip that node if we have
            // encountered it before
            if (currentFirst.isVisited())
                continue;

            // Mark the node as visited
            currentFirst.visit();
            System.out.print(currentFirst.n + " ");

            LinkedList<Node> allNeighbors = adjacencyMap.get(currentFirst);

            // We have to check whether the list of neighbors is null before proceeding, otherwise
            // the for-each loop will throw an exception
            if (allNeighbors == null)
                continue;

            for (Node neighbor : allNeighbors) {
                // We only add unvisited neighbors
                if (!neighbor.isVisited()) {
                    queue.add(neighbor);
                }
            }
        }
        System.out.println();
    }

    void breadthFirstSearchModified(Node node) {
        breadthFirstSearch(node);

        for (Node n : adjacencyMap.keySet()) {
            if (!n.isVisited()) {
                breadthFirstSearch(n);
            }
        }
    }
}

class GraphShow {
    public static void main(String[] args) {

        Graph graph = new Graph(false);
        Node a = new Node(0);
        Node b = new Node(1);
        Node c = new Node(2);
        Node d = new Node(3);
        Node e = new Node(4);

        graph.addEdge(a,d);
        graph.addEdge(a,b);
        graph.addEdge(a,c);
        graph.addEdge(c,d);

        graph.printEdges();

        System.out.println();
        graph.breadthFirstSearch(a);
    }
}
