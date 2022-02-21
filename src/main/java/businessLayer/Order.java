package businessLayer;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
	private int orderID;
	private String clientID; // = clientName
	private Date orderDate;
	private static final long serialVersionUID = -654151901355938116L;
	
	public Order() {
		
	}

	public Order(int orderID, String clientID, Date date) {
		this.orderID = orderID;
		this.clientID = clientID;
		this.orderDate = date;
	//	this.menuItems = new ArrayList();
	}
	
	
	public int getOrderID() {
		return this.orderID;
	}
	public String getClientID() {
		return this.clientID;
	}
	public Date getOrderDate() {
		return this.orderDate;
	}
	
	public void setOrderID(int newOrderID) {
		orderID = newOrderID;
	}
	public void setClientID(String clientName) {
		clientID = clientName;
	}
	public void setOrderDate(Date newOrderDate) {
		this.orderDate = newOrderDate;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clientID == null) ? 0 : clientID.hashCode());
		result = prime * result + ((orderDate == null) ? 0 : orderDate.hashCode());
		result = prime * result + orderID;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (clientID == null) {
			if (other.clientID != null)
				return false;
		} else if (!clientID.equals(other.clientID))
			return false;
		if (orderDate == null) {
			if (other.orderDate != null)
				return false;
		} else if (!orderDate.equals(other.orderDate))
			return false;
		if (orderID != other.orderID)
			return false;
		return true;
	}
}
