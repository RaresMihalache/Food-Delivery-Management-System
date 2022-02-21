package presentationLayer;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;

import businessLayer.BaseProduct;
import businessLayer.DeliveryService;
import businessLayer.MenuItem;
import businessLayer.Order;

import java.awt.*;

public class ClientGUI extends JFrame implements ActionListener {
	private LogInGUI logInGUI;
	private JPanel header = new JPanel();
	private JLabel clientLbl = new JLabel("Client");
	private JButton backBtn = new JButton("Back");
	private JButton viewMenuBtn = new JButton("View menu");
	private JButton searchProductsBtn = new JButton("Search");
	private JButton orderBtn = new JButton("Order now!");
	private JPanel mainOp = new JPanel();
	
	private JPanel viewPanel = new JPanel();
	private JButton backBtnView = new JButton("Back");	
	private JPanel innerPanel = new JPanel();
	private JTable viewTable;
	private Object[] columns;
	private Object[][] data;
	
	
	private JPanel searchPanel = new JPanel();
	private JButton searchBackBtn = new JButton("Back");
	private JButton searchKeywordBtn = new JButton("Search on keyword");
	private JButton searchRatingBtn = new JButton("Search on rating");
	private JButton searchCaloriesBtn = new JButton("Search on number of calories");
	private JButton searchProteinsBtn = new JButton("Search on number of proteins");
	private JButton searchFatsBtn = new JButton("Search on number of fats");
	private JButton searchSodiumBtn = new JButton("Search on number of sodium");
	private JButton searchPriceBtn = new JButton("Search on price");
	
	private JPanel searchKeywordPanel = new JPanel();
	private JButton searchKeywordBackBtn = new JButton("Back");
	private JLabel searchKeywordLbl = new JLabel("Keyword: ");
	private JTextField searchKeywordTxt = new JTextField(40);
	private JButton searchKeywordSearchBtn = new JButton("Search!");
	private JPanel innerSearchKeywordPanel = new JPanel();
	private JTable searchKeywordTable;
	
	
	private JPanel searchRatingPanel = new JPanel();
	private JButton searchRatingBackBtn = new JButton("Back");
	private JLabel searchLessRatingLbl = new JLabel("Bigger than (rating): ");
	private JTextField searchLessRatingTxt = new JTextField(40);
	private JLabel searchMoreRatingLbl = new JLabel("Less than (rating): ");
	private JTextField searchMoreRatingTxt = new JTextField(40);
	private JButton searchRatingSearchBtn = new JButton("Search!");
	private JPanel innerSearchRatingPanel = new JPanel();
	private JTable searchRatingTable;
	private Object[][] dataRating;
	
	private JPanel searchPricePanel = new JPanel();
	private JButton searchPriceBackBtn = new JButton("Back");
	private JLabel searchLessPriceLbl = new JLabel("Bigger than (price): ");
	private JTextField searchLessPriceTxt = new JTextField(40);
	private JLabel searchMorePriceLbl = new JLabel("Less than (price): ");
	private JTextField searchMorePriceTxt = new JTextField(40);
	private JButton searchPriceSearchBtn = new JButton("Search!");
	private JPanel innerSearchPricePanel = new JPanel();
	private JTable searchPriceTable;
	private Object[][] dataPrice;
	
	private JPanel searchCaloriesPanel = new JPanel();
	private JButton searchCaloriesBackBtn = new JButton("Back");
	private JLabel searchLessCaloriesLbl = new JLabel("Bigger than (calories): ");
	private JTextField searchLessCaloriesTxt = new JTextField(40);
	private JLabel searchMoreCaloriesLbl = new JLabel("Less than (calories): ");
	private JTextField searchMoreCaloriesTxt = new JTextField(40);
	private JButton searchCaloriesSearchBtn = new JButton("Search!");
	private JPanel innerSearchCaloriesPanel = new JPanel();
	private JTable searchCaloriesTable;
	private Object[][] dataCalories;
	
	private JPanel searchProteinsPanel = new JPanel();
	private JButton searchProteinsBackBtn = new JButton("Back");
	private JLabel searchLessProteinsLbl = new JLabel("Bigger than (proteins): ");
	private JTextField searchLessProteinsTxt = new JTextField(40);
	private JLabel searchMoreProteinsLbl = new JLabel("Less than (proteins): ");
	private JTextField searchMoreProteinsTxt = new JTextField(40);
	private JButton searchProteinsSearchBtn = new JButton("Search!");
	private JPanel innerSearchProteinsPanel = new JPanel();
	private JTable searchProteinsTable;
	private Object[][] dataProteins;
	
	private JPanel searchFatsPanel = new JPanel();
	private JButton searchFatsBackBtn = new JButton("Back");
	private JLabel searchLessFatsLbl = new JLabel("Bigger than (fats): ");
	private JTextField searchLessFatsTxt = new JTextField(40);
	private JLabel searchMoreFatsLbl = new JLabel("Less than (fats): ");
	private JTextField searchMoreFatsTxt = new JTextField(40);
	private JButton searchFatsSearchBtn = new JButton("Search!");
	private JPanel innerSearchFatsPanel = new JPanel();
	private JTable searchFatsTable;
	private Object[][] dataFats;
	
