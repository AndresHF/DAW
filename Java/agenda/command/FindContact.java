package command;

import java.awt.Graphics;

import components.Agenda;
import components.ContactFinder;

public class FindContact extends ButtonPattern{

	private ContactFinder contactFinder;
	
	public FindContact(String path, String path2, String title, int posX, int posY,AgendaDevice agendaDevice) {
		super(path, path2, title, posX, posY,agendaDevice);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
	public void paintButton(Graphics g){
		super.paintButton(g);
		if(this.isActivated()){
			if(Agenda.friends){
				if(ContactFinder.ready) contactFinder.paint(g);
				else g.drawString("Cargando agenda...", 250, 300);
			}
		}
	}
	public ContactFinder getContactFinder() {
		return contactFinder;
	}

	public void setContactFinder(ContactFinder contactFinder) {
		this.contactFinder = contactFinder;
	}

}
