
import java.util.Scanner;

public class task2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int balance = 1000;

        System.out.println("1 Deposit");
        System.out.println("2 Withdraw");
        System.out.println("3 Check Balance");

        int choice = sc.nextInt();

        if (choice == 1) {
            int amount = sc.nextInt();
            balance = balance + amount;
        } else if (choice == 2) {
            int amount = sc.nextInt();
            balance = balance - amount;
        }

        System.out.println("Balance = " + balance);
    }
}
