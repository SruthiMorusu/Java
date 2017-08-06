package iss.nus.edu.sg.SouvinierStore.GUI;

/**
 * Name					Date
 * ---------------------------------
 * Sruthi 				20.03.2014
 * 
 * any panel can use the dialog as the alert dialog.
 * 
 * @author Sruthi
 * Since 20.03.2014
 * 
 * Modified By Sruthi*/

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AlertDialog extends Dialog {

	private static final long serialVersionUID = 1L;
	
	private JLabel msgLabel;

	public AlertDialog (Frame parent, String msg) {
        super (parent, "warning");
        this.setLocationRelativeTo(parent);
        int x = parent.getWidth() / 2;
        int y = parent.getHeight() / 2;
        this.setLocation(x, y);
        this.setSize(749, 160);
        this.setPreferredSize(new java.awt.Dimension(749, 160));
        add ("Center", createMsgPanel(msg));
        add ("South",  createButtonPanel());
    }
	
	private JPanel createMsgPanel(String msg) {
		JPanel jp = new JPanel();
		BorderLayout jpLayout = new BorderLayout();
		jp.setLayout(jpLayout);
		msgLabel = new JLabel(msg, JLabel.CENTER);
		msgLabel.setFont(new java.awt.Font("Andalus",1,18));
		jp.add(msgLabel, BorderLayout.CENTER);
		return jp;
	}
	
	private JPanel createButtonPanel() {
		JPanel p = new JPanel ();
		{
			JButton b = new JButton ("OK");
			p.add(b);
			b.setPreferredSize(new java.awt.Dimension(97, 33));
			b.setFont(new java.awt.Font("Andalus",1,24));
			b.addActionListener( new ActionListener () {
				public void actionPerformed (ActionEvent e) {
					setVisible (false);
					dispose();   
				}
			});
		}
	    return p;
	}

}
