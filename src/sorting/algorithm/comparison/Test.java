/*
 * Developers: Hyoungjin Choi and Josiah Stadler
 * Date: 4/21/2020
 * Purpose: This program empirically compares the performance of Quicksort and Hybrid Quicksort
 */
package sorting.algorithm.comparison;

import java.text.NumberFormat;
import java.util.Random;
import java.util.Arrays;
import java.util.Locale;

/**
 *
 * @author Hyoungjin Choi
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int arrs = 1000;
        // k = 10
        tests(10, 20, arrs);
        tests(10, 100, arrs);
        tests(10, 1000, arrs);
        tests(10, 10000, arrs);

        // k = 20
        tests(20, 20, arrs);
        tests(20, 100, arrs);
        tests(20, 1000, arrs);
        tests(20, 10000, arrs);

        // k = 50
        tests(50, 20, arrs);
        tests(50, 100, arrs);
        tests(50, 1000, arrs);
        tests(50, 10000, arrs);

        // k = 100
        tests(100, 20, arrs);
        tests(100, 100, arrs);
        tests(100, 1000, arrs);
        tests(100, 10000, arrs);

        // k = 200
        tests(200, 20, arrs);
        tests(200, 100, arrs);
        tests(200, 1000, arrs);
        tests(200, 10000, arrs);

        // k = 500
        tests(500, 20, arrs);
        tests(500, 100, arrs);
        tests(500, 1000, arrs);
        tests(500, 10000, arrs);

        // k = 1,000
        tests(1000, 20, arrs);
        tests(1000, 100, arrs);
        tests(1000, 1000, arrs);
        tests(1000, 10000, arrs);

        // k = 2,000
        tests(2000, 20, arrs);
        tests(2000, 100, arrs);
        tests(2000, 1000, arrs);
        tests(2000, 10000, arrs);

        // k = 5,000
        tests(5000, 20, arrs);
        tests(5000, 100, arrs);
        tests(5000, 1000, arrs);
        tests(5000, 10000, arrs);

        // k = 10,000
        tests(10000, 20, arrs);
        tests(10000, 100, arrs);
        tests(10000, 1000, arrs);
        tests(10000, 10000, arrs);

        // n = 100,000
        tests(100000, 20, arrs);
        tests(100000, 100, arrs);
        tests(100000, 1000, arrs);
        tests(100000, 10000, arrs);
        tests(100000, 100000, arrs);

    }

    /**
     * 
     * @param k "Switch" for Insertion sort to kick in
     * @param n Size of the array
     * @param arrs Number of arrays to randomly generate
     */
    public static void tests(int k, int n, int arrs) {
        long begin, end;
        long t1 = 0, t2 = 0;
        Random rand = new Random();

        for (int i = 0; i < arrs; i++) {

            int[] a = new int[n];
            // Fill the array
            for (int j = 0; j < n; j++) {
                a[j] = rand.nextInt(10);
            }
            // duplicate the array so both sorting algorithms work with the same array
            int[] duplicate = Arrays.copyOf(a, n);

            begin = System.nanoTime();
            new SortingAlgorithmComparison().quickSort(a);
            end = System.nanoTime();
            t1 += (end - begin);
            //System.out.println("Quicksort \n" + Arrays.toString(a));
            //System.out.println("That took " + t1 + " nanoseconds! \n");

            begin = System.nanoTime();
            new SortingAlgorithmComparison().hybridQuickSort(duplicate, k);
            end = System.nanoTime();
            t2 += (end - begin);
            //System.out.println("Hybrid Quicksort \n" + Arrays.toString(duplicate));
            //System.out.println("That took " + t2 + " nanoseconds! \n");

        }
        System.out.println("For k = " + NumberFormat.getNumberInstance(Locale.US).format(k)
                + ", and " + NumberFormat.getNumberInstance(Locale.US).format(arrs) + " array(s) of size "
                + NumberFormat.getNumberInstance(Locale.US).format(n) + ";");
        if (arrs == 1) {
            System.out.println("Quicksort took: " + NumberFormat.getNumberInstance(Locale.US).format(t1 / arrs) + " nanoseconds.");
            System.out.println("Hybrid Quiscksort took: " + NumberFormat.getNumberInstance(Locale.US).format(t2 / arrs) + " nanoseconds.");
            if (t1 < t2) {
                System.out.println("Quicksort was faster.\n");
            } else {
                System.out.println("Hybrid Quicksort was faster.\n");
            }
        } else {
            System.out.println("On average, Quicksort took: " + NumberFormat.getNumberInstance(Locale.US).format(t1 / arrs) + " nanoseconds.");
            System.out.println("On average, Hybrid Quiscksort took: " + NumberFormat.getNumberInstance(Locale.US).format(t2 / arrs) + " nanoseconds.");
            long avgt1 = t1 / arrs;
            long avgt2 = t2 / arrs;
            if (avgt1 < avgt2) {
                System.out.println("On average, Quicksort was faster.\n");
            } else {
                System.out.println("On average, Hybrid Quicksort was faster.\n");
            }
        }
    }
}
