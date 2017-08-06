package iss.nus.edu.sg.SouvinierStore.GUI;

/**
 * Name					Date
 * ---------------------------------
 * Xu Lei 			  20.03.2014
 * Xu Lei 			  30.03.2014
 * Xu Lei			  31.03.2014
 * Xu Lei			  01.04.2014
 * 
 * @author Xu Lei
 * Since 20.03.2014
 * 
 * Modified By Xu Lei*/

import iss.nus.edu.sg.SouvinierStore.SouvenirStoreApp;
import iss.nus.edu.sg.SouvinierStore.system.Category;
import iss.nus.edu.sg.SouvinierStore.system.Vendor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class VendorPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int ROW_HEIGHT = 20;
	
	private JScrollPane jScrollPaneForJList;
	private JLabel categoryForVendorJLabel;
	private JButton selectCategoryJButton;
	private JComboBox categoryForVendorJComboBox;
	// private JList specificVenorJList;
	private JTable vendorsListJTable;
	
	// JComboBoxModel values can dynamically get the list of category. 22-Mar-2014, Xu Lei. 
	private ComboBoxModel categoryForVendorJComboBoxModel;
	private StringBuilder categoriesForComboBox = new StringBuilder("Please Select");
	
	// for the values of the vendors list. 22-Mar-2014, Xu Lei. 
	// be instead of vendorsListJTableModel. 31-Mar-2014, Xu Lei.
	//private ListModel specificVenorJListModel;	
	
	private JLabel titleJLabel;
	private JPanel contentJPanel;
	// for build relationship with the logical module must through the SouvenirStoreApp.
	private SouvenirStoreApp souvenirStoreApp;
	// just for constructor of the dialog.
	private WindowFrame windowFrame;
	private JButton deleteJButton;
	private JButton addJButton;

	// data
	private String[][] vendors;
	private String[] columnNames = new String[] { "Vendor Name", "Description" };
	private TableModel vendorsListJTableModel;

	public VendorPanel(SouvenirStoreApp souvenirStoreApp, WindowFrame windowFrame) {
		super();
		this.souvenirStoreApp = souvenirStoreApp;
		this.windowFrame = windowFrame;
		initGUI();
	}
	
	private void initGUI() {
		try {
			BorderLayout thisLayout = new BorderLayout();
			this.setLayout(thisLayout);
			this.setPreferredSize(new java.awt.Dimension(1200, 481));
			this.setSize(1200, 481);
			{
				titleJLabel = new JLabel();
				this.add(titleJLabel, BorderLayout.NORTH);
				titleJLabel.setText("  Vendor");
				titleJLabel.setPreferredSize(new java.awt.Dimension(1200, 44));
				titleJLabel.setFont(new java.awt.Font("Castellar",1,28));
			}
			{
				contentJPanel = new JPanel();
				this.add(contentJPanel, BorderLayout.CENTER);
				contentJPanel.setLayout(null);
				{
					ArrayList<Category> listCategories = souvenirStoreApp.getCategories();
					for(Category c : listCategories) {
						categoriesForComboBox.append("," + c.getName());
					}
					categoryForVendorJComboBoxModel = new DefaultComboBoxModel(categoriesForComboBox.toString().split(","));
					categoryForVendorJComboBox = new JComboBox();
					contentJPanel.add(categoryForVendorJComboBox);
					categoryForVendorJComboBox.setModel(categoryForVendorJComboBoxModel);
					categoryForVendorJComboBox.setBounds(837, 14, 159, 31);
					categoryForVendorJComboBox.setFont(new java.awt.Font("Andalus",1,16));
				}
				{
					selectCategoryJButton = new JButton();
					contentJPanel.add(selectCategoryJButton);
					selectCategoryJButton.setText("Search");
					selectCategoryJButton.setBounds(1016, 13, 125, 31);
					selectCategoryJButton.addActionListener(new  ActionListener(){
						@Override
						public void actionPerformed(ActionEvent arg0) {
							int selectedIndex = categoryForVendorJComboBox.getSelectedIndex();
							Object selectedObj = categoryForVendorJComboBox.getSelectedItem();
							System.out.println("selectedObj: " + selectedIndex + " " + selectedObj.toString());
							if(selectedIndex != 0) {
								for(Category c : souvenirStoreApp.getCategories()) {
									if(selectedObj.toString().equals(c.getName())) {
										VendorPanel.this.refresh(c);
										break;
									}
								}
							} else {
								// back to 'please select'. 1-Apr-2014, Xu Lei.
								VendorPanel.this.refresh(null);
							}
						}});
				}
				{
					categoryForVendorJLabel = new JLabel();
					contentJPanel.add(categoryForVendorJLabel);
					categoryForVendorJLabel.setBounds(475, 12, 193, 31);
					categoryForVendorJLabel.setFont(new java.awt.Font("Andalus",1,22));
				}
				// add JScrollPane for the list of vendors. 28-Mar-2014, Xu Lei.
				{
					jScrollPaneForJList = new JScrollPane();
					contentJPanel.add(jScrollPaneForJList);
					jScrollPaneForJList.setBounds(50, 62, 1091, 348);
					jScrollPaneForJList.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
					{
						vendorsListJTableModel = new DefaultTableModel(vendors, columnNames);
						vendorsListJTable = new JTable(null, null, null);
						jScrollPaneForJList.add(vendorsListJTable);
						jScrollPaneForJList.setViewportView(vendorsListJTable);
						//specificVenorJList.setBounds(46, 49, 963, 356);
						//specificVenorJList.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
						vendorsListJTable.setPreferredSize(new java.awt.Dimension(1090, 345));
						vendorsListJTable.setRowHeight(ROW_HEIGHT);
						vendorsListJTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						vendorsListJTable.setModel(vendorsListJTableModel);
						//vendorsListJTable.setShowVerticalLines(false);
						vendorsListJTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
						vendorsListJTable.setFont(new java.awt.Font("Andalus",0,20));
						vendorsListJTable.setGridColor(new Color(200, 200, 200));
						vendorsListJTable.setSurrendersFocusOnKeystroke(true);
					}
				}
				{
					addJButton = new JButton();
					addJButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("iss/nus/edu/sg/SouvinierStore/GUI/add_icon.png")));
					contentJPanel.add(addJButton);
					addJButton.setText("Add");
					addJButton.setBounds(50, 15, 98, 28);
					addJButton.addActionListener(new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent arg0) {
							int selectedIndex = categoryForVendorJComboBox.getSelectedIndex();
							Object selectedObj = categoryForVendorJComboBox.getSelectedItem();
							System.out.println("addJButton selectedObj: " + selectedIndex + " " + selectedObj.toString());
							if(selectedIndex == 0) {
								AlertDialog alertDialog = new AlertDialog(windowFrame, "please choose one category first !");
								alertDialog.setVisible(true);
								return;
							}
							String currentCategoryName = categoryForVendorJLabel.getText().trim();
							System.out.println("add for currentCategoryName: " + currentCategoryName);
							Category category = null;
							for(Category c : souvenirStoreApp.getCategories()) {
								if(currentCategoryName.equals(c.getName())) {
									category = c;
									break;
								}
							}
							if(category != null) {
								AddVendorDialog addVendorDialog = new AddVendorDialog(windowFrame, "Add Vendor for" + category.getName(), 
										souvenirStoreApp, VendorPanel.this, category);
								addVendorDialog.setVisible(true);
							}	
						}});
				}
				{
					deleteJButton = new JButton();
					deleteJButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("iss/nus/edu/sg/SouvinierStore/GUI/delete_icon.png")));
					contentJPanel.add(deleteJButton);
					deleteJButton.setText("Delete");
					deleteJButton.setBounds(168, 15, 100, 28);
					deleteJButton.addActionListener(new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent e) {
							int selectedIndex = categoryForVendorJComboBox.getSelectedIndex();
							Object selectedObj = categoryForVendorJComboBox.getSelectedItem();
							System.out.println("deleteJButton selectedObj: " + selectedIndex + " " + selectedObj.toString());
							if(selectedIndex == 0) {
								AlertDialog alertDialog = new AlertDialog(windowFrame, "please choose one category first !");
								alertDialog.setVisible(true);
								return;
							}
							int rowSelectedIndex = vendorsListJTable.getSelectedRow();
							System.out.println("rowSelectedIndex: " + rowSelectedIndex);
							if(rowSelectedIndex >= 0) {
								Object vendorNameSelected = vendorsListJTable.getValueAt(rowSelectedIndex, 0);
								System.out.println("selected vendor name for delete: " + vendorNameSelected.toString());
								// 1. find the related category object.
								String currentCategoryName = categoryForVendorJLabel.getText().trim();
								System.out.println("delete for currentCategoryName: " + currentCategoryName);
								Category category = null;
								for(Category c : souvenirStoreApp.getCategories()) {
									if(currentCategoryName.equals(c.getName())) {
										category = c;
										break;
									}
								}
								// 2. find the related vendor object.
								Vendor vendor = null;
								if(category != null) {
									for(Vendor v : souvenirStoreApp.getVendorsForCertainCategory(category.getCategoryCode())) {
										if(vendorNameSelected.toString().trim().equals(v.getName())) {
											vendor = v;
											break;
										}
									}
								}
								if(category != null && vendor != null) {
									DeleteVendorDialog deleteVendorDialog = new DeleteVendorDialog(windowFrame, "Delete Vendor", 
											souvenirStoreApp, VendorPanel.this, category, vendor);
									deleteVendorDialog.setVisible(true);
								}	
							} else {
								AlertDialog alertDialog = new AlertDialog(windowFrame, "please choose one item you want to delete !");
								alertDialog.setVisible(true);
								return;
							}
						}});
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void refresh(Category category) {
		if(category != null) {
			vendors = new String[souvenirStoreApp.getVendorsForCertainCategory(category.getCategoryCode()).size()][2];
			StringBuilder vendorsForList = new StringBuilder("");
			categoryForVendorJLabel.setText(category.getName());
			ArrayList<Vendor> listVendors = souvenirStoreApp.getVendorsForCertainCategory(category.getCategoryCode());
			if(listVendors != null && listVendors.size() > 0) {
				for(int i = 0; i < listVendors.size(); i++) {
					vendors[i] = new String[] {listVendors.get(i).getName(), listVendors.get(i).getDescription()};
				}
				/*for(Vendor v : listVendors) {
					vendorsForList.append(v.getName() + "--------------" + v.getDescription() + ",");
				}
				specificVenorJListModel = 
						new DefaultComboBoxModel(vendorsForList.toString().substring(0, vendorsForList.toString().length() - 1).split(","));*/
			} else {
				// fix the bug if the list of vendor is nothing. 30-Mar-2014, Xu Lei.
				// specificVenorJListModel = new DefaultComboBoxModel(new String[0]);
			}	
		} else {
			// fix the BUG when back to 'please select'. 1-Apr-2014, Xu Lei.
			vendors = null;
		}
		// change from JList to JTable. 31-Mar-2014, Xu Lei.
		vendorsListJTableModel = new DefaultTableModel(vendors, columnNames);
		vendorsListJTable.setModel(vendorsListJTableModel);
		// jScrollPaneForJList.setViewportView(specificVenorJList);
		updateUI();
		repaint();
	}

}
