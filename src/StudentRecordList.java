// Task A2 — Student Service Records: Singly Linked List
// Custom singly linked list implementation.

public class StudentRecordList {

    private static class Node {
        Student data;
        Node next;

        Node(Student data) {
            this.data = data;
        }
    }

    private Node head;
    private int size;

    public StudentRecordList() {
        head = null;
        size = 0;
    }

    // Insert a student at the beginning
    public void insertAtBeginning(Student student) {
        Node newNode = new Node(student);

        newNode.next = head;
        head = newNode;
        size++;

        System.out.println("Inserted at beginning: " + student.name);
    }

    // Insert a student at the end
    public void insertAtEnd(Student student) {
        Node newNode = new Node(student);

        if (head == null) {
            head = newNode;
            size++;

            System.out.println("Inserted at end: " + student.name);
            return;
        }

        Node current = head;

        while (current.next != null) {
            current = current.next;
        }

        current.next = newNode;
        size++;

        System.out.println("Inserted at end: " + student.name);
    }

    // Insert a student at a specific 1-based position
    public void insertAtPosition(Student student, int position) {

        if (position < 1 || position > size + 1) {
            System.out.println("Invalid position: " + position);
            return;
        }

        if (position == 1) {
            insertAtBeginning(student);
            return;
        }

        if (position == size + 1) {
            insertAtEnd(student);
            return;
        }

        Node newNode = new Node(student);
        Node current = head;
        int index = 1;

        while (index < position - 1) {
            current = current.next;
            index++;
        }

        newNode.next = current.next;
        current.next = newNode;
        size++;

        System.out.println(
            "Inserted at position " + position + ": " + student.name
        );
    }

    // Existing method kept for compatibility with the menu system
    public void insertStudent(Student student, int position) {
        insertAtPosition(student, position);
    }

    // Existing method kept for compatibility
    public void insertStudentAtEnd(Student student) {
        insertAtEnd(student);
    }

    // Delete the student at the beginning
    public Student deleteAtBeginning() {

        if (head == null) {
            System.out.println("List is empty — nothing to delete.");
            return null;
        }

        Student deletedStudent = head.data;

        head = head.next;
        size--;

        System.out.println(
            "Deleted from beginning: " + deletedStudent.name
        );

        return deletedStudent;
    }

    // Delete the student at the end
    public Student deleteAtEnd() {

        if (head == null) {
            System.out.println("List is empty — nothing to delete.");
            return null;
        }

        if (head.next == null) {
            Student deletedStudent = head.data;

            head = null;
            size--;

            System.out.println(
                "Deleted from end: " + deletedStudent.name
            );

            return deletedStudent;
        }

        Node current = head;

        while (current.next.next != null) {
            current = current.next;
        }

        Student deletedStudent = current.next.data;

        current.next = null;
        size--;

        System.out.println(
            "Deleted from end: " + deletedStudent.name
        );

        return deletedStudent;
    }

    // Delete the student at a specific 1-based position
    public Student deleteAtPosition(int position) {

        if (position < 1 || position > size) {
            System.out.println("Invalid position: " + position);
            return null;
        }

        if (position == 1) {
            return deleteAtBeginning();
        }

        if (position == size) {
            return deleteAtEnd();
        }

        Node current = head;
        int index = 1;

        while (index < position - 1) {
            current = current.next;
            index++;
        }

        Student deletedStudent = current.next.data;

        current.next = current.next.next;
        size--;

        System.out.println(
            "Deleted from position " + position + ": "
            + deletedStudent.name
        );

        return deletedStudent;
    }

    // Existing delete method kept for compatibility with the menu system
    public boolean deleteStudent(String studentNo) {

        if (head == null) {
            System.out.println("List is empty — nothing to delete.");
            return false;
        }

        if (head.data.studentNo.equals(studentNo)) {
            deleteAtBeginning();
            return true;
        }

        Node current = head;

        while (current.next != null) {

            if (current.next.data.studentNo.equals(studentNo)) {

                Student deletedStudent = current.next.data;

                current.next = current.next.next;
                size--;

                System.out.println(
                    "Deleted: " + deletedStudent.name
                );

                return true;
            }

            current = current.next;
        }

        System.out.println(
            "Student " + studentNo + " not found — nothing deleted."
        );

        return false;
    }

    // Search for a student using student number
    public Student searchStudent(String studentNo) {

        Node current = head;
        int position = 1;

        while (current != null) {

            if (current.data.studentNo.equals(studentNo)) {

                System.out.println(
                    "Found at position " + position + ": "
                    + current.data
                );

                return current.data;
            }

            current = current.next;
            position++;
        }

        System.out.println(
            "Student " + studentNo + " not found."
        );

        return null;
    }

    // Display all student records
    public void displayStudents() {

        if (head == null) {
            System.out.println("No student records.");
            return;
        }

        System.out.println("---- Student Service Records ----");

        Node current = head;
        int position = 1;

        while (current != null) {

            System.out.println(
                position + ". " + current.data
            );

            current = current.next;
            position++;
        }

        System.out.println("----------------------------------");
    }

    public int getSize() {
        return size;
    }

    // Test the linked list operations
    public static void main(String[] args) {

        StudentRecordList list = new StudentRecordList();

        Student maria = new Student(
            "221045678",
            "Maria",
            "Registration",
            12
        );

        Student tomas = new Student(
            "222034512",
            "Tomas",
            "Student Card",
            5
        );

        Student simon = new Student(
            "221067341",
            "Simon",
            "Documents",
            4
        );

        Student ndapewa = new Student(
            "223041876",
            "Ndapewa",
            "Fees",
            8
        );

        Student helvi = new Student(
            "221099021",
            "Helvi",
            "Academic Enquiry",
            6
        );

        System.out.println("=== Insert at beginning ===");
        list.insertAtBeginning(maria);
        list.displayStudents();

        System.out.println("\n=== Insert at end ===");
        list.insertAtEnd(tomas);
        list.displayStudents();

        System.out.println("\n=== Insert at end again ===");
        list.insertAtEnd(simon);
        list.displayStudents();

        System.out.println("\n=== Insert at position 2 ===");
        list.insertAtPosition(ndapewa, 2);
        list.displayStudents();

        System.out.println("\n=== Search for Tomas ===");
        list.searchStudent("222034512");

        System.out.println("\n=== Delete from beginning ===");
        list.deleteAtBeginning();
        list.displayStudents();

        System.out.println("\n=== Delete from end ===");
        list.deleteAtEnd();
        list.displayStudents();

        System.out.println("\n=== Delete from position 2 ===");
        list.deleteAtPosition(2);
        list.displayStudents();

        System.out.println("\n=== Invalid position test ===");
        list.insertAtPosition(helvi, 10);

        System.out.println(
            "\nFinal list size: " + list.getSize()
        );
    }
}