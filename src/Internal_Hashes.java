import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

public class Internal_Hashes {
    public static void main(String[] args) {
        Hashtable<String,Double> hashtable = new Hashtable<>(25,0.8f);
        Enumeration products;

        hashtable.put("apple",0.67);
        hashtable.put("milk",1.49);

        System.out.println(hashtable.get("milk").toString());


        System.out.println();
/*
        products = hashtable.keys();
        while (products.hasMoreElements()){
            String product = (String) products.nextElement();
            System.out.println();
        }
*/

        for (Map.Entry<String, Double> map: hashtable.entrySet()) {
            System.out.println(map.toString());
        }

    }
}
