package iss.nus.edu.sg.SouvinierStore; 

/**
 * Name					Date
 * ---------------------------------
 * Xing Zibo		13.03.2014
 * 
 * @author Xing Zibo 
 * Since 17.03.2014
 * 
 * Modified By Xing Zibo*/

import iss.nus.edu.sg.SouvinierStore.GUI.WindowFrame;
import iss.nus.edu.sg.SouvinierStore.system.Bill;
import iss.nus.edu.sg.SouvinierStore.system.BillException;
import iss.nus.edu.sg.SouvinierStore.system.Category;
import iss.nus.edu.sg.SouvinierStore.system.Customer;
import iss.nus.edu.sg.SouvinierStore.system.IBarCodeReader;
import iss.nus.edu.sg.SouvinierStore.system.ManagerFactory;
import iss.nus.edu.sg.SouvinierStore.system.Member;
import iss.nus.edu.sg.SouvinierStore.system.MemberDiscount;
import iss.nus.edu.sg.SouvinierStore.system.NonMemberDiscount;
import iss.nus.edu.sg.SouvinierStore.system.Product;
import iss.nus.edu.sg.SouvinierStore.system.PurchaseOrder;
import iss.nus.edu.sg.SouvinierStore.system.Transaction;
import iss.nus.edu.sg.SouvinierStore.system.Vendor;

import java.awt.Frame;
import java.util.ArrayList;
import java.util.Date;

public class SouvenirStoreApp
{
	private static SouvenirStoreApp instance = null;
	private Bill currentBill;
	private PurchaseOrder currentReplenishmentOrder;
	private LabelPrinter labelPrinter;
	private ReceiptPrinter receiptPrinter;
	
	private SouvenirStoreApp(){
	}
	
	public static SouvenirStoreApp getInstance(){
		if(instance == null){
			instance = new SouvenirStoreApp();
		}
		return instance;
	}
	/**
	 * read all system data from files, use startup() only once time when Application start.
	 */
	public void startup() {
		updateSystemDataFromFiles();
		currentBill = null;
		currentReplenishmentOrder = null;
	}
	/**
	 * save all system data to files, use shutdown() only once time when Application stop.
	 */
	public void shutdown() {
		updateSystemDataToFiles();
		currentBill = null;
		currentReplenishmentOrder = null;
		System.exit(0);
	}
	
	public boolean updateSystemDataFromFiles() {
		boolean isSuccess = true;
		isSuccess &= ManagerFactory.getCustomerManager().readMembersFromFile();
		isSuccess &= ManagerFactory.getCategoryManager().readCategoriesFromFile();
		isSuccess &= ManagerFactory.getProductManager().readProductsFromFile();
		isSuccess &= ManagerFactory.getDiscountManager().readDiscountsFromFile();
		isSuccess &= ManagerFactory.getVendorManager().readVendorsFromFile();
		isSuccess &= ManagerFactory.getTransactionManager().readTransactionFromFile();
		isSuccess &= ManagerFactory.getStoreKeeperManager().readStoreKeepersFromFile();
		return isSuccess;
	}
	
	public boolean updateSystemDataToFiles() {
		boolean isSuccess = true;
		isSuccess &= ManagerFactory.getCustomerManager().saveMembersToFile();
		isSuccess &= ManagerFactory.getCategoryManager().saveCategoriesToFile();
		isSuccess &= ManagerFactory.getProductManager().saveProductsToFile();
		isSuccess &= ManagerFactory.getDiscountManager().saveDiscountsToFile();
		isSuccess &= ManagerFactory.getVendorManager().saveVendorsToFile();
		isSuccess &= ManagerFactory.getTransactionManager().saveTransactionToFile();
		return isSuccess;
	}
	
	public boolean login(String loginName, String password) {
		if(loginName == null || loginName.isEmpty() || password == null || password.isEmpty()){
			return false;
		}
		//TODO: waiting Ram's code
		return(ManagerFactory.getStoreKeeperManager().login(loginName, password));
	}
	public LabelPrinter getLabelPrinter(Frame parent){
		if(labelPrinter == null){
			labelPrinter = new LabelPrinter(parent);
		}
		return labelPrinter;
	}
	public ReceiptPrinter getReceiptPrinter(Frame parent){
		if(receiptPrinter == null){
			receiptPrinter = new ReceiptPrinter(parent);
		}
		return receiptPrinter;
	}
	/**
	 * 
	 * @param name
	 * @param memberId
	 * @param points assign -1 to new member
	 * @return
	 */
	public boolean registerMember(String name, String memberId) {
		if(name == null || name.isEmpty() || memberId == null || memberId.isEmpty()){
			return false;
		}
		return ManagerFactory.getCustomerManager().addMember(name, memberId, -1);
	}
	