	private JPanel searchSodiumPanel = new JPanel();
	private JButton searchSodiumBackBtn = new JButton("Back");
	private JLabel searchLessSodiumLbl = new JLabel("Bigger than (sodium): ");
	private JTextField searchLessSodiumTxt = new JTextField(40);
	private JLabel searchMoreSodiumLbl = new JLabel("Less than (sodium): ");
	private JTextField searchMoreSodiumTxt = new JTextField(40);
	private JButton searchSodiumSearchBtn = new JButton("Search!");
	private JPanel innerSearchSodiumPanel = new JPanel();
	private JTable searchSodiumTable;
	private Object[][] dataSodium;
	
	
	private JPanel orderPanel = new JPanel();
	private JButton backBtnOrder = new JButton("Back");
	private JButton orderNowBtn = new JButton("Order now!");
	private JLabel product1Lbl = new JLabel("Product 1: ");
	private JTextField product1Txt = new JTextField(50);
	private JLabel product2Lbl = new JLabel("Product 2: ");
	private JTextField product2Txt = new JTextField(50);
	private JLabel product3Lbl = new JLabel("Product 3: ");
	private JTextField product3Txt = new JTextField(50);
	
	private Font headerFont = new Font("Serif", Font.ITALIC, 60);
	private Font normalFont = new Font("Serif", Font.PLAIN, 20);
	
	
	private ArrayList<Order> orderList = new ArrayList<Order>();
	private DeliveryService devService;
	
