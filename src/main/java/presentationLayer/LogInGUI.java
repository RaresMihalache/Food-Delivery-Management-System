package presentationLayer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import dataLayer.Serializator;
import mainPackage.*;
import businessLayer.Account;
import javax.swing.*;

public class LogInGUI extends JFrame implements ActionListener {

	private JPanel logInPanel = new JPanel();
	private JPanel emptyPanel = new JPanel();
	private JPanel registerPanel = new JPanel();
	
	private JLabel emptyLbl1 = new JLabel("");
	private JLabel emptyLbl2 = new JLabel("");
	private JLabel emptyLbl3 = new JLabel("");
	private JLabel emptyLbl4 = new JLabel("");
	private JLabel emptyLbl5 = new JLabel("");
	
	private JLabel titleLblLog = new JLabel("Log In");
	private JLabel usernameLblLog = new JLabel("Username: ");
	private JLabel passwordLblLog = new JLabel("Password: ");
	
	private JLabel titleLblReg = new JLabel("Register");
	private JLabel usernameLblReg = new JLabel("Username: ");
	private JLabel passwordLblReg = new JLabel("Password: ");
	
	private JTextField usernameTxtLog = new JTextField(20);
	private JPasswordField passwordTxtLog = new JPasswordField(20);
	
	private JTextField usernameTxtReg = new JTextField(20);
	private JPasswordField passwordTxtReg = new JPasswordField(20);
	
	private JButton logInBtn = new JButton("Log In!");
	private JButton registerBtn = new JButton("Register!");
	
	private Font titleFont = new Font("Serif", Font.PLAIN, 45);
	private Font font = new Font("Serif", Font.PLAIN, 30);
	
	private String usernameLoggedIn;
	
	private AdministratorGUI adminGUI;
	private ClientGUI clientGUI;
	private EmployeeGUI employeeGUI;
	
	
	private FileInputStream fileInName;
	private FileOutputStream fileOutName;
	//private ObjectInputStream objectInputStream;
	//private ObjectOutputStream objectOutputStream;
	
	private ArrayList<Account> accounts = new ArrayList<Account>();
	
	
	public LogInGUI (int width, int height) throws Exception {
		setLayout(null);
		setSize(width, height);
		setResizable(false);
		setTitle("titlu");
		setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		
		adminGUI = new AdministratorGUI(width, height, this);
		clientGUI = new ClientGUI(width, height, this);
		employeeGUI = new EmployeeGUI(width, height, this);
		
		//fileInName = new FileInputStream("users.ser");
		//fileOutName = new FileOutputStream("users.ser");
		//fileInName = new FileInputStream("users.ser");
		//objectOutputStream = new ObjectOutputStream(fileOutName);
		//objectInputStream = new ObjectInputStream(fileInName);

		//fileOutName = new FileOutputStream("users.ser");
		//fileInName = new FileInputStream("users.ser");
		
		try {
			Path path = Paths.get("users.ser");
			System.out.println(Files.size(path));
			if(Files.size(path) <= 10) {
				System.out.println("Se initializeaza...");
				initializeAccounts();
			}
		} catch(Exception ex) {
			System.out.println("Nu exista fisierul... se creeaza");
			initializeAccounts();
		}
		
		//fileOutName = new FileOutputStream("users.ser");
		//fileInName = new FileInputStream("users.ser");
		
		
		logInPanel.setBounds(0, 0, 690, height);
		logInPanel.setBackground(new Color(241, 255, 193));
		logInPanel.setLayout(null);
		
		titleLblLog.setFont(titleFont);
		titleLblLog.setBounds(290, 10, 150, 75);
		
		usernameLblLog.setFont(font);
		usernameLblLog.setBounds(120, 170, 140, 50);

		passwordLblLog.setFont(font);
		passwordLblLog.setBounds(120, 220, 140, 50);
		
		usernameTxtLog.setFont(font);
		usernameTxtLog.setBounds(260, 180, 340, 40);
		
		passwordTxtLog.setFont(font);
		passwordTxtLog.setBounds(260, 230, 340, 40);
		
		logInBtn.setFont(font);
		logInBtn.setBounds(250, 450, 150, 60);
		logInBtn.addActionListener(this);
		
		logInPanel.add(titleLblLog);
		logInPanel.add(usernameLblLog);
		logInPanel.add(passwordLblLog);
		logInPanel.add(usernameTxtLog);
		logInPanel.add(passwordTxtLog);
		logInPanel.add(logInBtn);
		
		
		emptyPanel.setBounds(690, 0, 20, height);
		emptyPanel.setBackground(Color.black);
		
		registerPanel.setBounds(710, 0, 690, height);
		registerPanel.setBackground(new Color(241, 219, 195));
		registerPanel.setLayout(null);
		
		titleLblReg.setFont(titleFont);
		titleLblReg.setBounds(290, 10, 150, 75);
		
		usernameLblReg.setFont(font);
		usernameLblReg.setBounds(120, 170, 140, 50);

		passwordLblReg.setFont(font);
		passwordLblReg.setBounds(120, 220, 140, 50);
		
		usernameTxtReg.setFont(font);
		usernameTxtReg.setBounds(260, 180, 340, 40);
		
		passwordTxtReg.setFont(font);
		passwordTxtReg.setBounds(260, 230, 340, 40);
		
		registerBtn.setFont(font);
		registerBtn.setBounds(250, 450, 150, 60);
		registerBtn.addActionListener(this);
		
		registerPanel.add(titleLblReg);
		registerPanel.add(usernameLblReg);
		registerPanel.add(passwordLblReg);
		registerPanel.add(usernameTxtReg);
		registerPanel.add(passwordTxtReg);
		registerPanel.add(registerBtn);
		
		
		add(logInPanel);
		add(emptyPanel);
		add(registerPanel);

		setVisible(true);
	}

