package presentationLayer;

import javax.swing.*;

import businessLayer.Observable;
import businessLayer.Order;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import presentationLayer.Observer;

public class EmployeeGUI extends JFrame implements Observer, ActionListener {
	private JPanel panel = new JPanel();
	private JPanel header = new JPanel();
	private JLabel employeeLbl = new JLabel("Employee");
	private JTextArea textArea = new JTextArea();
	private JButton backBtn = new JButton("Back");
	
	private Font headerFont = new Font("Serif", Font.ITALIC, 60);
	private Font normalFont = new Font("Serif", Font.PLAIN, 20);
	private LogInGUI logIn;
	//private Font 
	
	public EmployeeGUI(int width, int height, LogInGUI logIn){
		this.logIn = logIn;
		
		setSize(width, height);
		setResizable(false);
		setTitle("Employee");
		setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		setVisible(false);
		
		setLayout(null);
		getContentPane().setBackground(Color.LIGHT_GRAY);
		
		panel.setLayout(null);
		panel.setBackground(new Color(146, 254, 133));
		panel.setBounds(0, 100, width, height);
		
		textArea.setBounds(100, 100, width - 200, height - 300);
		textArea.setFont(normalFont);
		
		backBtn.setBounds(50, 20, 200, 60);
		backBtn.setFont(normalFont);
		
		panel.add(backBtn);
		panel.add(textArea);
		
		header.setLayout(null);
		header.setBackground(new Color(146, 220, 133));
		header.setBounds(0, 0, width, 100);
		
		employeeLbl.setBounds(250, 0, 300, 100);
		employeeLbl.setFont(headerFont);
		
		header.add(employeeLbl);
		
		add(header);
		add(panel);
		
		backBtn.addActionListener(this);
		
	}
	public EmployeeGUI(int width, int height){
		
		setSize(width, height);
		setResizable(false);
		setTitle("Employee");
		setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		// false sau true? Alege ce doresti! :))
		setVisible(true);
		
		setLayout(null);
		getContentPane().setBackground(Color.LIGHT_GRAY);
		
		panel.setLayout(null);
		panel.setBackground(new Color(146, 254, 133));
		panel.setBounds(0, 100, width, height);
		
		textArea.setBounds(100, 100, width - 200, height - 300);
		textArea.setFont(normalFont);
		
		backBtn.setBounds(50, 20, 200, 60);
		backBtn.setFont(normalFont);
		
		panel.add(backBtn);
		panel.add(textArea);
		
		header.setLayout(null);
		header.setBackground(new Color(146, 220, 133));
		header.setBounds(0, 0, width, 100);
		
		employeeLbl.setBounds(250, 0, 300, 100);
		employeeLbl.setFont(headerFont);
		
		header.add(employeeLbl);
		
		add(header);
		add(panel);
		
		backBtn.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(backBtn)) {
			logIn.setVisible(true);
			this.setVisible(false);
		}
		
	}

	@Override
	public void update(Observable obs, Order order) {
		// TODO Auto-generated method stub
		//textArea.setText("");
		textArea.append("Order ID: " + order.getOrderID() + ", Client name: " + order.getClientID() + ", Date: " + order.getOrderDate() + "\n");
		
	}
}