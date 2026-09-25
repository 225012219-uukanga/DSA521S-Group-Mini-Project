// Shared record type used by the Queue (Task A1) and the Linked List (Task A2)
public class Student {
    String studentNo;
    String name;
    String serviceType;
    int estimatedTime; // minutes

    public Student(String studentNo, String name, String serviceType, int estimatedTime) {
        this.studentNo = studentNo;
        this.name = name;
        this.serviceType = serviceType;
        this.estimatedTime = estimatedTime;
    }

    @Override
    public String toString() {
        return studentNo + " | " + name + " | " + serviceType + " | " + estimatedTime + " min";
    }
}
