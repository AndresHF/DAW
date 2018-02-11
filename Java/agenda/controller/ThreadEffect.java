package controller;

import java.util.Iterator;

import command.ConfirmButton;
import command.FindContact;
import command.ModifyContact;
import components.Agenda;
import components.ContactFinder;
import components.NewContactBuilder;
import view.MainCanvas;
import command.NewContact;
import command.RemoveContact;

public class ThreadEffect extends Thread{
	
	//private boolean finishedEffect = false;
	private boolean loop = true;
	
	public ThreadEffect(){
		
	
	}
	
	public void run(){
		if(Agenda.buttons.get(0).isActivated()){
			NewContact n = (NewContact)Agenda.buttons.get(0);
			n.setBuilder(new NewContactBuilder());
		}else if(Agenda.buttons.get(1).isActivated()){
			FindContact f = (FindContact)Agenda.buttons.get(1);
			f.setContactFinder(new ContactFinder());
		}else if(Agenda.buttons.get(2).isActivated()){
			ModifyContact m = (ModifyContact) Agenda.buttons.get(2);
			m.setContactFinder(new ContactFinder());
		}else if(Agenda.buttons.get(3).isActivated()){
			RemoveContact r = (RemoveContact) Agenda.buttons.get(3);
			r.setContactFinder(new ContactFinder());
		}
		while(loop){
			if(MainCanvas.agenda.agendaSelected() && MainCanvas.agenda.optionSelected()){
				MainCanvas.optionActivated = true;
				optionSelectedProtocol();
			}else if(MainCanvas.agenda.agendaSelected() && !MainCanvas.agenda.optionSelected()){
				
				if(!Agenda.confirmButtons.get(1).isSecuenceFinished()) Agenda.confirmButtons.get(1).initVanish();
				else{
					loop = false; 
					System.out.println("ALL DONE BOY");
				}
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
 
		}

	}
	
	private boolean allDone(){
		int counter = 0;
		Iterator<Integer> agendaIterator = Agenda.buttons.keySet().iterator();
		while(agendaIterator.hasNext()){
			int key = (int) agendaIterator.next();
			if(!Agenda.buttons.get(key).isActivated() && Agenda.buttons.get(key).isSecuenceFinished()) counter ++;
			if(Agenda.buttons.get(key).isActivated() && Agenda.buttons.get(key).isSecuenceFinished()) counter ++;
		}
		return (counter == 4 || counter == 3) && MainCanvas.posX <= -10;
	}
	private void updateBackgroundPosition(){
		
		if(MainCanvas.posX > -10) MainCanvas.posX --;
	}
	
	private void optionSelectedProtocol(){
		updateBackgroundPosition();
		
		Iterator<Integer> agendaIterator = Agenda.buttons.keySet().iterator();
		while(agendaIterator.hasNext()){
			int key = (int) agendaIterator.next();
			if(!Agenda.buttons.get(key).isActivated() && !Agenda.buttons.get(key).isSecuenceFinished()) Agenda.buttons.get(key).initVanish();
			if(Agenda.buttons.get(key).isActivated() && !Agenda.buttons.get(key).isSecuenceFinished()) Agenda.buttons.get(key).setToMainPosition();
			if(Agenda.buttons.get(3).isActivated()) Agenda.buttons.get(3).setToMainPosition();
		}
		if(Agenda.friends && !Agenda.buttons.get(1).isActivated() && !Agenda.buttons.get(3).isActivated()){
			for(ConfirmButton c: Agenda.confirmButtons){
				if(!c.isSecuenceFinished()) c.initVanish();
			}
		}
		
		if(allDone()){
			loop = false;
			System.out.println("ALL DONE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		}
	}

}
