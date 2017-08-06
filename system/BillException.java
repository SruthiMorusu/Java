package iss.nus.edu.sg.SouvinierStore.system;

/**
 * Name					Date
 * ---------------------------------
 * Sruthi 				17.03.2014
 * 
 * Such as loyalty points not enough and so on, will throw the Exception
 * 
 * @author Sruthi
 * Since 17.03.2014
 * 
 * Modified By Sruthi*/

public class BillException extends Exception {

	private static final long serialVersionUID = 1L;

	public BillException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public BillException(String message){
		super(message);
	}
	
}
