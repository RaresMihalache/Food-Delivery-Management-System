package businessLayer;

import java.io.Serializable;

public abstract class MenuItem implements Serializable {

	private static final long serialVersionUID = -5962359664965286279L;
	
	public abstract String getTitle();
	public abstract float getRating();
	public abstract int getCalories();
	public abstract int getProtein();
	public abstract int getFat();
	public abstract int getSodium();
	public abstract int getPrice();
	
	public abstract void setRating(float newRating);
	public abstract void setPrice(int newPrice);
	
	public abstract int computePrice();
	
}
