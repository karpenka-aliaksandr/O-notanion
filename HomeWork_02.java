public class HomeWork_02
{
    public static void main(String args[]){
        int arr[] = {12, 11, 13, 5, 6, 7, 7, 12, 3, 23, 234,432,43,23,1,3,5,1,2,5,1,2,2,3,4};
        System.out.println("Unsorted array is: ");
        printArray(arr);
        sortArray(arr);
        System.out.println("Sorted array is");
        printArray(arr);
    }

    static void sortArray(int arr[]){
        int len = arr.length;
        for (int i = len/2-1; i >= 0; i--) {
            screening(arr,len,i);
        }
        while (--len > 0) {
            //printArray(arr);
            int temp = arr[0];
            arr[0] = arr[len];
            arr[len] = temp;
            screening(arr, len, 0);
        }
    }
    static void screening(int arr[],int len, int i){
        if (i < len/2) {
            int child = i*2+1;
            if (child + 1 < len && arr[child] < arr[child+1]) {
                child++;
            }
            if (arr[child] > arr[i]) {
                int temp = arr[i];
                arr[i] = arr[child];
                arr[child] = temp;
                screening(arr, len, child); 
            }
        }
    }
   
    static void printArray(int arr[])
    {
        int n = arr.length;
        for (int i=0; i<n; ++i)
            System.out.print(arr[i]+" ");
        System.out.println();
    }     
}
    

