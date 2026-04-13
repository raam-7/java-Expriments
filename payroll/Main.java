import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // ── Full-Time: Software Engineer ─────────────────────────────────────
        FullTimeEmployee swe = new FullTimeEmployee(
            101, "Alice Johnson", "ABCPJ1234D",
            LocalDate.of(2021, 6, 1), "SWE",
            80_000, 15_000, 0          // baseSalary + perfBonus; no hiringCommission
        );

        // ── Full-Time: HR ────────────────────────────────────────────────────
        FullTimeEmployee hr = new FullTimeEmployee(
            102, "Bob Sharma", "XYZBS5678F",
            LocalDate.of(2020, 3, 15), "HR",
            70_000, 0, 12_000          // baseSalary + hiringCommission
        );

        // ── Contract Employee ────────────────────────────────────────────────
        ContractEmployee contractor = new ContractEmployee(
            201, "Carol White", "LMNPW9012G",
            LocalDate.of(2024, 1, 10), "Contract Developer",
            160, 500                   // 160 hrs/month @ ₹500/hr
        );

        // ── Manager (Full-Time + allowances) ─────────────────────────────────
        Manager manager = new Manager(
            301, "David Kumar", "PQRDK3456H",
            LocalDate.of(2018, 9, 1), "Engineering Manager",
            1_20_000, 25_000,          // baseSalary, perfBonus
            10_000, 8_000              // travelAllowance, educationAllowance
        );

        // ── Polymorphic list ─────────────────────────────────────────────────
        List<Employee> employees = new ArrayList<>();
        employees.add(swe);
        employees.add(hr);
        employees.add(contractor);
        employees.add(manager);

        System.out.println("=".repeat(100));
        System.out.println("                              EMPLOYEE CTC REPORT");
        System.out.println("=".repeat(100));

        for (Employee e : employees) {
            System.out.println(e);
        }

        System.out.println("=".repeat(100));

        // ── Breakdown for clarity ─────────────────────────────────────────────
        System.out.printf("%nCTC Breakdown:%n");
        System.out.printf("  SWE  (Alice) : Base ₹%.0f + PerfBonus ₹%.0f         = ₹%.0f%n",
            swe.getBaseSalary(), swe.getPerformanceBonus(), swe.calcCTC());
        System.out.printf("  HR   (Bob)   : Base ₹%.0f + HiringComm ₹%.0f        = ₹%.0f%n",
            hr.getBaseSalary(), hr.getHiringCommission(), hr.calcCTC());
        System.out.printf("  Contractor(Carol): %,.0f hrs × ₹%.0f/hr             = ₹%.0f%n",
            contractor.getNumberOfHours(), contractor.getHourlyRate(), contractor.calcCTC());
        System.out.printf("  Manager(David): Base ₹%.0f + Perf ₹%.0f + TA ₹%.0f + EduAllw ₹%.0f = ₹%.0f%n",
            manager.getBaseSalary(), manager.getPerformanceBonus(),
            manager.getTravelAllowance(), manager.getEducationAllowance(), manager.calcCTC());
    }
}
