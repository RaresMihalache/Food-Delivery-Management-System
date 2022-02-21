package businessLayer;

import java.util.ArrayList;
import java.util.List;

import presentationLayer.EmployeeGUI;

public class Observable {
	private Order order;
	private List<EmployeeGUI> empList = new ArrayList<EmployeeGUI>();
	
	public Observable() {
		empList.add(new EmployeeGUI(700, 1400));
	}
	
	public void notifyObservers() {
		for(int i=0; i <empList.size(); i++) {
			empList.get(i).update(this, order);
		}
	}
	public void setOrder(Order order) {
		this.order = order;
	}
}
