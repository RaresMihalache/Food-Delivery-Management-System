package businessLayer;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.*;
import java.util.stream.Collectors;
import javax.swing.*;
import dataLayer.*;
import dataLayer.FileWriter;

/**
 * Represents the DeliveryService class. It is used to implement the logic of the application
 * @author rares
 *
 */
public class DeliveryService implements IDeliveryServiceProcessing {

	private FileInputStream fileInMenu;
	private FileOutputStream fileOutMenu;
	private Map<Order, ArrayList<MenuItem>> hashMap;
	private ArrayList<MenuItem> menuItem;
	private Observable obs = new Observable();
	
	/**
	 * Constructor - initializes Map<Order, ArrayList<MenuItem>> hashMap instance variable
	 * @throws Exception 
	 */
	public DeliveryService() throws Exception{
		hashMap = new HashMap<Order, ArrayList<MenuItem>>();
		menuItem = getItemsFromMenu();
	}
	
	/**
	 * Puts all fields of an Object into an Array
	 * @param object - represents the target
	 * @return It returns an ArrayList with the fields of the given parameter
	 */
	public static ArrayList<Object> retrieveFields(Class object){
		ArrayList <Object> myArray = new ArrayList();
		for(Field field : object.getDeclaredFields()) {
			myArray.add(field.getName());
		}
		return myArray;
	}
	
	public boolean invariant() {
		return menuItem.size() <= 0 ? false : true;
	}
	/**
	 * Initializare fisier cu comenzi.	
	 * @throws IOException
	 */
	public void initializeOrderFile() throws IOException {
		try {
			Path path = Paths.get("order.ser");
			System.out.println(Files.size(path));
			if(Files.size(path) <= 10) {
				System.out.println("Se initializeaza order-list...");
			}
			else {
				return ;
			}
		} catch(Exception ex) {
			System.out.println("Nu exista fisierul pt order-list... se creeaza");
		}
		
		fileOutMenu = new FileOutputStream("order.ser");
		fileInMenu = new FileInputStream("order.ser");
		fileOutMenu.close();
		fileInMenu.close();
	}
	/**
	 * Face o comanda
	 * @param order
	 * @param productsName Numele produselor pe care le punem in comanda
	 * @param userID -> ID-ul(numele) pe care il asignam la client
	 * @throws Exception
	 */
	public void makeOrder(Order order, ArrayList<String> productsName, String userID) throws Exception {
		Path path = Paths.get("order.ser");
		System.out.println("Make order size: " + Files.size(path));
		ArrayList<MenuItem> menu = getItemsFromMenu();
		ArrayList<MenuItem> productsInOrder = new ArrayList<MenuItem>();
		int found = 0;
			
		for(String s1 : productsName) {
			for(MenuItem m2 : menu) {
				if(s1.equals(m2.getTitle())) {
					found++;
					productsInOrder.add(m2);
					break;
				}
			}
		}
		
		if(found == productsName.size()) {
			System.out.println("User: " + userID);
			order.setClientID(userID);
			if(Files.size(path) > 10) {
				hashMap = getOrders();
			}
			order.setOrderID(hashMap.size() + 1);
			System.out.println(hashMap.size());
			hashMap.put(order, productsInOrder);
			obs.setOrder(order);
			obs.notifyObservers();
			Serializator.serializeUnshared("order.ser", hashMap);
			FileWriter fw = new FileWriter(order, productsInOrder);
			JOptionPane.showMessageDialog(null, "Order placed successfully!");
		}
		else {
			JOptionPane.showMessageDialog(null, "Could not find all products!");
		}
	}
	/**
	 * Desrealizeaza din fisierul "order.ser" toate datele si le pune intr-un HashMap<Order, ArrayList<MenuItem>> pe care ulterior le returneaza.
	 * @return HashMap cu toate comenzile realizate.
	 * @throws Exception
	 */
	public HashMap<Order, ArrayList<MenuItem>> getOrders() throws Exception {
		HashMap<Order, ArrayList<MenuItem>> toReturn = (HashMap<Order, ArrayList<MenuItem>>) Serializator.deserializeObj("order.ser");
		//System.out.println(toReturn.forEach(Order, ArrayList<MenuItem>) -> System.out.println(Order + ": " + ArrayList<MenuItem>));
		for(Order order : toReturn.keySet()) {
			System.out.println("Order #: " + toReturn.size());
			System.out.println("        -> Date: " + order.getOrderDate());
		}
		return toReturn;
	}
	
