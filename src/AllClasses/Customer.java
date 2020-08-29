package AllClasses;
/**
 * Concrete Class: Customer
 * A class that holds the customr's data, id, name and total accounts opened.
 * @author Husam Saleem
 * @date 03/20/20
 * CS 108 Section 1
 */

public class Customer {
	private final int custId;
	private String name;
	private int totalAccountsOpened;
	
	// Fix the id
	public Customer() {
		this.custId = Bank.totalCustomers++;
		this.name = "Customer[" + this.custId + "]";
		this.totalAccountsOpened = 0;
	}
	
	// Fix id
	public Customer(String name) {
		this.custId = Bank.totalCustomers++;
		this.name = name;
		this.totalAccountsOpened = 0;
	}
	
	public int getCustomerId() {
		return this.custId;
	}
	
	public String getCustomerName() {
		return this.name;
	}
	
	public void setCustomerName(String name) {
		this.name = name;;
	}
	
	public int getCustomerAccounts() {
		return this.totalAccountsOpened;
	}
	
	public void setCustomerAccounts(int totalAccountsOpened) {
		this.totalAccountsOpened = totalAccountsOpened;
	}
	
	@Override
	public String toString() {
		return "Customer: " + this.name + " | Customer ID: " + this.custId;
	}
}