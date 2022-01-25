package app.switcher;


import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Scanner;

public class Backend {

	private Preferences p;
	
	private int numUsers;
	HashMap<String, User> map = new HashMap<String, User>();
	private String uPath = "src/main/resources/app/switcher/users.txt";
	private String pPath = "src/main/resources/app/switcher/pwd.txt";

	private String key;
	
	public Backend() {

		//readFromFile();
		
	}

	public void setPreferences(Preferences p) {
		this.p = p;
	}

	public void outString() {
		System.out.println(numUsers);

		for (HashMap.Entry<String, User> entry : map.entrySet()) {
			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue().getPassword());
		}
	}
	
	public int getNumUsers() {
		return numUsers;
	}

	public void setKey(String k) {
		this.key = k;
		System.out.println(key);
	}

	public boolean checkKey(String k) {
		Encryptor e = new Encryptor();
		if (e.checkHash(k)) {
			return true;
		}
		return false;
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

				String outPass;
				if (p.encryptEnabled) {
					Encryptor e = new Encryptor();
					outPass = e.encrypt(pass, key);
				} else {
					outPass = pass;
				}


				BufferedWriter uOut = new BufferedWriter(new FileWriter(uPath, true));
				BufferedWriter pOut = new BufferedWriter(new FileWriter(pPath, true));
				uOut.write(user + "\n");
				pOut.write(outPass + "\n");
				uOut.close();
				pOut.close();
				map.put(user, new User(user, pass));
				numUsers++;

			    
			} catch (Exception e) {
				return ("Couldn't create new user: " + e);
			}
		}
		
		return ("Successfully created new user.");
		
	}

	public String editUser(String user, String newName, String pass) {

		if (!map.containsKey(user)) {
			return ("User does not exist!");
		}

		if (map.containsKey(newName) && !user.equals(newName)) {
			return ("Another account is using this username");
		}

		if (user.equals("")) {
			return ("Cannot add a blank username or password");
		}

		else {

			if (pass.equals("")) {

				pass = map.get(user).getPassword();

			}

			newName.replaceAll("\\s+","");

			try {

				removeUser(user);

				String outPass;
				if (p.encryptEnabled) {
					Encryptor e = new Encryptor();
					outPass = e.encrypt(pass, key);
				} else {
					outPass = pass;
				}

				BufferedWriter uOut = new BufferedWriter(new FileWriter(uPath, true));
				BufferedWriter pOut = new BufferedWriter(new FileWriter(pPath, true));
				uOut.write(newName + "\n");
				pOut.write(outPass + "\n");
				uOut.close();
				pOut.close();

				map.put(newName, new User(newName, pass));

			} catch (Exception e) {
				return ("Couldn't edit user: " + e);
			}
		}

		return ("Successfully updated user " + newName);

	}
	
	public String removeUser(String user) {
		
		if (!map.containsKey(user)) {
			return "This user does not exist";
		}
		
		else {
			
			map.remove(user);
			
			try {
				FileWriter uWriter = new FileWriter(uPath);
				FileWriter pWriter = new FileWriter(pPath);
				
			    uWriter.write("");
				pWriter.write("");
			    uWriter.close();
				pWriter.close();
			    
			    for (String u : map.keySet()) {

					BufferedWriter uOut = new BufferedWriter(new FileWriter(uPath, true));
					BufferedWriter pOut = new BufferedWriter(new FileWriter(pPath, true));
					uOut.write(u + "\n");
					String pass;
					if (p.encryptEnabled) {
						Encryptor e = new Encryptor();
						pass = e.encrypt(map.get(u).getPassword(), key);
					} else {
						pass = map.get(u).getPassword();
					}
					pOut.write(pass + "\n");
					uOut.close();
					pOut.close();

				}
			    
			    
			} catch (Exception e) {
				return ("Something went wrong: " + e);
			}
		}
		
		return ("Successfully removed account");
	}
	
	public void readFromFile() {
		try {

			Scanner uin = new Scanner(new FileReader(uPath));
			Scanner pin = new Scanner(new FileReader(pPath));
			numUsers = 0;
			
			while (uin.hasNext()) {

				String thisLineU = uin.nextLine();
				String thisLineP = pin.nextLine();


				if (p.encryptEnabled) {
					Encryptor d = new Encryptor();
					thisLineP = d.decrypt(thisLineP, key);
				}

				map.put(thisLineU, new User(thisLineU, thisLineP));
				numUsers++;

			}
			
		} catch (Exception e) {
			System.out.println("Something went wrong: " + e);
		}
		outString();
	}

	public void decryptFile() {
		try {
			FileWriter uWriter = new FileWriter(uPath);
			FileWriter pWriter = new FileWriter(pPath);

			uWriter.write("");
			pWriter.write("");
			uWriter.close();
			pWriter.close();

			for (String u : map.keySet()) {

				BufferedWriter uOut = new BufferedWriter(new FileWriter(uPath, true));
				BufferedWriter pOut = new BufferedWriter(new FileWriter(pPath, true));
				uOut.write(u + "\n");
				pOut.write(map.get(u).getPassword() + "\n");
				uOut.close();
				pOut.close();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void encryptFile() {
		try {
			FileWriter uWriter = new FileWriter(uPath);
			FileWriter pWriter = new FileWriter(pPath);

			uWriter.write("");
			pWriter.write("");
			uWriter.close();
			pWriter.close();

			Encryptor e = new Encryptor();
			e.createHash(key);

			for (String u : map.keySet()) {

				String outPass = e.encrypt(map.get(u).getPassword(), key);


				BufferedWriter uOut = new BufferedWriter(new FileWriter(uPath, true));
				BufferedWriter pOut = new BufferedWriter(new FileWriter(pPath, true));
				uOut.write(u + "\n");
				pOut.write(outPass + "\n");
				uOut.close();
				pOut.close();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
}