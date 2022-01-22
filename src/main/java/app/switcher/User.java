package app.switcher;

public class User {
	
	private String username;
	private String password;
	private int number;
	
	public User(String usr, String psw) {
		this.username = usr;
		this.password = psw;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
}
