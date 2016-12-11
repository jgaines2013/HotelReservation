//package hotel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JOptionPane;

/**
 * Takes in reservations and checks if new reservations conflict with existing ones
 * Can also determine which reservations are active on certain days
 * 
 * @author Brandon Cecilio
 */
public class RoomScheduler {
	private Map<Integer, Set<Reservation>> reservations;
	
	/**
	 * Ctor for a Room Scheduler
	 */
	public RoomScheduler(){
		reservations = new HashMap<>();
	}
	
	/**
	 * Ctor for a Room Scheduler, initialized with a collection of Reservations
	 * @param reservations  the reservations to start with
	 * @throws SchedulingConflictException 
	 */
	public RoomScheduler(Collection<Reservation> reservations) throws SchedulingConflictException{
		this();
		if (reservations != null)
			for (Reservation r : reservations)
				this.add(r);
	}
	
	/**
	 * Adds a reservation to the scheduler
	 * @param r the reservation to add
	 * @return the reservation added, or null if it failed to add
	 * @throws SchedulingConflictException 
	 */
	public Reservation add(Reservation r) throws SchedulingConflictException{
		// fails if check-in time is before current date
		if (r.getCheckIn().before(new Date()))
			return null;
		int rnum = r.getRoom().getNum();
		// check if reservation conflicts with any others, time-wise
		Reservation conflict = checkOverlap(r);
		if (conflict == null){
			// check if tree has been initialized for this room number
			if (!reservations.containsKey(rnum))
				reservations.put(rnum, new TreeSet<>());
			
			// if adding succeeds
			if (reservations.get(rnum).add(r)){
				r.getGuest().addReservation(r);
				return r;
			}
		}
		else
			throw new SchedulingConflictException(conflict);
		return null;
	}
	
	
	/**
	 * Removes a reservation from the scheduler
	 * @param r the reservation to remove
	 * @return the reservation removed, or null if it was not found
	 */
	public Reservation remove(Reservation r){
		int rnum = r.getRoom().getNum();
		Set<Reservation> set = reservations.get(rnum);
		if (set != null && set.remove(r)){
			r.getGuest().removeReservation(r);
			if (set.isEmpty())
				reservations.remove(rnum);
			return r;
		}
		return null;
	}
	
	/**
	 * Checks if a reservation exists in the scheduler
	 * @param r the reservation to check
	 * @return  if the reservation is already in the scheduler
	 */
	public boolean has(Reservation r){
		int rnum = r.getRoom().getNum();
		Set<Reservation> set = reservations.get(rnum);
		return set != null && set.contains(r);
	}
	
	/**
	 * Retrieve the numbers of all rooms that have scheduled reservations
	 * @return  list of all reserved room numbers
	 */
	public ArrayList<Integer> getRoomNums(){
		return new ArrayList<>(reservations.keySet());
	}
	
	/**
	 * Retrieve a list of all scheduled reservations
	 * @return  list of all reservations
	 */
	public ArrayList<Reservation> getReservations(){
		Set<Reservation> res = new TreeSet<>();
		for (Set<Reservation> rs : reservations.values())
			for (Reservation r : rs)
				res.add(r);
		return new ArrayList<>(res);
	}
	
	/**
	 * Check if a reservation conflicts with any other scheduled reservations
	 * @param reservation   the reservation to check
	 * @return              true if there are no conflicts, false otherwise
	 */
	public boolean isAvailable(Reservation reservation){
		return checkOverlap(reservation) == null;
	}
	
	/**
	 * Check if a reservation conflicts with any other scheduled reservations,
	 * returning the conflicting reservation in question if it exists
	 * @param reservation   the reservation to check
	 * @return              the conflicting reservation, or null if there are no conflicts
	 */
	public Reservation checkOverlap(Reservation reservation){
		Set<Reservation> set = reservations.get(reservation.getRoom().getNum());
		if (set != null)
			for (Reservation r : set)
				if (r.overlaps(reservation))
					return r;
		return null;
	}
	
	/**
	 * Retrieve all active reservations for a certain day
	 * @param day   the day to check
	 * @return      list of all reservations on that day
	 */
	public ArrayList<Reservation> getByDay(Date day){
		Set<Reservation> onDay = new TreeSet<>();
		for (Set<Reservation> rs : reservations.values())
			for (Reservation r : rs)
				if (r.contains(day))
					onDay.add(r);
		return new ArrayList<>(onDay);
	}
	
	/**
	 * Retrieve all reservations scheduled for a certain room, given its number
	 * @param roomNum   number of the room to check
	 * @return          list of all reservations for that room
	 */
	public ArrayList<Reservation> getByRoom(int roomNum){
		return new ArrayList<>(reservations.get(roomNum));
	}
	
	/**
	 * Retrieve all reservations scheduled for a certain room
	 * @param room  the room to check
	 * @return      list of all reservations for that room
	 */
	public ArrayList<Reservation> getByRoom(Room room){
		return getByRoom(room.getNum());
	}
	
	// tester
	public static void main(String args[]) throws ParseException{
		RoomScheduler rs = new RoomScheduler();
		User u = new User("test guest", 10005, false);
		Room rm1 = new Room(101, RoomType.ECONOMY);
		Room rm2 = new Room(102, RoomType.ECONOMY);
		Room rm3 = new Room(103, RoomType.ECONOMY);
		
		SimpleDateFormat f = new SimpleDateFormat("MM/dd/yy");
		Reservation r1 = new Reservation(u, rm1, f.parse("11/12/13"), f.parse("11/13/13"));
		Reservation r2 = new Reservation(u, rm2, f.parse("11/13/13"), f.parse("11/14/13"));
		Reservation r3 = new Reservation(u, rm2, f.parse("11/12/13"), f.parse("11/13/13"));
		Reservation r4 = new Reservation(u, rm2, f.parse("11/15/13"), f.parse("11/16/13"));
		Reservation r5 = new Reservation(u, rm3, f.parse("11/13/13"), f.parse("11/16/13"));
		try {
			rs.add(r1);
			rs.add(r2);
			rs.add(r4);
			rs.add(r5);
			rs.add(r3); // conflicts with r2
		} catch (SchedulingConflictException e){
			e.printStackTrace();
		}
		System.out.println(rs.reservations);
		System.out.println(rs.getRoomNums());
		System.out.println(rs.getReservations());
		System.out.println(rs.getByDay(f.parse("11/13/13")));
		System.out.println(rs.getByRoom(rm2.getNum()));
		System.out.println(rs.has(r5));
		
		rs.remove(r5);
		System.out.println("\nRemoved ["+r5+"]\n");
		System.out.println(rs.reservations);
		System.out.println(rs.getRoomNums());
		System.out.println(rs.getReservations());
		System.out.println(rs.getByDay(f.parse("11/13/13")));
		System.out.println(rs.getByRoom(rm2.getNum()));
		System.out.println(rs.has(r5));
	}
}
