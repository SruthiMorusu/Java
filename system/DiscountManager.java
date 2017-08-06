package iss.nus.edu.sg.SouvinierStore.system;
/**
 * Name					Date
 * ---------------------------------
 * Sruthi 				18.03.2014
 * 
 * Author Sruthi
 * Since 18.03.2014
 * 
 **/

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DiscountManager implements IReport
{
	public static final String DISCOUNT_DATA_FILE = "data/Discount.dat";
	public static final char APPLICABLE_TO_MEMBER_FLAG = 'M';
	public static final char APPLICABLE_TO_ALL_FLAG = 'A';
	public static final String ALWAYS_APPLICABLE_FLAG = "ALWAYS";
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	private ArrayList<MemberDiscount> memberDiscounts;
	private ArrayList<NonMemberDiscount> allCustomerDiscounts;
	private SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
	private String dataFile = DISCOUNT_DATA_FILE;
	
	public DiscountManager() {
		memberDiscounts = new ArrayList<MemberDiscount>();
		allCustomerDiscounts = new ArrayList<NonMemberDiscount>();
	}
	public boolean setDataFile(String fileNameWithPath){
		if(fileNameWithPath == null || fileNameWithPath.isEmpty()){
			return false;
		}
		dataFile = fileNameWithPath;
		return true;
	}
	
	public boolean addDiscount(String code, String descrption, Date startDate, int daysPeriod, int percentage, char applicable) {
		if(code == null || code.isEmpty()
				|| descrption == null || descrption.isEmpty()
				|| percentage < 0 || percentage > 100){
			// invalidate param
			System.out.println("error - DiscountManager addDiscount() invalidate param");
			return false;
		}
		switch(applicable){
		case APPLICABLE_TO_MEMBER_FLAG:
			for(MemberDiscount memberDiscount : memberDiscounts){
				if(memberDiscount.getCode().trim().equalsIgnoreCase(code.trim())){
					System.out.println("error - DiscountManager addDiscount() member discount already exist");
					return false;
				}
			}
			MemberDiscount discount = new MemberDiscount(code, descrption, startDate, daysPeriod, percentage);
			memberDiscounts.add(discount);
			break;
		case APPLICABLE_TO_ALL_FLAG:
			for(NonMemberDiscount nonmemberDiscount : allCustomerDiscounts){
				if(nonmemberDiscount.getCode().trim().equalsIgnoreCase(code.trim())){
					System.out.println("error - DiscountManager addDiscount() nonmember discount already exist");
					return false;
				}
			}
			NonMemberDiscount discountForAll = new NonMemberDiscount(code, descrption, startDate, daysPeriod, percentage);
			allCustomerDiscounts.add(discountForAll);
			break;
			default:
				System.out.println("error - DiscountManager addDiscount() invalidate param applicable");
				return false;
		}
		return saveDiscountsToFile();

	}
	/**
	 * 
	 * @param discount
	 * @return if there is no such discount in list, also return true.
	 */
	public boolean deleteDiscount(MemberDiscount discount) {
		if(discount == null){
			System.out.println("error - DiscountManager deleteDiscount() invalidate param");
			return false;
		}
		
		if(memberDiscounts.remove(discount)){
			// deleted
			return saveDiscountsToFile();
		}else{
			System.out.println("warning - DiscountManager deleteDiscount() no such discount in list");
			return false;
		}
	}
	
	public boolean deleteDiscount(NonMemberDiscount discount) {
		if(discount == null){
			System.out.println("error - DiscountManager deleteDiscount() invalidate param");
			return false;
		}
		if(allCustomerDiscounts.remove(discount)){
			// deleted
			return saveDiscountsToFile();
		}else{
			System.out.println("warning - DiscountManager deleteDiscount() no such discount in list");
			return false;
		}
	}
	
	public ArrayList<MemberDiscount> getMemberDiscounts() {
		return memberDiscounts;
	}
	
	public ArrayList<NonMemberDiscount> getAllCustomerDiscounts() {
		return allCustomerDiscounts;
	}
	/**
	 * 
	 * @param code
	 * @return if not found, return null
	 */
	public MemberDiscount getMemberDiscount(String code) {
		if(code == null || code.isEmpty()){
			System.out.println("error - DiscountManager getMemberDiscount() invalidate param");
			return null;
		}
		for(MemberDiscount memberDiscount : memberDiscounts){
			if(memberDiscount.getCode().trim().equalsIgnoreCase(code.trim())){
				return memberDiscount;
			}
		}
		return null;
	}
	
	public NonMemberDiscount getAllCustomerDiscount(String code) {
		if(code == null || code.isEmpty()){
			System.out.println("error - DiscountManager getAllCustomerDiscount() invalidate param");
			return null;
		}
		for(NonMemberDiscount nonmemberDiscount : allCustomerDiscounts){
			if(nonmemberDiscount.getCode().trim().equalsIgnoreCase(code.trim())){
				return nonmemberDiscount;
			}
		}
		return null;
	}
	/**
	 * 
	 * @param customer
	 * @param currentDate
	 * @return if there is no discount available, return 0, else return the best discount.
	 * for example: 10% discount will return 0.10
	 */
	public double getBestDiscountIfAvailable(Customer customer, Date currentDate) {
		double bestPercentageForCustom = 0;
		if(customer == null || currentDate == null){
			System.out.println("error - DiscountManager getBestDiscountIfAvailable() invalidate param");
			return bestPercentageForCustom / 100;
		}
		for(NonMemberDiscount nonmemberDiscount : allCustomerDiscounts){
			if(!nonmemberDiscount.isExpired(currentDate)){
				if(bestPercentageForCustom < (nonmemberDiscount.getPercentage())){
					bestPercentageForCustom = (nonmemberDiscount.getPercentage());
				}
			}
		}
		if(customer instanceof Member){
			for(MemberDiscount memberDiscount : memberDiscounts){
				if(!memberDiscount.isExpired(currentDate)){
					if(bestPercentageForCustom < (memberDiscount.getPercentage())){
						bestPercentageForCustom = (memberDiscount.getPercentage());
					}
				}
			}
		}
		return bestPercentageForCustom / 100;
	}
	
	public boolean saveDiscountsToFile() {
		File fileToWrite = new File(dataFile);
		String discountData = "";
		for(NonMemberDiscount nonmemberDiscount : allCustomerDiscounts){
			discountData += nonmemberDiscount.toString() + "\n";
		}
		for(MemberDiscount memberDiscount : memberDiscounts){
			discountData += memberDiscount.toString() + "\n";
		}
		return (FileUtility.writeFile(fileToWrite, discountData, false));
	}
	
	public boolean readDiscountsFromFile() {
		File fileToRead = new File(dataFile);
		if(!fileToRead.isFile()){
			System.out.println("error - DiscountManager readDiscountsFromFile() file not found");
			return false;
		}
		// clean Manager data before read from file
		memberDiscounts.clear();
		allCustomerDiscounts.clear();
		ArrayList<String> fileContents = FileUtility.readFile(fileToRead);
		if(!fileContents.isEmpty()){
			for(String tempString : fileContents){
				String[] attributeList = tempString.split(",");
				formDiscount(attributeList);				
			}
		}
		return true;
	}
	private void formDiscount(String[] attributesString) {
		if(attributesString == null || attributesString.length < 6){
			System.out.println("error - DiscountManager formDiscount() invalidate param");
			return;
		}
		char applicable = attributesString[5].trim().charAt(0);
		Date startDate = null;
		if(!ALWAYS_APPLICABLE_FLAG.equals(attributesString[2].trim())){
			try {
				startDate = dateFormat.parse(attributesString[2].trim());
			} catch (ParseException e) {
				System.out.println("error - DiscountManager formDiscount() format Date faild");
				return;
			}
		}
		int daysPeriod = -1;
		if(!ALWAYS_APPLICABLE_FLAG.equals(attributesString[3].trim())){
			try {
				daysPeriod = Integer.parseInt(attributesString[3].trim());
			} catch (Exception e) {
				System.out.println("error - DiscountManager formDiscount() format daysPeriod faild");
				return;
			}
		}
		int percentage = 0;
		try {
			percentage = Integer.parseInt(attributesString[4].trim());
		} catch (Exception e) {
			System.out.println("error - DiscountManager formDiscount() format percentage faild");
			return;
		}
		switch(applicable){
		case APPLICABLE_TO_MEMBER_FLAG:
			MemberDiscount discount = new MemberDiscount(attributesString[0].trim(), attributesString[1].trim(), startDate, daysPeriod, percentage);
			memberDiscounts.add(discount);
			break;
		case APPLICABLE_TO_ALL_FLAG:
			NonMemberDiscount discountForAll = new NonMemberDiscount(attributesString[0].trim(), attributesString[1].trim(), startDate, daysPeriod, percentage);
			allCustomerDiscounts.add(discountForAll);
			break;
			default:
				System.out.println("error - DiscountManager formDiscount() invalidate param applicable");
				return;
		
		}
	}

	@Override
	public ArrayList<String> report() {
		ArrayList<String> strList = new ArrayList<String>();
		for(MemberDiscount memberDiscount : memberDiscounts){
			strList.add(memberDiscount.toString());
		}
		for(NonMemberDiscount allCustomerDiscount : allCustomerDiscounts){
			strList.add(allCustomerDiscount.toString());
		}
		return strList;
	}
}

