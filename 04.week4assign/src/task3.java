
import java.util.Scanner;

public class task3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int ax = 0, ay = 0;
        int hx = 0, hy = 0;

        for (int i = 1; i <= 3; i++) {
            System.out.print("Enter direction for Ali (^, v, <, >): ");
            char d = sc.next().charAt(0);

            if (d == '^') ay++;
            if (d == 'v') ay--;
            if (d == '>') ax++;
            if (d == '<') ax--;

            if (ax == 1 && ay == 2) {
                ax = 0;
                ay = 0;
                System.out.println("Ali found a hurdle!");
            }

            // Hasan always moves up
            hy++;

            if (hx == 1 && hy == 2) {
                hy--;
                System.out.println("Hasan found a hurdle!");
            }

            System.out.println("Ali is at (" + ax + ":" + ay + ")");
            System.out.println("Hasan is at (" + hx + ":" + hy + ")");
        }

        sc.close();
    }
}
