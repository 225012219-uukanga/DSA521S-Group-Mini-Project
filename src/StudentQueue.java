// Task A1 — Waiting Line: Queue
// Custom linked-list-based queue (no built-in Java Queue/LinkedList used).
public class StudentQueue {

    private static class Node {
        Student data;
        Node next;
        Node(Student data) { this.data = data; }
    }

    private Node front;
    private Node rear;
    private int size;

    public StudentQueue() {
        front = null;
        rear = null;
        size = 0;
    }

    // enqueue(student): add a new student to the back of the line
    public void enqueue(Student student) {
        Node newNode = new Node(student);
        if (rear == null) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
        System.out.println("Enqueued: " + student.name + " (" + student.serviceType + ")");
    }

    // dequeue(): remove and return the student at the front
    public Student dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty — no student to serve.");
            return null;
        }
        Node served = front;
        front = front.next;
        if (front == null) rear = null;
        size--;
        System.out.println("Served: " + served.data.name + " (" + served.data.serviceType + ")");
        return served.data;
    }

    // peek(): look at the front student without removing them
    public Student peek() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
            return null;
        }
        return front.data;
    }

    public boolean isEmpty() {
        return front == null;
    }

    // displayQueue(): print all waiting students, front to back
    public void displayQueue() {
        if (isEmpty()) {
            System.out.println("No students currently waiting.");
            return;
        }
        System.out.println("---- Waiting Queue (front to back) ----");
        Node current = front;
        int position = 1;
        while (current != null) {
            System.out.println(position + ". " + current.data);
            current = current.next;
            position++;
        }
        System.out.println("----------------------------------------");
    }

    public int getSize() { return size; }

    public static void main(String[] args) {
        StudentQueue queue = new StudentQueue();

        System.out.println("=== Six students arrive ===");
        queue.enqueue(new Student("221045678", "Maria", "Registration", 12));
        queue.enqueue(new Student("222034512", "Tomas", "Student Card", 5));
        queue.enqueue(new Student("223041876", "Ndapewa", "Fees", 8));
        queue.enqueue(new Student("221067341", "Simon", "Documents", 4));
        queue.enqueue(new Student("221099021", "Helvi", "Academic Enquiry", 6));
        queue.enqueue(new Student("222011234", "Josef", "Registration", 10));

        System.out.println();
        queue.displayQueue();

        System.out.println("\n=== Three students are served ===");
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();

        System.out.println();
        queue.displayQueue();

        System.out.println("\nNext in line (peek): " + (queue.peek() != null ? queue.peek().name : "none"));
        System.out.println("Is queue empty? " + queue.isEmpty());
        System.out.println("Students still waiting: " + queue.getSize());
    }
}
