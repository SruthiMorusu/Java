package iss.nus.edu.sg.SouvinierStore.test;

/**
 * Name					Date
 * ---------------------------------
 * Sruthi 				18.03.2014
 * Sruthi				01.04.2014
 * 
 * @author Sruthi
 * Since 18.03.2014
 * 
 * Modified By Sruthi*/

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import iss.nus.edu.sg.SouvinierStore.system.Category;
import iss.nus.edu.sg.SouvinierStore.system.Vendor;
import iss.nus.edu.sg.SouvinierStore.system.VendorManager;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class VendorManagerTest {
	
	HashMap<Category, ArrayList<Vendor>> vendors;
	Category MUG;
	ArrayList<Vendor> vendorsMUG;
	Category CLO;
	ArrayList<Vendor> vendorsCLO;
	Category DES;
	ArrayList<Vendor> vendorsDES;
	VendorManager vm;

	@Before
	public void startup() {
		vendors = new HashMap<Category, ArrayList<Vendor>>();
		MUG = new Category("MUG", "mugs");
		vendorsMUG = new ArrayList<Vendor>(); 
		CLO = new Category("CLO", "clothes");
		vendorsCLO = new ArrayList<Vendor>();
		DES = new Category("DES", "desks");
		vendorsDES = new ArrayList<Vendor>();
		vm = new VendorManager();
	}
	
	@After
	public void end() {
		vendors = null;
		MUG = null;
		vendorsMUG = null; 
		CLO = null;
		vendorsCLO = null;
		DES = null;
		vendorsDES = null;
		vm = null;
	}
	
	@Test
	public void getVendorTest() {
		vendorsMUG.add(new Vendor("Nancy's Gifts", "Best of the best gifts from Nancy's"));
		vendorsMUG.add(new Vendor("Office Sovenirs", "One and only Office Sovenirs"));
		vendorsMUG.add(new Vendor("Pen's and such", "Sovenirs and gifts for all occasions"));
		vendorsMUG.add(new Vendor("ArtWorks Stationary Store", "All kinds of Stationary and Gifts"));
		Vendor vendor = new Vendor("Lock Lock", "Very good quality");
		vendorsMUG.add(vendor);
		vendors.put(MUG, vendorsMUG);
		vm.setVendors(vendors);
		assertNotNull(vm.getVendors());
	}
	
	@Test
	public void getVendorTest_null() {
		vm.setVendors(null);
		assertNull(vm.getVendors());
		/*ArrayList<Category> listCategory = ManagerFactory.getCategoryManager().getCategories();
		for(Category c : listCategory) {
			ArrayList<Vendor> listVendorByCategory = ManagerFactory.getVendorManager().getVendors(c);
			System.out.println(c.getName());
			for(Vendor v : listVendorByCategory) {
				System.out.println(v.getName() + ", " + v.getDescription());
			}
			System.out.println();
		}*/
	}
	
	@Test
	public void getVendorsByCategoryTest() {
		vendorsMUG.add(new Vendor("Nancy's Gifts", "Best of the best gifts from Nancy's"));
		vendorsMUG.add(new Vendor("Office Sovenirs", "One and only Office Sovenirs"));
		vendorsMUG.add(new Vendor("Pen's and such", "Sovenirs and gifts for all occasions"));
		vendorsMUG.add(new Vendor("ArtWorks Stationary Store", "All kinds of Stationary and Gifts"));
		Vendor vendor = new Vendor("Lock Lock", "Very good quality");
		vendorsMUG.add(vendor);
		vendors.put(MUG, vendorsMUG);
		vm.setVendors(vendors);
		assertNotNull(vm.getVendors(MUG));
	}
	
	@Test
	public void getVendorsByCategoryTest_illegal() {
		vendorsMUG.add(new Vendor("Nancy's Gifts", "Best of the best gifts from Nancy's"));
		vendorsMUG.add(new Vendor("Office Sovenirs", "One and only Office Sovenirs"));
		vendorsMUG.add(new Vendor("Pen's and such", "Sovenirs and gifts for all occasions"));
		vendorsMUG.add(new Vendor("ArtWorks Stationary Store", "All kinds of Stationary and Gifts"));
		Vendor vendor = new Vendor("Lock Lock", "Very good quality");
		vendorsMUG.add(vendor);
		vendors.put(MUG, vendorsMUG);
		vm.setVendors(vendors);
		assertNull(vm.getVendors(DES));
	}
	
	@Test
	public void getVendorsByCategoryTest_null() {
		vendorsMUG.add(new Vendor("Nancy's Gifts", "Best of the best gifts from Nancy's"));
		vendorsMUG.add(new Vendor("Office Sovenirs", "One and only Office Sovenirs"));
		vendorsMUG.add(new Vendor("Pen's and such", "Sovenirs and gifts for all occasions"));
		vendorsMUG.add(new Vendor("ArtWorks Stationary Store", "All kinds of Stationary and Gifts"));
		Vendor vendor = new Vendor("Lock Lock", "Very good quality");
		vendorsMUG.add(vendor);
		vendors.put(MUG, vendorsMUG);
		vm.setVendors(vendors);
		assertNull(vm.getVendors(null));
	}
	
	@Test
	public void deleteVendorTest() {
		vendorsMUG.add(new Vendor("Nancy's Gifts", "Best of the best gifts from Nancy's"));
		vendorsMUG.add(new Vendor("Office Sovenirs", "One and only Office Sovenirs"));
		vendorsMUG.add(new Vendor("Pen's and such", "Sovenirs and gifts for all occasions"));
		vendorsMUG.add(new Vendor("ArtWorks Stationary Store", "All kinds of Stationary and Gifts"));
		Vendor vendor = new Vendor("Lock Lock", "Very good quality");
		vendorsMUG.add(vendor);
		vendors.put(MUG, vendorsMUG);
		vm.setVendors(vendors);
		assertTrue(vm.deleteVendor(MUG, vendor));
	}
	
	@Test
	public void deleteVendorTest_illegal() {
		vendorsMUG.add(new Vendor("Nancy's Gifts", "Best of the best gifts from Nancy's"));
		vendorsMUG.add(new Vendor("Office Sovenirs", "One and only Office Sovenirs"));
		vendorsMUG.add(new Vendor("Pen's and such", "Sovenirs and gifts for all occasions"));
		vendorsMUG.add(new Vendor("ArtWorks Stationary Store", "All kinds of Stationary and Gifts"));
		vendorsMUG.add(new Vendor("Lock Lock", "Very good quality"));
		vendors.put(MUG, vendorsMUG);
		vm.setVendors(vendors);
		assertFalse(vm.deleteVendor(MUG, new Vendor("Lock Lock", "Very good quality")));
	}
	
	@Test
	public void deleteVendorTest_null() {
		vendorsMUG.add(new Vendor("Nancy's Gifts", "Best of the best gifts from Nancy's"));
		vendorsMUG.add(new Vendor("Office Sovenirs", "One and only Office Sovenirs"));
		vendorsMUG.add(new Vendor("Pen's and such", "Sovenirs and gifts for all occasions"));
		vendorsMUG.add(new Vendor("ArtWorks Stationary Store", "All kinds of Stationary and Gifts"));
		vendorsMUG.add(new Vendor("Lock Lock", "Very good quality"));
		vendors.put(MUG, vendorsMUG);
		vm.setVendors(vendors);
		assertFalse(vm.deleteVendor(MUG, null));
	}
	
	@Test
	public void addVendorTest() {
		vendorsMUG.add(new Vendor("Nancy's Gifts", "Best of the best gifts from Nancy's"));
		vendorsMUG.add(new Vendor("Office Sovenirs", "One and only Office Sovenirs"));
		vendorsMUG.add(new Vendor("Pen's and such", "Sovenirs and gifts for all occasions"));
		vendorsMUG.add(new Vendor("ArtWorks Stationary Store", "All kinds of Stationary and Gifts"));
		vendorsMUG.add(new Vendor("Lock Lock", "Very good quality"));
		vendors.put(MUG, vendorsMUG);
		vm.setVendors(vendors);
		assertTrue(vm.addVendor(MUG, new Vendor("Starbucks", "Speicial")));
	}
	
	@Test
	public void addVendorTest_illegal() {
		vendorsMUG.add(new Vendor("Nancy's Gifts", "Best of the best gifts from Nancy's"));
		vendorsMUG.add(new Vendor("Office Sovenirs", "One and only Office Sovenirs"));
		vendorsMUG.add(new Vendor("Pen's and such", "Sovenirs and gifts for all occasions"));
		vendorsMUG.add(new Vendor("ArtWorks Stationary Store", "All kinds of Stationary and Gifts"));
		vendorsMUG.add(new Vendor("Lock Lock", "Very good quality"));
		vendors.put(MUG, vendorsMUG);
		vm.setVendors(vendors);
		assertFalse(vm.addVendor(DES, new Vendor("Starbucks", "Speicial")));
	}
	
	@Test
	public void addVendorTest_null() {
		vendorsMUG.add(new Vendor("Nancy's Gifts", "Best of the best gifts from Nancy's"));
		vendorsMUG.add(new Vendor("Office Sovenirs", "One and only Office Sovenirs"));
		vendorsMUG.add(new Vendor("Pen's and such", "Sovenirs and gifts for all occasions"));
		vendorsMUG.add(new Vendor("ArtWorks Stationary Store", "All kinds of Stationary and Gifts"));
		vendorsMUG.add(new Vendor("Lock Lock", "Very good quality"));
		vendors.put(MUG, vendorsMUG);
		vm.setVendors(vendors);
		assertFalse(vm.addVendor(MUG, null));
	}
	
	@Test
	public void generateRelatedVendorsDataTest() {
		assertTrue(vm.generateRelatedVendorsData(DES));
	}
	
	@Test
	public void generateRelatedVendorsDataTest_illegal() {
		vendorsMUG.add(new Vendor("Nancy's Gifts", "Best of the best gifts from Nancy's"));
		vendorsMUG.add(new Vendor("Office Sovenirs", "One and only Office Sovenirs"));
		vendorsMUG.add(new Vendor("Pen's and such", "Sovenirs and gifts for all occasions"));
		vendorsMUG.add(new Vendor("ArtWorks Stationary Store", "All kinds of Stationary and Gifts"));
		vendorsMUG.add(new Vendor("Lock Lock", "Very good quality"));
		vendors.put(MUG, vendorsMUG);
		vm.setVendors(vendors);
		assertFalse(vm.generateRelatedVendorsData(MUG));
	}
	
	@Test
	public void generateRelatedVendorsDataTest_null() {
		assertFalse(vm.generateRelatedVendorsData(null));
	}
	
	@Test
	public void saveVendorsToFileTest() {
		vendorsMUG.add(new Vendor("Nancy's Gifts", "Best of the best gifts from Nancy's"));
		vendorsMUG.add(new Vendor("Office Sovenirs", "One and only Office Sovenirs"));
		vendorsMUG.add(new Vendor("Pen's and such", "Sovenirs and gifts for all occasions"));
		vendorsMUG.add(new Vendor("ArtWorks Stationary Store", "All kinds of Stationary and Gifts"));
		vendorsMUG.add(new Vendor("Lock Lock", "Very good quality"));
		vendors.put(MUG, vendorsMUG);
		vendorsCLO.add(new Vendor("NUS Store", "Best NUS T-shirt"));
		vendorsCLO.add(new Vendor("Sports", "All the sports T-shirt"));
		vendors.put(CLO, vendorsCLO);
		vm.setVendors(vendors);
		assertTrue(vm.saveVendorsToFile());
	}
	
	@Test
	public void saveVendorsToFileTest_empty() {
		vm.setVendors(vendors);
		assertTrue(vm.saveVendorsToFile());
	}
	
	@Test
	public void saveVendorsToFileTest_null() {
		vm.setVendors(null);
		assertFalse(vm.saveVendorsToFile());
	}
	
	@Test
	public void saveVendorsToFileByCategoryTest() {
		vendorsMUG.add(new Vendor("Nancy's Gifts", "Best of the best gifts from Nancy's"));
		vendorsMUG.add(new Vendor("Office Sovenirs", "One and only Office Sovenirs"));
		vendorsMUG.add(new Vendor("Pen's and such", "Sovenirs and gifts for all occasions"));
		vendorsMUG.add(new Vendor("ArtWorks Stationary Store", "All kinds of Stationary and Gifts"));
		vendors.put(MUG, vendorsMUG);
		vm.setVendors(vendors);
		assertTrue(vm.saveVendorsToFile(MUG));
	}
	
	@Test
	public void saveVendorsToFileByCategoryTest_notExist() {
		assertTrue(vm.saveVendorsToFile(MUG));
	}
	
	@Test
	public void saveVendorsToFileByCategoryTest_null() {
		assertFalse(vm.saveVendorsToFile(null));
	}

}
