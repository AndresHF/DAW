package components;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import command.AgendaDevice;
import command.ApproveAction;
import command.ButtonPattern;
import command.CancelAction;
import command.ConfirmButton;
import command.FindContact;
import command.ModifyContact;
import command.NewContact;
import command.RemoveContact;
import command.SelectAgendaButton;
import command.SelectDoctor;
import command.SelectFriends;
import command.SelectWork;

public class Agenda {

	//OPTION BUTTONS
	private NewContact newContact;
	private FindContact findContact;
	private ModifyContact modifyContact;
	private RemoveContact removeContact;
	public static Map<Integer,ButtonPattern> buttons = new HashMap<Integer,ButtonPattern>();
	
	//COMMIT-ROLLBACK BUTTONS
	private ApproveAction approve;
	private CancelAction cancel;
	public static ArrayList<ConfirmButton> confirmButtons = new ArrayList<ConfirmButton>();
	
	//AGENDA TYPE SELECTION BUTTONS
	private SelectFriends selectFriends;
	private SelectWork selectWork;
	private SelectDoctor selectDoctor;
	
	public static Map<Integer, SelectAgendaButton> selectButtons = new HashMap<Integer,SelectAgendaButton>();
	
	private AgendaDevice agendaDevice;
	
	public static boolean friends,work,doctor;
	
	public static ArrayList<FriendContact> fContacts;
	
	public Agenda(){
		
		agendaDevice = new AgendaDevice();
		//OPTION BUTTONS CONFIG
		newContact = new NewContact("img/addContact.png", "img/addContact2.png", "Nuevo contacto", 20, 0,agendaDevice);
		findContact = new FindContact("img/findContact.png", "img/findContact2.png","Buscar contacto",20,150,agendaDevice);
		modifyContact = new ModifyContact("img/modifyContact.png","img/modifyContact2.png","Modificar contacto",20,300,agendaDevice);
		removeContact = new RemoveContact("img/removeContact.png","img/removeContact2.png","Eliminar contacto",20,450,agendaDevice);
		buttons.put(0, newContact);
		buttons.put(1, findContact);
		buttons.put(2, modifyContact);
		buttons.put(3, removeContact);
		
		//COMMIT-ROLLBACK BUTTONS CONFIG
		approve = new ApproveAction("img/approve.png","img/approve2.png"," ",550,700,agendaDevice);
		cancel = new CancelAction("img/cancel.png","img/cancel2.png"," ",650,700,agendaDevice);
		confirmButtons.add(approve);
		confirmButtons.add(cancel);
		
		//AGENDA TYPE SELECTION BUTTONS CONFIG
		selectFriends = new SelectFriends("img/family1.png","img/family.png","Familiares y amigos",140,160,agendaDevice);
		selectWork = new SelectWork("img/work1.png","img/work.png","Contactos de trabajo",350,200,agendaDevice);
		selectDoctor = new SelectDoctor("img/doctor1.png","img/doctor.png","Agenda médica",550,220,agendaDevice);
		selectButtons.put(0, selectFriends);
		selectButtons.put(1, selectWork);
		selectButtons.put(2, selectDoctor);
		
		friends = false;
		work = false;
		doctor = false;
	
		fContacts = new ArrayList<FriendContact>();
		loadFriendAgenda();
	}
	
	public void paintAgenda(Graphics g){
		
		if(!friends && !work && !doctor){
			Iterator<Integer> agendaIterator = Agenda.selectButtons.keySet().iterator();
			while(agendaIterator.hasNext()){
				int key = (int) agendaIterator.next();
				selectButtons.get(key).paintButton(g);
			}
		}else{
			Iterator<Integer> agendaIterator2 = Agenda.buttons.keySet().iterator();
			while(agendaIterator2.hasNext()){
				int key = (int) agendaIterator2.next();
				buttons.get(key).paintButton(g);
			}
			if(friends) selectFriends.paintActivatedButton(g);
			else if(work) selectWork.paintActivatedButton(g);
			else if(doctor) selectDoctor.paintActivatedButton(g);
			
		}

		for(ConfirmButton c : confirmButtons){
			c.paintButton(g);
		}
	}
	
	public boolean agendaSelected(){
		return doctor || work || friends;
	}
	
	public void setAgendaBack(){
		doctor = false;
		work = false;
		friends = false;
		Iterator<Integer> agendaIterator = Agenda.selectButtons.keySet().iterator();
		while(agendaIterator.hasNext()){
			int key = (int) agendaIterator.next();
			Agenda.selectButtons.get(key).setActivated(false);
			Agenda.selectButtons.get(key).resetPos();
		}
	}
	
	public boolean optionSelected(){
		Iterator<Integer> agendaIterator2 = Agenda.buttons.keySet().iterator();
		while(agendaIterator2.hasNext()){
			int key = (int) agendaIterator2.next();
			if(buttons.get(key).isActivated()) return true;
		}
		return false;
	}

	public boolean isShowingContactInfo(){
		FindContact f = (FindContact)buttons.get(1);
		return f.isActivated() && f.getContactFinder().getContactInfo() != null;
	}
	
	public boolean isModifingContact(){
		ModifyContact m = (ModifyContact)buttons.get(2);
		return m.getBuilder() != null;
	}
	
	private void loadFriendAgenda(){
		Deserializer d = new Deserializer("save/miAgenda.xml");
		d.loadFriendAgenda();
	}
	
}
