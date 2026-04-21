import java.time.LocalDate;

public class Manager extends FullTimeEmployee {

    private double travelAllowance;
    private double educationAllowance;

    // Constructor
    public Manager(int empId, String name, String panNo,
                   LocalDate joiningDate, String designation,
                   double baseSalary, double performanceBonus,
                   double travelAllowance, double educationAllowance) {
        // hiringCommission = 0 for managers (not applicable)
        super(empId, name, panNo, joiningDate, designation,
              baseSalary, performanceBonus, 0.0);
        this.travelAllowance    = travelAllowance;
        this.educationAllowance = educationAllowance;
    }

    /**
     * Manager's CTC overrides FullTimeEmployee's designation-based logic:
     * CTC = baseSalary + performanceBonus + travelAllowance + educationAllowance
     */
    @Override
    public double calcCTC() {
        return baseSalary + performanceBonus + travelAllowance + educationAllowance;
    }

    // Getters & Setters
    public double getTravelAllowance()    { return travelAllowance; }
    public double getEducationAllowance() { return educationAllowance; }

    public void setTravelAllowance(double travelAllowance)       { this.travelAllowance = travelAllowance; }
    public void setEducationAllowance(double educationAllowance) { this.educationAllowance = educationAllowance; }
}
