class student
{
   private String name;
    private int Roll;

    public String getName() {
        return name;
    }

    public student(String name, int roll) {
        this.name = name;
        Roll = roll;
    }


    public int getRoll() {
        return Roll;
    }


}

public class task1 {
    static void main(String[] args) {
        student a = new student("zainab", 76);
        System.out.println(a.getName());
        System.out.println(a.getRoll());
        student b = new student("noor", 65);
        System.out.println(b.getName());
        System.out.println(b.getRoll());


    }
}
