package ggl_assignmen;


import java.util.ArrayList;

public class Client
{
    String Id;
    Person PersonDetails;
    ArrayList<Account> AcList;

    static int count = 0;

    public Client()
    {
        AcList = new ArrayList<Account>();
    }

    public Client(Person p)
    {
        count++;
        Id = "C" + count;
        PersonDetails = p;
        AcList = new ArrayList<Account>();
    }

    public String getId()
    {
        return Id;
    }

    public void setId(String id)
    {
        Id = id;
    }

    public Person getPersonDetails()
    {
        return PersonDetails;
    }

    public void setPersonDetails(Person p)
    {
        PersonDetails = p;
    }

    public ArrayList<Account> getAcList()
    {
        return AcList;
    }

    public void setAcList(ArrayList<Account> list)
    {
        AcList = list;
    }

    void addAccount(Account a)
    {
        AcList.add(a);
    }

    float totalAmount()
    {
        float total = 0;

        for(int i = 0; i < AcList.size(); i++)
        {
            total = total + AcList.get(i).getAmount();
        }

        return total;
    }

    void deposit(float money, String accNo)
    {
        for(int i = 0; i < AcList.size(); i++)
        {
            if(AcList.get(i).getNumber().equals(accNo))
            {
                AcList.get(i).deposit(money);

                System.out.println("Current Amount: " +
                        AcList.get(i).getAmount());

                return;
            }
        }

        System.out.println("Account not found");
    }

    void withdraw(float money, String accNo)
    {
        for(int i = 0; i < AcList.size(); i++)
        {
            if(AcList.get(i).getNumber().equals(accNo))
            {
                AcList.get(i).withdraw(money);

                System.out.println("Remaining Amount: " +
                        AcList.get(i).getAmount());

                return;
            }
        }

        System.out.println("Account not found");
    }

    public String toString()
    {
        String details = PersonDetails.toString();

        details = details + "\nClient ID: " + Id;

        for(int i = 0; i < AcList.size(); i++)
        {
            details = details + AcList.get(i).toString();
        }

        return details;
    }
}
