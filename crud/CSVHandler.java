import java.io.*;
import java.nio.file.*;
import java.util.*;

/**
 * Handles all CRUD operations on Students.csv.
 * Every public method catches and reports IOException.
 */
public class CSVHandler {

    private static final String HEADER =
        "studentId,name,branch,marks1,marks2,marks3,marks4,marks5,percentage";

    private final String filePath;

    public CSVHandler(String filePath) {
        this.filePath = filePath;
    }

    // ════════════════════════════════════════════════════════════════════════
    //  CREATE — write the CSV file from scratch with 2 seed rows
    // ════════════════════════════════════════════════════════════════════════
    public void createFile(List<Student> seedStudents) {
        System.out.println("\n" + banner("CREATE — Writing CSV file with seed data"));
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write(HEADER);
            bw.newLine();
            for (Student s : seedStudents) {
                bw.write(s.toCSV());
                bw.newLine();
                System.out.println("  Written : " + s.toCSV());
            }
            System.out.println("  File created successfully: " + filePath);
        } catch (IOException e) {
            System.err.println("  [IOException] createFile() failed: " + e.getMessage());
        }
    }

    // ════════════════════════════════════════════════════════════════════════
    //  READ — load all students from CSV into a List
    // ════════════════════════════════════════════════════════════════════════
    public List<Student> readAll() {
        List<Student> students = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true;
            while ((line = br.readLine()) != null) {
                if (firstLine) { firstLine = false; continue; }   // skip header
                if (!line.trim().isEmpty()) {
                    students.add(Student.fromCSV(line));
                }
            }
        } catch (IOException e) {
            System.err.println("  [IOException] readAll() failed: " + e.getMessage());
        }
        return students;
    }

    // ════════════════════════════════════════════════════════════════════════
    //  INSERT — append new rows (marks4 & marks5 = 0 initially)
    // ════════════════════════════════════════════════════════════════════════
    public void insertRows(List<Student> newStudents) {
        System.out.println("\n" + banner("INSERT — Appending 3 new rows (marks4 & marks5 = 0)"));
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            for (Student s : newStudents) {
                bw.write(s.toCSV());
                bw.newLine();
                System.out.println("  Inserted: " + s.toCSV());
            }
        } catch (IOException e) {
            System.err.println("  [IOException] insertRows() failed: " + e.getMessage());
        }
    }

    // ════════════════════════════════════════════════════════════════════════
    //  UPDATE — rewrite the file with modified student data
    // ════════════════════════════════════════════════════════════════════════
    public void updateAll(List<Student> students, String operationLabel) {
        System.out.println("\n" + banner("UPDATE — " + operationLabel));
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write(HEADER);
            bw.newLine();
            for (Student s : students) {
                bw.write(s.toCSV());
                bw.newLine();
                System.out.println("  Updated : " + s.toCSV());
            }
        } catch (IOException e) {
            System.err.println("  [IOException] updateAll() failed: " + e.getMessage());
        }
    }

    // ════════════════════════════════════════════════════════════════════════
    //  CALCULATE PERCENTAGE — update each student's percentage field
    // ════════════════════════════════════════════════════════════════════════
    public void updatePercentages(List<Student> students) {
        System.out.println("\n" + banner("CALCULATE & UPDATE — Percentage for every student"));
        for (Student s : students) {
            double before = s.getPercentage();
            s.calculatePercentage();
            System.out.printf("  Student %-18s | Before: %6.2f%% -> After: %6.2f%%%n",
                s.getName(), before, s.getPercentage());
        }
        updateAll(students, "Writing updated percentages to file");
    }

    // ════════════════════════════════════════════════════════════════════════
    //  DELETE — remove a row by studentId
    // ════════════════════════════════════════════════════════════════════════
    public void deleteById(int targetId) {
        System.out.println("\n" + banner("DELETE — Removing student with ID = " + targetId));
        List<Student> current = readAll();
        boolean found = false;

        List<Student> remaining = new ArrayList<>();
        for (Student s : current) {
            if (s.getStudentId() == targetId) {
                System.out.println("  Deleted : " + s.toCSV());
                found = true;
            } else {
                remaining.add(s);
            }
        }

        if (!found) {
            System.out.println("  [Warning] Student with ID " + targetId + " not found.");
            return;
        }

        // Rewrite file without the deleted row
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write(HEADER);
            bw.newLine();
            for (Student s : remaining) {
                bw.write(s.toCSV());
                bw.newLine();
            }
            System.out.println("  File updated after deletion.");
        } catch (IOException e) {
            System.err.println("  [IOException] deleteById() failed: " + e.getMessage());
        }
    }

    // ════════════════════════════════════════════════════════════════════════
    //  DISPLAY — pretty-print the current state of the file
    // ════════════════════════════════════════════════════════════════════════
    public void display(String label) {
        System.out.println("\n" + banner("READ — " + label));
        List<Student> students = readAll();
        String divider = "-".repeat(96);
        System.out.println(divider);
        System.out.printf("| %-4s | %-18s | %-12s | %-5s | %-5s | %-5s | %-5s | %-5s | %-9s |%n",
            "ID", "Name", "Branch", "M1", "M2", "M3", "M4", "M5", "Percent");
        System.out.println(divider);
        if (students.isEmpty()) {
            System.out.println("| No records found." + " ".repeat(74) + "|");
        } else {
            for (Student s : students) System.out.println(s);
        }
        System.out.println(divider);
        System.out.println("  Total records: " + students.size());
    }

    // ════════════════════════════════════════════════════════════════════════
    //  SIMULATE EXCEPTION — try to read a file that doesn't exist
    // ════════════════════════════════════════════════════════════════════════
    public void simulateReadException() {
        System.out.println("\n" + banner("EXCEPTION DEMO — Reading a non-existent file"));
        String badPath = "nonexistent_file.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(badPath))) {
            br.readLine();
        } catch (IOException e) {
            System.err.println("  [IOException CAUGHT] " + e.getClass().getSimpleName()
                + ": " + e.getMessage());
            System.err.println("  => Gracefully handled. Program continues normally.");
        }
    }

    // ── Utility: section banner ───────────────────────────────────────────────
    private static String banner(String text) {
        return ">>> " + text + " <<<";
    }
}
