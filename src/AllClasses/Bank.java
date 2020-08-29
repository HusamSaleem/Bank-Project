package AllClasses;
/**
 * Concrete Class: Bank
 * A class that has methods for getting the current month, ending it, also stores the total amount of customers and accounts created.
 * @author Husam Saleem
 * @date 03/20/20
 * CS 108 Section 1
 */

public class Bank {
	public static int accountsCreated;
	public static int totalCustomers;
	private int month;
	public boolean monthEnd;
	
	public Bank() {
		this.month = 0;
		monthEnd = false;
	}
	
	public int getMonth() {
		return this.month;
	}
	
	public int nextMonth() {
		if (!monthEnd) {
			System.out.println("It is not the end of the month!");
			return -1;
		}
		
		this.month++;
		if (this.month > 11) 
			this.month = 0;
		
		monthEnd = false;
		return this.month;
	}
	
	public int endOfMonth() {
		if (monthEnd) {
			System.out.println("It is already end of month!");
			return -1;
		}
		
		monthEnd = true;
		System.out.println("It is now end of month!");
		return this.month;
	}
}