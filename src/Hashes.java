public class Hashes {
    String[] theArray;
    int arraySize;
    int count=0;

    public Hashes(int size){
        this.arraySize =size;
        theArray = new String[size];
        for(int i=0; i<size; i++){
            theArray[i]="-1";
        }
    }

    public String findKey(String key){
        int index = Integer.parseInt(key)%29;
        while (theArray[index] != "-1"){
           if (theArray[index]==key) {
               System.out.println(key + " was found in " + index);
                return theArray[index];
           }
           ++index;
           index %= arraySize;
        }
        System.out.println("Key was not found!");
        return "";
    }

    public void HashFunction1(String[] strings, String[] theArray){
        for(int i=0; i<strings.length; i++){
            String newVal = strings[i];
            theArray[Integer.parseInt(newVal)] = newVal;
        }
    }

    public void HashFunction2(String[] strings, String[] theArray){
        for(int i=0; i<strings.length; i++){
            String newVal = strings[i];

            int index = Integer.parseInt(newVal)%29;
            System.out.println("Mod Index= "+index+" for value "+ newVal);
            while (theArray[index] != "-1"){
                System.out.println("Collision "+index+ " try "+(index+1));
                ++index;
                index %= arraySize;
            }
            System.out.println("Now in "+index);
            theArray[index] = newVal;
        }
    }

    public void displayTheStack() {

        int increment = 0;

        for (int m = 0; m < 3; m++) {

            increment += 10;

            for (int n = 0; n < 71; n++)
                System.out.print("-");

            System.out.println();

            for (int n = increment - 10; n < increment; n++) {

                System.out.format("| %3s " + " ", n);

            }

            System.out.println("|");

            for (int n = 0; n < 71; n++)
                System.out.print("-");

            System.out.println();

            for (int n = increment - 10; n < increment; n++) {

                if (theArray[n].equals("-1"))
                    System.out.print("|      ");

                else
                    System.out
                            .print(String.format("| %3s " + " ", theArray[n]));

            }

            System.out.println("|");

            for (int n = 0; n < 71; n++)
                System.out.print("-");

            System.out.println();

        }

    }




    public static void main(String[] args) {
        Hashes func = new Hashes(30);
        /*
        String[] elementsToAdd = { "1", "5", "17", "21", "26" };
        func.HashFunction1(elementsToAdd, func.theArray);
        func.displayTheStack();
        */

        String[] elementsToAdd2 = { "100", "510", "170", "214", "268", "398",
                "235", "802", "900", "723", "699", "1", "16", "999", "890",
                "725", "998", "978", "988", "990", "989", "984", "320", "321",
                "400", "415", "450", "50", "660", "624" };

        func.HashFunction2(elementsToAdd2, func.theArray);
        func.displayTheStack();
        func.findKey("660");

        //func.findKey("660");
    }
}
