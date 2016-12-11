import java.util.ArrayList;

/**
 * Models a set of reservations made by a user in one transaction
 * @author Brandon Cecilio
 */
public class Transaction {
	private ArrayList<Reservation> orders;
	private User guest;
	
	/**
	 * Ctor for a transaction, taking in the current user
	 * @param user	the current user
	 */
	public Transaction(User u){
		orders = new ArrayList<>();
		guest = u;
	}
	
	/**
	 * Retrieves a list of all orders in transaction
	 * @return list of all transactions
	 */
	public ArrayList<Reservation> getAll(){
		return orders;
	}
	
	/**
	 * Adds a reservation to this transaction
	 * @param r	the reservation to add
	 * @return	whether adding was successful
	 */
	public boolean add(Reservation r){
		return orders.add(r);
	}
	
	/**
	 * Removes a reservation from this transaction
	 * @param r	the reservation to remove
	 * @return	whether removal was successful
	 */
	public boolean remove(Reservation r){
		return orders.remove(r);
	}
	
	/**
	 * Retrieves the customer of this transaction
	 * @return	the customer
	 */
	public User getCustomer(){
		return guest;
	}
	
	/**
	 * Builds a formatted receipt of this transaction
	 * @param	formatter strategy
	 * @return	receipt in string form
	 */
	public String format(ReceiptFormatter formatter){
		return formatter.formatReceipt(this);
	}
}
