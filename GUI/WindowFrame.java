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

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class WindowFrame extends Frame {
	
	private static final long serialVersionUID = 1L;
	
	private TitlePanel titlePanel;
	private MainMenuPanel mainMenuPanel;
	private LoginPanel loginPanel;
	private WindowListener windowListener;
	private SouvenirStoreApp souvenirStoreApp;
	
	public WindowFrame(final SouvenirStoreApp souvenirStoreApp) {
		super("University Souvenir Store");
		this.souvenirStoreApp = souvenirStoreApp;
		initGUI();
		windowListener = new WindowAdapter() {
			// invoke when close button pressed
			@Override
			public void windowClosing(WindowEvent e) {
				souvenirStoreApp.shutdown();	
			}
		};
		addWindowListener(windowListener);
		titlePanel = new TitlePanel();
		add(titlePanel, BorderLayout.NORTH);
		titlePanel.setBackground(new java.awt.Color(255,128,0));
		loginPanel = new LoginPanel(souvenirStoreApp, this);
		add(loginPanel, BorderLayout.CENTER);
	}

	public void login() {
		remove(loginPanel);
		loginPanel = null;
		mainMenuPanel = new MainMenuPanel(souvenirStoreApp, this);
		add(mainMenuPanel, BorderLayout.CENTER);
		refreshWindow();	
	}
	
	public void logout() {
		remove(mainMenuPanel);
		mainMenuPanel = null;
		loginPanel = new LoginPanel(souvenirStoreApp, this);
		add(loginPanel, BorderLayout.CENTER);
		refreshWindow();
	}
	
	private void refreshWindow() {
		repaint();
		validate();
		invalidate();
		validate();
	}
	
	private void initGUI() {
		try {
			this.setSize(1200, 650);
			BorderLayout borderLayout = new BorderLayout();
			this.setLayout(borderLayout);
			// maximize button disabled cause we do not want the user to change the app screen size, Karthik.
			this.setResizable(false);
			// keeping the app in the middle of the screen, Karthik.
			this.setLocationRelativeTo(null);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
