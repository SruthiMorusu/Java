package iss.nus.edu.sg.SouvinierStore.test;

/** 
 * Name          	          Date 
 * -------------------------------------------------- 
 * Xing Zibo               19.03.2014 
 *  
 * Author : Xing Zibo
 * Since 18.03.2014 
 *  */

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import iss.nus.edu.sg.SouvinierStore.system.Category;
import iss.nus.edu.sg.SouvinierStore.system.CategoryManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CategoryManagerTest {
	CategoryManager manager = null;
	String code1 = null;
	String name1 = null;
	String code2 = null;
	String name2 = null;
	String testDataFile = CategoryManager.CATEGORY_DATA_FILE;
	@Before
	public void setUp() throws Exception {
		String fileName  = CategoryManager.CATEGORY_DATA_FILE.substring(0, CategoryManager.CATEGORY_DATA_FILE.lastIndexOf("."));
		String fileType = CategoryManager.CATEGORY_DATA_FILE.substring(CategoryManager.CATEGORY_DATA_FILE.lastIndexOf("."));
		testDataFile = fileName + "_JUnitTest" + fileType;
		
		manager = new CategoryManager();
		manager.setDataFile(testDataFile);
		code1 = "testCode1";
		name1 = "testName1";
		code2 = "testCode2";
		name2 = "testName2";
	}

	@After
	public void tearDown() throws Exception {
		String path = testDataFile;
		File testDataFile = new File(path);
		if(testDataFile.exists()){
			testDataFile.delete();
		}
		manager = null;
		code1 = null;
		name1 = null;
		code2 = null;
		name2 = null;
	}

	@Test
	public void testCategoryManager() {
		assertNotNull(manager);
	}
	//= addCategory
	@Test
	public void testAddCategory_OneCategory() {
		manager.addCategory(code1, name1);
		assertEquals(1, manager.getCategories().size());
		Category result = manager.getCategories().get(manager.getCategories().size() - 1);
		assertEquals(code1, result.getCategoryCode());
		assertEquals(name1, result.getName());
	}
	@Test
	public void testAddCategory_MultipleCategories() {
		manager.addCategory(code1, name1);
		manager.addCategory(code2, name2);
		assertEquals(2, manager.getCategories().size());
		Category result1 = manager.getCategories().get(manager.getCategories().size() - 2);
		assertEquals(code1, result1.getCategoryCode());
		assertEquals(name1, result1.getName());
		Category result2 = manager.getCategories().get(manager.getCategories().size() - 1);
		assertEquals(code2, result2.getCategoryCode());
		assertEquals(name2, result2.getName());
	}
	@Test
	public void testAddCategory_alreadyExist() {
		manager.addCategory(code1, name1);
		assertFalse(manager.addCategory(code1, name1));
	}
	//@Test(expected = IllegalArgumentException.class)
	@Test
	public void testAddCategory_illegal_code() {
		manager.addCategory(code1, null);
	}
	//@Test(expected = IllegalArgumentException.class)
	@Test
	public void testAddCategory_illegal_name() {
		manager.addCategory(null, name1);
	}
	//= getCategories
	@Test
	public void testGetCategories_oneCategory() {
		manager.addCategory(code1, name1);
		assertEquals(1, manager.getCategories().size());
	}
	@Test
	public void testGetCategories_multipleCategory() {
		manager.addCategory(code1, name1);
		manager.addCategory(code2, name2);
		assertEquals(2, manager.getCategories().size());
	}
	@Test
	public void testGetCategories_empty() {
		assertNotNull(manager.getCategories());
		assertEquals(0, manager.getCategories().size());
		assertTrue(manager.getCategories().isEmpty());
	}
	@Test
	public void testCheckIfCategoryExists_exist() {
		manager.addCategory(code1, name1);
		manager.addCategory(code2, name2);
		assertTrue(manager.checkIfCategoryExists(code1));
	}
	@Test
	public void testCheckIfCategoryExists_notExist() {
		manager.addCategory(code1, name1);
		assertFalse(manager.checkIfCategoryExists(code2));
	}
	@Test
	public void testCheckIfCategoryExists_empty() {
		assertFalse(manager.checkIfCategoryExists(code2));
	}
	//= getCategory
	@Test
	public void testGetCategoriesString_exist() {
		manager.addCategory(code1, name1);
		Category result = manager.getCategories(code1);
		assertNotNull(result);
		assertEquals(code1, result.getCategoryCode());
		assertEquals(name1, result.getName());
	}
	@Test
	public void testGetCategoriesString_notExist() {
		manager.addCategory(code1, name1);
		Category result = manager.getCategories(code2);
		assertNull(result);
	}
	@Test
	public void testGetCategoriesString_empty() {
		Category result = manager.getCategories(code2);
		assertNull(result);
	}
	@Test
	public void testGetCategoriesString_illegal() {
		manager.addCategory(code1, name1);
		Category result = manager.getCategories(null);
		assertNull(result);
	}
	//= deleteCategory
	@Test
	public void testDeleteCategory_exist() {
		manager.addCategory(code1, name1);
		manager.addCategory(code2, name2);
		Category category = new Category(code1,name1);
		manager.deleteCategory(category);
		assertFalse(manager.getCategories().contains(category));
	}
	@Test
	public void testDeleteCategory_notExist() {
		manager.addCategory(code2, name2);
		Category category = new Category(code1,name1);
		assertFalse(manager.deleteCategory(category));
		assertFalse(manager.getCategories().contains(category));
	}
	@Test
	public void testDeleteCategory_empty() {
		Category category = new Category(code1,name1);
		assertFalse(manager.deleteCategory(category));
	}
	@Test
	public void testDeleteCategory_illegal() {
		manager.addCategory(code1, name1);
		manager.addCategory(code2, name2);
		assertFalse(manager.deleteCategory(null));
	}
	//= saveFile
	@Test
	public void testSaveCategoriesToFile() {
		manager.addCategory(code1, name1);
		manager.addCategory(code2, name2);
		assertTrue(manager.saveCategoriesToFile());
		
	}
	@Test
	public void testSaveCategoriesToFile_empty() {
		assertTrue(manager.saveCategoriesToFile());
	}
	@Test
	public void testReadCategoriesFromFile() {
		manager.addCategory(code1, name1);
		manager.addCategory(code2, name2);
		manager.saveCategoriesToFile();
		assertTrue(manager.readCategoriesFromFile());
		assertNotNull(manager.getCategories(code1));
		assertNotNull(manager.getCategories(code2));
	}
	@Test
	public void testReadCategoriesFromFile_empty() {
		File dataFile = new File(testDataFile);
		if(dataFile.exists()){
			dataFile.delete();
		}
		try {
			dataFile.createNewFile();
		} catch (IOException e) {
		}
		assertTrue(manager.readCategoriesFromFile());
	}
	@Test
	public void testReadCategoriesFromFile_fileNotExist() {
		assertFalse(manager.readCategoriesFromFile());
	}
	@Test
	public void testReport_oneCategory() {
		manager.addCategory(code1, name1);
		ArrayList<String> result = manager.report();
		assertEquals(1, result.size());
	    assertEquals("TESTCODE1,testName1", result.get(0));
	}
	@Test
	public void testReport_multipleCategory() {
		manager.addCategory(code1, name1);
		manager.addCategory(code2, name2);
		ArrayList<String> result = manager.report();
		assertEquals(2, result.size());
	    assertEquals("TESTCODE1,testName1", result.get(0));
	    assertEquals("TESTCODE2,testName2", result.get(1));
	}
	@Test
	public void testReport_empty() {
		ArrayList<String> result = manager.report();
		assertTrue(result.isEmpty());
	}
}