	/**
	 * Initializeaza meniul. Importa in fisierul "menu.ser" datele din .csv si de acolo le va deserializa le nevoie.
	 */
	public void initializeMenu() throws IOException {
		try {
			Path path = Paths.get("menu.ser");
			System.out.println(Files.size(path));
			if(Files.size(path) <= 10) {
				System.out.println("Se initializeaza...");
				//initializeAccounts();
			}
			else {
				return ;
			}
		} catch(Exception ex) {
			System.out.println("Nu exista fisierul... se creeaza");
			//initializeAccounts();
		}
		fileOutMenu = new FileOutputStream("menu.ser");
		fileInMenu = new FileInputStream("menu.ser");
		fileOutMenu.close();
		fileInMenu.close();
		Path path = Paths.get("menu.ser");
		System.out.println("Asta e size-ul: " + Files.size(path));
		
		List<String> arr = Files.lines(Paths.get("products.csv"))
				.skip(1)
				.collect(Collectors.toList());
		
		String[] properties = new String[7];
		List<MenuItem> menu = new ArrayList<MenuItem>();
		
		for(int i=0; i<arr.size(); i++) {
			properties = arr.get(i).split(",");
			String name = properties[0];
			float rating = Float.parseFloat(properties[1]);
			int calories = Integer.parseInt(properties[2]);
			int proteins = Integer.parseInt(properties[3]);
			int fat = Integer.parseInt(properties[4]);
			int sodium = Integer.parseInt(properties[5]);
			int price = Integer.parseInt(properties[6]);
			MenuItem menuItem = new BaseProduct(name, rating, calories, proteins, fat, sodium, price);
			menu.add(menuItem);
		}
		List<MenuItem> importDistinct = menu.stream()
				.filter(distinctByKey(p -> p.getTitle()))
				.collect(Collectors.toList());
		System.out.println(importDistinct.size());
		System.out.println(importDistinct.getClass());
		Serializator.serializeUnshared("menu.ser", importDistinct);
		assert invariant();
	}
	/**
	 * Returneaza toate produsele deserializate din "menu.ser"
	 * @return toate produsele stocate in fisier
	 * @throws Exception
	 */
	public ArrayList<MenuItem> getItemsFromMenu() throws Exception{
		ArrayList<MenuItem> toReturn = new ArrayList<MenuItem>();
		toReturn = (ArrayList<MenuItem>) Serializator.deserializeObj("menu.ser");
		return toReturn;
	}
	/**
	 * Adauga un produs si il serializeaza
	 * @param item -> produsul care urmeaza sa fie adaugat
	 * @throws Exception
	 */
	public void addProduct(MenuItem item) throws Exception {
		assert item != null;
		List<MenuItem> products = getItemsFromMenu();
		System.out.println("Add product des: " + products.size());
		products.add(item);
		assert invariant();
		JOptionPane.showMessageDialog(null, "Product: " + item.getTitle() + " successfully added!");
		Serializator.serializeUnshared("menu.ser", products);
	}
	/**
	 * Sterge un produs din structura(ArrayList) si serializeaza din nou structura
	 * @param name -> numele produsului care urmeaza sa fie sters
	 * @throws Exception
	 */
	public void deleteProduct(String name) throws Exception {
		List<MenuItem> products = getItemsFromMenu();
		assert !products.isEmpty();
		for(MenuItem item : products) {
			if(item.getTitle().equals(name)) {
				System.out.println("Vom sterge produsul!!!");
				products.remove(item);
				Serializator.serializeUnshared("menu.ser", products);
				JOptionPane.showMessageDialog(null, "Product: " + name + " successfully deleted!");
				return ;
			}
		}
		assert (products.size() >= 0);
		assert invariant();
		JOptionPane.showMessageDialog(null, "Product: " + name + " does not exist!");
	}
	
