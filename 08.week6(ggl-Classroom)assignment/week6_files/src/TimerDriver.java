
public class TimerDriver {
    public static void main(String[] args) {
        DigitalTimer t = new DigitalTimer();

        System.out.println(t);

        t.set(10, 59);
        System.out.println(t);

        t.tick();
        System.out.println(t);

        t.set(23, 59);
        t.tick();
        System.out.println(t);
    }
}
