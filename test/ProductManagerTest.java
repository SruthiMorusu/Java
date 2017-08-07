package iss.nus.edu.sg.SouvinierStore.test;

/**
 * Name					Date
 * ---------------------------------
 * Sruthi 				11.03.2014
 * 
 * Author Sruthi 
 * Since 11.03.2014
 * 
 * Modified By Sruthi*/

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import iss.nus.edu.sg.SouvinierStore.system.Category;
import iss.nus.edu.sg.SouvinierStore.system.Product;
import iss.nus.edu.sg.SouvinierStore.system.ProductManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ProductManagerTest {
	
	ProductManager productManager;
	String testDataFile = ProductManager.PRODUCT_DATA_FILE;
	
	Category category1;
	String productName1;
	String description1;
	int quantity1;
	double price1;
	String barCode1;
	int recordQuantity1;
	int orderQuantity1;
	
	Category category2;
	String productName2;
	String description2;
	int quantity2;
	double price2;
	String barCode2;
	int recordQuantity2;
	int orderQuantity2;

	@Before
	public void setUp() throws Exception {
		productManager=new ProductManager();
		category1 = new Category("code1", "name1");
		category2 = new Category("code2", "name2");
		String fileName  = testDataFile.substring(0, testDataFile.lastIndexOf("."));
		String fileType = testDataFile.substring(testDataFile.lastIndexOf("."));
		testDataFile = fileName + "_JUnitTest" + fileType;
		productManager.setDataFile(testDataFile);
		
		productName1 = "XYZ";
		description1 = "DESC1";
		quantity1 = 10;
		price1 = 50.00;
		barCode1 = "1234";
		recordQuantity1 = 2;
		orderQuantity1 = 5;
		
		productName2 = "ABC";
		description2 = "DESC2";
		quantity2 = 20;
		price2 = 20.00;
		barCode2 = "1894";
		recordQuantity2 = 5;
		orderQuantity2 = 15;
	}

	@After
	public void tearDown() throws Exception {
		String path = testDataFile;
		File testDataFile = new File(path);
		if(testDataFile.exists()){
			testDataFile.delete();
		}
		productManager = null;
	}

	@Test
	public void testAddProduct_oneProduct() {
		productManager.addProduct(category1, productName1, description1, quantity1, price1, barCode1, recordQuantity1, orderQuantity1);
				
		assertEquals(1,productManager.getProducts().size());
	}
	
	@Test
	public void testAddProduct_multiProduct() {
		productManager.addProduct(category1, productName1, description1, quantity1, price1, barCode1, recordQuantity1, orderQuantity1);
		productManager.addProduct(category2, productName2, description2, quantity2, price2, barCode2, recordQuantity2, orderQuantity2);
				
		assertEquals(2,productManager.getProducts().size());
	}
	
	@Test
	public void testAddProduct_alreadyExist() {
		productManager.addProduct(category1, productName1, description1, quantity1, price1, barCode1, recordQuantity1, orderQuantity1);
		assertFalse(productManager.addProduct(category1, productName1, description1, quantity1, price1, barCode1, recordQuantity1, orderQuantity1));
		assertTrue(productManager.addProduct(category2, productName2, description2, quantity2, price2, barCode2, recordQuantity2, orderQuantity2));
	}
	
	@Test
	public void testGetProducts_empty() {
		assertNotNull(productManager.getProducts());
		assertEquals(0, productManager.getProducts().size());
		assertTrue(productManager.getProducts().isEmpty());
	}
	@Test
	public void testAddProduct_illegal_values() {
		assertFalse(productManager.addProduct(category1, null, description1, quantity1, price1, barCode1, recordQuantity1, orderQuantity1));
		assertFalse(productManager.addProduct(category1, productName1, null, quantity1, price1, barCode1, recordQuantity1, orderQuantity1));
		assertFalse(productManager.addProduct(category1, productName1, description1, quantity1, price1, null, recordQuantity1, orderQuantity1));
	}
	
	@Test
	public void testGetProducts() {
		productManager.addProduct(category1, productName1, description1, quantity1, price1, barCode1, recordQuantity1, orderQuantity1);
		productManager.addProduct(category2, productName2, description2, quantity2, price2, barCode2, recordQuantity2, orderQuantity2);
		
		assertEquals(2,productManager.getProducts().size());

		assertEquals("code1", productManager.getProducts().get(0).getCategory().getCategoryCode());
		assertEquals("name1", productManager.getProducts().get(0).getCategory().getName());
		assertEquals("code1/1", productManager.getProducts().get(0).getId());
		assertEquals("XYZ", productManager.getProducts().get(0).getName());
		assertEquals("DESC1", productManager.getProducts().get(0).getDescription());
		assertEquals(10, productManager.getProducts().get(0).getQuantityAvail());
		assertEquals(50.0, productManager.getProducts().get(0).getPrice(), 0);
		assertEquals("1234", productManager.getProducts().get(0).getBarCode());
		assertEquals(2, productManager.getProducts().get(0).getThreshold());
		assertEquals(5, productManager.getProducts().get(0).getOrderQuantity());
		
		assertEquals("code2", productManager.getProducts().get(1).getCategory().getCategoryCode());
		assertEquals("name2", productManager.getProducts().get(1).getCategory().getName());
		assertEquals("code2/1", productManager.getProducts().get(1).getId());
		assertEquals("ABC", productManager.getProducts().get(1).getName());
		assertEquals("DESC2", productManager.getProducts().get(1).getDescription());
		assertEquals(20, productManager.getProducts().get(1).getQuantityAvail());
		assertEquals(20.0, productManager.getProducts().get(1).getPrice(), 0);
		assertEquals("1894", productManager.getProducts().get(1).getBarCode());
		assertEquals(5, productManager.getProducts().get(1).getThreshold());
		assertEquals(15, productManager.getProducts().get(1).getOrderQuantity());
	}

	@Test
	public void testGetProductsCategory_single() {
		productManager.addProduct(category1, productName1, description1, quantity1, price1, barCode1, recordQuantity1, orderQuantity1);
		
		ArrayList<Product> test = productManager.getProducts(category1);
		assertEquals("code1", test.get(0).getCategory().getCategoryCode());
		assertEquals("name1", test.get(0).getCategory().getName());

	}

	@Test
	public void testGetProductsCategory_multi() {
		productManager.addProduct(category1, productName1, description1, quantity1, price1, barCode1, recordQuantity1, orderQuantity1);
		productManager.addProduct(category2, productName2, description2, quantity2, price2, barCode2, recordQuantity2, orderQuantity2);
		
		ArrayList<Product> test = productManager.getProducts(category1);
		assertEquals("code1", test.get(0).getCategory().getCategoryCode());
		assertEquals("name1", test.get(0).getCategory().getName());
		test = productManager.getProducts(category2);
		assertEquals("code2", test.get(0).getCategory().getCategoryCode());
		assertEquals("name2", test.get(0).getCategory().getName());
	}

	@Test
	public void testGetProduct() {
		productManager.addProduct(category1, productName1, description1, quantity1, price1, barCode1, recordQuantity1, orderQuantity1);
		productManager.addProduct(category2, productName2, description2, quantity2, price2, barCode2, recordQuantity2, orderQuantity2);
		
		Product product1 = productManager.getProduct("code1/1");
		
		assertEquals("code1", product1.getCategory().getCategoryCode());
		assertEquals("name1", product1.getCategory().getName());
		assertEquals("code1/1", product1.getId());
		assertEquals("XYZ", product1.getName());
		assertEquals("DESC1", product1.getDescription());
		assertEquals(10, product1.getQuantityAvail());
		assertEquals(50.0, product1.getPrice(), 0);
		assertEquals("1234", product1.getBarCode());
		assertEquals(2, product1.getThreshold());
		assertEquals(5, product1.getOrderQuantity());
		
		Product product2 = productManager.getProduct("code2/1");
						
		assertEquals("code2", product2.getCategory().getCategoryCode());
		assertEquals("name2", product2.getCategory().getName());
		assertEquals("code2/1", product2.getId());
		assertEquals("ABC", product2.getName());
		assertEquals("DESC2", product2.getDescription());
		assertEquals(20, product2.getQuantityAvail());
		assertEquals(20.0, product2.getPrice(), 0);
		assertEquals("1894", product2.getBarCode());
		assertEquals(5, product2.getThreshold());
		assertEquals(15, product2.getOrderQuantity());
	}

	@Test
	public void testSaveProductsToFile() {
		assertTrue(productManager.addProduct(category1, productName1, description1, quantity1, price1, barCode1, recordQuantity1, orderQuantity1));
		assertTrue(productManager.addProduct(category2, productName2, description2, quantity2, price2, barCode2, recordQuantity2, orderQuantity2));
	}

	@Test
	public void testReadProductsFromFile() {
		productManager.addProduct(category1, productName1, description1, quantity1, price1, barCode1, recordQuantity1, orderQuantity1);
		productManager.addProduct(category2, productName2, description2, quantity2, price2, barCode2, recordQuantity2, orderQuantity2);

		assertTrue(productManager.readProductsFromFile());
		
		assertNotNull(productManager.getProduct("code1/1"));
		assertNotNull(productManager.getProduct("code2/1"));
	}
	
	@Test
	public void testReadProductsFromFile_fileNotExist() {
		assertFalse(productManager.readProductsFromFile());
	}
	
	@Test
	public void testReadProductsFromFile_empty() {
		File dataFile = new File(testDataFile);
		if(dataFile.exists()){
			dataFile.delete();
		}
		try {
			dataFile.createNewFile();
		} catch (IOException e) {
		}
		assertTrue(productManager.readProductsFromFile());
	}
	@Test
	public void testReport_single() {
		productManager.addProduct(category1, productName1, description1, quantity1, price1, barCode1, recordQuantity1, orderQuantity1);
		assertEquals("code1/1,XYZ,DESC1,10,50.00,1234,2,5", productManager.report().get(0));
	}
	
	@Test
	public void testReport_multi() {
		productManager.addProduct(category1, productName1, description1, quantity1, price1, barCode1, recordQuantity1, orderQuantity1);
		productManager.addProduct(category2, productName2, description2, quantity2, price2, barCode2, recordQuantity2, orderQuantity2);
		
		assertEquals("code1/1,XYZ,DESC1,10,50.00,1234,2,5", productManager.report().get(0));
		assertEquals("code2/1,ABC,DESC2,20,20.00,1894,5,15", productManager.report().get(1));
	}
	
	@Test
	public void testDeleteProduct() {
		productManager.addProduct(category1, productName1, description1, quantity1, price1, barCode1, recordQuantity1, orderQuantity1);
		assertEquals(1,productManager.getProducts().size());
		assertFalse(productManager.deleteProduct("code/1"));
		assertEquals(1,productManager.getProducts().size());
	}
	
	@Test
	public void testDeleteProduct_illegal() {
		productManager.addProduct(category1, productName1, description1, quantity1, price1, barCode1, recordQuantity1, orderQuantity1);
		assertFalse(productManager.deleteProduct(null));
		assertFalse(productManager.deleteProduct(""));
	}
}
