package businessLayer;

import java.util.*;

public class CompositeProduct extends MenuItem {
	private List<MenuItem> menuItemList = new ArrayList<MenuItem>();
	private String title;
	private float rating;
	private int calories;
	private int protein;
	private int fat;
	private int sodium;
	private int price;
	
	public CompositeProduct() {
		title = "";
		rating = 0f;
		calories = 0;
		protein = 0;
		fat = 0;
		sodium = 0;
		price = 0;
	}
	
	public int computePrice() {
		for(MenuItem item : menuItemList) {
			price = price + item.computePrice();
			rating = rating + item.getRating();
			calories = calories + item.getCalories();
			protein = protein + item.getProtein();
			fat = fat + item.getFat();
			sodium = sodium + item.getSodium();
		}
		rating = rating / menuItemList.size();
		return price;
	}
	
	public void setTitle() {
		for(MenuItem item : menuItemList) {
			if(menuItemList.indexOf(item) == 0) {
				title = item.getTitle();
			}
			else {
				title = title + " & " + item.getTitle();
			}
		}
	}
	
	public void addItem(MenuItem m) {
		menuItemList.add(m);
	}
	
	public String getTitle() {
		return title;
	}
	public float getRating() {
		return rating;
	}
	public int getCalories() {
		return calories;
	}
	public int getProtein() {
		return protein;
	}
	public int getFat() {
		return fat;
	}
	public int getSodium() {
		return sodium;
	}
	public int getPrice() {
		return price;
	}

	@Override
	public void setRating(float newRating) {
		rating = newRating;
		
	}

	@Override
	public void setPrice(int newPrice) {
		price = newPrice;
		
	}
	
}
