package iss.nus.edu.sg.SouvinierStore.system;

/**
 * Name					Date
 * ---------------------------------
 * Sruthi 				17.03.2014
 * Sruthi 				25.03.2014
 * Sruthi               29.03.2014
 * 
 * @author Sruthi
 * Since 17.03.2013
 * 
 * Modified By Sruthi*/

import iss.nus.edu.sg.SouvinierStore.ReceiptPrinter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

public class Bill {

	// 17-Mar-2014, Sruthi.
	private ArrayList<BillItem> billItems;
	private double totalPrice;
	private double totalPriceAfterDiscount; 
	// discount will be the double such as 0.10, 0.15. 16-Mar-2014, Sruthi.
	private double discount;
	// 17-Mar-2014, Sruthi.
	private double cashAmount;
	// 17-Mar-2014, Sruthi.
	private double returnChange;
	private int redeemPoint;
	private int accumulatePoint;
	// 30-Mar-2014, Sruthi.
	private double amountForPay;
	// customer must be the member or non-member. 16-Mar-2014, Sruthi.
	private Customer customer;
	// for the price format. 16-Mar-2014, Sruthi.
	private static DecimalFormat df = new DecimalFormat("#.00");
	
	public Bill(Customer customer) throws BillException {
		// 29-Mar-2014, Sruthi.
		if(customer == null) {
			throw new BillException("Create new bill error, Customer cannot be null!");
		}
		this.customer = customer;
		billItems = new ArrayList<BillItem>();
		discount = ManagerFactory.getDiscountManager().getBestDiscountIfAvailable(customer, new Date());	
	}

	public double getTotalPriceAfterDiscount() {
		return Double.parseDouble(df.format(totalPriceAfterDiscount));
	}

	public double getDiscount() {
		return discount;
	}

	public double getTotalPrice() {
		return Double.parseDouble(df.format(totalPrice));
	}

	public Customer getCustomer() {
		return customer;
	}
	
	// 17-Mar-2014, Sruthi.
	public ArrayList<BillItem> getBillItems() {
		return billItems;
	}

	// count the total price. 16-Mar-2014, Sruthi.
	private void count() {
		totalPrice = 0;
		for(BillItem b : billItems) {
			totalPrice += b.getProduct().getPrice() * b.getQuantity();
		}
		totalPriceAfterDiscount = totalPrice * (1 - discount);
	}
	
	// 25-Mar-2014. Sruthi.
	private boolean validateProductNotInBill(Product product) {
		if(product == null) {
			return false;
		}
		for(BillItem bi : billItems) {
			if(product.getId().equals(bi.getProduct().getId())) {
				return false;
			}
		}
		return true;
	}

	// 29-Mar-2014, Sruthi.
	public boolean addBillItem(Product product, int quantity) throws BillException {
		if(product == null || quantity < 0) {
			return false;
		}
		// add validation to make sure no duplicated product in one bill. 17-Mar-2014.
		if(!validateProductNotInBill(product)) {
			throw new BillException("This product " + product.getId() + " is already exist in the bill.");
		}
		// add validation. 17-Mar-2014, Sruthi.
		if(product.getQuantityAvail() < quantity) {
			throw new BillException(product.getName() + " is not enoough. The maximum you can buy is " + product.getQuantityAvail() + ".");
		}
		boolean ifSuccess = billItems.add(new BillItem(product, quantity));
		if(ifSuccess) {
			count();
		}
		return ifSuccess;
	}

	// 17-Mar-2014, Sruthi.
	public boolean deleteBillItem(BillItem billItem) {
		if(billItem == null) {
			return false;
		}
		boolean ifSuccess = billItems.remove(billItem);
		if(ifSuccess) {
			count();
		}
		return ifSuccess;
	}
	
	// 17-Mar-2014, Sruthi.
	public boolean deleteBillItem(Product product) {
		if(product == null) {
			return false;
		}
		boolean ifSuccess = billItems.remove(getBillItem(product));
		if(ifSuccess) {
			count();
		}
		return ifSuccess;
	}
	
	// 17-Mar-2014, Sruthi.
	public BillItem getBillItem(Product product) {
		if(product == null) {
			return null;
		}
		for(BillItem b : billItems) {
			if(b.getProduct().getId().equals(product.getId())) {
				return b;
			}
		}
		return null;
	}
	
	// 17-Mar-2014, Sruthi.
	public void setProductQuantity(Product product, int quantity) {
		getBillItem(product).setQuantity(quantity);
		count();
	}

	// 100 points = 5 dollar. 16-Mar-2014, Sruthi.
	private double pointsToDollar(int points) {
		return points/20.0;
	}
	
	// 10 dollar = 1 point. round numbers. 17-Mar-2014, Sruthi.
	private int dollarToPoints(double dollar) {
		return (int) (dollar/10);
	}
	
	// 100 points = 5 dollar. 25-Mar-2014, Sruthi.
	private int getMaximumPointsForCurrentBill(double dollar) {
		return (int) (dollar * 20);
	}
	
