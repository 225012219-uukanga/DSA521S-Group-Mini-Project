// Task A2 — Student Service Records: Singly Linked List
// Custom singly linked list implementation.
public class StudentRecordList {

    private static class Node {
        Student data;
        Node next;
        Node(Student data) { this.data = data; }
    }

    private Node head;
    private int size;

    public StudentRecordList() {
        head = null;
        size = 0;
    }

    // insertStudent(): insert at beginning, end, or a specific 1-based position
    public void insertStudent(Student student, int position) {
        Node newNode = new Node(student);

        // Insert at beginning
        if (position <= 1 || head == null) {
            newNode.next = head;
            head = newNode;
            size++;
            System.out.println("Inserted at beginning: " + student.name);
            return;
        }

        // Walk to the node just before the target position
        Node current = head;
        int index = 1;
        while (index < position - 1 && current.next != null) {
            current = current.next;
            index++;
        }

        // Insert after 'current' (covers "insert at end" when current.next == null)
        newNode.next = current.next;
        current.next = newNode;
        size++;
        System.out.println("Inserted at position " + (index + 1) + ": " + student.name);
    }

    // Convenience overload: insert at the end
    public void insertStudentAtEnd(Student student) {
        insertStudent(student, size + 1);
    }

    // deleteStudent(): remove the record matching the given student number
    public boolean deleteStudent(String studentNo) {
        if (head == null) {
            System.out.println("List is empty — nothing to delete.");
            return false;
        }

        if (head.data.studentNo.equals(studentNo)) {
            System.out.println("Deleted: " + head.data.name);
            head = head.next;
            size--;
            return true;
        }

        Node current = head;
        while (current.next != null && !current.next.data.studentNo.equals(studentNo)) {
            current = current.next;
        }

        if (current.next == null) {
            System.out.println("Student " + studentNo + " not found — nothing deleted.");
            return false;
        }

        System.out.println("Deleted: " + current.next.data.name);
        current.next = current.next.next; // unlink the matched node
        size--;
        return true;
    }

    // searchStudent(): find and return a record by student number
    public Student searchStudent(String studentNo) {
        Node current = head;
        int position = 1;
        while (current != null) {
            if (current.data.studentNo.equals(studentNo)) {
                System.out.println("Found at position " + position + ": " + current.data);
                return current.data;
            }
            current = current.next;
            position++;
        }
        System.out.println("Student " + studentNo + " not found.");
        return null;
    }

    // displayStudents(): traverse and print every record
    public void displayStudents() {
        if (head == null) {
            System.out.println("No student records.");
            return;
        }
        System.out.println("---- Student Service Records ----");
        Node current = head;
        int position = 1;
        while (current != null) {
            System.out.println(position + ". " + current.data);
            current = current.next;
            position++;
        }
        System.out.println("----------------------------------");
    }

    public int getSize() { return size; }

    public static void main(String[] args) {
        StudentRecordList list = new StudentRecordList();

        System.out.println("=== Insert at end (Maria, Tomas, Simon) ===");
        list.insertStudentAtEnd(new Student("221045678", "Maria", "Registration", 12));
        list.insertStudentAtEnd(new Student("222034512", "Tomas", "Student Card", 5));
        list.insertStudentAtEnd(new Student("221067341", "Simon", "Documents", 4));
        list.displayStudents();

        System.out.println("\n=== Insert at beginning (Ndapewa) ===");
        list.insertStudent(new Student("223041876", "Ndapewa", "Fees", 8), 1);
        list.displayStudents();

        System.out.println("\n=== Insert at position 3 (Helvi) ===");
        list.insertStudent(new Student("221099021", "Helvi", "Academic Enquiry", 6), 3);
        list.displayStudents();

        System.out.println("\n=== Search for Tomas (222034512) ===");
        list.searchStudent("222034512");

        System.out.println("\n=== Delete Simon (221067341) ===");
        list.deleteStudent("221067341");
        list.displayStudents();
    }
}
