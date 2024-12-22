
public class StudentAccount extends SavingsAccount {
    private double scholarshipAmount;

    // Constructor
    public StudentAccount(int accountNumber, double balance, String customerName, int customerID, double scholarshipAmount) {
        super(accountNumber, balance, customerName, customerID);
        this.scholarshipAmount = scholarshipAmount;
    }

    // Additional Method
    public void addScholarship() {
        deposit(scholarshipAmount);
        System.out.println("Scholarship of " + scholarshipAmount + " added to the account.");
    }
}
