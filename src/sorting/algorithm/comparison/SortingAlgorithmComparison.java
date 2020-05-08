/*
 * Developers: Hyoungjin Choi and Josiah Stadler
 * Date: 4/21/2020
 * Purpose: This class implements Quicksort and Hybrid Quicksort
 */
package sorting.algorithm.comparison;

/**
 *
 * @author Hyoungjin Choi
 */
public class SortingAlgorithmComparison {

    /**
     * @param a Sample integer array
     */
    public void quickSort(int[] a) {
        if(a.length < 1){
            throw new IllegalArgumentException("Array size less than 1!");
        }
        if(a.length == 1){
            return;
        }
        if(a.length == 2){
            if(a[0] > a[1]){
                swap(a, 0, 1);
            }
            else{
                swap(a, 1, 0);
            }
        }
        quickSort(a, 0, a.length - 1);
    }

    /**
     * @param a int array sample
     * @param p Lower bound
     * @param r Upper bound
     */
    private void quickSort(int[] a, int p, int r) {
        while (p < r) {
            int q = partition(a, p, r);
            // tail recursion
            if (q - p < r - q) {
                quickSort(a, p, q - 1);
                p = q + 1;
            } else {
                quickSort(a, q + 1, r);
                r = q - 1;
            }
        }
    }

    /**
     * @param a Sample integer array
     * @param k "Switch" to for Insertion sort to kick in @ a size less than or
     * equal to k
     */
    public void hybridQuickSort(int[] a, int k) {
        if(a.length < 1){
            throw new IllegalArgumentException("Array size less than 1!");
        }
        if(k < 1){
            throw new IllegalArgumentException("k less than 1!");
        }
        if(a.length == 1){
            return;
        }
        if(a.length == 2){
            if(a[0] > a[1]){
                swap(a, 0, 1);
            }
            else{
                swap(a, 1, 0);
            }
        }
        hybridQuickSort(a, 0, a.length - 1, k);
    }

    /**
     * @param a Sample integer array
     * @param p Lower bound index
     * @param r Upper bound index
     * @param k "Switch" to for Insertion sort to kick in @ a size less than or
     * equal to k
     */
    private void hybridQuickSort(int[] a, int p, int r, int k) {
        while (p < r) {
            if (r - p + 1 > k) {
                int q = partition(a, p, r);
                // tail recursion to cut down on time
                if (q - p < r - q) {
                    hybridQuickSort(a, p, q - 1, k);
                    p = q + 1;
                } else {
                    hybridQuickSort(a, q + 1, r, k);
                    r = q - 1;
                }
            } else {
                insertionSort(a, p, r);
                return;
            }
        }
    }

    /**
     * @param a Sample integer array
     * @param p Lower bound from quicksort subarray
     * @param r Upper bound from quicksort subarray
     */
    public void insertionSort(int[] a, int p, int r) {
        for (int i = p + 1; i <= r; i++) {
            int currentElement = a[i];
            int j = i;
            while(j > p && a[j - 1] > currentElement){
                a[j] = a[j - 1];
                j--;
            }
            a[j] = currentElement;
        }
    }

    /**
     * @param a int array sample
     * @param p Lower bound
     * @param r Upper bound
     * @return Pivot index for quicksort
     */
    private int partition(int[] a, int p, int r) {
        // Picking pivot using median of three
        int pivot = medianOfThree(a, p, r);
        int i = p - 1;
        for (int j = p; j <= r - 1; j++) {
            if (a[j] <= pivot) {
                i++;
                swap(a, i, j);
            }
        }
        i++;
        swap(a, i, r);
        return i;
    }

    /**
     * @param a Sample integer array
     * @param i First element index
     * @param j Second elementindex
     */
    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    /**
     * @param a Sample integer array
     * @param left First integer
     * @param right Second integer
     * @return Median of the three integers
     */
    private int medianOfThree(int[] a, int left, int right) {
        int middle = (left + right) / 2;
        if (a[left] > a[middle]) {
            swap(a, left, middle);
        }
        if (a[left] > a[right]) {
            swap(a, left, right);
        }
        if (a[middle] > a[right]) {
            swap(a, middle, right);
        }
        swap(a, middle, right);
        return a[right];
    }
}
