package iss.nus.edu.sg.SouvinierStore.test;
/**
 * Name					Date
 * ---------------------------------
 * Sruthi 				18.03.2014
 * 
 * Author Sruthi
 * Since 18.03.2014
 * 
 **/
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import iss.nus.edu.sg.SouvinierStore.system.CategoryManager;
import iss.nus.edu.sg.SouvinierStore.system.DiscountManager;
import iss.nus.edu.sg.SouvinierStore.system.Member;
import iss.nus.edu.sg.SouvinierStore.system.MemberDiscount;
import iss.nus.edu.sg.SouvinierStore.system.NonMember;
import iss.nus.edu.sg.SouvinierStore.system.NonMemberDiscount;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
public class DiscountManagerTest {
	DiscountManager manager = null;
	String testDataFile = DiscountManager.DISCOUNT_DATA_FILE;
	private String code1_member = null;
	private String descrption1_member = null;
	private Date startDate1_member = null;
	private int daysPeriod1_member = 0;
	private int percentage1_member = 0;
	private String code1_public = null;
	private String descrption1_public = null;
	private Date startDate1_public = null;
	private int daysPeriod1_public = 0;
	private int percentage1_public = 0;
	private String code2_member = null;
	private String descrption2_member = null;
	private Date startDate2_member = null;
	private int daysPeriod2_member = 0;
	private int percentage2_member = 0;
	private String code2_public = null;
	private String descrption2_public = null;
	private Date startDate2_public = null;
	private int daysPeriod2_public = 0;
	private int percentage2_public = 0;
	private Member customer_member = null;
	private NonMember customer_public  = null;

	@Before
	public void setUp() throws Exception {
		String fileName  = DiscountManager.DISCOUNT_DATA_FILE.substring(0, DiscountManager.DISCOUNT_DATA_FILE.lastIndexOf("."));
		String fileType = DiscountManager.DISCOUNT_DATA_FILE.substring(DiscountManager.DISCOUNT_DATA_FILE.lastIndexOf("."));
		testDataFile = fileName + "_JUnitTest" + fileType;
		
		code1_member = "MEMBER_CODE1";
		descrption1_member = "member discount descrption 1";
		startDate1_member = new Date(System.currentTimeMillis());
		daysPeriod1_member = 10;
		percentage1_member = 10;
		code1_public = "PUBLIC_CODE1";
		descrption1_public = "public discount descrption 1";
		startDate1_public = new Date(System.currentTimeMillis());
		daysPeriod1_public = 20;
		percentage1_public = 8;
		code2_member = "MEMBER_CODE2";
		descrption2_member = "member discount descrption 2";
		startDate2_member = new Date(System.currentTimeMillis());
		daysPeriod2_member = 20;
		percentage2_member = 25;
		code2_public = "PUBLIC_CODE2";
		descrption2_public = "public discount descrption 2";
		startDate2_public = new Date(System.currentTimeMillis());
		daysPeriod2_public = 40;
		percentage2_public = 15;
		
		customer_member = new Member("member_name", "member_id", 0);
		customer_public = new NonMember();
		manager = new DiscountManager();
		manager.setDataFile(testDataFile);
	}

	@After
	public void tearDown() throws Exception {
		String path = testDataFile;
		File testDataFile = new File(path);
		if(testDataFile.exists()){
			testDataFile.delete();
		}
		code1_member = null;
		descrption1_member = null;
		startDate1_member = null;
		daysPeriod1_member = 0;
		percentage1_member = 0;
		code1_public = null;
		descrption1_public = null;
		startDate1_public = null;
		daysPeriod1_public = 0;
		percentage1_public = 0;
		code2_member = null;
		descrption2_member = null;
		startDate2_member = null;
		daysPeriod2_member = 0;
		percentage2_member = 0;
		code2_public = null;
		descrption2_public = null;
		startDate2_public = null;
		daysPeriod2_public = 0;
		percentage2_public = 0;
		
		customer_member = null;
		customer_public = null;
		manager = null;
	}

