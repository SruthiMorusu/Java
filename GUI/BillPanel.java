package iss.nus.edu.sg.SouvinierStore.GUI;

/**
 * Name					Date
 * ---------------------------------
 * Sruthi 				20.03.2014
 * Sruthi 				23.03.2014
 * 
 * @author Sruthi
 * Since 20.03.2014
 * 
 * Modified By Sruthi*/

import iss.nus.edu.sg.SouvinierStore.SouvenirStoreApp;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class BillPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JLabel titleJLabel;
	// for build relationship with the logical module must through the SouvenirStoreApp.
	private SouvenirStoreApp souvenirStoreApp;
	// just for constructor of the dialog.
	private WindowFrame windowFrame;
	private MainMenuPanel mainmenuPanel;

	public BillPanel(SouvenirStoreApp souvenirStoreApp, WindowFrame windowFrame, MainMenuPanel mainmenuPanel) {
		super();
		this.souvenirStoreApp = souvenirStoreApp;
		this.windowFrame = windowFrame;
		this.mainmenuPanel = mainmenuPanel;
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
				titleJLabel.setText("  Checkout");
				titleJLabel.setPreferredSize(new java.awt.Dimension(1200, 44));
				titleJLabel.setFont(new java.awt.Font("Castellar",1,28));
			}
			{
				BillContentPanel_Before contentJPanel = new BillContentPanel_Before(souvenirStoreApp, this, windowFrame, mainmenuPanel);
				this.add(contentJPanel, BorderLayout.CENTER);
				contentJPanel.setLayout(null);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void refreshPanel() {
		updateUI();
		repaint();
	}
	
}
