package businessLayer;

import java.io.Serializable;

public class Account implements Serializable {
	private String username;
	private String password;
	private String type;
	private static final long serialVersionUID = -7750155540025708055L;
	
	public Account() {
		
	}
	public Account(String username, String password, String type) {
		this.username = username;
		this.password = password;
		this.type = type;
	}
	public Account(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getType() {
		return type;
	}
	
	public void setType(String newType) {
		type = newType;
	}
	
}
