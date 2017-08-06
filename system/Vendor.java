package iss.nus.edu.sg.SouvinierStore.system;

/**
 * Name					Date
 * ---------------------------------
 * Lei Xu 				17.03.2014
 * 
 * @author Lei Xu
 * Since 17.03.2014
 * 
 * Modified By Lei Xu*/

public class Vendor {

	private String name;

	private String description;
	
	public Vendor(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

}