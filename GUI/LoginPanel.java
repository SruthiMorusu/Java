package iss.nus.edu.sg.SouvinierStore.GUI;

/** 
 * Name          	          Date 
 * -------------------------------------------------- 
 * Xu Lei					17.03.2014
 * Sruthi               20.03.2014 
 * Xu Lei					01.04.2014
 *  
 * Author : Sruthi, Xu Lei
 * Since 20.03.2014 
 * 
 * Modified By Lei Xu
 *  */

import iss.nus.edu.sg.SouvinierStore.SouvenirStoreApp;

import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class LoginPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JLabel loginNameJLabel;
	private JTextField loginNameJTextField;
	private JFormattedTextField jFormattedTextField1;
	private JLabel incorrectCredentials;
	private JLabel passwordNotBlank;
	private JLabel userNameNotBlank;
	private JLabel passwordJLabel;
	private JButton loginJButton;
	private WindowFrame windowFrame;
	private SouvenirStoreApp souvenirStoreApp;
	private JPasswordField jpwName; 

	public LoginPanel(SouvenirStoreApp souvenirStoreApp, WindowFrame windowFrame) {
		super();
		this.souvenirStoreApp = souvenirStoreApp;
		this.windowFrame = windowFrame;
		initGUI();
	}

	@SuppressWarnings("unchecked")
	private void initGUI() {
		try {
			{
				this.setPreferredSize(new java.awt.Dimension(1200, 600));
				this.setSize(1200, 600);
				this.setLayout(null);
				this.setBackground(new java.awt.Color(255,128,0));
				{
					loginNameJLabel = new JLabel();
					this.add(loginNameJLabel);
					loginNameJLabel.setText("Login Name : ");
					loginNameJLabel.setBounds(308, 116, 188, 49);
					loginNameJLabel.setFont(new java.awt.Font("Forte",1,24));
				}
				{
					loginNameJTextField = new JTextField();
					this.add(loginNameJTextField);
					loginNameJTextField.setBounds(570, 124, 284, 36);
					loginNameJTextField.setFont(new java.awt.Font("Andalus",1,20));
					
					loginNameJTextField.setFocusTraversalKeys(
					        KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
							loginNameJTextField.addKeyListener(new java.awt.event.KeyAdapter() {
							    @SuppressWarnings("static-access")
								public void keyPressed(java.awt.event.KeyEvent evt) {
							        if(evt.getKeyCode() == evt.VK_TAB){
							            
							        	jpwName.grabFocus();
							    }
							}
						});
					
				}
				
				{
					jpwName = new JPasswordField(10);
					jpwName.setEchoChar('*');
					jpwName.setVisible(true);
					this.add(jpwName);
					jpwName.setBounds(570, 278, 284, 35);
					jpwName.setEchoChar('*');
					
					jpwName.setFocusTraversalKeys(
					        KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
							jpwName.addKeyListener(new java.awt.event.KeyAdapter() {
							    @SuppressWarnings("static-access")
								public void keyPressed(java.awt.event.KeyEvent evt) {
							        if(evt.getKeyCode() == evt.VK_TAB){
							            
							        	loginJButton.grabFocus();
							    }
							}
						});
					
				}
				
				
				{
					passwordJLabel = new JLabel();
					this.add(passwordJLabel);
					passwordJLabel.setText("Password    : ");
					passwordJLabel.setBounds(308, 268, 188, 48);
					passwordJLabel.setFont(new java.awt.Font("Forte",1,24));
				}
				
				{
					loginJButton = new JButton();
					this.add(loginJButton);
					loginJButton.setText("Login");
					loginJButton.setBounds(412, 400, 304, 69);
					loginJButton.setFont(new java.awt.Font("AR BERKLEY",1,36));
					
					loginJButton.registerKeyboardAction(loginJButton.getActionForKeyStroke(
                            KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                            KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
           					JComponent.WHEN_FOCUSED);
					
					
					
					loginJButton.registerKeyboardAction(loginJButton.getActionForKeyStroke(
                            KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                            KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
           					JComponent.WHEN_FOCUSED);
					
					
					loginJButton.addActionListener(new ActionListener(){
						@SuppressWarnings("deprecation")
						@Override
						public void actionPerformed(ActionEvent e) {
							if(check()){
								windowFrame.login();
							} 
							
							else if((loginNameJTextField.getText() == null || loginNameJTextField.getText().isEmpty()) && !(jpwName.getText().isEmpty()))
							{
								userNameNotBlank.setVisible(true);
								passwordNotBlank.setVisible(false);
								incorrectCredentials.setVisible(false);
							}
							
							else if((jpwName.getText() == null || jpwName.getText().isEmpty()) && !(loginNameJTextField.getText().isEmpty()))
							{
								passwordNotBlank.setVisible(true);
								userNameNotBlank.setVisible(false);
								incorrectCredentials.setVisible(false);
							}
							
							else if((loginNameJTextField.getText() == null || loginNameJTextField.getText().isEmpty()) &&
									(jpwName.getText() == null || jpwName.getText().isEmpty()))
							{
									passwordNotBlank.setVisible(true);
									userNameNotBlank.setVisible(true);
									incorrectCredentials.setVisible(false);
							}
							
							else
							{
								passwordNotBlank.setVisible(false);
								userNameNotBlank.setVisible(false);
								incorrectCredentials.setVisible(true);
							}
							}
								
						});
				}
				{
					userNameNotBlank = new JLabel();
					this.add(userNameNotBlank);
					userNameNotBlank.setText("Username cannot be blank !!!");
					userNameNotBlank.setBounds(882, 130, 213, 29);
					userNameNotBlank.setVisible(false);
					userNameNotBlank.setFont(new java.awt.Font("Tahoma",1,12));
				}
				{
					passwordNotBlank = new JLabel();
					this.add(passwordNotBlank);
					passwordNotBlank.setText("Password cannot be blank !!!");
					passwordNotBlank.setBounds(882, 282, 203, 28);
					passwordNotBlank.setVisible(false);
					passwordNotBlank.setFont(new java.awt.Font("Tahoma",1,12));
				}
				{
					incorrectCredentials = new JLabel();
					this.add(incorrectCredentials);
					incorrectCredentials.setText("Incorrect Username or Password !!! Please try again !!!");
					incorrectCredentials.setBounds(759, 418, 356, 34);
					incorrectCredentials.setVisible(false);
					incorrectCredentials.setFont(new java.awt.Font("Tahoma",1,12));
				}
				

			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	private boolean check() {
		
		return(SouvenirStoreApp.getInstance().login(loginNameJTextField.getText(), jpwName.getText()));	
		
	}

}
