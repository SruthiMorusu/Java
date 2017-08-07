package iss.nus.edu.sg.SouvinierStore.test;

/** 
 * Name          	          Date 
 * -------------------------------------------------- 
 * Sruthi               19.03.2014 
 *  
 * Author : Sruthi
 * Since 18.03.2014 
 **/

import static org.junit.Assert.*;

import java.io.File;

import iss.nus.edu.sg.SouvinierStore.system.StoreKeeperManager;
import iss.nus.edu.sg.SouvinierStore.system.StoreKeeper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StoreKeeperManagerTest {
	
	StoreKeeperManager manager = null;
	StoreKeeper sk = null;
	String testUsername = null;
	String testPassword = null;
	String testDataFile = StoreKeeperManager.STOREKEEPER_DATA_FILE;

	@Before
	public void setUp() throws Exception {
		
		String fileName  = StoreKeeperManager.STOREKEEPER_DATA_FILE.substring(0, StoreKeeperManager.STOREKEEPER_DATA_FILE.lastIndexOf("."));
		String fileType = StoreKeeperManager.STOREKEEPER_DATA_FILE.substring(StoreKeeperManager.STOREKEEPER_DATA_FILE.lastIndexOf("."));
		testDataFile = fileName + "_JUnitTest" + fileType;
		
		manager = new StoreKeeperManager();
		manager.setDataFile(testDataFile);
		testUsername = "Ram";
		testPassword = "1234";
		
	}

	@After
	public void tearDown() throws Exception {
		
		String path = testDataFile;
		File testDataFile = new File(path);
		if(testDataFile.exists()){
			testDataFile.delete();
		}
		manager = null;
		testUsername = null;
		testPassword = null;
		
	}
	
	@Test
	public void testreadStoreKeepersFromFile() {
		manager.addStoreKeeper(testUsername, testPassword);
		assertTrue(manager.readStoreKeepersFromFile());
	}
	
		
	@Test
	public void login()
	{
		
		manager.addStoreKeeper(testUsername, testPassword);
		assertTrue(manager.login(testUsername, testPassword));
		  
	}
	
	@Test
	public void login_invalidvalues()
	{
		
		
		assertFalse(manager.login(null, testPassword));
		assertFalse(manager.login(testUsername, null));
		assertFalse(manager.login("",""));
		  
	}
	
	@Test
	public void test_getCurrentStoreKeeper()
	{
		manager.addStoreKeeper(testUsername, testPassword);
		manager.login(testUsername, testPassword);
		assertNotNull(manager.getCurrentStoreKeeper());
		assertEquals(testUsername,manager.getCurrentStoreKeeper().getName());
		assertEquals(testPassword,manager.getCurrentStoreKeeper().getPassword());
	}
	
	
		
		
		
	}