	public void editProduct(String name, float rating, int price) throws Exception {
		assert (!name.equals("") && rating > 0 && rating < 5);
		assert invariant();
		List<MenuItem> products = getItemsFromMenu();
		System.out.println("Edit product des: " + products.size());
		for(MenuItem item : products) {
			if(item.getTitle().equals(name)) {
				System.out.println("Am gasit produsul!!!!");
				item.setRating(rating);
				item.setPrice(price);
				Serializator.serializeUnshared("menu.ser", products);
				JOptionPane.showMessageDialog(null, "Successfuly updated product: " + name);
				return ;
			}
		}
		JOptionPane.showMessageDialog(null, "Could not find product named: " + name);
	}
	
	public void createComposedProduct(String name1, String name2) throws Exception {
		assert (!name1.equals("") && !name2.equals(""));
		List<MenuItem> products = getItemsFromMenu();
		System.out.println("Compose product: " + products.size());
		
		MenuItem product1 = new BaseProduct("", 0, 0, 0, 0, 0, 0);
		MenuItem product2 = new BaseProduct("", 0, 0, 0, 0, 0, 0);
		
		for(MenuItem m : products) {
			if(m.getTitle().equals(name1)) {
				product1 = m;
			}
			if(m.getTitle().equals(name2)) {
				product2 = m;
			}
		}
		
		CompositeProduct cmpProduct = new CompositeProduct();
		if(product1.getTitle() != "" && product2.getTitle() != "") {
			cmpProduct.addItem(product1);
			cmpProduct.addItem(product2);
			
			cmpProduct.setTitle();
			cmpProduct.computePrice();
			
			products.add(cmpProduct);
			System.out.println("Compose product 2: " + products.size());
			Serializator.serializeUnshared("menu.ser", products);
			JOptionPane.showMessageDialog(null, "<html><font color = #ff0000>Composed procut</font> added successfuly!</html>");
		}
		else {
			JOptionPane.showMessageDialog(null, "<html>Could not create composed prodcut!</html>");
		}
		assert invariant();
		
	}
	
	public ArrayList<MenuItem> searchProductByName(String name) throws Exception{
		assert !name.equals("");
		assert invariant();
		ArrayList<MenuItem> menuItems = getItemsFromMenu();
		List<MenuItem> toReturn = menuItems.stream()
				.filter(p -> p.getTitle().contains(name))
				.collect(Collectors.toList());
		System.out.println("Keyword (" + name + "): " + toReturn.size());
		return (ArrayList<MenuItem>) toReturn;
	}
	
	public ArrayList<MenuItem> searchProductByInterval(int min, int max, int mode) throws Exception{
		assert ((mode > 0 && mode < 7) ? true : false);
		assert invariant();
		ArrayList<MenuItem> menuItems = getItemsFromMenu();
		List<MenuItem> toReturn;
		if(mode == 1) {
			toReturn = menuItems.stream()
					.filter(p -> p.getRating() >= min && p.getRating() <= max)
					.collect(Collectors.toList());
		} else if(mode == 2) {
			toReturn = menuItems.stream()
					.filter(p -> p.getCalories() >= min && p.getCalories() <= max)
					.collect(Collectors.toList());
		} else if(mode == 3) {
			toReturn = menuItems.stream()
					.filter(p -> p.getProtein() >= min && p.getProtein() <= max)
					.collect(Collectors.toList());
		} else if(mode == 4) {
			toReturn = menuItems.stream()
					.filter(p -> p.getFat() >= min && p.getFat() <= max)
					.collect(Collectors.toList());
		} else if(mode == 5) {
			toReturn = menuItems.stream()
					.filter(p -> p.getSodium() >= min && p.getSodium() <= max)
					.collect(Collectors.toList());
		} else if(mode == 6) {
			toReturn = menuItems.stream()
					.filter(p -> p.getPrice() >= min && p.getPrice() <= max)
					.collect(Collectors.toList());
		} else {
			return null;
		}
		return (ArrayList<MenuItem>) toReturn;
	}
	
