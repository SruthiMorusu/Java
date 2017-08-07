package iss.nus.edu.sg.SouvinierStore.test;

/**
 * Name					Date
 * ---------------------------------
 * Sruthi 				29.03.2014
 * Sruthi 				30.03.2014
 * Sruthi				02.04.2014
 * 
 * @author Sruthi
 * Since 29.03.2013
 * 
 * Modified By Sruthi*/

import iss.nus.edu.sg.SouvinierStore.SouvenirStoreApp;
import iss.nus.edu.sg.SouvinierStore.system.Bill;
import iss.nus.edu.sg.SouvinierStore.system.BillException;
import iss.nus.edu.sg.SouvinierStore.system.BillItem;
import iss.nus.edu.sg.SouvinierStore.system.CustomerManager;
import iss.nus.edu.sg.SouvinierStore.system.ManagerFactory;
import iss.nus.edu.sg.SouvinierStore.system.Member;
import iss.nus.edu.sg.SouvinierStore.system.NonMember;
import iss.nus.edu.sg.SouvinierStore.system.Product;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BillTest {
	
	Bill bill; 
	SouvenirStoreApp souvenirStoreApp;

	@Before
	public void startup() {
		souvenirStoreApp = SouvenirStoreApp.getInstance();
		souvenirStoreApp.startup();
	}
	
	@After
	public void end() {
//		souvenirStoreApp.shutdown();
		bill = null;
	}
	
	@Test
	public void billConstructorTest_null() {
		try {
			bill = new Bill(null);
			if(bill.getCustomer() instanceof Member) {
				System.out.println("bill customer id: " + ((Member)bill.getCustomer()).getName());
			} else if (bill.getCustomer() instanceof NonMember) {
				System.out.println("bill customer id: " + ((NonMember)bill.getCustomer()).getId());
			} else {
				fail("billItem constructor test failed !");
			}
		} catch (BillException e) {
			System.out.println("Create bill error. Error Message: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			fail("billItem constructor test failed !");
		}
	}
	
	@Test
	public void billConstructorTest_customer_null() {
		try {
			bill = new Bill(ManagerFactory.getCustomerManager().getCustomer(null));
			if(bill.getCustomer() instanceof Member) {
				System.out.println("bill customer id: " + ((Member)bill.getCustomer()).getName());
			} else if (bill.getCustomer() instanceof NonMember) {
				System.out.println("bill customer id: " + ((NonMember)bill.getCustomer()).getId());
			} else {
				fail("billItem constructor test failed !");
			}
		} catch (BillException e) {
			System.out.println("Create bill error. Error Message: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			fail("billItem constructor test failed !");
		}
	}
	
	@Test
	public void billConstructorTest_customer_empty() {
		try {
			bill = new Bill(ManagerFactory.getCustomerManager().getCustomer(""));
			if(bill.getCustomer() instanceof Member) {
				System.out.println("bill customer id: " + ((Member)bill.getCustomer()).getName());
			} else if (bill.getCustomer() instanceof NonMember) {
				System.out.println("bill customer id: " + ((NonMember)bill.getCustomer()).getId());
			} else {
				fail("billItem constructor test failed !");
			}
		} catch (BillException e) {
			System.out.println("Create bill error. Error Message: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			fail("billItem constructor test failed !");
		}
	}
	
	@Test
	public void billConstructorTest_customer_public() {
		try {
			bill = new Bill(ManagerFactory.getCustomerManager().getCustomer(CustomerManager.NON_MEMBER_ID));
			if(bill.getCustomer() instanceof Member) {
				System.out.println("bill customer id: " + ((Member)bill.getCustomer()).getName());
			} else if (bill.getCustomer() instanceof NonMember) {
				System.out.println("bill customer id: " + ((NonMember)bill.getCustomer()).getId());
			} else {
				fail("billItem constructor test failed !");
			}
		} catch (BillException e) {
			System.out.println("Create bill error. Error Message: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			fail("billItem constructor test failed !");
		}
	}
	
	@Test
	public void billConstructorTest_customer_member() {
		try {
			bill = new Bill(ManagerFactory.getCustomerManager().getCustomer("B8274892357"));
			if(bill.getCustomer() instanceof Member) {
				System.out.println("bill customer id: " + ((Member)bill.getCustomer()).getName());
			} else if (bill.getCustomer() instanceof NonMember) {
				System.out.println("bill customer id: " + ((NonMember)bill.getCustomer()).getId());
			} else {
				fail("billItem constructor test failed !");
			}	
		} catch (BillException e) {
			System.out.println("Create bill error. Error Message: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			fail("billItem constructor test failed !");
		}
	}
	
	@Test
	public void addBillItemTest() {
		try {
			bill = new Bill(ManagerFactory.getCustomerManager().getCustomer(CustomerManager.NON_MEMBER_ID));
			assertTrue(bill.addBillItem(ManagerFactory.getProductManager().getProduct("CLO/1"), 1));
			assertTrue(bill.addBillItem(ManagerFactory.getProductManager().getProduct("MUG/1"), 1));
		} catch (BillException e) {
			System.out.println("addBill item error. Error Message: " + e.getMessage());
		} catch (Exception e) {
			fail("addBillItem test failed !");
		}
	}
	
	@Test
	public void addBillItemTest_illegal() {
		try {
			bill = new Bill(ManagerFactory.getCustomerManager().getCustomer(CustomerManager.NON_MEMBER_ID));
			assertFalse(bill.addBillItem(ManagerFactory.getProductManager().getProduct(""), 1));
			assertFalse(bill.addBillItem(ManagerFactory.getProductManager().getProduct("MUG/1"), -1));
		} catch (BillException e) {
			System.out.println("addBill item error. Error Message: " + e.getMessage());
		} catch (Exception e) {
			fail("addBillItem test failed !");
		}
	}
	
	@Test
	public void addBillItemTest_null() {
		try {
			bill = new Bill(ManagerFactory.getCustomerManager().getCustomer(CustomerManager.NON_MEMBER_ID));
			assertFalse(bill.addBillItem(null, 1));
		} catch (BillException e) {
			System.out.println("addBill item error. Error Message: " + e.getMessage());
		} catch (Exception e) {
			fail("addBillItem test failed !");
		}
	}
	
	@Test
	public void deleteBillItemTest() {
		try {
			bill = new Bill(ManagerFactory.getCustomerManager().getCustomer(CustomerManager.NON_MEMBER_ID));
			try {
				bill.addBillItem(ManagerFactory.getProductManager().getProduct("CLO/1"), 1);
				bill.addBillItem(ManagerFactory.getProductManager().getProduct("MUG/1"), 1);
				bill.addBillItem(ManagerFactory.getProductManager().getProduct("STA/1"), 1);
			} catch (BillException e) {
				System.out.println("addBill item error. Error Message: " + e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				fail("addBillItem test failed !");
			}
			try {
				assertTrue(bill.deleteBillItem(ManagerFactory.getProductManager().getProduct("MUG/1")));
			} catch (Exception e) {
				e.printStackTrace();
				fail("delete billItem test failed !");
			}
		} catch (BillException e) {
			System.out.println("delete billItem error. Error Message: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			fail("delete billItem test failed !");
		} 
	}
	
	@Test
	public void deleteBillItemTest_illegal() {
		try {
			bill = new Bill(ManagerFactory.getCustomerManager().getCustomer(CustomerManager.NON_MEMBER_ID));
			try {
				bill.addBillItem(ManagerFactory.getProductManager().getProduct("CLO/1"), 1);
				bill.addBillItem(ManagerFactory.getProductManager().getProduct("MUG/1"), 1);
				bill.addBillItem(ManagerFactory.getProductManager().getProduct("STA/1"), 1);
			} catch (BillException e) {
				System.out.println("addBill item error. Error Message: " + e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				fail("addBillItem test failed !");
			}
			try {
				assertFalse(bill.deleteBillItem(new BillItem(new Product(),1)));
			} catch (Exception e) {
				e.printStackTrace();
				fail("delete billItem test failed !");
			}
		} catch (BillException e) {
			System.out.println("delete billItem error. Error Message: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			fail("delete billItem test failed !");
		}
	}
	
	@Test
	public void deleteBillItemTest_null() {
		try {
			bill = new Bill(ManagerFactory.getCustomerManager().getCustomer(CustomerManager.NON_MEMBER_ID));
			try {
				bill.addBillItem(ManagerFactory.getProductManager().getProduct("CLO/1"), 1);
				bill.addBillItem(ManagerFactory.getProductManager().getProduct("MUG/1"), 1);
				bill.addBillItem(ManagerFactory.getProductManager().getProduct("STA/1"), 1);
			} catch (BillException e) {
				System.out.println("addBill item error. Error Message: " + e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				fail("addBillItem test failed !");
			}
			BillItem billItem = null;
			try {
				assertFalse(bill.deleteBillItem(billItem));
			} catch (Exception e) {
				e.printStackTrace();
				fail("delete billItem test failed !");
			}
		} catch (BillException e) {
			System.out.println("delete billItem error. Error Message: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			fail("delete billItem test failed !");
		}
	}
	
	@Test
	public void getEReceiptTest_before_payment() {
		StringBuilder strB = null;
		try {
			bill = new Bill(ManagerFactory.getCustomerManager().getCustomer(CustomerManager.NON_MEMBER_ID));
			try {
				bill.addBillItem(ManagerFactory.getProductManager().getProduct("CLO/1"), 1);
				bill.addBillItem(ManagerFactory.getProductManager().getProduct("MUG/1"), 1);
			} catch (BillException e) {
				System.out.println("addBill item error. Error Message: " + e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				fail("addBillItem test failed !");
			}	
			strB = new StringBuilder("Bill: " + "\n");
			strB.append("customer id: " + bill.getCustomer().getId() + "\n");
			for(BillItem b : bill.getBillItems()) {
				strB.append("bill item: " + b.getProduct().getName() + ", " + b.getQuantity() + "\n");
			}
			strB.append("total price: " + bill.getTotalPrice() + "\n");
			strB.append("discount: " + bill.getDiscount() + "\n");
			strB.append("price after discount: " + bill.getTotalPriceAfterDiscount() + "\n");
			strB.append("receipt: " + bill.getEReceipt());
		} catch (BillException e) {
			System.out.println("get receipt error. Error Message: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			fail("get receipt test failed !");
		} finally {
			System.out.println(strB.toString());
		}
	}
	
	@Test
	public void getEReceiptTest_after_payment() {
		StringBuilder strB = null;
		try {
			bill = new Bill(ManagerFactory.getCustomerManager().getCustomer(CustomerManager.NON_MEMBER_ID));
			try {
				bill.addBillItem(ManagerFactory.getProductManager().getProduct("CLO/1"), 1);
				bill.addBillItem(ManagerFactory.getProductManager().getProduct("MUG/1"), 1);
				bill.payment(20, 0);
			} catch (BillException e) {
				System.out.println("addBill item error. Error Message: " + e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				fail("addBillItem test failed !");
			}	
			strB = new StringBuilder("Bill: " + "\n");
			strB.append("customer id: " + bill.getCustomer().getId() + "\n");
			for(BillItem b : bill.getBillItems()) {
				strB.append("bill item: " + b.getProduct().getName() + ", " + b.getQuantity() + "\n");
			}
			strB.append("total price: " + bill.getTotalPrice() + "\n");
			strB.append("discount: " + bill.getDiscount() + "\n");
			strB.append("price after discount: " + bill.getTotalPriceAfterDiscount() + "\n");
			strB.append("receipt: " + bill.getEReceipt());
		} catch (BillException e) {
			System.out.println("get receipt error. Error Message: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			fail("get receipt test failed !");
		} finally {
			System.out.println(strB.toString());
		}
	}
	
	@Test
	public void getEReceiptTest_empty() {
		StringBuilder strB = null;
		try {
			bill = new Bill(ManagerFactory.getCustomerManager().getCustomer(CustomerManager.NON_MEMBER_ID));	
			strB = new StringBuilder("Bill: " + "\n");
			strB.append("customer id: " + bill.getCustomer().getId() + "\n");
			for(BillItem b : bill.getBillItems()) {
				strB.append("bill item: " + b.getProduct().getName() + ", " + b.getQuantity() + "\n");
			}
			strB.append("total price: " + bill.getTotalPrice() + "\n");
			strB.append("discount: " + bill.getDiscount() + "\n");
			strB.append("price after discount: " + bill.getTotalPriceAfterDiscount() + "\n");
			strB.append("receipt: " + bill.getEReceipt());
		} catch (BillException e) {
			System.out.println("get receipt error. Error Message: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			fail("get receipt test failed !");
		} finally {
			System.out.println(strB.toString());
		}
	}
	
	@Test
	public void getBillItemTest() {
		try {
			bill = new Bill(ManagerFactory.getCustomerManager().getCustomer(CustomerManager.NON_MEMBER_ID));
			try {
				bill.addBillItem(ManagerFactory.getProductManager().getProduct("CLO/1"), 1);
				bill.addBillItem(ManagerFactory.getProductManager().getProduct("MUG/1"), 1);
				bill.addBillItem(ManagerFactory.getProductManager().getProduct("STA/1"), 1);
			} catch (BillException e) {
				System.out.println("addBill item error. Error Message: " + e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				fail("addBillItem test failed !");
			}
			try {
				assertNotNull(bill.getBillItem(ManagerFactory.getProductManager().getProduct("CLO/1")));
			} catch (Exception e) {
				e.printStackTrace();
				fail("getBillItem test failed !");
			}
		} catch (BillException e) {
			System.out.println("getBillItem error. Error Message: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			fail("getBillItem test failed !");
		}
	}
	
	@Test
	public void getBillItemTest_illegal() {
		try {
			bill = new Bill(ManagerFactory.getCustomerManager().getCustomer(CustomerManager.NON_MEMBER_ID));
			try {
				bill.addBillItem(ManagerFactory.getProductManager().getProduct("CLO/1"), 1);
				bill.addBillItem(ManagerFactory.getProductManager().getProduct("MUG/1"), 1);
				bill.addBillItem(ManagerFactory.getProductManager().getProduct("STA/1"), 1);
			} catch (BillException e) {
				System.out.println("addBill item error. Error Message: " + e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				fail("addBillItem test failed !");
			}
			try {
				assertNull(bill.getBillItem(new Product()));
			} catch (Exception e) {
				e.printStackTrace();
				fail("getBillItem test failed !");
			}
		} catch (BillException e) {
			System.out.println("getBillItem error. Error Message: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			fail("getBillItem test failed !");
		}
	}
	
	@Test
	public void getBillItemTest_null() {
		try {
			bill = new Bill(ManagerFactory.getCustomerManager().getCustomer(CustomerManager.NON_MEMBER_ID));
			try {
				bill.addBillItem(ManagerFactory.getProductManager().getProduct("CLO/1"), 1);
				bill.addBillItem(ManagerFactory.getProductManager().getProduct("MUG/1"), 1);
				bill.addBillItem(ManagerFactory.getProductManager().getProduct("STA/1"), 1);
			} catch (BillException e) {
				System.out.println("addBill item error. Error Message: " + e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				fail("addBillItem test failed !");
			}
			Product product = null;
			try {
				assertNull(bill.getBillItem(product));
			} catch (Exception e) {
				e.printStackTrace();
				fail("getBillItem test failed !");
			}
		} catch (BillException e) {
			System.out.println("getBillItem error. Error Message: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			fail("getBillItem test failed !");
		}
	}
	
	@Test
	public void memberPaymentTest_illegal() {
		try {
			bill = new Bill(ManagerFactory.getCustomerManager().getCustomer("B8274892357"));
			try {
				bill.addBillItem(ManagerFactory.getProductManager().getProduct("CLO/1"), 1);
				bill.addBillItem(ManagerFactory.getProductManager().getProduct("MUG/1"), 1);
			} catch (BillException e) {
				System.out.println(e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				fail("addBillItem test failed !");
			}	
			try {
				// 1. any one < 0.
				bill.payment(0, -1);	
			} catch (BillException e1) {
				System.out.println(e1.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				fail("amount one < 0 payment test failed !");
			}
		} catch (BillException e) {
			System.out.println("memberPaymentTest_illegal error. Error Message: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			fail("memberPaymentTest_illegal test failed !");
		}
	}
	
	@Test
	public void memberPaymentTest_amount_or_points_not_enough_for_pay() {
		try {
			bill = new Bill(ManagerFactory.getCustomerManager().getCustomer("B8274892357"));
			try {
				bill.addBillItem(ManagerFactory.getProductManager().getProduct("CLO/1"), 1);
				bill.addBillItem(ManagerFactory.getProductManager().getProduct("MUG/1"), 1);
			} catch (BillException e) {
				System.out.println(e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				fail("addBillItem test failed !");
			}	
			try {
				// 2. amount or points not enough for payment.
				bill.payment(1, 1);
			} catch (BillException e2) {
				System.out.println(e2.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				fail("amount or points not enough payment test failed !");
			}
		} catch (BillException e) {
			System.out.println("memberPaymentTest_amount_or_points_not_enough_for_pay error. Error Message: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			fail("memberPaymentTest_amount_or_points_not_enough_for_pay test failed !");
		}
	}
	
	@Test
	public void memberPaymentTest_loyal_points_not_enough() {
		try {
			bill = new Bill(ManagerFactory.getCustomerManager().getCustomer("B8274892357"));
			try {
				bill.addBillItem(ManagerFactory.getProductManager().getProduct("CLO/1"), 1);
				bill.addBillItem(ManagerFactory.getProductManager().getProduct("MUG/1"), 1);
			} catch (BillException e) {
				System.out.println(e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				fail("addBillItem test failed !");
			}	
			try {
				// 3. loyal points of the member not enough.
				bill.payment(0, 5000);
			} catch (BillException e3) {
				System.out.println(e3.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				fail("loyal points of the member not enough payment test failed !");
			}
		} catch (BillException e) {
			System.out.println("memberPaymentTest_loyal_points_not_enough error. Error Message: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			fail("memberPaymentTest_loyal_points_not_enough test failed !");
		}
	}
	
	@Test
	public void memberPaymentTest_redeem_more_than_payment() {
		try {
			bill = new Bill(ManagerFactory.getCustomerManager().getCustomer("B8274892357"));
			try {
				bill.addBillItem(ManagerFactory.getProductManager().getProduct("CLO/1"), 1);
				bill.addBillItem(ManagerFactory.getProductManager().getProduct("MUG/1"), 1);
			} catch (BillException e) {
				System.out.println(e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				fail("addBillItem test failed !");
			}	
			try {
				// 4. redeem more than payment, only redeem for payment cannot return cash.
				bill.payment(0, 1900);
			} catch (BillException e4) {
				System.out.println(e4.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				fail("redeem more than payment test failed !");
			}	
		} catch (BillException e) {
			System.out.println("memberPaymentTest_redeem_more_than_payment error. Error Message: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			fail("memberPaymentTest_redeem_more_than_payment test failed !");
		}
	}
	
	@Test
	public void memberPaymentTest_enough() {
		try {
			bill = new Bill(ManagerFactory.getCustomerManager().getCustomer("B8274892357"));
			try {
				bill.addBillItem(ManagerFactory.getProductManager().getProduct("CLO/1"), 1);
				bill.addBillItem(ManagerFactory.getProductManager().getProduct("MUG/1"), 1);
			} catch (BillException e) {
				System.out.println(e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				fail("addBillItem test failed !");
			}	
			try {
				// 5. enough for payment.
				bill.payment(10, 10);
			} catch (BillException e5) {
				System.out.println(e5.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				fail("amount enough payment test failed !");
			}
		} catch (BillException e) {
			System.out.println("memberPaymentTest_enough error. Error Message: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			fail("memberPaymentTest_enough test failed !");
		}
	}
	
	@Test
	public void publicPaymentTest_illegal() {
		try {
			bill = new Bill(ManagerFactory.getCustomerManager().getCustomer(CustomerManager.NON_MEMBER_ID));
			try {
				bill.addBillItem(ManagerFactory.getProductManager().getProduct("CLO/1"), 1);
				bill.addBillItem(ManagerFactory.getProductManager().getProduct("MUG/1"), 1);
			} catch (BillException e) {
				System.out.println(e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				fail("addBillItem test failed !");
			}	
			try {
				// 1. amount < 0.
				bill.payment(-1, 0);	
			} catch (BillException e1) {
				System.out.println(e1.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				fail("amount < 0 payment test failed !");
			}	
		} catch (BillException e) {
			System.out.println("publicPaymentTest_illegal error. Error Message: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			fail("publicPaymentTest_illegal test failed !");
		}
	}
	
	@Test
	public void publicPaymentTest_amount_not_enough() {
		try {
			bill = new Bill(ManagerFactory.getCustomerManager().getCustomer(CustomerManager.NON_MEMBER_ID));
			try {
				bill.addBillItem(ManagerFactory.getProductManager().getProduct("CLO/1"), 1);
				bill.addBillItem(ManagerFactory.getProductManager().getProduct("MUG/1"), 1);
			} catch (BillException e) {
				System.out.println(e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				fail("addBillItem test failed !");
			}	
			try {
				// 2. amount not enough.
				bill.payment(1, 10);
			} catch (BillException e2) {
				System.out.println(e2.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				fail("amount not enough payment test failed !");
			}
		} catch (BillException e) {
			System.out.println("publicPaymentTest_amount_not_enough error. Error Message: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			fail("publicPaymentTest_amount_not_enough test failed !");
		}
	}
	
	@Test
	public void publicPaymentTest_amount_enough() {
		try {
			bill = new Bill(ManagerFactory.getCustomerManager().getCustomer(CustomerManager.NON_MEMBER_ID));
			try {
				bill.addBillItem(ManagerFactory.getProductManager().getProduct("CLO/1"), 1);
				bill.addBillItem(ManagerFactory.getProductManager().getProduct("MUG/1"), 1);
			} catch (BillException e) {
				System.out.println(e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				fail("addBillItem test failed !");
			}	
			try {
				// 3. amount enough.
				bill.payment(10, 10);
			} catch (BillException e3) {
				System.out.println(e3.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				fail("amount enough payment test failed !");
			}	
		} catch (BillException e) {
			System.out.println("publicPaymentTest_amount_enough error. Error Message: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			fail("publicPaymentTest_amount_enough test failed !");
		}
	}

}
