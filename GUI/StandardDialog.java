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

import java.awt.*;
import java.awt.event.*;

public abstract class StandardDialog extends Dialog {

	private static final long serialVersionUID = 1L;

	public StandardDialog (Frame parent, String title) {
        super (parent, title);
        this.setLocationRelativeTo(parent);
        int x = parent.getWidth() / 2;
        int y = parent.getHeight() / 2;
        this.setLocation(x, y);
        add ("Center", createFormPanel());
        add ("South",  createButtonPanel());
    }

    public StandardDialog (Frame parent) {
        this (parent, "");
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
        b = new Button ("Cancel");
        b.setSize(80, 20);;
        b.addActionListener (new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                destroyDialog ();
            }
        });
        p.add (b);
        return p;
    }

    private void destroyDialog () {
        setVisible (false);
        dispose();
    }

    protected abstract Panel createFormPanel () ;

    protected abstract boolean performOkAction () ;

}
