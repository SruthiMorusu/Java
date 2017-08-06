package iss.nus.edu.sg.SouvinierStore.GUI;

/**
 * Name					Date
 * ---------------------------------
 * Sruthi 				20.03.2014
 * 
 * @author Sruthi
 * Since 20.03.2014
 * 
 * Modified By Sruthi*/

import iss.nus.edu.sg.SouvinierStore.SouvenirStoreApp;
import iss.nus.edu.sg.SouvinierStore.system.BillException;
import iss.nus.edu.sg.SouvinierStore.system.BillItem;
import iss.nus.edu.sg.SouvinierStore.system.Customer;
import iss.nus.edu.sg.SouvinierStore.system.Member;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class BillContentPanel_Process extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private static final int ROW_HEIGHT = 20;
	
	private JLabel discountPriceJLabel;
	private JButton cancelJButton;
	private JButton paymentJButton;
	private JTextField redeemJTextField;
	private JLabel redeemPointsJLabel;
	private JTextField cashValueJTextField;
	private JLabel cashJLabel;
	private JLabel customerTitleJLabel;
	private JLabel discountPriceValueJLabel;
	private JLabel discountValueJLabel;
	private JLabel totalPriceValueJLabel;
	private JButton deleteJButton;
	private JButton addJButton;
	private JLabel discountJLabel;
	private JLabel totalJLabel;
	private JTable productsListJTable;
	
	private SouvenirStoreApp souvenirStoreApp;
	private BillPanel billPanel;
	// just for constructor of the dialog.
	private WindowFrame windowFrame;
	private MainMenuPanel mainmenuPanel;
	
	// data
	private String[][] products = new String[][] { { "Product ID", "Product Name", "Unit Price", "Quantity" } };
	private String[] columnNames = new String[] { "Product ID", "Product Name", "Unit Price", "Quantity" };
	private TableModel productsListJTableModel = null;

	public BillContentPanel_Process(SouvenirStoreApp souvenirStoreApp, BillPanel billPanel, 
			WindowFrame windowFrame, MainMenuPanel mainmenuPanel) {
		super();
		this.souvenirStoreApp = souvenirStoreApp;
		this.billPanel = billPanel;
		this.windowFrame = windowFrame;
		this.mainmenuPanel = mainmenuPanel;
		initGUI();
	}

	private void initGUI() {
		try {
			{
				{
					this.setSize(1200, 437);
					this.setLayout(null);
					this.setPreferredSize(new java.awt.Dimension(1200, 437));
				}	
				{
					
					productsListJTableModel = new DefaultTableModel(products, columnNames);
					productsListJTable = new JTable(null, null, null);
					this.add(productsListJTable);
					productsListJTable.setRowHeight(ROW_HEIGHT);
					productsListJTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					productsListJTable.setModel(productsListJTableModel);
					productsListJTable.setBounds(26, 22, 872, 392);
					productsListJTable.setShowVerticalLines(false);
					productsListJTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					productsListJTable.setFont(new java.awt.Font("Andalus",0,20));productsListJTable.setGridColor(new Color(200, 200, 200));
					for(int i = 0; i < productsListJTable.getColumnCount(); i++) {
						productsListJTable.getColumnModel().getColumn(i).setCellRenderer(new Renderer());
					}
					productsListJTable.setSurrendersFocusOnKeystroke(true);
					productsListJTable.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
				}
				{
					totalJLabel = new JLabel();
					this.add(totalJLabel);
					totalJLabel.setText("Total Price : ");
					totalJLabel.setBounds(910, 119, 129, 29);
					totalJLabel.setFont(new java.awt.Font("Andalus",1,22));
				}
				{
					discountJLabel = new JLabel();
					this.add(discountJLabel);
					discountJLabel.setText("Discount : ");
					discountJLabel.setBounds(910, 170, 114, 26);
					discountJLabel.setFont(new java.awt.Font("Andalus",1,22));
				}
				{
					addJButton = new JButton();
					this.add(addJButton);
					addJButton.setText("Add");
					addJButton.setBounds(910, 22, 114, 43);
					addJButton.addActionListener(new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent e) {
							AddBillItemDialog addBillItemDialog = new AddBillItemDialog(windowFrame, "Add Product", 
									souvenirStoreApp, BillContentPanel_Process.this);
							addBillItemDialog.setVisible(true);
						}});
				}
				{
					deleteJButton = new JButton();
					this.add(deleteJButton);
					deleteJButton.setText("delete");
					deleteJButton.setBounds(1061, 22, 112, 43);
					deleteJButton.addActionListener(new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent arg0) {
							int rowSelectedIndex = productsListJTable.getSelectedRow();
							System.out.println("rowSelectedIndex: " + rowSelectedIndex);
							if(rowSelectedIndex > 0) {
								Object productIdSelected = productsListJTable.getValueAt(rowSelectedIndex, 0);
								System.out.println("selected product id for delete: " + productIdSelected.toString());
								DeleteBillItemDialog deleteBillItemDialog = new DeleteBillItemDialog(windowFrame , "Delete Product", 
										souvenirStoreApp, BillContentPanel_Process.this, productIdSelected.toString());
								deleteBillItemDialog.setVisible(true);
							}	
						}});
				}
				{
					discountPriceJLabel = new JLabel();
					this.add(discountPriceJLabel);
					discountPriceJLabel.setText("Discount Price : ");
					discountPriceJLabel.setBounds(910, 218, 172, 29);
					discountPriceJLabel.setFont(new java.awt.Font("Andalus",1,22));
				}
				{
					totalPriceValueJLabel = new JLabel();
					this.add(totalPriceValueJLabel);
					totalPriceValueJLabel.setText("$ " + souvenirStoreApp.getProductBill().getTotalPrice());
					totalPriceValueJLabel.setBounds(994, 147, 102, 27);
					totalPriceValueJLabel.setFont(new java.awt.Font("Andalus",3,22));
				}
				{
					discountValueJLabel = new JLabel();
					this.add(discountValueJLabel);
					discountValueJLabel.setText((int)(souvenirStoreApp.getProductBill().getDiscount() * 100) + " %");
					discountValueJLabel.setBounds(994, 196, 105, 25);
					discountValueJLabel.setFont(new java.awt.Font("Andalus",3,22));
				}
				{
					discountPriceValueJLabel = new JLabel();
					this.add(discountPriceValueJLabel);
					discountPriceValueJLabel.setText("$ " + souvenirStoreApp.getProductBill().getTotalPriceAfterDiscount());
					discountPriceValueJLabel.setBounds(994, 241, 105, 26);
					discountPriceValueJLabel.setFont(new java.awt.Font("Andalus",3,22));
				}
				{
					customerTitleJLabel = new JLabel();
					this.add(customerTitleJLabel);
					customerTitleJLabel.setBounds(910, 76, 263, 31);
					customerTitleJLabel.setFont(new java.awt.Font("Andalus",1,24)); 
					Customer customer = souvenirStoreApp.getProductBill().getCustomer();
					if(customer instanceof Member) {
						customerTitleJLabel.setText(((Member) customer).getName());
					} else {
						customerTitleJLabel.setText("Public");
					}
				}
				{
					cashJLabel = new JLabel();
					this.add(cashJLabel);
					cashJLabel.setText("Cash :    $");
					cashJLabel.setBounds(910, 279, 102, 32);
					cashJLabel.setFont(new java.awt.Font("Andalus",1,22));
				}
				{
					cashValueJTextField = new JTextField();
					this.add(cashValueJTextField);
					cashValueJTextField.setBounds(1024, 281, 150, 32);
					cashValueJTextField.setFont(new java.awt.Font("Andalus",1,20));
				}
				{
					redeemPointsJLabel = new JLabel();
					this.add(redeemPointsJLabel);
					redeemPointsJLabel.setText("Redeem :");
					redeemPointsJLabel.setBounds(910, 323, 95, 32);
					redeemPointsJLabel.setFont(new java.awt.Font("Andalus",1,20));
				}
				{
					redeemJTextField = new JTextField();
					this.add(redeemJTextField);
					redeemJTextField.setBounds(1023, 325, 150, 32);
					redeemJTextField.setEnabled(false);
					redeemJTextField.setBackground(new java.awt.Color(212,208,200));
					if(souvenirStoreApp.getProductBill().getCustomer() instanceof Member) {
						redeemJTextField.setEnabled(true);
						redeemJTextField.setBackground(new java.awt.Color(255,255,255));
					}	
				}
				{
					paymentJButton = new JButton();
					this.add(paymentJButton);
					paymentJButton.setText("Payment");
					paymentJButton.setBounds(910, 369, 130, 46);
					paymentJButton.setFont(new java.awt.Font("Andalus",1,22));
					paymentJButton.addActionListener(new ActionListener() {	
						@Override
						public void actionPerformed(ActionEvent arg0) {
							String cashStr = cashValueJTextField.getText().trim();
							String redeemStr = null;
							if(souvenirStoreApp.getProductBill().getCustomer() instanceof Member) {
								redeemStr = redeemJTextField.getText().trim();
							}
							double cash = -1;
							int redeem = 0;
							try {
								cash = Double.parseDouble(cashStr);
								if(redeemStr != null && !"".equals(redeemStr)) {
									redeem = Integer.parseInt(redeemStr);
								}	
							} catch (Exception e) {
								System.out.println("cash or redeem points input for the payment error.");
								return;
							}
							if(cash < 0 || redeem < 0) {
								System.out.println("cash or redeem points input for the payment <0 error.");
								return;
							}
							double returnChange = -1;
							try {
								returnChange = souvenirStoreApp.makePayment(cash, redeem);
							} catch (BillException e) {
								AlertDialog alertDialog = new AlertDialog(windowFrame, e.getMessage());
								alertDialog.setVisible(true);
								return;
							}
							if(returnChange < 0) {
								System.out.println("returnChange for bill error.");
								return;
							}
							billPanel.remove(BillContentPanel_Process.this);
							BillContentPanel_After contentJPanel = new BillContentPanel_After(souvenirStoreApp, billPanel, windowFrame, mainmenuPanel, returnChange);
							billPanel.add(contentJPanel);
							billPanel.refreshPanel();
						}
					});
				}
				{
					cancelJButton = new JButton();
					this.add(cancelJButton);
					cancelJButton.setText("Cancel");
					cancelJButton.setBounds(1051, 369, 128, 46);
					cancelJButton.setFont(new java.awt.Font("Andalus",1,22));
					cancelJButton.addActionListener(new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent arg0) {
							if(!souvenirStoreApp.cancelProductBill()) {
								System.out.println("cancel bill error.");
							}
							mainmenuPanel.reSetSpecificPanel(new WelcomePanel(souvenirStoreApp));
							mainmenuPanel.refreshPanel();
						}});
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void refresh() {
		ArrayList<BillItem> billItems = souvenirStoreApp.getProductBill().getBillItems();
		products = new String[billItems.size() + 1][4];
		products[0] = new String[] { "Product ID", "Product Name", "Unit Price", "Quantity" };
		for(int i = 0; i < billItems.size(); i++) {
			BillItem billItem = billItems.get(i);
			products[i + 1] = new String[] {billItem.getProduct().getId(), billItem.getProduct().getName(), billItem.getProduct().getPrice() + "", billItem.getQuantity() + ""};
		}
		productsListJTableModel = new DefaultTableModel(products, columnNames); 
		productsListJTable.setModel(productsListJTableModel);
		for(int i = 0; i < productsListJTable.getColumnCount(); i++) {
			productsListJTable.getColumnModel().getColumn(i).setCellRenderer(new Renderer());
		}
		totalPriceValueJLabel.setText("$ " + souvenirStoreApp.getProductBill().getTotalPrice());
		discountValueJLabel.setText((int)(souvenirStoreApp.getProductBill().getDiscount() * 100) + " %");
		discountPriceValueJLabel.setText("$ " + souvenirStoreApp.getProductBill().getTotalPriceAfterDiscount());
		updateUI();
		repaint();
	}
	
	private class Renderer extends DefaultTableCellRenderer {
		
		private static final long serialVersionUID = 1L;

		public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column){
            if (row == 0)
                setBackground(Color.ORANGE);
            else 
            	setBackground(null);
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        }
		
	}
	
}