	public String charListToString(char[] source) {
		String toReturn = "";
		for(char c : source) {
			toReturn = toReturn + c;
		}
		return toReturn;
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(this.logInBtn)) {
		
			String username = usernameTxtLog.getText();
			this.usernameLoggedIn = username;
			char[] passwordArray = passwordTxtLog.getPassword();
			String password = charListToString(passwordArray);
			Account tryLog = new Account(username, password);
			
			int connected;
			try {
				connected = checkAccount(tryLog);
				if(connected == 0) {
					JOptionPane.showMessageDialog(null, "Invalid credentials. Try again!");
				}
				else {
					JOptionPane.showMessageDialog(null, "<html>Successfully Logged in as: <font color = #ff000>" + tryLog.getType() + "</font></html>");
					if(tryLog.getType().equals("admin")) {
						//System.out.println("Sunt admin!!");
						this.setVisible(false);
						adminGUI.setVisible(true);
					}
					else if(tryLog.getType().equals("employee")) {
						//System.out.println("Sunt employee!!");
						this.setVisible(false);
						employeeGUI.setVisible(true);
					}
					else if(tryLog.getType().equals("client")) {
						//System.out.println("Sunt client!!");
						this.setVisible(false);
						clientGUI.setVisible(true);
					}
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		else if(e.getSource().equals(this.registerBtn)) {
			String username = usernameTxtReg.getText();
			char[] passowordArray = passwordTxtReg.getPassword();
			String password = charListToString(passowordArray);
			String type = "client";
			Account regAccount = new Account(username, password, type);
			
			System.out.println(regAccount.getUsername() + " " + regAccount.getPassword() + " " + regAccount.getType());
			
			try {
				addAccount(regAccount);
				JOptionPane.showMessageDialog(null, "New account was created. Now you can log in!");
			} catch (Exception e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error. Try creating the account with other credentials!");
			}
			
			//this.setVisible(false);
			//clientGUI.setVisible(true);
		}
	}
	
	public void initializeAccounts() throws IOException {
		fileOutName = new FileOutputStream("users.ser");
		fileInName = new FileInputStream("users.ser");
		Path path = Paths.get("users.ser");
		System.out.println("Asta e size-ul: " + Files.size(path));
		
		Account adminAcc = new Account("admin", "admin", "admin");
		Account employeeAcc = new Account("employee", "employee", "employee");
		Account clientAcc = new Account("client", "client", "client");
		
		accounts.add(adminAcc);
		accounts.add(employeeAcc);
		accounts.add(clientAcc);
	
		Serializator.serializeUnshared("users.ser", accounts);
	}
	
	    // method that returns user accounts in deserializable form
		public ArrayList<Account> getAccounts() throws Exception {
			ArrayList<Account> toReturn = new ArrayList<Account>();
			toReturn = (ArrayList<Account>) Serializator.deserializeObj("users.ser");
			return toReturn;
		}
		
		// prints out info for each account in ArrayList<Account> given as parameter
		public void getContentAccounts(ArrayList<Account> accounts) {
			for(Account account : accounts) {
				System.out.println("Account #" + accounts.indexOf(account) + ": " + account.getUsername() + " " + account.getPassword() + " " + account.getType());
			}
		}
		
		// returns 1 if user exists, otherwise it retuns 0.
		public int checkAccount(Account account) throws Exception {
			ArrayList<Account> checkAccounts = getAccounts();
			System.out.println("Account length in checkAccount: " + checkAccounts.size());
			String username = account.getUsername();
			String password = account.getPassword();
			System.out.println("Checking for... Username: " + username + " pass: " + password);
			for(Account a : checkAccounts) {
				System.out.println("Account #" + accounts.indexOf(a) + ": " + a.getUsername() + " " + a.getPassword() + " " + a.getType());
				if(a.getUsername().equals(username) && a.getPassword().equals(password)) {
					System.out.println("Successfully logged in: " + a.getType());
					account.setType(a.getType());
					return 1;
				}
			}
			return 0;
		}
		
		public void addAccount(Account account) throws Exception {
			accounts = getAccounts();
			accounts.add(account);
			System.out.println("Added new account! user: " + account.getUsername() + " pass: " + account.getPassword());
			System.out.println("Added new account! No of accounts: " + accounts.size());
			//getContentAccounts(getAccounts());
			Serializator.serializeUnshared("users.ser", accounts);
		}
		
		public String getUsernameLoggedIn() {
			return this.usernameLoggedIn;
		}
}
