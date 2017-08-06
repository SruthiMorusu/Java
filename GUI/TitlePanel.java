package iss.nus.edu.sg.SouvinierStore.GUI;

/**
 * Name					Date
 * ---------------------------------
 * Sruthi 				20.03.2014
 * 
 * Used for title.
 * 
 * @author Sruthi
 * Since 20.03.2014
 * 
 * Modified By Sruthi*/

import javax.swing.JLabel;
import javax.swing.JPanel;

public class TitlePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JLabel jLabelTitle;
	private JLabel jLabelTeam;

	public TitlePanel() {
		initGUI();
	}

	private void initGUI() {
		try {
			{
				this.setPreferredSize(new java.awt.Dimension(1200, 50));
				this.setLayout(null);
				{
					jLabelTitle = new JLabel();
					this.add(jLabelTitle);
					jLabelTitle.setText("University Souvenir Store");
					jLabelTitle.setBounds(452, 5, 264, 39);
					jLabelTitle.setFont(new java.awt.Font("Segoe UI", 1, 20));
				}
				{
					jLabelTeam = new JLabel();
					this.add(jLabelTeam);
					jLabelTeam.setText("full-time team 3");
					jLabelTeam.setBounds(1058, 28, 107, 16);
					jLabelTeam.setFont(new java.awt.Font("Segoe UI", 0, 14));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
