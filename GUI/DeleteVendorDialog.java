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

import java.awt.Frame;
import java.awt.Panel;

import javax.swing.JLabel;

import iss.nus.edu.sg.SouvinierStore.SouvenirStoreApp;
import iss.nus.edu.sg.SouvinierStore.system.Category;
import iss.nus.edu.sg.SouvinierStore.system.Vendor;

public class DeleteVendorDialog extends StandardDialog {

	private static final long serialVersionUID = 1L;

	private JLabel alertJLabel;
	private SouvenirStoreApp souvenirStoreApp;
	private VendorPanel vendorPanel;
	private Category category;
	private Vendor vendor;

	public DeleteVendorDialog(Frame parent, String title,
			SouvenirStoreApp souvenirStoreApp,
			VendorPanel vendorPanel, Category category, Vendor vendor) {
		super(parent, title);
		this.setSize(570, 161);
		// for get data by the structure.
		alertJLabel.setText("Do you really want to delete vendor " + vendor.getName() + 
				" of category " + category.getName() + " ?");
		this.souvenirStoreApp = souvenirStoreApp;
		this.vendorPanel = vendorPanel;
		this.category = category;
		this.vendor = vendor;
	}
	
	@Override
	protected Panel createFormPanel() {
		Panel p = new Panel();
		p.setLayout(null);
		initGUI(p);
		return p;
	}

	@Override
	protected boolean performOkAction() {
		if(souvenirStoreApp.deleteVendor(category, vendor)) {
			vendorPanel.refresh(category);
		} else {
			System.out.println("delete product from vendor " + vendor.getName() + 
				" of category " + category.getName() + " error.");
		}
		return true;
	}

	private void initGUI(Panel p) {
		try {
			{
				p.setPreferredSize(new java.awt.Dimension(570, 71));
				{
					alertJLabel = new JLabel();
					p.add(alertJLabel);
					alertJLabel.setBounds(37, 22, 530, 27);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
