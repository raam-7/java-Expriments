import java.time.LocalDate;

public abstract class Employee {

    // Attributes
    protected String name;
    protected String panNo;
    protected LocalDate joiningDate;
    protected String designation;
    protected int empId;

    // Constructor
    public Employee(int empId, String name, String panNo, LocalDate joiningDate, String designation) {
        this.empId      = empId;
        this.name       = name;
        this.panNo      = panNo;
        this.joiningDate = joiningDate;
        this.designation = designation;
    }

    // Abstract method — must be overridden by every concrete subclass
    public abstract double calcCTC();

    // Getters
    public int     getEmpId()       { return empId; }
    public String  getName()        { return name; }
    public String  getPanNo()       { return panNo; }
    public LocalDate getJoiningDate(){ return joiningDate; }
    public String  getDesignation() { return designation; }

    // Common display
    @Override
    public String toString() {
        return String.format(
            "EmpID: %d | Name: %-15s | PAN: %-12s | Joining: %s | Designation: %-20s | CTC: %.2f",
            empId, name, panNo, joiningDate, designation, calcCTC()
        );
    }
}
