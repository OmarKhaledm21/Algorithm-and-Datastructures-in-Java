import java.util.Arrays;

public class SortingAlgorithms {

    public static void printArray(int[] arr,int size){
        for(int i=0; i<size; i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }

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

    public static void insertionSort(int[] arr,int size){
        for(int i=1,j; i<size; i++){
            int temp = arr[i];
            for(j=i; j>0 && temp < arr[j-1]; j--){
                arr[j] = arr[j-1];
            }
            arr[j]=temp;
        }
    }

    public static void merge(int[] arr,int start,int mid, int end){
        int leftLength = mid - start +1;
        int rightLength = end - mid;

        int[] leftArray = new int[leftLength];
        int[] rightArray = new int[rightLength];

        for (int i = 0; i < leftLength; i++) {
            leftArray[i] = arr[start + i];
        }

        for (int j = 0; j < rightLength; j++) {
            rightArray[j] = arr[mid + 1 + j];
        }

        int i = 0, j = 0, k = start;

        while (i < leftLength && j < rightLength) {
            if (leftArray[i] < rightArray[j]) {
                arr[k] = leftArray[i];
                i++;
            }
            else {
                arr[k] = rightArray[j];
                j++;
            }

            k++;
        }

        while (i < leftLength) {
            arr[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < rightLength) {
            arr[k] = rightArray[j];
            j++;
            k++;
        }
    }

    public static void mergeSort(int[] arr,int start,int end){
        if(start<end){
            int mid = (start+end)/2;
            mergeSort(arr, start, mid);
            mergeSort(arr,mid+1,end);

            merge(arr,start,mid,end);
        }
    }

    public static void mergeSort(int[] arr,int size){
        mergeSort(arr,0,size-1);
    }


    static int partitionMiddle(int arr[],int l,int r,int pivot) {
        while (l <= r) {
            while (arr[l] < pivot) {
                l++;
            }

            while (arr[r] > pivot) {
                r--;
            }


            if (l <= r) {
                int temp = arr[l];
                arr[l]=arr[r];
                arr[r]=temp;
                l++;
                r--;
            }
        }
        return l;
    }

    static void quickSortMiddlePivot(int arr[],int l,int r) {
        if (l < r) {
            int pivot = arr[ (l+r) / 2 ];

            int pivotInRightPlaceIndex = partitionMiddle(arr,l,r,pivot);

            quickSortMiddlePivot(arr, l, pivotInRightPlaceIndex - 1);
            quickSortMiddlePivot(arr, pivotInRightPlaceIndex, r);
        }
    }

    static void quickSort(int arr[],int len) {
        quickSortMiddlePivot(arr, 0, len - 1);
        printArray(arr, len);
    }

    public static void main(String[] args) {
        int array[] = { 10, 8, 4, 80, 13, 1, 3, 11 };
        quickSort(array,8);
        System.out.println(Arrays.toString(array));

    }
}
