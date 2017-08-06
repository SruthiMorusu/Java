package iss.nus.edu.sg.SouvinierStore.system;

/** 
 * Name          	          Date 
 * -------------------------------------------------- 
 * Sruthi               19.03.2014 
 *  
 * Author : Sruthi
 * Since 18.03.2014 
 *  */

public class Category {
	private String code;
	private String name;
	
	public Category(String code, String name){
		this.code = code;
		this.name = name;
	}
	public String getCategoryCode() {
		return code;
	}
	
	public String getName() {
		return name;
	}
	@Override
    public String toString() {
    	return(getCategoryCode().toUpperCase() + ","+ getName());
    }
}
