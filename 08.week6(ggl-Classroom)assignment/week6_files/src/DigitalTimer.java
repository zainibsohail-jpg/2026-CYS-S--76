
public class DigitalTimer {
    Counter hours;
    Counter minutes;

    public DigitalTimer() {
        hours = new Counter(24);
        minutes = new Counter(60);
    }

    public DigitalTimer(int h, int m) {
        if (h < 0 || h > 23 || m < 0 || m > 59) {
            throw new IllegalArgumentException("Wrong time");
        }

        hours = new Counter(24);
        minutes = new Counter(60);

        hours.set(h);
        minutes.set(m);
    }

    public String toString() {
        return hours + ":" + minutes;
    }

    public void tick() {
        if (minutes.getCount() == 59) {
            minutes.set(0);
            hours.increment();
        } else {
            minutes.increment();
        }
    }

    public void set(int h, int m) {
        if (h < 0 || h > 23 || m < 0 || m > 59) {
            throw new IllegalArgumentException("Wrong time");
        }

        hours.set(h);
        minutes.set(m);
    }
}
