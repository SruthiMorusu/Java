package iss.nus.edu.sg.SouvinierStore.GUI;

/** 
 * Name          	          Date 
 * -------------------------------------------------- 
 * Sruthi               20.03.2014 
 *  
 * Author : Sruthi
 * Since 20.03.2014
 * 
 * Modified By Karthik
 *  */


import iss.nus.edu.sg.SouvinierStore.SouvenirStoreApp;
import iss.nus.edu.sg.SouvinierStore.system.Category;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
public class CategoryPanel extends JPanel {

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	private static final long serialVersionUID = 1L;
	
	private JLabel titleJLabel;
	private JPanel contentJPanel;
	// for build relationship with the logical module must through the SouvenirStoreApp.
	private SouvenirStoreApp souvenirStoreApp;
	private JButton AddCategoryButton;
	private JDialog CategoriesReportDialog;
	private AbstractAction CancelCategoryDialogAction;
	private AbstractAction AddCategoryDialogAction;
	private JPanel jPanel2;
	private JButton CancelCategoryDialogButton;
	private JButton AddCategoryDialogButton;
	private JPanel jPanel1;
	private JTextField CategoryNameText;
	private JLabel CategoryNameLabel;
	private JTextField CategoryCodeText;
	private JLabel CategoryCodeLabel;
	private JLabel AddCategoryLabel;
	private AbstractAction CategoryViewReportAction;
	private JScrollPane jScrollPane2;
	private JTable CategoriesReportTable;
	private JLabel CategoriesReportLabel;
	private JDialog AddCategoryDialog;
	private JScrollPane jScrollPane1;
	private JTable CategoriesTable;
	private JButton ViewCategoryReportButton;
	private JPanel CategoriesButtonPanel;
	// just for constructor of the dialog.
	private WindowFrame windowFrame;

