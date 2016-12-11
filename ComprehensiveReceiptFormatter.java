import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Describes a concrete strategy for formatting a transaction into a simple receipt
 * @author Brandon Cecilio
 */
public class ComprehensiveReceiptFormatter implements ReceiptFormatter {
	/**
	 * Formats a transaction into a comprehensive receipt
	 * @param t the transaction
	 * @return	the receipt
	 */
	public String formatReceipt(Transaction t){
		StringBuilder receipt = new StringBuilder("Receipt for "+t.getCustomer()+"\n");
		ArrayList<Reservation> order = new ArrayList<>(t.getCustomer().getReservations());
		double total = 0;
		for (Reservation r : order){
			double price = r.getRoom().getType().getPrice();
			receipt.append(dispRes(r));
			receipt.append("\t"+String.format(" $%.2f", price)+"\n");
			total += price;
		}
		receipt.append("TOTAL = "+String.format(" $%.2f", total)+"\n");
		return receipt.toString();
	}
	
	/**
	 * Helper for displaying a single reservation
	 * @param r	the reservation
	 * @return	formatted reservation
	 */
	public String dispRes(Reservation r){
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
		return r.getRoom() + ": " + sdf.format(r.getCheckIn()) + " - " + sdf.format(r.getCheckOut());
	}
}
