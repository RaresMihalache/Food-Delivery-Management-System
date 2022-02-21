package presentationLayer;

import businessLayer.Observable;
import businessLayer.Order;

public interface Observer {
	public void update(Observable obs, Order order);
}
