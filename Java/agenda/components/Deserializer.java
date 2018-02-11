package components;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Deserializer {
	
	private String path;
	private ArrayList<FriendContact> loadedContacts;
	public Deserializer(String path){
		
		this.path = path;
		loadedContacts = new ArrayList<FriendContact>();
	}

	
	public void loadFriendAgenda(){
		
		try {
			ObjectInputStream fileReader = new ObjectInputStream(new FileInputStream(path));
			loadedContacts = (ArrayList<FriendContact>) fileReader.readObject();
			fileReader.close();
			Agenda.fContacts = loadedContacts;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
