
public class Counter {
    int count;
    int limit;

    public Counter(int limit) {
        count = 0;
        this.limit = limit;
    }

    public void set(int value) {
        count = value;
    }

    public void increment() {
        count++;

        if (count == limit) {
            count = 0;
        }
    }

    public int getCount() {
        return count;
    }

    public String toString() {
        if (count < 10) {
            return "0" + count;
        }

        return "" + count;
    }
}
