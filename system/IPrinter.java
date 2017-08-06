package iss.nus.edu.sg.SouvinierStore.system;

/**
 * Name					Date
 * ---------------------------------
 * Xing Zibo		13.03.2014
 * 
 * @author Xing Zibo 
 * Since 15.03.2014
 * 
 * Modified By Xing Zibo*/

public interface IPrinter {
	/**
	 * param: formated data return: true - success, false - print failed
	 */
	public boolean print(String Data);
}
