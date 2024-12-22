public class CurrentAccount extends BankAccount {
    private double overdraftLimit;

    // Constructor
    public CurrentAccount(int accountNumber, double balance, double overdraftLimit) {
        super(accountNumber, balance);
        this.overdraftLimit = overdraftLimit;
    }

    // Abstract withdraw method
    @Override
    public void withdraw(double amount) {
        if (getBalance() + overdraftLimit >= amount) {
            deposit(-amount); // Use deposit with negative amount
            System.out.println("Withdrawal of " + amount + " successful with overdraft!");
        } else {
            System.out.println("Overdraft limit exceeded!");
        }
    }
}
