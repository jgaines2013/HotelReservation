//package hotel;

/**
 * Describes a single room in the hotel
 */
public class Room {
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
	 * Checks if this room is equal to the other room
	 * @return if the rooms are the same
	 */
	public boolean equals(Object that){
		return that instanceof Room
				&& this.name.equals(((Room)that).name)
				&& this.type.equals(((Room)that).type);
	}
}
