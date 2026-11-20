package wek8;

public class Students {
    Teacher tech;

    public Students(Teacher tech) {
        this.tech = tech;
    }
    void display()
    {
        System.out.println(tech.name);
    }

}
