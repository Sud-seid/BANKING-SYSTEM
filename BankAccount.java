public abstract class BankAccount {
    private int accountNumber;
    private double balance;

    // Constructor
    public BankAccount(int accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    // Getters
    public int getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    // Deposit Method (Single Parameter)
    public void deposit(double amount) {
        balance += amount;
    }

    // Deposit Method (Overloaded with Currency Parameter)
    public void deposit(double amount, String currency) {
        if (currency.equals("USD")) {
            balance += amount;
        } else {
            System.out.println("Currency not supported.");
        }
    }

    // Abstract Method
    public abstract void withdraw(double amount);
}
