package iss.nus.edu.sg.SouvinierStore.GUI;

/**
 * Name					Date
 * ---------------------------------
 * Karthik 				20.03.2014
 * 
 * Author Sruthi
 * Since 20.03.2014
 * 
 * Modified By Karthik*/

import iss.nus.edu.sg.SouvinierStore.SouvenirStoreApp;
import iss.nus.edu.sg.SouvinierStore.system.Category;
import iss.nus.edu.sg.SouvinierStore.system.Product;
import iss.nus.edu.sg.SouvinierStore.system.Vendor;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


/**
 * This code was edited or generated using CloudGarden's Jigloo
 * SWT/Swing GUI Builder, which is free for non-commercial
 * use. If Jigloo is being used commercially (ie, by a corporation,
 * company or business for any purpose whatever) then you
 * should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details.
 * Use of Jigloo implies acceptance of these licensing terms.
 * A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
 * THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
 * LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class ProductPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int PRODUCT_NUM_LIMITATION = 10;

	private JLabel titleJLabel;
	private JPanel contentJPanel;
	// for build relationship with the logical module must through the SouvenirStoreApp.
	private SouvenirStoreApp souvenirStoreApp;
	// just for constructor of the dialog.
	private WindowFrame windowFrame;
	private JButton AddProductButton;
	private JPanel jPanel1;
	private JScrollPane jScrollPane1;
	private JLabel ProductQuantityAvailable;
	private JButton PurchaseOrderButton;
	private JScrollPane jScrollPane3;
	private JTableX PurchaseOrderTable;
	private JLabel PurchaseOrderLabel;
	private JDialog PurchaseOrderDialog;
	private AbstractAction PurchaseOrderAction;
	private JScrollPane jScrollPane2;
	private JTable ProductsReportTble;
	private JLabel ProductsReportLabel;
	private JDialog ProductReportDialog;
	private AbstractAction ViewReportButtonAction;
	private JButton ViewReportButton;
	private AbstractAction CancelProductDialog;
	private AbstractAction AddProductDialogAction;
	private JLabel VendorNotSelectError;
	private AbstractAction PrintPurchaseOrderAction;
	private JButton PrintPurchaseOrderButton;
	private AbstractAction PrintLabelAction;
	private JButton PrintLabelButton;
	private JPanel jPanel3;
	private JButton CancelProduct;
	private JButton AddProductDialogButton;
	private JTextField ProductOrderQuantityText;
	private JLabel ProductOrderQuantityLabel;
	private JTextField ProductRecorderQuantityText;
	private JLabel ProductRecorderQuantityLabel;
	private JTextField ProductBarCodeText;
	private JLabel ProductBarCodeLabel;
	private JTextField ProductPriceText;
	private JLabel ProductPriceLabel;
	private JTextField ProductQuantityText;
	private JTextField ProductDescriptionText;
	private JLabel ProductDescriptionLabel;
	private JTextField ProductNameText;
	private JLabel ProductNameLabel;
	private JPanel jPanel2;
	private JComboBox ProductCategoryCombo;
	private JLabel ProductDategoryLabel;
	private JLabel AddProductDialogLabel;
	private JDialog AddProductDialog;
	private JTable jTable1;
	private JButton DeleteProductButton;

	public ProductPanel(SouvenirStoreApp souvenirStoreApp, WindowFrame windowFrame) {
		super();
		this.souvenirStoreApp = souvenirStoreApp;
		this.windowFrame = windowFrame;
		initGUI();
	}

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void initGUI() {
		try {
			BorderLayout thisLayout = new BorderLayout();
			this.setLayout(thisLayout);
			this.setPreferredSize(new java.awt.Dimension(1200, 481));
			this.setSize(1200, 481);
			{
				titleJLabel = new JLabel();
				// GroupLayout titleJLabelLayout = new GroupLayout((JComponent)titleJLabel);
				// titleJLabel.setLayout(titleJLabelLayout);
				this.add(titleJLabel, BorderLayout.NORTH);
				titleJLabel.setText("  Product");
				titleJLabel.setPreferredSize(new java.awt.Dimension(1200, 44));
				titleJLabel.setFont(new java.awt.Font("Castellar",1,28));
				// titleJLabelLayout.setVerticalGroup(titleJLabelLayout.createParallelGroup());
				// titleJLabelLayout.setHorizontalGroup(titleJLabelLayout.createParallelGroup());
			}
			{
				contentJPanel = new JPanel();
				GroupLayout contentJPanelLayout = new GroupLayout((JComponent)contentJPanel);
				this.add(contentJPanel, BorderLayout.CENTER);
				contentJPanel.setLayout(contentJPanelLayout);
				{
					//Add Functionality

					jPanel1 = new JPanel();
					GroupLayout jPanel1Layout = new GroupLayout((JComponent)jPanel1);
					jPanel1.setLayout(jPanel1Layout);
					{
						AddProductButton = new JButton();
						AddProductButton.setText("Add..");
						AddProductButton.setHorizontalTextPosition(SwingConstants.RIGHT);
						AddProductButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("iss/nus/edu/sg/SouvinierStore/GUI/add_icon.png")));
						AddProductButton.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent evt) {
								getAddProductDialog().setLocationRelativeTo(null);
								getAddProductDialog().setVisible(true);
								resetValues();
								checkAndUpdateProductDeleteFunction();
							}});
					}
					{
						//Delete functionality

						DeleteProductButton = new JButton();
						DeleteProductButton.setText("Delete");
						DeleteProductButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("iss/nus/edu/sg/SouvinierStore/GUI/delete_icon.png")));
						DeleteProductButton.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent evt) {
								int rowToDelete = jTable1.getSelectedRow();
								if(rowToDelete == -1){
									JOptionPane.showMessageDialog(null,"Please Select the required record to delete","Delete Error",
											JOptionPane.ERROR_MESSAGE);
								}
								else{
									int deleteOption = JOptionPane.showConfirmDialog(null,"Are you sure you want ot delete this product? You can always add it again.",
											"Delete Product Confirmation",JOptionPane.YES_NO_OPTION);
									if(deleteOption == 0){
										int deleteCheck = performDeleteButton();
										if(deleteCheck == 0){
											JOptionPane.showMessageDialog(null,"Could Not Delete. Product Minimum Limit Reached!!","Delete Error",
													JOptionPane.ERROR_MESSAGE);
										}
										else{
											JOptionPane.showMessageDialog(null, "Product Deleted successfully!!");
										}

									}
								}
								checkAndUpdateProductDeleteFunction();
							}

							//Delete operations performed

							private int performDeleteButton() {
								int rowToDelete = jTable1.getSelectedRow();
								String productId = (String) jTable1.getModel().getValueAt(rowToDelete, 0);
								boolean temp = SouvenirStoreApp.getInstance().deleteProductEntry(productId);
								TableModel jTable1Model = 
										new DefaultTableModel(
												getDisplayProductList(),
												new String[] { "Product ID", "Product Name", "Description", "Quantity", "Price", "Bar Code", "Threshold", "Order Quantity" });
								jTable1.setModel(jTable1Model);
								jTable1.setPreferredSize(new java.awt.Dimension(1004, 372));
								jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
								jTable1.getColumnModel().getColumn(0).setMinWidth(0);
								jTable1.getColumnModel().getColumn(0).setPreferredWidth(0);
								jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
								jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
								if(temp){
									return 1;
								}
								else{
									return 0;
								}
							}});
					}
					jPanel1Layout.setHorizontalGroup(jPanel1Layout.createSequentialGroup()
							.addContainerGap()
							.addComponent(AddProductButton, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
							.addGap(31)
							.addComponent(DeleteProductButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(349)
							.addComponent(getPrintLabelButton(), GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
							.addGap(183)
							.addComponent(getPurchaseOrderButton(), 0, 182, Short.MAX_VALUE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
							.addComponent(getViewReportButton(), GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE));
					jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(AddProductButton, GroupLayout.Alignment.BASELINE, 0, 40, Short.MAX_VALUE)
							.addComponent(DeleteProductButton, GroupLayout.Alignment.BASELINE, 0, 40, Short.MAX_VALUE)
							.addComponent(getPurchaseOrderButton(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
							.addComponent(getViewReportButton(), GroupLayout.Alignment.BASELINE, 0, 40, Short.MAX_VALUE)
							.addComponent(getPrintLabelButton(), GroupLayout.Alignment.BASELINE, 0, 40, Short.MAX_VALUE));
				}
				{
					//main display panel of products
					
					jScrollPane1 = new JScrollPane();
					{
						TableModel jTable1Model = 
								new DefaultTableModel(
										getDisplayProductList(),
										new String[] { "Product ID", "Product Name", "Description", "Quantity", "Price", "Bar Code", "Threshold", "Order Quantity" });
						jTable1 = new JTable(){
							public boolean isCellEditable(int rowIndex, int ColumnIndex)
							{
								return false;
							}
						};
						jScrollPane1.setViewportView(jTable1);
						jTable1.setModel(jTable1Model);
						jTable1.setPreferredSize(new java.awt.Dimension(1088, 337));
						//hide the 1st column
						jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
						jTable1.getColumnModel().getColumn(0).setMinWidth(0);
						jTable1.getColumnModel().getColumn(0).setPreferredWidth(0);
						jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
					}
				}
				contentJPanelLayout.setHorizontalGroup(contentJPanelLayout.createParallelGroup()
						.addGroup(GroupLayout.Alignment.LEADING, contentJPanelLayout.createSequentialGroup()
								.addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 1152, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(48, Short.MAX_VALUE))
								.addGroup(GroupLayout.Alignment.LEADING, contentJPanelLayout.createSequentialGroup()
										.addGap(46)
										.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 1106, GroupLayout.PREFERRED_SIZE)
										.addContainerGap(48, Short.MAX_VALUE)));
				contentJPanelLayout.setVerticalGroup(contentJPanelLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addGap(25)
						.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 339, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(21, Short.MAX_VALUE));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		checkAndUpdateProductDeleteFunction();
	}

	private void checkAndUpdateProductDeleteFunction(){
		int memberNum = SouvenirStoreApp.getInstance().checkInventory().size();
		if(memberNum > PRODUCT_NUM_LIMITATION){
			DeleteProductButton.setEnabled(true);
		}else{
			DeleteProductButton.setEnabled(false);
		}
	}
	//2D String array for table contents

	private String[][] getDisplayProductList(){
		List<Product> tempProdList = SouvenirStoreApp.getInstance().checkInventory();
		DecimalFormat df = new DecimalFormat("#.00");
		int size = tempProdList.size();
		int columnCount = 0;
		String [][] returnProdList = new String[size][];
		if(size!=0){
			String stringVal = tempProdList.get(0).toString();
			for(int ind=0;ind<stringVal.length();ind++){
				if(stringVal.charAt(ind)==','){
					columnCount++;
				}
			}
			returnProdList = new String[size][columnCount+1];
			for(int i=0;i<size;i++){
				returnProdList[i][0] = tempProdList.get(i).getId();
				returnProdList[i][1] = tempProdList.get(i).getName();
				returnProdList[i][2] = tempProdList.get(i).getDescription();  
				returnProdList[i][3] = Integer.toString(tempProdList.get(i).getQuantityAvail());
				returnProdList[i][4] = df.format(tempProdList.get(i).getPrice()); 
				returnProdList[i][5] = tempProdList.get(i).getBarCode();
				returnProdList[i][6] = Integer.toString(tempProdList.get(i).getThreshold());
				returnProdList[i][7] = Integer.toString(tempProdList.get(i).getOrderQuantity());
			}
		}
		return returnProdList;
	}

	//reset the values of the add dialog box

	private void resetValues(){
		getProductCategoryCombo().setSelectedIndex(-1);
		getProductNameText().setText("");
		getProductDescriptionText().setText("");
		getProductQuantityText().setText("");
		getProductPriceText().setText("");
		getProductBarCodeText().setText("");
		getProductRecorderQuantityText().setText("");
		getProductOrderQuantityText().setText("");
	}

	private JDialog getAddProductDialog() {
		if(AddProductDialog == null) {
			AddProductDialog = new JDialog();
			GroupLayout AddProductDialogLayout = new GroupLayout((JComponent)AddProductDialog.getContentPane());
			AddProductDialog.getContentPane().setLayout(AddProductDialogLayout);
			AddProductDialog.setPreferredSize(new java.awt.Dimension(348, 429));
			AddProductDialog.setSize(348, 429);
			AddProductDialogLayout.setHorizontalGroup(AddProductDialogLayout.createSequentialGroup()
				.addContainerGap()
				.addGroup(AddProductDialogLayout.createParallelGroup()
				    .addGroup(AddProductDialogLayout.createSequentialGroup()
				        .addComponent(getJPanel2(), GroupLayout.PREFERRED_SIZE, 301, GroupLayout.PREFERRED_SIZE)
				        .addGap(0, 0, Short.MAX_VALUE))
				    .addGroup(GroupLayout.Alignment.LEADING, AddProductDialogLayout.createSequentialGroup()
				        .addComponent(getAddProductDialogLabel(), GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
				        .addGap(0, 140, Short.MAX_VALUE))
				    .addGroup(GroupLayout.Alignment.LEADING, AddProductDialogLayout.createSequentialGroup()
				        .addPreferredGap(getJPanel2(), getJPanel3(), LayoutStyle.ComponentPlacement.INDENT)
				        .addComponent(getJPanel3(), GroupLayout.PREFERRED_SIZE, 277, GroupLayout.PREFERRED_SIZE)
				        .addGap(0, 12, Short.MAX_VALUE)))
				.addContainerGap(19, 19));
			AddProductDialogLayout.setVerticalGroup(AddProductDialogLayout.createSequentialGroup()
				.addContainerGap()
				.addComponent(getAddProductDialogLabel(), GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
				.addGap(19)
				.addComponent(getJPanel2(), GroupLayout.PREFERRED_SIZE, 253, GroupLayout.PREFERRED_SIZE)
				.addGap(34)
				.addComponent(getJPanel3(), GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(23, Short.MAX_VALUE));
		}
		return AddProductDialog;
	}

	private JLabel getAddProductDialogLabel() {
		if(AddProductDialogLabel == null) {
			AddProductDialogLabel = new JLabel();
			AddProductDialogLabel.setText("Add Product..");
			AddProductDialogLabel.setFont(new java.awt.Font("Calibri",1,20));
		}
		return AddProductDialogLabel;
	}

	private JLabel getProductDategoryLabel() {
		if(ProductDategoryLabel == null) {
			ProductDategoryLabel = new JLabel();
			ProductDategoryLabel.setText("Product Category");
			ProductDategoryLabel.setBounds(0, 3, 132, 16);
		}
		return ProductDategoryLabel;
	}

	private JComboBox getProductCategoryCombo() {
		if(ProductCategoryCombo == null) {
			ComboBoxModel<String> ProductCategoryComboModel = 
					new DefaultComboBoxModel<String>(
							getDisplayCategoryList());
			ProductCategoryCombo = new JComboBox();
			ProductCategoryCombo.setModel(ProductCategoryComboModel);
			ProductCategoryCombo.setBounds(150, 0, 146, 22);
		}
		return ProductCategoryCombo;
	}

	private JPanel getJPanel2() {
		if(jPanel2 == null) {
			jPanel2 = new JPanel();
			GroupLayout jPanel2Layout = new GroupLayout((JComponent)jPanel2);
			jPanel2.setLayout(null);
			jPanel2.add(getProductCategoryCombo());
			jPanel2.add(getProductDategoryLabel());
			jPanel2.add(getProductNameLabel());
			jPanel2.add(getProductNameText());
			jPanel2.add(getProductDescriptionLabel());
			jPanel2.add(getProductDescriptionText());
			jPanel2.add(getProductQuantityAvailable());
			jPanel2.add(getProductQuantityText());
			jPanel2.add(getProductPriceLabel());
			jPanel2.add(getProductPriceText());
			jPanel2.add(getProductBarCodeLabel());
			jPanel2.add(getProductBarCodeText());
			jPanel2.add(getProductRecorderQuantityLabel());
			jPanel2.add(getProductRecorderQuantityText());
			jPanel2.add(getProductOrderQuantityLabel());
			jPanel2.add(getProductOrderQuantityText());
		}
		return jPanel2;
	}

	private JLabel getProductNameLabel() {
		if(ProductNameLabel == null) {
			ProductNameLabel = new JLabel();
			ProductNameLabel.setText("Product Name");
			ProductNameLabel.setBounds(0, 39, 132, 16);
		}
		return ProductNameLabel;
	}

	private JTextField getProductNameText() {
		if(ProductNameText == null) {
			ProductNameText = new JTextField();
			ProductNameText.setBounds(150, 37, 146, 22);
		}
		return ProductNameText;
	}

	private JLabel getProductDescriptionLabel() {
		if(ProductDescriptionLabel == null) {
			ProductDescriptionLabel = new JLabel();
			ProductDescriptionLabel.setText("Product Description");
			ProductDescriptionLabel.setBounds(0, 71, 132, 16);
		}
		return ProductDescriptionLabel;
	}

	private JTextField getProductDescriptionText() {
		if(ProductDescriptionText == null) {
			ProductDescriptionText = new JTextField();
			ProductDescriptionText.setBounds(150, 69, 146, 22);
		}
		return ProductDescriptionText;
	}

	private JLabel getProductQuantityAvailable() {
		if(ProductQuantityAvailable == null) {
			ProductQuantityAvailable = new JLabel();
			ProductQuantityAvailable.setText("Product Quantity");
			ProductQuantityAvailable.setBounds(0, 103, 132, 16);
		}
		return ProductQuantityAvailable;
	}

	private JTextField getProductQuantityText() {
		if(ProductQuantityText == null) {
			ProductQuantityText = new JTextField();
			ProductQuantityText.setBounds(150, 101, 146, 22);
		}
		return ProductQuantityText;
	}

	private JLabel getProductPriceLabel() {
		if(ProductPriceLabel == null) {
			ProductPriceLabel = new JLabel();
			ProductPriceLabel.setText("Product Price");
			ProductPriceLabel.setBounds(0, 135, 132, 16);
		}
		return ProductPriceLabel;
	}

	private JTextField getProductPriceText() {
		if(ProductPriceText == null) {
			ProductPriceText = new JTextField();
			ProductPriceText.setBounds(150, 133, 146, 22);
		}
		return ProductPriceText;
	}

	private JLabel getProductBarCodeLabel() {
		if(ProductBarCodeLabel == null) {
			ProductBarCodeLabel = new JLabel();
			ProductBarCodeLabel.setText("Product Barcode ID");
			ProductBarCodeLabel.setBounds(0, 167, 132, 16);
		}
		return ProductBarCodeLabel;
	}

	private JTextField getProductBarCodeText() {
		if(ProductBarCodeText == null) {
			ProductBarCodeText = new JTextField();
			ProductBarCodeText.setBounds(150, 165, 146, 22);
		}
		return ProductBarCodeText;
	}

	private JLabel getProductRecorderQuantityLabel() {
		if(ProductRecorderQuantityLabel == null) {
			ProductRecorderQuantityLabel = new JLabel();
			ProductRecorderQuantityLabel.setText("Product Threshold");
			ProductRecorderQuantityLabel.setBounds(0, 199, 132, 16);
		}
		return ProductRecorderQuantityLabel;
	}

	private JTextField getProductRecorderQuantityText() {
		if(ProductRecorderQuantityText == null) {
			ProductRecorderQuantityText = new JTextField();
			ProductRecorderQuantityText.setBounds(150, 197, 146, 22);
		}
		return ProductRecorderQuantityText;
	}

	private JLabel getProductOrderQuantityLabel() {
		if(ProductOrderQuantityLabel == null) {
			ProductOrderQuantityLabel = new JLabel();
			ProductOrderQuantityLabel.setText("Order Quantity");
			ProductOrderQuantityLabel.setBounds(0, 231, 132, 16);
		}
		return ProductOrderQuantityLabel;
	}

	private JTextField getProductOrderQuantityText() {
		if(ProductOrderQuantityText == null) {
			ProductOrderQuantityText = new JTextField();
			ProductOrderQuantityText.setBounds(150, 229, 146, 22);
		}
		return ProductOrderQuantityText;
	}

	private JButton getAddProductDialogButton() {
		if(AddProductDialogButton == null) {
			AddProductDialogButton = new JButton();
			AddProductDialogButton.setText("Add Product");
			AddProductDialogButton.setAction(getAddProductDialogAction());
		}
		return AddProductDialogButton;
	}

	private JButton getCancelProduct() {
		if(CancelProduct == null) {
			CancelProduct = new JButton();
			CancelProduct.setText("Cancel");
			CancelProduct.setAction(getCancelProductDialog());
		}
		return CancelProduct;
	}

	private JPanel getJPanel3() {
		if(jPanel3 == null) {
			jPanel3 = new JPanel();
			GroupLayout jPanel3Layout = new GroupLayout((JComponent)jPanel3);
			jPanel3.setLayout(jPanel3Layout);
			jPanel3Layout.setHorizontalGroup(jPanel3Layout.createSequentialGroup()
					.addGap(8)
					.addComponent(getAddProductDialogButton(), GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
					.addGap(0, 15, Short.MAX_VALUE)
					.addComponent(getCancelProduct(), GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
					.addContainerGap());
			jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(getCancelProduct(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					.addComponent(getAddProductDialogButton(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE));
		}
		return jPanel3;
	}

	private String[] getDisplayCategoryList(){
		ArrayList<String> displayCategoryString = new ArrayList<String>();
		displayCategoryString = SouvenirStoreApp.getInstance().getCategoriesReporting();
		String[] comboBoxValues = new String[(displayCategoryString.size())];

		for(int i=0;i<displayCategoryString.size();i++){
			String[] tempString = new String[2];
			tempString = displayCategoryString.get(i).split(",");
			comboBoxValues[i] = tempString[1];
		}

		return comboBoxValues;
	}

	//ActionListner for the Add button on the Add dialog box
	
	private AbstractAction getAddProductDialogAction() {
		if(AddProductDialogAction == null) {
			AddProductDialogAction = new AbstractAction("Add Product", null) {
				public void actionPerformed(ActionEvent evt) {
					int addCheck = performAddButton();
					if(addCheck == 0){
						JOptionPane.showMessageDialog(null,"Please fill in all the fields","Missing Data",
								JOptionPane.ERROR_MESSAGE);
					}
					else if(addCheck == 1){
						JOptionPane.showMessageDialog(null,"Product Already Exists","Add Product error",
								JOptionPane.ERROR_MESSAGE);
					}
					else if(addCheck == -1){
						JOptionPane.showMessageDialog(null,"Incorrecet Values entered!","Add Product error",
								JOptionPane.ERROR_MESSAGE);
					}
					else{
						int continueOption = JOptionPane.showConfirmDialog(null,"Product Successfully added!! Add anotther Product?",
								"Confirmation",JOptionPane.YES_NO_OPTION);
						if(continueOption == 0){
							getAddProductDialog().setLocationRelativeTo(null);
							getAddProductDialog().setVisible(true);
							resetValues();
						}
						else{
							getAddProductDialog().dispose();
						}
					}
				}

				//Add functionality and refresh the data in the screen

				private int performAddButton() {
					String productCategory = (String)ProductCategoryCombo.getSelectedItem();
					String productName = ProductNameText.getText();
					String productDescription = ProductDescriptionText.getText();
					String orderQuantity = ProductOrderQuantityText.getText();
					String productPrice = ProductPriceText.getText();
					String recorderQuantity = ProductRecorderQuantityText.getText();
					String productQuantity = ProductQuantityText.getText();
					String barCodeNumber = ProductBarCodeText.getText();

					int productQuantityInt;
					int recorderQuantityInt;
					int orderQuantityInt;
					double productPriceDouble;

					if(productCategory==null || productName.isEmpty() || productDescription.isEmpty() || productQuantity.isEmpty()
							|| productPrice.isEmpty() || barCodeNumber.isEmpty() || recorderQuantity.isEmpty() || orderQuantity.isEmpty()){
						return 0;
					}

					try{
						productQuantityInt = Integer.parseInt(productQuantity);
						recorderQuantityInt = Integer.parseInt(recorderQuantity);
						orderQuantityInt = Integer.parseInt(orderQuantity);
						productPriceDouble = Double.parseDouble(productPrice);
					}
					catch (NumberFormatException e){
						return -1;
					}
					ArrayList<Category> categoryList = SouvenirStoreApp.getInstance().getCategories();
					for(Category cc : categoryList){
						if(cc.getName().trim().equalsIgnoreCase(productCategory.trim())){
							productCategory = cc.getCategoryCode();
						}
					}

					//add and refresh screen
					
					boolean temp = SouvenirStoreApp.getInstance().newProductEntry(productCategory, productName, productDescription, productQuantityInt, productPriceDouble, barCodeNumber, recorderQuantityInt, orderQuantityInt);
					TableModel JProductTableModel = 
							new DefaultTableModel(
									getDisplayProductList(),
									new String[] {"Product ID", "Product Name", "Description", "Quantity", "Price", "Bar Code", "Threshold", "Order Quantity"});
					jTable1.setModel(JProductTableModel);
					jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
					jTable1.getColumnModel().getColumn(0).setMinWidth(0);
					jTable1.getColumnModel().getColumn(0).setPreferredWidth(0);
					if(temp){
						return 2;
					}
					else{
						return 1;
					}					
				}
			};
		}
		return AddProductDialogAction;
	}

	//simply close the dialog on cancel button
	
	private AbstractAction getCancelProductDialog() {
		if(CancelProductDialog == null) {
			CancelProductDialog = new AbstractAction("Cancel", null) {
				public void actionPerformed(ActionEvent evt) {
					resetValues();
					getAddProductDialog().dispose();
				}
			};
		}
		return CancelProductDialog;
	}

	private JButton getViewReportButton() {
		if(ViewReportButton == null) {
			ViewReportButton = new JButton();
			ViewReportButton.setText("View Report");
			ViewReportButton.setAction(getViewReportButtonAction());
		}
		return ViewReportButton;
	}

	//actionlistner to show the report dialog
	
	private AbstractAction getViewReportButtonAction() {
		if(ViewReportButtonAction == null) {
			ViewReportButtonAction = new AbstractAction("View Report", null) {
				public void actionPerformed(ActionEvent evt) {
					getProductReportDialog().setLocationRelativeTo(null);
					getProductReportDialog().setVisible(true);
				}
			};
		}
		return ViewReportButtonAction;
	}

	private JDialog getProductReportDialog() {
		if(ProductReportDialog == null) {
			ProductReportDialog = new JDialog();
			GroupLayout ProductReportDialogLayout = new GroupLayout((JComponent)ProductReportDialog.getContentPane());
			ProductReportDialog.getContentPane().setLayout(ProductReportDialogLayout);
			ProductReportDialog.setPreferredSize(new java.awt.Dimension(1200, 650));
			ProductReportDialog.setSize(1200, 650);
			ProductReportDialogLayout.setHorizontalGroup(ProductReportDialogLayout.createParallelGroup()
					.addComponent(getJScrollPane2(), GroupLayout.Alignment.LEADING, 0, 1184, Short.MAX_VALUE)
					.addGroup(GroupLayout.Alignment.LEADING, ProductReportDialogLayout.createSequentialGroup()
							.addGap(0, 524, Short.MAX_VALUE)
							.addComponent(getProductsReportLabel(), GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(492, 492)));
			ProductReportDialogLayout.setVerticalGroup(ProductReportDialogLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(getProductsReportLabel(), GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addGap(16)
					.addComponent(getJScrollPane2(), 0, 550, Short.MAX_VALUE));
		}
		return ProductReportDialog;
	}

	private JLabel getProductsReportLabel() {
		if(ProductsReportLabel == null) {
			ProductsReportLabel = new JLabel();
			ProductsReportLabel.setText("PRODUCTS REPORT");
			ProductsReportLabel.setFont(new java.awt.Font("Calibri",1,20));
			ProductsReportLabel.setHorizontalTextPosition(SwingConstants.CENTER);
			ProductsReportLabel.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return ProductsReportLabel;
	}

	//show the report table dialog
	
	private JTable getProductsReportTble() {
		if(ProductsReportTble == null) {
			TableModel ProductsReportTbleModel = 
					new DefaultTableModel(
							getDisplayProductList(),
							new String[] { "Product ID", "Product Name", "Description", "Quantity", "Price", "Bar Code", "Threshold", "Order Quantity" });
			ProductsReportTble = new JTable(){
				public boolean isCellEditable(int rowIndex, int ColumnIndex)
				{
					return false;
				}
			};
			ProductsReportTble.setModel(ProductsReportTbleModel);
			ProductsReportTble.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		}
		return ProductsReportTble;
	}

	private JScrollPane getJScrollPane2() {
		if(jScrollPane2 == null) {
			jScrollPane2 = new JScrollPane();
			jScrollPane2.setViewportView(getProductsReportTble());
		}
		return jScrollPane2;
	}

	private JButton getPurchaseOrderButton() {
		if(PurchaseOrderButton == null) {
			PurchaseOrderButton = new JButton();
			PurchaseOrderButton.setText("Generate Purchase Order");
			PurchaseOrderButton.setAction(getPurchaseOrderAction());
		}
		return PurchaseOrderButton;
	}

	//show the purchase order dialog
	
	private AbstractAction getPurchaseOrderAction() {
		if(PurchaseOrderAction == null) {
			PurchaseOrderAction = new AbstractAction("Genrate Purchase Order", null) {
				public void actionPerformed(ActionEvent evt) {
					getPurchaseOrderDialog().setLocationRelativeTo(null);
					getPurchaseOrderDialog().setVisible(true);
				}
			};
		}
		return PurchaseOrderAction;
	}

	private JDialog getPurchaseOrderDialog() {
		if(PurchaseOrderDialog == null) {
			PurchaseOrderDialog = new JDialog();
			GroupLayout PurchaseOrderDialogLayout = new GroupLayout((JComponent)PurchaseOrderDialog.getContentPane());
			PurchaseOrderDialog.getContentPane().setLayout(PurchaseOrderDialogLayout);
			PurchaseOrderDialog.setPreferredSize(new java.awt.Dimension(660, 421));
			PurchaseOrderDialog.setSize(660, 421);
			PurchaseOrderDialogLayout.setHorizontalGroup(PurchaseOrderDialogLayout.createSequentialGroup()
				.addContainerGap()
				.addGroup(PurchaseOrderDialogLayout.createParallelGroup()
				    .addComponent(getJScrollPane3(), GroupLayout.Alignment.LEADING, 0, 620, Short.MAX_VALUE)
				    .addGroup(GroupLayout.Alignment.LEADING, PurchaseOrderDialogLayout.createSequentialGroup()
				        .addGap(152)
				        .addGroup(PurchaseOrderDialogLayout.createParallelGroup()
				            .addGroup(PurchaseOrderDialogLayout.createSequentialGroup()
				                .addGap(0, 0, Short.MAX_VALUE)
				                .addComponent(getPurchaseOrderLabel(), GroupLayout.PREFERRED_SIZE, 313, GroupLayout.PREFERRED_SIZE))
				            .addGroup(GroupLayout.Alignment.LEADING, PurchaseOrderDialogLayout.createSequentialGroup()
				                .addGap(30)
				                .addComponent(getVendorNotSelectError(), GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
				                .addGap(0, 132, Short.MAX_VALUE)))
				        .addComponent(getPrintPurchaseOrderButton(), GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap());
			PurchaseOrderDialogLayout.setVerticalGroup(PurchaseOrderDialogLayout.createSequentialGroup()
				.addContainerGap()
				.addComponent(getPurchaseOrderLabel(), GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				.addGap(31)
				.addComponent(getJScrollPane3(), GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
				.addGap(17)
				.addGroup(PurchaseOrderDialogLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(getPrintPurchaseOrderButton(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(getVendorNotSelectError(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(20, 20));
		}
		return PurchaseOrderDialog;
	}

	private JLabel getPurchaseOrderLabel() {
		if(PurchaseOrderLabel == null) {
			PurchaseOrderLabel = new JLabel();
			PurchaseOrderLabel.setText("PURCHASE ORDER FOR INVENTORY");
			PurchaseOrderLabel.setFont(new java.awt.Font("Calibri",1,20));
		}
		return PurchaseOrderLabel;
	}

	//generate the purchase order
	
	private JTableX getPurchaseOrderTable() {
		if(PurchaseOrderTable == null) {
			DefaultTableModel PurchaseOrderTableModel = new DefaultTableModel();

			PurchaseOrderTableModel.addColumn("Product Name");
			PurchaseOrderTableModel.addColumn("Quantity Available");
			PurchaseOrderTableModel.addColumn("Threshold");
			PurchaseOrderTableModel.addColumn("Order Quantity");
			PurchaseOrderTableModel.addColumn("Vendor");

			PurchaseOrderTable = new JTableX(PurchaseOrderTableModel){
				public boolean isCellEditable(int row, int col)
				{
					if (col!=4)
						return false;
					return true;
				}
			};
			PurchaseOrderTable.setRowSelectionAllowed(false);
			PurchaseOrderTable.setColumnSelectionAllowed(false);

			getPurchaseOrderList(PurchaseOrderTableModel);
			PurchaseOrderTable.setRowHeight(25);

		}
		return PurchaseOrderTable;
	}

	private JScrollPane getJScrollPane3() {
		if(jScrollPane3 == null) {
			jScrollPane3 = new JScrollPane();
			jScrollPane3.setViewportView(getPurchaseOrderTable());
		}
		return jScrollPane3;
	}

	private void getPurchaseOrderList(DefaultTableModel d){

		List<Product> tempProdList = SouvenirStoreApp.getInstance().checkInventory();
		ArrayList<Product> replenishList = new ArrayList<Product>();
		for(Product item : tempProdList){
			if(item.isNeedReplenish()){
				replenishList.add(item);
			}
		}
		ArrayList<String []> vendorList = new ArrayList<String[]>();
		if(replenishList.size()>0){
			for(int i=0;i<replenishList.size();i++){
				Object[] rowData = new Object[5];
				rowData[0] = (String)replenishList.get(i).getName();
				rowData[1] = Integer.toString(replenishList.get(i).getQuantityAvail());
				rowData[2] = Integer.toString(replenishList.get(i).getThreshold());
				rowData[3] = Integer.toString(replenishList.get(i).getOrderQuantity());

				ArrayList<Vendor> vendorListArray = SouvenirStoreApp.getInstance().getVendorsForCertainCategory(replenishList.get(i).getCategory().getCategoryCode());
				String[] values = new String[vendorListArray.size()];

				for(int j=0;j<vendorListArray.size();j++){
					values[j] = vendorListArray.get(j).getName();
				}
				vendorList.add(values);

				d.addRow(rowData);			
			}

			RowEditorModel rm = new RowEditorModel();
			PurchaseOrderTable.setRowEditorModel(rm);

			for(int k=0;k<vendorList.size();k++){
				JComboBox cb = new JComboBox(vendorList.get(k));
				DefaultCellEditor ed = new DefaultCellEditor(cb);
				rm.addEditorForRow(k,ed);
			}
		}
	}

	private JButton getPrintLabelButton() {
		if(PrintLabelButton == null) {
			PrintLabelButton = new JButton();
			PrintLabelButton.setText("Print Label");
			PrintLabelButton.setAction(getPrintLabelAction());
		}
		return PrintLabelButton;
	}

	private AbstractAction getPrintLabelAction() {
		if(PrintLabelAction == null) {
			PrintLabelAction = new AbstractAction("Print Label", null) {
				public void actionPerformed(ActionEvent evt) {
					int rowToPrint = jTable1.getSelectedRow();
					if(rowToPrint == -1){
						JOptionPane.showMessageDialog(null,"Please Select the required record whose label you want to print","Print Error",
								JOptionPane.ERROR_MESSAGE);
					}
					else{
						String productId = (String) jTable1.getModel().getValueAt(rowToPrint, 0);
						ArrayList<Product> prodList = SouvenirStoreApp.getInstance().checkInventory();
						for(Product productItem : prodList){
							if(productItem.getId().equalsIgnoreCase(productId)){
								productItem.printLabel(SouvenirStoreApp.getInstance().getLabelPrinter(windowFrame));
							}
						}
					}
				}
			};
		}
		return PrintLabelAction;
	}

	private JButton getPrintPurchaseOrderButton() {
		if(PrintPurchaseOrderButton == null) {
			PrintPurchaseOrderButton = new JButton();
			PrintPurchaseOrderButton.setText("Print Purchase Order");
			PrintPurchaseOrderButton.setAction(getPrintPurchaseOrderAction());
		}
		return PrintPurchaseOrderButton;
	}

	private AbstractAction getPrintPurchaseOrderAction() {
		if(PrintPurchaseOrderAction == null) {
			PrintPurchaseOrderAction = new AbstractAction("Print Purchase Order", null) {
				public void actionPerformed(ActionEvent evt) {
					boolean flag = false;
					for(int index=0;index<PurchaseOrderTable.getRowCount();index++){
						String valueOfCell = (String)PurchaseOrderTable.getValueAt(index,4);
						if(valueOfCell == null){
							flag = true;
						}
					}
					if(flag){
						getVendorNotSelectError().setVisible(true);
					}
					else{
						SouvenirStoreApp.getInstance().getReceiptPrinter(windowFrame);
						for(int index=0;index<PurchaseOrderTable.getRowCount();index++){
							PurchaseOrderTable.setValueAt("", index, 4);
						}
						getVendorNotSelectError().setVisible(false);
						getPurchaseOrderDialog().dispose();
					}					
				}
			};
		}
		return PrintPurchaseOrderAction;
	}
	
	private JLabel getVendorNotSelectError() {
		if(VendorNotSelectError == null) {
			VendorNotSelectError = new JLabel();
			VendorNotSelectError.setText("Please Select a Vendor!!");
			VendorNotSelectError.setForeground(new java.awt.Color(255,0,0));
			VendorNotSelectError.setVisible(false);
		}
		return VendorNotSelectError;
	}
}
