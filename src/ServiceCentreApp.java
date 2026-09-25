// Part D — Integrated Service-Centre System
// Ties the Queue (A1), Linked List (A2), Array Statistics (A4) and Sorting
// (Part B/C) into one menu-driven program. The postfix Stack (A3) is kept
// separate, per the project brief, and is not part of this menu.
import java.util.Arrays;
import java.util.Scanner;

public class ServiceCentreApp {

    private static StudentQueue queue = new StudentQueue();
    private static StudentRecordList recordList = new StudentRecordList();
    private static DailyStatistics stats = new DailyStatistics(200);
    private static int[] serviceTimesForSorting = {17, 5, 23, 8, 14, 3, 11, 20, 6, 9};

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        preloadSampleData();
        boolean running = true;

        while (running) {
            printMenu();
            int choice = readInt("Select option: ");

            switch (choice) {
                case 1: addToQueue(); break;
                case 2: serveNextStudent(); break;
                case 3: queue.displayQueue(); break;
                case 4: addStudentRecord(); break;
                case 5: recordList.displayStudents(); break;
                case 6: searchStudentRecord(); break;
                case 7: removeStudentRecord(); break;
                case 8: stats.displayStatistics(); break;
                case 9: sortServiceTimes(); break;
                case 10: runSortingExperiment(); break;
                case 11: running = false; System.out.println("Exiting. Goodbye!"); break;
                default: System.out.println("Invalid option. Please choose 1-11.");
            }
            System.out.println();
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("========================================");
        System.out.println(" CAMPUS SERVICE CENTRE");
        System.out.println("========================================");
        System.out.println("1. Add student to waiting queue");
        System.out.println("2. Serve next student (remove from queue)");
        System.out.println("3. Display waiting students");
        System.out.println("4. Add student service record (Linked List)");
        System.out.println("5. Display student service records");
        System.out.println("6. Search for student record");
        System.out.println("7. Remove student record");
        System.out.println("8. Display daily statistics");
        System.out.println("9. Sort service times");
        System.out.println("10. Run sorting experiment");
        System.out.println("11. Exit");
    }

    // ---- Option 1: Queue — enqueue ----
    private static void addToQueue() {
        System.out.print("Student No: ");
        String no = scanner.nextLine();
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Service Type: ");
        String type = scanner.nextLine();
        int time = readInt("Estimated Time (min): ");
        queue.enqueue(new Student(no, name, type, time));
    }

    // ---- Option 2: Queue — dequeue ----
    private static void serveNextStudent() {
        Student served = queue.dequeue();
        if (served != null) {
            stats.addServiceTime(served.estimatedTime); // feed Array stats (Option 8)
        }
    }

    // ---- Option 4: Linked List — insertion ----
    private static void addStudentRecord() {
        System.out.print("Student No: ");
        String no = scanner.nextLine();
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Service Type: ");
        String type = scanner.nextLine();
        int time = readInt("Estimated Time (min): ");
        int position = readInt("Insert at position (1 = beginning, or size+1 = end): ");
        recordList.insertStudent(new Student(no, name, type, time), position);
    }

    // ---- Option 6: Linked List — search ----
    private static void searchStudentRecord() {
        System.out.print("Enter Student No to search: ");
        String no = scanner.nextLine();
        recordList.searchStudent(no);
    }

    // ---- Option 7: Linked List — deletion ----
    private static void removeStudentRecord() {
        System.out.print("Enter Student No to remove: ");
        String no = scanner.nextLine();
        recordList.deleteStudent(no);
    }

    // ---- Option 9: Sorting algorithm(s) ----
    private static void sortServiceTimes() {
        System.out.println("Choose algorithm: 1) Selection 2) Insertion 3) Merge 4) Quick");
        int algoChoice = readInt("Choice: ");
        int[] copy = Arrays.copyOf(serviceTimesForSorting, serviceTimesForSorting.length);

        SortingAlgorithms.resetCounters();
        switch (algoChoice) {
            case 1: SortingAlgorithms.selectionSort(copy, true); break;
            case 2: SortingAlgorithms.insertionSort(copy, true); break;
            case 3: SortingAlgorithms.mergeSort(copy, true); break;
            case 4: SortingAlgorithms.quickSort(copy, true); break;
            default: System.out.println("Invalid choice."); return;
        }
        System.out.println("Sorted result: " + Arrays.toString(copy));
        System.out.println("Comparisons: " + SortingAlgorithms.comparisons);
    }

    // ---- Option 10: Sorting experiment ----
    private static void runSortingExperiment() {
        AlgorithmExperiment.main(new String[0]);
    }

    // ---- Helpers ----
    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine();
            try {
                return Integer.parseInt(line.trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a whole number.");
            }
        }
    }

    // Preloads some sample data so options 2/3/5/6/7/8 have data to work with immediately
    private static void preloadSampleData() {
        queue.enqueue(new Student("221045678", "Maria", "Registration", 12));
        queue.enqueue(new Student("222034512", "Tomas", "Student Card", 5));
        queue.enqueue(new Student("223041876", "Ndapewa", "Fees", 8));

        recordList.insertStudentAtEnd(new Student("221045678", "Maria", "Registration", 12));
        recordList.insertStudentAtEnd(new Student("222034512", "Tomas", "Student Card", 5));

        int[] sampleTimes = {12, 5, 8, 4, 6, 10, 15, 3, 9, 11};
        for (int t : sampleTimes) stats.addServiceTime(t);
    }
}
