package iss.nus.edu.sg.SouvinierStore;

/**
 * Name					Date
 * ---------------------------------
 * Xing Zibo		13.03.2014
 * 
 * @author Xing Zibo 
 * Since 23.03.2014
 * 
 * Modified By Xing Zibo*/

import iss.nus.edu.sg.SouvinierStore.system.IPrinter;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ReceiptPrinter extends Dialog implements IPrinter
{
	private static final long serialVersionUID = -5021494683789951893L;
	Frame currentParent;
	JTextArea textArea;
	
	public ReceiptPrinter(Frame parent) {
		super(parent, "Receipt Printer");
		this.setSize(443, 230);
		this.setLocationRelativeTo(parent);
        int x = parent.getWidth() / 2;
        int y = parent.getHeight() / 2;
        this.setLocation(x, y);
        add ("Center", createFormPanel());
        add ("South",  createButtonPanel());
        currentParent = parent;
	}

	@Override
	public boolean print(String data) {
		this.setVisible(true);
		this.textArea.setText(data);
		return true;
	}


	protected boolean performOkAction() {
		this.setVisible(false);
		return true;
	}
	private Panel createButtonPanel () {
        Panel p = new Panel ();
        Button b = new Button ("   OK   "); 
        b.addActionListener( new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                boolean success = performOkAction ();
                if (success) {
                    destroyDialog ();
                }
            }
        });
        p.add (b);
        return p;
    }
	private void destroyDialog () {
        setVisible (false);
        dispose();
    }

	protected Panel createFormPanel() {
			Panel p = new Panel();
			p.setLayout(null);
			p.setPreferredSize(new java.awt.Dimension(443, 159));
			initGUI(p);
			return p;
	}
	private void initGUI(Panel panel) {
		try {
			{
				panel.setLayout(new BorderLayout());
				panel.setPreferredSize(new java.awt.Dimension(443, 155));
				{
					textArea = new JTextArea();
					textArea.setBackground(Color.BLACK);
					textArea.setForeground(Color.GREEN);
					textArea.setEditable(false);
					JScrollPane scrollpane = new JScrollPane(textArea);
					panel.add(scrollpane, BorderLayout.CENTER);
				}
				
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
