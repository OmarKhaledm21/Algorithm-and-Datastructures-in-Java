import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Map<Integer,LinkedList<Integer>> map = new HashMap<>();

        map.put(10,new LinkedList<>());

        map.get(10).add(5);
        map.get(10).add(55);

        System.out.println(map.get(10).get(0));

        input.close();
    }


}
