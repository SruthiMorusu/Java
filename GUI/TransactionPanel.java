package iss.nus.edu.sg.SouvinierStore.GUI;

/**
 * Name					Date
 * ---------------------------------
 * Sruthi 			20.03.2014
 * 
 * @author Sruthi 
 * Since 20.03.2014
 * 
 * Modified By Chandrakala*/

import iss.nus.edu.sg.SouvinierStore.SouvenirStoreApp;
import iss.nus.edu.sg.SouvinierStore.system.BillItem;
import iss.nus.edu.sg.SouvinierStore.system.Transaction;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class TransactionPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	
	private JLabel titleJLabel;
	private JPanel contentJPanel;
	// for build relationship with the logical module must through the SouvenirStoreApp.
	private SouvenirStoreApp souvenirStoreApp;
	// just for constructor of the dialog.
	private WindowFrame windowFrame;
	private JPanel jPanel1;
	//private JButton TransactionButton;
	private JScrollPane jScrollPane1;
	private JLabel EndDateLabel;
	private JLabel StartDateLabel;
	private JTable jTable1;
	//private AbstractAction TransactionButtonAction;
	private SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
	private AbstractAction GetTransactionAction;
	private JButton GetTransactionButton;
	private JDatePickerImpl startDatePicker;
	private JDatePickerImpl endDatePicker;
    private TableModel jTable1Model;
    private AbstractAction ViewReportAction;
    private JButton ViewReportButton;
    private JDialog ReportDialog;
    private JTable ReportTable;
	private JLabel ReportLabel;
	private JScrollPane jScrollPanelReport;
    
	public TransactionPanel(SouvenirStoreApp souvenirStoreApp, WindowFrame windowFrame) {
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
				this.add(titleJLabel, BorderLayout.NORTH);
				titleJLabel.setText("  Transaction");
				titleJLabel.setPreferredSize(new java.awt.Dimension(1200, 44));
				titleJLabel.setFont(new java.awt.Font("Castellar",1,28));
			}
			{
				contentJPanel = new JPanel();
				GroupLayout contentJPanelLayout = new GroupLayout((JComponent)contentJPanel);
				this.add(contentJPanel, BorderLayout.CENTER);
				contentJPanel.setLayout(contentJPanelLayout);
				contentJPanel.setPreferredSize(new java.awt.Dimension(1200, 369));
				{
					jPanel1 = new JPanel();
					GroupLayout jPanel1Layout = new GroupLayout((JComponent)jPanel1);
					jPanel1.setLayout(jPanel1Layout);
					{
						StartDateLabel = new JLabel();
						StartDateLabel.setText("Start Date");
						StartDateLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
						
					}
					{
						EndDateLabel = new JLabel();
						EndDateLabel.setText("End Date");
						
					}
					{
						GetTransactionButton = new JButton();
						GetTransactionButton.setText("Get Transactions");
						GetTransactionButton.setAction(getGetTransactionAction());

					}
					jPanel1Layout.setHorizontalGroup(jPanel1Layout.createSequentialGroup()
						.addContainerGap(74, 74)
						.addComponent(StartDateLabel, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
						.addGap(25)
						.addComponent(getStartDatePicker(), GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE)
						.addGap(100)
						.addComponent(EndDateLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(25)
						.addComponent(getEndDatePicker(), GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
						.addGap(100)
						.addComponent(GetTransactionButton, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
						.addGap(31)
						.addComponent(getViewReportButton(), GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(94, Short.MAX_VALUE));
					jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup()
						.addGroup(GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						    .addComponent(StartDateLabel, GroupLayout.Alignment.BASELINE, 0, 40, Short.MAX_VALUE)
						    .addComponent(EndDateLabel, GroupLayout.Alignment.BASELINE, 0, 40, Short.MAX_VALUE)
						    .addComponent(GetTransactionButton, GroupLayout.Alignment.BASELINE, 0, 40, Short.MAX_VALUE)
							.addComponent(getViewReportButton(), GroupLayout.Alignment.BASELINE, 0, 40, GroupLayout.PREFERRED_SIZE))
						.addGroup(jPanel1Layout.createSequentialGroup()
						    .addGap(12)
						    .addGroup(jPanel1Layout.createParallelGroup()
						        .addGroup(jPanel1Layout.createSequentialGroup()
						            .addComponent(getStartDatePicker(), GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
						        .addGroup(jPanel1Layout.createSequentialGroup()
						            .addComponent(getEndDatePicker(), GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)))
						    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 2, Short.MAX_VALUE)));
				}
				{
					jScrollPane1 = new JScrollPane();
					{
					   jTable1Model = 
								new DefaultTableModel(
										null,
										new String[] { "Transaction ID", "Product ID", "Member ID","Quantity Purchased", "Date"});
						jTable1 = new JTable(){
							public boolean isCellEditable(int rowIndex, int ColumnIndex)
							{
								return false;
							}
						
				};				
				jScrollPane1.setViewportView(jTable1);
				jTable1.setModel(jTable1Model);
				jTable1.setPreferredSize(new java.awt.Dimension(1092, 458));
				jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			}
		}
		contentJPanelLayout.setHorizontalGroup(contentJPanelLayout.createParallelGroup()
			.addGroup(GroupLayout.Alignment.LEADING, contentJPanelLayout.createSequentialGroup()
			    .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 1100, GroupLayout.PREFERRED_SIZE)
			    .addContainerGap(108, Short.MAX_VALUE))
			.addGroup(GroupLayout.Alignment.LEADING, contentJPanelLayout.createSequentialGroup()
			    .addGap(46)
			    .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 1106, GroupLayout.PREFERRED_SIZE)
			    .addContainerGap(48, Short.MAX_VALUE)));
		contentJPanelLayout.setVerticalGroup(contentJPanelLayout.createSequentialGroup()
			.addContainerGap()
			.addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
			.addGap(25)
			.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
			.addContainerGap(60, Short.MAX_VALUE));
	}
		
	}
catch (Exception e) {
	e.printStackTrace();
}}

			private String[][] getDisplayTransactionList(Date startDate, Date endDate){
				List<Transaction> tempTransactionList = new ArrayList<Transaction>();
				tempTransactionList = SouvenirStoreApp.getInstance().getTransactions(startDate, endDate);
				if(tempTransactionList == null){
					return new String[0][5];
				}
				int size = 0; 
				for(Transaction transaction: tempTransactionList){
					size += transaction.getBillItems().size();
				}
				int index = 0;
				    String [][] returnTransactionList = new String[size][2*size];
				    for(Transaction transaction: tempTransactionList){
					ArrayList<BillItem> billItems = transaction.getBillItems();
					System.out.println(billItems.size());
					for(BillItem billItem : billItems){
						returnTransactionList[index][0] =Integer.toString(transaction.getTransactionId()); 
						returnTransactionList[index][1] = billItem.getProduct().getId();
						returnTransactionList[index][2] = transaction.getCustomer().getId();
						returnTransactionList[index][3] = Integer.toString(billItem.getQuantity());
						returnTransactionList[index][4] = dateFormat.format(transaction.getDate());
						index++;
					}
					    }
					return returnTransactionList;
					}
				
			private JDatePickerImpl getStartDatePicker(){
				if(startDatePicker == null){
					UtilDateModel model = new UtilDateModel();
					Date current= new Date(System.currentTimeMillis());
					model.setDate(current.getYear() + 1900, current.getMonth(), current.getDate());
					model.setSelected(true);
					JDatePanelImpl datePanel = new JDatePanelImpl(model);
					startDatePicker = new JDatePickerImpl(datePanel);
				}
				return startDatePicker;
			}
			private JDatePickerImpl getEndDatePicker(){
				if(endDatePicker == null){
					UtilDateModel model = new UtilDateModel();
					Date current= new Date(System.currentTimeMillis());
					model.setDate(current.getYear() + 1900, current.getMonth(), current.getDate());
					model.setSelected(true);
					JDatePanelImpl datePanel = new JDatePanelImpl(model);
					endDatePicker = new JDatePickerImpl(datePanel);
				}
				return endDatePicker;
			}
			private String[][] getDisplayReportList(Date startDate, Date endDate){
				ArrayList<Transaction> tempTransactionList = new ArrayList<Transaction>();
				tempTransactionList = SouvenirStoreApp.getInstance().getTransactions(startDate, endDate);
				if(tempTransactionList == null){
					return new String[0][6];
				}
				int size = 0; 
				for(Transaction transaction: tempTransactionList){
					size += transaction.getBillItems().size();
				}
				int index = 0;
				ArrayList<String[]> displayDataList = new ArrayList<String[]>();
				for(Transaction transaction: tempTransactionList){
					ArrayList<BillItem> billItems = transaction.getBillItems();
						for(BillItem billItem : billItems){
							index = 0;
							for(int i = 0; i < displayDataList.size(); i++){
								String productId = displayDataList.get(i)[1];
								if(billItem.getProduct().getId().compareToIgnoreCase(productId) >= 0){
									index ++;
								}else{
									break;
								}
							}
							
							String [] tmpData= new String[6];
							tmpData[0] =Integer.toString(transaction.getTransactionId()); 
							tmpData[1] = billItem.getProduct().getId();
							if(billItem.getProduct().getName() == null || billItem.getProduct().getName().isEmpty()){
								tmpData[2] = "NOT EXIST";
							}else{
								tmpData[2] = billItem.getProduct().getName();
							}
							tmpData[3] = billItem.getProduct().getDescription();
							tmpData[4] = Integer.toString(billItem.getQuantity());
							tmpData[5] = dateFormat.format(transaction.getDate());
							displayDataList.add(index, tmpData);
						}
					}
				String [][] returnTransactionList = new String[size][6];
				for(int i = 0; i < displayDataList.size(); i++){
					String[] tmpStringArr = displayDataList.get(i);
					returnTransactionList[i][0] = tmpStringArr[0];
					returnTransactionList[i][1] = tmpStringArr[1];
					returnTransactionList[i][2] = tmpStringArr[2];
					returnTransactionList[i][3] = tmpStringArr[3];
					returnTransactionList[i][4] = tmpStringArr[4];
					returnTransactionList[i][5] = tmpStringArr[5];
				}
				return returnTransactionList;
			}
			private JTable getReportTable() {
				if(ReportTable == null) {
					Date startDate = (Date)startDatePicker.getModel().getValue();
					Date endDate = (Date)endDatePicker.getModel().getValue();
					TableModel ReportTableModel = 
							new DefaultTableModel(
									getDisplayReportList(startDate, endDate),
									new String[] {"Transaction No.", "Product id", "Product Name", "Product description","Quantity","Date"});
					ReportTable = new JTable(){
						public boolean isCellEditable(int rowIndex, int ColumnIndex)
						{
							return false;
						}
					};
					ReportTable.setModel(ReportTableModel);
					ReportTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
				}else{
					Date startDate = (Date)startDatePicker.getModel().getValue();
					Date endDate = (Date)endDatePicker.getModel().getValue();
					TableModel ReportTableModel = 
							new DefaultTableModel(
									getDisplayReportList(startDate, endDate),
									new String[] {"Transaction No.", "Product id", "Product Name", "Product description","Quantity","Date"});
					ReportTable.setModel(ReportTableModel);
					ReportTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
				}
				return ReportTable;
			}
			private JScrollPane getJScrollPaneReport() {
				if(jScrollPanelReport == null) {
					jScrollPanelReport = new JScrollPane();
					jScrollPanelReport.setViewportView(getReportTable());
				}else{
					jScrollPanelReport.setViewportView(getReportTable());
				}
				return jScrollPanelReport;
			}
			private JDialog getReportDialog() {
				if(ReportDialog == null) {
					ReportDialog = new JDialog();
					GroupLayout ReportDialogLayout = new GroupLayout((JComponent)ReportDialog.getContentPane());
					ReportDialog.getContentPane().setLayout(ReportDialogLayout);
					ReportDialogLayout.setVerticalGroup(ReportDialogLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(getReportLabel(), GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addGap(29)
						.addComponent(getJScrollPaneReport(), GroupLayout.PREFERRED_SIZE, 343, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(17, Short.MAX_VALUE));
					ReportDialogLayout.setHorizontalGroup(ReportDialogLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(ReportDialogLayout.createParallelGroup()
						    .addComponent(getJScrollPaneReport(), GroupLayout.Alignment.LEADING, 0, 500, Short.MAX_VALUE)
						    .addGroup(GroupLayout.Alignment.LEADING, ReportDialogLayout.createSequentialGroup()
						    	.addGap(0, 50, Short.MAX_VALUE)
						        .addComponent(getReportLabel(), GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE)
						        .addGap(0, 101, Short.MAX_VALUE)))
						.addContainerGap());
					ReportDialog.setSize(900, 500);
				}else{
					GroupLayout ReportDialogLayout = new GroupLayout((JComponent)ReportDialog.getContentPane());
					ReportDialog.getContentPane().setLayout(ReportDialogLayout);
					ReportDialogLayout.setVerticalGroup(ReportDialogLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(getReportLabel(), GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addGap(29)
						.addComponent(getJScrollPaneReport(), GroupLayout.PREFERRED_SIZE, 343, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(17, Short.MAX_VALUE));
					ReportDialogLayout.setHorizontalGroup(ReportDialogLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(ReportDialogLayout.createParallelGroup()
						    .addComponent(getJScrollPaneReport(), GroupLayout.Alignment.LEADING, 0, 500, Short.MAX_VALUE)
						    .addGroup(GroupLayout.Alignment.LEADING, ReportDialogLayout.createSequentialGroup()
						    	.addGap(0, 50, Short.MAX_VALUE)
						        .addComponent(getReportLabel(), GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE)
						        .addGap(0, 101, Short.MAX_VALUE)))
						.addContainerGap());
					ReportDialog.setSize(900, 500);
				}
				return ReportDialog;
			}
			private JLabel getReportLabel() {
				if(ReportLabel == null) {
					Date startDate = (Date)startDatePicker.getModel().getValue();
					Date endDate = (Date)endDatePicker.getModel().getValue();
					ReportLabel = new JLabel();
					ReportLabel.setText("TRANSACTION REPORT (" + dateFormat.format(startDate) + " ~ " + dateFormat.format(endDate) + ")");
					ReportLabel.setFont(new java.awt.Font("Calibri",1,20));
					ReportLabel.setHorizontalAlignment(SwingConstants.CENTER);
					ReportLabel.setHorizontalTextPosition(SwingConstants.CENTER);
				}else{
					Date startDate = (Date)startDatePicker.getModel().getValue();
					Date endDate = (Date)endDatePicker.getModel().getValue();
					ReportLabel.setText("TRANSACTION REPORT (" + dateFormat.format(startDate) + " ~ " + dateFormat.format(endDate) + ")");
				}
				return ReportLabel;
			}
			private AbstractAction getGetTransactionAction() {
				if(GetTransactionAction == null) {
					GetTransactionAction = new AbstractAction("Get Transactions", null) {
						public void actionPerformed(ActionEvent evt) {
							Date startDate = (Date)startDatePicker.getModel().getValue();
							Date endDate = (Date)endDatePicker.getModel().getValue();
							if(startDate == null || endDate == null || startDate.after(endDate)){
								return;
							}
							jTable1Model = 
									new DefaultTableModel(
											getDisplayTransactionList(startDate, endDate),
											new String[] { "Transaction ID", "Product ID", "Member ID","Quantity Purchased", "Date"});
							jTable1 = new JTable(){
								public boolean isCellEditable(int rowIndex, int ColumnIndex)
								{
									return false;
								}
							};
							jScrollPane1.setViewportView(jTable1);
							jTable1.setModel(jTable1Model);
							jTable1.setPreferredSize(new java.awt.Dimension(1092, 458));
							jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
							jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
						
						}
					};
				}
			
				return GetTransactionAction;
			}
			private AbstractAction getViewReportAction() {
				if(ViewReportAction == null) {
					ViewReportAction = new AbstractAction("View Report", null) {
						public void actionPerformed(ActionEvent evt) {
							getReportDialog().setLocationRelativeTo(null);
							getReportDialog().setVisible(true);
						}
					};
				}
				return ViewReportAction;
			}
			private JButton getViewReportButton() {
				if(ViewReportButton == null) {
					ViewReportButton = new JButton();
					ViewReportButton.setText("View Report");
					ViewReportButton.setAction(getViewReportAction());
				}
				return ViewReportButton;
			}

	}

	
