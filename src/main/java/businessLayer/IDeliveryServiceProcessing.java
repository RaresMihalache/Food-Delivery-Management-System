package businessLayer;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JTextArea;

public interface IDeliveryServiceProcessing {
	//public void manageProducts();
	//public void generateReports();
	//public void createOrder();
	//public void searchProducts();
	
	public void makeOrder(Order order, ArrayList<String> productsName, String userID) throws Exception;
	
	/**
	 * @pre true
	 * @post true
	 * @throws IOException
	 */
	public void initializeMenu() throws IOException;
	
	/**
	 * @pre item != null
	 * @post true
	 * @param item - adaugare item
	 * @throws Exception
	 */
	public void addProduct(MenuItem item) throws Exception;
	
	/**
	 * @pre !isEmpty()
	 * @post getSize() >= 0
	 * @param name - numele produsului pe cae il vom sterge
	 * @throws Exception
	 */
	public void deleteProduct(String name) throws Exception;
	
	/**
	 * @pre !name.equals("") && rating > 0 && rating < 5
	 * @post true
	 * @param name - numele produsului pe care il vom edita
	 * @param rating - ratingul nou
	 * @param price - pretul nou
	 * @throws Exception
	 */
	public void editProduct(String name, float rating, int price) throws Exception;
	
	/**
	 * @pre !name1.equals("") && !name2.equals("")
	 * @post true
	 * @param name1
	 * @param name2
	 * @throws Exception
	 */
	public void createComposedProduct(String name1, String name2) throws Exception;
	
	/**
	 * @pre !name.equals("")
	 * @post true
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public ArrayList<MenuItem> searchProductByName(String name) throws Exception;
	
	/**
	 * @pre mode >0 && mode < 7
	 * @post 
	 * @param min
	 * @param max
	 * @param mode
	 * @return
	 * @throws Exception
	 */
	public ArrayList<MenuItem> searchProductByInterval(int min, int max, int mode) throws Exception;
	
	/**
	 * @pre startHour >= 0 && endHour < 24
	 * @post true
	 * @param startHour
	 * @param endHour
	 * @param txtArea
	 * @throws Exception
	 */
	public void generateReport1(int startHour, int endHour, JTextArea txtArea) throws Exception;
	
	/**
	 * @pre true
	 * @post true
	 * @param amount
	 * @param txtArea
	 * @throws Exception
	 */
	public void generateReport2(int amount, JTextArea txtArea) throws Exception;
	
	/**
	 * @pre true
	 * @post true
	 * @param noTimes
	 * @param valueOfOrder
	 * @param txtArea
	 * @throws Exception
	 */
	public void generateReport3(int noTimes, int valueOfOrder, JTextArea txtArea) throws Exception;
	
	/**
	 * @pre day > 0 && day <= 31
	 * @post true
	 * @param day
	 * @param txtArea
	 * @throws Exception
	 */
	public void generateReport4(int day, JTextArea txtArea) throws Exception;
}
