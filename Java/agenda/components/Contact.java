package components;

import java.io.Serializable;

public class Contact implements Comparable<Contact>, Serializable{
	
	private String name;
	private String surnames;
	private String phoneNumber;
	private String movileNumber;
	private String address;
	private String email;
	
	public Contact(String name, String surnames, String phoneNumber, String movileNumber, String address,String email){
		this.name = name;
		this.surnames = surnames;
		this.phoneNumber = phoneNumber;
		this.movileNumber = movileNumber;
		this.address = address;
		this.email = email;
	}

	public int compareTo(Contact other) {
		String actualContact = this.name + " " + this.surnames;
		String otherContact = other.name + " " + other.surnames;
		if(actualContact.compareTo(otherContact) > 0) return 1;
		else if(actualContact.compareTo(otherContact) < 0) return -1;
		else return 0;
	}
	
	public String toString(){
		return "Nombre: " + this.name +
				"\nApellidos: " + this.surnames +
				"\nTeléfono fijo: " + this.phoneNumber +
				"\nTeléfono móvil: " + this.movileNumber+
				"\nDirección: " + this.address + 
				"\ne-mail: " + this.email;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurnames() {
		return surnames;
	}

	public void setSurnames(String surnames) {
		this.surnames = surnames;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getMovileNumber() {
		return movileNumber;
	}

	public void setMovileNumber(String movileNumber) {
		this.movileNumber = movileNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
