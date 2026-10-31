package ggl_assignmen;


public class Person
{
    String Name;
    String CNIC;
    String phoneNo;

    public Person()
    {
    }

    public Person(String name, String cnic, String phone)
    {
        Name = name;
        CNIC = cnic;
        phoneNo = phone;
    }

    public String getName()
    {
        return Name;
    }

    public void setName(String name)
    {
        Name = name;
    }

    public String getCNIC()
    {
        return CNIC;
    }

    public void setCNIC(String cnic)
    {
        CNIC = cnic;
    }

    public String getPhoneNo()
    {
        return phoneNo;
    }

    public void setPhoneNo(String phone)
    {
        phoneNo = phone;
    }

    public String toString()
    {
        return "Name: " + Name +
                "\nCNIC: " + CNIC +
                "\nPhone: " + phoneNo;
    }
}
