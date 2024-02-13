package CRUDApplication;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Bank {
    private int AccountID;
    private String AccountName;
    private long MobileNumber;
    private float AccountBalance;
    private String TypeOfAccount;

    static HashMap<Integer, Bank> bankData = new HashMap<>();
    static int count = 1;

    public int getAccountID() {
        return AccountID;
    }

    public void setAccountID(int accountID) {
        AccountID = accountID;
    }

    public String getAccountName() {
        return AccountName;
    }

    public void setAccountName(String accountName) {
        AccountName = accountName;
    }

    public long getMobileNumber() {
        return MobileNumber;
    }

    public void setMobileNumber(long mobileNumber) {
        MobileNumber = mobileNumber;
    }

    public float getAccountBalance() {
        return AccountBalance;
    }

    public void setAccountBalance(float f) {
        AccountBalance = f;
    }

    public String getTypeOfAccount() {
        return TypeOfAccount;
    }

    public void setTypeOfAccount(String typeOfAccount) {
        TypeOfAccount = typeOfAccount;
    }
}

public class BankingSystem {
    public static void main(String[] args) {
        System.out.println("Welcome to Banking System");
        while (true) {
            printMenu();
            Scanner input = new Scanner(System.in);
            int Choice = input.nextInt();
            switch (Choice) {
                case 1:
                    toCreateAnAccount();
                    break;
                case 2:
                    toRemoveAnAccount();
                    break;
                case 3:
                    toUpdateAnAccount();
                    break;
                case 4:
                    toDisplayAnAccount();
                    break;
                case 5:
                    toSearchAnAccount();
                    break;
                case 6:
                    System.out.println("System Is Existed");
                    return;
                default:
                    System.out.println("Invalid Choice!!!!!");
                    break;
            }
        }
    }

    private static void printMenu() {
        System.out.println("1. To create an account");
        System.out.println("2. To remove an account");
        System.out.println("3. To update an account");
        System.out.println("4. To display the account details");
        System.out.println("5. To search the account");
        System.out.println("6. Exit Application");
        System.out.println("Enter Your Choice");
    }

    private static void toCreateAnAccount() {

        System.out.println("//Welcome to Creating An Account//");
        Scanner input = new Scanner(System.in);
        Bank obj = new Bank();
        obj.setAccountID(Bank.count);
        System.out.println("Account Id:" + obj.getAccountID());

        System.out.println("Enter The account Holder Name :");
        obj.setAccountName(input.next());
        System.out.println("Enter The Mobile Number :");
        obj.setMobileNumber(input.nextLong());
        System.out.println("Enter The Type of account savings or current :");
        obj.setTypeOfAccount(input.next());
        System.out.println("Enter the account balance :");
        obj.setAccountBalance(input.nextFloat());

        Bank.bankData.put(obj.getAccountID(), obj);
        Bank.count++;
        System.out.println("\\Thank You For Creating an Account\\ \n");
        toDisplayAnAccount();

    }

    private static void toRemoveAnAccount() {
        System.out.println("Welcome to Remove an Account");
        Scanner input = new Scanner(System.in);

        System.out.println("Enter the Id of the Account to be removed");
        int id = input.nextInt();
        if (Bank.bankData.containsKey(id)) {
            Bank.bankData.remove(id);
            System.out.println("Your Account Is Removed Successfully");
        } else {
            System.out.println("Account does not exist with this ID");
        }
    }

    private static void toUpdateAnAccount(){
            System.out.println("//Welcome To Update An Account//");
            Scanner input=new Scanner(System.in);

            System.out.println("Enter the ID");
            int id =input.nextInt();
            if(Bank.bankData.containsKey(id)){
             System.out.println("1. Update Account Name:");
             System.out.println("2. Update Mobile Number:");
             System.out.println("3. Update Type of the account savings or Current");
             System.out.println("Enter a choice: ");
             int Choice=input.nextInt();
             switch (Choice) {
                case 1:
                    System.out.println("Enter the updated name");
                    String name=input.next();
                    Bank.bankData.get(id).setAccountName(name);
                    break;
                case 2:
                    System.out.println("Enter the updated Mobile Number");
                    long num=input.nextLong();
                    Bank.bankData.get(id).setMobileNumber(num);
                    break;
                case 3:
                    System.out.println("Enter the updated type of account Savings or Current");
                    String acc=input.next();
                    Bank.bankData.get(id).setTypeOfAccount(acc); 
                    break;
                default:
                System.out.println("Invalid Choice!!!!!!");
                    return;
             }
             System.out.println("Updated Successfully");
            }else{
                System.err.println("This ID is not Exist:");
            }
         }

    private static void toDisplayAnAccount() {
           System.out.println("//Welcome To Display//");

           System.out.println("------------------------------------------------------------------------------------------");
           System.out.println("ID \t\t Name \t\t Mobile Number \t\t Balance \t\t  Type of Account");
           System.out.println("------------------------------------------------------------------------------------------");
            for(Map.Entry<Integer,Bank> dataEntry:Bank.bankData.entrySet()){
                System.out.println(dataEntry.getValue().getAccountID() + "\t\t"+dataEntry.getValue().getAccountName()+"\t\t"+dataEntry.getValue().getMobileNumber()+"\t\t"+dataEntry.getValue().getAccountBalance()+"\t\t"+dataEntry.getValue().getTypeOfAccount() );
            }
            System.out.println("------------------------------------------------------------------------------------------");

           System.out.println("Displayed Successfully \n");
    }

    private static void toSearchAnAccount() {
        System.out.println("Welcome to Search an Account");
        Scanner input = new Scanner(System.in);

        System.out.println("Enter the Id of the Account to be search");
        int id = input.nextInt();
        if (Bank.bankData.containsKey(id)) {
            System.out.println("Name: " + Bank.bankData.get(id).getAccountName());
            System.out.println("Mobile Number: " + Bank.bankData.get(id).getMobileNumber());
            System.out.println("Balance: " + Bank.bankData.get(id).getAccountBalance());
            System.out.println("Type of Account: " + Bank.bankData.get(id).getTypeOfAccount());

            System.out.println("Your Account details printed Successfully");
        } else {
            System.out.println("Account does not exist with this ID");
        }
    }
}