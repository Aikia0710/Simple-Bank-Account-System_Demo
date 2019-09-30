import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class Exercise3Tests {

	private BankAccountAdministrator root, deputy;
	private Customer customerJohn, customerMary, customerTony;
	private BankAccount bankAccountJohn, bankAccountMary, bankAccountTony;
	private BankAccountStandardUser john, mary, tony;

	@Before
	public void setUp() {
		root = new BankAccountAdministrator("Sam", "rootUser");
		deputy = new BankAccountAdministrator("Tim", "deputyUser");
		customerJohn = new Customer("John", "m", "Bham", "0121");
		customerMary = new Customer("Mary", "f", "Bham", "0121");
		customerTony = new Customer("Tony", "x", "Bham", "0121");
		bankAccountJohn = new BankAccount(customerJohn, "john");
		bankAccountMary = new BankAccount(customerMary, "mary");
		bankAccountTony = new BankAccount(customerTony, "tony");
		john = new BankAccountStandardUser("John", "johnUser", bankAccountJohn);
		mary = new BankAccountStandardUser("Mary", "maryUser", bankAccountMary);
		tony = new BankAccountStandardUser("Tony", "tonyUser", bankAccountTony);
	}

	// John logs in successfully and transfers
	// some money to Mary's bank account.
	@Test
	public void test1() {
		john.login("johnUser");

		// expected number of failed login attempts is 0
		int expectedLoginAttempts = 0;
		int actualLoginAttempts = john.getLoginAttempts();

		assertEquals(expectedLoginAttempts, actualLoginAttempts);
		assertTrue(john.getLoggedIn());

                // transfer fails because of insufficient funds
		john.transferMoney(bankAccountMary, 500, "john");

		double expectedBalance = 100;
		double actualBalance = mary.getBankAccount().getBalance();

		assertEquals(expectedBalance, actualBalance, 0.00001);

                // transfer succeeds
		john.transferMoney(bankAccountMary, 50, "john");
		double expectedBalanceJohn = 50;
		double actualBalanceJohn = john.getBankAccount().getBalance();
		assertEquals(expectedBalanceJohn, actualBalanceJohn, 0.00001);
		double expectedBalanceMary = 150;
		double actualBalanceMary = mary.getBankAccount().getBalance();
		assertEquals(expectedBalanceMary, actualBalanceMary, 0.00001);
	}

	// Mary makes 1 failed login attempt,
	// logs in using the correct password
	// and logs out. Then Mary tries to transfer some
	// money to Tony's bank account but forgets the password
	@Test
	public void test2() {
		mary.login("Mary");

		int expectedLoginAttempts = 1;
		int actualLoginAttempts = mary.getLoginAttempts();

		assertEquals(expectedLoginAttempts, actualLoginAttempts);

		assertFalse(mary.getLoggedIn());

		mary.login("maryUser");
		expectedLoginAttempts = 0;
		actualLoginAttempts = mary.getLoginAttempts();
		assertEquals(expectedLoginAttempts, actualLoginAttempts);
		assertTrue(mary.getLoggedIn());

		mary.logout();
		assertFalse(mary.getLoggedIn());

		mary.setPassword("MaryUser");
		mary.transferMoney(bankAccountTony, 20, "mary");

		double expectedBalance = 100;
		double actualBalance = tony.getBankAccount().getBalance();

		assertEquals(expectedBalance, actualBalance, 0.00001);

	}

	// Tony makes 3 failed login attempts and
	// then logs in using the correct password
	@Test
	public void test3() {

		// First failed login attempt
		tony.login("kornUser");

		int expectedLoginAttempts = 1;
		int actualLoginAttempts = tony.getLoginAttempts();

		assertEquals(expectedLoginAttempts, actualLoginAttempts);

		assertFalse(tony.getLoggedIn());

		// Second failed login attempt
		tony.login("cornuser");
		expectedLoginAttempts = 2;
		actualLoginAttempts = tony.getLoginAttempts();
		
		assertEquals(expectedLoginAttempts, actualLoginAttempts);
		assertFalse(tony.getLoggedIn());
        
		// Third failed login attempt
		tony.login("tuser");
		expectedLoginAttempts = 3;
		actualLoginAttempts = tony.getLoginAttempts();
		
		assertEquals(expectedLoginAttempts, actualLoginAttempts);
		assertFalse(tony.getLoggedIn());
		
		// Fourth attempt is successful
		tony.login("tonyUser");
		expectedLoginAttempts = 0;
		actualLoginAttempts = tony.getLoginAttempts();
		
		assertEquals(expectedLoginAttempts, actualLoginAttempts);
		assertTrue(tony.getLoggedIn());

	}

	// John makes 4 failed login attempts and
	// calls root. Root logs in successfully
	// and then resets John's password and John's login attempts to 0.
	@Test
	public void test4() {

		// First failed login attempt
		john.login("wehatUser");

		int expectedLoginAttempts = 1;
		int actualLoginAttempts = john.getLoginAttempts();

		assertEquals(expectedLoginAttempts, actualLoginAttempts);

		assertFalse(john.getLoggedIn());

		// Second failed login attempt
		john.login("wheatuser");
		expectedLoginAttempts = 2;
		actualLoginAttempts = john.getLoginAttempts();

		assertEquals(expectedLoginAttempts, actualLoginAttempts);
		assertFalse(john.getLoggedIn());

		// Third failed login attempt
		john.login("wheatuser");
		expectedLoginAttempts = 3;
		actualLoginAttempts = john.getLoginAttempts();

		assertEquals(expectedLoginAttempts, actualLoginAttempts);
		assertFalse(john.getLoggedIn());

		// Fourth failed login attempt
		john.login("wheatuser");
		// Failed login attempts remain 3
		expectedLoginAttempts = 3;
		actualLoginAttempts = john.getLoginAttempts();

		assertEquals(expectedLoginAttempts, actualLoginAttempts);
		assertFalse(john.getLoggedIn());

		// Administrator resets john's password
		root.login("rootUser");
		root.addUser(john);
		root.resetAccount(john, "JohnUser");

		// John logs in successfully using the
		// new password
		john.login("JohnUser");

		expectedLoginAttempts = 0;
		actualLoginAttempts = john.getLoginAttempts();

		assertEquals(expectedLoginAttempts, actualLoginAttempts);
		assertTrue(john.getLoggedIn());

	}

	// John makes 4 failed login attempts and
	// calls root. Root forgets to log in and tries
	// to reset John's password.
	@Test
	public void test5() {

		// First failed login attempt
		john.login("wehatUser");

		int expectedLoginAttempts = 1;
		int actualLoginAttempts = john.getLoginAttempts();

		assertEquals(expectedLoginAttempts, actualLoginAttempts);

		assertFalse(john.getLoggedIn());

		// Second failed login attempt
		john.login("wheatuser");
		expectedLoginAttempts = 2;
		actualLoginAttempts = john.getLoginAttempts();

		assertEquals(expectedLoginAttempts, actualLoginAttempts);
		assertFalse(john.getLoggedIn());

		// Third failed login attempt
		john.login("wheatuser");
		expectedLoginAttempts = 3;
		actualLoginAttempts = john.getLoginAttempts();

		assertEquals(expectedLoginAttempts, actualLoginAttempts);
		assertFalse(john.getLoggedIn());

		// Fourth failed login attempt
		john.login("wheatuser");
		// Failed login attempts remain 3
		expectedLoginAttempts = 3;
		actualLoginAttempts = john.getLoginAttempts();

		assertEquals(expectedLoginAttempts, actualLoginAttempts);
		assertFalse(john.getLoggedIn());

		// Administrator tries to reset john's password
		// without logging in
		root.addUser(john);
		root.resetAccount(john, "JohnUser");

		// John tries to log in again
		john.login("JohnUser");

		// login attempts still 3
		expectedLoginAttempts = 3;
		actualLoginAttempts = john.getLoginAttempts();

		assertEquals(expectedLoginAttempts, actualLoginAttempts);
		assertFalse(john.getLoggedIn());
	}

	// John makes 4 failed login attempts and
	// calls root. Root calls in deputy
	// to reset John's password.
    	@Test
	public void test6() {

		// First failed login attempt
		john.login("wehatUser");

		int expectedLoginAttempts = 1;
		int actualLoginAttempts = john.getLoginAttempts();

		assertEquals(expectedLoginAttempts, actualLoginAttempts);

		assertFalse(john.getLoggedIn());

		// Second failed login attempt
		john.login("wheatuser");
		expectedLoginAttempts = 2;
		actualLoginAttempts = john.getLoginAttempts();

		assertEquals(expectedLoginAttempts, actualLoginAttempts);
		assertFalse(john.getLoggedIn());

		// Third failed login attempt
		john.login("wheatuser");
		expectedLoginAttempts = 3;
		actualLoginAttempts = john.getLoginAttempts();

		assertEquals(expectedLoginAttempts, actualLoginAttempts);
		assertFalse(john.getLoggedIn());

		// Fourth failed login attempt
		john.login("wheatuser");
		// Failed login attempts remain 3
		expectedLoginAttempts = 3;
		actualLoginAttempts = john.getLoginAttempts();

		assertEquals(expectedLoginAttempts, actualLoginAttempts);
		assertFalse(john.getLoggedIn());

		// Administrator resets john's password
		deputy.login("deputyUser");
		deputy.addUser(john);
		deputy.resetAccount(john, "JohnUser");

		// John logs in successfully using the
		// new password	
		john.login("JohnUser");

		expectedLoginAttempts = 0;
		actualLoginAttempts = john.getLoginAttempts();

		assertEquals(expectedLoginAttempts, actualLoginAttempts);
		assertTrue(john.getLoggedIn());

		john.logout();
		assertFalse(john.getLoggedIn());
	}
    	
}
