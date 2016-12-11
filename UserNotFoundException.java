//package hotel;

/**
 * An exception for when the system tries to reference a user who doesn't exist
 */
public class UserNotFoundException extends Exception {
	/**
	 * Ctor taking in error message
	 * @param msg   the error message
	 */
	public UserNotFoundException(String msg){
		super(msg);
	}
	
	/**
	 * Ctor taking in the id of the user that couldn't be found
	 * @param id    id of user not found
	 */
	public UserNotFoundException(int id){
		super("User with id: "+id+" was not found");
	}
}
