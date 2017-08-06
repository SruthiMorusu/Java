package iss.nus.edu.sg.SouvinierStore.system; 

/**
 * Name					Date
 * ---------------------------------
 * Karthik 				11.03.2014
 * 
 * Author Sruthi
 * Since 11.03.2014
 * 
 * Modified By Karthik*/

import java.io.File;
import java.util.ArrayList;

public class ProductManager implements IReport
{
	public static final String PRODUCT_DATA_FILE = "data/Products.dat";
	private ArrayList<Product> products;
	private String dataFile = PRODUCT_DATA_FILE;
	
	public ProductManager() {
		products = new ArrayList<Product>();
	}
	public boolean setDataFile(String fileNameWithPath){
		if(fileNameWithPath == null || fileNameWithPath.isEmpty()){
			return false;
		}
		dataFile = fileNameWithPath;
		return true;
	}
	/**
	 * @return the products list
	 */
	public ArrayList<Product> getProducts() {
		return products;
	}

	/**
	 * @param products the products to set
	 */
	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}

	/**
	 * Function that adds a required product
	 * 
	 * @param productName
	 * @param discription
	 * @param quantity
	 * @param price
	 * @param barCode
	 * @param recordQuantity
	 * @param orderQuantity
	 */
	public boolean addProduct(Category category, String productName, String description, int quantity, double price, String barCode, int recordQuantity, int orderQuantity)
	{
		Product tempProd = new Product();
		//check for existing product in file
		for(Product tempVal : products){
			if(tempVal.getName().trim().equalsIgnoreCase(productName)){
				return false;
			}
		}
		//validate the inputs
		boolean validationResult = validateProduct(productName, description, quantity, price, barCode, recordQuantity);
		if(!validationResult){
			return false;
		}
		//generate the ID and set the parameters
		generateID(category, tempProd);
		tempProd.setName(productName);
		tempProd.setDescription(description);
		tempProd.setQuantityAvail(quantity);
		tempProd.setPrice(price);
		tempProd.setBarCode(barCode);
		tempProd.setThreshold(recordQuantity);
		tempProd.setOrderQuantity(orderQuantity);
		tempProd.setCategory(category);
		products.add(tempProd);
		//save product object to file
		return saveProductsToFile();
	}
	
	/**
	 * Function to validate the inputs
	 * 
	 * @param productName
	 * @param description
	 * @param quantity
	 * @param price
	 * @param barCode
	 * @param recordQuantity
	 * @return
	 */
	private boolean validateProduct(String productName, String description, int quantity, double price, String barCode, int recordQuantity) {
		if(productName == null || description == null || barCode == null){
			return false;
		}		
		return true;
	}

	/**
	 * Get the ID to be assignmend to the product
	 * 
	 * @param categoryEntered
	 * @param productToAdd
	 */
	private void generateID(Category categoryEntered, Product productToAdd) {
		int countProd = 0;
		for(Product prodItem : products){
			//compare the category codes and generate the id based on that
			if(categoryEntered.getCategoryCode().equalsIgnoreCase(prodItem.getCategory().getCategoryCode())){
				countProd++;
			}
		}	
		productToAdd.setId(categoryEntered.getCategoryCode()+"/"+Integer.toString(countProd+1));
	}

	/**
	 * Function to delete a product
	 * 
	 * @param productId
	 */
	public boolean deleteProduct(String productId)
	{
		int productToDelete = 0;
		if(productId != null && !productId.isEmpty()){
			for(int i=0;i<products.size();i++){
				if(productId.trim().equalsIgnoreCase(products.get(i).getId())){
					productToDelete =i;
				}
			}
			//Do not delete the product if the number of products is less than 10
			if(products.size()<=10){
				return false;
			}
			products.remove(productToDelete);
			return saveProductsToFile();	
		}
		else{
			return false;
		}
	}	

	/**
	 * Function to get a list of products based on a particular category
	 * 
	 * @param category
	 * @return returnProdList
	 */
	public ArrayList<Product> getProducts(Category category)
	{
		ArrayList<Product> returnProdList = new ArrayList<Product>();
		for(Product prodItem : products){
			if(category.getCategoryCode().equalsIgnoreCase(prodItem.getCategory().getCategoryCode())){
				returnProdList.add(prodItem);
			}
		}
		return returnProdList;
	}
	
	/**
	 * Return the required product object matching the product ID
	 * 
	 * @param productId
	 * @return prodRequired if true; null if false;
	 */
	public Product getProduct(String productId){
		for(Product prodRequired : products){
			//compare the product IDs to get the required product object
			if(prodRequired.getId().equalsIgnoreCase(productId)){
				return prodRequired;
			}
		}
		return null;
	}
    /**
     * Function to read the Bar Code of any product from the barcode reader, then return the product.
     * return null - product not found
     */
    public Product readProductFromBarCode(IBarCodeReader codeReader)
    {
    	if(codeReader == null){
    		return null;
    	}
    	String barCode = codeReader.readBarCode();
    	if(barCode == null || barCode.isEmpty()){
    		return null;
    	}
    	barCode = barCode.trim();
    	for(Product item: products){
    		if(barCode.equalsIgnoreCase(item.getBarCode())){
    			return item;
    		}
    	}
    	return null;
    }
	/**
	 * Function to write the data into the file
	 * 
	 * @return FileUtility.writeFile()
	 */
	public boolean saveProductsToFile()
	{
		File fileToWrite = new File(dataFile);
		String productData = "";
		for(Product productItem : products){
			productData = productData + productItem.toString() + "\n";
		}
		return (FileUtility.writeFile(fileToWrite, productData, false));
	}

	/**
	 * Function to read the data from the file
	 * 
	 * @return true if read successful;false if read failure;
	 */
	public boolean readProductsFromFile()
	{
		File fileToRead = new File(dataFile);
		if(!fileToRead.isFile()){
			System.out.println("error - ProductManager readProductsFromFile() file not found");
			return false;
		}
		// clean Manager data before read from file
		products.clear();
		ArrayList<String> fileContents = FileUtility.readFile(fileToRead);

		if(!fileContents.isEmpty()){
			Product tempProduct;
			for(String tempString : fileContents){
				String[] attributeList = tempString.split(",");
				tempProduct = new Product();
				formProductList(tempProduct, attributeList);				
			}
		}
		return true;
	}

	/**
	 * Function to generate the report
	 * 
	 * @return tempProdString
	 */
	public ArrayList<String> report(){
		ArrayList<String> tempProdString = new ArrayList<String>();
		for(Product prodList : products){
			tempProdString.add(prodList.toString());
		}
		return tempProdString;
	}

	/**
	 * Function to form the required product object and add it to the List of Products
	 * 
	 * @param product
	 * @param attributesString
	 */
	private void formProductList(Product product, String[] attributesString) {
		//set each attribute value to the product objects
		product.setId(attributesString[0].trim());
		product.setName(attributesString[1].trim());
		product.setDescription(attributesString[2].trim());
		product.setQuantityAvail(Integer.parseInt(attributesString[3].trim()));
		product.setPrice(Double.parseDouble(attributesString[4].trim()));
		product.setBarCode(attributesString[5].trim());
		product.setThreshold(Integer.parseInt(attributesString[6].trim()));
		product.setOrderQuantity(Integer.parseInt(attributesString[7].trim()));
		//get the category based on the ID
		String[] temp = product.getId().split("/");
		Category prodCategory = ManagerFactory.getCategoryManager().getCategories(temp[0]);
		if(prodCategory != null){
			product.setCategory(prodCategory);
		}
		else{
			product.setCategory(new Category("",""));
		}		
		//add the product object to the list of products
		products.add(product);
	}
}
