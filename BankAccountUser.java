/**
 * this class is defined as the BanAccountUser, which will implement all abstract 
 * methods then extend it's functions, it contains three field variables. 
 * 
 * username:  a String type value which help to uniquely find a BankAccountUser
 * password: a String type value for the password of the BankAccountUser
 * loggediN: a boolean type value is used to show if a bankAccountUser is logged in 
 * 
 * @author Zibo Wang
 * @version 2018-11-21 
 */
public abstract class BankAccountUser implements BankAccountUserInterface{
	private String username;
	private String password;
	private Boolean loggedIn;
	/**
	 * BankAccountUser is a constructor for a simple BanAccountCreated
	 * @param username is the user name of a BankAccountUser
	 * @param password is the password of a BankAccountUser
	 */
	public BankAccountUser(String username, String password) {
		this.username = username;
		this.password = password;
		this.loggedIn = false;
	}
	/**
	 * getter method for the user name
	 * @return the value of the user name
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * setter method for the user name
	 * @param username setting a new value for the user name 
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	public abstract void login(String password); //abstract method
	
	/**
     *  The internet user is no longer logged in, indicated by the
     *  loggedIn variable set to false.
     */
	public void logout() {
		setLoggedIn(false);
	}
	
	/**
     * The method checks whether a provided password is correct.
     * @param password A password string that is to be compared to the
     * stored password.
     * @return true if the provided password is equal to the stored
     * password, false else.
     */
	public boolean passwordCorrect(String password) {
		if (this.password.equals(password)) {
			return true;
		} return false;
	}
	
	/**
     *  Setter for the password.
     *  @param password The new password.
     */
    public void setPassword(String password) {
    	this.password = password;
    }

    /**
     *  Getter to check whether a user is logged in.
     *  @return true if the user is looged in, false else.
     */
    public boolean getLoggedIn() {
    	return this.loggedIn;
    }

    /**
     *  setter for loggedIn
     *  @param loggedIn New value for the variable loggedIn
     */
    public void setLoggedIn(boolean loggedIn) {
    	this.loggedIn = loggedIn;
    }
	
}
