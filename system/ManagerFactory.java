package iss.nus.edu.sg.SouvinierStore.system;

/**
 * Name					Date
 * ---------------------------------
 * Sruthi 				18.03.2014
 * 
 * @author Sruthi
 * Since 18.03.2014
 * 
 * Modified By Sruthi*/

public class ManagerFactory {
	
	private static VendorManager vendorManager;

	private static CategoryManager categoryManager;

	private static ProductManager productManager;

	private static TransactionManager transactionManager;
	
	// we use CustomerManager instead of MemberManager.  Xu Lei, 16-Mar-2014.
	private static CustomerManager customerManager;

	private static DiscountManager discountManager;

	private static StoreKeeperManager storeKeeperManager;

	public static VendorManager getVendorManager() {
		if(vendorManager == null) {
			vendorManager = new VendorManager();
		}
		return vendorManager;
	}

	public static CategoryManager getCategoryManager() {
		if(categoryManager == null) {
			categoryManager = new CategoryManager();
		}
		return categoryManager;
	}

	public static ProductManager getProductManager() {
		if(productManager == null) {
			productManager = new ProductManager();
		}
		return productManager;
	}

	public static TransactionManager getTransactionManager() {
		if(transactionManager == null) {
			transactionManager = new TransactionManager();
		}
		return transactionManager;
	}

	public static CustomerManager getCustomerManager() {
		if(customerManager == null) {
			customerManager = new CustomerManager();
		}
		return customerManager;
	}

	public static DiscountManager getDiscountManager() {
		if(discountManager == null) {
			discountManager = new DiscountManager();
		}
		return discountManager;
	}

	public static StoreKeeperManager getStoreKeeperManager() {
		if(storeKeeperManager == null) {
			storeKeeperManager = new StoreKeeperManager();
		}
		return storeKeeperManager;
	}

}
