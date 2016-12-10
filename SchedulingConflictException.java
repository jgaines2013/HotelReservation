//package hotel;

import java.text.SimpleDateFormat;

public class SchedulingConflictException extends Exception {
	public SchedulingConflictException(String msg){
		super(msg);
	}
	public SchedulingConflictException(Reservation conflict){
		super(formMessage(conflict));
	}
	
	private static String formMessage(Reservation conflict){
		StringBuilder msg = new StringBuilder();
		SimpleDateFormat f = new SimpleDateFormat("MM/dd/yy");
		
		msg.append("Room "+conflict.getRoom().getNum());
		msg.append(" is already booked "+f.format(conflict.getCheckIn()));
		msg.append(" - "+f.format(conflict.getCheckOut()));
		msg.append(" by "+conflict.getGuest());
		return msg.toString();
	}
}