	public CategoryPanel(SouvenirStoreApp souvenirStoreApp, WindowFrame windowFrame) {
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
				titleJLabel.setText("  Category");
				titleJLabel.setPreferredSize(new java.awt.Dimension(1200, 44));
				titleJLabel.setFont(new java.awt.Font("Castellar",1,28));
			}
			{
				contentJPanel = new JPanel();
				GroupLayout contentJPanelLayout = new GroupLayout((JComponent)contentJPanel);
				this.add(contentJPanel, BorderLayout.CENTER);
				contentJPanel.setLayout(contentJPanelLayout);
				{
					CategoriesButtonPanel = new JPanel();
					GroupLayout CategoriesButtonPanelLayout = new GroupLayout((JComponent)CategoriesButtonPanel);
					CategoriesButtonPanel.setLayout(CategoriesButtonPanelLayout);
					CategoriesButtonPanel.setOpaque(false);
					{
						AddCategoryButton = new JButton();
						AddCategoryButton.setText("Add..");
						AddCategoryButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("iss/nus/edu/sg/SouvinierStore/GUI/add_icon.png")));
						AddCategoryButton.setHorizontalTextPosition(SwingConstants.RIGHT);
						AddCategoryButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								getAddCategoryDialog().setLocationRelativeTo(null);
								getAddCategoryDialog().setVisible(true);
								resetValues();
							}
						});
					}
					CategoriesButtonPanelLayout.setVerticalGroup(CategoriesButtonPanelLayout.createSequentialGroup()
						.addGroup(CategoriesButtonPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						    .addComponent(AddCategoryButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						    .addComponent(getViewCategoryReportButton(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED));
					CategoriesButtonPanelLayout.setHorizontalGroup(CategoriesButtonPanelLayout.createSequentialGroup()
						.addContainerGap(18, 18)
						.addComponent(AddCategoryButton, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
						.addGap(928, 928, GroupLayout.PREFERRED_SIZE)
						.addComponent(getViewCategoryReportButton(), GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
						.addContainerGap());
				}
				{
					jScrollPane1 = new JScrollPane();
					{
						TableModel CategoriesTableModel = 
								new DefaultTableModel(
										getDisplayCategoryList(),
										new String[] { "Category Code", "Category Name" });
						CategoriesTable = new JTable(){
							public boolean isCellEditable(int rowIndex, int ColumnIndex)
							{
								return false;
							}
						};
						jScrollPane1.setViewportView(CategoriesTable);
						CategoriesTable.setModel(CategoriesTableModel);
						CategoriesTable.getColumnModel().getColumn(0).setMaxWidth(0);
						CategoriesTable.getColumnModel().getColumn(0).setMinWidth(0);
						CategoriesTable.getColumnModel().getColumn(0).setPreferredWidth(0);
						CategoriesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						CategoriesTable.setPreferredSize(new java.awt.Dimension(1098, 283));
						CategoriesTable.setOpaque(false);
						CategoriesTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
					}
				}
				contentJPanelLayout.setHorizontalGroup(contentJPanelLayout.createParallelGroup()
					.addGroup(GroupLayout.Alignment.LEADING, contentJPanelLayout.createSequentialGroup()
					    .addComponent(CategoriesButtonPanel, GroupLayout.PREFERRED_SIZE, 1155, GroupLayout.PREFERRED_SIZE)
					    .addContainerGap(45, Short.MAX_VALUE))
					.addGroup(GroupLayout.Alignment.LEADING, contentJPanelLayout.createSequentialGroup()
					    .addGap(43)
					    .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 1112, GroupLayout.PREFERRED_SIZE)
					    .addContainerGap(45, Short.MAX_VALUE)));
				contentJPanelLayout.setVerticalGroup(contentJPanelLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(CategoriesButtonPanel, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addGap(25)
					.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 321, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(38, Short.MAX_VALUE));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String[][] getDisplayCategoryList(){
		ArrayList<String> displayString = new ArrayList<String>();
		List<Category> tempCategoryList = new ArrayList<Category>();
		Category category;
		displayString = SouvenirStoreApp.getInstance().getCategoriesReporting(); 
		for(String tempString : displayString){
			String[] columnValueList = tempString.split(",");
			category = new Category(columnValueList[0].trim(), columnValueList[1].trim());
			tempCategoryList.add(category);
		}
		int size = tempCategoryList.size(); 
		String [][] returnCategoryList = new String[size][(2*size)];
		for(int i=0;i<size;i++){
			returnCategoryList[i][0] = tempCategoryList.get(i).getCategoryCode();
			returnCategoryList[i][1] = tempCategoryList.get(i).getName();
		}
		return returnCategoryList;
	}
	
	private JDialog getAddCategoryDialog() {
		if(AddCategoryDialog == null) {
			AddCategoryDialog = new JDialog();
			GroupLayout AddCategoryDialogLayout = new GroupLayout((JComponent)AddCategoryDialog.getContentPane());
			AddCategoryDialog.getContentPane().setLayout(AddCategoryDialogLayout);
			AddCategoryDialog.setPreferredSize(new java.awt.Dimension(270, 236));
			AddCategoryDialog.setSize(270, 236);
			AddCategoryDialogLayout.setHorizontalGroup(AddCategoryDialogLayout.createSequentialGroup()
				.addContainerGap()
				.addGroup(AddCategoryDialogLayout.createParallelGroup()
				    .addGroup(GroupLayout.Alignment.LEADING, AddCategoryDialogLayout.createSequentialGroup()
				        .addComponent(getAddCategoryLabel(), GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
				        .addGap(0, 90, Short.MAX_VALUE))
				    .addGroup(AddCategoryDialogLayout.createSequentialGroup()
				        .addComponent(getJPanel1(), GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE)
				        .addGap(0, 0, Short.MAX_VALUE))
				    .addGroup(GroupLayout.Alignment.LEADING, AddCategoryDialogLayout.createSequentialGroup()
				        .addPreferredGap(getAddCategoryLabel(), getJPanel2(), LayoutStyle.ComponentPlacement.INDENT)
				        .addComponent(getJPanel2(), GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)
				        .addGap(0, 6, Short.MAX_VALUE)))
				.addContainerGap());
			AddCategoryDialogLayout.setVerticalGroup(AddCategoryDialogLayout.createSequentialGroup()
				.addContainerGap()
				.addComponent(getAddCategoryLabel(), GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				.addGap(27)
				.addComponent(getJPanel1(), GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(getJPanel2(), GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(140, Short.MAX_VALUE));
		}
		return AddCategoryDialog;
	}
	
	private JLabel getAddCategoryLabel() {
		if(AddCategoryLabel == null) {
			AddCategoryLabel = new JLabel();
			AddCategoryLabel.setText("Add Category..");
			AddCategoryLabel.setFont(new java.awt.Font("Calibri",1,20));
		}
		return AddCategoryLabel;
	}
	
	private JLabel getCategoryCodeLabel() {
		if(CategoryCodeLabel == null) {
			CategoryCodeLabel = new JLabel();
			CategoryCodeLabel.setText("Category Code");
		}
		return CategoryCodeLabel;
	}
	
	private JTextField getCategoryCodeText() {
		if(CategoryCodeText == null) {
			CategoryCodeText = new JTextField();
		}
		return CategoryCodeText;
	}
	
	private JLabel getCategoryNameLabel() {
		if(CategoryNameLabel == null) {
			CategoryNameLabel = new JLabel();
			CategoryNameLabel.setText("Category Name");
		}
		return CategoryNameLabel;
	}
	
	private JTextField getCategoryNameText() {
		if(CategoryNameText == null) {
			CategoryNameText = new JTextField();
		}
		return CategoryNameText;
	}
	
	private JPanel getJPanel1() {
		if(jPanel1 == null) {
			jPanel1 = new JPanel();
			GroupLayout jPanel1Layout = new GroupLayout((JComponent)jPanel1);
			jPanel1.setLayout(jPanel1Layout);
			jPanel1Layout.setVerticalGroup(jPanel1Layout.createSequentialGroup()
				.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(getCategoryCodeText(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(getCategoryCodeLabel(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(26)
				.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(getCategoryNameText(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(getCategoryNameLabel(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(74, 74));
			jPanel1Layout.setHorizontalGroup(jPanel1Layout.createSequentialGroup()
				.addGroup(jPanel1Layout.createParallelGroup()
				    .addComponent(getCategoryNameLabel(), GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
				    .addComponent(getCategoryCodeLabel(), GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				.addGroup(jPanel1Layout.createParallelGroup()
				    .addComponent(getCategoryNameText(), GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
				    .addComponent(getCategoryCodeText(), GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)));
		}
		return jPanel1;
	}
	
	private JButton getAddCategoryDialogButton() {
		if(AddCategoryDialogButton == null) {
			AddCategoryDialogButton = new JButton();
			AddCategoryDialogButton.setText("Add");
			AddCategoryDialogButton.setAction(getAddCategoryDialogAction());
		}
		return AddCategoryDialogButton;
	}
	
	private JButton getCancelCategoryDialogButton() {
		if(CancelCategoryDialogButton == null) {
			CancelCategoryDialogButton = new JButton();
			CancelCategoryDialogButton.setText("Cancel");
			CancelCategoryDialogButton.setAction(getCancelCategoryDialogAction());
		}
		return CancelCategoryDialogButton;
	}
	
	private JPanel getJPanel2() {
		if(jPanel2 == null) {
			jPanel2 = new JPanel();
			GroupLayout jPanel2Layout = new GroupLayout((JComponent)jPanel2);
			jPanel2.setLayout(jPanel2Layout);
			jPanel2Layout.setHorizontalGroup(jPanel2Layout.createSequentialGroup()
				.addComponent(getAddCategoryDialogButton(), GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				.addComponent(getCancelCategoryDialogButton(), GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
				.addGap(0, 6, Short.MAX_VALUE));
			jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				.addComponent(getCancelCategoryDialogButton(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
				.addComponent(getAddCategoryDialogButton(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE));
		}
		return jPanel2;
	}
	
	private AbstractAction getAddCategoryDialogAction() {
		if(AddCategoryDialogAction == null) {
			AddCategoryDialogAction = new AbstractAction("Add", null) {
				public void actionPerformed(ActionEvent evt) {
					int addCheck = performAddButton();
					if(addCheck == 0){
						JOptionPane.showMessageDialog(null,"Please fill in all the fields","Missing Data",
								JOptionPane.ERROR_MESSAGE);
					}
					else if(addCheck == 1){
						JOptionPane.showMessageDialog(null,"Category Already Exists","Add Category error",
								JOptionPane.ERROR_MESSAGE);
						getAddCategoryDialog().dispose();
					}
					else if(addCheck == -1){
						JOptionPane.showMessageDialog(null,"Incorrecet Values entered!","Add Category error",
								JOptionPane.ERROR_MESSAGE);
					}
					else{
						JOptionPane.showMessageDialog(null, "Category Added successfully!!");
						JOptionPane.showMessageDialog(null,"Please make sure you add Vendors to the newly added Category","Vendor Add Warning",JOptionPane.WARNING_MESSAGE);
						getAddCategoryDialog().dispose();
					}
				}

				private int performAddButton() {
					String categoryCode = CategoryCodeText.getText();
					String categoryName = CategoryNameText.getText();
					
					if(categoryCode.isEmpty() || categoryName.isEmpty()){
						return 0;
					}
					
					if(categoryCode.length()!=3){
						return -1;
					}
					
					boolean temp = SouvenirStoreApp.getInstance().addCategory(categoryCode.toUpperCase(), categoryName);
					TableModel JProductTableModel = 
							new DefaultTableModel(
									getDisplayCategoryList(),
									new String[] {"Category Code", "Category Name"});
					CategoriesTable.setModel(JProductTableModel);
					CategoriesTable.getColumnModel().getColumn(0).setMaxWidth(0);
					CategoriesTable.getColumnModel().getColumn(0).setMinWidth(0);
					CategoriesTable.getColumnModel().getColumn(0).setPreferredWidth(0);
					if(temp){
						return 2;
					}
					else{
						return 1;
					}					
				}
			};
		}
		return AddCategoryDialogAction;
	}
	
	private AbstractAction getCancelCategoryDialogAction() {
		if(CancelCategoryDialogAction == null) {
			CancelCategoryDialogAction = new AbstractAction("Cancel", null) {
				public void actionPerformed(ActionEvent evt) {
					resetValues();
					getAddCategoryDialog().dispose();
				}
			};
		}
		return CancelCategoryDialogAction;
	}
	
	private void resetValues(){
		CategoryCodeText.setText("");
		CategoryNameText.setText("");
	}
	
	private JDialog getCategoriesReportDialog() {
		if(CategoriesReportDialog == null) {
			CategoriesReportDialog = new JDialog();
			GroupLayout CategoriesReportDialogLayout = new GroupLayout((JComponent)CategoriesReportDialog.getContentPane());
			CategoriesReportDialog.getContentPane().setLayout(CategoriesReportDialogLayout);
			CategoriesReportDialogLayout.setVerticalGroup(CategoriesReportDialogLayout.createSequentialGroup()
				.addContainerGap()
				.addComponent(getCategoriesReportLabel(), GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
				.addGap(29)
				.addComponent(getJScrollPane2(), GroupLayout.PREFERRED_SIZE, 343, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(17, Short.MAX_VALUE));
			CategoriesReportDialogLayout.setHorizontalGroup(CategoriesReportDialogLayout.createSequentialGroup()
				.addContainerGap()
				.addGroup(CategoriesReportDialogLayout.createParallelGroup()
				    .addComponent(getJScrollPane2(), GroupLayout.Alignment.LEADING, 0, 361, Short.MAX_VALUE)
				    .addGroup(GroupLayout.Alignment.LEADING, CategoriesReportDialogLayout.createSequentialGroup()
				        .addGap(89)
				        .addComponent(getCategoriesReportLabel(), GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
				        .addGap(0, 101, Short.MAX_VALUE)))
				.addContainerGap());
			CategoriesReportDialog.setSize(401, 467);
		}
		return CategoriesReportDialog;
	}
	
	private JLabel getCategoriesReportLabel() {
		if(CategoriesReportLabel == null) {
			CategoriesReportLabel = new JLabel();
			CategoriesReportLabel.setText("CATEGORY REPORT");
			CategoriesReportLabel.setFont(new java.awt.Font("Calibri",1,20));
			CategoriesReportLabel.setHorizontalAlignment(SwingConstants.CENTER);
			CategoriesReportLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		}
		return CategoriesReportLabel;
	}
	
	private JTable getCategoriesReportTable() {
		if(CategoriesReportTable == null) {
			TableModel CategoriesReportTableModel = 
					new DefaultTableModel(
							getDisplayCategoryList(),
							new String[] {"Category Code", "Category Name"});
			CategoriesReportTable = new JTable(){
				public boolean isCellEditable(int rowIndex, int ColumnIndex)
				{
					return false;
				}
			};
			CategoriesReportTable.setModel(CategoriesReportTableModel);
			CategoriesReportTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		}
		return CategoriesReportTable;
	}
	
	private JScrollPane getJScrollPane2() {
		if(jScrollPane2 == null) {
			jScrollPane2 = new JScrollPane();
			jScrollPane2.setViewportView(getCategoriesReportTable());
		}
		return jScrollPane2;
	}
	
	private AbstractAction getCategoryViewReportAction() {
		if(CategoryViewReportAction == null) {
			CategoryViewReportAction = new AbstractAction("View Report", null) {
				public void actionPerformed(ActionEvent evt) {
					getCategoriesReportDialog().setLocationRelativeTo(null);
					getCategoriesReportDialog().setVisible(true);
				}
			};
		}
		return CategoryViewReportAction;
	}
	
	private JButton getViewCategoryReportButton() {
		if(ViewCategoryReportButton == null) {
			ViewCategoryReportButton = new JButton();
			ViewCategoryReportButton.setText("View Report");
			ViewCategoryReportButton.setAction(getCategoryViewReportAction());
		}
		return ViewCategoryReportButton;
	}

}