	// for member, 1. point to dollar. 2. calculate change. 3. deduct the quantity of product in store. 4. deduct for the redeem. 
	// 5. accumulate for this bill. 6. add to transaction. 17-Mar-2014, Sruthi.
	// for member, first payment, then accumulate points. 17-Mar-2014, Sruthi.
	// for member. return the changes, may throw BillException such as loyal points not enough. 16-Mar-2014, Sruthi.
	private double pay(double amount, int points) throws BillException{
		Member member = (Member) customer;
		this.cashAmount = amount;
		if(member.getPoint() < points) {
			// 17-Mar-2014, Sruthi.
			throw new BillException("Points not enough in the account. Your loyal points remian: " + member.getPoint());
		}
		redeemPoint = points;
		double pointsToDollar = pointsToDollar(points);
		// Redeem cannot be more than total price. 25-Mar-2014, Sruthi.
		if(pointsToDollar > totalPriceAfterDiscount) {
			throw new BillException("Redeem cannot be more than total price. Maximum redeem points for this bill is " + 
					getMaximumPointsForCurrentBill(totalPriceAfterDiscount));
		}
		amountForPay = amount + pointsToDollar;
		if(amountForPay < totalPriceAfterDiscount) {
			throw new BillException("Amount or points not enough for the pay.");
		}
		returnChange = amountForPay - totalPriceAfterDiscount;
		for(BillItem bi : billItems) {
			bi.getProduct().setQuantityAvail(bi.getProduct().getQuantityAvail() - bi.getQuantity());
		}
		member.updatePoint(-redeemPoint);
		accumulatePoint = dollarToPoints(totalPriceAfterDiscount);
		member.updatePoint(accumulatePoint);
		ManagerFactory.getTransactionManager().addTransaction(this, new Date());
		return Double.parseDouble(df.format(returnChange));
	}
	
	// for non-member, 1. calculate change. 2. deduct the quantity of product in store. 3. add to transaction. 17-Mar-2014, Sruthi.
	// for non-member. return the changes, may throw BillException such as amount not enough. 16-Mar-2014, Sruthi.
	private double pay(double amount) throws BillException{
		this.cashAmount = amount;
		if(amount < totalPriceAfterDiscount) {
			throw new BillException("Amount not enough for the pay.");
		}
		returnChange = amount - totalPriceAfterDiscount;
		for(BillItem bi : billItems) {
			bi.getProduct().setQuantityAvail(bi.getProduct().getQuantityAvail() - bi.getQuantity());
		}
		ManagerFactory.getTransactionManager().addTransaction(this, new Date());
		return Double.parseDouble(df.format(returnChange));
	}
	
	public double payment(double amount, int points) throws BillException{
		if(customer instanceof Member) {
			// add validation. 29-Mar-2014, Sruthi.
			if(amount < 0 || points < 0) {
				throw new BillException("For Member, amount or redeem points should not be below 0.");
			}
			return pay(amount, points);
		} else if(customer instanceof NonMember){
			// add validation. 29-Mar-2014, Sruthi.
			if(amount < 0) {
				throw new BillException("For public, amount should not be below 0.");
			}
			return pay(amount);
		} else {
			throw new BillException("Bill System error.");
		}
	}
	
	// for member receipt. 30-Mar-2014, Sruthi.
	private String memberFormat() {
		StringBuilder sbuilder = new StringBuilder(generalFormate() + " (amount + redeem = $" + amountForPay +")\n");
		Member member = (Member) customer;
		sbuilder.append("member id: " + member.getId() + "   " + "member name: " + member.getName() + "\n");
		sbuilder.append("redeemPoint: " + redeemPoint + "   " + "accumulatePoint: " + accumulatePoint + "   " + "points remaining: " + member.getPoint());
		return sbuilder.toString();
	}
	
	// for non-member receipt. 17-Mar-2014, Sruthi.
	private String generalFormate() {
		StringBuilder sbuilder = new StringBuilder("Receipt: " + "\n");
		sbuilder.append("productName" + "    " + "price" + "   " + "quantity" + "\n");
		for(BillItem bi : billItems) {
			sbuilder.append(bi.getProduct().getName()+ "     $" + bi.getProduct().getPrice() + 
					"     " + bi.getQuantity() + "\n");
		}
		sbuilder.append("totalPrice: $" + getTotalPrice() + ", ");
		sbuilder.append("discount: " + discount + "%, ");
		sbuilder.append("totalPrice after discount: $" + getTotalPriceAfterDiscount() + "\n");
		sbuilder.append("cashAmount: $" + cashAmount + "     " + "returnChange: $" + Double.parseDouble(df.format(returnChange)));
		return sbuilder.toString();
	}

	public void print(ReceiptPrinter receiptPrinter) throws BillException {
		if(receiptPrinter == null) {
			return;
		}
		// add validation. 29-Mar-2014, Sruthi.
		if(cashAmount == 0 && redeemPoint == 0 && totalPrice != 0) {
			throw new BillException("cannot get the receipt before payment !");
		}
		if(customer instanceof Member) {
			receiptPrinter.print(memberFormat());			
		} else if(customer instanceof NonMember){
			receiptPrinter.print(generalFormate());		
		} else {
			throw new BillException("Bill System error");
		}
	}
	
	public String getEReceipt() throws BillException {
		// add validation. 29-Mar-2014, Sruthi.
		if(cashAmount == 0 && redeemPoint == 0 && totalPrice != 0) {
			throw new BillException("cannot get the receipt before payment !");
		}
		String receipt = null;
		if(customer instanceof Member) {
			receipt = memberFormat();			
		} else if(customer instanceof NonMember){
			receipt = generalFormate();
		} else {
			throw new BillException("Bill System error");
		}
		return receipt;
	}
	
}
