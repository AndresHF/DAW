package command;

import java.awt.Graphics;

import components.Agenda;
import components.ContactFinder;
import components.NewContactBuilder;

public class ModifyContact extends ButtonPattern{

	private ContactFinder contactFinder;
	private NewContactBuilder builder;
	
	public ModifyContact(String path, String path2, String title, int posX, int posY,AgendaDevice agendaDevice) {
		super(path, path2, title, posX, posY,agendaDevice);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		agendaDevice.modifyContact();
		
	}

	public void paintButton(Graphics g){
		super.paintButton(g);
		if(this.isActivated()){
			if(Agenda.friends){
				if(builder == null){
					if(ContactFinder.ready) contactFinder.paint(g);
					else g.drawString("Cargando agenda...", 250, 300);
				}else{
					builder.paint(g);
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

	public NewContactBuilder getBuilder() {
		return builder;
	}

	public void setBuilder(NewContactBuilder builder) {
		this.builder = builder;
	}
}
