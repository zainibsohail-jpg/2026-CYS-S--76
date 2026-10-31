package pk1;

public class Student {
    String name;
    course c;

    public Student(String name) {
        this.name = name;
    }

    public Student(course c) {
        this.c = c;
    }

    void show(){
        System.out.println(name+"studies"+c.name);
    }
                    //    @Override
                //    public String toString() {
                //        return "Student{" +
                //                "name='" + name + '\'' +
                //                ", rollno=" + rollno +
                //                '}';
                //    }
                //
                //    String name = "Zainab";
                //    int rollno = 76;
                //    String pass = "7545658867786";
}
