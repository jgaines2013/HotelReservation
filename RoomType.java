//package hotel;

/**
 * Lists the possible room types,
 * along with corresponding prices
 */
public enum RoomType {
	LUXURY(200), ECONOMY(100);
	
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
}
