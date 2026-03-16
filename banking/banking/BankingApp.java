// Main class to demonstrate the banking system
public class BankingApp {
    public static void main(String[] args) {
        Bank bank = new Bank();

        // Create accounts
        SavingsAccount savings = new SavingsAccount("SA001", "John Doe", 1000.0, 5.0);
        CheckingAccount checking = new CheckingAccount("CA001", "Jane Smith", 500.0, 200.0);

        // Add accounts to bank
        bank.addAccount(savings);
        bank.addAccount(checking);

        // Perform operations
        savings.deposit(200);
        savings.withdraw(100);
        savings.addInterest();

        checking.deposit(100);
        checking.withdraw(700); // Should allow overdraft

        // Display all accounts
        bank.displayAllAccounts();
    }
}