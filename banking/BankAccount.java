// Abstract base class for bank accounts
public abstract class BankAccount {
    protected String accountNumber;
    protected String accountHolderName;
    protected double balance;

    public BankAccount(String accountNumber, String accountHolderName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = initialBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    // Abstract methods to be overridden by subclasses
    public abstract void deposit(double amount);
    public abstract void withdraw(double amount);

    // Common method
    public void displayAccountInfo() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Balance: $" + balance);
    }
}