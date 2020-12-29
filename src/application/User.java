package application;

public class User {
	
	private String username;
	private String password;
	
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
