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
	
	public String addUser(String user, String pass) {
		
		if (map.containsKey(user)) {
			return ("User already exists!");
			
		}
		
		if (user.equals("") || pass.equals("")) {
			return ("Cannot add a blank username or password");
		}
		
		else {
			user.replaceAll("\\s+","");
			
			try {
				
				BufferedWriter out = new BufferedWriter(new FileWriter(path, true)); 
		        out.write(user + " " + pass + "\n"); 
		        out.close();
			    
			} catch (Exception e) {
				return ("Couldn't create new user: " + e);
			}
		}
		
		return ("Successfully created new user.");
		
	}
	
	public String removeUser(String user) {
		
		if (!map.containsKey(user)) {
			return "This user does not exist";
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
				return ("Something went wrong: " + e);
			}
		}
		
		return ("Successfully removed account");
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