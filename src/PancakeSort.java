import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Given an array of integers arr, sort the array by performing a series of pancake flips.
 * In one pancake flip we do the following steps:
 *  1. Choose an integer k where 1 <= k <= arr.length.
 *  2. Reverse the sub-array arr[0...k-1] (0-indexed).
 * For example, if arr = [3,2,1,4] and we performed a pancake flip choosing k = 3,
 * we reverse the sub-array [3,2,1], so arr = [1,2,3,4] after the pancake flip at k = 3.
 * Return an array of the k-values corresponding to a sequence of pancake flips that sort arr.
 * Any valid answer that sorts the array within 10 * arr.length flips will be judged as correct.

 * Example 1:
 *      Input: arr = [3,2,4,1]
 *      Output: [4,2,4,3]
 *      Explanation:
 *      We perform 4 pancake flips, with k values 4, 2, 4, and 3.
 *      Starting state: arr = [3, 2, 4, 1]
 *      After 1st flip (k = 4): arr = [1, 4, 2, 3]
 *      After 2nd flip (k = 2): arr = [4, 1, 2, 3]
 *      After 3rd flip (k = 4): arr = [3, 2, 1, 4]
 *      After 4th flip (k = 3): arr = [1, 2, 3, 4], which is sorted.

 * Example 2:
 *      Input: arr = [1,2,3]
 *      Output: []
 *      Explanation: The input is already sorted, so there is no need to flip anything.
 *      Note that other answers, such as [3, 3], would also be accepted.
 */
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
        int[] arr = {3,2,4,1};

        System.out.printf("before: %s\n", Arrays.toString(arr));
        PancakeSort pancake = new PancakeSort();
        List<Integer> result = pancake.pancakeSort(arr);
        System.out.println(result);
        System.out.printf("after: %s\n", Arrays.toString(arr));
    }
}
