package iss.nus.edu.sg.SouvinierStore.GUI;

/**
 * Name					Date
 * ---------------------------------
 * Sruthi 				31.03.2014
 * 
 * @author Sruthi
 * Since 31.03.2014
 * 
 * Modified By Sruthi*/

import iss.nus.edu.sg.SouvinierStore.SouvenirStoreApp;
import iss.nus.edu.sg.SouvinierStore.system.Category;
import iss.nus.edu.sg.SouvinierStore.system.Vendor;

import java.awt.Frame;
import java.awt.Panel;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class AddVendorDialog extends StandardDialog {
	
	private static final long serialVersionUID = 1L;
	
	private JLabel vendorNameJLabel;
	private JLabel vendorDescrJLabel;
	private JTextField vendorNameJTextField;
	private JTextField vendorDescrJTextField;
	private SouvenirStoreApp souvenirStoreApp;
	// for add a dialog.
	//private Frame frame;
	private VendorPanel vendorPanel;
	private Category category;

	public AddVendorDialog(Frame parent, String title, SouvenirStoreApp souvenirStoreApp, VendorPanel vendorPanel, Category category) {
		super(parent, title);
		this.setSize(443, 230);
		this.souvenirStoreApp = souvenirStoreApp;
		this.vendorPanel = vendorPanel;
		this.category = category;
		//this.frame = parent;
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
		String verdorName = vendorNameJTextField.getText().trim();
		String vendorDescr = vendorDescrJTextField.getText().trim();
		if(verdorName == null || "".equals(verdorName)) {
			return false;
		}	
		if(vendorDescr == null || "".equals(vendorDescr)) {
			vendorDescr = "";
		}
		if(souvenirStoreApp.addVendor(category, new Vendor(verdorName, vendorDescr))) {
			vendorPanel.refresh(category);
			return true;
		} else {
			System.out.println("add vendor error.");
			return false;
		}		
	}
	
	private void initGUI(Panel p) {
		try {
			{
				p.setPreferredSize(new java.awt.Dimension(443, 155));
				{
					vendorNameJTextField = new JTextField();
					p.add(vendorNameJTextField);
					vendorNameJTextField.setBounds(250, 33, 121, 28);
				}
				{
					vendorNameJLabel = new JLabel();
					p.add(vendorNameJLabel);
					vendorNameJLabel.setText("Vendor Name :");
					vendorNameJLabel.setBounds(85, 33, 120, 25);
					vendorNameJLabel.setFont(new java.awt.Font("Andalus",1,16));
				}
				{
					vendorDescrJTextField = new JTextField();
					p.add(vendorDescrJTextField);
					vendorDescrJTextField.setBounds(250, 95, 121, 28);
				}
				{
					vendorDescrJLabel = new JLabel();
					p.add(vendorDescrJLabel);
					vendorDescrJLabel.setText("Description :");
					vendorDescrJLabel.setBounds(85, 95, 120, 25);
					vendorDescrJLabel.setFont(new java.awt.Font("Andalus",1,16));
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
