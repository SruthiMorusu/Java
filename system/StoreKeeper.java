package iss.nus.edu.sg.SouvinierStore.system;

/** 
 * Name          	          Date 
 * -------------------------------------------------- 
 * Sruthi               19.03.2014 
 *  
 * Author : Sruthi
 * Since 18.03.2014 
 *  */

public class StoreKeeper {
	private String name;
	private String password;
	
	public StoreKeeper(String name, String password){
		this.name = name;
		this.password = password;
	}
	public boolean validate(String name, String password) {
		if(this.name .equals(name) && this.password.equals(password)){
			return true;
		}else{
			return false;
		}
	}
	
	public String getName() {
		return name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public boolean setPassword(String newPassword) {
		if(newPassword == null || newPassword.isEmpty() || newPassword.equals(password)){
			return false;
		}
		password = newPassword;
		return true;
	}
	@Override
    public String toString() {
    	return(getName() + ","+ getPassword());
    }
}
