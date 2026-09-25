// Part C — Algorithm Experiment
// Compares Selection, Insertion, Merge and Quick Sort on random arrays of
// size 20, 50, 100, 500, plus one almost-sorted 100-element array.
import java.util.Arrays;
import java.util.Random;

public class AlgorithmExperiment {

    private static final int[] SIZES = {20, 50, 100, 500};
    private static final Random RANDOM = new Random(42); // fixed seed = reproducible results

    // Generates an array of the given size filled with random ints in [0, 10000)
    private static int[] generateRandomArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = RANDOM.nextInt(10000);
        }
        return arr;
    }

    // Times a sort and returns {comparisons, elapsedNanos}
    private static long[] runTimedSort(String algorithm, int[] data) {
        SortingAlgorithms.resetCounters();
        long start = System.nanoTime();

        switch (algorithm) {
            case "Selection Sort": SortingAlgorithms.selectionSort(data, false); break;
            case "Insertion Sort": SortingAlgorithms.insertionSort(data, false); break;
            case "Merge Sort":     SortingAlgorithms.mergeSort(data, false); break;
            case "Quick Sort":     SortingAlgorithms.quickSort(data, false); break;
        }

        long end = System.nanoTime();
        return new long[]{SortingAlgorithms.comparisons, end - start};
    }

    private static void printRow(String algorithm, int size, long comparisons, long timeNs) {
        System.out.printf("%-15s %-10d %-15d %-15d%n", algorithm, size, comparisons, timeNs);
    }

    public static void main(String[] args) {
        String[] algorithms = {"Selection Sort", "Insertion Sort", "Merge Sort", "Quick Sort"};

        System.out.println("=== Part C: Sorting Performance Comparison ===\n");
        System.out.printf("%-15s %-10s %-15s %-15s%n", "Algorithm", "Size", "Comparisons", "Time (ns)");
        System.out.println("---------------------------------------------------------------");

        for (int size : SIZES) {
            int[] baseArray = generateRandomArray(size);

            for (String algorithm : algorithms) {
                // Give each algorithm an identical copy of the same values
                int[] copy = Arrays.copyOf(baseArray, baseArray.length);
                long[] result = runTimedSort(algorithm, copy);
                printRow(algorithm, size, result[0], result[1]);
            }
        }

        // ---- Additional test: almost-sorted 100-element array ----
        System.out.println("\n=== Almost-Sorted Test (100 elements, 5 neighbouring pairs swapped) ===\n");
        int[] almostSorted = generateRandomArray(100);
        SortingAlgorithms.mergeSort(almostSorted, false); // use our own sort to build the test input (no built-in sort used)
        // Swap five pairs of neighbouring values to make it "almost sorted"
        int[] swapPositions = {10, 25, 40, 60, 80};
        for (int pos : swapPositions) {
            int temp = almostSorted[pos];
            almostSorted[pos] = almostSorted[pos + 1];
            almostSorted[pos + 1] = temp;
        }

        System.out.printf("%-15s %-10s %-15s %-15s%n", "Algorithm", "Size", "Comparisons", "Time (ns)");
        System.out.println("---------------------------------------------------------------");
        for (String algorithm : algorithms) {
            int[] copy = Arrays.copyOf(almostSorted, almostSorted.length);
            long[] result = runTimedSort(algorithm, copy);
            printRow(algorithm, 100, result[0], result[1]);
        }
    }
}
