
import java.util.Scanner;
import java.util.Random;

class World {
    boolean hasHurdle(int x, int y) {
        if (x == 1 && y == 2) {
            return true;
        }
        return false;
    }
}

class DirectedWalker {
    int x = 0;
    int y = 0;
    World w;

    DirectedWalker(World w) {
        this.w = w;
    }

    void move(char d) {
        int a = x;
        int b = y;

        if (d == '^') b++;
        else if (d == 'v') b--;
        else if (d == '>') a++;
        else if (d == '<') a--;

        if (w.hasHurdle(a, b)) {
            System.out.println("Ali found a hurdle!");
        } else {
            x = a;
            y = b;
        }
    }
}

class RandomWalker {
    int x = 0;
    int y = 0;
    World w;
    Random r = new Random();

    RandomWalker(World w) {
        this.w = w;
    }

    void move() {
        int d = r.nextInt(4);
        int a = x;
        int b = y;

        if (d == 0) b++;
        else if (d == 1) b--;
        else if (d == 2) a++;
        else a--;

        if (w.hasHurdle(a, b)) {
            System.out.println("Hassan found a hurdle!");
        } else {
            x = a;
            y = b;
        }
    }
}

public class task2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        World w = new World();
        DirectedWalker ali = new DirectedWalker(w);
        RandomWalker hassan = new RandomWalker(w);

        while (true) {
            System.out.print("Enter direction (^, v, <, >) or q to quit: ");
            char d = sc.next().charAt(0);

            if (d == 'q') {
                break;
            }

            ali.move(d);
            hassan.move();

            System.out.println("Ali: " + ali.x + " : " + ali.y);
            System.out.println("Hassan: " + hassan.x + " : " + hassan.y);
        }

        sc.close();
    }
}
