package CrudApp;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Bank1 {
	private int AccountID;
	private String AccountName;
	private long MobileNumber;
	private float AccountBalance;
	private String TypeOfAccount;

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

public class BankingCreation {
	public static void Bank(String[] args) {
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
		Bank1 obj = new Bank1();

		System.out.println("Enter The account Holder Name :");
		obj.setAccountName(input.next());

		System.out.println("Enter The Mobile Number :");
		obj.setMobileNumber(input.nextLong());

		System.out.println("Enter The Type of account savings or current :");
		obj.setTypeOfAccount(input.next());

		System.out.println("Enter the account balance :");
		obj.setAccountBalance(input.nextFloat());

		BankingOperations.toCreateAnAccount(obj);
	}

	public static void toRemoveAnAccount() {
		System.out.println("Welcome Remove an Account");
		Scanner input = new Scanner(System.in);

		System.out.println("Enter the ID of the Account to remove");
		int id = input.nextInt();
		BankingOperations.toRemoveAnAccount(id);
	}

	public static void toUpdateAnAccount() {
		System.out.println("Welcome Update a Account");
		Scanner input = new Scanner(System.in);

		System.out.println("Enter the ID of the Student to update:");
		int id = input.nextInt();
		if (BankingOperations.BankExists(id)) {
			System.out.println("1. Update name");
			System.out.println("2. Update balance");
			System.out.println("3. Update number");
			System.out.println("4. Update type");
			System.out.println("Enter your choice");
			int choice = input.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Enter the update name: ");
				String name = input.next();
				BankingOperations.toUpdateAccountName(name, id);

				break;
			case 2:
				System.out.println("Enter the update balance: ");
				long balance = input.nextLong();
				BankingOperations.toUpdateAccountBalance(balance, id);

				break;
			case 3:
				System.out.println("Enter the update Mobile Number: ");
				Long number = input.nextLong();
				BankingOperations.toUpdateMobileNumber(number, id);
				break;
			case 4:
				System.out.println("Enter the update type of Account: ");
				String Type = input.next();
				BankingOperations.toUpdateTypeOfAccount(Type, id);
				break;

			default:
				System.out.println("Invalid choice!!!!!");
				input.close();
				return;
			}
			System.out.println("Account  is  Successfully \n");
		} else {
			System.out.println("Account does not exist with this id !!!!!!!\n");
		}
		toDisplayAnAccount();
//		input.close();
	}

	public static void toDisplayAnAccount() {
		HashMap<Integer, Bank1> bankData1 = BankingOperations.toDisplayAccountData();
		System.out.println(
				"-------------------------------------------------------------------------------------------------------");
		System.out.println("Account ID \t\t Account Name \t\t Account Balance \t\t Mobile Number \t\t Type of Account");
		System.out.println(
				"-------------------------------------------------------------------------------------------------------");
		for (Map.Entry<Integer, Bank1> bankData : bankData1.entrySet()) {
			System.out.print(bankData.getKey() + "\t\t");
			System.out.print(bankData.getValue().getAccountName() + "\t\t");
			System.out.print(bankData.getValue().getAccountBalance() + "\t\t");
			System.out.print(bankData.getValue().getMobileNumber() + "\t\t");
			System.out.println(bankData.getValue().getTypeOfAccount());
		}

		System.out.println(
				"-------------------------------------------------------------------------------------------------------");
		System.out.println("Printed Account Data Successfully");
	}

	public static void toSearchAnAccount() {
		System.out.println("Welcome Search an Account");
		Scanner input = new Scanner(System.in);

		System.out.println("Enter the ID of the Account to search");
		int id = input.nextInt();
		if (BankingOperations.BankExists(id)) {
			Bank1 bankData = BankingOperations.toGetbankData(id);
			System.out.println("Account name :" + bankData.getAccountName());
			System.out.println("Account Balance :" + bankData.getAccountBalance());
			System.out.println("Mobile Number :" + bankData.getMobileNumber());
			System.out.println("Type of Account :" + bankData.getTypeOfAccount());
			System.out.println("Account is Displayed Successfully \n");
		} else {
			System.out.println("Account does not exist with this id !!!!!!!\n");
		}
//		input.close();

	}

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("\t\t\tAccount Database Application\n");
		boolean flag = true;
		while (flag) {
			printMenu();
			System.out.println("Enter your choice");
			int choice = s.nextInt();
			switch (choice) {
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
				flag = false;
				break;

			default:
				System.err.println("Invalid choice");
				break;
			}
		}
		// s.close();
		System.out.println("Have a good day");
	}
}
