package iss.nus.edu.sg.SouvinierStore.test;

/**
 * Name					Date
 * ---------------------------------
 * Sruthi 			13.03.2014
 * 
 * @author Sruthi 
 * Since 13.03.2014
 * 
 * Modified By Sruthi*/

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import iss.nus.edu.sg.SouvinierStore.system.Customer;
import iss.nus.edu.sg.SouvinierStore.system.CustomerManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class CustomerManagerTest {
	CustomerManager customerManager = null;
	String testDataFile = customerManager.MEMBER_DATA_FILE;
	String memberName1;
	String id1;
	int points1;
	String memberName2;
	String id2;
	int points2;
	

	@Before
	public void setUp() throws Exception {
		customerManager=new CustomerManager();
		String fileName  = testDataFile.substring(0, testDataFile.lastIndexOf("."));
		String fileType = testDataFile.substring(testDataFile.lastIndexOf("."));
		testDataFile = fileName + "_JUnitTest" + fileType;
		customerManager.setDataFile(testDataFile);
		memberName1 = "testName";
		id1 = "testID";
		points1 = 10;
		memberName2 = "testName2";
		id2 = "testID2";
		points2 = 20;
		
	}

	@After
	public void tearDown() throws Exception {
		String path = testDataFile;
		File testDataFile = new File(path);
		if(testDataFile.exists()){
			testDataFile.delete();
		}
		customerManager = null;
		memberName1 = null;
		id1 =null;
		points1 = 0;
		memberName2 = null;
		id2 = null;
		points2 = 0;
		

	}

	@Test
	public void testCustomerManager() {
		assertNotNull(customerManager);
	}

	@Test
	public void testAddMember() {
		customerManager.addMember(memberName1, id1, points1);
		assertEquals(1,customerManager.getMembers().size());
		assertEquals("testName", customerManager.getMembers().get(0).getName());
		assertEquals("testID", customerManager.getMembers().get(0).getId());
		assertEquals(10, customerManager.getMembers().get(0).getPoint());
	}
		
//= Delete Member
	@Test
	public void testDeleteMember_exists() {
		customerManager.addMember(memberName1, id1, points1);
		customerManager.addMember(memberName2, id2, points2);
		customerManager.deleteMember(id1);
		assertFalse(customerManager.getMembers().contains(id1));
	
	}
	
	@Test
	public void testDeleteMember_notexists() {
		customerManager.addMember(memberName2, id2, points2);
		assertFalse(customerManager.deleteMember(id1));
		assertFalse(customerManager.getMembers().contains(id1));
	}

	@Test
	public void testDeleteMember_empty() {
		assertFalse(customerManager.deleteMember(id1));
	}
	
	@Test
	public void testDeleteMember_illegal() {
		customerManager.addMember(memberName1, id1, points1);
		customerManager.addMember(memberName2, id2, points2);
		assertFalse(customerManager.deleteMember(null));
	}
	
	@Test
	public void testGetMembers() {
		customerManager.addMember(memberName1, id1, points1);
		assertEquals(1,customerManager.getMembers().size());
		assertEquals("testName", customerManager.getMembers().get(0).getName());
		assertEquals("testID", customerManager.getMembers().get(0).getId());
		assertEquals(10, customerManager.getMembers().get(0).getPoint());
	}
	
	
	@Test
	public void testGetMembers_empty() {
		assertNotNull(customerManager.getMembers());
		assertEquals(0, customerManager.getMembers().size());
		assertTrue(customerManager.getMembers().isEmpty());
	}
	

	@Test
	public void testGetCustomer_exists() {
		customerManager.addMember(memberName1, id1, points1);
		Customer customer = customerManager.getCustomer(id1);
		assertEquals("testID", customer.getId());
	}
	
	@Test
	public void testGetCustomer_notexists(){
		customerManager.addMember(memberName2, id2, points2);
		Customer customer = customerManager.getCustomer(id1);
		assertNull(customer);
	}
	
	@Test
	public void testGetCustomer_illegal(){
		assertNull(customerManager.getCustomer(null));
	}

	//= Read Members
	@Test
	public void testReadMembersFromFile() {
		customerManager.addMember(memberName1, id1, points1);
		customerManager.addMember(memberName2, id2, points2);
		assertTrue(customerManager.readMembersFromFile());
		assertNotNull(customerManager.getCustomer(id1));
		assertNotNull(customerManager.getCustomer(id2));
	}
	@Test
	public void testReadMembersFromFile_empty() {
		File dataFile = new File(testDataFile);
		if(dataFile.exists()){
			dataFile.delete();
		}
		try {
			dataFile.createNewFile();
		} catch (IOException e) {
		}
		assertTrue(customerManager.readMembersFromFile());
	}
	
	@Test
	public void testReadMembersFromFile_fileNotExist() {
		assertFalse(customerManager.readMembersFromFile());
	}
	
//= Save Members
	@Test
	public void testSaveMembersToFile() {
		assertTrue(customerManager.addMember(memberName1, id1, points1));
		assertEquals(1, customerManager.getMembers().size());
	
	}
	
	@Test
	public void testSaveCategoriesToFile_empty() {
		assertTrue(customerManager.saveMembersToFile());
	}

	//- Report
	@Test
	public void testReport() {
		customerManager.addMember(memberName1, id1, points1);
		customerManager.addMember(memberName2, id2, points2);
		ArrayList<String> result = customerManager.report();
		assertEquals(2, result.size());
		assertEquals("testName,testID,10", result.get(0));
		assertEquals("testName2,testID2,20", result.get(1));		
	}
	
	@Test
	public void testReport_empty() {
		ArrayList<String> result = customerManager.report();
		assertTrue(result.isEmpty());
	}


}
