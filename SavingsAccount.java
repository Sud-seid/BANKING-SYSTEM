public class SavingsAccount extends BankAccount implements AccountOperations, CustomerDetails {
    private String customerName;
    private int customerID;

    // Constructor
    public SavingsAccount(int accountNumber, double balance, String customerName, int customerID) {
        super(accountNumber, balance);
        this.customerName = customerName;
        this.customerID = customerID;
    }

    // Abstract withdraw method
    @Override
    public void withdraw(double amount) {
        if (getBalance() >= amount) {
            deposit(-amount); // Use deposit with negative amount
            System.out.println("Withdrawal of " + amount + " successful!");
        } else {
            System.out.println("Insufficient balance!");
        }
    }

    // CustomerDetails
    @Override
    public String getCustomerName() {
        return customerName;
    }

    @Override
    public int getCustomerID() {
        return customerID;
    }
}
