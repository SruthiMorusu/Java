
package iss.nus.edu.sg.SouvinierStore.system;

/**
 * Name					Date
 * ---------------------------------
 * Sruthi 				17.03.2014

 * @author Sruthi
 * Since 17.03.2014
 * 
 * Modified By Sruthi*/

public class BillItem {

	private Product product;

	private int quantity;
		
	public BillItem(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	// This if for the arrow up and down, and it means add 1 or minus 1. 16-Mar-2014, Xu Lei.
	// 17-Mar-2014, Xu Lei.
	public void increaseQuantity() {
		quantity++;
	}

	// This if for the arrow up and down, and it means add 1 or minus 1. 16-Mar-2014, Xu Lei.
	public void decreaseQuantity() {
		quantity++;
	}
	
}