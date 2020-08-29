package AllClasses;
import Driver.BankApp;
import Interfaces.Account;

/**
 * Concrete Class: Savings
 * A sub-class of Deposit that is for savings account only
 * @author Husam Saleem
 * @date 03/20/20
 * CS 108 Section 1
 */
public class Savings extends Deposit {
	private final int interestRate = 4;

	public Savings() {
		super();
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
					System.out.println("Do you want to delete your savings account with AccID: " + custId + " ?");

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

	@Override
	public boolean withdrawMoney(int num) {
		if (super.accountBalance >= num && super.withdrawCount < 3) {
			super.accountBalance -= num;
			System.out.println("Updated Balance: " + super.accountBalance);
			System.out.println("Remaining Withdrawals: " + (3-++super.withdrawCount));

			return true;
		} else if (num > super.accountBalance) {
			System.out.println("Not Enough Balance");
			return false;
		} else if (num < 0) {
			System.out.println("Invalid Amount");
			return false;
		} else if (super.withdrawCount == 3) {
			System.out.println("Withdrawals Limit Exceeded");
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
		return new String("Savings | Balance: " + super.accountBalance + " | Withdrawals: " + super.withdrawCount
				+ " | Potential Interest: " + this.calcInterest());
	}

}
