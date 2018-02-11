package components;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Serializer {
	
	private ArrayList<FriendContact> fContacts;

	
	
	public Serializer(ArrayList<FriendContact> fContacts){
		
		this.fContacts = fContacts;
		
	}
	
	
	public void saveAgenda(){
		
		try {
			ObjectOutputStream fileWriter = new ObjectOutputStream(new FileOutputStream("save/miAgenda.xml"));
			fileWriter.writeObject(fContacts);
			fileWriter.close();
			System.out.println("Agenda saved");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
