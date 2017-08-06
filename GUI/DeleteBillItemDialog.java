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

import iss.nus.edu.sg.SouvinierStore.SouvenirStoreApp;

import java.awt.Frame;
import java.awt.Panel;

import javax.swing.JLabel;

public class DeleteBillItemDialog extends StandardDialog {

	private static final long serialVersionUID = 1L;

	private JLabel alertJLabel;
	private SouvenirStoreApp souvenirStoreApp;
	private BillContentPanel_Process billProcess;
	private String productId;

	public DeleteBillItemDialog(Frame parent, String title,
			SouvenirStoreApp souvenirStoreApp,
			BillContentPanel_Process billProcess, String productId) {
		super(parent, title);
		this.setSize(443, 161);
		// for get data by the structure.
		alertJLabel.setText("Do you really want to delete bill item " + productId + " ?");
		this.souvenirStoreApp = souvenirStoreApp;
		this.billProcess = billProcess;
		this.productId = productId;
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
		if(souvenirStoreApp.deleteProductFromBill(productId)) {
			billProcess.refresh();
		} else {
			System.out.println("delete product from bill error.");
		}
		return true;
	}

	private void initGUI(Panel p) {
		try {
			{
				p.setPreferredSize(new java.awt.Dimension(443, 71));
				{
					alertJLabel = new JLabel();
					p.add(alertJLabel);
					alertJLabel.setBounds(37, 22, 370, 27);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
