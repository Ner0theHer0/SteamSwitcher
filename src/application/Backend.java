package application;


import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Scanner;

public class Backend {
	
	private int numUsers;
	HashMap<String, String> map = new HashMap<String, String>();
	private String path = "src/application/users.txt";
	
	public Backend() {
		
		readFromFile();
		
		System.out.println(numUsers);
		
		for (HashMap.Entry<String, String> entry : map.entrySet()) {
		    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
		}
		
	}
	
	public boolean addUser(String user, String pass) {
		
		if (map.containsKey(user)) {
			System.out.println("User already exists!");
			return false;
		}
		
		if (user.equals("") || pass.equals("")) {
			System.out.println("Cannot add a blank username or password");
			return false;
		}
		
		else {
			user.replaceAll("\\s+","");
			
			try {
				
				BufferedWriter out = new BufferedWriter(new FileWriter(path, true)); 
		        out.write(user + " " + pass + "\n"); 
		        out.close();
		        
			    System.out.println("Successfully created new user.");
			    
			} catch (Exception e) {
				System.out.println("Couldn't create new user: " + e);
				return false;
			}
		}
		
		return true;
		
	}
	
	public void removeUser(String user) {
		
		if (!map.containsKey(user)) {
			System.out.println("This user does not exist");
		}
		
		else {
			
			
			map.remove(user);
			
			try {
				FileWriter writer = new FileWriter(path);
				
			    writer.write("");
			    writer.close();
			    
			    for (String key : map.keySet()) {
			    	BufferedWriter out = new BufferedWriter(new FileWriter(path, true)); 
			        out.write(key + " " + map.get(key) + "\n"); 
			        out.close();
				}
			    
			    
			} catch (Exception e) {
				System.out.println("Something went wrong");
			}
		}
	}
	
	public void readFromFile() {
		try {
			Scanner in = new Scanner(new FileReader(path));
			numUsers = 0;
			
			
			while (in.hasNext()) {
				
				String[] thisLine = in.nextLine().split("\\s+");
				map.put(thisLine[0], thisLine[1]);
				numUsers++;
					
			}
			
			
			
		} catch (Exception e) {
			System.out.println("something went wrong: " + e);
		}
	}
	
}