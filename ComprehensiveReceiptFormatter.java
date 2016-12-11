import java.util.ArrayList;

/**
 * Describes a concrete strategy for formatting a transaction into a simple receipt
 * @author Brandon Cecilio
 */
public class ComprehensiveReceiptFormatter {
	/**
	 * Formats a transaction into a comprehensive receipt
	 * @param t the transaction
	 * @return	the receipt
	 */
	public String formatReceipt(Transaction t){
		StringBuilder receipt = new StringBuilder("Receipt for "+t.getCustomer());
		ArrayList<Reservation> order = new ArrayList<>(t.getCustomer().getReservations());
		double total = 0;
		for (Reservation r : order){
			double price = r.getRoom().getType().getPrice();
			receipt.append(dispRes(r));
			receipt.append("\t"+String.format(" ($%.2f)", price));
			total += price;
		}
		receipt.append("TOTAL = "+String.format(" ($%.2f)", total));
		return receipt.toString();
	}
	
	/**
	 * Helper for displaying a single reservation
	 * @param r	the reservation
	 * @return	formatted reservation
	 */
	public String dispRes(Reservation r){
		return r.getRoom() + ": " + r.getCheckIn() + " - " + r.getCheckOut();
	}
}
