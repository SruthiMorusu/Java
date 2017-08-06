package iss.nus.edu.sg.SouvinierStore.GUI;

/**
 * Name					Date
 * ---------------------------------
 * Sruthi 				20.03.2014
 * 
 * Author Sruthi
 * Since 20.03.2014
 * 
 * Modified By Karthik*/

import iss.nus.edu.sg.SouvinierStore.SouvenirStoreApp;
import iss.nus.edu.sg.SouvinierStore.system.MemberDiscount;
import iss.nus.edu.sg.SouvinierStore.system.NonMemberDiscount;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;


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
public class DiscountPanel extends JPanel {

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
	private JScrollPane jScrollPane1;
	private JTextField DiscountDescriptionText;
	private JRadioButton AppAllRadio;
	private JRadioButton AppMemberRadio;
	private JLabel DiscountApplicableLabel;
	private JTextField DiscountPercentageText;
	private JLabel DiscountPercentageLabel;
	private AbstractAction CancelDiscountDialogAction;
	private ButtonGroup AppButtonsGroup;
	private AbstractAction AddDiscountDialogAction;
	private JPanel jPanel2;
	private JButton CancelDiscountDialogButton;
	private JButton AddDiscountDialogButton;
	private JTextField DiscountPeriodText;
	private JLabel DiscountPeriodLabel;
	private JLabel DiscountDateLabel;
	private JLabel DiscountDescriptionLabel;
	private JPanel jPanel1;
	private JTextField DiscountCodeText;
	private JLabel DiscountCodeLabel;
	private JLabel AddDiscountLabel;
	private JDialog AddDiscountDialog;
	private JTable jTable1;
	private JButton AddDiscountButton;
	private JPanel DiscountButtonsPanel;
	private JDatePickerImpl datePicker;
	private JLabel percentSymbolLabel;
	private AbstractAction AlwaysPeriodBoxAction;
	private AbstractAction AlwaysDateBoxAction;
	private JCheckBox AlwaysCheckBoxPeriod;
	// just for constructor of the dialog.
	private WindowFrame windowFrame;