	public boolean deleteMember(String memberId) {
		if(memberId == null || memberId.isEmpty()){
			return false;
		}
		return ManagerFactory.getCustomerManager().deleteMember(memberId);
	}
	
	public ArrayList<Member> getMemberList() {
		return ManagerFactory.getCustomerManager().getMembers();
	}
	/**
	 * Function to read the Bar Code of member card from the barcode reader, then return the Member.
	 * @param barcodeReader
	 * @return return null - member not found
	 */
	public Member scanMemberBarCode(IBarCodeReader barcodeReader){
		return ManagerFactory.getCustomerManager().readMemberFromBarCode(barcodeReader);
	}
	public boolean newProductEntry(String categoryCode, String productName, String brief, int quantityAvail, double price, String barCode, int threshold, int orderQuantity) {
		if(categoryCode == null || categoryCode.isEmpty() || productName == null || productName.isEmpty() 
				|| brief == null || brief.isEmpty() || quantityAvail < 0 || price < 0 || threshold < 0){
			return false;
		}
		Category category = ManagerFactory.getCategoryManager().getCategories(categoryCode);
		return ManagerFactory.getProductManager().addProduct(category, productName, brief, quantityAvail, price, barCode, threshold, orderQuantity);
	}
	
	public boolean deleteProductEntry(String productId) {
		if(productId == null || productId.isEmpty()){
			return false;
		}
		return ManagerFactory.getProductManager().deleteProduct(productId);
	}
	
	public ArrayList<Product> checkInventory() {
		return ManagerFactory.getProductManager().getProducts();
	}
	/**
	 * 
	 * @param categoryCode
	 * @return return empty ArrayList<Product> when it's failed
	 */
	public ArrayList<Product> checkInventory(String categoryCode) {
		ArrayList<Product> products = new ArrayList<Product>();
		if(categoryCode == null || categoryCode.isEmpty()){
			return products;
		}
		Category category = ManagerFactory.getCategoryManager().getCategories(categoryCode);
		if(category != null){
			return ManagerFactory.getProductManager().getProducts(category);
		}else{
			return products;
		}
	}
	/**
	 * Function to read the Bar Code of any product from the barcode reader, then return the product.
	 * @param barcodeReader
	 * @return return null - product not found
	 */
	public Product scanProductBarCode(IBarCodeReader barcodeReader){
		return ManagerFactory.getProductManager().readProductFromBarCode(barcodeReader);
	}
	public boolean addCategory(String categoryCode, String categoryName) {
		if(categoryCode == null || categoryCode.isEmpty() || categoryName == null || categoryName.isEmpty()){
			return false;
		}
		return ManagerFactory.getCategoryManager().addCategory(categoryCode, categoryName);
	}
	
	public boolean deleteCategory(String categoryCode) {
		if(categoryCode == null || categoryCode.isEmpty()){
			return false;
		}
		Category category = ManagerFactory.getCategoryManager().getCategories(categoryCode);
		if(category == null){
			return false;
		}
		return ManagerFactory.getCategoryManager().deleteCategory(category);
	}
	/**
	 * 
	 * @param categoryCode
	 * @return return empty ArrayList<Vendor> if failed
	 */
	public ArrayList<Vendor> getVendorsForCertainCategory(String categoryCode) {
		ArrayList<Vendor> vendors = new ArrayList<Vendor>();
		if(categoryCode == null || categoryCode.isEmpty()){
			return vendors;
		}
		Category category = ManagerFactory.getCategoryManager().getCategories(categoryCode);
		if(category == null){
			return vendors;
		}
		return ManagerFactory.getVendorManager().getVendors(category);
	}
	public boolean deleteVendor(Category category, Vendor vendor) {
		if(category == null || vendor == null) {
			return false;
		}
		return ManagerFactory.getVendorManager().deleteVendor(category, vendor);
	}
	public boolean addVendor(Category category, Vendor vendor) {
		if(category == null || vendor == null) {
			return false;
		}
		return ManagerFactory.getVendorManager().addVendor(category, vendor);
	}
	public ArrayList<Category> getCategories() {
		return ManagerFactory.getCategoryManager().getCategories();
	}
	//TODO:
	public boolean addStockReplenishmentOrder(String productId, String vendorId, int quaitity) {
		return true;
	}
	//TODO:
	public boolean deleteStorkReplenishmentOrder(String productId, String vendorId) {
		return true;
	}
	//TODO:
	public PurchaseOrder getStorkReplenishmentOrder() {
		return currentReplenishmentOrder;
	}
	//TODO:
	public boolean cancelStorkReplenishment() {
		return true;
	
	}
	//TODO:
	public boolean submitStorkReplenishment() {
		return true;
	}
	
