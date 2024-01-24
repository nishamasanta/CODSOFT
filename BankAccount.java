import java.util.Scanner;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }
}

class ATM {
    private BankAccount bankAccount;

    public ATM(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public void displayOptions() {
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
    }

    public void performTransaction(int choice, double amount) {
        switch (choice) {
            case 1:
                if (bankAccount.withdraw(amount)) {
                    System.out.println("Withdrawal successful. Remaining balance: $" + bankAccount.getBalance());
                } else {
                    System.out.println("Insufficient funds. Withdrawal failed.");
                }
                break;
            case 2:
                bankAccount.deposit(amount);
                System.out.println("Deposit successful. Updated balance: $" + bankAccount.getBalance());
                break;
            case 3:
                System.out.println("Current balance: $" + bankAccount.getBalance());
                break;
            case 4:
                System.out.println("Exiting ATM. Thank you!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please choose a valid option.");
        }
    }
}

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter initial balance: $");
        double initialBalance = scanner.nextDouble();

        BankAccount userAccount = new BankAccount(initialBalance);
        ATM atm = new ATM(userAccount);

        while (true) {
            atm.displayOptions();
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            if (choice == 4) {
                break; // Exit the loop if the user chooses to exit
            }

            System.out.print("Enter amount: $");
            double amount = scanner.nextDouble();

            if (amount < 0) {
                System.out.println("Invalid amount. Please enter a positive value.");
                continue; // Skip the rest of the loop and restart
            }

            atm.performTransaction(choice, amount);
        }

        scanner.close();
    }
}
