//package hotel;

import java.util.TreeSet;
import java.util.Date;
import java.util.Set;

/**
 * Describes a single user of the application,
 * and whether or not they are a guest or an administrator
 */
public class User implements Comparable<User>{
	private String name;
	private int id;
	private boolean privileged;
	private Set<Reservation> activeReservations;
	
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
		activeReservations = new TreeSet<>();
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
	
	/**
	 * Creates a new reservation with the user's info and adds it to the user's active reservations
	 * @param room      room to reserve
	 * @param checkIn   check in date
	 * @param checkOut  check out date
	 * @return res      the newly made reservation
	 */
	public Reservation makeReservation(Room room, Date checkIn, Date checkOut){
		Reservation res = new Reservation(this, room, checkIn, checkOut);
		activeReservations.add(res);
		return res;
	}
	
	/**
	 * Adds a reservation to the user's active reservations
	 * @param target    the reservation to remove
	 * @return          whether or not addition was successful (if reservation was not found among active ones)
	 */
	public boolean addReservation(Reservation toAdd){
		return activeReservations.add(toAdd);
	}
	
	/**
	 * Removes a reservation from the user's active reservations
	 * @param target    the reservation to remove
	 * @return          whether or not removal was successful (if reservation was found among active ones)
	 */
	public boolean removeReservation(Reservation target){
		return activeReservations.remove(target);
	}
	
	/**
	 * Retrieves a Set view into all of the user's active reservations
	 * @return user's active reservations
	 */
	public Set<Reservation> getReservations(){
		return activeReservations;
	}
	
	/**
	 * Compares this user to another by id
	 * @param that  the other user
	 * @return      if this user is less than, equal to, or greater than the other
	 */
	public int compareTo(User that){ return this.id - that.id; }
	
	
	/**
	 * Checks if the current user is equal to another user
	 * @param that  the other user
	 * @return      if this user is equal to the other
	 */
	public boolean equals(Object that){
		return that instanceof User && this.compareTo((User)that) == 0;
	}
	
	/**
	 * Returns string representation of this User
	 * @return string representation
	 */
	public String toString(){ return (privileged ? "Mgr" : "Guest") + " " + id + ": " + name; };
}
