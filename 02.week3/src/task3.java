import java.util.Scanner;

public class task3 {
    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter minutes: ");
        int sec = sc.nextInt();
        int min = sec / 60;
        int rem = sec % 60;
        System.out.println("Hours = " + min);
        System.out.println("Minutes = " + rem);
        System.out.println(min+":"+rem);
        System.out.println();
    }
}