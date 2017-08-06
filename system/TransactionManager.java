package iss.nus.edu.sg.SouvinierStore.system;

/**
 * Name					Date
 * ---------------------------------
 * Sruthi 				18.03.2014
 * 
 * Author Sruthi
 * Since 18.03.2014
 * 
 * Modified By Xing Zibo*/

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TransactionManager implements IReport{
	public static final String TRANSACTION_DATA_FILE = "data/Transaction.dat";
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	private ArrayList<Transaction> transactions;
	private int currentTransactionId = 0; // for generate transactionId
	private SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
	private String dataFile = TRANSACTION_DATA_FILE;
	
	public TransactionManager(){
		transactions = new ArrayList<Transaction>();
		currentTransactionId = 0;
	}
	public boolean setDataFile(String fileNameWithPath){
		if(fileNameWithPath == null || fileNameWithPath.isEmpty()){
			return false;
		}
		dataFile = fileNameWithPath;
		return true;
	}
	public boolean addTransaction(Bill bill, Date date) {
		if(bill == null || date == null){
			System.out.println("error - TransactionManager addTransaction() invalidate param");
			return false;
		}
		Transaction transaction = new Transaction(++currentTransactionId, bill.getCustomer(), date);
		for(BillItem item : bill.getBillItems()){
			transaction.addBillItem(item);
		}
		return transactions.add(transaction);
	}
	
	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}
	/**
	 * 
	 * @param startDate
	 * @param endDate
	 * @return return empty ArrayList if nothing matched
	 * 
	 */
	public ArrayList<Transaction> getTransactions(Date startDate, Date endDate) {
		ArrayList<Transaction> returnList = new ArrayList<Transaction>();
		if(startDate == null || endDate == null){
			System.out.println("error - TransactionManager getTransactions() invalidate param");
			return returnList;
		}
		for(Transaction t : transactions){
			if(t.getDate().after(startDate) && t.getDate().before(endDate)){
				returnList.add(t);
			}
		}
		return returnList;
	}
	/**
	 * 
	 * @param id
	 * @return return null if not found
	 */
	public Transaction getTransaction(int id) {
		for(Transaction t: transactions){
			if(t.getTransactionId() == id){
				return t;
			}
		}
		return null;
	}
	
	public boolean saveTransactionToFile() {
		File fileToWrite = new File(dataFile);
		String data = "";
		for(Transaction transaction : transactions){
			data += transaction.toString() + '\n';
		}
		return (FileUtility.writeFile(fileToWrite, data, false));
	}
	
	public boolean readTransactionFromFile() {
		File fileToRead = new File(dataFile);
		if(!fileToRead.isFile()){
			System.out.println("error - TransactionManager readTransactionFromFile() file not found");
			return false;
		}
		// clean Manager data before read from file
		transactions.clear();
		ArrayList<String> fileContents = FileUtility.readFile(fileToRead);
		if(!fileContents.isEmpty()){
			for(String tempString : fileContents){
				String[] attributeList = tempString.split(",");
				formTransaction(attributeList);				
			}
		}
		for(Transaction transaction:transactions){
			if(currentTransactionId < transaction.getTransactionId()){
				currentTransactionId = transaction.getTransactionId();
			}
		}
		return true;
	}
	
	private void formTransaction(String[] attributeList) {
		boolean isMember = false;
		if(attributeList == null || attributeList.length < 5){
			System.out.println("error - TransactionManager formTransaction() invalidate param");
			return;
		}
		int id = 0;
		try {
			id = Integer.parseInt(attributeList[0].trim());
		} catch (Exception e) {
			System.out.println("error - TransactionManager formTransaction() format id faild");
			return;
		}
		String memberId = "";
		if("PUBLIC".equals(attributeList[2].trim())){
			isMember = false;
		}else{
			isMember = true;
		}
		memberId = attributeList[2].trim();
		int quantity = 0;
		try {
			quantity = Integer.parseInt(attributeList[3].trim());
		} catch (Exception e) {
			System.out.println("error - TransactionManager formTransaction() format quantity faild");
			return;
		}
		Date date;
		try {
			date = dateFormat.parse(attributeList[4].trim());
		} catch (ParseException e) {
			System.out.println("error - TransactionManager formTransaction() format Date faild");
			return;
		}
		String productId = attributeList[1].trim();
		Product product = ManagerFactory.getProductManager().getProduct(productId);
		if(product == null){
			// there is no such a product in system, maybe have been deleted.
			product = new Product();
			product.setId(productId);
			product.setName("");
			product.setBarCode("");
			product.setDescription("");
		}
		BillItem billItem = new BillItem(product, quantity);
		Transaction transaction = getTransaction(id);
		Customer customer = ManagerFactory.getCustomerManager().getCustomer(memberId);
		if(customer == null){
			// there is no such a member in system, maybe have been deleted.
			if(isMember){
				customer = new Member("", memberId, 0);
			}else{
				customer = new NonMember();
			}
		}
		if(transaction == null){
			transaction = new Transaction(id, customer, date);
			transaction.addBillItem(billItem);
			transactions.add(transaction);
		}else{
			transaction.addBillItem(billItem);
		}
	}
	public ArrayList<String> report(){
		ArrayList<String> strList = new ArrayList<String>();
		for(Transaction transaction : transactions){
			strList.add(transaction.toString());
		}
		return strList;
	};
	
	
    
}
