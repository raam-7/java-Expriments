import java.util.*;

public class Main {

    public static void main(String[] args) {

        CSVHandler csv = new CSVHandler("Students.csv");

        System.out.println("=".repeat(96));
        System.out.println("          STUDENT CSV — COMPLETE CRUD DEMONSTRATION");
        System.out.println("=".repeat(96));

       
        List<Student> seedStudents = new ArrayList<>(Arrays.asList(
            new Student(1, "Aanya Mehta",    "CSE",  88, 76, 91, 85, 79, 0),
            new Student(2, "Rohan Kulkarni", "ECE",  72, 68, 80, 74, 66, 0)
        ));
        // Calculate percentage for seed students before writing
        seedStudents.forEach(Student::calculatePercentage);
        csv.createFile(seedStudents);

        // ════════════════════════════════════════════════════════════════════
        // STEP 2 — READ and display what was written
        // ════════════════════════════════════════════════════════════════════
        csv.display("After initial file creation (2 seed rows)");

        // ════════════════════════════════════════════════════════════════════
        // STEP 3 — INSERT 3 new rows (marks4 & marks5 = 0 initially)
        // ════════════════════════════════════════════════════════════════════
        List<Student> newStudents = new ArrayList<>(Arrays.asList(
            new Student(3, "Priya Desai",   "IT",   84, 90, 78, 0, 0, 0),
            new Student(4, "Karan Joshi",   "MECH", 65, 70, 60, 0, 0, 0),
            new Student(5, "Sneha Patil",   "CIVIL",78, 82, 88, 0, 0, 0)
        ));
        csv.insertRows(newStudents);

        // ════════════════════════════════════════════════════════════════════
        // STEP 4 — READ and display after insert
        // ════════════════════════════════════════════════════════════════════
        csv.display("After inserting 3 new rows (marks4 & marks5 = 0)");

        // ════════════════════════════════════════════════════════════════════
        // STEP 5 — UPDATE: fill correct marks4 & marks5 for new students
        // ════════════════════════════════════════════════════════════════════
        List<Student> allStudents = csv.readAll();

        // Define correct marks4 & marks5 for each student id
        Map<Integer, double[]> correctMarks = new HashMap<>();
        correctMarks.put(3, new double[]{92, 87});   // Priya
        correctMarks.put(4, new double[]{75, 68});   // Karan
        correctMarks.put(5, new double[]{80, 95});   // Sneha

        System.out.println("\n>>> UPDATE — Filling correct marks4 & marks5 for new students <<<");
        for (Student s : allStudents) {
            if (correctMarks.containsKey(s.getStudentId())) {
                double[] m = correctMarks.get(s.getStudentId());
                System.out.printf("  Student %-18s | marks4: %.1f -> %.1f  | marks5: %.1f -> %.1f%n",
                    s.getName(), s.getMarks4(), m[0], s.getMarks5(), m[1]);
                s.setMarks4(m[0]);
                s.setMarks5(m[1]);
            }
        }
        csv.updateAll(allStudents, "Correct marks4 & marks5 written to file");

        // ════════════════════════════════════════════════════════════════════
        // STEP 6 — READ and display after marks update
        // ════════════════════════════════════════════════════════════════════
        csv.display("After updating marks4 & marks5 for all students");

        // ════════════════════════════════════════════════════════════════════
        // STEP 7 — CALCULATE & UPDATE percentage for every student
        // ════════════════════════════════════════════════════════════════════
        allStudents = csv.readAll();
        csv.updatePercentages(allStudents);

        // ════════════════════════════════════════════════════════════════════
        // STEP 8 — READ and display after percentage calculation
        // ════════════════════════════════════════════════════════════════════
        csv.display("After calculating & updating percentage for all students");

        // ════════════════════════════════════════════════════════════════════
        // STEP 9 — DELETE a student (e.g., studentId = 4)
        // ════════════════════════════════════════════════════════════════════
        csv.deleteById(4);

        // ════════════════════════════════════════════════════════════════════
        // STEP 10 — READ final state
        // ════════════════════════════════════════════════════════════════════
        csv.display("Final state after deleting student ID = 4");

        // ════════════════════════════════════════════════════════════════════
        // STEP 11 — EXCEPTION DEMO: Delete a non-existent student ID
        // ════════════════════════════════════════════════════════════════════
        System.out.println("\n>>> EXCEPTION DEMO — Deleting a non-existent student ID = 99 <<<");
        csv.deleteById(99);

        // ════════════════════════════════════════════════════════════════════
        // STEP 12 — EXCEPTION DEMO: Read a file that doesn't exist
        // ════════════════════════════════════════════════════════════════════
        csv.simulateReadException();

        System.out.println("\n" + "=".repeat(96));
        System.out.println("  All CRUD operations completed successfully.");
        System.out.println("=".repeat(96));
    }
}
