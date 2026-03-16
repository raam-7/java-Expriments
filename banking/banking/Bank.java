import java.util.ArrayList;
import java.util.List;

// Bank class to manage accounts
public class Bank {
    private List<BankAccount> accounts;

    public Bank() {
        accounts = new ArrayList<>();
    }

    public void addAccount(BankAccount account) {
        accounts.add(account);
        System.out.println("Account added: " + account.getAccountNumber());
    }

    public BankAccount findAccount(String accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public void displayAllAccounts() {
        for (BankAccount account : accounts) {
            account.displayAccountInfo();
            System.out.println("-----");
        }
    }
}