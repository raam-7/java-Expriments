import java.time.LocalDate;
public class ContractEmployee extends Employee {

    private double numberOfHours;
    private double hourlyRate;

    // Constructor
    public ContractEmployee(int empId, String name, String panNo,
                            LocalDate joiningDate, String designation,
                            double numberOfHours, double hourlyRate) {
        super(empId, name, panNo, joiningDate, designation);
        this.numberOfHours = numberOfHours;
        this.hourlyRate    = hourlyRate;
    }

    @Override
    public double calcCTC() {
        return numberOfHours * hourlyRate;
    }

    // Getters & Setters
    public double getNumberOfHours() { return numberOfHours; }
    public double getHourlyRate()    { return hourlyRate; }

    public void setNumberOfHours(double numberOfHours) { this.numberOfHours = numberOfHours; }
    public void setHourlyRate(double hourlyRate)        { this.hourlyRate = hourlyRate; }
}
