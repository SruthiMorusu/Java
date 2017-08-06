package iss.nus.edu.sg.SouvinierStore.GUI;

/**
 * Name					Date
 * ---------------------------------
 * Sruthi			20.03.2014
 * 
 * @author Sruthi
 * Since 20.03.2014
 * 
 * Modified By Chandrakala*/

import iss.nus.edu.sg.SouvinierStore.SouvenirStoreApp;
import iss.nus.edu.sg.SouvinierStore.system.Member;

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

public class MemberPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int MEMBER_NUM_LIMITATION = 10;

	private JLabel titleJLabel;
	private JPanel contentJPanel;
	private JPanel jPanel1;
	private JButton AddMemberButton;
	private JTextField MemberIdText;
	private JLabel MemberIdLabel;
	private JDialog AddMemberDialog;
	private JTextField MemberNameText;
	private JLabel MemberNameLabel;
	private JLabel AddMemberDialogLabel;
	private JPanel jPanel2;
	private JPanel jPanel3;
	private JButton AddMemberDialogButton;
	private JButton CancelMember;
	private AbstractAction CancelMemberDialog;
	private AbstractAction AddMemberDialogAction;
	private JTable jTable1;
	private JButton DeleteMemberButton;
	private JScrollPane jScrollPane1;
	private JButton ViewReportButton;
	private AbstractAction ViewReportButtonAction;
	private JTable MembersReportTble;
	private JLabel MembersReportLabel;
	private JDialog MemberReportDialog;
	private JScrollPane jScrollPane2;
	

	// for build relationship with the logical module must through the SouvenirStoreApp.
	private SouvenirStoreApp souvenirStoreApp;
	// just for constructor of the dialog.
	private WindowFrame windowFrame;

	public MemberPanel(SouvenirStoreApp souvenirStoreApp, WindowFrame windowFrame) {
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
				titleJLabel.setText("  Member");
				titleJLabel.setPreferredSize(new java.awt.Dimension(1200, 44));
				titleJLabel.setFont(new java.awt.Font("Castellar",1,28));
			}
			{
				contentJPanel = new JPanel();
				GroupLayout contentJPanelLayout = new GroupLayout((JComponent)contentJPanel);
				this.add(contentJPanel, BorderLayout.CENTER);
				contentJPanel.setLayout(contentJPanelLayout);
				{
					jPanel1 = new JPanel();
					GroupLayout jPanel1Layout = new GroupLayout((JComponent)jPanel1);
					jPanel1.setLayout(jPanel1Layout);
					{
						AddMemberButton = new JButton();
						AddMemberButton.setText("Add..");
						AddMemberButton.setHorizontalTextPosition(SwingConstants.RIGHT);
						AddMemberButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("iss/nus/edu/sg/SouvinierStore/GUI/add_icon.png")));
						AddMemberButton.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent evt) {
								getAddMemberDialog().setLocationRelativeTo(null);
								getAddMemberDialog().setVisible(true);
								resetValues();
								checkAndUpdateMemberDeleteFunction();
							}
						});
						}
					{
						DeleteMemberButton = new JButton();
						DeleteMemberButton.setText("Delete");
						DeleteMemberButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("iss/nus/edu/sg/SouvinierStore/GUI/delete_icon.png")));
						DeleteMemberButton.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent evt) {
								int deleteOption = JOptionPane.showConfirmDialog(null,"Are you sure you want ot delete this member? You can always add it again.",
										"Delete Member Confirmation",JOptionPane.YES_NO_OPTION);
								if(deleteOption == 0){
									int deleteCheck = performDeleteButton();
									if(deleteCheck == 0){
										JOptionPane.showMessageDialog(null,"Could Not Delete. Member Minimum Limit Reached!!","Delete Error",
												JOptionPane.ERROR_MESSAGE);
									}
									else if(deleteCheck == -1){
										JOptionPane.showMessageDialog(null,"Please Select the required record to delete","Delete Error",
												JOptionPane.ERROR_MESSAGE);
									}
									else{
										checkAndUpdateMemberDeleteFunction();
										JOptionPane.showMessageDialog(null, "Member Deleted successfully!!");
									}
								}
							}
							private int performDeleteButton() {
								int rowToDelete = jTable1.getSelectedRow();

								if(rowToDelete == -1){
									return -1;
								}
								String memberId = (String) jTable1.getModel().getValueAt(rowToDelete, 1);
								boolean temp = SouvenirStoreApp.getInstance().deleteMember(memberId);
								TableModel jTable1Model = 
										new DefaultTableModel(
												getDisplayMemberList(),
												new String[] { "Member Name", "Member ID", "Member Points"});
								jTable1.setModel(jTable1Model);
								if(temp){
									return 1;
								}
								else{
									return 0;
								}
							}					
						});
						DeleteMemberButton.setEnabled(false);
			}
					jPanel1Layout.setHorizontalGroup(jPanel1Layout.createSequentialGroup()
							.addContainerGap(46, 46)
							.addComponent(AddMemberButton, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
							.addGap(31)
							.addComponent(DeleteMemberButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(764)
							.addComponent(getViewReportButton(), GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(23, Short.MAX_VALUE));
						jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(AddMemberButton, GroupLayout.Alignment.BASELINE, 0, 40, Short.MAX_VALUE)
							.addComponent(DeleteMemberButton, GroupLayout.Alignment.BASELINE, 0, 40, Short.MAX_VALUE)
							.addComponent(getViewReportButton(), GroupLayout.Alignment.BASELINE, 0, 40, Short.MAX_VALUE));
					}
				{
					jScrollPane1 = new JScrollPane();
					{
						TableModel jTable1Model = 
								new DefaultTableModel(
										getDisplayMemberList(),
										new String[] { "Member Name", "Member ID", "Member Points"});
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
						.addContainerGap(100, Short.MAX_VALUE))
						.addGroup(GroupLayout.Alignment.LEADING, contentJPanelLayout.createSequentialGroup()
								.addGap(46)
								.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 1106, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(48, Short.MAX_VALUE)));
		contentJPanelLayout.setVerticalGroup(contentJPanelLayout.createSequentialGroup().addContainerGap()
				.addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
				.addGap(25)
				.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(40, Short.MAX_VALUE));
	}

				
			
		}catch(Exception e){
			e.printStackTrace();
		}
		checkAndUpdateMemberDeleteFunction();		
	}
	private void checkAndUpdateMemberDeleteFunction(){
		int memberNum = SouvenirStoreApp.getInstance().getMemberList().size();
		if(memberNum > MEMBER_NUM_LIMITATION){
			DeleteMemberButton.setEnabled(true);
		}else{
			DeleteMemberButton.setEnabled(false);
		}
	}
					private String[][] getDisplayMemberList(){
					List<Member> tempMemberList = new ArrayList<Member>();
					tempMemberList = SouvenirStoreApp.getInstance().getMemberList();					
					int size = tempMemberList.size(); 
					String [][] returnMemberList = new String[size][(2*size)];
					for(int i=0;i<size;i++){
						returnMemberList[i][0] = tempMemberList.get(i).getName();
						returnMemberList[i][1] = tempMemberList.get(i).getId();
						returnMemberList[i][2] = Integer.toString(tempMemberList.get(i).getPoint());  
					}
					return returnMemberList;
				}


				private void resetValues(){
					getMemberNameText().setText("");
					getMemberIdText().setText("");
				}

				private JDialog getAddMemberDialog() {
					if(AddMemberDialog == null) {
						AddMemberDialog = new JDialog();
						GroupLayout AddProductDialogLayout = new GroupLayout((JComponent)AddMemberDialog.getContentPane());
						AddMemberDialog.getContentPane().setLayout(AddProductDialogLayout);
						AddMemberDialog.setPreferredSize(new java.awt.Dimension(348, 429));
						AddMemberDialog.setSize(348, 429);
						AddProductDialogLayout.setHorizontalGroup(AddProductDialogLayout.createSequentialGroup()
								.addContainerGap()
								.addGroup(AddProductDialogLayout.createParallelGroup()
										.addGroup(GroupLayout.Alignment.LEADING, AddProductDialogLayout.createSequentialGroup()
												.addComponent(getAddMemberDialogLabel(), GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
												.addGap(0, 128, Short.MAX_VALUE))
												.addGroup(AddProductDialogLayout.createSequentialGroup()
														.addPreferredGap(getAddMemberDialogLabel(), getJPanel2(), LayoutStyle.ComponentPlacement.INDENT)
														.addGroup(AddProductDialogLayout.createParallelGroup()
																.addGroup(AddProductDialogLayout.createSequentialGroup()
																		.addComponent(getJPanel2(), GroupLayout.PREFERRED_SIZE, 277, GroupLayout.PREFERRED_SIZE))
																		.addGroup(AddProductDialogLayout.createSequentialGroup()
																				.addComponent(getJPanel3(), GroupLayout.PREFERRED_SIZE, 277, GroupLayout.PREFERRED_SIZE)))
																				.addGap(0, 0, Short.MAX_VALUE)))
																				.addContainerGap(31, 31));
						AddProductDialogLayout.setVerticalGroup(AddProductDialogLayout.createSequentialGroup()
								.addContainerGap()
								.addComponent(getAddMemberDialogLabel(), GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addGap(19)
								.addComponent(getJPanel2(), GroupLayout.PREFERRED_SIZE, 253, GroupLayout.PREFERRED_SIZE)
								.addGap(34)
								.addComponent(getJPanel3(), GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(23, Short.MAX_VALUE));
					}
					return AddMemberDialog;
				}

				private JLabel getAddMemberDialogLabel() {
					if(AddMemberDialogLabel == null) {
						AddMemberDialogLabel = new JLabel();
						AddMemberDialogLabel.setText("Add Member..");
						AddMemberDialogLabel.setFont(new java.awt.Font("Calibri",1,20));
					}
					return AddMemberDialogLabel;
				}

				private JPanel getJPanel2() {
					if(jPanel2 == null) {
						jPanel2 = new JPanel();
						GroupLayout jPanel2Layout = new GroupLayout((JComponent)jPanel2);
						jPanel2.setLayout(null);
						jPanel2.add(getMemberNameLabel());
						jPanel2.add(getMemberNameText());
						jPanel2.add(getMemberIdLabel());
						jPanel2.add(getMemberIdText());
						}
					return jPanel2;
				}

				private JLabel getMemberNameLabel() {
					if(MemberNameLabel == null) {
						MemberNameLabel = new JLabel();
						MemberNameLabel.setText("Member Name");
						MemberNameLabel.setBounds(0, 39, 81, 16);
					}
					return MemberNameLabel;
				}


				private JTextField getMemberNameText() {
					if(MemberNameText == null) {
						MemberNameText = new JTextField();
						MemberNameText.setBounds(131, 37, 146, 22);
					}
					return MemberNameText;
				}

				private JLabel getMemberIdLabel() {
					if(MemberIdLabel == null) {
						MemberIdLabel = new JLabel();
						MemberIdLabel.setText("Member ID");
						MemberIdLabel.setBounds(0, 71, 113, 16);
					}
					return MemberIdLabel;
				}

				private JTextField getMemberIdText() {
					if(MemberIdText == null) {
						MemberIdText = new JTextField();
						MemberIdText.setBounds(131, 69, 146, 22);
					}
					return MemberIdText;
				}

				private JButton getAddMemberDialogButton() {
					if(AddMemberDialogButton == null) {
						AddMemberDialogButton = new JButton();
						AddMemberDialogButton.setText("Add Product");
						AddMemberDialogButton.setAction(getAddMemberDialogAction());
					}
					return AddMemberDialogButton;
				}

				private JButton getCancelMember() {
					if(CancelMember == null) {
						CancelMember = new JButton();
						CancelMember.setText("Cancel");
						CancelMember.setAction(getCancelMemberDialog());
					}
					return CancelMember;
				}


				private JPanel getJPanel3() {
					if(jPanel3 == null) {
						jPanel3 = new JPanel();
						GroupLayout jPanel3Layout = new GroupLayout((JComponent)jPanel3);
						jPanel3.setLayout(jPanel3Layout);
						jPanel3Layout.setHorizontalGroup(jPanel3Layout.createSequentialGroup()
								.addGap(8)
								.addComponent(getAddMemberDialogButton(), GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
								.addGap(0, 15, Short.MAX_VALUE)
								.addComponent(getCancelMember(), GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
								.addContainerGap());
						jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(getCancelMember(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(getAddMemberDialogButton(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE));
					}
					return jPanel3;
				}

				private AbstractAction getAddMemberDialogAction() {
					if(AddMemberDialogAction == null) {
						AddMemberDialogAction = new AbstractAction("Add Member", null) {
							public void actionPerformed(ActionEvent evt) {
								int addCheck = performAddButton();
								if(addCheck == 0){
									JOptionPane.showMessageDialog(null,"Please fill in all the fields","Missing Data",
											JOptionPane.ERROR_MESSAGE);
								}
								else if(addCheck == 1){
									JOptionPane.showMessageDialog(null,"Member Already Exists","Add Member error",
											JOptionPane.ERROR_MESSAGE);
								}
								else if(addCheck == -1){
									JOptionPane.showMessageDialog(null,"Incorrecet Values entered!","Add Member error",
											JOptionPane.ERROR_MESSAGE);
								}
								else{
									int continueOption = JOptionPane.showConfirmDialog(null,"Member Successfully added!! Add anotther Member?",
											"Confirmation",JOptionPane.YES_NO_OPTION);
									if(continueOption == 0){
										getAddMemberDialog().setLocationRelativeTo(null);
										getAddMemberDialog().setVisible(true);
										resetValues();
									}
									else{
										getAddMemberDialog().dispose();
									}
								}
							}

							private int performAddButton() {
								String memberName = MemberNameText.getText();

								
								String memberId = MemberIdText.getText();


								if(memberName.isEmpty() || memberId.isEmpty()){
									return 0;
								}

								
								boolean temp = SouvenirStoreApp.getInstance().registerMember(memberName, memberId);
								TableModel JMemberTableModel = 
										new DefaultTableModel(
												getDisplayMemberList(),
												new String[] { "Member Name", "Member ID", "Member Points"});
								jTable1.setModel(JMemberTableModel);
								if(temp){
									return 2;
								}
								else{
									return 1;
								}					
							}
						};
					}
					return AddMemberDialogAction;
				}

				private AbstractAction getCancelMemberDialog() {
					if(CancelMemberDialog == null) {
						CancelMemberDialog = new AbstractAction("Cancel", null) {
			
							public void actionPerformed(ActionEvent evt) {
								resetValues();
								getAddMemberDialog().dispose();
							}
						};
					}
					return CancelMemberDialog;
	}
				
				private JButton getViewReportButton() {
					if(ViewReportButton == null) {
						ViewReportButton = new JButton();
						ViewReportButton.setText("View Report");
						ViewReportButton.setAction(getViewReportButtonAction());
					}
					return ViewReportButton;
				}
				
				private AbstractAction getViewReportButtonAction() {
					if(ViewReportButtonAction == null) {
						ViewReportButtonAction = new AbstractAction("View Report", null) {
							public void actionPerformed(ActionEvent evt) {
								getMemberReportDialog().setLocationRelativeTo(null);
								getMemberReportDialog().setVisible(true);
							}
						};
					}
					return ViewReportButtonAction;
				}
				
				private JDialog getMemberReportDialog() {
					if(MemberReportDialog == null) {
						MemberReportDialog = new JDialog();
						GroupLayout MemberReportDialogLayout = new GroupLayout((JComponent)MemberReportDialog.getContentPane());
						MemberReportDialog.getContentPane().setLayout(MemberReportDialogLayout);
						MemberReportDialog.setPreferredSize(new java.awt.Dimension(1200, 650));
						MemberReportDialog.setSize(1200, 650);
						MemberReportDialogLayout.setHorizontalGroup(MemberReportDialogLayout.createParallelGroup()
							.addComponent(getJScrollPane2(), GroupLayout.Alignment.LEADING, 0, 1184, Short.MAX_VALUE)
							.addGroup(GroupLayout.Alignment.LEADING, MemberReportDialogLayout.createSequentialGroup()
							    .addGap(0, 524, Short.MAX_VALUE)
							    .addComponent(getMembersReportLabel(), GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
							    .addContainerGap(492, 492)));
						MemberReportDialogLayout.setVerticalGroup(MemberReportDialogLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(getMembersReportLabel(), GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
							.addGap(16)
							.addComponent(getJScrollPane2(), 0, 550, Short.MAX_VALUE));
					}
					return MemberReportDialog;
				}
				
				private JLabel getMembersReportLabel() {
					if(MembersReportLabel == null) {
						MembersReportLabel = new JLabel();
						MembersReportLabel.setText("MEMBERS REPORT");
						MembersReportLabel.setFont(new java.awt.Font("Calibri",1,20));
						MembersReportLabel.setHorizontalTextPosition(SwingConstants.CENTER);
						MembersReportLabel.setHorizontalAlignment(SwingConstants.CENTER);
					}
					return MembersReportLabel;
				}
				
				private JTable getMembersReportTble() {
					if(MembersReportTble == null) {
						TableModel ProductsReportTbleModel = 
								new DefaultTableModel(
										getDisplayMemberList(),
										new String[] { "Member Name", "Member ID", "Member Points"});
						MembersReportTble = new JTable(){
							public boolean isCellEditable(int rowIndex, int ColumnIndex)
							{
								return false;
							}
						};
						MembersReportTble.setModel(ProductsReportTbleModel);
						MembersReportTble.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
					}
					return MembersReportTble;
				}
				
				private JScrollPane getJScrollPane2() {
					if(jScrollPane2 == null) {
						jScrollPane2 = new JScrollPane();
						jScrollPane2.setViewportView(getMembersReportTble());
					}
					return jScrollPane2;
				}


				 
				}







