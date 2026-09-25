# DSA521S — Group Mini-Project 2026
## NUST Service Centre Simulation

**Group Number:** 2

**Group Members:**
| Student Number | Full Name |
|---|---|
| 225012219 | Hileni NN Uukanga |
| 223053376 | Junior Mupetami |
| 224086391 | Festus Shinedima Mt |
| 223122386 | Rebazar Allembe |
| 221019855 | Naomi kamenye |
| 225082470 | Maria N Tjoya |

**Submitted by:** 225012219 – Hileni NN Uukanga

**GitHub Repository:** https://github.com/225012219-uukanga/DSA521S-Group-Mini-Project.git

## How to Run

All source files are plain Java with no external dependencies (default package, no build tool required).

1. Compile everything:
   ```
   javac src/*.java
   ```
2. Run any individual task demo (each has its own `main` method):
   ```
   java -cp src StudentQueue          # Task A1
   java -cp src StudentRecordList     # Task A2
   java -cp src PostfixEvaluator      # Task A3
   java -cp src DailyStatistics       # Task A4
   java -cp src SortingAlgorithms     # Part B
   java -cp src AlgorithmExperiment   # Part C
   ```
3. Run the integrated menu-driven system (Part D):
   ```
   java -cp src ServiceCentreApp
   ```

## File Guide

| File | Task | Description |
|---|---|---|
| `Student.java` | — | Shared record type used by the Queue and Linked List |
| `StudentQueue.java` | A1 | Queue (linked-list based) for the waiting line |
| `StudentRecordList.java` | A2 | Singly linked list of student service records |
| `PostfixEvaluator.java` | A3 | Stack-based postfix expression evaluator |
| `DailyStatistics.java` | A4 | Array-based daily statistics |
| `SortingAlgorithms.java` | B1–B4 | Selection, Insertion, Merge, and Quick Sort with comparison/swap counters |
| `AlgorithmExperiment.java` | Part C | Performance comparison across input sizes 20/50/100/500 + almost-sorted test |
| `ServiceCentreApp.java` | Part D | Integrated menu tying Queue, Linked List, Array stats, and Sorting together |

## Notes

- No built-in Java `Stack`, `Queue`, `LinkedList`, or sorting methods were used anywhere in the implementation.
- `PSEUDOCODE.md` (project root) contains Part E pseudocode.
- The project report (PDF) is in the project root.
