/**
 * Model class representing a Student record.
 */
public class Student {

    private int    studentId;
    private String name;
    private String branch;
    private double marks1, marks2, marks3, marks4, marks5;
    private double percentage;

    // ── Full constructor ──────────────────────────────────────────────────────
    public Student(int studentId, String name, String branch,
                   double marks1, double marks2, double marks3,
                   double marks4, double marks5, double percentage) {
        this.studentId  = studentId;
        this.name       = name;
        this.branch     = branch;
        this.marks1     = marks1;
        this.marks2     = marks2;
        this.marks3     = marks3;
        this.marks4     = marks4;
        this.marks5     = marks5;
        this.percentage = percentage;
    }

    // ── Build from a CSV line ─────────────────────────────────────────────────
    public static Student fromCSV(String csvLine) {
        String[] parts = csvLine.split(",");
        return new Student(
            Integer.parseInt(parts[0].trim()),
            parts[1].trim(),
            parts[2].trim(),
            Double.parseDouble(parts[3].trim()),
            Double.parseDouble(parts[4].trim()),
            Double.parseDouble(parts[5].trim()),
            Double.parseDouble(parts[6].trim()),
            Double.parseDouble(parts[7].trim()),
            Double.parseDouble(parts[8].trim())
        );
    }

    // ── Convert to CSV line ───────────────────────────────────────────────────
    public String toCSV() {
        return String.format("%d,%s,%s,%.1f,%.1f,%.1f,%.1f,%.1f,%.2f",
            studentId, name, branch,
            marks1, marks2, marks3, marks4, marks5, percentage);
    }

    // ── Calculate and set percentage (out of 500) ─────────────────────────────
    public void calculatePercentage() {
        this.percentage = ((marks1 + marks2 + marks3 + marks4 + marks5) / 500.0) * 100.0;
    }

    // ── Pretty print ──────────────────────────────────────────────────────────
    @Override
    public String toString() {
        return String.format(
            "| %-4d | %-18s | %-12s | %5.1f | %5.1f | %5.1f | %5.1f | %5.1f | %6.2f%% |",
            studentId, name, branch, marks1, marks2, marks3, marks4, marks5, percentage);
    }

    // ── Getters & Setters ─────────────────────────────────────────────────────
    public int    getStudentId()  { return studentId; }
    public String getName()       { return name; }
    public String getBranch()     { return branch; }
    public double getMarks1()     { return marks1; }
    public double getMarks2()     { return marks2; }
    public double getMarks3()     { return marks3; }
    public double getMarks4()     { return marks4; }
    public double getMarks5()     { return marks5; }
    public double getPercentage() { return percentage; }

    public void setMarks1(double v) { marks1 = v; }
    public void setMarks2(double v) { marks2 = v; }
    public void setMarks3(double v) { marks3 = v; }
    public void setMarks4(double v) { marks4 = v; }
    public void setMarks5(double v) { marks5 = v; }
    public void setPercentage(double v) { percentage = v; }
}
