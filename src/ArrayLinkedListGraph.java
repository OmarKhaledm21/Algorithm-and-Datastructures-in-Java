import java.util.LinkedList;

public class ArrayLinkedListGraph<T> {
    private LinkedList<Integer>[] arr;
    private int size;

    public  ArrayLinkedListGraph(int size){
        this.size = size;
        arr = new LinkedList[size];
        for(int i=0; i<size; i++){
            arr[i] = new LinkedList<>();
        }
    }

    public void addEdge(Integer source,Integer destination,boolean bidirectional){
        if(source<size && destination<size){
            arr[source].add(destination);
            if(bidirectional){
                arr[destination].add(source);
            }
        }
    }

    public void getVertexCount(){
        System.out.println("The graph has "+ size+" vertices");
    }

    public void getEdgesCount(boolean bidirectional){
        int count =0;
        for(int i=0; i<size; i++){
            count+=arr[i].size();
        }

        if(bidirectional){
            count = count/2;
        }

        System.out.println("The graph has "+count+" Edges");
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        for(int i=0; i<size; i++){
            builder.append(i+" : ");
            for(int j=0; j<arr[i].size(); j++){
                builder.append(arr[i].get(j).toString()+" ");
            }
            builder.append("\n");
        }
        return (builder.toString());
    }
}

class Driver{
    public static void main(String[] args) {
        ArrayLinkedListGraph<Integer> arrayLinkedListGraph = new ArrayLinkedListGraph<>(5);
        arrayLinkedListGraph.addEdge(0, 1, true);
        arrayLinkedListGraph.addEdge(0, 4, true);
        arrayLinkedListGraph.addEdge(1, 2, true);
        arrayLinkedListGraph.addEdge(1, 3, true);
        arrayLinkedListGraph.addEdge(1, 4, true);
        arrayLinkedListGraph.addEdge(2, 3, true);
        arrayLinkedListGraph.addEdge(3, 4, true);

        System.out.println("Graph:\n"+arrayLinkedListGraph.toString());
    }
}
