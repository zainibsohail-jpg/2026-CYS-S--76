class Hello
{
    private String Name;

    int Rollno = 76;
    void B()
    {
        System.out.println("CNIC : 0284989349849");

    }

    public void setName(String name) {
        Name = name;
    }

    public String getName() {
        return Name;
    }
}
class Number
{
    long Num = +823847184738L;
}
public class week4
{
    static void main(String[] args)
    {
        Hello b1 = new Hello();
        b1.B();
        Hello a1 = new Hello();
        a1.setName("Zainab");
        System.out.println("Name : " + a1.getName());
        Hello c1 = new Hello();
        System.out.println("Rollno : " + c1.Rollno);
        Number e1 = new Number();
        System.out.println("Number : " + e1.Num);
    }
}