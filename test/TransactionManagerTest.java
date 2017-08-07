package iss.nus.edu.sg.SouvinierStore.test;

/**
 * Name					Date
 * ---------------------------------
 * Sruthi 				18.03.2014
 * 
 * Author Sruthi
 * Since 18.03.2014
 * 
 * Modified By Sruthi*/

import static org.junit.Assert.*;
import iss.nus.edu.sg.SouvinierStore.system.Bill;
import iss.nus.edu.sg.SouvinierStore.system.BillException;
import iss.nus.edu.sg.SouvinierStore.system.Customer;
import iss.nus.edu.sg.SouvinierStore.system.NonMember;
import iss.nus.edu.sg.SouvinierStore.system.Product;
import iss.nus.edu.sg.SouvinierStore.system.Transaction;
import iss.nus.edu.sg.SouvinierStore.system.TransactionManager;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TransactionManagerTest {
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	private SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
	TransactionManager transactionManager = null;
	String testDataFile = transactionManager.TRANSACTION_DATA_FILE;
	int transactionID1;
	String productID1;
	Date date1;
	int transactionID2;
	String productID2;
	Date date2;
	Date date3;
	Date date4;
	Date date5;
	@Before
	public void setUp() throws Exception {
		transactionManager = new TransactionManager();
		String fileName  = TransactionManager.TRANSACTION_DATA_FILE.substring(0, TransactionManager.TRANSACTION_DATA_FILE.lastIndexOf("."));
		String fileType = TransactionManager.TRANSACTION_DATA_FILE.substring(TransactionManager.TRANSACTION_DATA_FILE.lastIndexOf("."));
		testDataFile = fileName + "_JUnitTest" + fileType;
		transactionManager.setDataFile(testDataFile);
		transactionID1 = 1;
		productID1 = "testID1";
		date1 = new Date(System.currentTimeMillis());
		transactionID2 = 2;
		productID2 = "testID2";
		date2 =new Date(System.currentTimeMillis());
		date3 = dateFormat.parse("2013-09-28");
		date4 = dateFormat.parse("2013-09-29");
		date5 = dateFormat.parse("2013-09-30");
		
	}

	@After
	public void tearDown() throws Exception {
		String path = testDataFile;
		File testDataFile = new File(path);
		if(testDataFile.exists()){
			testDataFile.delete();
		}

		transactionManager = null;
		transactionID1 = 0;
		productID1 = null;
		date1 = null;
		transactionID2 = 2;
		productID2 = null;
		date2 = null;
		date3 = null;
		date4 = null;
		date5 = null;
		
		
	}

	@Test
	public void testTransactionManager() {
		assertNotNull(transactionManager);
	}

	@Test
	public void testAddTransaction() throws BillException {
		Customer customer = new NonMember();
		Bill bill = new Bill(customer);
		Product product = new Product();
		try {
			bill.addBillItem(product, 1);
		} catch (BillException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(transactionManager.addTransaction(bill, new Date(System.currentTimeMillis())));
	}

	@Test
	public void testGetTransactions_oneTransaction() throws BillException {
		Customer customer = new NonMember();
		Bill bill = new Bill(customer);
		Product product = new Product();
		try {
			bill.addBillItem(product, 1);
		} catch (BillException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(transactionManager.addTransaction(bill,date1));
		assertEquals(1,transactionManager.getTransactions().size());
	}
	
	@Test
	public void testGetTransactions_multipleTransactions() throws BillException {
		Customer customer1 = new NonMember();
		Bill bill1 = new Bill(customer1);
		Product product1 = new Product();
		product1.setId(productID1);
		try {
			bill1.addBillItem(product1, 1);
		} catch (BillException e) {
			e.printStackTrace();
		}
		Customer customer2 = new NonMember();
		Bill bill2 = new Bill(customer2);
		Product product2 = new Product();
		product2.setId(productID2);
		try {
			bill2.addBillItem(product2, 1);
		} catch (BillException e) {
			e.printStackTrace();
		}
		
		assertTrue(transactionManager.addTransaction(bill1,date1));
		assertTrue(transactionManager.addTransaction(bill2,date2));
		assertEquals(2,transactionManager.getTransactions().size());
	}

	@Test
	public void testGetTransactions_empty(){
		assertNotNull(transactionManager.getTransactions());
		assertEquals(0, transactionManager.getTransactions().size());
		assertTrue(transactionManager.getTransactions().isEmpty());
	}

	@Test
	
	public void testGetTransactionsDateDate() throws BillException {
		
		Customer customer1 = new NonMember();
		Bill bill1 = new Bill(customer1);
		Product product1 = new Product();
		product1.setId(productID1);
		product1.setBarCode("");
		product1.setName("");
		product1.setQuantityAvail(100);
		try {
			bill1.addBillItem(product1, 1);
		} catch (BillException e) {
			e.printStackTrace();
		}
		Customer customer2 = new NonMember();
		Bill bill2 = new Bill(customer2);
		Product product2 = new Product();
		product2.setId(productID2);
		product2.setBarCode("");
		product2.setName("");
		product2.setQuantityAvail(100);
		try {
			bill2.addBillItem(product2, 1);
		} catch (BillException e) {
			e.printStackTrace();
		}
		Customer customer3 = new NonMember();
		Bill bill3 = new Bill(customer3);
		Product product3 = new Product();
		try {
			bill3.addBillItem(product3, 1);
		} catch (BillException e) {
			e.printStackTrace();
		}
		assertTrue(transactionManager.addTransaction(bill1,date3));
		assertTrue(transactionManager.addTransaction(bill2,date4));
		assertTrue(transactionManager.addTransaction(bill1,date5));
		ArrayList<Transaction> result = transactionManager.getTransactions(date3, date5);
		assertEquals(1, result.size());
		//assertEquals()
		
	}

	@Test
	public void testGetTransaction_exists() throws BillException {
		Customer customer1 = new NonMember();
		Bill bill1 = new Bill(customer1);
		Product product1 = new Product();
		try {
			bill1.addBillItem(product1, 1);
		} catch (BillException e) {
			e.printStackTrace();
		}
		Customer customer2 = new NonMember();
		Bill bill2 = new Bill(customer2);
		Product product2 = new Product();
		try {
			bill2.addBillItem(product2, 1);
		} catch (BillException e) {
			e.printStackTrace();
		}
		
		assertTrue(transactionManager.addTransaction(bill1,date1));
		assertTrue(transactionManager.addTransaction(bill2,date2));
		Transaction transaction1 = transactionManager.getTransaction(transactionID1);
		Transaction transaction2 = transactionManager.getTransaction(transactionID2);
		assertEquals(1, transaction1.getTransactionId());
		assertEquals(2, transaction2.getTransactionId());	
	}
	
	@Test
	public void testGetTransaction_notexists() throws BillException{
		Customer customer1 = new NonMember();
		Bill bill1 = new Bill(customer1);
		Product product1 = new Product();
		try {
			bill1.addBillItem(product1, 1);
		} catch (BillException e) {
			e.printStackTrace();
		}
		Customer customer2 = new NonMember();
		Bill bill2 = new Bill(customer2);
		Product product2 = new Product();
		try {
			bill2.addBillItem(product2, 1);
		} catch (BillException e) {
			e.printStackTrace();
		}
		
		assertTrue(transactionManager.addTransaction(bill1,date1));
		Transaction transaction = transactionManager.getTransaction(transactionID1);
		assertNotNull(transaction);
	}
	
	@Test
	public void testGetTransaction_illegal(){
		Transaction transaction = transactionManager.getTransaction(0);
		assertNull(transaction);
	
	}
	@Test
	public void testSaveTransactionToFile() throws BillException {
		Customer customer1 = new NonMember();
		Bill bill1 = new Bill(customer1);
		Product product1 = new Product();
		product1.setId(productID1);
		try {
			bill1.addBillItem(product1, 1);
		} catch (BillException e) {
			e.printStackTrace();
		}
		Customer customer2 = new NonMember();
		Bill bill2 = new Bill(customer2);
		Product product2 = new Product();
		product2.setId(productID2);
		try {
			bill2.addBillItem(product2, 1);
		} catch (BillException e) {
			e.printStackTrace();
		}
		
		assertTrue(transactionManager.addTransaction(bill1,date1));
		assertTrue(transactionManager.addTransaction(bill2,date2));
		assertTrue(transactionManager.saveTransactionToFile());
	}
	
	@Test
	public void testSaveTransactionToFile_empty() {
		assertTrue(transactionManager.saveTransactionToFile());
	}

	@Test
	public void testReadTransactionFromFile() throws BillException {
		Customer customer1 = new NonMember();
		Bill bill1 = new Bill(customer1);
		Product product1 = new Product();
		product1.setId(productID1);
		product1.setQuantityAvail(5);
		try {
			bill1.addBillItem(product1, 1);
		} catch (BillException e) {
			e.printStackTrace();
		}
		Customer customer2 = new NonMember();
		Bill bill2 = new Bill(customer2);
		Product product2 = new Product();
		product2.setId(productID2);
		product2.setQuantityAvail(5);
		try {
			bill2.addBillItem(product2, 1);
		} catch (BillException e) {
			e.printStackTrace();
		}
		
		assertTrue(transactionManager.addTransaction(bill1,date1));
		assertTrue(transactionManager.addTransaction(bill2,date2));
		assertTrue(transactionManager.saveTransactionToFile());
		assertTrue(transactionManager.readTransactionFromFile());
		assertNotNull(transactionManager.getTransaction(transactionID1));
		assertNotNull(transactionManager.getTransaction(transactionID2));
	}
	
	@Test
	public void testReadTransactionFromFile_empty() {
		assertFalse(transactionManager.readTransactionFromFile());
	}
	

	@Test
	public void testReport() throws BillException{
		Customer customer1 = new NonMember();
		Bill bill1 = new Bill(customer1);
		Product product1 = new Product();
		product1.setId(productID1);
		product1.setQuantityAvail(5);
		try {
			bill1.addBillItem(product1, 1);
		} catch (BillException e) {
			e.printStackTrace();
		}
		Customer customer2 = new NonMember();
		Bill bill2 = new Bill(customer2);
		Product product2 = new Product();
		product2.setId(productID2);
		product2.setQuantityAvail(5);
		try {
			bill2.addBillItem(product2, 1);
		} catch (BillException e) {
			e.printStackTrace();
		}
		
		assertTrue(transactionManager.addTransaction(bill1,date1));
		assertTrue(transactionManager.addTransaction(bill2,date2));
		ArrayList<String> test_report = transactionManager.report();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		assertEquals(2,test_report.size());
		assertEquals("1,testID1,PUBLIC,1," + dateFormat.format(date1),test_report.get(0));
		assertEquals("2,testID2,PUBLIC,1," + dateFormat.format(date2), test_report.get(1));	  	
	}
	
	@Test
	public void testReport_empty() {
		ArrayList<String> test_report = transactionManager.report();
		assertTrue(test_report.isEmpty());
	}

}
