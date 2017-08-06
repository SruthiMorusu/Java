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

import iss.nus.edu.sg.SouvinierStore.ReceiptPrinter;
import iss.nus.edu.sg.SouvinierStore.SouvenirStoreApp;
import iss.nus.edu.sg.SouvinierStore.system.BillException;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class BillContentPanel_After extends JPanel {

	private static final long serialVersionUID = 1L;

	// add for receipt scoll. 28-Mar-2014, Xu Lei.
	private JButton mainMenuJButton;
	private JButton printJButton;
	private JLabel receiptJLabel;
	private JTextArea receiptJTextArea;
	private JScrollPane jScrollPaneForReceipt;
	private JLabel changeValueJLabel;
	private JLabel changeJLabel;
	private JPanel receiptJPanel;
	private JPanel changeJPanel;
	
	private ReceiptPrinter printer;
	private SouvenirStoreApp souvenirStoreApp;
	private BillPanel billPanel;
	// just for constructor of the dialog.
	private WindowFrame windowFrame;
	private MainMenuPanel mainmenuPanel;
	double returnChange = -1;

	public BillContentPanel_After(SouvenirStoreApp souvenirStoreApp, BillPanel billPanel, 
			WindowFrame windowFrame, MainMenuPanel mainmenuPanel, double returnChange) {
		super();
		this.souvenirStoreApp = souvenirStoreApp;
		this.billPanel = billPanel;
		this.windowFrame = windowFrame;
		this.mainmenuPanel = mainmenuPanel;
		this.returnChange = returnChange;
		printer = SouvenirStoreApp.getInstance().getReceiptPrinter(windowFrame);
		initGUI();
	}
	
	private void initGUI() {
		try {
			{
				this.setPreferredSize(new java.awt.Dimension(1200, 437));
				BorderLayout thisLayout = new BorderLayout();
				this.setLayout(thisLayout);
				{
					changeJPanel = new JPanel();
					this.add(changeJPanel, BorderLayout.NORTH);
					changeJPanel.setPreferredSize(new java.awt.Dimension(1200, 55));
					changeJPanel.setLayout(null);
					{
						changeJLabel = new JLabel();
						changeJPanel.add(changeJLabel);
						changeJLabel.setText("Change :");
						changeJLabel.setBounds(413, 5, 169, 43);
						changeJLabel.setFont(new java.awt.Font("Andalus",1,36));
					}
					{
						changeValueJLabel = new JLabel();
						changeJPanel.add(changeValueJLabel);
						changeValueJLabel.setText("$ " + returnChange);
						changeValueJLabel.setBounds(624, 5, 184, 43);
						changeValueJLabel.setFont(new java.awt.Font("Andalus",1,36));
					}
				}
				{
					receiptJPanel = new JPanel();
					this.add(receiptJPanel, BorderLayout.CENTER);
					receiptJPanel.setLayout(null);
					{
						jScrollPaneForReceipt = new JScrollPane();
						receiptJPanel.add(jScrollPaneForReceipt);
						jScrollPaneForReceipt.setBounds(50, 28, 720, 323);
						jScrollPaneForReceipt.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
						{
							receiptJTextArea = new JTextArea();
							jScrollPaneForReceipt.add(receiptJTextArea);
							jScrollPaneForReceipt.setViewportView(receiptJTextArea);
							//receiptJTextArea.setBounds(50, 28, 720, 323);
							receiptJTextArea.setFont(new java.awt.Font("Andalus",0,18));
							receiptJTextArea.setEditable(false);
							//receiptJTextArea.setPreferredSize(new java.awt.Dimension(720, 800));
							receiptJTextArea.setText(souvenirStoreApp.getProductBill().getEReceipt());
						}
					}				
					{
						receiptJLabel = new JLabel();
						receiptJPanel.add(receiptJLabel);
						receiptJLabel.setText("Receipt");
						receiptJLabel.setBounds(311, 0, 94, 28);
						receiptJLabel.setFont(new java.awt.Font("Andalus",1,24));
					}
					{
						printJButton = new JButton();
						receiptJPanel.add(printJButton);
						printJButton.setText("Print Receipt");
						printJButton.setBounds(872, 87, 204, 73);
						printJButton.setFont(new java.awt.Font("Andalus",1,24));
						printJButton.addActionListener(new ActionListener(){
							@Override
							public void actionPerformed(ActionEvent e) {
								try {
									souvenirStoreApp.getProductBill().print(printer);
								} catch (BillException e1) {
									System.out.println("print receipt error.");
									return;
								}
							}});
					}
					{
						mainMenuJButton = new JButton();
						receiptJPanel.add(mainMenuJButton);
						mainMenuJButton.setText("Main Menu");
						mainMenuJButton.setBounds(872, 231, 204, 68);
						mainMenuJButton.setFont(new java.awt.Font("Andalus",1,24));
						mainMenuJButton.addActionListener(new ActionListener(){
							@Override
							public void actionPerformed(ActionEvent arg0) {
								mainmenuPanel.reSetSpecificPanel(new WelcomePanel(souvenirStoreApp));
								mainmenuPanel.refreshPanel();
							}});
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
