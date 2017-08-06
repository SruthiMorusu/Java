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

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * for the first page when login. update at 19-Mar-2014, Xu Lei.
 * 
 * @author Sruthi
 *
 */
public class WelcomePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JLabel welcomeJLabel;
	public WelcomePanel(SouvenirStoreApp souvenirStoreApp) {
		super();
		initGUI();
	}

	private void initGUI() {
		try {
			{
				this.setPreferredSize(new java.awt.Dimension(1200, 481));
				this.setSize(1200, 481);
				this.setLayout(null);
				{
					welcomeJLabel = new JLabel();
					this.add(welcomeJLabel);
					// TODO change to the current store keeper's name.
					welcomeJLabel.setText("Hello "+SouvenirStoreApp.getInstance().getCurrentStoreKeeper()+", Welcome to University Souvenir Store!!!");
					//welcomeJLabel.setText("Welcome to University Souvenir Store, Team 3 !");
					welcomeJLabel.setBounds(203, 180, 890, 123);
					welcomeJLabel.setFont(new java.awt.Font("Curlz MT",1,36));
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
