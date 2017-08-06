package iss.nus.edu.sg.SouvinierStore.system;

/**
 * Name					Date
 * ---------------------------------
 * Sruthi 				18.03.2014
 * 
 * Author Sruthi
 * Since 18.03.2014
 * 
 * Modified By Xing Zibo*/

import java.text.SimpleDateFormat;
import java.util.Date;

public class NonMemberDiscount {
	private String code = "";
	private String description = "";
	private Date startDate = null;
	private int daysPeriod = 0;
	private int percentage = 0;
	
	public NonMemberDiscount(String code, String description, Date startDate, int daysPeriod, int percentage){
		this.code = code;
		this.description = description;
		this.startDate = startDate;
		this.daysPeriod = daysPeriod;
		this.percentage = percentage;
	}
	
	public String getCode() {
		return code;
	}
	
	public String getDescription() {
		return description;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	
	public int getdaysPeriod() {
		return daysPeriod;
	}
	
	public int getPercentage() {
		return percentage;
	}
	
	/**
	 * 
	 * @param currentDate - can not be null
	 * @return if this discount expired or not applicable return true, if this discount applicable return false
	 */
	public boolean isExpired(Date currentDate) {
		if(startDate == null || daysPeriod < 0){
			// always applicable
			return false;
		}
		int days = (int) (currentDate.getTime()/(24 * 60 * 60 * 1000) - startDate.getTime()/(24 * 60 * 60 * 1000));
		if(days < 0 || days > daysPeriod){
			return true;
		}
		return false;
	}
	@Override
    public String toString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(DiscountManager.DATE_FORMAT);
		String date = "";
		if(getStartDate() == null){
			date = DiscountManager.ALWAYS_APPLICABLE_FLAG;
		}else{
			date = dateFormat.format(getStartDate());
		}
		String period = "";
		if(getdaysPeriod() == -1){
			period = DiscountManager.ALWAYS_APPLICABLE_FLAG;
		}else{
			period = String.valueOf(getdaysPeriod());
		}
    	return(getCode() + ","+ getDescription() + "," + date + ","
		+ period + "," + getPercentage() + "," + DiscountManager.APPLICABLE_TO_ALL_FLAG);
    }
}
