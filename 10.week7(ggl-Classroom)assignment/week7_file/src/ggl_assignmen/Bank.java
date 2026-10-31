package ggl_assignmen;



import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Bank
{
    String name;
    ArrayList<Client> ClList;
    ArrayList<Account> AcList;

    public Bank()
    {
        ClList = new ArrayList<Client>();
        AcList = new ArrayList<Account>();
    }

    public Bank(String name)
    {
        this.name = name;
        ClList = new ArrayList<Client>();
        AcList = new ArrayList<Account>();
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public ArrayList<Client> getClList()
    {
        return ClList;
    }

    public void setClList(ArrayList<Client> list)
    {
        ClList = list;
    }

    public ArrayList<Account> getAcList()
    {
        return AcList;
    }

    public void setAcList(ArrayList<Account> list)
    {
        AcList = list;
    }

    // Create text file
    void createFile() throws IOException
    {
        File f = new File("Bank.txt");

        if(f.createNewFile())
        {
            System.out.println("File Created Successfully");
        }
    }

    // Save all data in file
    void saveData() throws IOException
    {
        FileWriter fw = new FileWriter("Bank.txt");

        for(int i = 0; i < ClList.size(); i++)
        {
            Client c = ClList.get(i);

            fw.write("CLIENT," + c.getId() + "," +
                    c.getPersonDetails().getName() + "," +
                    c.getPersonDetails().getCNIC() + "," +
                    c.getPersonDetails().getPhoneNo() + "\n");
        }

        for(int i = 0; i < AcList.size(); i++)
        {
            Account a = AcList.get(i);

            fw.write("ACCOUNT," + a.getNumber() + "," +
                    a.getAmount() + "," +
                    a.getACholder().getId() + "\n");
        }

        fw.close();
    }

    // Read saved data from file
    void loadData() throws FileNotFoundException
    {
        File f = new File("Bank.txt");

        if(!f.exists())
        {
            return;
        }

        Scanner s = new Scanner(f);

        while(s.hasNextLine())
        {
            String line = s.nextLine();

            if(line.trim().equals(""))
            {
                continue;
            }

            String[] data = line.split(",");

            if(data[0].equals("CLIENT"))
            {
                Person p = new Person(data[2], data[3], data[4]);

                Client c = new Client(p);
                c.setId(data[1]);

                ClList.add(c);

                int id = Integer.parseInt(data[1].substring(1));

                if(id > Client.count)
                {
                    Client.count = id;
                }
            }

            else if(data[0].equals("ACCOUNT"))
            {
                Client c = null;

                for(int i = 0; i < ClList.size(); i++)
                {
                    if(ClList.get(i).getId().equals(data[3]))
                    {
                        c = ClList.get(i);
                        break;
                    }
                }

                if(c != null)
                {
                    Account a = new Account(
                            Float.parseFloat(data[2]), c);

                    a.setNumber(data[1]);

                    AcList.add(a);
                    c.addAccount(a);

                    int id = Integer.parseInt(data[1].substring(1));

                    if(id > Account.count)
                    {
                        Account.count = id;
                    }
                }
            }
        }

        s.close();
    }

    Client addClient(Person p) throws IOException
    {
        Client c = new Client(p);

        ClList.add(c);

        saveData();

        System.out.println("Client added successfully");
        System.out.println("Client ID: " + c.getId());

        return c;
    }

    Account addAccount(String id, float amount, Client c)
            throws IOException
    {
        if(ClList.contains(c) && c.getId().equals(id))
        {
            Account a = new Account(amount, c);

            AcList.add(a);
            c.addAccount(a);

            saveData();

            System.out.println("Account added successfully");
            System.out.println("Account Number: " + a.getNumber());

            return a;
        }

        System.out.println("Client not found");
        return null;
    }

    Account searchAccount(String id)
    {
        for(int i = 0; i < AcList.size(); i++)
        {
            if(AcList.get(i).getNumber().equals(id))
            {
                return AcList.get(i);
            }
        }

        return null;
    }

    boolean removeClient(String id) throws IOException
    {
        for(int i = 0; i < ClList.size(); i++)
        {
            if(ClList.get(i).getId().equals(id))
            {
                Client c = ClList.get(i);

                AcList.removeAll(c.getAcList());
                c.getAcList().clear();

                ClList.remove(i);

                saveData();

                return true;
            }
        }

        return false;
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

    Client searchCustomerDetail(String CNIC)
    {
        for(int i = 0; i < ClList.size(); i++)
        {
            if(ClList.get(i).getPersonDetails().getCNIC().equals(CNIC))
            {
                return ClList.get(i);
            }
        }

        return null;
    }

    public String toString()
    {
        String details = "Bank Name: " + name;

        for(int i = 0; i < ClList.size(); i++)
        {
            details = details + "\n\n" + ClList.get(i).toString();
        }

        return details;
    }
}

