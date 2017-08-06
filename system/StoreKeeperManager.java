package iss.nus.edu.sg.SouvinierStore.system;

/** 
 * Name          	          Date 
 * -------------------------------------------------- 
 * Sruthi               19.03.2014 
 *  
 * Author : Sruthi
 * Since 18.03.2014 
 *  */

import java.io.File;
import java.util.*;

public class StoreKeeperManager {
	public static final String STOREKEEPER_DATA_FILE = "data/StoreKeepers.dat";
	private String dataFile = STOREKEEPER_DATA_FILE;
	private ArrayList<StoreKeeper> storekeepers;
	public StoreKeeper currentStoreKeeper;
	
	public StoreKeeperManager(){
		storekeepers = new ArrayList<StoreKeeper>();
		currentStoreKeeper = null;
	}
	public boolean addStoreKeeper(String name, String password) {
		if(name == null || name.isEmpty() || password == null || password.isEmpty()){
			return false;
		}
		if(getStoreKeeper(name) != null){
			// already exist
			return false;
		}
		StoreKeeper sk = new StoreKeeper(name, password);
		storekeepers.add(sk);
		saveStoreKeepersToFile();
		return true;
	}
	
	public boolean deleteStoreKeeper(StoreKeeper storeKeeper)
	{
		if(storeKeeper == null){
			return false;
		}
		if(storekeepers.remove(storeKeeper)){
			saveStoreKeepersToFile();
			return true;	
		}else{
			return false;
		}
	}
	
	/**
	 * 
	 * @param name
	 * @return return null if not found
	 */
	public StoreKeeper getStoreKeeper(String name){
		if(name == null || name.isEmpty()){
			return null;
		}
		for(StoreKeeper item : storekeepers){
			if(item.getName().equals(name)){
				return item;
			}
		}
		return null;
	}
	
	public boolean login (String name, String password)
	{
		if(name == null || name.isEmpty() || password == null || password.isEmpty()){
			return false;
		}
		StoreKeeper storeKeeper = getStoreKeeper(name);
		if(storeKeeper != null && storeKeeper.getPassword().equals(password)){
			currentStoreKeeper = storeKeeper;
			return true;
		}
		return false;
	}
	
	public StoreKeeper getCurrentStoreKeeper()
	{
		return currentStoreKeeper;
	}
	
	public boolean saveStoreKeepersToFile() 
	{
		File fileToWrite = new File(dataFile);
		String storkeeperData = "";
		for(StoreKeeper item : storekeepers){
			storkeeperData += item.toString() + "\n";
		}
		return (FileUtility.writeFile(fileToWrite, storkeeperData, false));
	}
	
	
	
	public boolean readStoreKeepersFromFile()
	{
		File fileToRead = new File(dataFile);
		if(!fileToRead.isFile()){
			System.out.println("error - StoreKeeperManager readStoreKeepersFromFile() file not found");
			return false;
		}
		// clean Manager data before read from file
		storekeepers.clear();
		ArrayList<String> fileContents = FileUtility.readFile(fileToRead);
		if(!fileContents.isEmpty()){
			for(String tempString : fileContents){
				String[] attributeList = tempString.split(",");
				formStoreKeeper(attributeList);				
			}
		}
		return true;
	}	
	private void formStoreKeeper(String[] attributeList) {
		if(attributeList == null || attributeList.length < 2){
			System.out.println("error - StoreKeeperManager formStoreKeeper() invalidate param");
			return;
		}
		StoreKeeper sk = new StoreKeeper(attributeList[0].trim(), attributeList[1].trim());	
		storekeepers.add(sk);
	}
    public boolean setDataFile(String fileNameWithPath)
    {
    	if(fileNameWithPath == null || fileNameWithPath.isEmpty()){
			return false;
		}
		dataFile = fileNameWithPath;
		return true;
    }
}
