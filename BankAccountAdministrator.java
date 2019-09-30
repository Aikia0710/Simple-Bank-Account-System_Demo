/**
 * BankAccountAdministrator is a simple subclass of the BankAccountUser which also implements 
 * the abstract method in the interface BankAccountAdministrator Interface. It contains 
 * one field variable.
 * 
 * bankAccountUsers: a list of bank account users have contained in one array
 * 
 * @author Zibo Wang
 * @version 2018-11-21
 */
import java.util.ArrayList;

public class BankAccountAdministrator extends BankAccountUser implements BankAccountAdministratorInterface{
	private ArrayList<BankAccountUser> bankAccountUsers ;
	
	public BankAccountAdministrator(String username, String password) {
		super(username,password);
		this.bankAccountUsers = new ArrayList<BankAccountUser>();
	}
	
	/**
     *  Method for an adminstrator to log by providing a password. It
     *  is checked whether the password provided is correct.
     *  @param password The password provided for the login; this is
     *  to be compared to the password stored on the system.
     */
    public void login(String password) {
    	if(super.passwordCorrect(password)==true) {
    		super.setLoggedIn(true);
    	} else {
    		System.out.println("You password is incorrect, please try again");
    	}
    }
    
    /**
     *  Add a user to the list of all users who are in the
     *  responsibility of the administrator.
     *  @param user The user to be added to the responsibility of the
     *  adminstrator.
     */
    public void addUser(BankAccountUser user) {
    	if(super.getLoggedIn()==true) {
    		bankAccountUsers.add(user);
    	}
    }

    /**
     *  If an account can no longer be used, since either the user has
     *  forgotten their password or in case of a standard user has
     *  entered the password incorrectly too often the administrator
     *  can reset the account by setting a new password and resetting
     *  the number of failed login attempts to zero.
     *  @param bankAccountUser The bank account user for whom the
     *  account is to be reset.
     *  @param password The new password for the account that is to be
     *  reset.
     */
    public void resetAccount(BankAccountUser bankAccountUser, String password) {
    	if (super.getLoggedIn()==true) {
    		bankAccountUser.setPassword(password);
    		((BankAccountStandardUser) bankAccountUser).resetLoginAttempts();
    		}
    }
	
}