	@Test
	public void testDiscountManager() {
		assertNotNull(manager);
	}
	//-- addDiscount
	@Test
	public void testAddDiscount_One_AllAplicableDiscount() {
		assertTrue(manager.addDiscount(code1_public, descrption1_public, startDate1_public, daysPeriod1_public, percentage1_public, DiscountManager.APPLICABLE_TO_ALL_FLAG));
		assertEquals(1, manager.getAllCustomerDiscounts().size());
		NonMemberDiscount discount = manager.getAllCustomerDiscounts().get(0);
		assertNotNull(discount);
		assertEquals(code1_public, discount.getCode());
		assertEquals(descrption1_public, discount.getDescription());
		assertEquals(startDate1_public, discount.getStartDate());
		assertEquals(daysPeriod1_public, discount.getdaysPeriod());
		assertEquals(percentage1_public, discount.getPercentage());
	}
	@Test
	public void testAddDiscount_Multi_AllAplicableDiscount() {
		assertTrue(manager.addDiscount(code1_public, descrption1_public, startDate1_public, daysPeriod1_public, percentage1_public, DiscountManager.APPLICABLE_TO_ALL_FLAG));
		assertTrue(manager.addDiscount(code2_public, descrption2_public, startDate2_public, daysPeriod2_public, percentage2_public, DiscountManager.APPLICABLE_TO_ALL_FLAG));
		assertEquals(2, manager.getAllCustomerDiscounts().size());
		NonMemberDiscount discount1 = manager.getAllCustomerDiscounts().get(0);
		assertNotNull(discount1);
		assertEquals(code1_public, discount1.getCode());
		assertEquals(descrption1_public, discount1.getDescription());
		assertEquals(startDate1_public, discount1.getStartDate());
		assertEquals(daysPeriod1_public, discount1.getdaysPeriod());
		assertEquals(percentage1_public, discount1.getPercentage());
		NonMemberDiscount discount2 = manager.getAllCustomerDiscounts().get(1);
		assertNotNull(discount2);
		assertEquals(code2_public, discount2.getCode());
		assertEquals(descrption2_public, discount2.getDescription());
		assertEquals(startDate2_public, discount2.getStartDate());
		assertEquals(daysPeriod2_public, discount2.getdaysPeriod());
		assertEquals(percentage2_public, discount2.getPercentage());
	}
	@Test
	public void testAddDiscount_One_MemberAplicableDiscount() {
		assertTrue(manager.addDiscount(code1_member, descrption1_member, startDate1_member, daysPeriod1_member, percentage1_member, DiscountManager.APPLICABLE_TO_MEMBER_FLAG));
		assertEquals(1, manager.getMemberDiscounts().size());
		MemberDiscount discount = manager.getMemberDiscounts().get(0);
		assertNotNull(discount);
		assertEquals(code1_member, discount.getCode());
		assertEquals(descrption1_member, discount.getDescription());
		assertEquals(startDate1_member, discount.getStartDate());
		assertEquals(daysPeriod1_member, discount.getdaysPeriod());
		assertEquals(percentage1_member, discount.getPercentage());
	}
	@Test
	public void testAddDiscount_Multi_MemberAplicableDiscount() {
		assertTrue(manager.addDiscount(code1_member, descrption1_member, startDate1_member, daysPeriod1_member, percentage1_member, DiscountManager.APPLICABLE_TO_MEMBER_FLAG));
		assertTrue(manager.addDiscount(code2_member, descrption2_member, startDate2_member, daysPeriod2_member, percentage2_member, DiscountManager.APPLICABLE_TO_MEMBER_FLAG));
		assertEquals(2, manager.getMemberDiscounts().size());
		MemberDiscount discount1 = manager.getMemberDiscounts().get(0);
		assertNotNull(discount1);
		assertEquals(code1_member, discount1.getCode());
		assertEquals(descrption1_member, discount1.getDescription());
		assertEquals(startDate1_member, discount1.getStartDate());
		assertEquals(daysPeriod1_member, discount1.getdaysPeriod());
		assertEquals(percentage1_member, discount1.getPercentage());
		MemberDiscount discount2 = manager.getMemberDiscounts().get(1);
		assertNotNull(discount2);
		assertEquals(code2_member, discount2.getCode());
		assertEquals(descrption2_member, discount2.getDescription());
		assertEquals(startDate2_member, discount2.getStartDate());
		assertEquals(daysPeriod2_member, discount2.getdaysPeriod());
		assertEquals(percentage2_member, discount2.getPercentage());
	}
	@Test
	public void testAddDiscount_Mix_Discount() {
		assertTrue(manager.addDiscount(code1_public, descrption1_public, startDate1_public, daysPeriod1_public, percentage1_public, DiscountManager.APPLICABLE_TO_ALL_FLAG));
		assertTrue(manager.addDiscount(code2_member, descrption2_member, startDate2_member, daysPeriod2_member, percentage2_member, DiscountManager.APPLICABLE_TO_MEMBER_FLAG));
		assertEquals(1, manager.getAllCustomerDiscounts().size());
		NonMemberDiscount discount1 = manager.getAllCustomerDiscounts().get(0);
		assertNotNull(discount1);
		assertEquals(code1_public, discount1.getCode());
		assertEquals(descrption1_public, discount1.getDescription());
		assertEquals(startDate1_public, discount1.getStartDate());
		assertEquals(daysPeriod1_public, discount1.getdaysPeriod());
		assertEquals(percentage1_public, discount1.getPercentage());
		assertEquals(1, manager.getMemberDiscounts().size());
		MemberDiscount discount2 = manager.getMemberDiscounts().get(0);
		assertNotNull(discount2);
		assertEquals(code2_member, discount2.getCode());
		assertEquals(descrption2_member, discount2.getDescription());
		assertEquals(startDate2_member, discount2.getStartDate());
		assertEquals(daysPeriod2_member, discount2.getdaysPeriod());
		assertEquals(percentage2_member, discount2.getPercentage());
	}
	@Test
	public void testAddDiscount_AllAplicableDiscount_alreadyExist() {
		manager.addDiscount(code1_public, descrption1_public, startDate1_public, daysPeriod1_public, percentage1_public, DiscountManager.APPLICABLE_TO_ALL_FLAG);
		assertFalse(manager.addDiscount(code1_public, descrption1_public, startDate1_public, daysPeriod1_public, percentage1_public, DiscountManager.APPLICABLE_TO_ALL_FLAG));
	}
	@Test
	public void testAddDiscount_AllAplicableDiscount_illegal() {
		assertFalse(manager.addDiscount(null, null, null, daysPeriod1_public, -1, DiscountManager.APPLICABLE_TO_ALL_FLAG));
	}
	@Test
	public void testAddDiscount_MemberAplicableDiscount_alreadyExist() {
		manager.addDiscount(code1_member, descrption1_member, startDate1_member, daysPeriod1_member, percentage1_member, DiscountManager.APPLICABLE_TO_MEMBER_FLAG);
		assertFalse(manager.addDiscount(code1_member, descrption1_member, startDate1_member, daysPeriod1_member, percentage1_member, DiscountManager.APPLICABLE_TO_MEMBER_FLAG));
	}
	@Test
	public void testAddDiscount_MemberAplicableDiscount_illegal() {
		assertFalse(manager.addDiscount(null, null, null, daysPeriod1_member, -1, DiscountManager.APPLICABLE_TO_MEMBER_FLAG));
	}
	//-- deleteDiscount
	@Test
	public void testDeleteDiscountMemberDiscount() {
		manager.addDiscount(code1_member, descrption1_member, startDate1_member, daysPeriod1_member, percentage1_member, DiscountManager.APPLICABLE_TO_MEMBER_FLAG);
		MemberDiscount discount = manager.getMemberDiscount(code1_member);
		assertNotNull(discount);
		assertTrue(manager.deleteDiscount(discount));
		assertEquals(0, manager.getMemberDiscounts().size());
	}
	@Test
	public void testDeleteDiscountMemberDiscount_notExsit() {
		manager.addDiscount(code1_member, descrption1_member, startDate1_member, daysPeriod1_member, percentage1_member, DiscountManager.APPLICABLE_TO_MEMBER_FLAG);
		MemberDiscount discount = manager.getMemberDiscount(code1_member);
		assertNotNull(discount);
		manager.deleteDiscount(discount);
		assertFalse(manager.deleteDiscount(discount));
	}
	@Test
	public void testDeleteDiscountMemberDiscount_illegal() {
		manager.addDiscount(code1_member, descrption1_member, startDate1_member, daysPeriod1_member, percentage1_member, DiscountManager.APPLICABLE_TO_MEMBER_FLAG);
		MemberDiscount discount = null;
		assertFalse(manager.deleteDiscount(discount));
	}
	@Test
	public void testDeleteDiscountNonMemberDiscount() {
		manager.addDiscount(code1_public, descrption1_public, startDate1_public, daysPeriod1_public, percentage1_public, DiscountManager.APPLICABLE_TO_ALL_FLAG);
		NonMemberDiscount discount = manager.getAllCustomerDiscount(code1_public);
		assertNotNull(discount);
		assertTrue(manager.deleteDiscount(discount));
		assertEquals(0, manager.getMemberDiscounts().size());
	}
	@Test
	public void testDeleteDiscountNonMemberDiscount_notExsit() {
		manager.addDiscount(code1_public, descrption1_public, startDate1_public, daysPeriod1_public, percentage1_public, DiscountManager.APPLICABLE_TO_ALL_FLAG);
		NonMemberDiscount discount = manager.getAllCustomerDiscount(code1_public);
		manager.deleteDiscount(discount);
		assertFalse(manager.deleteDiscount(discount));
	}
	@Test
	public void testDeleteDiscountNonMemberDiscount_illegal() {
		manager.addDiscount(code1_public, descrption1_public, startDate1_public, daysPeriod1_public, percentage1_public, DiscountManager.APPLICABLE_TO_ALL_FLAG);
		NonMemberDiscount discount = null;
		assertFalse(manager.deleteDiscount(discount));
	}
	//-- getDiscounts
	@Test
	public void testGetMemberDiscounts() {
		assertTrue(manager.addDiscount(code1_member, descrption1_member, startDate1_member, daysPeriod1_member, percentage1_member, DiscountManager.APPLICABLE_TO_MEMBER_FLAG));
		assertNotNull(manager.getMemberDiscounts());
		assertEquals(1, manager.getMemberDiscounts().size());
	}
	@Test
	public void testGetMemberDiscounts_empty() {
		assertNotNull(manager.getMemberDiscounts());
		assertEquals(0, manager.getMemberDiscounts().size());
	}
	@Test
	public void testGetAllCustomerDiscounts() {
		assertTrue(manager.addDiscount(code1_public, descrption1_public, startDate1_public, daysPeriod1_public, percentage1_public, DiscountManager.APPLICABLE_TO_ALL_FLAG));
		assertNotNull(manager.getAllCustomerDiscounts());
		assertEquals(1, manager.getAllCustomerDiscounts().size());
	}
	@Test
	public void testGetAllCustomerDiscounts_empty() {
		assertNotNull(manager.getAllCustomerDiscounts());
		assertEquals(0, manager.getAllCustomerDiscounts().size());
	}
	@Test
	public void testGetMemberDiscount() {
		manager.addDiscount(code1_member, descrption1_member, startDate1_member, daysPeriod1_member, percentage1_member, DiscountManager.APPLICABLE_TO_MEMBER_FLAG);
		MemberDiscount discount = manager.getMemberDiscount(code1_member);
		assertNotNull(discount);
		assertEquals(code1_member, discount.getCode());
		assertEquals(descrption1_member, discount.getDescription());
		assertEquals(startDate1_member, discount.getStartDate());
		assertEquals(daysPeriod1_member, discount.getdaysPeriod());
		assertEquals(percentage1_member, discount.getPercentage());
	}
	@Test
	public void testGetMemberDiscount_notExist() {
		MemberDiscount discount = manager.getMemberDiscount(code1_member);
		assertNull(discount);
	}
	@Test
	public void testGetMemberDiscount_illegal() {
		manager.addDiscount(code1_member, descrption1_member, startDate1_member, daysPeriod1_member, percentage1_member, DiscountManager.APPLICABLE_TO_MEMBER_FLAG);
		MemberDiscount discount = manager.getMemberDiscount(null);
		assertNull(discount);
	}
	@Test
	public void testGetAllCustomerDiscount() {
		manager.addDiscount(code1_public, descrption1_public, startDate1_public, daysPeriod1_public, percentage1_public, DiscountManager.APPLICABLE_TO_ALL_FLAG);
		NonMemberDiscount discount = manager.getAllCustomerDiscount(code1_public);
		assertNotNull(discount);
		assertEquals(code1_public, discount.getCode());
		assertEquals(descrption1_public, discount.getDescription());
		assertEquals(startDate1_public, discount.getStartDate());
		assertEquals(daysPeriod1_public, discount.getdaysPeriod());
		assertEquals(percentage1_public, discount.getPercentage());
	}
	@Test
	public void testGetAllCustomerDiscount_notExist() {
		NonMemberDiscount discount = manager.getAllCustomerDiscount(code1_public);
		assertNull(discount);
	}
	@Test
	public void testGetAllCustomerDiscount_illegal() {
		manager.addDiscount(code1_public, descrption1_public, startDate1_public, daysPeriod1_public, percentage1_public, DiscountManager.APPLICABLE_TO_ALL_FLAG);
		NonMemberDiscount discount = manager.getAllCustomerDiscount(null);
		assertNull(discount);
	}
	//-- getBestDiscount
	@Test
	public void testGetBestDiscountIfAvailable_ForMember_noDiscount() {
		double result = manager.getBestDiscountIfAvailable(customer_member, new Date(System.currentTimeMillis()));
		assertEquals(0.0, result, 0.001);
	}
	@Test
	public void testGetBestDiscountIfAvailable_ForMember_illegal() {
		double result = manager.getBestDiscountIfAvailable(null, null);
		assertEquals(0.0, result, 0.001);
	}
	@Test
	public void testGetBestDiscountIfAvailable_ForMember_matchMemberDiscount() {
		manager.addDiscount(code1_public, descrption1_public, startDate1_public, daysPeriod1_public, percentage1_public, DiscountManager.APPLICABLE_TO_ALL_FLAG);
		manager.addDiscount(code2_public, descrption2_public, startDate2_public, daysPeriod2_public, percentage2_public, DiscountManager.APPLICABLE_TO_ALL_FLAG);
		manager.addDiscount(code1_member, descrption1_member, startDate1_member, daysPeriod1_member, percentage1_member, DiscountManager.APPLICABLE_TO_MEMBER_FLAG);
		manager.addDiscount(code2_member, descrption2_member, startDate2_member, daysPeriod2_member, percentage2_member, DiscountManager.APPLICABLE_TO_MEMBER_FLAG);
		double result = manager.getBestDiscountIfAvailable(customer_member, new Date(System.currentTimeMillis()));
		double besetDiscount = (double)(percentage2_member) / 100;
		assertEquals(besetDiscount, result, 0.001);
	}
	@Test
	public void testGetBestDiscountIfAvailable_ForMember_matchNonMemberDiscount() {
		manager.addDiscount(code1_public, descrption1_public, startDate1_public, daysPeriod1_public, percentage1_public, DiscountManager.APPLICABLE_TO_ALL_FLAG);
		manager.addDiscount(code2_public, descrption2_public, startDate2_public, daysPeriod2_public, percentage2_public, DiscountManager.APPLICABLE_TO_ALL_FLAG);
		manager.addDiscount(code1_member, descrption1_member, startDate1_member, daysPeriod1_member, percentage1_member, DiscountManager.APPLICABLE_TO_MEMBER_FLAG);
		double result = manager.getBestDiscountIfAvailable(customer_member, new Date(System.currentTimeMillis()));
		double besetDiscount = (double)(percentage2_public) / 100;
		assertEquals(besetDiscount, result, 0.001);
	}
	@Test
	public void testGetBestDiscountIfAvailable_ForPublic_noDiscount() {
		double result = manager.getBestDiscountIfAvailable(customer_public, new Date(System.currentTimeMillis()));
		assertEquals(0.0, result, 0.001);
	}
	@Test
	public void testGetBestDiscountIfAvailable_ForPublic_illegal() {
		double result = manager.getBestDiscountIfAvailable(null, null);
		assertEquals(0.0, result, 0.001);
	}
	@Test
	public void testGetBestDiscountIfAvailable_ForPublic() {
		manager.addDiscount(code1_public, descrption1_public, startDate1_public, daysPeriod1_public, percentage1_public, DiscountManager.APPLICABLE_TO_ALL_FLAG);
		manager.addDiscount(code2_public, descrption2_public, startDate2_public, daysPeriod2_public, percentage2_public, DiscountManager.APPLICABLE_TO_ALL_FLAG);
		manager.addDiscount(code1_member, descrption1_member, startDate1_member, daysPeriod1_member, percentage1_member, DiscountManager.APPLICABLE_TO_MEMBER_FLAG);
		manager.addDiscount(code2_member, descrption2_member, startDate2_member, daysPeriod2_member, percentage2_member, DiscountManager.APPLICABLE_TO_MEMBER_FLAG);
		double result = manager.getBestDiscountIfAvailable(customer_public, new Date(System.currentTimeMillis()));
		double besetDiscount = (double)(percentage2_public) / 100;
		assertEquals(besetDiscount, result, 0.001);
	}
	// file
	@Test
	public void testSaveDiscountsToFile() {
		manager.addDiscount(code1_public, descrption1_public, startDate1_public, daysPeriod1_public, percentage1_public, DiscountManager.APPLICABLE_TO_ALL_FLAG);
		manager.addDiscount(code2_public, descrption2_public, startDate2_public, daysPeriod2_public, percentage2_public, DiscountManager.APPLICABLE_TO_ALL_FLAG);
		manager.addDiscount(code1_member, descrption1_member, startDate1_member, daysPeriod1_member, percentage1_member, DiscountManager.APPLICABLE_TO_MEMBER_FLAG);
		manager.addDiscount(code2_member, descrption2_member, startDate2_member, daysPeriod2_member, percentage2_member, DiscountManager.APPLICABLE_TO_MEMBER_FLAG);
		assertTrue(manager.saveDiscountsToFile());
	}
	@Test
	public void testSaveDiscountsToFile_empty() {
		assertTrue(manager.saveDiscountsToFile());
	}
	@Test
	public void testReadDiscountsFromFile() {
		manager.addDiscount(code1_public, descrption1_public, startDate1_public, daysPeriod1_public, percentage1_public, DiscountManager.APPLICABLE_TO_ALL_FLAG);
		manager.addDiscount(code2_public, descrption2_public, startDate2_public, daysPeriod2_public, percentage2_public, DiscountManager.APPLICABLE_TO_ALL_FLAG);
		manager.addDiscount(code1_member, descrption1_member, startDate1_member, daysPeriod1_member, percentage1_member, DiscountManager.APPLICABLE_TO_MEMBER_FLAG);
		manager.addDiscount(code2_member, descrption2_member, startDate2_member, daysPeriod2_member, percentage2_member, DiscountManager.APPLICABLE_TO_MEMBER_FLAG);
		manager.saveDiscountsToFile();
		assertTrue(manager.readDiscountsFromFile());
		assertNotNull(manager.getAllCustomerDiscount(code1_public));
		assertNotNull(manager.getAllCustomerDiscount(code2_public));
		assertNotNull(manager.getMemberDiscount(code1_member));
		assertNotNull(manager.getMemberDiscount(code2_member));
	}
	@Test
	public void testReadDiscountsFromFile_empty() {
		File dataFile = new File(testDataFile);
		if(dataFile.exists()){
			dataFile.delete();
		}
		try {
			dataFile.createNewFile();
		} catch (IOException e) {
		}
		assertTrue(manager.readDiscountsFromFile());
		assertNull(manager.getAllCustomerDiscount(code1_public));
		assertNull(manager.getAllCustomerDiscount(code2_public));
		assertNull(manager.getMemberDiscount(code1_member));
		assertNull(manager.getMemberDiscount(code2_member));
	}
	@Test
	public void testReadDiscountsFromFile_fileNotExist() {
		assertFalse(manager.readDiscountsFromFile());
	}
	@Test
	public void testReport_oneMemberDiscount() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(DiscountManager.DATE_FORMAT);
		String dateStr = dateFormat.format(startDate1_member);
		manager.addDiscount(code1_member, descrption1_member, startDate1_member, daysPeriod1_member, percentage1_member, DiscountManager.APPLICABLE_TO_MEMBER_FLAG);
		ArrayList<String> result = manager.report();
		assertEquals(1, result.size());
	    assertEquals(code1_member + "," + descrption1_member + "," + dateStr + "," + daysPeriod1_member + "," + percentage1_member + "," + DiscountManager.APPLICABLE_TO_MEMBER_FLAG, result.get(0));
	}
	@Test
	public void testReport_multiMemberDiscounts() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(DiscountManager.DATE_FORMAT);
		String dateStr1 = dateFormat.format(startDate1_member);
		String dateStr2 = dateFormat.format(startDate2_member);
		manager.addDiscount(code1_member, descrption1_member, startDate1_member, daysPeriod1_member, percentage1_member, DiscountManager.APPLICABLE_TO_MEMBER_FLAG);
		manager.addDiscount(code2_member, descrption2_member, startDate2_member, daysPeriod2_member, percentage2_member, DiscountManager.APPLICABLE_TO_MEMBER_FLAG);
		ArrayList<String> result = manager.report();
		assertEquals(2, result.size());
	    assertEquals(code1_member + "," + descrption1_member + "," + dateStr1 + "," + daysPeriod1_member + "," + percentage1_member + "," + DiscountManager.APPLICABLE_TO_MEMBER_FLAG, result.get(0));
	    assertEquals(code2_member + "," + descrption2_member + "," + dateStr2 + "," + daysPeriod2_member + "," + percentage2_member + "," + DiscountManager.APPLICABLE_TO_MEMBER_FLAG, result.get(1));
	}
	@Test
	public void testReport_oneNonMemberDiscount() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(DiscountManager.DATE_FORMAT);
		String dateStr1 = dateFormat.format(startDate1_public);
		manager.addDiscount(code1_public, descrption1_public, startDate1_public, daysPeriod1_public, percentage1_public, DiscountManager.APPLICABLE_TO_ALL_FLAG);
		ArrayList<String> result = manager.report();
		assertEquals(1, result.size());
	    assertEquals(code1_public + "," + descrption1_public + "," + dateStr1 + "," + daysPeriod1_public + "," + percentage1_public + "," + DiscountManager.APPLICABLE_TO_ALL_FLAG, result.get(0));
	}
	@Test
	public void testReport_multiNonMemberDiscounts() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(DiscountManager.DATE_FORMAT);
		String dateStr1 = dateFormat.format(startDate1_public);
		String dateStr2 = dateFormat.format(startDate2_public);
		manager.addDiscount(code1_public, descrption1_public, startDate1_public, daysPeriod1_public, percentage1_public, DiscountManager.APPLICABLE_TO_ALL_FLAG);
		manager.addDiscount(code2_public, descrption2_public, startDate2_public, daysPeriod2_public, percentage2_public, DiscountManager.APPLICABLE_TO_ALL_FLAG);
		ArrayList<String> result = manager.report();
		assertEquals(2, result.size());
	    assertEquals(code1_public + "," + descrption1_public + "," + dateStr1 + "," + daysPeriod1_public + "," + percentage1_public + "," + DiscountManager.APPLICABLE_TO_ALL_FLAG, result.get(0));
	    assertEquals(code2_public + "," + descrption2_public + "," + dateStr2 + "," + daysPeriod2_public + "," + percentage2_public + "," + DiscountManager.APPLICABLE_TO_ALL_FLAG, result.get(1));
	}
	@Test
	public void testReport_mixedDiscounts() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(DiscountManager.DATE_FORMAT);
		String dateStr1 = dateFormat.format(startDate1_member);
		String dateStr2 = dateFormat.format(startDate2_member);
		String dateStr3 = dateFormat.format(startDate1_public);
		String dateStr4 = dateFormat.format(startDate2_public);
		manager.addDiscount(code1_member, descrption1_member, startDate1_member, daysPeriod1_member, percentage1_member, DiscountManager.APPLICABLE_TO_MEMBER_FLAG);
		manager.addDiscount(code2_member, descrption2_member, startDate2_member, daysPeriod2_member, percentage2_member, DiscountManager.APPLICABLE_TO_MEMBER_FLAG);
		manager.addDiscount(code1_public, descrption1_public, startDate1_public, daysPeriod1_public, percentage1_public, DiscountManager.APPLICABLE_TO_ALL_FLAG);
		manager.addDiscount(code2_public, descrption2_public, startDate2_public, daysPeriod2_public, percentage2_public, DiscountManager.APPLICABLE_TO_ALL_FLAG);
		ArrayList<String> result = manager.report();
		assertEquals(4, result.size());
	    assertEquals(code1_member + "," + descrption1_member + "," + dateStr1 + "," + daysPeriod1_member + "," + percentage1_member + "," + DiscountManager.APPLICABLE_TO_MEMBER_FLAG, result.get(0));
	    assertEquals(code2_member + "," + descrption2_member + "," + dateStr2 + "," + daysPeriod2_member + "," + percentage2_member + "," + DiscountManager.APPLICABLE_TO_MEMBER_FLAG, result.get(1));
	    assertEquals(code1_public + "," + descrption1_public + "," + dateStr3 + "," + daysPeriod1_public + "," + percentage1_public + "," + DiscountManager.APPLICABLE_TO_ALL_FLAG, result.get(2));
	    assertEquals(code2_public + "," + descrption2_public + "," + dateStr4 + "," + daysPeriod2_public + "," + percentage2_public + "," + DiscountManager.APPLICABLE_TO_ALL_FLAG, result.get(3));
	}
	@Test
	public void testReport_empty() {
		ArrayList<String> result = manager.report();
		assertTrue(result.isEmpty());
	}
}
