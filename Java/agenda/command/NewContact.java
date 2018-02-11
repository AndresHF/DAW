package command;

import java.awt.Graphics;

import components.Agenda;
import components.NewContactBuilder;

public class NewContact extends ButtonPattern{

	private NewContactBuilder builder;
	
	public NewContact(String path,String path2, String title, int posX, int posY,AgendaDevice agendaDevice) {
		super(path, path2, title, posX, posY,agendaDevice);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {

		agendaDevice.addContact();
		
	}
	
	public void paintButton(Graphics g){
		super.paintButton(g);
		if(this.isActivated()){
			if(Agenda.friends){
				builder.paint(g);
			}
			
		}
	}

	public NewContactBuilder getBuilder() {
		return builder;
	}

	public void setBuilder(NewContactBuilder builder) {
		this.builder = builder;
	}

	
}
