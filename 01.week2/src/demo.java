import java.util.Scanner;
class Demo {
    static void main(String[] args) {
        //System.out.println("zainab");
       // System.out.println("A");
        //System.out.println("B");
        //System.out.println("l1\nl2");

        //int x;
        //x=10;
        //System.out.println(x);

        //String y = "Zainab";
       // System.out.println(y);

        //char z = 'A';
        //System.out.println(z);

        //float s = 3.7f;
        //System.out.println(s);

       // long e = 25698324;
        //System.out.println(e);

        //System.out.println((int)3.7);
        //System.out.println((float)3);

       // String h = "10";
        //String c = "11";
        //System.out.println(h+c);

        //int j = Integer.parseInt("10");
        //int k = Integer.parseInt("11");
        //System.out.println(j+k);

        //System.out.println(Math.pow(2,10));

        //System.out.println(7/2.0);

        //System.out.println(Math.sqrt(100));
        //System.out.println(Math.sqrt(-1));

        //System.out.println(Math.floor(3));
        //System.out.println(Math.ceil(3));
        //System.out.println(Math.abs(-3));
        //System.out.println(Math.round(3.4));
        //System.out.println(Math.random(1,10));
        //int p = 10;
        //System.out.println(p=p+1);

        //int q = 10;
        //System.out.println(--q);

        //Scanner d = new Scanner(System.in);
        //System.out.println("Enter you Age");
        //int age = d.nextInt();

        Scanner m = new Scanner(System.in);
        System.out.println("Enter you Roll no");
        double Rollno = m.nextDouble();

        Scanner g = new Scanner(System.in);
        System.out.println("Enter you total marks");
        int Total = g.nextInt();

        Scanner b = new Scanner(System.in);
        System.out.println("Enter you obtained marks");
        int Obtained = b.nextInt();

        double percentage = (((double)Obtained/Total)*100);
        System.out.println(percentage);



    }
}