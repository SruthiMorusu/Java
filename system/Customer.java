package iss.nus.edu.sg.SouvinierStore.system;

/**
 * Name					Date
 * ---------------------------------
 * Sruthi 			13.03.2014
 * 
 * @author Sruthi 
 * Since 13.03.2014
 * 
 * Modified By Sruthi*/

public abstract class Customer {

	private String Id;
	
	// This constructor is defined for the subclasses' constructor default invoking,
	// so we usually define the empty constructor in the super class.  16-Mar-2014, Xu Lei.
	//public Customer(){}

	public Customer(String Id) {
		this.Id = Id;
	}

	public String getId(){
		return Id;
	}
}