	public ClientGUI(int width, int height, LogInGUI logInGUI) throws Exception {
		this.logInGUI = logInGUI;
		devService = new DeliveryService();
		columns = DeliveryService.retrieveFields(BaseProduct.class).toArray();
		viewTable = new JTable(data, columns);
		searchKeywordTable = new JTable(data, columns);
		searchRatingTable = new JTable(data, columns);
		searchCaloriesTable = new JTable(data, columns);
		searchProteinsTable = new JTable(data, columns);
		searchFatsTable = new JTable(data, columns);
		searchSodiumTable = new JTable(data, columns);
		searchPriceTable = new JTable(data, columns);
		
		setSize(width, height);
		setResizable(false);
		getContentPane().setBackground(new Color(221, 154, 86));
		setTitle("Client");
		setLayout(null);
		setVisible(false);
		setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		
		header.setLayout(null);
		header.setBackground(new Color(230, 87, 86));
		header.setBounds(0, 0, width, 100);
		
		clientLbl.setBounds(250, 0, 200, 100);
		clientLbl.setFont(headerFont);
		
		header.add(clientLbl);
		
		backBtn.setBounds(50, 40, 150, 60);
		backBtn.setFont(normalFont);
		
		viewMenuBtn.setBounds(425, 40, 150, 60);
		viewMenuBtn.setFont(normalFont);
		
		
		viewPanel.setLayout(null);
		viewPanel.setBackground(new Color(221, 154, 86));
		viewPanel.setBounds(10, 120, width - 40, height);
		viewPanel.setVisible(false);
		
		innerPanel.setBounds(50, 90, width - 100, height - 255);
		innerPanel.setLayout(new GridLayout(1, 1));
		innerPanel.setBackground(Color.WHITE);
		backBtnView.setBounds(10, 10, 180, 60);
		
		viewPanel.add(backBtnView);
		viewPanel.add(innerPanel);
		
		searchProductsBtn.setBounds(800, 40, 150, 60);
		searchProductsBtn.setFont(normalFont);
		
		orderBtn.setBounds(1175, 40, 150, 60);
		orderBtn.setFont(normalFont);
		
		orderPanel = new JPanel();
		orderPanel.setLayout(null);
		orderPanel.setBackground(new Color(221, 154, 86));
		orderPanel.setBounds(10, 120, width - 40, height);
		orderPanel.setVisible(false);
		
		backBtnOrder.setBounds(50, 50, 160, 50);
		backBtnOrder.setFont(normalFont);
		
		product1Lbl.setBounds(550, 100, 100, 30);
		product1Lbl.setFont(normalFont);
		product1Txt.setBounds(650, 100, 600, 30);
		product1Txt.setFont(normalFont);
		
		product2Lbl.setBounds(550, 200, 100, 30);
		product2Lbl.setFont(normalFont);
		product2Txt.setBounds(650, 200, 600, 30);
		product2Txt.setFont(normalFont);
		
		product3Lbl.setBounds(550, 300, 100, 30);
		product3Lbl.setFont(normalFont);
		product3Txt.setBounds(650, 300, 600, 30);
		product3Txt.setFont(normalFont);
		
		orderNowBtn.setBounds(650, 500, 200, 50);
		orderNowBtn.setFont(normalFont);
		
		orderPanel.add(this.backBtnOrder);
		orderPanel.add(this.product1Lbl);
		orderPanel.add(this.product2Lbl);
		orderPanel.add(this.product3Lbl);
		orderPanel.add(this.product1Txt);
		orderPanel.add(this.product2Txt);
		orderPanel.add(this.product3Txt);
		orderPanel.add(this.orderNowBtn);
		
		
		searchPanel = new JPanel();
		searchPanel.setLayout(null);
		searchPanel.setBackground(new Color(221, 154, 86));
		searchPanel.setBounds(10, 120, width - 40, height);
		searchPanel.setVisible(false);
		
		searchBackBtn.setBounds(50, 50, 160, 40);
		searchBackBtn.setFont(normalFont);
		
		searchKeywordBtn.setBounds(300, 150, 200, 80);
		searchKeywordBtn.setFont(normalFont);
		
		searchRatingBtn.setBounds(300, 250, 200, 80);
		searchRatingBtn.setFont(normalFont);
		
		searchCaloriesBtn.setBounds(800, 150, 270, 80);
		searchCaloriesBtn.setFont(normalFont);
		
		searchProteinsBtn.setBounds(800, 250, 270, 80);
		searchProteinsBtn.setFont(normalFont);
		
		searchFatsBtn.setBounds(800, 350, 270, 80);
		searchFatsBtn.setFont(normalFont);
		
		searchSodiumBtn.setBounds(800, 450, 270, 80);
		searchSodiumBtn.setFont(normalFont);
		
		searchPriceBtn.setBounds(300, 350, 200, 80);
		searchPriceBtn.setFont(normalFont);
		
		searchPanel.add(searchBackBtn);
		searchPanel.add(searchKeywordBtn);
		searchPanel.add(searchRatingBtn);
		searchPanel.add(searchCaloriesBtn);
		searchPanel.add(searchProteinsBtn);
		searchPanel.add(searchFatsBtn);
		searchPanel.add(searchSodiumBtn);
		searchPanel.add(searchPriceBtn);
		
		
		searchKeywordPanel.setLayout(null);
		searchKeywordPanel.setBackground(new Color(221, 154, 86));
		searchKeywordPanel.setBounds(10, 120, width - 40, height);
		searchKeywordPanel.setVisible(false);
		
		searchKeywordBackBtn.setBounds(40, 30, 200, 40);
		searchKeywordBackBtn.setFont(normalFont);
		
		searchKeywordLbl.setBounds(600, 30, 100, 40);
		searchKeywordLbl.setFont(normalFont);
		
		searchKeywordTxt.setBounds(700, 30, 400, 40);
		searchKeywordTxt.setFont(normalFont);
		
		searchKeywordSearchBtn.setBounds(1200, 30, 150, 40);
		searchKeywordSearchBtn.setFont(normalFont);
		
		innerSearchKeywordPanel.setLayout(new GridLayout(1, 1));
		innerSearchKeywordPanel.setBackground(Color.WHITE);
		innerSearchKeywordPanel.setBounds(35, 100, width - 100, height - 280);
		
		searchKeywordPanel.add(searchKeywordBackBtn);
		searchKeywordPanel.add(searchKeywordLbl);
		searchKeywordPanel.add(searchKeywordTxt);
		searchKeywordPanel.add(searchKeywordSearchBtn);
		searchKeywordPanel.add(innerSearchKeywordPanel);
		
		/**/
		searchRatingPanel.setLayout(null);
		searchRatingPanel.setBackground(new Color(221, 154, 86));
		searchRatingPanel.setBounds(10, 120, width - 40, height);
		searchRatingPanel.setVisible(false);
		
		searchRatingBackBtn.setBounds(40, 30, 200, 40);
		searchRatingBackBtn.setFont(normalFont);
		
		searchLessRatingLbl.setBounds(20, 150, 170, 40);
		searchLessRatingLbl.setFont(normalFont);
		
		searchLessRatingTxt.setBounds(190, 150, 450, 40);
		searchLessRatingTxt.setFont(normalFont);
		
		searchMoreRatingLbl.setBounds(20, 250, 170, 40);
		searchMoreRatingLbl.setFont(normalFont);
		
		searchMoreRatingTxt.setBounds(190, 250, 450, 40);
		searchMoreRatingTxt.setFont(normalFont);
		
		searchRatingSearchBtn.setBounds(200, 500, 160, 60);
		searchRatingSearchBtn.setFont(normalFont);
		
		innerSearchRatingPanel.setLayout(new GridLayout(1, 1));
		innerSearchRatingPanel.setBackground(Color.WHITE);
		innerSearchRatingPanel.setBounds(700, 20, width - 650, height - 220);
		
		searchRatingPanel.add(searchRatingBackBtn);
		searchRatingPanel.add(searchLessRatingLbl);
		searchRatingPanel.add(searchLessRatingTxt);
		searchRatingPanel.add(searchMoreRatingLbl);
		searchRatingPanel.add(searchMoreRatingTxt);
		searchRatingPanel.add(searchRatingSearchBtn);
		searchRatingPanel.add(innerSearchRatingPanel);
		/**/
		
		//
		searchCaloriesPanel.setLayout(null);
		searchCaloriesPanel.setBackground(new Color(221, 154, 86));
		searchCaloriesPanel.setBounds(10, 120, width - 40, height);
		searchCaloriesPanel.setVisible(false);
		
		searchCaloriesBackBtn.setBounds(40, 30, 200, 40);
		searchCaloriesBackBtn.setFont(normalFont);
		
		searchLessCaloriesLbl.setBounds(20, 150, 180, 40);
		searchLessCaloriesLbl.setFont(normalFont);
		
		searchLessCaloriesTxt.setBounds(220, 150, 410, 40);
		searchLessCaloriesTxt.setFont(normalFont);
		
		searchMoreCaloriesLbl.setBounds(20, 250, 180, 40);
		searchMoreCaloriesLbl.setFont(normalFont);
		
		searchMoreCaloriesTxt.setBounds(220, 250, 410, 40);
		searchMoreCaloriesTxt.setFont(normalFont);
		
		searchCaloriesSearchBtn.setBounds(200, 500, 160, 60);
		searchCaloriesSearchBtn.setFont(normalFont);
		
		innerSearchCaloriesPanel.setLayout(new GridLayout(1, 1));
		innerSearchCaloriesPanel.setBackground(Color.WHITE);
		innerSearchCaloriesPanel.setBounds(700, 20, width - 650, height - 220);
		
		searchCaloriesPanel.add(searchCaloriesBackBtn);
		searchCaloriesPanel.add(searchLessCaloriesLbl);
		searchCaloriesPanel.add(searchLessCaloriesTxt);
		searchCaloriesPanel.add(searchMoreCaloriesLbl);
		searchCaloriesPanel.add(searchMoreCaloriesTxt);
		searchCaloriesPanel.add(searchCaloriesSearchBtn);
		searchCaloriesPanel.add(innerSearchCaloriesPanel);
		//
		
		searchProteinsPanel.setLayout(null);
		searchProteinsPanel.setBackground(new Color(221, 154, 86));
		searchProteinsPanel.setBounds(10, 120, width - 40, height);
		searchProteinsPanel.setVisible(false);
		
		searchProteinsBackBtn.setBounds(40, 30, 200, 40);
		searchProteinsBackBtn.setFont(normalFont);
		
		searchLessProteinsLbl.setBounds(20, 150, 190, 40);
		searchLessProteinsLbl.setFont(normalFont);
		
		searchLessProteinsTxt.setBounds(220, 150, 410, 40);
		searchLessProteinsTxt.setFont(normalFont);
		
		searchMoreProteinsLbl.setBounds(20, 250, 180, 40);
		searchMoreProteinsLbl.setFont(normalFont);
		
		searchMoreProteinsTxt.setBounds(220, 250, 410, 40);
		searchMoreProteinsTxt.setFont(normalFont);
		
		searchProteinsSearchBtn.setBounds(200, 500, 160, 60);
		searchProteinsSearchBtn.setFont(normalFont);
		
		innerSearchProteinsPanel.setLayout(new GridLayout(1, 1));
		innerSearchProteinsPanel.setBackground(Color.WHITE);
		innerSearchProteinsPanel.setBounds(700, 20, width - 650, height - 220);
		
		searchProteinsPanel.add(searchProteinsBackBtn);
		searchProteinsPanel.add(searchLessProteinsLbl);
		searchProteinsPanel.add(searchLessProteinsTxt);
		searchProteinsPanel.add(searchMoreProteinsLbl);
		searchProteinsPanel.add(searchMoreProteinsTxt);
		searchProteinsPanel.add(searchProteinsSearchBtn);
		searchProteinsPanel.add(innerSearchProteinsPanel);
		//
		
		searchFatsPanel.setLayout(null);
		searchFatsPanel.setBackground(new Color(221, 154, 86));
		searchFatsPanel.setBounds(10, 120, width - 40, height);
		searchFatsPanel.setVisible(false);
		
		searchFatsBackBtn.setBounds(40, 30, 200, 40);
		searchFatsBackBtn.setFont(normalFont);
		
		searchLessFatsLbl.setBounds(20, 150, 190, 40);
		searchLessFatsLbl.setFont(normalFont);
		
		searchLessFatsTxt.setBounds(220, 150, 410, 40);
		searchLessFatsTxt.setFont(normalFont);
		
		searchMoreFatsLbl.setBounds(20, 250, 180, 40);
		searchMoreFatsLbl.setFont(normalFont);
		
		searchMoreFatsTxt.setBounds(220, 250, 410, 40);
		searchMoreFatsTxt.setFont(normalFont);
		
		searchFatsSearchBtn.setBounds(200, 500, 160, 60);
		searchFatsSearchBtn.setFont(normalFont);
		
		innerSearchFatsPanel.setLayout(new GridLayout(1, 1));
		innerSearchFatsPanel.setBackground(Color.WHITE);
		innerSearchFatsPanel.setBounds(700, 20, width - 650, height - 220);
		
		searchFatsPanel.add(searchFatsBackBtn);
		searchFatsPanel.add(searchLessFatsLbl);
		searchFatsPanel.add(searchLessFatsTxt);
		searchFatsPanel.add(searchMoreFatsLbl);
		searchFatsPanel.add(searchMoreFatsTxt);
		searchFatsPanel.add(searchFatsSearchBtn);
		searchFatsPanel.add(innerSearchFatsPanel);
		//
		
		searchSodiumPanel.setLayout(null);
		searchSodiumPanel.setBackground(new Color(221, 154, 86));
		searchSodiumPanel.setBounds(10, 120, width - 40, height);
		searchSodiumPanel.setVisible(false);
		
		searchSodiumBackBtn.setBounds(40, 30, 200, 40);
		searchSodiumBackBtn.setFont(normalFont);
		
		searchLessSodiumLbl.setBounds(20, 150, 190, 40);
		searchLessSodiumLbl.setFont(normalFont);
		
		searchLessSodiumTxt.setBounds(220, 150, 410, 40);
		searchLessSodiumTxt.setFont(normalFont);
		
		searchMoreSodiumLbl.setBounds(20, 250, 180, 40);
		searchMoreSodiumLbl.setFont(normalFont);
		
		searchMoreSodiumTxt.setBounds(220, 250, 410, 40);
		searchMoreSodiumTxt.setFont(normalFont);
		
		searchSodiumSearchBtn.setBounds(200, 500, 160, 60);
		searchSodiumSearchBtn.setFont(normalFont);
		
		innerSearchSodiumPanel.setLayout(new GridLayout(1, 1));
		innerSearchSodiumPanel.setBackground(Color.WHITE);
		innerSearchSodiumPanel.setBounds(700, 20, width - 650, height - 220);
		
		searchSodiumPanel.add(searchSodiumBackBtn);
		searchSodiumPanel.add(searchLessSodiumLbl);
		searchSodiumPanel.add(searchLessSodiumTxt);
		searchSodiumPanel.add(searchMoreSodiumLbl);
		searchSodiumPanel.add(searchMoreSodiumTxt);
		searchSodiumPanel.add(searchSodiumSearchBtn);
		searchSodiumPanel.add(innerSearchSodiumPanel);
		//
		
		searchPricePanel.setLayout(null);
		searchPricePanel.setBackground(new Color(221, 154, 86));
		searchPricePanel.setBounds(10, 120, width - 40, height);
		searchPricePanel.setVisible(false);
		
		searchPriceBackBtn.setBounds(40, 30, 200, 40);
		searchPriceBackBtn.setFont(normalFont);
		
		searchLessPriceLbl.setBounds(20, 150, 190, 40);
		searchLessPriceLbl.setFont(normalFont);
		
		searchLessPriceTxt.setBounds(220, 150, 410, 40);
		searchLessPriceTxt.setFont(normalFont);
		
		searchMorePriceLbl.setBounds(20, 250, 180, 40);
		searchMorePriceLbl.setFont(normalFont);
		
		searchMorePriceTxt.setBounds(220, 250, 410, 40);
		searchMorePriceTxt.setFont(normalFont);
		
		searchPriceSearchBtn.setBounds(200, 500, 160, 60);
		searchPriceSearchBtn.setFont(normalFont);
		
		innerSearchPricePanel.setLayout(new GridLayout(1, 1));
		innerSearchPricePanel.setBackground(Color.WHITE);
		innerSearchPricePanel.setBounds(700, 20, width - 650, height - 220);
		
		searchPricePanel.add(searchPriceBackBtn);
		searchPricePanel.add(searchLessPriceLbl);
		searchPricePanel.add(searchLessPriceTxt);
		searchPricePanel.add(searchMorePriceLbl);
		searchPricePanel.add(searchMorePriceTxt);
		searchPricePanel.add(searchPriceSearchBtn);
		searchPricePanel.add(innerSearchPricePanel);
		//
		
		mainOp.setLayout(null);
		mainOp.setBackground(new Color(221, 154, 86));
		mainOp.setBounds(0, 100, width, height);
		
		mainOp.add(backBtn);
		mainOp.add(viewMenuBtn);
		mainOp.add(searchProductsBtn);
		mainOp.add(orderBtn);
		
		add(header);
		add(mainOp);
		add(viewPanel);
		add(searchPanel);
		add(searchKeywordPanel);
		add(searchRatingPanel);
		add(searchPricePanel);
		add(searchCaloriesPanel);
		add(searchProteinsPanel);
		add(searchFatsPanel);
		add(searchSodiumPanel);
		add(orderPanel);
		
		
		backBtn.addActionListener(this);
		backBtnView.addActionListener(this);
		viewMenuBtn.addActionListener(this);
		searchBackBtn.addActionListener(this);
		searchKeywordBtn.addActionListener(this);
		searchRatingBtn.addActionListener(this);
		searchCaloriesBtn.addActionListener(this);
		searchProteinsBtn.addActionListener(this);
		searchFatsBtn.addActionListener(this);
		searchSodiumBtn.addActionListener(this);
		searchPriceBtn.addActionListener(this);
		
		searchKeywordBackBtn.addActionListener(this);
		searchRatingBackBtn.addActionListener(this);
		searchPriceBackBtn.addActionListener(this);
		searchCaloriesBackBtn.addActionListener(this);
		searchProteinsBackBtn.addActionListener(this);
		searchFatsBackBtn.addActionListener(this);
		searchSodiumBackBtn.addActionListener(this);
		
		searchKeywordSearchBtn.addActionListener(this);
		searchRatingSearchBtn.addActionListener(this);
		searchCaloriesSearchBtn.addActionListener(this);
		searchProteinsSearchBtn.addActionListener(this);
		searchFatsSearchBtn.addActionListener(this);
		searchSodiumSearchBtn.addActionListener(this);
		searchPriceSearchBtn.addActionListener(this);
		
		
		backBtnOrder.addActionListener(this);
		orderNowBtn.addActionListener(this);
		searchProductsBtn.addActionListener(this);
		orderBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(backBtn)) {
			logInGUI.setVisible(true);
			this.setVisible(false);
		}
		else if(e.getSource().equals(backBtnView) || e.getSource().equals(backBtnOrder) || e.getSource().equals(searchBackBtn)) {
			setVisibleTrueMainOpPanel();
		}
		else if(e.getSource().equals(searchKeywordBackBtn) || e.getSource().equals(searchRatingBackBtn) || e.getSource().equals(searchPriceBackBtn) || 
				e.getSource().equals(searchCaloriesBackBtn) || e.getSource().equals(searchProteinsBackBtn) || e.getSource().equals(searchFatsBackBtn) || 
				e.getSource().equals(searchSodiumBackBtn)) {
			System.out.println("btn pressed");
			this.setVisibleTrueSearchPanel();
		}
		else if(e.getSource().equals(viewMenuBtn)) {
			System.out.println("View menu btn pressed!");
			setVisibleTrueViewPanel();
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
				viewTable = new JTable(data, columns);
				innerPanel.add(new JScrollPane(viewTable));
				//innerPanel.repaint();
				innerPanel.setVisible(false);
				innerPanel.setVisible(true);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getSource().equals(searchProductsBtn)) {
			System.out.println("src products btn pressed!");
			setVisibleTrueSearchPanel();
		}
		else if(e.getSource().equals(orderBtn)) {
			System.out.println("order btn pressed!");
			setVisibleTrueOrderPanel();
		}
		else if(e.getSource().equals(orderNowBtn)) {
			System.out.println("order now btn pressed!!");
			/// TODO NOWWWWWWWWW!!! Gandit cum sa fac cu creatul de order... cum generez id pt order
			String usernameClient = logInGUI.getUsernameLoggedIn();
			//System.out.println("Current account:   ----  :    " + this.logInGUI.getUsernameLoggedIn());
			Order newOrder = new Order();
			int randomMinute = getRandomNumber(0, 59);
			int randomHour = getRandomNumber(0, 24);
			int randomDay = getRandomNumber(1, 31);
			
			Date newOrderDate = new Date(121, 4, randomDay, randomHour, randomMinute);
			newOrder.setOrderDate(newOrderDate);
			
			ArrayList<String> productsToOrder = new ArrayList<String>();
			String product1 = product1Txt.getText();
			String product2 = product2Txt.getText();
			String product3 = product3Txt.getText();
			
			productsToOrder.add(product1);
			productsToOrder.add(product2);
			productsToOrder.add(product3);
			
			try {
				this.devService.initializeOrderFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try {
				devService.makeOrder(newOrder, productsToOrder, usernameClient);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try {
				devService.getOrders();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getSource().equals(searchKeywordBtn)) {
			System.out.println("search on keyword");
			setVisibleTrueSearchKeywordPanel();
		}
		else if(e.getSource().equals(searchKeywordSearchBtn)) {
			String searchName = this.searchKeywordTxt.getText();
			System.out.println(searchName);
			ArrayList<MenuItem> menu;
			try {
				menu = devService.searchProductByName(searchName);
				data = new Object[menu.size()][columns.length];
				for(int i=0; i<menu.size(); i++) {
					data[i][0] = menu.get(i).getTitle();
					data[i][1] = menu.get(i).getRating();
					data[i][2] = menu.get(i).getCalories();
					data[i][3] = menu.get(i).getProtein();
					data[i][4] = menu.get(i).getFat();
					data[i][5] = menu.get(i).getSodium();
					data[i][6] = menu.get(i).getPrice();
				}
				innerSearchKeywordPanel.removeAll();
				searchKeywordTable = new JTable(data, columns);
				innerSearchKeywordPanel.add(new JScrollPane(searchKeywordTable));
				innerSearchKeywordPanel.setVisible(false);
				innerSearchKeywordPanel.setVisible(true);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//innerPanel.repaint();
		}
		else if(e.getSource().equals(this.searchRatingSearchBtn)) {
			int minRating = Integer.parseInt(this.searchLessRatingTxt.getText());
			int maxRating = Integer.parseInt(this.searchMoreRatingTxt.getText());
			ArrayList<MenuItem> menu;
			try {
				menu = devService.searchProductByInterval(minRating, maxRating, 1);
				data = new Object[menu.size()][columns.length];
				for(int i=0; i<menu.size(); i++) {
					data[i][0] = menu.get(i).getTitle();
					data[i][1] = menu.get(i).getRating();
					data[i][2] = menu.get(i).getCalories();
					data[i][3] = menu.get(i).getProtein();
					data[i][4] = menu.get(i).getFat();
					data[i][5] = menu.get(i).getSodium();
					data[i][6] = menu.get(i).getPrice();
				}
				innerSearchRatingPanel.removeAll();
				searchRatingTable = new JTable(data, columns);
				innerSearchRatingPanel.add(new JScrollPane(searchRatingTable));
				innerSearchRatingPanel.setVisible(false);
				innerSearchRatingPanel.setVisible(true);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getSource().equals(this.searchCaloriesSearchBtn)) {
			int minCalories = Integer.parseInt(this.searchLessCaloriesTxt.getText());
			int maxCalories = Integer.parseInt(this.searchMoreCaloriesTxt.getText());
			ArrayList<MenuItem> menu;
			try {
				menu = devService.searchProductByInterval(minCalories, maxCalories, 2);
				data = new Object[menu.size()][columns.length];
				for(int i=0; i<menu.size(); i++) {
					data[i][0] = menu.get(i).getTitle();
					data[i][1] = menu.get(i).getRating();
					data[i][2] = menu.get(i).getCalories();
					data[i][3] = menu.get(i).getProtein();
					data[i][4] = menu.get(i).getFat();
					data[i][5] = menu.get(i).getSodium();
					data[i][6] = menu.get(i).getPrice();
				}
				innerSearchCaloriesPanel.removeAll();
				searchCaloriesTable = new JTable(data, columns);
				innerSearchCaloriesPanel.add(new JScrollPane(searchCaloriesTable));
				innerSearchCaloriesPanel.setVisible(false);
				innerSearchCaloriesPanel.setVisible(true);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getSource().equals(this.searchProteinsSearchBtn)) {
			int minProteins = Integer.parseInt(this.searchLessProteinsTxt.getText());
			int maxProteins = Integer.parseInt(this.searchMoreProteinsTxt.getText());
			ArrayList<MenuItem> menu;
			try {
				menu = devService.searchProductByInterval(minProteins, maxProteins, 3);
				data = new Object[menu.size()][columns.length];
				for(int i=0; i<menu.size(); i++) {
					data[i][0] = menu.get(i).getTitle();
					data[i][1] = menu.get(i).getRating();
					data[i][2] = menu.get(i).getCalories();
					data[i][3] = menu.get(i).getProtein();
					data[i][4] = menu.get(i).getFat();
					data[i][5] = menu.get(i).getSodium();
					data[i][6] = menu.get(i).getPrice();
				}
				innerSearchProteinsPanel.removeAll();
				searchProteinsTable = new JTable(data, columns);
				innerSearchProteinsPanel.add(new JScrollPane(searchProteinsTable));
				innerSearchProteinsPanel.setVisible(false);
				innerSearchProteinsPanel.setVisible(true);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getSource().equals(this.searchFatsSearchBtn)) {
			int minFats = Integer.parseInt(this.searchLessFatsTxt.getText());
			int maxFats = Integer.parseInt(this.searchMoreFatsTxt.getText());
			ArrayList<MenuItem> menu;
			try {
				menu = devService.searchProductByInterval(minFats, maxFats, 4);
				data = new Object[menu.size()][columns.length];
				for(int i=0; i<menu.size(); i++) {
					data[i][0] = menu.get(i).getTitle();
					data[i][1] = menu.get(i).getRating();
					data[i][2] = menu.get(i).getCalories();
					data[i][3] = menu.get(i).getProtein();
					data[i][4] = menu.get(i).getFat();
					data[i][5] = menu.get(i).getSodium();
					data[i][6] = menu.get(i).getPrice();
				}
				innerSearchFatsPanel.removeAll();
				searchFatsTable = new JTable(data, columns);
				innerSearchFatsPanel.add(new JScrollPane(searchFatsTable));
				innerSearchFatsPanel.setVisible(false);
				innerSearchFatsPanel.setVisible(true);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getSource().equals(this.searchSodiumSearchBtn)) {
			int minSodium = Integer.parseInt(this.searchLessSodiumTxt.getText());
			int maxSodium = Integer.parseInt(this.searchMoreSodiumTxt.getText());
			ArrayList<MenuItem> menu;
			try {
				menu = devService.searchProductByInterval(minSodium, maxSodium, 5);
				data = new Object[menu.size()][columns.length];
				for(int i=0; i<menu.size(); i++) {
					data[i][0] = menu.get(i).getTitle();
					data[i][1] = menu.get(i).getRating();
					data[i][2] = menu.get(i).getCalories();
					data[i][3] = menu.get(i).getProtein();
					data[i][4] = menu.get(i).getFat();
					data[i][5] = menu.get(i).getSodium();
					data[i][6] = menu.get(i).getPrice();
				}
				innerSearchSodiumPanel.removeAll();
				searchSodiumTable = new JTable(data, columns);
				innerSearchSodiumPanel.add(new JScrollPane(searchSodiumTable));
				innerSearchSodiumPanel.setVisible(false);
				innerSearchSodiumPanel.setVisible(true);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getSource().equals(this.searchPriceSearchBtn)) {
			int minPrice = Integer.parseInt(this.searchLessPriceTxt.getText());
			int maxPrice = Integer.parseInt(this.searchMorePriceTxt.getText());
			ArrayList<MenuItem> menu;
			try {
				menu = devService.searchProductByInterval(minPrice, maxPrice, 6);
				data = new Object[menu.size()][columns.length];
				for(int i=0; i<menu.size(); i++) {
					data[i][0] = menu.get(i).getTitle();
					data[i][1] = menu.get(i).getRating();
					data[i][2] = menu.get(i).getCalories();
					data[i][3] = menu.get(i).getProtein();
					data[i][4] = menu.get(i).getFat();
					data[i][5] = menu.get(i).getSodium();
					data[i][6] = menu.get(i).getPrice();
				}
				innerSearchPricePanel.removeAll();
				searchPriceTable = new JTable(data, columns);
				innerSearchPricePanel.add(new JScrollPane(searchPriceTable));
				innerSearchPricePanel.setVisible(false);
				innerSearchPricePanel.setVisible(true);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getSource().equals(searchRatingBtn)) {
			System.out.println("search on rating");
			setVisibleTrueSearchRatingPanel();
		}
		else if(e.getSource().equals(searchCaloriesBtn)) {
			System.out.println("search on calories");
			setVisibleTrueSearchCaloriesPanel();
		}
		else if(e.getSource().equals(searchProteinsBtn)) {
			System.out.println("search on proteins");
			setVisibleTrueSearchProteinsPanel();
		}
		else if(e.getSource().equals(searchFatsBtn)) {
			System.out.println("search on fats");
			setVisibleTrueSearchFatsPanel();
		}
		else if(e.getSource().equals(searchSodiumBtn)) {
			System.out.println("search on sodium");
			setVisibleTrueSearchSodiumPanel();
		}
		else if(e.getSource().equals(searchPriceBtn)) {
			System.out.println("search on price");
			setVisibleTrueSearchPricePanel();
		}
		
	}
	
	private int getRandomNumber(int min, int max) {
		return (int) (Math.random() * (max - min) + min);
	}
	
	
	
	private void setVisibleTrueMainOpPanel() {
		logInGUI.setVisible(false);

		mainOp.setVisible(true);
		viewPanel.setVisible(false);
		searchPanel.setVisible(false);
		searchKeywordPanel.setVisible(false);
		searchRatingPanel.setVisible(false);
		searchPricePanel.setVisible(false);
		searchCaloriesPanel.setVisible(false);
		searchProteinsPanel.setVisible(false);
		searchFatsPanel.setVisible(false);
		searchSodiumPanel.setVisible(false);
		orderPanel.setVisible(false);
	}
	private void setVisibleTrueViewPanel() {
		logInGUI.setVisible(false);

		mainOp.setVisible(false);
		viewPanel.setVisible(true);
		searchPanel.setVisible(false);
		searchKeywordPanel.setVisible(false);
		searchRatingPanel.setVisible(false);
		searchPricePanel.setVisible(false);
		searchCaloriesPanel.setVisible(false);
		searchProteinsPanel.setVisible(false);
		searchFatsPanel.setVisible(false);
		searchSodiumPanel.setVisible(false);
		orderPanel.setVisible(false);
	
	}
	private void setVisibleTrueSearchPanel() {
		logInGUI.setVisible(false);

		mainOp.setVisible(false);
		viewPanel.setVisible(false);
		searchPanel.setVisible(true);
		searchKeywordPanel.setVisible(false);
		searchRatingPanel.setVisible(false);
		searchPricePanel.setVisible(false);
		searchCaloriesPanel.setVisible(false);
		searchProteinsPanel.setVisible(false);
		searchFatsPanel.setVisible(false);
		searchSodiumPanel.setVisible(false);
		orderPanel.setVisible(false);
	}
	private void setVisibleTrueSearchKeywordPanel() {
		logInGUI.setVisible(false);

		mainOp.setVisible(false);
		viewPanel.setVisible(false);
		searchPanel.setVisible(false);
		searchKeywordPanel.setVisible(true);
		searchRatingPanel.setVisible(false);
		searchPricePanel.setVisible(false);
		searchCaloriesPanel.setVisible(false);
		searchProteinsPanel.setVisible(false);
		searchFatsPanel.setVisible(false);
		searchSodiumPanel.setVisible(false);
		orderPanel.setVisible(false);
	}
	private void setVisibleTrueSearchRatingPanel() {
		logInGUI.setVisible(false);

		mainOp.setVisible(false);
		viewPanel.setVisible(false);
		searchPanel.setVisible(false);
		searchKeywordPanel.setVisible(false);
		searchRatingPanel.setVisible(true);
		searchPricePanel.setVisible(false);
		searchCaloriesPanel.setVisible(false);
		searchProteinsPanel.setVisible(false);
		searchFatsPanel.setVisible(false);
		searchSodiumPanel.setVisible(false);
		orderPanel.setVisible(false);
	}
	private void setVisibleTrueSearchPricePanel() {
		logInGUI.setVisible(false);

		mainOp.setVisible(false);
		viewPanel.setVisible(false);
		searchPanel.setVisible(false);
		searchKeywordPanel.setVisible(false);
		searchRatingPanel.setVisible(false);
		searchPricePanel.setVisible(true);
		searchCaloriesPanel.setVisible(false);
		searchProteinsPanel.setVisible(false);
		searchFatsPanel.setVisible(false);
		searchSodiumPanel.setVisible(false);
		orderPanel.setVisible(false);
	}
	private void setVisibleTrueSearchCaloriesPanel() {
		logInGUI.setVisible(false);

		mainOp.setVisible(false);
		viewPanel.setVisible(false);
		searchPanel.setVisible(false);
		searchKeywordPanel.setVisible(false);
		searchRatingPanel.setVisible(false);
		searchPricePanel.setVisible(false);
		searchCaloriesPanel.setVisible(true);
		searchProteinsPanel.setVisible(false);
		searchFatsPanel.setVisible(false);
		searchSodiumPanel.setVisible(false);
		orderPanel.setVisible(false);
	}
	private void setVisibleTrueSearchProteinsPanel() {
		logInGUI.setVisible(false);

		mainOp.setVisible(false);
		viewPanel.setVisible(false);
		searchPanel.setVisible(false);
		searchKeywordPanel.setVisible(false);
		searchRatingPanel.setVisible(false);
		searchPricePanel.setVisible(false);
		searchCaloriesPanel.setVisible(false);
		searchProteinsPanel.setVisible(true);
		searchFatsPanel.setVisible(false);
		searchSodiumPanel.setVisible(false);
		orderPanel.setVisible(false);
	}
	private void setVisibleTrueSearchFatsPanel() {
		logInGUI.setVisible(false);

		mainOp.setVisible(false);
		viewPanel.setVisible(false);
		searchPanel.setVisible(false);
		searchKeywordPanel.setVisible(false);
		searchRatingPanel.setVisible(false);
		searchPricePanel.setVisible(false);
		searchCaloriesPanel.setVisible(false);
		searchProteinsPanel.setVisible(false);
		searchFatsPanel.setVisible(true);
		searchSodiumPanel.setVisible(false);
		orderPanel.setVisible(false);
	}
	private void setVisibleTrueSearchSodiumPanel() {
		logInGUI.setVisible(false);

		mainOp.setVisible(false);
		viewPanel.setVisible(false);
		searchPanel.setVisible(false);
		searchKeywordPanel.setVisible(false);
		searchRatingPanel.setVisible(false);
		searchPricePanel.setVisible(false);
		searchCaloriesPanel.setVisible(false);
		searchProteinsPanel.setVisible(false);
		searchFatsPanel.setVisible(false);
		searchSodiumPanel.setVisible(true);
		orderPanel.setVisible(false);
	}
	private void setVisibleTrueOrderPanel() {
		logInGUI.setVisible(false);
		
		mainOp.setVisible(false);
		viewPanel.setVisible(false);
		searchPanel.setVisible(false);
		searchKeywordPanel.setVisible(false);
		searchRatingPanel.setVisible(false);
		searchPricePanel.setVisible(false);
		searchCaloriesPanel.setVisible(false);
		searchProteinsPanel.setVisible(false);
		searchFatsPanel.setVisible(false);
		searchSodiumPanel.setVisible(false);
		orderPanel.setVisible(true);
	}
	
	
}
