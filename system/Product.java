package iss.nus.edu.sg.SouvinierStore.system; 

import iss.nus.edu.sg.SouvinierStore.LabelPrinter;

import java.text.DecimalFormat;

/**
 * Name					Date
 * ---------------------------------
 * Karthik 				11.03.2014
 * 
 * Author Sruthi
 * Since 11.03.2014
 * 
 * Modified By Karthik*/

public class Product
{
    private String id;
    private String name;
    private String description;
    private int quantityAvail;
    private double price;
    private String barCode;
    private int threshold;
    private int orderQuantity;
    private Category category;
    DecimalFormat df = new DecimalFormat("#.00");

    /**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the brief
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param brief the brief to set
	 */
	public void setDescription(String brief) {
		this.description = brief;
	}

	/**
	 * @return the quantityAvail
	 */
	public int getQuantityAvail() {
		return quantityAvail;
	}

	/**
	 * @param quantityAvail the quantityAvail to set
	 */
	public void setQuantityAvail(int quantityAvail) {
		this.quantityAvail = quantityAvail;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the barCode
	 */
	public String getBarCode() {
		return barCode;
	}

	/**
	 * @param barCode the barCode to set
	 */
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	/**
	 * @return the threshold
	 */
	public int getThreshold() {
		return threshold;
	}

	/**
	 * @param threshold the threshold to set
	 */
	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}

	/**
	 * @return the orderQuantity
	 */
	public int getOrderQuantity() {
		return orderQuantity;
	}

	/**
	 * @param orderQuantity the orderQuantity to set
	 */
	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	/**
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

    /**
     * Function to find out if a product needs replenishment
     */
    public boolean isNeedReplenish()
    {
    	if(getQuantityAvail() < getThreshold())
    	{
    		return true;
    	}
    	return false;
    }
    
    /** 
     * Function to print the barcode label for a prodcut
     */
    public void printLabel(LabelPrinter lp)
    {
    	//implement Printer class print function
    	lp.print(barCode);
    }
    
    /**
     * Overwridden toString()
     */
    public String toString() {
    	return(getId()+","+getName()+","+getDescription()+","+
    			getQuantityAvail()+","+df.format(getPrice())+","+
    			getBarCode()+","+getThreshold()+","+getOrderQuantity());
    }
}
