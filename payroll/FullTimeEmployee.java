import java.time.LocalDate;

/**
 * Full-Time Employee
 * CTC depends on role/designation:
 *   SWE  -> baseSalary + performanceBonus
 *   HR   -> baseSalary + hiringCommission
 *   (default) -> baseSalary only
 */
public class FullTimeEmployee extends Employee {

    protected double baseSalary;
    protected double performanceBonus;   // used for SWE
    protected double hiringCommission;   // used for HR

    // Constructor
    public FullTimeEmployee(int empId, String name, String panNo,
                            LocalDate joiningDate, String designation,
                            double baseSalary, double performanceBonus,
                            double hiringCommission) {
        super(empId, name, panNo, joiningDate, designation);
        this.baseSalary        = baseSalary;
        this.performanceBonus  = performanceBonus;
        this.hiringCommission  = hiringCommission;
    }

    /**
     * CTC calculation based on designation/role.
     * Comparison is case-insensitive for convenience.
     */
    @Override
    public double calcCTC() {
        switch (designation.toUpperCase()) {
            case "SWE":
            case "SOFTWARE ENGINEER":
                return baseSalary + performanceBonus;

            case "HR":
            case "HUMAN RESOURCES":
                return baseSalary + hiringCommission;

            default:
                return baseSalary;           // fallback for unlisted roles
        }
    }

    // Getters & Setters
    public double getBaseSalary()       { return baseSalary; }
    public double getPerformanceBonus() { return performanceBonus; }
    public double getHiringCommission() { return hiringCommission; }

    public void setBaseSalary(double baseSalary)             { this.baseSalary = baseSalary; }
    public void setPerformanceBonus(double performanceBonus) { this.performanceBonus = performanceBonus; }
    public void setHiringCommission(double hiringCommission) { this.hiringCommission = hiringCommission; }
}
