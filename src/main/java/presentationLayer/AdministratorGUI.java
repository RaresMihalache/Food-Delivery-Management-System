package presentationLayer;

import javax.swing.*;

import businessLayer.*;
import businessLayer.MenuItem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class AdministratorGUI extends JFrame implements ActionListener {
	
	private LogInGUI logInGUI;
	
	private JPanel adminPanel = new JPanel();
	private JLabel adminLbl = new JLabel("Admin");
	
	private DeliveryService devService;
	
	private JPanel mainOperationPanel = new JPanel();
	private JButton backBtn = new JButton("Back");
	private JButton importBtn = new JButton("Import products");
	private JButton manageBtn = new JButton("Manage products");
	private JButton generateBtn = new JButton("Generate reports");
	
	
	private JPanel importPanel = new JPanel();
	private JButton backBtnImp = new JButton("Back");
	private JLabel selectFile = new JLabel("Import csv from file name: ");
	private JTextField fileTxt = new JTextField(45);
	private JButton importBtnImport = new JButton("Import csv!");
	private JPanel innerPanel = new JPanel();
	private JTable importTable;
	private Object[] columns;
	private Object[][] data;
	
	
	private JPanel managePanel = new JPanel();
	private JButton backBtnMan = new JButton("Back");
	private JPanel managePanelOp = new JPanel();
	private JButton addProductBtn = new JButton("Add product");
	private JButton deleteProductBtn = new JButton("Delete product");
	private JButton modifyProductBtn = new JButton("Modify product");
	private JButton createComposedBtn = new JButton("Create composed product");
	
	private JPanel addPanel = new JPanel();
	private JLabel addNameLbl = new JLabel("Add product name: ");
	private JLabel addRatingLbl = new JLabel("Add product rating: ");
	private JLabel addCaloriesLbl = new JLabel("Add product calories: ");
	private JLabel addProteinLbl = new JLabel("Add product protein: ");
	private JLabel addFatLbl = new JLabel("Add product fat: ");
	private JLabel addSodiumLbl = new JLabel("Add product sodium: ");
	private JLabel addPriceLbl = new JLabel("Add product price: ");
	private JTextField addNameTxt = new JTextField(100);
	private JTextField addRatingTxt = new JTextField(100);
	private JTextField addCaloriesTxt = new JTextField(100);
	private JTextField addProteinTxt = new JTextField(100);
	private JTextField addFatTxt = new JTextField(100);
	private JTextField addSodiumTxt = new JTextField(100);
	private JTextField addPriceTxt = new JTextField(100);
	private JButton addBtn = new JButton("Add product!");
	
	
	private JPanel deletePanel = new JPanel();
	private JLabel deleteNameLlb = new JLabel("Delete product (name): ");
	private JTextField deleteNameTxt = new JTextField(100);
	private JButton deleteBtn = new JButton("Delete product!");
	
	private JPanel modifyPanel = new JPanel();
	private JLabel modifyNameLbl = new JLabel("Product name: ");
	private JLabel modifyRatingLbl = new JLabel("New rating: ");
	private JLabel modifyPriceLbl = new JLabel("New price: ");
	private JTextField modifyNameTxt = new JTextField(100);
	private JTextField modifyRatingTxt = new JTextField(100);
	private JTextField modifyPriceTxt = new JTextField(100);
	private JButton modifyBtn = new JButton("Modify product!");
	
	private JPanel composedPanel = new JPanel();
	private JLabel composedItemLbl1 = new JLabel("MenuItem #1: ");
	private JLabel composedItemLbl2 = new JLabel("MenuItem #2: ");
	private JTextField composedItemTxt1 = new JTextField(100);
	private JTextField composedItemTxt2 = new JTextField(100);
	private JButton composeBtn = new JButton("Compose products!");
	
	private JPanel generatePanel = new JPanel();
	private JButton backBtnGen = new JButton("Back");
	private JPanel generateOpPanel = new JPanel();
	private JButton reportNo1 = new JButton("Report #1");
	private JLabel summaryNo1 = new JLabel("<html><font color = #ff0000> Time interval of the orders</font><br>->a report should be generated with the orders performed betweena given start hour and a given end hour regardless the date.</html>");
	private JButton reportNo2 = new JButton("Report #2");
	private JLabel summaryNo2 = new JLabel("<html><font color = #ff0000> The products ordered more than a specified number of times so far.</html>");
	private JButton reportNo3 = new JButton("Report #3");
	private JLabel summaryNo3 = new JLabel("<html><font color = #ff0000> The clients that have ordered more than a specified number of times and the value of the order was higher than a specified amount.</html>");
	private JButton reportNo4 = new JButton("Report #4");
	private JLabel summaryNo4 = new JLabel("<html><font color = #ff0000> The products ordered within a specified day with the number of times they have been ordered.</html>");
	
	
	private JPanel report1Panel = new JPanel();
	private JButton backBtnRep1 = new JButton("Back");
	private JLabel startHourLbl = new JLabel("Start hour(range: [0; 23]): ");
	private JTextField startHourTxt = new JTextField(40);
	private JLabel endHourLbl = new JLabel("End hour(range: [0; 23]): ");
	private JTextField endHourTxt = new JTextField(40);
	private JPanel txtPanel1 = new JPanel();
	private JTextArea txtReport1 = new JTextArea();
	private JButton generate1Btn = new JButton("Generate!");
	
	private JPanel report2Panel = new JPanel();
	private JButton backBtnRep2 = new JButton("Back");
	private JLabel orderedTimesLbl = new JLabel("Ordered more than no. of times: ");
	private JTextField orderedTimesTxt = new JTextField(40);
	private JPanel txtPanel2 = new JPanel();
	private JTextArea txtReport2 = new JTextArea();
	private JButton generate2Btn = new JButton("Generate!");
	
	private JPanel report3Panel = new JPanel();
	private JButton backBtnRep3 = new JButton("Back");
	private JLabel orderedTimesLbl2 = new JLabel("Ordered more than no. of times: ");
	private JTextField orderedTimesTxt2 = new JTextField(40);
	private JLabel orderPriceLbl = new JLabel("Higher than amount: ");
	private JTextField orderPriceTxt = new JTextField(40);
	private JPanel txtPanel3 = new JPanel();
	private JTextArea txtReport3 = new JTextArea();
	private JButton generate3Btn = new JButton("Generate!");
	
	private JPanel report4Panel = new JPanel();
	private JButton backBtnRep4 = new JButton("Back");
	private JLabel dayLbl = new JLabel("Day: ");
	private JTextField dayTxt = new JTextField(40);
	private JPanel txtPanel4 = new JPanel();
	private JTextArea txtReport4 = new JTextArea();
	private JButton generate4Btn = new JButton("Generate!");
	
	private Font adminLblFont = new Font("Serif", Font.ITALIC, 60);
	private Font normalFont = new Font("Serif", Font.PLAIN, 20);
	private Font h6Font = new Font("Serif", Font.PLAIN, 16);
	private Font h1Font = new Font("Serif", Font.PLAIN, 45);
	
	
	AdministratorGUI(int width, int height, LogInGUI logInGUI) throws Exception{
		
		this.logInGUI = logInGUI;
		
		setSize(width, height);
		getContentPane().setBackground(new Color(173, 250, 242));
		setTitle("Administrator");
		setResizable(false);
		setLayout(null);
		setVisible(false);
		setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		
		devService = new DeliveryService();
		
		
		// adminPanel
		adminPanel.setBounds(0, 0, width, 100);
		adminPanel.setBackground(new Color(55, 197, 248));
		adminPanel.setLayout(null);
	
		adminLbl.setBounds(200, 20, 300, 50);
		adminLbl.setFont(adminLblFont);
		
		adminPanel.add(adminLbl);
		
		// mainOperationPanel
		mainOperationPanel.setLayout(null);
		mainOperationPanel.setBackground(new Color(192, 227, 234));
		mainOperationPanel.setBounds(0, 100, width, height - 100);
		
		backBtn.setBounds(20, 20, 200, 40);
		backBtn.setFont(normalFont);
		
		importBtn.setBounds(370, 20, 200, 40);
		importBtn.setFont(normalFont);

		manageBtn.setBounds(720, 20, 200, 40);
		manageBtn.setFont(normalFont);
		
		generateBtn.setBounds(1070, 20, 200, 40);
		generateBtn.setFont(normalFont);
		
		backBtn.addActionListener(this);
		importBtn.addActionListener(this);
		manageBtn.addActionListener(this);
		generateBtn.addActionListener(this);
		
		mainOperationPanel.add(backBtn);
		mainOperationPanel.add(importBtn);
		mainOperationPanel.add(manageBtn);
		mainOperationPanel.add(generateBtn);
		
		// importPanel
		importPanel.setLayout(null);
		importPanel.setBackground(new Color(192, 227, 234));
		importPanel.setBounds(0, 100, width, height - 100);
		importPanel.setVisible(false);
		
		backBtnImp.setBounds(20, 20, 200, 40);
		backBtnImp.setFont(normalFont);
		/*
		selectFile.setBounds(20, 100, 220, 40);
		selectFile.setFont(normalFont);
		
		fileTxt.setBounds(250, 100, 400, 40);
		fileTxt.setFont(normalFont);*/
		
		
		innerPanel.setLayout(new GridLayout(1, 1));
		innerPanel.setBounds(100, 100, width - 200, height - 250);
		columns = DeliveryService.retrieveFields(BaseProduct.class).toArray();
 
		/*System.out.println("cdcdcdcdcdcdcdcd");
		devService.initializeMenu();
		System.out.println("cdcdcdcdcdcdcdcdcdc");*/
		//ArrayList<BaseProduct> products = devService.importProducts();
		//data = new Object[products.size()][columns.length];
		
		importTable = new JTable(data, columns);
		//innerPanel.add(new JScrollPane(importTable));
		
		importBtnImport.setBounds(1170, 20, 200, 40);
		importBtnImport.setFont(normalFont);
		
		backBtnImp.addActionListener(this);
		importBtnImport.addActionListener(this);
		
		importPanel.add(backBtnImp);
		//importPanel.add(selectFile);
		//importPanel.add(fileTxt);
		importPanel.add(importBtnImport);
		importPanel.add(innerPanel);
		
		// managePanel
		managePanel.setLayout(null);
		managePanel.setBackground(new Color(192, 227, 234));
		managePanel.setBounds(0, 100, width, height - 100);
		managePanel.setVisible(false);
		
		backBtnMan.setBounds(20, 20, 200, 40);
		backBtnMan.setFont(normalFont);
		
		managePanelOp.setLayout(null);
		managePanelOp.setBackground(new Color(192, 227, 234));
		managePanelOp.setBounds(0, 100, width, height - 200);
		managePanelOp.setVisible(true);
		
		addProductBtn.setBounds(60, 20, 200, 40);
		addProductBtn.setFont(normalFont);
		
		deleteProductBtn.setBounds(380, 20, 200, 40);
		deleteProductBtn.setFont(normalFont);
		
		modifyProductBtn.setBounds(700, 20, 200, 40);
		modifyProductBtn.setFont(normalFont);
		
		createComposedBtn.setBounds(1020, 20, 300, 40);
		createComposedBtn.setFont(normalFont);
		
		
		
		backBtnMan.addActionListener(this);
		addProductBtn.addActionListener(this);
		deleteProductBtn.addActionListener(this);
		modifyProductBtn.addActionListener(this);
		createComposedBtn.addActionListener(this);
		
		managePanelOp.add(addProductBtn);
		managePanelOp.add(deleteProductBtn);
		managePanelOp.add(modifyProductBtn);
		managePanelOp.add(createComposedBtn);
		
		// AddPanel (ManagePanel):
		
		addPanel.setLayout(null);
		addPanel.setBackground(new Color(160, 176, 255));
		addPanel.setBounds(0, 100, width, height - 100);
		addPanel.setVisible(false);
		
		addNameLbl.setBounds(250, 50, 200, 50);
		addNameLbl.setFont(normalFont);
		
		addRatingLbl.setBounds(250, 100, 200, 50);
		addRatingLbl.setFont(normalFont);
		
		addCaloriesLbl.setBounds(250, 150, 200, 50);
		addCaloriesLbl.setFont(normalFont);
		
		addProteinLbl.setBounds(250, 200, 200, 50);
		addProteinLbl.setFont(normalFont);
		
		addFatLbl.setBounds(250, 250, 200, 50);
		addFatLbl.setFont(normalFont);
		
		addSodiumLbl.setBounds(250, 300, 200, 50);
		addSodiumLbl.setFont(normalFont);
		
		addPriceLbl.setBounds(250, 350, 200, 50);
		addPriceLbl.setFont(normalFont);
		
		addNameTxt.setBounds(450, 60, 400, 30);
		addNameTxt.setFont(normalFont);
		
		addRatingTxt.setBounds(450, 110, 400, 30);
		addRatingTxt.setFont(normalFont);
		
		addCaloriesTxt.setBounds(450, 160, 400, 30);
		addCaloriesTxt.setFont(normalFont);
		
		addProteinTxt.setBounds(450, 210, 400, 30);
		addProteinTxt.setFont(normalFont);
		
		addFatTxt.setBounds(450, 260, 400, 30);
		addFatTxt.setFont(normalFont);
		
		addSodiumTxt.setBounds(450, 310, 400, 30);
		addSodiumTxt.setFont(normalFont);
		
		addPriceTxt.setBounds(450, 360, 400, 30);
		addPriceTxt.setFont(normalFont);
		
		addBtn.setBounds(900, 450, 200, 50);
		addBtn.setFont(normalFont);
		
		addBtn.addActionListener(this);
		
		addPanel.add(addNameLbl);
		addPanel.add(addRatingLbl);
		addPanel.add(addCaloriesLbl);
		addPanel.add(addProteinLbl);
		addPanel.add(addFatLbl);
		addPanel.add(addSodiumLbl);
		addPanel.add(addPriceLbl);
		addPanel.add(addNameTxt);
		addPanel.add(addRatingTxt);
		addPanel.add(addCaloriesTxt);
		addPanel.add(addProteinTxt);
		addPanel.add(addFatTxt);
		addPanel.add(addSodiumTxt);
		addPanel.add(addPriceTxt);
		addPanel.add(addBtn);
		
		// deletePanel (ManagePanel):
		

		deletePanel.setLayout(null);
		deletePanel.setBackground(new Color(160, 176, 255));
		deletePanel.setBounds(0, 100, width, height - 100);
		deletePanel.setVisible(false);
		
		deleteNameLlb.setBounds(250, 150, 200, 50);
		deleteNameLlb.setFont(normalFont);
		
		deleteNameTxt.setBounds(450, 160, 400, 30);
		deleteNameTxt.setFont(normalFont);
		
		deleteBtn.setBounds(525, 250, 200, 50);
		deleteBtn.setFont(normalFont);
		
		deleteBtn.addActionListener(this);
		
		deletePanel.add(deleteNameLlb);
		deletePanel.add(deleteNameTxt);
		deletePanel.add(deleteBtn);
		
		
		// modifyPanel (ManagePanel):
		modifyPanel.setLayout(null);
		modifyPanel.setBackground(new Color(160, 176, 255));
		modifyPanel.setBounds(0, 100, width, height - 100);
		modifyPanel.setVisible(false);
		
		modifyNameLbl.setBounds(250, 50, 200, 50);
		modifyNameLbl.setFont(normalFont);
		
		modifyRatingLbl.setBounds(250, 100, 200, 50);
		modifyRatingLbl.setFont(normalFont);
		
		modifyPriceLbl.setBounds(250, 150, 200, 50);
		modifyPriceLbl.setFont(normalFont);
		
		modifyNameTxt.setBounds(450, 60, 400, 30);
		modifyNameTxt.setFont(normalFont);
		
		modifyRatingTxt.setBounds(450, 110, 400, 30);
		modifyRatingTxt.setFont(normalFont);
		
		modifyPriceTxt.setBounds(450, 160, 400, 30);
		modifyPriceTxt.setFont(normalFont);
		
		modifyBtn.setBounds(525, 250, 200, 50);
		modifyBtn.setFont(normalFont);
		
		modifyBtn.addActionListener(this);
		
		
		modifyPanel.add(modifyNameLbl);
		modifyPanel.add(modifyRatingLbl);
		modifyPanel.add(modifyPriceLbl);
		modifyPanel.add(modifyNameTxt);
		modifyPanel.add(modifyRatingTxt);
		modifyPanel.add(modifyPriceTxt);
		modifyPanel.add(modifyBtn);
		
		// composePanel (ManagePanel):
		composedPanel.setLayout(null);
		composedPanel.setBackground(new Color(160, 176, 255));
		composedPanel.setBounds(0, 100, width, height - 100);
		composedPanel.setVisible(false);
		
		composedItemLbl1.setBounds(250, 50, 200, 50);
		composedItemLbl1.setFont(normalFont);
		
		composedItemLbl2.setBounds(250, 100, 200, 50);
		composedItemLbl2.setFont(normalFont);
		
		composedItemTxt1.setBounds(450, 60, 400, 30);
		composedItemTxt1.setFont(normalFont);
		
		composedItemTxt2.setBounds(450, 110, 400, 30);
		composedItemTxt2.setFont(normalFont);
		
		composeBtn.setBounds(525, 250, 200, 50);
		composeBtn.setFont(normalFont);
		
		composeBtn.addActionListener(this);
		
		composedPanel.add(composedItemLbl1);
		composedPanel.add(composedItemLbl2);
		composedPanel.add(composedItemTxt1);
		composedPanel.add(composedItemTxt2);
		composedPanel.add(composeBtn);
		
		
		managePanel.add(backBtnMan);
		managePanel.add(managePanelOp);
		managePanel.add(addPanel);
		managePanel.add(deletePanel);
		managePanel.add(modifyPanel);
		managePanel.add(composedPanel);
		
		
		
		// generatePanel
		generatePanel.setLayout(null);
		generatePanel.setBackground(new Color(192, 227, 234));
		generatePanel.setBounds(0, 100, width, height - 100);
		generatePanel.setVisible(false);

		backBtnGen.setBounds(20, 20, 200, 40);
		backBtnGen.setFont(normalFont);
		
		generateOpPanel.setLayout(null);
		generateOpPanel.setBackground(new Color(192, 227, 234));
		generateOpPanel.setBounds(0, 0, width, height - 200);
		generateOpPanel.setVisible(true);
		
		reportNo1.setBounds(140, 220, 250, 100);
		reportNo1.setFont(h1Font);
		
		summaryNo1.setBounds(150, 300, 200, 200);
		summaryNo1.setFont(h6Font);
		
		reportNo2.setBounds(440, 220, 250, 100);
		reportNo2.setFont(h1Font);
		
		summaryNo2.setBounds(450, 265, 200, 200);
		summaryNo2.setFont(h6Font);
		
		reportNo3.setBounds(740, 220, 250, 100);
		reportNo3.setFont(h1Font);
		
		summaryNo3.setBounds(750, 290, 200, 200);
		summaryNo3.setFont(h6Font);
		
		reportNo4.setBounds(1040, 220, 250, 100);
		reportNo4.setFont(h1Font);
		
		summaryNo4.setBounds(1050, 275, 200, 200);
		summaryNo4.setFont(h6Font);	
		
		reportNo1.addActionListener(this);
		reportNo2.addActionListener(this);
		reportNo3.addActionListener(this);
		reportNo4.addActionListener(this);

		generateOpPanel.add(reportNo1);
		generateOpPanel.add(summaryNo1);
		generateOpPanel.add(reportNo2);
		generateOpPanel.add(summaryNo2);
		generateOpPanel.add(reportNo3);
		generateOpPanel.add(summaryNo3);
		generateOpPanel.add(reportNo4);
		generateOpPanel.add(summaryNo4);
		
		generatePanel.add(backBtnGen);
		generatePanel.add(generateOpPanel);
		
		backBtnGen.addActionListener(this);
		
		//report1 panel:
		report1Panel.setLayout(null);
		report1Panel.setBackground(new Color(192, 227, 234));
		report1Panel.setBounds(0, 100, width, height - 100);
		report1Panel.setVisible(false);
		
		backBtnRep1.setBounds(20, 20, 160, 40);
		backBtnRep1.setFont(normalFont);
		
		startHourLbl.setBounds(30, 120, 210, 40);
		startHourLbl.setFont(normalFont);
		
		startHourTxt.setBounds(240, 130, 160, 30);
		startHourTxt.setFont(normalFont);
		
		endHourLbl.setBounds(30, 200, 210, 40);
		endHourLbl.setFont(normalFont);
		
		endHourTxt.setBounds(240, 210, 160, 30);
		endHourTxt.setFont(normalFont);
		
		generate1Btn.setBounds(150, 300, 160, 40);
		generate1Btn.setFont(normalFont);
		
		txtPanel1.setLayout(new GridLayout(1, 1));
		txtPanel1.setBackground(Color.WHITE);
		txtPanel1.setBounds(420, 100, width - 450, height - 300);
		
		txtReport1.setFont(normalFont);
		
		txtPanel1.add(txtReport1);
		
		report1Panel.add(backBtnRep1);
		report1Panel.add(startHourLbl);
		report1Panel.add(startHourTxt);
		report1Panel.add(endHourLbl);
		report1Panel.add(endHourTxt);
		report1Panel.add(generate1Btn);
		report1Panel.add(txtPanel1);
		
		//report2 panel:
		report2Panel.setLayout(null);
		report2Panel.setBackground(new Color(192, 227, 234));
		report2Panel.setBounds(0, 100, width, height - 100);
		report2Panel.setVisible(false);
		
		backBtnRep2.setBounds(20, 20, 160, 40);
		backBtnRep2.setFont(normalFont);
		
		orderedTimesLbl.setBounds(40, 120, 270, 40);
		orderedTimesLbl.setFont(normalFont);
		
		orderedTimesTxt.setBounds(310, 130, 160, 30);
		orderedTimesTxt.setFont(normalFont);
		
		generate2Btn.setBounds(300, 220, 160, 40);
		generate2Btn.setFont(normalFont);
		
		txtPanel2.setLayout(new GridLayout(1, 1));
		txtPanel2.setBackground(Color.WHITE);
		txtPanel2.setBounds(480, 100, width - 510, height - 300);
		
		txtReport2.setFont(normalFont);
		
		txtPanel2.add(txtReport2);
		
		report2Panel.add(backBtnRep2);
		report2Panel.add(orderedTimesLbl);
		report2Panel.add(orderedTimesTxt);
		report2Panel.add(generate2Btn);
		report2Panel.add(txtPanel2);
		
		//report3 panel:
		report3Panel.setLayout(null);
		report3Panel.setBackground(new Color(192, 227, 234));
		report3Panel.setBounds(0, 100, width, height - 100);
		report3Panel.setVisible(false);
		
		backBtnRep3.setBounds(20, 20, 160, 40);
		backBtnRep3.setFont(normalFont);
		
		orderedTimesLbl2.setBounds(40, 120, 270, 40);
		orderedTimesLbl2.setFont(normalFont);
		
		orderedTimesTxt2.setBounds(310, 130, 160, 30);
		orderedTimesTxt2.setFont(normalFont);
		
		orderPriceLbl.setBounds(40, 200, 270, 40);
		orderPriceLbl.setFont(normalFont);
		
		orderPriceTxt.setBounds(310, 210, 160, 30);
		orderPriceTxt.setFont(normalFont);
		
		generate3Btn.setBounds(300, 320, 160, 40);
		generate3Btn.setFont(normalFont);
		
		txtPanel3.setLayout(new GridLayout(1, 1));
		txtPanel3.setBackground(Color.WHITE);
		txtPanel3.setBounds(480, 100, width - 510, height - 300);
		
		txtReport3.setFont(normalFont);
		
		txtPanel3.add(txtReport3);
		
		report3Panel.add(backBtnRep3);
		report3Panel.add(orderedTimesLbl2);
		report3Panel.add(orderedTimesTxt2);
		report3Panel.add(orderPriceLbl);
		report3Panel.add(orderPriceTxt);
		report3Panel.add(generate3Btn);
		report3Panel.add(txtPanel3);
		
		//report4 panel:
		report4Panel.setLayout(null);
		report4Panel.setBackground(new Color(192, 227, 234));
		report4Panel.setBounds(0, 100, width, height - 100);
		report4Panel.setVisible(false);
		
		backBtnRep4.setBounds(20, 20, 160, 40);
		backBtnRep4.setFont(normalFont);
		
		dayLbl.setBounds(60, 120, 100, 40);
		dayLbl.setFont(normalFont);
		
		dayTxt.setBounds(110, 130, 160, 30);
		dayTxt.setFont(normalFont);
		
		generate4Btn.setBounds(200, 220, 160, 40);
		generate4Btn.setFont(normalFont);
		
		txtPanel4.setLayout(new GridLayout(1, 1));
		txtPanel4.setBackground(Color.WHITE);
		txtPanel4.setBounds(480, 100, width - 510, height - 300);
		
		txtReport4.setFont(normalFont);
		
		txtPanel4.add(txtReport4);
		
		report4Panel.add(backBtnRep4);
		report4Panel.add(dayLbl);
		report4Panel.add(dayTxt);
		report4Panel.add(generate4Btn);
		report4Panel.add(txtPanel4);
		
		
		
		
		backBtnRep1.addActionListener(this);
		generate1Btn.addActionListener(this);
		backBtnRep2.addActionListener(this);
		generate2Btn.addActionListener(this);
		backBtnRep3.addActionListener(this);
		generate3Btn.addActionListener(this);
		backBtnRep4.addActionListener(this);
		generate4Btn.addActionListener(this);
		
		
		
		// add all to main JFrame
		add(adminPanel);
		add(mainOperationPanel);
		add(importPanel);
		add(managePanel);
		add(generatePanel);
		add(report1Panel);
		add(report2Panel);
		add(report3Panel);
		add(report4Panel);
	}
	
	private void setVisibleTrueMainOperationPanel() {
		logInGUI.setVisible(false);
		mainOperationPanel.setVisible(true);
		importPanel.setVisible(false);
		managePanel.setVisible(false);
		addPanel.setVisible(false);
		deletePanel.setVisible(false);
		modifyPanel.setVisible(false);
		composedPanel.setVisible(false);
		generatePanel.setVisible(false);
		report1Panel.setVisible(false);
		report2Panel.setVisible(false);
		report3Panel.setVisible(false);
		report4Panel.setVisible(false);
	}
	private void setVisibleTrueImportPanel() {
		logInGUI.setVisible(false);
		mainOperationPanel.setVisible(false);
		importPanel.setVisible(true);
		managePanel.setVisible(false);
		addPanel.setVisible(false);
		deletePanel.setVisible(false);
		modifyPanel.setVisible(false);
		composedPanel.setVisible(false);
		generatePanel.setVisible(false);
		report1Panel.setVisible(false);
		report2Panel.setVisible(false);
		report3Panel.setVisible(false);
		report4Panel.setVisible(false);
	}
	private void setVisibleTrueManagePanel() {
		logInGUI.setVisible(false);
		mainOperationPanel.setVisible(false);
		importPanel.setVisible(false);
		managePanel.setVisible(true);
		managePanelOp.setVisible(false);
		addPanel.setVisible(false);
		deletePanel.setVisible(false);
		modifyPanel.setVisible(false);
		generatePanel.setVisible(false);
		report1Panel.setVisible(false);
		report2Panel.setVisible(false);
		report3Panel.setVisible(false);
		report4Panel.setVisible(false);
	}
	private void setVisibleTrueManageOpPanel() {
		logInGUI.setVisible(false);
		mainOperationPanel.setVisible(false);
		importPanel.setVisible(false);
		managePanel.setVisible(true);
		managePanelOp.setVisible(true);
		addPanel.setVisible(false);
		deletePanel.setVisible(false);
		modifyPanel.setVisible(false);
		composedPanel.setVisible(false);
		generatePanel.setVisible(false);
		report1Panel.setVisible(false);
		report2Panel.setVisible(false);
		report3Panel.setVisible(false);
		report4Panel.setVisible(false);
	}
	private void setVisibleFalseAddPanel() {
		logInGUI.setVisible(false);
		mainOperationPanel.setVisible(false);
		importPanel.setVisible(false);
		managePanel.setVisible(true);
		managePanelOp.setVisible(false);
		addPanel.setVisible(false);
		deletePanel.setVisible(false);
		modifyPanel.setVisible(false);
		generatePanel.setVisible(false);
		report1Panel.setVisible(false);
		report2Panel.setVisible(false);
		report3Panel.setVisible(false);
		report4Panel.setVisible(false);
	}
	private void setVisibleTrueAddPanel() {
		logInGUI.setVisible(false);
		mainOperationPanel.setVisible(false);
		importPanel.setVisible(false);
		managePanel.setVisible(true);
		managePanelOp.setVisible(false);
		addPanel.setVisible(true);
		deletePanel.setVisible(false);
		modifyPanel.setVisible(false);
		composedPanel.setVisible(false);
		generatePanel.setVisible(false);
		report1Panel.setVisible(false);
		report2Panel.setVisible(false);
		report3Panel.setVisible(false);
		report4Panel.setVisible(false);
	}
	private void setVisibleFalseDeletePanel() {
		logInGUI.setVisible(false);
		mainOperationPanel.setVisible(false);
		importPanel.setVisible(false);
		managePanel.setVisible(true);
		managePanelOp.setVisible(false);
		addPanel.setVisible(false);
		deletePanel.setVisible(false);
		modifyPanel.setVisible(false);
		generatePanel.setVisible(false);
		report1Panel.setVisible(false);
		report2Panel.setVisible(false);
		report3Panel.setVisible(false);
		report4Panel.setVisible(false);
	}
	private void setVisibleTrueDeletePanel() {
		logInGUI.setVisible(false);
		mainOperationPanel.setVisible(false);
		importPanel.setVisible(false);
		managePanel.setVisible(true);
		managePanelOp.setVisible(false);
		addPanel.setVisible(false);
		deletePanel.setVisible(true);
		modifyPanel.setVisible(false);
		composedPanel.setVisible(false);
		generatePanel.setVisible(false);
		report1Panel.setVisible(false);
		report2Panel.setVisible(false);
		report3Panel.setVisible(false);
		report4Panel.setVisible(false);
	}
	private void setVisibleFalseModifyPanel() {
		logInGUI.setVisible(false);
		mainOperationPanel.setVisible(false);
		importPanel.setVisible(false);
		managePanel.setVisible(true);
		managePanelOp.setVisible(false);
		addPanel.setVisible(false);
		deletePanel.setVisible(false);
		modifyPanel.setVisible(false);
		generatePanel.setVisible(false);
		report1Panel.setVisible(false);
		report2Panel.setVisible(false);
		report3Panel.setVisible(false);
		report4Panel.setVisible(false);
	}
	private void setVisibleTrueModifyPanel() {
		logInGUI.setVisible(false);
		mainOperationPanel.setVisible(false);
		importPanel.setVisible(false);
		managePanel.setVisible(true);
		managePanelOp.setVisible(false);
		addPanel.setVisible(false);
		deletePanel.setVisible(false);
		modifyPanel.setVisible(true);
		composedPanel.setVisible(false);
		generatePanel.setVisible(false);
		report1Panel.setVisible(false);
		report2Panel.setVisible(false);
		report3Panel.setVisible(false);
		report4Panel.setVisible(false);
	}
	private void setVisibleTrueComposePanel() {
		logInGUI.setVisible(false);
		mainOperationPanel.setVisible(false);
		importPanel.setVisible(false);
		managePanel.setVisible(true);
		managePanelOp.setVisible(false);
		addPanel.setVisible(false);
		deletePanel.setVisible(false);
		modifyPanel.setVisible(false);
		composedPanel.setVisible(true);
		generatePanel.setVisible(false);
		report1Panel.setVisible(false);
		report2Panel.setVisible(false);
		report3Panel.setVisible(false);
		report4Panel.setVisible(false);
	}
	
	private void setVisibleTrueGeneratePanel() {
		logInGUI.setVisible(false);
		mainOperationPanel.setVisible(false);
		importPanel.setVisible(false);
		managePanel.setVisible(false);
		generatePanel.setVisible(true);
		addPanel.setVisible(false);
		deletePanel.setVisible(false);
		modifyPanel.setVisible(false);
		composedPanel.setVisible(false);
		generateOpPanel.setVisible(false);
		report1Panel.setVisible(false);
		report2Panel.setVisible(false);
		report3Panel.setVisible(false);
		report4Panel.setVisible(false);
	}
	private void setVisibleTrueGenerateOpPanel() {
		logInGUI.setVisible(false);
		mainOperationPanel.setVisible(false);
		importPanel.setVisible(false);
		managePanel.setVisible(false);
		managePanelOp.setVisible(false);
		addPanel.setVisible(false);
		deletePanel.setVisible(false);
		modifyPanel.setVisible(false);
		composedPanel.setVisible(false);
		generatePanel.setVisible(true);
		generateOpPanel.setVisible(true);
		report1Panel.setVisible(false);
		report2Panel.setVisible(false);
		report3Panel.setVisible(false);
		report4Panel.setVisible(false);
	}
	private void setVisibleTrueReport1Panel() {
		logInGUI.setVisible(false);
		mainOperationPanel.setVisible(false);
		importPanel.setVisible(false);
		managePanel.setVisible(false);
		managePanelOp.setVisible(false);
		addPanel.setVisible(false);
		deletePanel.setVisible(false);
		modifyPanel.setVisible(false);
		composedPanel.setVisible(false);
		generatePanel.setVisible(false);
		generateOpPanel.setVisible(false);
		report1Panel.setVisible(true);
		report2Panel.setVisible(false);
		report3Panel.setVisible(false);
		report4Panel.setVisible(false);

	}
	private void setVisibleTrueReport2Panel() {
		logInGUI.setVisible(false);
		mainOperationPanel.setVisible(false);
		importPanel.setVisible(false);
		managePanel.setVisible(false);
		managePanelOp.setVisible(false);
		addPanel.setVisible(false);
		deletePanel.setVisible(false);
		modifyPanel.setVisible(false);
		composedPanel.setVisible(false);
		generatePanel.setVisible(false);
		generateOpPanel.setVisible(false);
		report1Panel.setVisible(false);
		report2Panel.setVisible(true);
		report3Panel.setVisible(false);
		report4Panel.setVisible(false);
	}
	private void setVisibleTrueReport3Panel() {
		logInGUI.setVisible(false);
		mainOperationPanel.setVisible(false);
		importPanel.setVisible(false);
		managePanel.setVisible(false);
		managePanelOp.setVisible(false);
		addPanel.setVisible(false);
		deletePanel.setVisible(false);
		modifyPanel.setVisible(false);
		composedPanel.setVisible(false);
		generatePanel.setVisible(false);
		generateOpPanel.setVisible(false);
		report1Panel.setVisible(false);
		report2Panel.setVisible(false);
		report3Panel.setVisible(true);
		report4Panel.setVisible(false);
	}
	private void setVisibleTrueReport4Panel() {
		logInGUI.setVisible(false);
		mainOperationPanel.setVisible(false);
		importPanel.setVisible(false);
		managePanel.setVisible(false);
		managePanelOp.setVisible(false);
		addPanel.setVisible(false);
		deletePanel.setVisible(false);
		modifyPanel.setVisible(false);
		composedPanel.setVisible(false);
		generatePanel.setVisible(false);
		generateOpPanel.setVisible(false);
		report1Panel.setVisible(false);
		report2Panel.setVisible(false);
		report3Panel.setVisible(false);
		report4Panel.setVisible(true);
	}
	private void setVisibleTrueLogInPanel() {
		logInGUI.setVisible(true);
		this.setVisible(false);
	}

	

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(backBtn)) {
			setVisibleTrueLogInPanel();
		}
		else if(e.getSource().equals(backBtnImp) || e.getSource().equals(backBtnMan) || e.getSource().equals(backBtnGen)) {
			setVisibleTrueMainOperationPanel();
		}
		else if(e.getSource().equals(backBtnRep1) || e.getSource().equals(backBtnRep2) || e.getSource().equals(backBtnRep3) || e.getSource().equals(backBtnRep4)) {
			setVisibleTrueGeneratePanel();
			setVisibleTrueGenerateOpPanel();
		}
		else if(e.getSource().equals(importBtn)) {
			setVisibleTrueImportPanel();
		}
		else if(e.getSource().equals(importBtnImport)) {
			System.out.println("Importing csv..");
			try {
				devService.initializeMenu();
				
				ArrayList<MenuItem> menu = devService.getItemsFromMenu();
				System.out.println("Menu size: " + menu.size());
				data = new Object[menu.size()][columns.length];
				for(int i=0; i< menu.size(); i++) {
					data[i][0] = menu.get(i).getTitle();
					data[i][1] = menu.get(i).getRating();
					data[i][2] = menu.get(i).getCalories();
					data[i][3] = menu.get(i).getProtein();
					data[i][4] = menu.get(i).getFat();
					data[i][5] = menu.get(i).getSodium();
					data[i][6] = menu.get(i).getPrice();
				}
				innerPanel.removeAll();
				importTable = new JTable(data, columns);
				innerPanel.add(new JScrollPane(importTable));
				//innerPanel.repaint();
				innerPanel.setVisible(false);
				innerPanel.setVisible(true);
				
				/*ArrayList<BaseProduct> products = devService.initializeMenu();
				System.out.println(products.size());
				data = new Object[products.size()][columns.length];
				for(int i=0; i<products.size(); i++) {*/
					/*for(int j=0; j< columns.length; j++) {
						data[i][j] = products.get(i).getTitle();
					}*/
					/*data[i][0] = products.get(i).getTitle();
					data[i][1] = products.get(i).getRating();
					data[i][2] = products.get(i).getCalories();
					data[i][3] = products.get(i).getProtein();
					data[i][4] = products.get(i).getFat();
					data[i][5] = products.get(i).getSodium();
					data[i][6] = products.get(i).getPrice();
				}*/
				/*for(int i=0; i< products.size(); i++) {
					data[i] = DeliveryService.retrieveProperties(products.get(i)).toArray();*/
					/*if(i < 10) {
						System.out.println(data[i][0] + " " +  data[i][1]);
						System.out.println(DeliveryService.retrieveProperties((BaseProduct)products.get(i)));
					}*/
				//}
				//System.out.println(data[1][2]);
				//System.out.println(data[1][2].getClass());
				//System.out.println(data[1].getClass());
				/*innerPanel.removeAll();
				importTable = new JTable(data, columns);
				innerPanel.add(new JScrollPane(importTable));
				innerPanel.repaint();*/
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getSource().equals(manageBtn)) {
			setVisibleTrueManageOpPanel();
		}
		else if(e.getSource().equals(addProductBtn)) {
			System.out.println("lol1");
			setVisibleTrueAddPanel();
		}
		else if(e.getSource().equals(addBtn)) {
			System.out.println("sa bag add");
			String title = addNameTxt.getText();
			float rating = Float.parseFloat(addRatingTxt.getText());
			int calories = Integer.parseInt(addCaloriesTxt.getText());
			int protein = Integer.parseInt(addProteinTxt.getText());
			int fat = Integer.parseInt(addFatTxt.getText());
			int sodium = Integer.parseInt(addSodiumTxt.getText());
			int price = Integer.parseInt(addPriceTxt.getText());
			MenuItem item = new BaseProduct(title, rating, calories, protein, fat, sodium, price);
			try {
				devService.addProduct(item);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getSource().equals(deleteProductBtn)) {
			System.out.println("lol2");
			setVisibleTrueDeletePanel();
		}
		else if(e.getSource().equals(deleteBtn)) {
			System.out.println("sa bag delete");
			String titleOfProduct = deleteNameTxt.getText();
			try {
				devService.deleteProduct(titleOfProduct);
			} catch(Exception e1) {
				e1.printStackTrace();
			}
		}
		else if(e.getSource().equals(modifyProductBtn)) {
			System.out.println("lol3");
			setVisibleTrueModifyPanel();
		}
		else if(e.getSource().equals(modifyBtn)) {
			System.out.println("sa bag modify");
			String titleOfProduct = modifyNameTxt.getText();
			float newRating = Float.parseFloat(modifyRatingTxt.getText());
			int newPrice = Integer.parseInt(modifyPriceTxt.getText());
			try {
				devService.editProduct(titleOfProduct, newRating, newPrice);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		else if(e.getSource().equals(createComposedBtn)) {
			System.out.println("lol4");
			this.setVisibleTrueComposePanel();
		}
		else if(e.getSource().equals(composeBtn)) {
			System.out.println("sa bag compose");
			String item1Name = composedItemTxt1.getText();
			String item2Name = composedItemTxt2.getText();
			try {
				devService.createComposedProduct(item1Name, item2Name);
			} catch(Exception e1) {
				e1.printStackTrace();
			}
		}
		else if(e.getSource().equals(generateBtn)) {
			setVisibleTrueGenerateOpPanel();
		}
		else if(e.getSource().equals(reportNo1)) {
			System.out.println("lol xd1");
			setVisibleTrueReport1Panel();
		}
		else if(e.getSource().equals(this.generate1Btn)) {
			System.out.println("abc");
			int startHour = Integer.parseInt(this.startHourTxt.getText());
			int endHour = Integer.parseInt(this.endHourTxt.getText());
			try {
				devService.generateReport1(startHour, endHour, txtReport1);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getSource().equals(reportNo2)) {
			System.out.println("lol xd2");
			setVisibleTrueReport2Panel();
		}
		else if(e.getSource().equals(this.generate2Btn)) {
			int noOrdered = Integer.parseInt(this.orderedTimesTxt.getText());
			try {
				devService.generateReport2(noOrdered, txtReport2);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getSource().equals(reportNo3)) {
			System.out.println("lol xd3");
			setVisibleTrueReport3Panel();
		}
		else if(e.getSource().equals(this.generate3Btn)) {
			int noTimes = Integer.parseInt(this.orderedTimesTxt2.getText());
			int amount = Integer.parseInt(this.orderPriceTxt.getText());
			try {
				devService.generateReport3(noTimes, amount, txtReport3);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getSource().equals(reportNo4)) {
			System.out.println("lol xd4");
			setVisibleTrueReport4Panel();
		}
		else if(e.getSource().equals(this.generate4Btn)) {
			System.out.println("4");
			int day = Integer.parseInt(this.dayTxt.getText());
			try {
				devService.generateReport4(day, txtReport4);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