	public boolean validateMember(String memberId) {
		if(ManagerFactory.getCustomerManager().getCustomer(memberId) != null) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean createNewBill(String customId){
		if(customId == null || customId.isEmpty()){
			return false;
		}
		Customer customer = ManagerFactory.getCustomerManager().getCustomer(customId);
		if(customer == null){
			return false;
		}
		try {
			currentBill = new Bill(customer);
		} catch (BillException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
	
	public boolean validateProductExist(String productId) {
		boolean ifSuccess = false;
		if(ManagerFactory.getProductManager().getProduct(productId) != null) {
			ifSuccess = true;
		}
		return ifSuccess;
	}
	/**
	 * use createNewBill(String customId) before add product to bill
	 * @param productId
	 * @param quaitity
	 * @return
	 * @throws BillException 
	 */
	public boolean addProductToBill(String productId, int quantity) throws BillException {
		if(currentBill == null || productId == null || productId.isEmpty() || quantity <= 0){
			return false;
		}
		Product product = ManagerFactory.getProductManager().getProduct(productId);
		if(product == null){
			return false;
		}
		return currentBill.addBillItem(product, quantity);
	}
	
	public boolean deleteProductFromBill(String productId) {
		if(currentBill == null || productId == null || productId.isEmpty()){
			return false;
		}
		Product product = ManagerFactory.getProductManager().getProduct(productId);
		if(product == null){
			return false;
		}
		return currentBill.deleteBillItem(product);
	}
	
	public Bill getProductBill() {
		return currentBill;
	}
	
	public boolean cancelProductBill() {
		currentBill = null;
		return true;
	}
	/**
	 * 
	 * @param amount
	 * @param points
	 * @return
	 * @throws BillException 
	 */
	public double makePayment(double amount, int points) throws BillException {
		if(currentBill == null || amount < 0 || points < 0){
			throw new BillException("Bill System error");
		}
		double change = 0.0;
		try {
			change = currentBill.payment(amount, points);
		} catch (BillException e) {
			throw e;
		}
		// bill will save it into transaction, avoid duplication. 26-Mar-2014, Xu Lei.
		//saveBillToTransactionLog();
		return change;
	}
	
	private boolean saveBillToTransactionLog() {
		if(currentBill == null){
			return false;
		}
		return ManagerFactory.getTransactionManager().addTransaction(currentBill, new Date(System.currentTimeMillis()));
	}
	
	/*modified by.... Chandrakala*/
	public ArrayList<Transaction> getTransactions(Date startDate, Date endDate) {
		return ManagerFactory.getTransactionManager().getTransactions(startDate, endDate);
	}
	public boolean offerDiscounts(String code, String descrption, Date startDate, int daysPeriod, int percentage, char applicable) {
		if(code == null || code.isEmpty() || descrption == null || descrption.isEmpty()){
			return false;
		}
		return ManagerFactory.getDiscountManager().addDiscount(code, descrption, startDate, daysPeriod, percentage, applicable);
	}
	/**
	 * 
	 * @return return empty ArrayList<MemberDiscount> if it's failed
	 */
	public ArrayList<MemberDiscount> getMemberDisconts() {
		return ManagerFactory.getDiscountManager().getMemberDiscounts();
	}
	
	public ArrayList<NonMemberDiscount> getAllCustomerDisconts() {
		return ManagerFactory.getDiscountManager().getAllCustomerDiscounts();
	}
	
	public String getCurrentStoreKeeper() {
		return ManagerFactory.getStoreKeeperManager().getCurrentStoreKeeper().getName();
	}
	
	public ArrayList<String> getCategoriesReporting() {
		return ManagerFactory.getCategoryManager().report();
	}
	
	public ArrayList<String> getProductsReporting() {
    	return ManagerFactory.getProductManager().report();
	}
	
	public ArrayList<String> getTransactionsReporting() {
		return ManagerFactory.getTransactionManager().report();
	}
	
	public ArrayList<String> getMembersReporting() {
		return ManagerFactory.getCustomerManager().report();
	}
	
	// just for test temporarily, main() should be in the app. 18-Mar-2014, Xu Lei.
	public static void main(String[] args) {
		WindowFrame wf = new WindowFrame(SouvenirStoreApp.getInstance());
		SouvenirStoreApp.getInstance().startup();
		wf.setVisible(true);
	}
}
