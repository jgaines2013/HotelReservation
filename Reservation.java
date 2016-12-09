//package hotel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Describes a single reservation in a hotel room
 */
public class Reservation implements Comparable<Reservation>{
	private User guest;
	private Room room;
	private Date start;
	private Date end;
	
	/**
	 * Ctor for a reservation
	 * @param u         the guest who will be staying
	 * @param r         the room they'll be staying in
	 * @param checkIn   day the guest checks in
	 * @param checkOut  day the guest checks out
	 */
	public Reservation(User u, Room r, Date checkIn, Date checkOut){
		if (checkOut.before(checkIn))
			throw new IllegalArgumentException("Check out date cannot be before check in date.");
		guest = u;
		room = r;
		start = checkIn;
		end = checkOut;
	}
	
	/**
	 * Checks if the current reservation overlaps with another
	 * @param that  the other reservation
	 * @return      if the two reservations would ever occupy the same room
	 */
	public boolean overlaps(Reservation that){
		if (!this.room.equals(that.room))
			return false;
		// check if any days are the same
		if (this.start.equals(that.start) || this.start.equals(that.end))
			return true;
		else if (this.end.equals(that.start) || this.end.equals(that.end))
			return true;
		
		// check if other reservation begins during this one
		else if (this.start.before(that.start) && this.end.after(that.start))
			return true;
		
		// check if this reservation begins during other one
		else if (that.start.before(this.start) && that.end.after(this.start))
			return true;
		
		return false;
	}
	
	/**
	 * Checks whether or not a certain day falls within the reservation's range
	 * @param day   the day to check
	 * @return      if the reservation covers that day
	 */
	public boolean contains(Date day){
		if (this.start.after(day) || this.end.before(day))
			return false;
		return true;
	}
	
	/**
	 * Gets guest
	 * @return guest
	 */
	public User getGuest(){ return guest; }
	
	/**
	 * Gets room
	 * @return room
	 */
	public Room getRoom(){ return room; }
	
	/**
	 * Gets check in date
	 * @return start
	 */
	public Date getCheckIn(){ return start; }
	
	/**
	 * Gets check out date
	 * @return end
	 */
	public Date getCheckOut(){ return end; }
	
	/**
	 * Compares the current reservation to another reservation
	 * @param that  the other reservation
	 * @return cmp  if the current reservation is less than, equal to, or greater than the other
	 */
	public int compareTo(Reservation that){
		int cmp = this.getGuest().getID() - that.getGuest().getID();
		if (cmp == 0)
			cmp = this.room.compareTo(that.room);
		if (cmp == 0)
			cmp = this.start.compareTo(that.start);
		if (cmp == 0)
			cmp = this.end.compareTo(that.end);
		return cmp;
	}
	
	/**
	 * Checks if the current reservation is equal to another reservation
	 * @param that  the other reservation
	 * @return      if this reservation is equal to the other
	 */
	public boolean equals(Object that){
		return that instanceof Reservation && this.compareTo((Reservation)that) == 0;
	}
	
	// tester
	public static void main(String args[]) throws ParseException{
		SimpleDateFormat f = new SimpleDateFormat("MM/dd/yy");
		User g = new User("me", 5, false);
		Room r = new Room("101", RoomType.ECONOMY);
		Date mon = f.parse("12/12/16");
		Date tue = f.parse("12/13/16");
		Date wed = f.parse("12/14/16");
		Date thu = f.parse("12/15/16");
		Reservation r1 = new Reservation(g, r, mon, wed);
		Reservation r2 = new Reservation(g, r, tue, thu);
		Reservation r3 = new Reservation(g, r, tue, wed);
		Reservation r4 = new Reservation(g, r, mon, thu);
		Reservation r5 = new Reservation(g, r, mon, tue);
		Reservation r6 = new Reservation(g, r, wed, thu);
		
		// true
		System.out.println(r1.overlaps(r2));
		System.out.println(r2.overlaps(r1));
		System.out.println(r3.overlaps(r4));
		System.out.println(r6.overlaps(r1));
		
		// false
		System.out.println(r5.overlaps(r6));
		System.out.println(r6.overlaps(r5));
	}
}
