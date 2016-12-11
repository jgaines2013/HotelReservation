
/**
 * Describes a template for a strategy that formats a transaction 
 * into some kind of receipt
 * @author Brandon Cecilio
 */
public interface ReceiptFormatter {
	/**
	 * Formats the transaction into a receipt
	 * @param t	the transaction
	 * @return	formatted receipt
	 */
	public String formatReceipt(Transaction t);
}
