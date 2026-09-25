// Task A4 — Daily Statistics: Array
// Manual traversal only — no built-in max()/min()/sum()/average helpers.
public class DailyStatistics {

    private int[] serviceTimes;
    private int count; // number of slots actually filled

    public DailyStatistics(int capacity) {
        serviceTimes = new int[capacity];
        count = 0;
    }

    public void addServiceTime(int minutes) {
        if (count == serviceTimes.length) {
            System.out.println("Array is full — cannot add more service times.");
            return;
        }
        serviceTimes[count] = minutes;
        count++;
    }

    public int totalStudentsServed() {
        return count;
    }

    public int totalServiceTime() {
        int total = 0;
        for (int i = 0; i < count; i++) {
            total += serviceTimes[i];
        }
        return total;
    }

    public double averageServiceTime() {
        if (count == 0) return 0;
        return (double) totalServiceTime() / count;
    }

    public int highestServiceTime() {
        if (count == 0) return -1;
        int highest = serviceTimes[0];
        for (int i = 1; i < count; i++) {
            if (serviceTimes[i] > highest) highest = serviceTimes[i];
        }
        return highest;
    }

    public int lowestServiceTime() {
        if (count == 0) return -1;
        int lowest = serviceTimes[0];
        for (int i = 1; i < count; i++) {
            if (serviceTimes[i] < lowest) lowest = serviceTimes[i];
        }
        return lowest;
    }

    public int countLongerThan(int minutes) {
        int c = 0;
        for (int i = 0; i < count; i++) {
            if (serviceTimes[i] > minutes) c++;
        }
        return c;
    }

    public void displayStatistics() {
        System.out.println("---- Daily Statistics ----");
        System.out.println("Total students served: " + totalStudentsServed());
        System.out.println("Total service time: " + totalServiceTime() + " min");
        System.out.printf("Average service time: %.2f min%n", averageServiceTime());
        System.out.println("Highest service time: " + highestServiceTime() + " min");
        System.out.println("Lowest service time: " + lowestServiceTime() + " min");
        System.out.println("Services longer than 10 min: " + countLongerThan(10));
        System.out.println("---------------------------");
    }

    public static void main(String[] args) {
        DailyStatistics stats = new DailyStatistics(20);

        // Service times for students served during the day
        int[] sampleTimes = {12, 5, 8, 4, 6, 10, 15, 3, 9, 11};
        for (int t : sampleTimes) {
            stats.addServiceTime(t);
        }

        stats.displayStatistics();
    }
}
