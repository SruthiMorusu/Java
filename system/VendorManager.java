package iss.nus.edu.sg.SouvinierStore.system;

/**
 * Name					Date
 * ---------------------------------
 * Xu Lei 				18.03.2014
 * Xu Lei				31.03.2014
 * 
 * @author Xu Lei
 * Since 18.03.2014
 * 
 * Modified By Xu Lei*/

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class VendorManager {
	
	// just follow the diagram, change List to ArrayList and Map to HashMap. 17-Mar-2014, Xu Lei.
	private HashMap<Category, ArrayList<Vendor>> vendors;
	
	public VendorManager() {
		vendors = new HashMap<Category, ArrayList<Vendor>>();
	}

	// just for JUnit test case. 18-Mar-2014, Xu Lei.
	public void setVendors(HashMap<Category, ArrayList<Vendor>> vendors) {
		this.vendors = vendors;
	}

	// return vendor list by specified  category. 17-Mar-2014, Xu Lei.
	public ArrayList<Vendor> getVendors(Category category) {
		if(category == null || vendors == null) {
			return null;
		}
		return vendors.get(category);
	}

	// return all the vendor list. 17-Mar-2014, Xu Lei.
	public ArrayList<Vendor> getVendors() {
		if(vendors == null) {
			return null;
		}
		ArrayList<Vendor> listVendors = new ArrayList<Vendor>();
		Collection<ArrayList<Vendor>> vendorCollection = vendors.values();
		for(ArrayList<Vendor> lv : vendorCollection){
			listVendors.addAll(lv);
		}
		return listVendors;
	}
	
	// for function expansion in the future if need add vendor. 31-Mar-2014, Xu Lei.
	public boolean addVendor(Category category, Vendor vendor) {
		if(category == null || vendors.get(category) == null || vendor == null) {
			return false;
		}
		boolean ifSuccess = vendors.get(category).add(vendor);
		if(ifSuccess) {
			saveVendorsToFile(category);
		}
		return ifSuccess;
	}
	
	// delete vendor by category. 31-Mar-2014, Xu Lei.
	public boolean deleteVendor(Category category, Vendor vendor) {
		if(category == null || vendors.get(category) == null || vendor == null) {
			return false;
		}
		boolean ifSuccess = vendors.get(category).remove(vendor);
		if(ifSuccess) {
			saveVendorsToFile(category);
		}
		return ifSuccess;
	}
	
	// delete vendor regardless of category. 17-Mar-2014, Xu Lei.
	// for function expansion in the future if need delete the vendor regardless of category. 17-Mar-2014, Xu Lei.
	/*public void deleteVendor(Vendor vendor) {
		Set<Category> sc = vendors.keySet();
		for(Category c : sc) {
			deleteVendor(c, vendor);
		}
	}*/
	
	// when new a category and related vendor list must be put into the map and create related file. 18-Mar-2014, Xu Lei.
	public boolean generateRelatedVendorsData(Category category) {
		if(category == null) {
			return false;
		}
		for(Category c : vendors.keySet()) {
			if(c.getCategoryCode().equals(category.getCategoryCode())) {
				return false;
			}
		}
		addVendorListForNewCategory(category);
		createVendorsFileForNewCategory(category);
		return true;
	}
	
	// first, put the related vendor list into the map. 18-Mar-2014, Xu Lei.
	private void addVendorListForNewCategory(Category category) {
		vendors.put(category, new ArrayList<Vendor>());
	}
	
	// second, create related file. 18-Mar-2014, Xu Lei.
	private void createVendorsFileForNewCategory(Category category) {
		saveVendorsToFile(category);
	}

	// save for the file of category. 18-Mar-2014, Xu Lei.
	public boolean saveVendorsToFile(Category category) {
		if(category == null) {
			return false;
		}
		File file = new File("data" + File.separator + "Vendors" + category.getCategoryCode() + ".dat");
		System.out.println("Log: VendorManager file absolute path: " + file.getAbsolutePath());
		StringBuilder sBuilder = new StringBuilder("");
		if(vendors == null) {
			return false;
		}
		if(vendors.get(category) != null) {
			for(Vendor v : vendors.get(category)) {
				sBuilder.append(v.getName() + "," + v.getDescription() + "\n");
			}
		}
		return FileUtility.writeFile(file, sBuilder.toString(), false);
	}
	
	// save for all files. 18-Mar-2014, Xu Lei.
	public boolean saveVendorsToFile() {
		if(vendors == null) {
			return false;
		}
		Set<Category> cs = vendors.keySet();
		boolean ifAllSuccess = true;
		for(Category c : cs) {
			ifAllSuccess = ifAllSuccess && saveVendorsToFile(c);
		}
		return ifAllSuccess;
	}

	// after load category, then load vendors. 18-Mar-2014, Xu Lei.
	public boolean readVendorsFromFile() {
		ArrayList<Category> categories = ManagerFactory.getCategoryManager().getCategories();
		if(categories == null) {
			return false;
		}
		for(Category c : categories) {
			File file = null;
			ArrayList<String> vendorsStrForCategory;
			try {
				file = new File("data" + File.separator + "Vendors" + c.getCategoryCode() + ".dat");
				vendorsStrForCategory = FileUtility.readFile(file);
			} catch (Exception e) {
				System.out.println("LogErr: VendorManager read File (" + file.getAbsolutePath() + ") error.");
				return false;
			}
			ArrayList<Vendor> vendorsForCategory = new ArrayList<Vendor>();
			if(vendorsStrForCategory != null){
				for(String s : vendorsStrForCategory) {	
					if(s != null && !"".equals(s) && s.contains(",")) {
						String[] nameAndDescr = s.split(",");
						vendorsForCategory.add(new Vendor(nameAndDescr[0], nameAndDescr[1]));
					}
				}
			}
			vendors.put(c, vendorsForCategory);
		}
		return true;
	}
	
}
