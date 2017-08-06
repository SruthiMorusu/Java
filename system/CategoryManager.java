package iss.nus.edu.sg.SouvinierStore.system;

import java.io.File;
import java.util.*;

/** 
 * Name          	          Date 
 * -------------------------------------------------- 
 * Sruthi               19.03.2014 
 *  
 * Author : Sruthi
 * Since 18.03.2014 
 *  */

public class CategoryManager implements IReport {
	public static final String CATEGORY_DATA_FILE = "data/Category.dat";
	private String dataFile = CATEGORY_DATA_FILE;
	private ArrayList<Category> categories;
	
	public CategoryManager(){
		categories = new ArrayList<Category>();
	}
	
	public boolean setDataFile(String fileNameWithPath){
		if(fileNameWithPath == null || fileNameWithPath.isEmpty()){
			return false;
		}
		dataFile = fileNameWithPath;
		return true;
	}
	public boolean addCategory(String code, String name) {
		if(code == null || code.isEmpty() || name == null || name.isEmpty()){
			System.out.println("error - CategoryManager addCategory() invalidate param");
			return false;
		}
		if(checkIfCategoryExists(code)){
			//exist
			System.out.println("error - CategoryManager addCategory() already exist");
			return false;
		}
		Category category = new Category(code, name);
		if(categories.add(category)){
			return ManagerFactory.getVendorManager().generateRelatedVendorsData(category) && saveCategoriesToFile();
		}else{
			return false;
		}
	}

	
	public boolean deleteCategory(Category category) {
		if(category == null){
			System.out.println("error - CategoryManager deleteCategory() invalidate param");
			return false;
		}
		if(categories.remove(category)){
			// deleted
			return saveCategoriesToFile();
		}else{
			System.out.println("warning - CategoryManager deleteCategory() no such category in list");
			return false;
		}
	}
	
	public ArrayList<Category> getCategories() {
		return categories;
	}
	
	public Boolean checkIfCategoryExists(String code) {
		if(code == null || code.isEmpty()){
			System.out.println("error - CategoryManager checkIfCategoryExists() invalidate param");
			return false;
		}
		for(Category item : categories){
			if(code.equalsIgnoreCase(item.getCategoryCode())){
				return true;
			}
		}
		return false;
	}
	/**
	 * 
	 * @param code
	 * @return return null if not found
	 */
	public Category getCategories(String code) {
		if(code == null || code.isEmpty()){
			System.out.println("error - CategoryManager getCategories() invalidate param");
			return null;
		}
		for(Category item : categories){
			if(code.equalsIgnoreCase(item.getCategoryCode())){
				return item;
			}
		}
		return null;
	}
	
	public boolean saveCategoriesToFile() {
		File fileToWrite = new File(dataFile);
		String categoryData = "";
		for(Category category : categories){
			categoryData += category.toString() + "\n";
		}
		return (FileUtility.writeFile(fileToWrite, categoryData, false));
	}
	public boolean readCategoriesFromFile() {
		File fileToRead = new File(dataFile);
		if(!fileToRead.isFile()){
			System.out.println("error - CategoryManager readCategoriesFromFile() file not found");
			return false;
		}
		// clean Manager data before read from file
		categories.clear();
		ArrayList<String> fileContents = FileUtility.readFile(fileToRead);
		if(!fileContents.isEmpty()){
			for(String tempString : fileContents){
				String[] attributeList = tempString.split(",");
				formCategory(attributeList);				
			}
		}
		return true;
	}
	
	private void formCategory(String[] attributeList) {
		if(attributeList == null || attributeList.length < 2){
			System.out.println("error - CategoryManager formCategory() invalidate param");
			return;
		}
		Category category = new Category(attributeList[0].trim(), attributeList[1].trim());	
		categories.add(category);
	}
	public ArrayList<String> report(){
		ArrayList<String> strList = new ArrayList<String>();
		for(Category category : categories){
			strList.add(category.toString());
		}
		return strList;
	};
	
}
