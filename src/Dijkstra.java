import java.util.ArrayList;

public class Dijkstra {

    static int minDistance(int dist[], boolean sptSet[], int V) {
        //initializing minimum value.
        int min = Integer.MAX_VALUE, min_index = 0;

        for (int v = 0; v < V; v++)
            if (sptSet[v] == false && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }

        return min_index;
    }

    static int[] dijkstra(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj, int S) {
        int[][] adj_mat = new int[V][V];

        for (int i = 0; i < V; i++) {
            for (int j = 0; j < adj.get(i).size(); j++) {
                adj_mat[i][adj.get(i).get(j).get(0)] = adj.get(i).get(j).get(1);
            }
        }

        int[] dist = new int[V];
        boolean[] sptSet = new boolean[V];

        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

        dist[S] = 0;

        for (int count = 0; count < V - 1; count++) {
            int u = minDistance(dist, sptSet, V);
            sptSet[u] = true;

            for (int v = 0; v < V; v++) {
                if (!sptSet[v] && adj_mat[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + adj_mat[u][v] < dist[v])
                    dist[v] = dist[u] + adj_mat[u][v];
            }
        }
        //returning the list.
        return dist;
    }
}
