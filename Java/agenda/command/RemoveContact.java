package command;

import java.awt.Color;
import java.awt.Graphics;

import components.Agenda;
import components.ContactFinder;

public class RemoveContact extends ButtonPattern{

	private ContactFinder contactFinder;
	private boolean readyToPaint = true;
	public RemoveContact(String path, String path2, String title, int posX, int posY,AgendaDevice agendaDevice) {
		super(path, path2, title, posX, posY,agendaDevice);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		
		
	}
	public void execute(int pos){
		readyToPaint = false;
		agendaDevice.removeContact(pos);

	}

	public void paintButton(Graphics g){
		super.paintButton(g);
		if(this.isActivated()){
			if(Agenda.friends){
				if(readyToPaint){
					if(ContactFinder.ready) contactFinder.paint(g);
					else g.drawString("Cargando agenda...", 250, 300);
				}else{
					g.setColor(Color.RED);
					g.drawString("Realizando operación", 250, 300);
				}
				
			}
		}
	}
	public ContactFinder getContactFinder() {
		return contactFinder;
	}

	public void setContactFinder(ContactFinder contactFinder) {
		this.contactFinder = contactFinder;
	}

	public boolean isReadyToPaint() {
		return readyToPaint;
	}

	public void setReadyToPaint(boolean readyToPaint) {
		this.readyToPaint = readyToPaint;
	}

}
