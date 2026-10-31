package ggl_assignmen;


public class Account
{
    String number;
    float amount;
    Client ACholder;

    static int count = 0;

    public Account()
    {
    }

    public Account(float amount, Client c)
    {
        count++;
        number = "A" + count;
        this.amount = amount;
        ACholder = c;
    }

    public String getNumber()
    {
        return number;
    }

    public void setNumber(String number)
    {
        this.number = number;
    }

    public float getAmount()
    {
        return amount;
    }

    public void setAmount(float amount)
    {
        this.amount = amount;
    }

    public Client getACholder()
    {
        return ACholder;
    }

    public void setACholder(Client c)
    {
        ACholder = c;
    }

    float deposit(float money)
    {
        if(money > 0)
        {
            amount = amount + money;
        }
        else
        {
            System.out.println("Invalid amount");
        }

        return amount;
    }

    float withdraw(float money)
    {
        if(money > 0 && money <= amount)
        {
            amount = amount - money;
        }
        else
        {
            System.out.println("Insufficient balance");
        }

        return amount;
    }

    public String toString()
    {
        return "\nAccount Number: " + number +
                "\nAmount: " + amount +
                "\nHolder: " + ACholder.getPersonDetails().getName();
    }
}
