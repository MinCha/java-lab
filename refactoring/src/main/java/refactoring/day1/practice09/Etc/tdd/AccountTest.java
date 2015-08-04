package refactoring.day1.practice09.Etc.tdd;

import junit.framework.TestCase;

public class AccountTest extends TestCase {

	Account account;
	protected void setUp() throws Exception {
		account = new Account(10000);
	}

	protected void tearDown() throws Exception {
		account = null;
	}

	public void testDeposit() {
		account.deposit(1000);
		assertEquals(11000, account.getBalance());
	}

	public void testWithdraw() {
		account.withdraw(1000);
		assertEquals(9000, account.getBalance());

	}

	public void testGetBalance() {
		assertEquals(10000, account.getBalance());
	}

	public void testGetCompoundInterest() {
		Account firstAccount = new Account(120000);
		assertEquals(265788, (int)firstAccount.getCompoundInterest( 2, 0.07));
		assertEquals(1025893, (int)firstAccount.getCompoundInterest( 7, 0.05));
		
		Account secondAccount = new Account(100000);
		assertEquals(854910, (int)secondAccount.getCompoundInterest( 2, 0.07));
		
		Account thirdAccount = new Account(0);
		assertEquals(0, (int)thirdAccount.getCompoundInterest( 2, 0.07));
	}

}
