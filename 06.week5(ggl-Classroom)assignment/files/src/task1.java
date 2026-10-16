
class dayType {
    String[] days = {
            "Sunday", "Monday", "Tuesday", "Wednesday",
            "Thursday", "Friday", "Saturday"
    };

    int day;

    dayType(String d) {
        setDay(d);
    }

    void setDay(String d) {
        for (int i = 0; i < 7; i++) {
            if (days[i].equalsIgnoreCase(d)) {
                day = i;
                return;
            }
        }
    }

    void printDay() {
        System.out.println(days[day]);
    }

    String getDay() {
        return days[day];
    }

    String nextDay() {
        return days[(day + 1) % 7];
    }

    String previousDay() {
        return days[(day + 6) % 7];
    }

    String addDays(int n) {
        return days[(day + n % 7) % 7];
    }

    public static void main(String[] args) {
        dayType d = new dayType("Monday");

        System.out.print("Current day: ");
        d.printDay();

        System.out.println("Next day: " + d.nextDay());
        System.out.println("Previous day: " + d.previousDay());
        System.out.println("After 4 days: " + d.addDays(4));
    }
}
