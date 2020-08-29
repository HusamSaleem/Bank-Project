package Driver;
import java.util.ArrayList;
import java.util.Scanner;

import AllClasses.Bank;
import AllClasses.Checking;
import AllClasses.Customer;
import AllClasses.Deposit;
import AllClasses.Savings;

/**
 * Main Driver: BankApp
 * This is the main driver that takes care of input, creating accounts, depositing, and all those features that the user can do.
 * @author Husam Saleem
 * @date 03/20/20
 * CS 108 Section 1
 */

public class BankApp {

	public static Scanner scan = new Scanner(System.in);
	public static Bank bank = new Bank();
	public static ArrayList<Deposit> allAccounts = new ArrayList<Deposit>();
	public static ArrayList<Customer> registeredCustomers = new ArrayList<Customer>();
	
	private boolean printIntro = false;

	public static void main(String[] args) {
		BankApp b = new BankApp();

		b.printMenu();
	}

	public BankApp() {
		// default ctor
	}

	public void printMenu() {
		
		if (!printIntro) {
			System.out.println("The Local Union");
			printIntro = true;
		}
		System.out.println();
		System.out.println("1. Open a new account.");
		System.out.println("2. Add a second holder to an existing account.");
		System.out.println("3. Deposit/Withdraw");
		System.out.println("4. Delete a current account.");
		System.out.println("5. Print details about a account.");
		System.out.println("6. Print details about a customer.");
		System.out.println("7. Update to end month [reset withdrawals & add interest].");
		System.out.println("8. Update to next month.");
		System.out.println("9. Print details about month.");
		System.out.println("0. Exit\n");
		System.out.println("Enter a relevant option: ");

		int userResponse = scan.nextInt();
		if (userResponse > 9 || userResponse < 0) {
			while (userResponse > 9 || userResponse < 0) {
				System.out.println("Enter a relevant option: ");
				userResponse = scan.nextInt();
			}
		}

		if (userResponse == 0) {
			System.out.println("Thanks for using the bank application");
		} else if (userResponse == 1) {
			optionOne();
		} else if (userResponse == 2) {
			 optionTwo();
		} else if (userResponse == 3) {
			optionThree();
		} else if (userResponse == 4) {
			optionFour();
		} else if (userResponse == 5) {
			optionFive();
		} else if (userResponse == 6) {
			optionSix();
		} else if (userResponse == 7) {
			optionSeven();
		} else if (userResponse == 8) {
			optionEight();
		} else if (userResponse == 9) {
			optionNine();
		}
	}

	private void optionOne() {
		System.out.println();
		System.out.println("1. Deposit Account: Checking");
		System.out.println("2. Deposit Account: Savings");
		System.out.println("0. Return to the main menu");
		System.out.println("Enter the type of account you wish to open: ");
		
		int userResp = scan.nextInt();
		
		if (userResp == 1) {
			
			allAccounts.add(new Checking());
			printMenu();
		} else if (userResp == 2) {
			allAccounts.add(new Savings());
			printMenu();
		} else if (userResp == 0) {
			System.out.println("Exiting to Main Menu");
			printMenu();
		}
	}
	
	private void optionTwo() {
		System.out.println("Enter your account ID: ");
		int accId = scan.nextInt();
		
		findAccount(accId).addAccountHolder();
		printMenu();
	}
	
	private void optionThree() {
		System.out.println("Enter your account ID: ");
		int accId = scan.nextInt();
		
		System.out.println("1. Deposit");
		System.out.println("2. Withdraw");
		System.out.println("0. Return to Main Menu");
		
		int userResp = scan.nextInt();
		
		if (userResp == 1) {
			System.out.println("Enter the amount you wish to deposit: ");
			int depositAmt = scan.nextInt();
			
			findAccount(accId).depositMoney(depositAmt);
			printMenu();
		} else if (userResp == 2) {
			System.out.println("Enter the amount you wish to withdraw: ");
			int withdrawAmt = scan.nextInt();
			
			findAccount(accId).withdrawMoney(withdrawAmt);
			printMenu();
		} else if (userResp == 0) {
			System.out.println("Exiting to Main Menu");
			printMenu();
		} else {
			System.out.println("Exiting to main menu");
			printMenu();
		}
	}
	
	private void optionFour() {
		System.out.println("Enter your account ID: "); 
		
		int accId = scan.nextInt();
		findAccount(accId).deleteAccount();
		printMenu();
	}
	
	private void optionFive() {
		System.out.println("Enter your account ID: "); 
		
		int accId = scan.nextInt();
		System.out.println(findAccount(accId).toString());
		printMenu();
	}
	
	private void optionSix() {
		System.out.println("Enter your customer ID: "); 
		
		int accId = scan.nextInt();
		System.out.println(findCustomer(accId).toString());
		printMenu();
	}
	
	private void optionSeven() {
		if(bank.endOfMonth() != -1) {
			for (int i = 0; i < allAccounts.size(); i++) {
				allAccounts.get(i).addInterest();
				allAccounts.get(i).resetWithdrawals();
			}
		}
		printMenu();
	}
	
	private void optionEight() {
		bank.nextMonth();
		printMenu();
	}
	
	private void optionNine() {
		System.out.println("Current Month: " + bank.getMonth());
		System.out.println("Month End Flag: " + bank.monthEnd);
		printMenu();
	}
	
	private Deposit findAccount(int id) {
		for (int i = 0; i < allAccounts.size(); i++) {
			if (allAccounts.get(i).getAccountId() == id) {
				return allAccounts.get(i);
			}
		}
		return null;
	}
	
	private Customer findCustomer(int id) {
		for (int i = 0; i < registeredCustomers.size(); i++) {
			if (registeredCustomers.get(i).getCustomerId() == id) {
				return registeredCustomers.get(i);
			}
		}
		return null;
	}
}