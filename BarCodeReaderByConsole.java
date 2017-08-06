package iss.nus.edu.sg.SouvinierStore;

/**
 * Name					Date
 * ---------------------------------
 * Xing Zibo		19.03.2014
 * 
 * @author Xing Zibo 
 * Since 19.03.2014
 * 
 * Modified By Xing Zibo*/

import iss.nus.edu.sg.SouvinierStore.system.IBarCodeReader;

import java.awt.Color;

import javax.swing.JTextField;

public class BarCodeReaderByConsole extends JTextField implements IBarCodeReader
{
	public BarCodeReaderByConsole(){
		super();
		this.setBackground(Color.BLACK);
		this.setForeground(Color.GREEN);
	}
	@Override
	public String readBarCode() {
		return this.getText();
	}
}
