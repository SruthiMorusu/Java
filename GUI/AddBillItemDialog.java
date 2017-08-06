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

import iss.nus.edu.sg.SouvinierStore.BarCodeReaderByConsole;
import iss.nus.edu.sg.SouvinierStore.SouvenirStoreApp;
import iss.nus.edu.sg.SouvinierStore.system.BillException;

import java.awt.Frame;
import java.awt.Panel;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class AddBillItemDialog extends StandardDialog {
	
	private static final long serialVersionUID = 1L;
	
	private JLabel alertJLabel;
	private BarCodeReaderByConsole productCodeText;
	private JLabel productCodeJLabel;
	private JLabel quantityJLabel;
	private JTextField quantityJTextField;
	private SouvenirStoreApp souvenirStoreApp;
	// for add a dialog.
	private Frame frame;
	private BillContentPanel_Process billProcess;

	public AddBillItemDialog(Frame parent, String title, SouvenirStoreApp souvenirStoreApp, BillContentPanel_Process billProcess) {
		super(parent, title);
		this.setSize(443, 230);
		this.souvenirStoreApp = souvenirStoreApp;
		this.billProcess = billProcess;
		this.frame = parent;
	}

	@Override
	protected Panel createFormPanel() {
		Panel p = new Panel();
		p.setLayout(null);
		p.setPreferredSize(new java.awt.Dimension(443, 159));
		initGUI(p);
		return p;
	}

	@Override
	protected boolean performOkAction() {
		String productCode = productCodeText.getText().trim();
		String quantityStr = quantityJTextField.getText().trim();
		int quantity = -1;
		if(productCode == null || "".equals(productCode) || quantityStr == null || "".equals(quantityStr)) {
			return false;
		}
		try {
			quantity = Integer.parseInt(quantityStr);
		} catch (Exception e) {
			System.out.println("user input product quantity error.");
			return false;
		}
		if(!souvenirStoreApp.validateProductExist(productCode)) {
			alertJLabel.setText("Product not exist.");
			return false;
		}
		// now is ok for add. 
		try {
			souvenirStoreApp.addProductToBill(productCode, quantity);
			billProcess.refresh();
			return true;
		} catch (BillException billException) {
			AlertDialog alert = new AlertDialog(frame, billException.getMessage());
			alert.setVisible(true);
			return false;
		}
	}
	
	private void initGUI(Panel p) {
		try {
			{
				p.setPreferredSize(new java.awt.Dimension(443, 155));
				{
					productCodeText = new BarCodeReaderByConsole();
					p.add(productCodeText);
					productCodeText.setBounds(235, 31, 121, 28);
				}
				{
					productCodeJLabel = new JLabel();
					p.add(productCodeJLabel);
					productCodeJLabel.setText("Product Code :");
					productCodeJLabel.setBounds(92, 33, 109, 25);
					productCodeJLabel.setFont(new java.awt.Font("Andalus",1,16));
				}
				{
					quantityJTextField = new JTextField();
					p.add(quantityJTextField);
					quantityJTextField.setBounds(235, 95, 121, 28);
				}
				{
					quantityJLabel = new JLabel();
					p.add(quantityJLabel);
					quantityJLabel.setText("Quantity :");
					quantityJLabel.setBounds(92, 95, 77, 25);
					quantityJLabel.setFont(new java.awt.Font("Andalus",1,16));
				}
				{
					alertJLabel = new JLabel();
					alertJLabel.setBounds(235, 63, 121, 13);
					alertJLabel.setBackground(new java.awt.Color(255,0,0));
					p.add(alertJLabel);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