	public DiscountPanel(SouvenirStoreApp souvenirStoreApp, WindowFrame windowFrame) {
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
				titleJLabel.setText("  Discount");
				titleJLabel.setPreferredSize(new java.awt.Dimension(1200, 44));
				titleJLabel.setFont(new java.awt.Font("Castellar",1,28));
			}
			{
				contentJPanel = new JPanel();
				GroupLayout contentJPanelLayout = new GroupLayout((JComponent)contentJPanel);
				this.add(contentJPanel, BorderLayout.CENTER);
				contentJPanel.setLayout(contentJPanelLayout);
				{
					DiscountButtonsPanel = new JPanel();
					GroupLayout DiscountButtonsPanelLayout = new GroupLayout((JComponent)DiscountButtonsPanel);
					DiscountButtonsPanel.setLayout(DiscountButtonsPanelLayout);
					DiscountButtonsPanel.setSize(1154, 40);
					DiscountButtonsPanel.setOpaque(false);
					{
						AddDiscountButton = new JButton();
						AddDiscountButton.setText("Add..");
						AddDiscountButton.setHorizontalTextPosition(SwingConstants.RIGHT);
						AddDiscountButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("iss/nus/edu/sg/SouvinierStore/GUI/add_icon.png")));
						AddDiscountButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								getAddDiscountDialog().setLocationRelativeTo(null);
								getAddDiscountDialog().setVisible(true);
								resetValues();
							}
						});
					}
					DiscountButtonsPanelLayout.setHorizontalGroup(DiscountButtonsPanelLayout.createSequentialGroup()
						.addComponent(AddDiscountButton, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(1063, Short.MAX_VALUE));
					DiscountButtonsPanelLayout.setVerticalGroup(DiscountButtonsPanelLayout.createSequentialGroup()
						.addComponent(AddDiscountButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 0, Short.MAX_VALUE));
				}
				{
					jScrollPane1 = new JScrollPane();
					{
						TableModel jTable1Model = 
								new DefaultTableModel(
										getDisplayDiscountList(),
										new String[] { "Discount Code", "Discount", "Start Date", "Discount Period", "Percentage", "Applicable To" });
						jTable1 = new JTable(){
							public boolean isCellEditable(int rowIndex, int ColumnIndex)
							{
								return false;
							}
						};
						jScrollPane1.setViewportView(jTable1);
						jTable1.setModel(jTable1Model);
						jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
						jTable1.getColumnModel().getColumn(0).setMinWidth(0);
						jTable1.getColumnModel().getColumn(0).setPreferredWidth(0);
						jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
						jTable1.setPreferredSize(new java.awt.Dimension(1108, 333));
					}
				}
				contentJPanelLayout.setHorizontalGroup(contentJPanelLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(contentJPanelLayout.createParallelGroup()
					    .addGroup(contentJPanelLayout.createSequentialGroup()
					        .addComponent(DiscountButtonsPanel, GroupLayout.PREFERRED_SIZE, 1152, GroupLayout.PREFERRED_SIZE))
					    .addGroup(GroupLayout.Alignment.LEADING, contentJPanelLayout.createSequentialGroup()
					        .addGap(26)
					        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 1126, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(36, Short.MAX_VALUE));
				contentJPanelLayout.setVerticalGroup(contentJPanelLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(DiscountButtonsPanel, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addGap(25)
					.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 339, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(20, Short.MAX_VALUE));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String[][] getDisplayDiscountList(){
		List<NonMemberDiscount> tempDiscListNM = new ArrayList<NonMemberDiscount>();
		List<MemberDiscount> tempDiscListM = new ArrayList<MemberDiscount>();
		tempDiscListNM = SouvenirStoreApp.getInstance().getAllCustomerDisconts(); 
		tempDiscListM = SouvenirStoreApp.getInstance().getMemberDisconts();
		int size = tempDiscListNM.size(); 
		int totalSize = size+tempDiscListM.size();
		String [][] returnProdList = new String[totalSize][(2*totalSize)];
		int i;
		for(i=0;i<size;i++){
			returnProdList[i][0] = tempDiscListNM.get(i).getCode(); 
			returnProdList[i][1] = tempDiscListNM.get(i).getDescription();
			if(tempDiscListNM.get(i).getStartDate()==null){
				returnProdList[i][2] = "ALWAYS";
			}
			else{
				Date date = tempDiscListNM.get(i).getStartDate();
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				returnProdList[i][2] = dateFormat.format(date);
			}			  
			if(tempDiscListNM.get(i).getdaysPeriod()==-1){
				returnProdList[i][3] = "ALWAYS";
			}
			else{
				returnProdList[i][3] = Integer.toString(tempDiscListNM.get(i).getdaysPeriod());
			}
			returnProdList[i][4] = Integer.toString(tempDiscListNM.get(i).getPercentage());
			returnProdList[i][5] = "All";
		}
		int j = i;
		for(i=0;i<tempDiscListM.size();i++){
			returnProdList[j][0] = tempDiscListM.get(i).getCode(); 
			returnProdList[j][1] = tempDiscListM.get(i).getDescription();
			if(tempDiscListM.get(i).getStartDate()==null){
				returnProdList[j][2] = "ALWAYS";
			}
			else{
				Date date = tempDiscListM.get(i).getStartDate();
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				returnProdList[j][2] = dateFormat.format(date);
			}			  
			if(tempDiscListM.get(i).getdaysPeriod()==-1){
				returnProdList[j][3] = "ALWAYS";
			}
			else{
				returnProdList[j][3] = Integer.toString(tempDiscListM.get(i).getdaysPeriod());
			}
			returnProdList[j][4] = Integer.toString(tempDiscListM.get(i).getPercentage());
			returnProdList[j][5] = "Member";
			j++;
		}
		return returnProdList;
	}	

	private JDialog getAddDiscountDialog() {
		if(AddDiscountDialog == null) {
			AddDiscountDialog = new JDialog();
			GroupLayout AddDiscountDialogLayout = new GroupLayout((JComponent)AddDiscountDialog.getContentPane());
			AddDiscountDialog.getContentPane().setLayout(AddDiscountDialogLayout);
			AddDiscountDialog.setPreferredSize(new java.awt.Dimension(281, 374));
			AddDiscountDialog.setSize(281, 374);
			AddDiscountDialogLayout.setHorizontalGroup(AddDiscountDialogLayout.createSequentialGroup()
				.addContainerGap()
				.addGroup(AddDiscountDialogLayout.createParallelGroup()
				    .addGroup(GroupLayout.Alignment.LEADING, AddDiscountDialogLayout.createSequentialGroup()
				        .addComponent(getAddDiscountLabel(), GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
				        .addGap(0, 98, Short.MAX_VALUE))
				    .addComponent(getJPanel1(), GroupLayout.Alignment.LEADING, 0, 236, Short.MAX_VALUE)
				    .addGroup(GroupLayout.Alignment.LEADING, AddDiscountDialogLayout.createSequentialGroup()
				        .addGap(35)
				        .addComponent(getJPanel2(), GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
				        .addGap(0, 34, Short.MAX_VALUE)))
				.addContainerGap(17, 17));
			AddDiscountDialogLayout.setVerticalGroup(AddDiscountDialogLayout.createSequentialGroup()
				.addContainerGap()
				.addComponent(getAddDiscountLabel(), GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				.addGap(22)
				.addComponent(getJPanel1(), GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)
				.addGap(17)
				.addComponent(getJPanel2(), GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		}
		return AddDiscountDialog;
	}

	private JLabel getAddDiscountLabel() {
		if(AddDiscountLabel == null) {
			AddDiscountLabel = new JLabel();
			AddDiscountLabel.setText("Add Discount..");
			AddDiscountLabel.setFont(new java.awt.Font("Calibri",1,20));
		}
		return AddDiscountLabel;
	}

	private JLabel getDiscountCodeLabel() {
		if(DiscountCodeLabel == null) {
			DiscountCodeLabel = new JLabel();
			DiscountCodeLabel.setText("Discount Code");
		}
		return DiscountCodeLabel;
	}

	private JTextField getDiscountCodeText() {
		if(DiscountCodeText == null) {
			DiscountCodeText = new JTextField();
		}
		return DiscountCodeText;
	}

	private JPanel getJPanel1() {
		if(jPanel1 == null) {
			jPanel1 = new JPanel();
			GroupLayout jPanel1Layout = new GroupLayout((JComponent)jPanel1);
			jPanel1.setLayout(jPanel1Layout);

			jPanel1Layout.setHorizontalGroup(jPanel1Layout.createSequentialGroup()
				.addGroup(jPanel1Layout.createParallelGroup()
				    .addComponent(getDiscountPercentageLabel(), GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
				    .addComponent(getDiscountPeriodLabel(), GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
				    .addComponent(getDiscountDateLabel(), GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
				    .addComponent(getDiscountDescriptionLabel(), GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
				    .addComponent(getDiscountCodeLabel(), GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
				    .addComponent(getDiscountApplicableLabel(), GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(jPanel1Layout.createParallelGroup()
				    .addGroup(GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
				        .addComponent(getAppMemberRadio(), GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
				        .addGap(0, 45, Short.MAX_VALUE))
				    .addGroup(jPanel1Layout.createSequentialGroup()
				        .addGap(7)
				        .addGroup(jPanel1Layout.createParallelGroup()
				            .addGroup(GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
				                .addComponent(getDiscountCodeText(), 0, 115, Short.MAX_VALUE)
				                .addGap(7))
				            .addGroup(GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
				                .addComponent(getDiscountDescriptionText(), 0, 115, Short.MAX_VALUE)
				                .addGap(7))
				            .addGroup(GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
				                .addComponent(getDiscountPercentageText(), 0, 106, Short.MAX_VALUE)
				                .addComponent(getPercentSymbolLabel(), GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				                .addGap(0, 7, GroupLayout.PREFERRED_SIZE))
				            .addGroup(GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
				                .addComponent(getDiscoutnPeriodText(), 0, 115, Short.MAX_VALUE)
				                .addGap(7))
				            .addGroup(GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
				                .addComponent(getDiscountDatePicker(), 0, 115, Short.MAX_VALUE)
				                .addGap(7))
				            .addGroup(jPanel1Layout.createSequentialGroup()
				                .addGap(45)
				                .addGroup(jPanel1Layout.createParallelGroup()
				                    .addGroup(GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
				                        .addComponent(getAlwaysCheckBoxPeriod(), 0, 70, Short.MAX_VALUE)
				                        .addGap(7))
				                    .addGroup(GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
				                        .addGap(30)
				                        .addComponent(getAppAllRadio(), GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))))))));
			jPanel1Layout.setVerticalGroup(jPanel1Layout.createSequentialGroup()
				.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(getDiscountCodeText(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(getDiscountCodeLabel(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(getDiscountDescriptionText(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(getDiscountDescriptionLabel(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				.addGroup(jPanel1Layout.createParallelGroup()
				    .addComponent(getDiscountDatePicker(), GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
				    .addGroup(GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
				        .addGap(11)
				        .addComponent(getDiscountDateLabel(), GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)))
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(getDiscoutnPeriodText(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(getDiscountPeriodLabel(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(getAlwaysCheckBoxPeriod(), GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(getDiscountPercentageText(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
				    .addComponent(getPercentSymbolLabel(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(getDiscountPercentageLabel(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				.addGroup(jPanel1Layout.createParallelGroup()
				    .addGroup(GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				        .addComponent(getAppAllRadio(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
				        .addComponent(getAppMemberRadio(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
				    .addGroup(GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
				        .addComponent(getDiscountApplicableLabel(), GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
				        .addGap(0, 8, Short.MAX_VALUE)))
				.addContainerGap());
		}
		getAppButtonsGroup().add(AppMemberRadio);
		getAppButtonsGroup().add(AppAllRadio);		
		return jPanel1;
	}

	private JLabel getDiscountDescriptionLabel() {
		if(DiscountDescriptionLabel == null) {
			DiscountDescriptionLabel = new JLabel();
			DiscountDescriptionLabel.setText("Description");
		}
		return DiscountDescriptionLabel;
	}

	private JTextField getDiscountDescriptionText() {
		if(DiscountDescriptionText == null) {
			DiscountDescriptionText = new JTextField();
			DiscountDescriptionText.setSize(120, 20);
		}
		return DiscountDescriptionText;
	}

	private JLabel getDiscountDateLabel() {
		if(DiscountDateLabel == null) {
			DiscountDateLabel = new JLabel();
			DiscountDateLabel.setText("Discount Date");
		}
		return DiscountDateLabel;
	}

	private JLabel getDiscountPeriodLabel() {
		if(DiscountPeriodLabel == null) {
			DiscountPeriodLabel = new JLabel();
			DiscountPeriodLabel.setText("Discount Period");
		}
		return DiscountPeriodLabel;
	}

	private JTextField getDiscoutnPeriodText() {
		if(DiscountPeriodText == null) {
			DiscountPeriodText = new JTextField();
			DiscountPeriodText.setSize(120, 20);
		}
		return DiscountPeriodText;
	}
	private JDatePickerImpl getDiscountDatePicker(){
		if(datePicker == null){
			UtilDateModel model = new UtilDateModel();
			Date current= new Date(System.currentTimeMillis());
			model.setDate(current.getYear() + 1900, current.getMonth(), current.getDate());
			model.setSelected(true);
			JDatePanelImpl datePanel = new JDatePanelImpl(model);
			datePicker = new JDatePickerImpl(datePanel);
			datePicker.setButtonFocusable(false);
			datePicker.setSize(120, 27);
		}
		return datePicker;
	}

	private JLabel getDiscountPercentageLabel() {
		if(DiscountPercentageLabel == null) {
			DiscountPercentageLabel = new JLabel();
			DiscountPercentageLabel.setText("Percentage");
		}
		return DiscountPercentageLabel;
	}

	private JTextField getDiscountPercentageText() {
		if(DiscountPercentageText == null) {
			DiscountPercentageText = new JTextField();
			DiscountPercentageText.setSize(120, 21);
		}
		return DiscountPercentageText;
	}

	private JLabel getDiscountApplicableLabel() {
		if(DiscountApplicableLabel == null) {
			DiscountApplicableLabel = new JLabel();
			DiscountApplicableLabel.setText("Applicable To");
		}
		return DiscountApplicableLabel;
	}

	private JRadioButton getAppMemberRadio() {
		if(AppMemberRadio == null) {
			AppMemberRadio = new JRadioButton();
			AppMemberRadio.setText("Members");
		}
		return AppMemberRadio;
	}

	private JRadioButton getAppAllRadio() {
		if(AppAllRadio == null) {
			AppAllRadio = new JRadioButton();
			AppAllRadio.setText("All");
		}
		return AppAllRadio;
	}

	private JButton getAddDiscountDialogButton() {
		if(AddDiscountDialogButton == null) {
			AddDiscountDialogButton = new JButton();
			AddDiscountDialogButton.setText("Add");
			AddDiscountDialogButton.setAction(getAddDiscountDialogAction());
		}
		return AddDiscountDialogButton;
	}

	private JButton getCancelDiscountDialogButton() {
		if(CancelDiscountDialogButton == null) {
			CancelDiscountDialogButton = new JButton();
			CancelDiscountDialogButton.setText("Cancel");
			CancelDiscountDialogButton.setAction(getCancelDiscountDialogAction());
		}
		return CancelDiscountDialogButton;
	}

	private JPanel getJPanel2() {
		if(jPanel2 == null) {
			jPanel2 = new JPanel();
			GroupLayout jPanel2Layout = new GroupLayout((JComponent)jPanel2);
			jPanel2.setLayout(jPanel2Layout);
			jPanel2Layout.setHorizontalGroup(jPanel2Layout.createSequentialGroup()
					.addComponent(getAddDiscountDialogButton(), GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addComponent(getCancelDiscountDialogButton(), GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 0, Short.MAX_VALUE));
			jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(getCancelDiscountDialogButton(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addComponent(getAddDiscountDialogButton(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE));
		}
		return jPanel2;
	}

	public void resetValues(){
		getDiscountDescriptionText().setText("");
		getDiscountCodeText().setText("");
		getDiscountPercentageText().setText("");
		getDiscoutnPeriodText().setText("");
		getDiscoutnPeriodText().setEnabled(true);
		getDiscoutnPeriodText().setEditable(true);
		datePicker.getComponent(1).setEnabled(true);
		getAppAllRadio().setSelected(true);
		getAppMemberRadio().setSelected(true);
		AlwaysCheckBoxPeriod.setSelected(false);
		AlwaysCheckBoxPeriod.setEnabled(true);
	}

	private AbstractAction getAddDiscountDialogAction() {
		if(AddDiscountDialogAction == null) {
			AddDiscountDialogAction = new AbstractAction("Add", null) {
				public void actionPerformed(ActionEvent evt) {
					int addCheck = performAddButton();
					if(addCheck == 0){
						JOptionPane.showMessageDialog(null,"Please fill in all the fields","Missing Data",
								JOptionPane.ERROR_MESSAGE);
					}
					else if(addCheck == 1){
						JOptionPane.showMessageDialog(null,"Discount Already Exists","Add Discount error",
								JOptionPane.ERROR_MESSAGE);
					}
					else if(addCheck == -1){
						JOptionPane.showMessageDialog(null,"Incorrecet Values entered!","Add Discount error",
								JOptionPane.ERROR_MESSAGE);
					}
					else{
						JOptionPane.showMessageDialog(null, "Discount Added successfully!!");
						getAddDiscountDialog().dispose();
					}
				}

				private int performAddButton() {
					String discountCode = DiscountCodeText.getText();
					String discountDescription = DiscountDescriptionText.getText();
					Date discountDate = (Date)getDiscountDatePicker().getModel().getValue();
					String discountPeriod = DiscountPeriodText.getText();
					DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
					if(dateFormat.format(discountDate).equals(dateFormat.format(Calendar.getInstance().getTime())) && discountPeriod.isEmpty()){
						discountDate = null;
					}
					else{
						discountDate = (Date)getDiscountDatePicker().getModel().getValue();
					}
					
					if(AlwaysCheckBoxPeriod.isSelected()){
						discountPeriod ="-1";
					}
					else{
						discountPeriod = DiscountPeriodText.getText();
					}
					String discountPercent = DiscountPercentageText.getText();
					char discountApplicable;
					if(AppAllRadio.isSelected()){
						discountApplicable = 'A';
					}
					else if(AppMemberRadio.isSelected()){
						discountApplicable = 'M';
					}
					else{
						return 0;
					}

					int discountPercentInt;
					int discountPeriodInt;

					if(discountCode==null || discountDescription.isEmpty() || discountPercent.isEmpty()
							|| discountPeriod.isEmpty()){
						return 0;
					}

					try{
						discountPercentInt = Integer.parseInt(discountPercent);
						discountPeriodInt = Integer.parseInt(discountPeriod);
					}
					catch (NumberFormatException e){
						return -1;
					}

					boolean temp = SouvenirStoreApp.getInstance().offerDiscounts(discountCode.toUpperCase(), discountDescription, discountDate, discountPeriodInt, discountPercentInt, discountApplicable);
					TableModel JProductTableModel = 
							new DefaultTableModel(
									getDisplayDiscountList(),
									new String[] { "Discount Code", "Discount", "Start Date", "Discount Period", "Percentage", "Applicable To" });
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
		return AddDiscountDialogAction;
	}

	private ButtonGroup getAppButtonsGroup() {
		if(AppButtonsGroup == null) {
			AppButtonsGroup = new ButtonGroup();
		}
		return AppButtonsGroup;
	}

	private AbstractAction getCancelDiscountDialogAction() {
		if(CancelDiscountDialogAction == null) {
			CancelDiscountDialogAction = new AbstractAction("Cancel", null) {
				public void actionPerformed(ActionEvent evt) {
					resetValues();
					getAddDiscountDialog().dispose();
				}
			};
		}
		return CancelDiscountDialogAction;
	}

	private JCheckBox getAlwaysCheckBoxPeriod() {
		if(AlwaysCheckBoxPeriod == null) {
			AlwaysCheckBoxPeriod = new JCheckBox();
			AlwaysCheckBoxPeriod.setText("Always");
			AlwaysCheckBoxPeriod.setAction(getAlwaysPeriodBoxAction());
		}
		return AlwaysCheckBoxPeriod;
	}

	private AbstractAction getAlwaysPeriodBoxAction() {
		if(AlwaysPeriodBoxAction == null) {
			AlwaysPeriodBoxAction = new AbstractAction("Always", null) {
				public void actionPerformed(ActionEvent evt) {
					if(AlwaysCheckBoxPeriod.isSelected()){
						getDiscoutnPeriodText().setEditable(false);
						getDiscoutnPeriodText().setEnabled(false);
					}
					else{
						getDiscoutnPeriodText().setEditable(true);
						getDiscoutnPeriodText().setEnabled(true);
					}
				}
			};
		}
		return AlwaysPeriodBoxAction;
	}
	
	private JLabel getPercentSymbolLabel() {
		if(percentSymbolLabel == null) {
			percentSymbolLabel = new JLabel();
			percentSymbolLabel.setText("%");
		}
		return percentSymbolLabel;
	}

}
