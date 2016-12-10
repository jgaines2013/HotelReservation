//package hotel;

import java.util.Date;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

/**
 * Takes in reservations and checks if new reservations conflict with existing ones
 * Can also determine which reservations are active on certain days
 */
public class RoomScheduler {
	private HashMap<Integer, Set<Reservation>> reservations;
	
	/**
	 * Ctor for a Room Scheduler
	 */
	public RoomScheduler(){
		reservations = new HashMap<>();
	}
	
	public Reservation add(Reservation r){
		int rnum = r.getRoom().getNum();
		// check if reservation conflicts with any others, time-wise
		if (!checkReservation(rnum, r)){
			// check if tree has been initialized for this room number
			if (!reservations.containsKey(rnum))
				reservations.put(rnum, new TreeSet<>());
			
			// if adding succeeds
			if (reservations.get(rnum).add(r))
				return r;
		}
		return null;
	}
	
	public Set<Integer> getRoomNums(){
		return reservations.keySet();
	}
	
	public Set<Reservation> getReservations(){
		Set<Reservation> res = new TreeSet<>();
		for (Set<Reservation> rs : reservations.values())
			for (Reservation r : rs)
				res.add(r);
		return res;
	}
	
	public boolean checkReservation(int roomNum, Reservation res){
		boolean booked = false;
		for (Reservation r : reservations.get(roomNum))
			if (r.overlaps(res))
				booked = true;
		return booked;
	}
	
	public Set<Reservation> getByDay(Date day){
		Set<Reservation> onDay = new TreeSet<>();
		for (Set<Reservation> rs : reservations.values())
			for (Reservation r : rs)
				if (r.contains(day))
					onDay.add(r);
		return onDay;
	}
	
	public Set<Reservation> getByRoom(Room roomNum){
		return reservations.get(roomNum);
	}
	
	public boolean checkDate(String roomName, Reservation res){
		boolean booked = false;
		for (Reservation r : reservations.get(roomName))
			if (r.overlaps(res))
				booked = true;
		return booked;
	}
}
