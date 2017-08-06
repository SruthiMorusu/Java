package iss.nus.edu.sg.SouvinierStore.system; 

/**
 * Name					Date
 * ---------------------------------
 * Xing Zibo		13.03.2014
 * 
 * @author Xing Zibo 
 * Since 23.03.2014
 * 
 * Modified By Xing Zibo*/

public class PurchaseOrderItem
{
    private Product product;
    private int quantity;
    private Vendor vendor;
  
    public PurchaseOrderItem(Product product, int quantity, Vendor vendor){
    	this.product = product;
    	this.quantity = quantity;
    	this.vendor = vendor;
    }
    public Product getProduct(){
    	return product;
    }
    public int getQuantity(){
    	return quantity;
    }
    public Vendor getVendor(){
    	return vendor;
    }
    public boolean setQuantity(int quantity){
    	if(quantity < 0){
    		return false;
    	}
    	this.quantity = quantity;
    	return true;
    }
}
