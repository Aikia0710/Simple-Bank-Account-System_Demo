/**
 * BankAccountStandardUser is a simple subclass of the BankAccountUser which also implements 
 * the abstract method in the interface BankAccountStandardUserInterface. It contains two field
 * variables.
 * 
 * bankAccount: a bank account to specifically find a bank account user
 * loginAttempots: an integer type value which presents the number of log in times 
 * 
 * @author Zibo Wang
 * @version 2018-11-21
 */
public class BankAccountStandardUser extends BankAccountUser implements BankAccountStandardUserInterface {
	private BankAccount bankAccount;
	private int loginAttempts;
	private static final int MAXIMAL_LOGIN_ATTEMPTS = 3;
	/**
	 * BankAccountStandardUser is a constructor method simply created in the class 
	 * @param bankAccount is the bank account of the standard user
	 */
	public BankAccountStandardUser(String username, String password,BankAccount bankAccount){
		super(username,password);
		this.bankAccount = bankAccount;
		this.loginAttempts = 0;
	}
	
	/**
     *  Getter for the bankAccout.
     *  @return The bankAccount associated with the standard user.
     */
    public BankAccount getBankAccount() {
    	return bankAccount;
    }
    /**
     *  Method for a user to log in to internet banking by providing a
     *  password. It is first checked whether the account is still
     *  active (that is, not too many failed login attempts were made
     *  in the past) and secondly whether the password provided is
     *  correct. In case of a correct login the number of login
     *  attempts is reset to 0, else increased by 1.
     *  @param password The password provided for the login; this is
     *  to be compared to the password stored on the system.
     */
    public void login(String password) {
    	//to make login failed, must fullfill two conditions
    	//the password is incorrect and the login attempts over 3 times
    	if (super.passwordCorrect(password)==false || this.loginAttempts > MAXIMAL_LOGIN_ATTEMPTS) {
    		System.out.println("Login failed");
    		loginAttempts = getLoginAttempts() + 1;
 
    		if (getLoginAttempts() == 4) {
    			setLoginAttempts(3);
    	} 
    	} else {
    		System.out.println("Login success");
    		super.setLoggedIn(true);//call the method from the superclass
    		resetLoginAttempts();
    	}
    }
    
    /**
     *  Getter for the number of (failed) login attempts.
     *  @return The number of (failed) login attempts since last
     *  successful login or reset.
     */
    public int getLoginAttempts() {
    	return this.loginAttempts;
    }
    /**
     *  setter for loginAttempts
     *  @param loginAttempts New value for the variable loginAttempts.
     */
    public void setLoginAttempts(int loginAttempts){
    	this.loginAttempts = loginAttempts;
    }

    /**
     *  The method resets the number of (failed) login attempts to zero.
     */
    public void resetLoginAttempts() {
    	this.loginAttempts = 0;
    }

    /** 
     * The method transfers an amount of money from the this account
     * to the toAccount assumed that the user in logged in, the
     * password is correct (and that the account has sufficient
     * money), else an error message is printed. This is done by
     * calling the corresponding method in the BankAccount class
     * assumed the user is logged in.
     * @param toAccount The account to which the money is to be transferred.
     * @param amount The amount of money to be transferred.
     * @param password The password of the this account.
     */
    public void transferMoney(BankAccount toAccount,long amount,String password) {
    	if (super.getLoggedIn()==true){
    		bankAccount.transferMoney(toAccount, amount, password);
    	}
    }
    /** 
     * The method prints the balance assumed that the user is logged in,
     * else a corresponding message.
     */
    public void printBalance() {
    	if (super.getLoggedIn()==true) {
    		bankAccount.printBalance();
    	} else {
    		System.out.println("You failed to login, please try again");
    	}
    }
    /** 
     * The method prints a statement if the user is logged in,
     * else a request to first log in is printed.
     */
    public void printStatement() {
    	if (super.getLoggedIn()==true) {
    		bankAccount.printStatement();
    	} else {
    		System.out.println("Welcome" + super.getUsername() + ", pleas log into your account");
    	}
    }
    
}
