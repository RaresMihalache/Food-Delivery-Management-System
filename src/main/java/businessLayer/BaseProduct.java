package businessLayer;

public class BaseProduct extends MenuItem {
	private String title;
	private float rating;
	private int calories;
	private int protein;
	private int fat;
	private int sodium;
	private int price;
	
	private static final long serialVersionUID = 1136724446299372216L;
	
	public BaseProduct(String newTitle, float newRating, int newCalories, int newProtein, int newFat, int newSodium, int newPrice){
		title = newTitle;
		rating = newRating;
		calories = newCalories;
		protein = newProtein;
		fat = newFat;
		sodium = newSodium;
		price = newPrice;
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
	public int computePrice() {
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
