//package hotel;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

public class RoomScheduler implements Serializable {
	private HashMap<String, Set<Reservation>> reservations;
	private Set<Room> rooms;
	
	public RoomScheduler(){
		rooms = new TreeSet<>();
		for (int i = 0; i < 10; i++){
			rooms.add(new Room(101+i+"",RoomType.LUXURY));
			rooms.add(new Room(201+i+"",RoomType.ECONOMY));
		}
		
		reservations = new HashMap<>();
		for (Room rm : rooms)
			reservations.put(rm.getName(),new TreeSet<>());
	}
	
	public boolean checkReservation(String roomName, Reservation res){
		boolean booked = false;
		for (Reservation r : reservations.get(roomName))
			if (r.overlaps(res))
				booked = true;
		return booked;
	}
	
	public boolean checkDate(String roomName, Reservation res){
		boolean booked = false;
		for (Reservation r : reservations.get(roomName))
			if (r.overlaps(res))
				booked = true;
		return booked;
	}
}
