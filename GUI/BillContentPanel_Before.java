package iss.nus.edu.sg.SouvinierStore.GUI;

/**
 * Name					Date
 * ---------------------------------
 * Sruthi 				20.03.2014
 * Sruthi 				22.03.2014
 * 
 * before make a bill.
 * 
 * @author Sruthi
 * Since 20.03.2014
 * 
 * Modified By Sruthi*/

import iss.nus.edu.sg.SouvinierStore.BarCodeReaderByConsole;
import iss.nus.edu.sg.SouvinierStore.SouvenirStoreApp;
import iss.nus.edu.sg.SouvinierStore.system.CustomerManager;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BillContentPanel_Before extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JCheckBox memberJCheckBox;
	private JLabel memberIdJLabel;
	private BarCodeReaderByConsole memberIdText;  
	private JLabel ifMemberJLabel;
	private JButton processJButton;

	private SouvenirStoreApp souvenirStoreApp;
	private BillPanel billPanel;
	// just for constructor of the dialog.
	private WindowFrame windowFrame;
	private MainMenuPanel mainmenuPanel;
	
	public BillContentPanel_Before(SouvenirStoreApp souvenirStoreApp, BillPanel billPanel, 
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
				this.setPreferredSize(new java.awt.Dimension(1200, 437));
				this.setLayout(null);
				{
					processJButton = new JButton();
					this.add(processJButton);
					processJButton.setText("Process");
					processJButton.setBounds(470, 294, 217, 73);
					processJButton.setFont(new java.awt.Font("Andalus",1,36));
					processJButton.addActionListener(new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent e) {
							if(memberJCheckBox.isSelected()) {
								if(memberIdText.getText() != null && !"".equals(memberIdText.getText().trim())) {
									if(souvenirStoreApp.validateMember(memberIdText.getText())) {
										boolean ifSuccess = souvenirStoreApp.createNewBill(memberIdText.getText());
										if(!ifSuccess) {
											System.out.println("create bill error.");
											return;
										}
									} else {
										AlertDialog alertDialog = new AlertDialog(windowFrame, "Member not exist !");
										alertDialog.setVisible(true);
										return;
									}
								} else {
									AlertDialog alertDialog = new AlertDialog(windowFrame, "Member ID cannot be empty !");
									alertDialog.setVisible(true);
									return;
								}	
							} else {
								boolean ifSuccess = souvenirStoreApp.createNewBill(CustomerManager.NON_MEMBER_ID);
								if(!ifSuccess) {
									System.out.println("create bill error.");
									return;
								}
							}
							billPanel.remove(BillContentPanel_Before.this);
							BillContentPanel_Process contentJPanel = new BillContentPanel_Process(souvenirStoreApp, billPanel, windowFrame, mainmenuPanel);
							billPanel.add(contentJPanel);
							billPanel.refreshPanel();					
						}});
				}
				{
					memberJCheckBox = new JCheckBox();
					this.add(memberJCheckBox);
					memberJCheckBox.setText("Member");
					memberJCheckBox.setBounds(470, 184, 217, 54);
					memberJCheckBox.setFont(new java.awt.Font("Andalus",3,36));
					memberJCheckBox.addActionListener(new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent arg0) {
							if(memberJCheckBox.isSelected()) {
								memberIdText.setEnabled(true);
								memberIdText.setBackground(Color.BLACK);
							} else {
								memberIdText.setText("");
								memberIdText.setEnabled(false);
								memberIdText.setBackground(new java.awt.Color(212,208,200));
							}
						}});
				}
				{
					ifMemberJLabel = new JLabel();
					this.add(ifMemberJLabel);
					ifMemberJLabel.setText("If Member ?");
					ifMemberJLabel.setBounds(195, 12, 261, 56);
					ifMemberJLabel.setFont(new java.awt.Font("Andalus",1,36));
				}
				{
					memberIdText = new BarCodeReaderByConsole();
					this.add(memberIdText);
					memberIdText.setBounds(617, 107, 217, 44);
					memberIdText.setEnabled(false);
					memberIdText.setBackground(new java.awt.Color(212,208,200));
					memberIdText.setFont(new java.awt.Font("Andalus",1,20));
				}
				{
					memberIdJLabel = new JLabel();
					this.add(memberIdJLabel);
					memberIdJLabel.setText("Member ID : ");
					memberIdJLabel.setBounds(364, 103, 186, 48);
					memberIdJLabel.setFont(new java.awt.Font("Andalus",1,28));
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
