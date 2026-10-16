
import java.util.Scanner;

class Walls {
    String flavor;
    int scoops;
    String cone;
    int price;

    Walls() {
        flavor = "Vanilla";
        scoops = 1;
        cone = "Sugar";
        price = 75;
    }

    Walls(String f, int s, String c) {
        flavor = f;
        scoops = s;
        cone = c;

        price = scoops * 75;

        if (cone.equalsIgnoreCase("Waffle")) {
            price = price + 40;
        }
    }

    void showData() {
        System.out.println("Flavor: " + flavor);
        System.out.println("Scoops: " + scoops);
        System.out.println("Cone: " + cone);
        System.out.println("Price: Rs. " + price);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter flavor: ");
        String f = sc.nextLine();

        System.out.print("Enter scoops: ");
        int s = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter cone (Sugar/Waffle): ");
        String c = sc.nextLine();

        Walls w = new Walls(f, s, c);
        w.showData();

        sc.close();
    }
}
