package Searching_Sorting;

public class SearchAlgorithms {
    public static boolean linearSearch(int[] arr,int value,int size){
        for(int i=0; i<size; i++){
            if(arr[i] == value){
                return true;
            }
        }
        return false;
    }

    public static boolean binarySearch(int[] arr,int value,int size){
        int lowIndex=0;
        int highIndex=size-1;
        while (lowIndex<highIndex){
            int midIndex = (lowIndex+highIndex)/2;
            if(arr[midIndex]<value){
                lowIndex=midIndex+1;
            }else if(arr[midIndex]>value){
                highIndex=midIndex-1;
            }else{
                return true;
            }
        }
        return false;
    }
}
