package composition;

public class House {
    Room r = new Room("zainab");
    void display ()
    {
        System.out.println(r.name);
    }

}
