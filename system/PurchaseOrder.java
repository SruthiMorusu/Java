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

import java.util.ArrayList;

public class PurchaseOrder
{
    private ArrayList<PurchaseOrderItem> orderItems;
    
    public PurchaseOrder(){
    	orderItems = new ArrayList<PurchaseOrderItem>();
    }
    public ArrayList<PurchaseOrderItem> getPurchaseItems()
    {
    	return orderItems;
    }
    
    public boolean addPurchaseItem(Product product, Vendor vendor, int quaitity)
    {
    	if(product == null || vendor == null || quaitity < 0){
    		return false;
    	}
    	PurchaseOrderItem order = new PurchaseOrderItem(product, quaitity, vendor);
    	return orderItems.add(order);
    }
    
    public boolean deletePurchaseItem(Product product)
    {
    	if(product == null){
    		return false;
    	}
    	return orderItems.remove(product);
    }
    
    public boolean cancelPuchaseOrder()
    {
    	orderItems.clear();
    	return true;
    }
    
    public boolean purchase()
    {
    	if(orderItems.isEmpty()){
    		return false;
    	}
    	int quantitiy = 0;
    	for(PurchaseOrderItem item : orderItems){
    		quantitiy = item.getQuantity();
    		item.getProduct().setOrderQuantity(quantitiy);
    	}
    	return true;
    }
}
