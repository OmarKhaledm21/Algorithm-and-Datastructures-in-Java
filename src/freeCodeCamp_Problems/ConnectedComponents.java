package freeCodeCamp_Problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.LinkedList;

public class ConnectedComponents {
    private int vertices;
    private LinkedList<Integer>[] adjList;

    int count = 0; //no. of connected components
    Hashtable<Integer, ArrayList<Integer>> components = new Hashtable();
    boolean[] visited;


    public ConnectedComponents(int vertices){
        this.vertices = vertices;
        this.adjList = new LinkedList[vertices];
        for(int i=0; i<vertices; i++){
            adjList[i] = new LinkedList<>();
        }
        this.visited = new boolean[vertices];

    }

    public void addEdge(int v,int u){
        adjList[v].add(u);
        adjList[u].add(v);
    }

    public void printGraph(){
        for(int i=0; i<vertices; i++){
            System.out.println(adjList[i]);
        }
    }

    public void solveDFS(){
        for(int i=0; i<vertices; i++){
            if( !visited[i] ){
                count++;
                dfs(i);
            }
        }
        System.out.println("Count: "+count + "\nComponents: ");
        for(Integer key : components.keySet()){
            System.out.println(key+": "+components.get(key).toString());
        }
    }

    public void dfs(int node){
        visited[node] = true;

        if( components.get(count) == null){
            ArrayList<Integer> group = new ArrayList<>();
            group.add(node);
            components.put(count,group);
        }else{
            components.get(count).add(node);
        }

        for(var next : adjList[node]){
            if( !visited[next]){
                dfs(next);
            }
        }
    }

    public static void main(String[] args) {
        ConnectedComponents cc = new ConnectedComponents(18);

        cc.addEdge(6,11);
        cc.addEdge(7,11);
        cc.addEdge(6,7);

        cc.addEdge(0,4);
        cc.addEdge(0,13);
        cc.addEdge(0,14);
        cc.addEdge(0,8);
        cc.addEdge(13,14);
        cc.addEdge(14,8);
        cc.addEdge(4,8);

        cc.addEdge(1,5);
        cc.addEdge(5,17);
        cc.addEdge(5,16);

        cc.addEdge(3,9);
        cc.addEdge(9,15);
        cc.addEdge(9,2);
        cc.addEdge(15,2);
        cc.addEdge(15,10);

        //cc.printGraph();
        cc.solveDFS();
    }


}
