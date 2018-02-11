package command;

import java.util.Collections;
import java.util.Iterator;
import javax.swing.JOptionPane;
import components.Agenda;
import components.ContactFinder;
import components.FriendContact;
import components.NewContactBuilder;
import components.Serializer;
import view.MainCanvas;

public class AgendaDevice implements Actions{

	@Override
	public void addContact() {
		if(Agenda.friends){
			NewContact n = (NewContact)Agenda.buttons.get(0);
			String check = n.getBuilder().getData()[0];
			newFriendContactProtocol(n.getBuilder(), check, n.getBuilder().getPosition());
		}else if(Agenda.work){
			System.out.println("Test work");
		}else if(Agenda.doctor){
			System.out.println("Test doctor");
		}
		
	}

	@Override
	public void addFavourite() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void findContact() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeContact(int pos) {
		Agenda.fContacts.remove(pos);
		Collections.sort(Agenda.fContacts);
		Serializer s = new Serializer(Agenda.fContacts);
		s.saveAgenda();
		RemoveContact r = (RemoveContact)Agenda.buttons.get(3);
		r.setContactFinder(new ContactFinder());
		r.setReadyToPaint(true);
		JOptionPane.showMessageDialog(null, "¡Contacto eliminado con éxito!", "Eliminar contacto", 1);
	}

	@Override
	public void modifyContact() {
		if(Agenda.friends){
			ModifyContact f = (ModifyContact)Agenda.buttons.get(2);
			String check = f.getBuilder().getData()[0];
			newFriendContactProtocol(f.getBuilder(), check, f.getBuilder().getPosition());
			f.setBuilder(null);
		}else if(Agenda.work){
			System.out.println("Test work");
		}else if(Agenda.doctor){
			System.out.println("Test doctor");
		}
		
	}

	@Override
	public void cancel() {
		
		if(MainCanvas.agenda.agendaSelected() && MainCanvas.agenda.optionSelected()){
			if(MainCanvas.agenda.isShowingContactInfo()){
				FindContact f = (FindContact)Agenda.buttons.get(1);
				f.getContactFinder().setContactInfo(null);
			}else if(MainCanvas.agenda.isModifingContact()){
				ModifyContact m = (ModifyContact)Agenda.buttons.get(2);
				m.setBuilder(null);
			}else{
				MainCanvas.resetAll();
				resetAllOptionButtons();
				resetConfirmButton(0);
			}
			
		}else if(MainCanvas.agenda.agendaSelected() && !MainCanvas.agenda.optionSelected()){
			
			MainCanvas.agenda.setAgendaBack();
			resetConfirmButton(1);
		}
	}

	@Override
	public void approve() {
		Iterator<Integer> button = Agenda.buttons.keySet().iterator();
		while(button.hasNext()){
			int key = (int) button.next();
			if(Agenda.buttons.get(0).isActivated()) Agenda.buttons.get(key).execute();
			else if(Agenda.buttons.get(2).isActivated()){
				ModifyContact m = (ModifyContact) Agenda.buttons.get(2);
				if(m.getBuilder() != null){
					m.execute();
					break;
				}else{
					JOptionPane.showMessageDialog(null, "Selecciona un contacto primero", "Modificar contacto", 1);
					break;
				}
			}
		}
		
	}

	@Override
	public void selectFriends() {
		configureAgendaType(true, false, false);
		setAgendaButtonPosition();
	}

	@Override
	public void selectWork() {
		configureAgendaType(false, true, false);
		setAgendaButtonPosition();
	}

	@Override
	public void selectDoctor() {
		configureAgendaType(false, false, true);
		setAgendaButtonPosition();
	}
	
	private void configureAgendaType(boolean friends, boolean work, boolean doctor){
		Agenda.friends = friends;
		Agenda.work = work;
		Agenda.doctor = doctor;
	}

	private void setAgendaButtonPosition(){
		Iterator<Integer>agendaIterator = Agenda.selectButtons.keySet().iterator();
		while(agendaIterator.hasNext()){
			int key = (int) agendaIterator.next();
			Agenda.selectButtons.get(key).setHitBoxOutOfBounds();
			
		}
	}
	private void resetConfirmButton(int key){
		Agenda.confirmButtons.get(key).setActivated(false);
		Agenda.confirmButtons.get(key).setSecuenceFinished(false);
		Agenda.confirmButtons.get(key).resetPos();
	}
	private void resetAllOptionButtons(){
		Iterator<Integer> agendaIterator = Agenda.buttons.keySet().iterator();
		while(agendaIterator.hasNext()){
			int key = agendaIterator.next();
			Agenda.buttons.get(key).setActivated(false);
			Agenda.buttons.get(key).setSecuenceFinished(false);
			Agenda.buttons.get(key).resetPos();
		}
	}
	
	private void newFriendContactProtocol(NewContactBuilder builder, String check,int mode){

		if(!check.startsWith("Selecciona") && check.length() > 0){
			String[] depuratedData = new String[builder.getData().length];
			for(int i = 0; i < depuratedData.length; i++){
				String data = builder.getData()[i];
				if(data.startsWith("Selecciona")){
					depuratedData[i] = "--------";
				}else{
					depuratedData[i] = data;
				}
			}
			Agenda.fContacts.add(new FriendContact(depuratedData[0],
					depuratedData[1],
					depuratedData[2],
					depuratedData[3],
					depuratedData[4],
					depuratedData[5]));
			if(mode >= 0){
				Agenda.fContacts.remove(mode);
				JOptionPane.showMessageDialog(null, "¡Contacto modificado con éxito!");
			}else{
				JOptionPane.showMessageDialog(null, "¡Contacto creado con éxito!");
			}
			Collections.sort(Agenda.fContacts);
			Serializer s = new Serializer(Agenda.fContacts);
			s.saveAgenda();
			cancel();
		}else{
			JOptionPane.showMessageDialog(null, "El nombre del contacto es un campo obligatorio");
		}
	}
}
