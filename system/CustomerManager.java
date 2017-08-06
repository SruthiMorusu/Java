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

import java.io.*;
import java.util.*;

public class CustomerManager implements IReport {
	
	public static final String MEMBER_DATA_FILE = "data/Members.dat";
	public static final String NON_MEMBER_ID = "PUBLIC";
	private ArrayList<Member> members;
	private String dataFile = MEMBER_DATA_FILE;
	
	
	public CustomerManager(){
		members = new ArrayList<Member>();
	}
	public boolean setDataFile(String fileNameWithPath){
		if(fileNameWithPath == null || fileNameWithPath.isEmpty()){
			return false;
		}
		dataFile = fileNameWithPath;
		return true;
	}
	
	
	/**
	 * Method to add members
	 * @param name
	 * @param memberID
	 * @param points
	 */
	
	public boolean addMember(String name, String memberID, int points){
		if(name != null && memberID != null){
		Member member = new Member(name, memberID, points);
		members.add(member);
		return saveMembersToFile();
		}
		else 
			return false;
		}
	
	
	/**
	 * Method to Delete member
	 *@param Member
	 */
	
	
	public boolean deleteMember(String id){
		if(id == null){
			return false;
		}
		int memberToDelete = 0;
		for(int i=0;i<members.size();i++){
			if(id.trim().equalsIgnoreCase(members.get(i).getId())){
				memberToDelete =i;
			}
		}
		if(members.size()<=10){
			return false;
		}
		members.remove(memberToDelete);
		return saveMembersToFile();		
	}	
	
	
		/**
	 * @return the list of Members
	 */
	
	public ArrayList<Member> getMembers(){
		return members;
	}
	 /**
     * Function to read the Bar Code of member card from the barcode reader, then return the Member.
     * return null - member not found
     */
    public Member readMemberFromBarCode(IBarCodeReader codeReader)
    {
    	if(codeReader == null){
    		return null;
    	}
    	String barCode = codeReader.readBarCode();
    	if(barCode == null || barCode.isEmpty()){
    		return null;
    	}
    	barCode = barCode.trim();
    	for(Member item: members){
    		if(barCode.equalsIgnoreCase(item.getId())){
    			return item;
    		}
    	}
    	return null;
    }
	public Customer getCustomer(String id){
		if(id == null){
		return null;
		}
		if(id.equalsIgnoreCase(NON_MEMBER_ID)){
			return new NonMember();
		}
		else{
		Member tempMember = null;
		for(int i=0;i<members.size();i++){
			if(id.trim().equalsIgnoreCase(members.get(i).getId())){
				tempMember = members.get(i);
			}
			}
		return tempMember;
		}
	}
	
	public void formMemberList(String[] attributeList){
		String name = attributeList[0].trim();
		String id = attributeList[1].trim();
		int point = Integer.parseInt(attributeList[2].trim());
		
		Member m = new Member(name, id, point);
		
		
		members.add(m);
	}
	
	/**
	 * Read all members from the file
	 */
	
	public boolean readMembersFromFile(){
		

		File fileToRead = new File(dataFile);
		if(!fileToRead.isFile()){
			System.out.println("error - MemberManager readMembersFromFile() file not found");
			return false;
		}
		// clean Manager data before read from file
		members.clear();
		ArrayList<String> fileContents = FileUtility.readFile(fileToRead);

		if(!fileContents.isEmpty()){
			for(String tempString: fileContents){
				String[] attributeList = tempString.split(",");
				formMemberList(attributeList);
			}
		}
		return true;
	}

	/**
	 * Write all members into the file
	 */
	
	
	 public boolean saveMembersToFile() {
		 File fileToWrite = new File(dataFile);
			String memberData = "";
			for(Member memberItem : members){
				memberData = memberData + memberItem.toString() + "\n";
			}
			return (FileUtility.writeFile(fileToWrite, memberData, false));
		}
	 
	 public ArrayList<String> report(){
		 ArrayList<String> reportString = new ArrayList<String>();
			for(Member member : members){
				reportString.add(member.toString());
			}
			return reportString;

		}


}
