class Teacher {
    String name;
    Teacher(String name) {
        this.name = name;
    }
}

class Department {
     String teacher;

    Department(String n) {
        teacher = n;
    }

    //    Department(Teacher teacher) {
//        this.teacher = teacher;
//    }
    void p(){
        System.out.println("hello world");
    }
}

public class task3 {
    public static void main(String[] args) {
        Teacher t1 = new Teacher("Ali");
        Department d1 = new Department("Ali");
        System.out.println(d1.teacher);
        d1.p();
    }
}