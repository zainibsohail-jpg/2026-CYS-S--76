
import java.util.Scanner;

class Time {
    void convert() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter seconds: ");
        int sec = sc.nextInt();

        int hours = sec / 3600;
        int minutes = (sec % 3600) / 60;
        int remainingSeconds = sec % 60;

        System.out.println("Hours = " + hours);
        System.out.println("Minutes = " + minutes);
        System.out.println("Seconds = " + remainingSeconds);

        System.out.printf("%02d:%02d:%02d%n",
                hours, minutes, remainingSeconds);
    }
}

public class task1 {
    public static void main(String args[]) {
        Time t = new Time();
        t.convert();
    }
}
