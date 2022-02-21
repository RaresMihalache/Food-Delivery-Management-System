package dataLayer;

import java.io.*;
import java.util.*;

import businessLayer.*;

public class FileWriter {

	FileOutputStream os;
	OutputStreamWriter osw;
	BufferedWriter bw;
	private int totalSum = 0;
	
	private Order order;
	private ArrayList<MenuItem> products;
	
	public FileWriter(Order order, ArrayList<MenuItem> products) throws IOException{
		this.order = order;
		this.products = products;
		os = new FileOutputStream("Bill.txt");
		osw = new OutputStreamWriter(os, "UTF-16");
		bw = new BufferedWriter(osw);
		for(int i = 0; i < products.size(); i++) {
			bw.write(products.get(i).getTitle() + ": " + products.get(i).getPrice());
			totalSum = totalSum + products.get(i).getPrice();
			bw.newLine();
		}
		bw.write("total sum: " + totalSum);
		bw.close();
		
		
	}
}
