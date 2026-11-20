package wek8;

public class main {
    static void main(String[] args) {
        Teacher t = new Teacher("zainab");
        Students s = new Students(t);
        s.display();
        t.roll();
    }


}
