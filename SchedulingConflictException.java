//package hotel;

import java.text.SimpleDateFormat;

/**
 * An exception for when a Reservation cannot be added to the system due to
 * a time conflict with an existing Reservation
 * 
 * @author Brandon Cecilio
 */
public class SchedulingConflictException extends Exception {
	/**
	 * Ctor taking in error message
	 * @param msg   the error message
	 */
	public SchedulingConflictException(String msg){
		super(msg);
	}
	
	/**
	 * Ctor taking in the existing reservation that caused the conflict
	 * @param conflict  reservation in system that prevented addition
	 */
	public SchedulingConflictException(Reservation conflict){
		super(formMessage(conflict));
	}
	
	/**
	 * Helper class for generating an error message, given the conflicting Reservation
	 * @param conflict  conflicting reservation in system
	 * @return          error msg describing conflict
	 */
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
