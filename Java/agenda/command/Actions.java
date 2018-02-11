package command;

public interface Actions {

	public void addContact();
	
	public void addFavourite();
	
	public void findContact();
	
	public void removeContact(int pos);
	
	public void modifyContact();
	
	public void cancel();
	
	public void approve();
	
	public void selectFriends();
	
	public void selectWork();
	
	public void selectDoctor();
}
