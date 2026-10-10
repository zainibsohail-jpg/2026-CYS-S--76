import java.util.Scanner;
class Time
{
    void convert()
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter minutes: ");
        int sec = sc.nextInt();
        int min = sec / 60;
        int rem = sec % 60;
        System.out.println("Hours = " + min);
        System.out.println("Minutes = " + rem);
        System.out.println(min+":"+rem);
    }
}
public class task1
{
    public static void main(String args[])
    {
        Time t = new Time();
        t.convert();
    }
}