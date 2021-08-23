import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;

public class SortingAlgorithms {

    public static void bubbleSort(int[] arr,int size){
        for(int i=size-1; i>1; i--){
            for(int j=0; j<i; j++){
                if(arr[j]>arr[j+1]){
                    int temp = arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
        }
    }

    public static void selectionSort(int[] arr,int size){
        int minInd=0;
        for(int i = 0,j; i<size-1; i++){
            for(j=i+1,minInd=i; j<size; j++){
                if(arr[j]<arr[minInd]){
                    minInd=j;
                }
            }
            int temp = arr[minInd];
            arr[minInd]=arr[i];
            arr[i]=temp;
        }
    }



    public static void printArray(int[] arr,int size){
        for(int i=0; i<size; i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr ={1,6,2,9,7,3};
        selectionSort(arr,6);
        System.out.println(Arrays.toString(arr));
    }
}
