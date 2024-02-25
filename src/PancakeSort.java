import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class PancakeSort {
    public List<Integer> pancakeSort(int[] arr) {
        List<Integer> output = new LinkedList<>();
        int k = arr.length - 1;
        while (k > 0) {
            int maxIndex = findMaxIndex(arr, k);
            if (maxIndex != k) {
                pancakeSort(arr, maxIndex);
                output.add(maxIndex + 1);
                pancakeSort(arr, k);
                output.add(k + 1);
            }
            k--;
        }
        return output;
    }

    /**
     * Pancake flips an array to the k-th index.
     * Example:
     * arr                  = [3, 2, 4, 5, 6]
     * flip(length - 1)     = [6, 5, 4, 2, 3]

     * In this example:
     * elem k-1 is swapped with elem 0 (6 with 3)
     * elem k-2 is swapped with elem 2 (5 with 2)
     * elem k-3 is swapped with elem 3 (4 with 4)
     * @param arr the array to perform flips
     * @param k the last index (inclusive)
     */
    private void pancakeSort(int[] arr, int k) {
        assert k < arr.length;
        int start = 0;
        int end = k;
        while (start < end) {
            int tmp = arr[start];
            arr[start++] = arr[end];
            arr[end--] = tmp;
        }
    }

    /**
     * Finds the max value given an array.
     * @param arr the array
     * @return the index of max value
     */
    private int findMaxIndex(int[] arr, int k) {
        int maxIndex = 0;
        for (int i = 1; i <= k; i++) {
            if (arr[i] > arr[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public static void main(String[] args) {
//        int[] arr = {1,2,3};
        int[] arr = {3,2,4,1};
//        int size = 100;
//        int[] arr = new int[size];
//        Random random = new Random();
//        for (int i = 0; i < arr.length; i++) {
//            arr[i] = random.nextInt(100);
//        }

        System.out.printf("before: %s\n", Arrays.toString(arr));
        PancakeSort pancake = new PancakeSort();
        List<Integer> result = pancake.pancakeSort(arr);
        System.out.println(result);
        System.out.printf("after: %s\n", Arrays.toString(arr));
    }
}
