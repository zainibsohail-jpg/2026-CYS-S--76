package week9;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;
public class task1 {
    static void main(String[] args) throws IOException {
        Scanner sc = new Scanner (System.in);
        FileWriter f = new FileWriter("result.csv");
        f.write("Name,Rollno,Obtained marks,Total marks,Percentage,Grade\n");

        System.out.println("Enter number of student");
        int a = sc.nextInt();
        for (int i = 1 ; i<=a ; i++){
            System.out.println("Student#"+i);
            System.out.println("Enter Student name ");
            String name = sc.next();
            System.out.println("Enter roll no");
            String Rollno = sc.next();
            System.out.println("Enter obtained marks");
            double obtained = sc.nextDouble();
            System.out.println("Enter total marks");
            double total = sc.nextDouble();
            System.out.println();
            double percentage = (obtained/total)*100;
            System.out.println("Percentage is :"+percentage);
            String grade;
            if (percentage >= 90)
            grade = "A";
                else if (percentage >= 80)
                    grade = "B";
                else if (percentage >= 70)
                    grade = "C";
                else if (percentage >= 60)
                    grade = "D";
                else if (percentage >= 50);
                    grade = "F";

            f.write(name+","+Rollno+","+obtained+","+total+","+percentage+","+grade+"\n");

        }
        f.close();
    }
}
