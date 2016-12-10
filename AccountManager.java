//package hotel;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Manages guest and manager accounts in a hash map, where accounts are keyed by ID number.
 * Whenever a user signs up, they are assigned an ID number from the system as follows:
 * between 1000-9999 for managers, and 10000-infinity for guests.
 * To log in, a user must supply their system-assigned ID to access their account.
 */
public class AccountManager implements Serializable {
	private HashMap<Integer, User> users;
	//private RoomScheduler activeRes; // not sure about this honestly
	private Set<Room> rooms;
	
	// dummy accounts
	private static final User firstGuest = new User("user1", 1000, false);
	private static final User firstMgr = new User("mgr1", 10000, true);
	
	/**
	 * Ctor for an AccountManager, which is always initialized with two dummy accounts
	 */
	public AccountManager(){
		users = new HashMap<>();
		users.put(1000, firstMgr);
		users.put(10000, firstGuest);
		
		rooms = new TreeSet<>();
		for (int i = 1; i <= 10; i++){
			rooms.add(new Room(100+i,RoomType.LUXURY));
			rooms.add(new Room(200+i,RoomType.ECONOMY));
		}
	}
	
	/**
	 * Ctor for an AccountManager, taking in a collection of users to initialize it with
	 * @param accts users to add
	 */
	public AccountManager(Collection<User> accts){
		this();
		for (User u : accts){
			int id = u.getID();
			if (id != 1000 && id != 10000)
				users.put(id, u);
		}
	}
	/**
	 * Retrieves the next available ID number, depending on account type
	 * @param isMgr     if the user to be added is a manager account
	 * @return id       next available ID number
	 */
	private int assignNumber(boolean isMgr){
		int id;
		// grabs all ID numbers and sorts them
		SortedSet<Integer> sortedKeys = new TreeSet<>(users.keySet());
		if (isMgr){
			// grabs all ID numbers less than 10000
			SortedSet<Integer> mgrKeys = sortedKeys.subSet(1000, 10000);
			id = mgrKeys.last() + 1; // gets the last added mgr ID, plus one
		}
		else
			id = sortedKeys.last() + 1; // gets the last added guest ID, plus one
		
		if (id == 10000)
			throw new RuntimeException("Too many mgrs!");
		return id;
	}
	
	/**
	 * Sets up a new guest account, assigns and returns their new ID number
	 * @param name  the guest's name
	 * @return id   the guest's new ID number
	 */
	public int setupGuest(String name){
		int id = assignNumber(false);
		User guest = new User(name, id, false);
		users.put(id, guest);
		return id;
	}
	
	/**
	 * Sets up a new manager account, assigns and returns their new ID number
	 * @param name  the manager's name
	 * @return id   the manager's new ID number
	 */
	public int setupManager(String name){
		int id = assignNumber(true);
		User mgr = new User(name, id, true);
		users.put(id, mgr);
		return id;
	}
	
	/**
	 * Given an ID number, returns the user's account
	 * @param id    user's ID number	
	 * @return		user's account
	 */
	public User login(int id){
		if (id == 1000 || id == 10000)
			return null;
		return users.get(id);
	}
	
	/**
	 * Given an ID, permanently bans the user's account and removes it from the system
	 * @param id    ID number of the account to ban
	 * @return      banned user account
	 */
	public User permaban(int id){
		if (id == 1000 || id == 10000)
			return null;
		return users.remove(id);
	}
	
	/**
	 * Retrieves a collection of all the users in the database
	 * @return  all the users signed up for this application
	 */
	public Set<User> getAll(){
		SortedSet<User> accts = new TreeSet<>(users.values());
		accts.remove(firstGuest);
		accts.remove(firstMgr);
		return accts;
	}
	
	// tester
	public static void main(String args[]){
		AccountManager man = new AccountManager();
		
		SimpleDateFormat f = new SimpleDateFormat("MM/dd/yy");
		
		int target = man.setupGuest("shelly");
		int test = man.setupManager("brandon");
		man.setupGuest("jerry");
		System.out.println("initial: " + man.getAll());
		
		man.permaban(target);
		System.out.println("removed: " + man.getAll());
		
		man.setupManager("kim");
		System.out.println("added: " + man.getAll());
		System.out.println("All: " + man.users.values());
		System.out.println("User 10002 is " + man.login(10002));
	}
}
