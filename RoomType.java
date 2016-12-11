//package hotel;

/**
 * Lists the possible room types,
 * along with corresponding prices
 * 
 * @author Brandon Cecilio
 */
public enum RoomType {
	LUXURY(200), ECONOMY(100); // or was it $80 for economy?
	
	private double price;
	
	/**
	 * Ctor for assigning price to room type
	 * @param   price
	 */
	private RoomType(double price){ this.price = price; }
	
	/**
	 * Gets the price associated with a room type
	 * @return  price
	 */
	public double getPrice(){ return price; }
	
	/**
	 * Returns a string representation of the object
	 * @return string representation
	 */
	public String toString(){ return name()+String.format(" ($%.2f)", price); }
	
	// tester
	public static void main(String[] args){
		System.out.println(LUXURY);
	}
}
