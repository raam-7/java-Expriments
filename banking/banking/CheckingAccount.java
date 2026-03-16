// CheckingAccount class extending BankAccount
public class CheckingAccount extends BankAccount {
    private double overdraftLimit;

    public CheckingAccount(String accountNumber, String accountHolderName, double initialBalance, double overdraftLimit) {
        super(accountNumber, accountHolderName, initialBalance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited $" + amount + " to Checking Account.");
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && (balance + overdraftLimit) >= amount) {
            balance -= amount;
            System.out.println("Withdrew $" + amount + " from Checking Account.");
        } else {
            System.out.println("Overdraft limit exceeded or invalid amount.");
        }
    }

    @Override
    public void displayAccountInfo() {
        super.displayAccountInfo();
        System.out.println("Account Type: Checking");
        System.out.println("Overdraft Limit: $" + overdraftLimit);
    }
}