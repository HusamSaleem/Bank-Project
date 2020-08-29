package AllClasses;
import Driver.BankApp;
import Interfaces.Account;

/**
 * Abstract Class: Deposit
 * An abstract class that is extended by the checking and saving classes.
 * @author Husam Saleem
 * @date 03/20/20
 * CS 108 Section 1
 */
public abstract class Deposit implements Account {
	protected int accountId;
	protected Customer firstHolder;
	protected Customer secondHolder;
	protected int accountBalance;
	protected int withdrawCount;
	
	public Deposit() {
		this.accountId = Bank.accountsCreated++;
		firstHolder = this.checkCustomer();
		this.accountBalance = 0;
		this.withdrawCount = 0;
		
		System.out.println("A new account was created with account ID: " + this.accountId);
		System.out.println("The first holder is: " + firstHolder.toString());
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(int accountBalance) {
		this.accountBalance = accountBalance;
	}

	public int getWithdrawCount() {
		return withdrawCount;
	}

	public void setWithdrawCount(int withdrawCount) {
		this.withdrawCount = withdrawCount;
	}

	public Customer getFirstHolder() {
		return firstHolder;
	}

	public void setFirstHolder(Customer firstHolder) {
		this.firstHolder = firstHolder;
	}

	public Customer getSecondHolder() {
		return secondHolder;
	}

	public void setSecondHolder(Customer secondHolder) {
		this.secondHolder = secondHolder;
	}
	
	public boolean depositMoney(int money) {
		if (money <= 0)
			return false;
		
		this.accountBalance += money;
		System.out.println("Updated Balance: " + this.accountBalance);
		return true;
	}
	
	public Customer checkCustomer() {
		Customer result = null;
		
		System.out.println("Are you an existing customer? [Y: Yes; N: No]"); 
		
		String userResp = BankApp.scan.next();
		if (userResp.toLowerCase().equals("y")) {
			System.out.println("Enter Customer ID:");
			int custId = BankApp.scan.nextInt();
			
			for (int i = 0; i < BankApp.registeredCustomers.size(); i++) {
				if (BankApp.registeredCustomers.get(i).getCustomerId() == custId) {
					return BankApp.registeredCustomers.get(i);
				}
			}
			
			System.out.println("There was no record of the ID. A new ID will be created");
			return new Customer();
		} else if (userResp.toLowerCase().equals("n")) {
			System.out.println("Enter your name: ");
			
			String custName = BankApp.scan.next();
			if (BankApp.scan.hasNext() && !BankApp.scan.hasNextInt()) {
				custName = custName + BankApp.scan.next();
			}
			if (custName.length() == 0) {
				result = new Customer();
				BankApp.registeredCustomers.add(result);
				return result;
			} else {
				result = new Customer(custName);
				BankApp.registeredCustomers.add(result);
				return result;
			}
		}
		
		return result;
 	}
	
	public boolean addAccountHolder() {
		this.secondHolder = checkCustomer();
		
		System.out.println("For Account ID: " + this.accountId);
		System.out.println("First Holder: Customer: " + this.getFirstHolder().getCustomerName() + " | Customer ID: " + this.getFirstHolder().getCustomerId());
		System.out.println("Second Holder: Customer: " + this.getSecondHolder().getCustomerName() + " | Customer ID: " + this.getSecondHolder().getCustomerId());
		return true;
	}
	
	public boolean updateAccount(Customer cust) {
		this.firstHolder = cust;
		
		return true;
	}
	
	public boolean updateAccount(Customer cust, int accountHolderNum) {
		
		if (accountHolderNum != 1 || accountHolderNum != 2)
			return false;
		
		if (accountHolderNum == 1)
			this.firstHolder = cust;
		else if (accountHolderNum == 2)
			this.secondHolder = cust;
		
		return true;
	}
	
	public void deleteAccount() {
	}
	
	public void resetWithdrawals() {
		this.withdrawCount = 0;
	}
	
	public abstract boolean withdrawMoney(int num);
	public abstract int calcInterest();
	public abstract boolean addInterest();
	
}