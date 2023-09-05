import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
//Task_2 atm interface by saksham agarwal
class User {
    private String userId;
    private String userPin;
    private double balance;
    private StringBuilder transactionHistory;

    public User(String userId, String userPin) {
        this.userId = userId;
        this.userPin = userPin;
        this.balance = 0.0;
        this.transactionHistory = new StringBuilder();
    }

    public boolean validateUser(String enteredPin) {
        return userPin.equals(enteredPin);
    }

    public void deposit(double amount) {
        balance += amount;
        recordTransaction("Deposit: +" + amount);
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            recordTransaction("Withdraw: -" + amount);
            return true;
        } else {
            return false;
        }
    }

    public boolean transfer(User recipient, double amount) {
        if (balance >= amount) {
            balance -= amount;
            recipient.deposit(amount);
            recordTransaction("Transfer: -" + amount + " to " + recipient.getUserId());
            return true;
        } else {
            return false;
        }
    }

    public String getUserId() {
        return userId;
    }

    public String getTransactionHistory() {
        return transactionHistory.toString();
    }

    private void recordTransaction(String transaction) {
        transactionHistory.append(transaction).append("\n");
    }
}

public class Task_2 {
    private static Map<String, User> users = new HashMap<>();
    private static User currentUser = null;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeUsers();

        while (true) {
            System.out.print("Enter your User ID: ");
            String userId = scanner.next();

            if (users.containsKey(userId)) {
                System.out.print("Enter your PIN: ");
                String userPin = scanner.next();
                currentUser = users.get(userId);

                if (currentUser.validateUser(userPin)) {
                    showMainMenu();
                } else {
                    System.out.println("Invalid PIN. Please try again.");
                }
            } else {
                System.out.println("User not found. Please try again.");
            }
        }
    }

    private static void showMainMenu() {
        while (true) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    showTransactionHistory();
                    break;
                case 2:
                    performWithdrawal();
                    break;
                case 3:
                    performDeposit();
                    break;
                case 4:
                    performTransfer();
                    break;
                case 5:
                    exitATM();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void showTransactionHistory() {
        System.out.println("Transaction History:");
        System.out.println(currentUser.getTransactionHistory());
    }

    private static void performWithdrawal() {
        System.out.print("Enter the withdrawal amount: ");
        double amount = scanner.nextDouble();

        if (currentUser.withdraw(amount)) {
            System.out.println("Withdrawal successful.");
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    private static void performDeposit() {
        System.out.print("Enter the deposit amount: ");
        double amount = scanner.nextDouble();
        currentUser.deposit(amount);
        System.out.println("Deposit successful.");
    }

    private static void performTransfer() {
        System.out.print("Enter the recipient's User ID: ");
        String recipientUserId = scanner.next();

        if (users.containsKey(recipientUserId)) {
            User recipient = users.get(recipientUserId);

            System.out.print("Enter the transfer amount: ");
            double amount = scanner.nextDouble();

            if (currentUser.transfer(recipient, amount)) {
                System.out.println("Transfer successful.");
            } else {
                System.out.println("Insufficient balance.");
            }
        } else {
            System.out.println("Recipient not found.");
        }
    }

    private static void exitATM() {
        System.out.println("Exiting ATM. Thank you!");
        System.exit(0);
    }

    private static void initializeUsers() {
        users.put("user1", new User("user1", "1234"));
        users.put("user2", new User("user2", "5678"));
    }
}
