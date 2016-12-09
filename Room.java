//package hotel;

/**
 * Describes a single room in the hotel
 */
public class Room implements Comparable<Room>{
	private String name;
	private RoomType type;
	
	/**
	 * Ctor for a room
	 * @param name	name of the room
	 * @param type	room type, either ECONOMY or LUXURY
	 */
	public Room(String name, RoomType type){
		this.name = name;
		this.type = type;
	}
	
	/**
	 * Gets the name
	 * @return name
	 */
	public String getName(){ return name; }
	
	/**
	 * Gets the room type
	 * @return type
	 */
	public RoomType getType(){ return type; }
	
	/**
	 * Calculates the hash of the object
	 */
	public int hashCode(){
		return (int)(name.hashCode() * type.getPrice());
	}
	
	/**
	 * Compares this room to another room by name and type
	 * @param that  other room
	 * @return cmp  if this room is less than, equal to, or greater than the other
	 */
	public int compareTo(Room that){
		int cmp = this.name.compareTo(that.name);
		if (cmp == 0)
			cmp = this.type.ordinal() - that.type.ordinal();
		return cmp;
	}
	
	/**
	 * Checks if the current room is equal to another room
	 * @param that  the other room
	 * @return      if this room is equal to the other
	 */
	public boolean equals(Object that){
		return that instanceof Room && this.compareTo((Room)that) == 0;
	}
}
