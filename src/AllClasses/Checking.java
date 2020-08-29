package AllClasses;
import Driver.BankApp;
import Interfaces.Account;

/**
 * Concrete Class: Checking
 * A sub-class of Deposit that is for checking accounts only
 * @author Husam Saleem
 * @date 03/20/20
 * CS 108 Section 1
 */

public class Checking extends Deposit {
	private final int interestRate = 1;

	public Checking() {
		super();
	}

	@Override
	public boolean withdrawMoney(int num) {
		if (super.accountBalance >= num) {
			super.accountBalance -= num;
			System.out.println("Updated Balance: " + super.accountBalance);
			
			withdrawCount++;
			return true;
		} else if (num > super.accountBalance) {
			System.out.println("Not Enough Balance");
			return false;
		} else if (num < 0) {
			System.out.println("Invalid Amount");
			return false;
		}

		return false;
	}

	@Override
	public int calcInterest() {
		int interestPerYear = (int) Math.floor((super.accountBalance * (((float)this.interestRate / 100)) / 12));
		return interestPerYear;
	}

	@Override
	public boolean addInterest() {
		if (BankApp.bank.monthEnd) {
			super.accountBalance += calcInterest();
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return new String("Checking | Balance: " + super.accountBalance + " | Withdrawals: " + super.withdrawCount
				+ " | Potential Interest: " + this.calcInterest());
	}

	@Override
	public void deleteAccount() {
		System.out.println("Are you sure you want to delete your account?");

		String userResp = BankApp.scan.next();
		if (userResp.toLowerCase().equals("y")) {
			System.out.println("Enter your customer ID:");

			int custId = BankApp.scan.nextInt();

			for (int i = 0; i < BankApp.allAccounts.size(); i++) {
				if (BankApp.allAccounts.get(i).firstHolder.getCustomerId() == custId) {
					System.out.println("Do you want to delete your checking account with AccID: " + custId + " ?");

					userResp = BankApp.scan.next();

					if (userResp.toLowerCase().equals("y")) {
						BankApp.allAccounts.remove(i);
					} else if (userResp.toLowerCase().equals("n")) {
						System.out.println("Customer ID Invalid");
					}
				}
			}
		} else if (userResp.toLowerCase().equals("n")) {
			System.out.println("No accounts were deleted");
		}
	}

	public int getAccountID(Account acc) {
		// TODO Auto-generated method stub
		return 0;
	}

}