
import java.util.Scanner;

class Book
{
    String name;

    Book(String n)
    {
        name = n;
    }
}

class Main
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        Book b[] = new Book[4];

        for(int i = 0; i < 4; i++)
        {
            System.out.print("Enter book name: ");
            String n = sc.nextLine();

            b[i] = new Book(n);

            System.out.println("Shelf " + (i + 1));
        }

        for(int i = 0; i < 4; i++)
        {
            System.out.println("Book " + (i + 1) + " is on Shelf " + (i + 1));
        }
    }
}
