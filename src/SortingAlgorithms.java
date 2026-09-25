// Part B — Sorting Algorithm Challenge
// Selection Sort, Insertion Sort, Merge Sort, Quick Sort — all implemented
// manually (no built-in sort()).
import java.util.Arrays;

public class SortingAlgorithms {

    // Counters — reset before each run via resetCounters()
    public static long comparisons;
    public static long swaps; // also used for "shifts" in insertion sort

    public static void resetCounters() {
        comparisons = 0;
        swaps = 0;
    }

    // ---------------- Task B1: Selection Sort ----------------
    // Repeatedly selects the smallest remaining value and swaps it into place.
    public static void selectionSort(int[] arr, boolean trace) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                comparisons++;
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
                swaps++;
            }
            if (trace && i < 3) {
                System.out.println("After pass " + (i + 1) + ": " + Arrays.toString(arr));
            }
        }
    }

    // ---------------- Task B2: Insertion Sort ----------------
    // Builds a sorted section at the front, shifting larger values right to
    // make room for each newly inserted value.
    public static void insertionSort(int[] arr, boolean trace) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0) {
                comparisons++;
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    swaps++; // counted as a shift
                    j--;
                } else {
                    break;
                }
            }
            arr[j + 1] = key;
            if (trace && i <= 3) {
                System.out.println("After pass " + i + ": " + Arrays.toString(arr));
            }
        }
    }

    // ---------------- Task B3: Merge Sort ----------------
    public static void mergeSort(int[] arr, boolean trace) {
        int[] temp = new int[arr.length];
        mergeSortHelper(arr, temp, 0, arr.length - 1, trace);
    }

    private static void mergeSortHelper(int[] arr, int[] temp, int left, int right, boolean trace) {
        if (left >= right) {
            // Base case: a sub-array of size 0 or 1 is already sorted
            if (trace) {
                System.out.println("Base case reached: [" + arr[left] + "]");
            }
            return;
        }
        int mid = (left + right) / 2;
        if (trace) {
            System.out.println("Dividing " + Arrays.toString(Arrays.copyOfRange(arr, left, right + 1))
                    + " into " + Arrays.toString(Arrays.copyOfRange(arr, left, mid + 1))
                    + " and " + Arrays.toString(Arrays.copyOfRange(arr, mid + 1, right + 1)));
        }
        mergeSortHelper(arr, temp, left, mid, trace);
        mergeSortHelper(arr, temp, mid + 1, right, trace);
        merge(arr, temp, left, mid, right, trace);
    }

    private static void merge(int[] arr, int[] temp, int left, int mid, int right, boolean trace) {
        for (int i = left; i <= right; i++) {
            temp[i] = arr[i];
        }
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            comparisons++;
            if (temp[i] <= temp[j]) {
                arr[k++] = temp[i++];
            } else {
                arr[k++] = temp[j++];
            }
        }
        while (i <= mid) arr[k++] = temp[i++];
        while (j <= right) arr[k++] = temp[j++];

        if (trace) {
            System.out.println("Merged into: " + Arrays.toString(Arrays.copyOfRange(arr, left, right + 1)));
        }
    }

    // ---------------- Task B4: Quick Sort ----------------
    // Pivot rule: last element of the current sub-array (Lomuto partition scheme).
    private static int partitionCallCount;

    public static void quickSort(int[] arr, boolean trace) {
        partitionCallCount = 0;
        quickSortHelper(arr, 0, arr.length - 1, trace);
    }

    private static void quickSortHelper(int[] arr, int low, int high, boolean trace) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high, trace);
            quickSortHelper(arr, low, pivotIndex - 1, trace);
            quickSortHelper(arr, pivotIndex + 1, high, trace);
        }
    }

    private static int partition(int[] arr, int low, int high, boolean trace) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            comparisons++;
            if (arr[j] < pivot) {
                i++;
                int t = arr[i]; arr[i] = arr[j]; arr[j] = t;
                swaps++;
            }
        }
        int t = arr[i + 1]; arr[i + 1] = arr[high]; arr[high] = t;
        swaps++;

        partitionCallCount++;
        if (trace && partitionCallCount <= 2) {
            System.out.println("Partition stage " + partitionCallCount + " — pivot: " + pivot);
            System.out.println("  Left partition:  " + Arrays.toString(Arrays.copyOfRange(arr, low, i + 1)));
            System.out.println("  Right partition: " + Arrays.toString(Arrays.copyOfRange(arr, i + 2, high + 1)));
        }
        return i + 1;
    }

    // ---------------- Demonstration on the fixed Part B array ----------------
    public static void main(String[] args) {
        int[] original = {17, 5, 23, 8, 14, 3, 11, 20, 6, 9};

        System.out.println("Original array: " + Arrays.toString(original));

        System.out.println("\n=== Task B1: Selection Sort ===");
        int[] a1 = Arrays.copyOf(original, original.length);
        resetCounters();
        selectionSort(a1, true);
        System.out.println("Sorted: " + Arrays.toString(a1));
        System.out.println("Comparisons: " + comparisons + " | Swaps: " + swaps);

        System.out.println("\n=== Task B2: Insertion Sort ===");
        int[] a2 = Arrays.copyOf(original, original.length);
        resetCounters();
        insertionSort(a2, true);
        System.out.println("Sorted: " + Arrays.toString(a2));
        System.out.println("Comparisons: " + comparisons + " | Shifts: " + swaps);

        System.out.println("\n=== Task B3: Merge Sort ===");
        int[] a3 = Arrays.copyOf(original, original.length);
        resetCounters();
        mergeSort(a3, true);
        System.out.println("Sorted: " + Arrays.toString(a3));
        System.out.println("Comparisons: " + comparisons);

        System.out.println("\n=== Task B4: Quick Sort ===");
        int[] a4 = Arrays.copyOf(original, original.length);
        resetCounters();
        quickSort(a4, true);
        System.out.println("Sorted: " + Arrays.toString(a4));
        System.out.println("Comparisons: " + comparisons + " | Swaps: " + swaps);
    }
}
