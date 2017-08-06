package iss.nus.edu.sg.SouvinierStore.system;

/**
 * Name					Date
 * ---------------------------------
 * Sruthi 				18.03.2014
 * 
 * Author Sruthi
 * Since 18.03.2014
 * 
 **/

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Transaction implements Comparable<Transaction>{
	private int id;
	private ArrayList<BillItem> billItems;
	private Customer customer;
	private Date date;
	
	public Transaction(int transactionId, Customer customer, Date date) {
		billItems = new ArrayList<BillItem>();
		this.id = transactionId;
		this.customer = customer;
		this.date = date;
	}
	public int getTransactionId() {
		return id;
	}
	public ArrayList<BillItem> getBillItems() {
		return billItems;
	}
	public boolean addBillItem(BillItem billItem){
		return billItems.add(billItem);
	}
	public Customer getCustomer() {
		return customer;
	}
	
	public Date getDate() {
		return date;
	}
	@Override
	public String toString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(TransactionManager.DATE_FORMAT);
		String str = "";
		for(BillItem billItem : billItems){
			str += getTransactionId()+",";
			str += billItem.getProduct().getId() + ",";
			str += getCustomer().getId() + ",";
			str += billItem.getQuantity() + ",";
			str += dateFormat.format(getDate());
			str += "\n";
		}
    	return str.trim();
    }
	@Override
	public int compareTo(Transaction o) {
		int oid = o.getTransactionId();
		return id - oid;
	}
}
