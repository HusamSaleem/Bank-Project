package Interfaces;
import AllClasses.Customer;

/**
 * Interface: Account
 * This interface is essential to define what methods are needed to make an account to the classes that implement it. 
 * @author Husam Saleem
 * @date 03/20/20
 * CS 108 Section 1
 */
public interface Account {
	public boolean addAccountHolder();
	public boolean updateAccount(Customer cust);
	public boolean updateAccount(Customer cust, int id);
	public void deleteAccount();
	public int getAccountID(Account acc);
}