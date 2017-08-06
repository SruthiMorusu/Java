package iss.nus.edu.sg.SouvinierStore.GUI;

/**
 * Name					Date
 * ---------------------------------
 * Lei Xu 				20.03.2014
 * 
 * @author Lei Xu
 * Since 20.03.2014
 * 
 * Modified By Lei Xu*/

import iss.nus.edu.sg.SouvinierStore.SouvenirStoreApp;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * update 11-Mar-2014, Karthik. 
 * update 19-Mar-2014, Xu Lei.
 * update 28-Mar-2014, Xu Lei.
 * 
 * @author Karthik, Xu Lei
 * 
 */
public class MainMenuPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JPanel mainMenuButtonsPanel;
	private JButton billButton;
	private JButton categoryButton;
	private JButton productButton;
	private JButton memberButton;
	private JButton discountButton;
	private JButton vendorButton;
	private JButton transactionButton;
	// temporary cancelled this panel cause no need in the requirement. 28-Mar-2014, Xu Lei.
	//private JButton storeKeeperButton;
	private JButton logoutButton;
	private SouvenirStoreApp souvenirStoreApp;
	private WindowFrame windowFrame;

	// polymorphism, can accept all kinds of specific panel. 19-Mar-2014, Xu Lei.
	private JPanel specificPanel;

	// maybe have some risks cause garbage collector may collect the removed object very early. 25-Mar-2014, Xu Lei.
	public void reSetSpecificPanel(JPanel specificPanel) {
		this.add(specificPanel);
		this.remove(this.specificPanel);
		this.specificPanel = specificPanel;	
	}

	public MainMenuPanel(SouvenirStoreApp souvenirStoreApp, WindowFrame windowFrame) {
		super();
		this.souvenirStoreApp = souvenirStoreApp;
		this.windowFrame = windowFrame;
		specificPanel = new WelcomePanel(souvenirStoreApp);
		initGUI();
	}

	private void initGUI() {
		try {
			BorderLayout thisLayout = new BorderLayout();
			this.setLayout(thisLayout);
			this.setPreferredSize(new java.awt.Dimension(1200, 720));
			this.setOpaque(false);
			this.add(specificPanel, BorderLayout.CENTER);
			specificPanel.setPreferredSize(new java.awt.Dimension(1200, 481));
			{
				mainMenuButtonsPanel = new JPanel();
				this.add(mainMenuButtonsPanel, BorderLayout.NORTH);
				GroupLayout mainMenuButtonsPanelLayout = new GroupLayout(
						(JComponent) mainMenuButtonsPanel);
				mainMenuButtonsPanel.setLayout(mainMenuButtonsPanelLayout);
				mainMenuButtonsPanel.setSize(1200, 89);
				mainMenuButtonsPanel.setPreferredSize(new java.awt.Dimension(1200, 89));
				mainMenuButtonsPanel.setBackground(new java.awt.Color(0,0,255));
				{
					billButton = new JButton();
					billButton.setText("Checkout");
					billButton.setIcon(new ImageIcon(getClass()
							.getClassLoader().getResource("iss/nus/edu/sg/SouvinierStore/GUI/bill.jpg")));
					billButton.setHorizontalTextPosition(SwingConstants.CENTER);
					billButton.setFont(new java.awt.Font("Arial Narrow", 1, 14));
					billButton.setVerticalTextPosition(SwingConstants.BOTTOM);
					billButton.addActionListener(new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent arg0) {
							MainMenuPanel.this.remove(specificPanel);
							specificPanel = new BillPanel(souvenirStoreApp, windowFrame, MainMenuPanel.this);
							MainMenuPanel.this.add(specificPanel);
							MainMenuPanel.this.refreshPanel();
						}});
				}
				/*{
					storeKeeperButton = new JButton();
					storeKeeperButton.setText("StoreKeeper");
					storeKeeperButton.setIcon(new ImageIcon(getClass()
							.getClassLoader().getResource("iss/nus/edu/sg/SouvinierStore/GUI/storeKeeper.png")));
					storeKeeperButton.setHorizontalTextPosition(SwingConstants.CENTER);
					storeKeeperButton.setFont(new java.awt.Font("Arial Narrow", 1, 14));
					storeKeeperButton.setVerticalTextPosition(SwingConstants.BOTTOM);
					storeKeeperButton.addActionListener(new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent e) {
							MainMenuPanel.this.remove(specificPanel);
							specificPanel = new StoreKeeperPanel(souvenirStoreApp, windowFrame);
							MainMenuPanel.this.add(specificPanel);
							MainMenuPanel.this.refreshPanel();
						}});
				}*/
				{
					transactionButton = new JButton();
					transactionButton.setText("Transaction");
					transactionButton.setFont(new java.awt.Font("Arial Narrow", 1, 14));
					transactionButton.setIcon(new ImageIcon(getClass()
							.getClassLoader().getResource("iss/nus/edu/sg/SouvinierStore/GUI/transaction.jpg")));
					transactionButton.setHorizontalTextPosition(SwingConstants.CENTER);
					transactionButton.setVerticalTextPosition(SwingConstants.BOTTOM);
					transactionButton.addActionListener(new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent e) {
							MainMenuPanel.this.remove(specificPanel);
							specificPanel = new TransactionPanel(souvenirStoreApp, windowFrame);
							MainMenuPanel.this.add(specificPanel);
							MainMenuPanel.this.refreshPanel();
						}});
				}
				{
					vendorButton = new JButton();
					vendorButton.setText("Vendor");
					vendorButton.setIcon(new ImageIcon(getClass()
							.getClassLoader().getResource("iss/nus/edu/sg/SouvinierStore/GUI/vendor.jpg")));
					vendorButton.setHorizontalTextPosition(SwingConstants.CENTER);
					vendorButton.setFont(new java.awt.Font("Arial Narrow", 1, 14));
					vendorButton.setVerticalTextPosition(SwingConstants.BOTTOM);
					vendorButton.addActionListener(new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent e) {
							MainMenuPanel.this.remove(specificPanel);
							specificPanel = new VendorPanel(souvenirStoreApp, windowFrame);
							MainMenuPanel.this.add(specificPanel);
							MainMenuPanel.this.refreshPanel();
						}});
				}
				{
					discountButton = new JButton();
					discountButton.setText("Discount");
					discountButton.setIcon(new ImageIcon(getClass()
							.getClassLoader().getResource("iss/nus/edu/sg/SouvinierStore/GUI/discount.jpg")));
					discountButton.setHorizontalTextPosition(SwingConstants.CENTER);
					discountButton.setFont(new java.awt.Font("Arial Narrow", 1, 14));
					discountButton.setVerticalTextPosition(SwingConstants.BOTTOM);
					discountButton.addActionListener(new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent e) {
							MainMenuPanel.this.remove(specificPanel);
							specificPanel = new DiscountPanel(souvenirStoreApp, windowFrame);
							MainMenuPanel.this.add(specificPanel);
							MainMenuPanel.this.refreshPanel();
						}});
				}
				{
					memberButton = new JButton();
					memberButton.setText("Member");
					memberButton.setIcon(new ImageIcon(getClass()
							.getClassLoader().getResource("iss/nus/edu/sg/SouvinierStore/GUI/member.jpg")));
					memberButton.setHorizontalTextPosition(SwingConstants.CENTER);
					memberButton.setFont(new java.awt.Font("Arial Narrow", 1, 14));
					memberButton.setVerticalTextPosition(SwingConstants.BOTTOM);
					memberButton.addActionListener(new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent e) {
							MainMenuPanel.this.remove(specificPanel);
							specificPanel = new MemberPanel(souvenirStoreApp, windowFrame);
							MainMenuPanel.this.add(specificPanel);
							MainMenuPanel.this.refreshPanel();
						}});
				}
				{
					categoryButton = new JButton();
					categoryButton.setText("Category");
					categoryButton.setIcon(new ImageIcon(getClass()
							.getClassLoader().getResource("iss/nus/edu/sg/SouvinierStore/GUI/category.jpg")));
					categoryButton.setFont(new java.awt.Font("Arial Narrow", 1, 14));
					categoryButton.setHorizontalTextPosition(SwingConstants.CENTER);
					categoryButton.setVerticalTextPosition(SwingConstants.BOTTOM);
					categoryButton.addActionListener(new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent e) {
							MainMenuPanel.this.remove(specificPanel);
							specificPanel = new CategoryPanel(souvenirStoreApp, windowFrame);
							MainMenuPanel.this.add(specificPanel);
							MainMenuPanel.this.refreshPanel();
						}});
				}
				{
					productButton = new JButton();
					productButton.setText("Product");
					productButton.setIcon(new ImageIcon(getClass()
							.getClassLoader().getResource("iss/nus/edu/sg/SouvinierStore/GUI/product.jpg")));
					productButton.setFont(new java.awt.Font("Arial Narrow", 1, 14));
					productButton.setHorizontalTextPosition(SwingConstants.CENTER);
					productButton.setVerticalTextPosition(SwingConstants.BOTTOM);
					productButton.addActionListener(new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent e) {
							MainMenuPanel.this.remove(specificPanel);
							specificPanel = new ProductPanel(souvenirStoreApp, windowFrame);
							MainMenuPanel.this.add(specificPanel);
							MainMenuPanel.this.refreshPanel();
						}});
				}
				
				{
					logoutButton = new JButton();
					logoutButton.setText("Logout");
					logoutButton.setIcon(new ImageIcon(getClass()
							.getClassLoader().getResource("iss/nus/edu/sg/SouvinierStore/GUI/logout.png")));
					logoutButton.setFont(new java.awt.Font("Arial Narrow", 1, 10));
					logoutButton.setHorizontalTextPosition(SwingConstants.CENTER);
					logoutButton.setVerticalTextPosition(SwingConstants.BOTTOM);
					logoutButton.addActionListener(new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent e) {
							windowFrame.logout();
						}});
				}

				mainMenuButtonsPanelLayout.setHorizontalGroup(mainMenuButtonsPanelLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(billButton, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addComponent(categoryButton, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addComponent(productButton, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addComponent(memberButton, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addComponent(discountButton, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addComponent(vendorButton, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addComponent(transactionButton, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
					//.addGap(5)
					//.addComponent(storeKeeperButton, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
					.addGap(0, 52, Short.MAX_VALUE)
					.addComponent(logoutButton, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE));
				mainMenuButtonsPanelLayout.setVerticalGroup(mainMenuButtonsPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(billButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
					.addComponent(categoryButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
					.addComponent(productButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
					.addComponent(memberButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
					.addComponent(discountButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
					.addComponent(vendorButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
					.addComponent(transactionButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
					//.addComponent(storeKeeperButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
					.addComponent(logoutButton, GroupLayout.Alignment.BASELINE, 0, 89, Short.MAX_VALUE));
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
