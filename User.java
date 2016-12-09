//package hotel;

/**
 * Describes a single user of the application,
 * and whether or not they are a guest or an administrator
 */
public class User {
	private String name;
	private int id;
	private boolean privileged;
	
	/**
	 * Ctor for a user
	 * @param name      the user's name
	 * @param id        the user's system-assigned id number
	 * @param isAdmin   if the user is an admin
	 */
	public User(String name, int id, boolean isAdmin){
		this.name = name;
		this.id = id;
		privileged = isAdmin;
	}
	
	/**
	 * Gets the name
	 * @return name
	 */
	public String getName(){ return name; }
	
	/**
	 * Gets the ID number
	 * @return id
	 */
	public int getID(){ return id; }
	
	/**
	 * Checks if user is an admin
	 * @return if user is admin
	 */
	public boolean isAdmin(){ return privileged; }
}
