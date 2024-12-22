import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EnhancedBankingSystem {
    private JFrame frame;
    private ArrayList<JTextField> accountNameFields;
    private ArrayList<JTextField> accountNumberFields;
    private ArrayList<JTextField> accountBalanceFields;
    private JTextField transferAmount;
    private JLabel resultLabel;
    private ArrayList<SavingsAccount> accounts;

    public EnhancedBankingSystem() {
        frame = new JFrame("Enhanced Banking System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        accountNameFields = new ArrayList<>();
        accountNumberFields = new ArrayList<>();
        accountBalanceFields = new ArrayList<>();
        accounts = new ArrayList<>();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel headerLabel = new JLabel("Banking System Operations", JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(headerLabel, BorderLayout.NORTH);

        // Initial Account Section
        addAccountSection(panel);

        // Result Label
        resultLabel = new JLabel("", JLabel.CENTER);
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        frame.add(resultLabel, BorderLayout.SOUTH);

        // Buttons Panel
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        JButton setDetailsButton = new JButton("Set Account Details");
        JButton addAccountButton = new JButton("+ Add Account");
        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");
        JButton viewBalanceButton = new JButton("View Balances");
        JButton aboutUsButton = new JButton("About Us");

        buttonsPanel.add(setDetailsButton);
        buttonsPanel.add(addAccountButton);
        buttonsPanel.add(depositButton);
        buttonsPanel.add(withdrawButton);
        buttonsPanel.add(viewBalanceButton);
        buttonsPanel.add(aboutUsButton);

        frame.add(panel, BorderLayout.CENTER);
        frame.add(buttonsPanel, BorderLayout.SOUTH);

        // Add Account Button Action
        addAccountButton.addActionListener(e -> addAccountSection(panel));

       // Set Account Details Button Action
setDetailsButton.addActionListener(e -> {
    accounts.clear();
    try {
        for (int i = 0; i < accountNameFields.size(); i++) {
            String name = accountNameFields.get(i).getText();
            int number = Integer.parseInt(accountNumberFields.get(i).getText());
            double balance = Double.parseDouble(accountBalanceFields.get(i).getText());

            if (name.isEmpty()) {
                resultLabel.setText("Please enter valid account names.");
                return;
            }

            accounts.add(new SavingsAccount(number, balance, name, i + 1));
        }

        // Show success message
        JOptionPane.showMessageDialog(frame, "Account details set successfully!");
    } catch (NumberFormatException ex) {
        resultLabel.setText("Invalid input. Please check numbers and balances.");
    }
});


        // Deposit Button Action
        depositButton.addActionListener(e -> {
            String accountNumberStr = JOptionPane.showInputDialog("Enter Account Number:");
            int accountNumber = Integer.parseInt(accountNumberStr);
            String depositAmountStr = JOptionPane.showInputDialog("Enter Deposit Amount:");
            double depositAmount = Double.parseDouble(depositAmountStr);

            SavingsAccount account = findAccount(accountNumber);
            if (account != null) {
                account.deposit(depositAmount);
                resultLabel.setText("Deposit successful! New balance: " + account.getBalance());
            } else {
                resultLabel.setText("Account not found.");
            }
        });

        // Withdraw Button Action
        withdrawButton.addActionListener(e -> {
            try {
                String senderNumberStr = JOptionPane.showInputDialog("Enter Sender's Account Number:");
                int senderNumber = Integer.parseInt(senderNumberStr);

                String receiverNumberStr = JOptionPane.showInputDialog("Enter Receiver's Account Number:");
                int receiverNumber = Integer.parseInt(receiverNumberStr);

                String withdrawAmountStr = JOptionPane.showInputDialog("Enter Withdraw Amount:");
                double withdrawAmount = Double.parseDouble(withdrawAmountStr);

                SavingsAccount senderAccount = findAccount(senderNumber);
                SavingsAccount receiverAccount = findAccount(receiverNumber);

                if (senderAccount != null && receiverAccount != null) {
                    if (senderAccount.getBalance() >= withdrawAmount) {
                        senderAccount.withdraw(withdrawAmount);
                        receiverAccount.deposit(withdrawAmount);
                        resultLabel.setText("Transfer successful! New balance: " + senderAccount.getBalance());
                    } else {
                        resultLabel.setText("Insufficient balance in sender's account.");
                    }
                } else {
                    resultLabel.setText("Invalid sender or receiver account.");
                }
            } catch (NumberFormatException ex) {
                resultLabel.setText("Invalid input. Please check account numbers and amounts.");
            }
        });

        // View Balance Button Action
        viewBalanceButton.addActionListener(e -> {
            String accountNumberStr = JOptionPane.showInputDialog("Enter Account Number to View Balance:");
            int accountNumber = Integer.parseInt(accountNumberStr);

            SavingsAccount account = findAccount(accountNumber);
            if (account != null) {
                JOptionPane.showMessageDialog(frame, "Account Balance: " + account.getBalance());
            } else {
                JOptionPane.showMessageDialog(frame, "Account not found.");
            }
        });

        // About Us Button Action
        aboutUsButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Team Members:\n1. Semeriya Said\n2. Obse Taresa\n3. Rahma Salah\n4. Mahilet Mulugeta\n5. Sharifa Ebrahim");
        });

        frame.setVisible(true);
    }

    private void addAccountSection(JPanel panel) {
        JPanel accountPanel = new JPanel();
        accountPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField accountName = new JTextField(15);
        JTextField accountNumber = new JTextField(15);
        JTextField accountBalance = new JTextField(15);

        gbc.gridx = 0;
        gbc.gridy = 0;
        accountPanel.add(new JLabel("Account Name:"), gbc);
        gbc.gridx = 1;
        accountPanel.add(accountName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        accountPanel.add(new JLabel("Account Number:"), gbc);
        gbc.gridx = 1;
        accountPanel.add(accountNumber, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        accountPanel.add(new JLabel("Initial Balance:"), gbc);
        gbc.gridx = 1;
        accountPanel.add(accountBalance, gbc);

        accountNameFields.add(accountName);
        accountNumberFields.add(accountNumber);
        accountBalanceFields.add(accountBalance);

        panel.add(accountPanel);
        panel.revalidate();
        panel.repaint();
    }

    private SavingsAccount findAccount(int accountNumber) {
        for (SavingsAccount account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        new EnhancedBankingSystem();
    }
}

class SavingsAccount {
    private int accountNumber;
    private double balance;
    private String customerName;
    private int customerID;

    public SavingsAccount(int accountNumber, double balance, String customerName, int customerID) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.customerName = customerName;
        this.customerID = customerID;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        balance -= amount;
    }

    public double getBalance() {
        return balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getCustomerName() {
        return customerName;
    }
}