	public int frequencyOfMenuItem(MenuItem item) throws Exception {
		int toReturn = 0;
		List<MenuItem> menu = getItemsFromMenu();
		for(MenuItem m : menu) {
			if(m.getTitle().equals(item.getTitle())) {
				toReturn++;
			}
		}
		System.out.println(toReturn);
		return toReturn;
	}
	public ArrayList<MenuItem> orderedItems(Map<Order, ArrayList<MenuItem>> map){
		List<MenuItem> toReturn = new ArrayList<MenuItem>();
		map.forEach((k, v) -> toReturn.addAll(v));
		return (ArrayList<MenuItem>) toReturn;
	}
	public List<Order> orderedUsers(Map<Order, ArrayList<MenuItem>> map) {
		List<Order> toReturn = new ArrayList<Order>();
		map.forEach((k, v) -> toReturn.add(k));
		return toReturn;
	}
	
	public int countFrequencyInList(MenuItem item, List<MenuItem> myOrders) {
		int count = 0;
		for(MenuItem m : myOrders) {
			if(m.getTitle().equals(item.getTitle())) {
				count++;
			}
		}
		return count;
	}
	public int countUsersFrequency(Order order, List<Order> myOrders) {
		int count = 0;
		for(Order o : myOrders) {
			if(o.getClientID().equals(order.getClientID())) {
				count++;
			}
		}
		return count;
	}
	public int sumOfOrder(ArrayList<MenuItem> menu) {
		int sum = 0;
		for(MenuItem m : menu) {
			sum = sum + m.computePrice();
		}
		return sum;
	}
	
	public void generateReport1(int startHour, int endHour, JTextArea txtArea) throws Exception {
		assert (startHour >=0 && endHour < 24);
		assert invariant();
		hashMap = getOrders();
		Map<Order, ArrayList<MenuItem>> returnMap = hashMap.entrySet().stream()
				.filter(p -> p.getKey().getOrderDate().getHours() >= startHour && p.getKey().getOrderDate().getHours() <= endHour)
				.collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
		txtArea.setText("");
		returnMap.forEach((k, v) -> txtArea.append("(" + k.getOrderID() + ", " + k.getClientID() + ", " + k.getOrderDate() + "); Products: " + v.get(0).getTitle() + ", " + v.get(1).getTitle() + ", " + v.get(2).getTitle() + "\n"));
	}	
	public void generateReport2(int amount, JTextArea txtArea) throws Exception {
		assert invariant();
		List<MenuItem> myOrders = orderedItems(getOrders());
		List<MenuItem> toReturn = myOrders.stream()
				.filter(p -> countFrequencyInList(p, myOrders) > amount)
				.filter(distinctByKey(p -> p.getTitle()))
				.collect(Collectors.toList());
				
		txtArea.setText("");
		for(int i=0; i< toReturn.size(); i++) {
			txtArea.append("Product #" + i + ": " + toReturn.get(i).getTitle() + "\n");
		}
	}
	public void generateReport3(int noTimes, int valueOfOrder, JTextArea txtArea) throws Exception {
		assert invariant();
		hashMap = getOrders();
		List<Order> myUsers = orderedUsers(getOrders());
		Map<Order, ArrayList<MenuItem>> returnMap = hashMap.entrySet().stream()
				.filter(p -> countUsersFrequency(p.getKey(), myUsers) > noTimes)
				.filter(p -> sumOfOrder(p.getValue()) > valueOfOrder)
				.collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
		txtArea.setText("");
		returnMap.forEach((k, v) -> txtArea.append("Client: " + k.getClientID() + ". Value of order: " + sumOfOrder(v) + "\n"));
	}
	public void generateReport4(int day, JTextArea txtArea) throws Exception {
		assert(day > 0 && day <= 31);
		assert invariant();
		hashMap = getOrders();
		List<MenuItem> myOrders = orderedItems(getOrders());
		Map<Order, ArrayList<MenuItem>> returnMap = hashMap.entrySet().stream()
				.filter(p -> p.getKey().getOrderDate().getDay() == day)
				.collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
		txtArea.setText("");
		returnMap.forEach((k, v) -> txtArea.append("New order. " + "Product: " + v.get(0).getTitle() + ". Product: " + v.get(1).getTitle() + ". Product: " + v.get(2).getTitle() + "\n"));
	}
	
	public static <T> Predicate<T> distinctByKey(Function <? super T, ?> keyExtractor){
		
		Map<Object, Boolean> seen = new ConcurrentHashMap<>();
		return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}
	
}
