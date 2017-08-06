package iss.nus.edu.sg.SouvinierStore.GUI;

import iss.nus.edu.sg.SouvinierStore.SouvenirStoreApp;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class StoreKeeperPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JLabel titleJLabel;
	private JPanel contentJPanel;
	// for build relationship with the logical module must through the SouvenirStoreApp.
	private SouvenirStoreApp souvenirStoreApp;
	// just for constructor of the dialog.
	private WindowFrame windowFrame;

	public StoreKeeperPanel(SouvenirStoreApp souvenirStoreApp,  WindowFrame windowFrame) {
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
				titleJLabel.setText("  StoreKeeper");
				titleJLabel.setPreferredSize(new java.awt.Dimension(1200, 44));
				titleJLabel.setFont(new java.awt.Font("Castellar",1,28));
			}
			{
				contentJPanel = new JPanel();
				this.add(contentJPanel, BorderLayout.CENTER);
				contentJPanel.setLayout(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
