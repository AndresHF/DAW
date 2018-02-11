package controller;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JOptionPane;

import command.FindContact;
import command.ModifyContact;
import command.NewContact;
import command.RemoveContact;
import components.Agenda;
import components.ContactFinder;
import components.ContactInfoScreen;
import components.FriendContact;
import components.NewContactBuilder;

public class ContactControler extends Thread implements MouseListener, MouseMotionListener{

	private NewContact newContact;
	private FindContact findContact;
	private ModifyContact modifyContact;
	private RemoveContact removeContact;
	private boolean threadSwitch = true;
	private ThreadSpin spin;
	
	public ContactControler(NewContact newContact, FindContact findContact, ModifyContact modifyContact, RemoveContact removeContact){
		this.newContact = newContact;
		this.findContact = findContact;
		this.modifyContact = modifyContact;
		this.removeContact = removeContact;
	}

	
	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		Point p = new Point(e.getX(), e.getY());
		
		if(Agenda.friends && newContact.isActivated()){
			
			newContact.getBuilder().setHoverData(p);
			
		}else if(Agenda.friends && findContact.isActivated()){
			
			findContact.getContactFinder().setHoverData(p);
			spinProtocol(findContact.getContactFinder());
			
		}else if(Agenda.friends && modifyContact.isActivated()){
			
			if(modifyContact.getBuilder() != null){
				
				modifyContact.getBuilder().setHoverData(p);
			}else{
				modifyContact.getContactFinder().setHoverData(p);
				spinProtocol(modifyContact.getContactFinder());
			}
		}else if(Agenda.friends && removeContact.isActivated()){
			removeContact.getContactFinder().setHoverData(p);
			spinProtocol(removeContact.getContactFinder());
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {

		if(Agenda.friends && findContact.isActivated()){
			ContactFinder f = findContact.getContactFinder();
			quickFindProtocol(f);
			screenInfoProtocol(f);

		}else if(Agenda.friends && modifyContact.isActivated()){
			if(modifyContact.getBuilder() == null){
				ContactFinder f = modifyContact.getContactFinder();
				quickFindProtocol(f);
				modifyProtocol(f);
			}
			
		}else if(Agenda.friends && removeContact.isActivated()){
			ContactFinder f = removeContact.getContactFinder();
			quickFindProtocol(f);
			removeProtocol(f, removeContact);
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(Agenda.friends && newContact.isActivated()){
			newContact.getBuilder().launchDataSet();
		}
		if(Agenda.friends && modifyContact.getBuilder() != null){
			modifyContact.getBuilder().launchDataSet();
		}

	}
	
	private void spinProtocol(ContactFinder finder){
		
		if(finder.getHitboxes2().size() > 0){
			if(threadSwitch){
				for(boolean b: finder.getHoverImg()){
					if(b){
						threadSwitch = false;
						spin = new ThreadSpin(finder);
						spin.start();
					}
				}
			}else{
				threadSwitch = !spin.isLoop();
			}
		}

	}
	private void modifyProtocol(ContactFinder finder){
		int min = finder.getMinSpin();
		int max = finder.getMaxSpin();
		for(int i = min; i < max; i++){
			if(finder.getHoverData()[i]){
				FriendContact friend = Agenda.fContacts.get(i);
				String name = friend.getName();
				String surnames = friend.getSurnames();
				String phoneNumber = friend.getPhoneNumber();
				String movileNumber = friend.getMovileNumber();
				String address = friend.getAddress();
				String email = friend.getEmail();
				modifyContact.setBuilder(new NewContactBuilder(name,surnames,phoneNumber,movileNumber,address,email,i));
			}
		}
	}
	
	private void screenInfoProtocol(ContactFinder finder){
		int min = finder.getMinSpin();
		int max = finder.getMaxSpin();
		for(int i = min; i < max; i++){
			if(finder.getHoverData()[i]){
				finder.setContactInfo(new ContactInfoScreen(Agenda.fContacts.get(i)));
			}
		}
	}
	private void quickFindProtocol(ContactFinder finder){
		if(finder.isFinderHover()){
			finder.quickFind();
		}
	}
	private void removeProtocol(ContactFinder finder,RemoveContact r){
		int min = finder.getMinSpin();
		int max = finder.getMaxSpin();
		int answer = -1;
		for(int i = min; i < max; i++){
			if(finder.getHoverData()[i]){
				String nameSurname = Agenda.fContacts.get(i).getName() + " " + Agenda.fContacts.get(i).getSurnames();
				answer = JOptionPane.showConfirmDialog(null, "¿Deseas eliminar a " + nameSurname+"?", "EliminarContacto", 2);
				if(answer == 0){
					r.execute(i);
				}else if(answer == 2){
					JOptionPane.showMessageDialog(null, "Operación cancelada", "Eliminar contacto", 0);
				}
				break;
			}
		}

	}

}
