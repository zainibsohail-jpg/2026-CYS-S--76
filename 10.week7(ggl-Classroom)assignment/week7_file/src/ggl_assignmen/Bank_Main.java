package ggl_assignmen;



import java.util.Scanner;
import java.io.IOException;

public class Bank_Main
{
    static Bank b = new Bank("UET Bank");

    public static void main(String[] args) throws IOException
    {
        Scanner sc = new Scanner(System.in);

        // Create file and load old data
        b.createFile();
        b.loadData();

        System.out.println("Welcome to the UET Bank");

        int choice = 1;

        while(choice != 10)
        {
            System.out.println("\n1. Add Client");
            System.out.println("2. Add Account");
            System.out.println("3. Search Account");
            System.out.println("4. Deposit Money");
            System.out.println("5. Withdraw Money");
            System.out.println("6. Display All Clients");
            System.out.println("7. Search Client");
            System.out.println("8. Total Bank Amount");
            System.out.println("9. Remove Client");
            System.out.println("10. Exit");

            System.out.println("Enter your choice:");
            choice = sc.nextInt();
            sc.nextLine();

            if(choice == 1)
            {
                System.out.println("Enter Name:");
                String n = sc.nextLine();

                System.out.println("Enter CNIC:");
                String cnic = sc.nextLine();

                System.out.println("Enter Phone Number:");
                String ph = sc.nextLine();

                Person p = new Person(n, cnic, ph);

                b.addClient(p);
            }

            else if(choice == 2)
            {
                System.out.println("Enter Client ID:");
                String id = sc.nextLine();

                Client c = null;

                for(int i = 0; i < b.ClList.size(); i++)
                {
                    if(b.ClList.get(i).getId().equals(id))
                    {
                        c = b.ClList.get(i);
                    }
                }

                if(c != null)
                {
                    System.out.println("Enter Amount:");
                    float amount = sc.nextFloat();

                    b.addAccount(id, amount, c);
                }
                else
                {
                    System.out.println("Client not found");
                }
            }

            else if(choice == 3)
            {
                System.out.println("Enter Account Number:");
                String id = sc.nextLine();

                Account a = b.searchAccount(id);

                if(a != null)
                {
                    System.out.println(a);
                }
                else
                {
                    System.out.println("Account not found");
                }
            }

            else if(choice == 4)
            {
                System.out.println("Enter Account Number:");
                String id = sc.nextLine();

                Account a = b.searchAccount(id);

                if(a != null)
                {
                    System.out.println("Enter Amount:");
                    float money = sc.nextFloat();

                    a.getACholder().deposit(money, id);

                    b.saveData();
                }
                else
                {
                    System.out.println("Account not found");
                }
            }

            else if(choice == 5)
            {
                System.out.println("Enter Account Number:");
                String id = sc.nextLine();

                Account a = b.searchAccount(id);

                if(a != null)
                {
                    System.out.println("Enter Amount:");
                    float money = sc.nextFloat();

                    a.getACholder().withdraw(money, id);

                    b.saveData();
                }
                else
                {
                    System.out.println("Account not found");
                }
            }

            else if(choice == 6)
            {
                System.out.println(b);
            }

            else if(choice == 7)
            {
                System.out.println("Enter CNIC:");
                String cnic = sc.nextLine();

                Client c = b.searchCustomerDetail(cnic);

                if(c != null)
                {
                    System.out.println(c);
                }
                else
                {
                    System.out.println("Client not found");
                }
            }

            else if(choice == 8)
            {
                System.out.println("Total Bank Amount: " + b.totalAmount());
            }

            else if(choice == 9)
            {
                System.out.println("Enter Client ID:");
                String id = sc.nextLine();

                if(b.removeClient(id))
                {
                    System.out.println("Client removed successfully");
                }
                else
                {
                    System.out.println("Client not found");
                }
            }

            else if(choice == 10)
            {
                System.out.println("Thank you for using UET Bank");
            }

            else
            {
                System.out.println("Invalid input");
            }
        }

        sc.close();
    }
}
