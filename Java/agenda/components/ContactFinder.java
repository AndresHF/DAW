package components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import view.MainCanvas;

public class ContactFinder {

	public static final int CONTACT_X = 100;
	public static final int CONTACT_Y = 250;
	public static final int Y_VISIBLE = 530;
	private final int BOX_HEIGHT = 20;
	private final int ARROWX = 550;
	private final int ARROWY = 180;
	private String finder = "-> Búsqueda rápida <-";
	private boolean[] hoverData;
	private Rectangle[] imgHitboxes = new Rectangle[2];
	private boolean[] hoverImg = {false, false};
	private Map<Integer, Rectangle> hitboxes2 = new HashMap<Integer,Rectangle>();
	private Rectangle finderHitbox;
	private boolean finderHover = false;
	
	private int minSpin = 0;
	private int maxSpin = 7;
	private boolean moreThan7 = false;
	
	private String savedSearch = "";
	public static boolean ready = false;
	private ContactInfoScreen contactInfo;

	public ContactFinder(){
		
		if(Agenda.friends){
			ready = false;
			int size = Agenda.fContacts.size();
			configHitboxes();
			hoverData = new boolean[size];
			finderHitbox = new Rectangle(ARROWX - 310, 100, finder.length() * 17, 30);
			//this.findContact = findContact;
			moreThan7 = hitboxes2.size() > 7;
			if(!moreThan7) maxSpin = hitboxes2.size();
		}
		
	}
	public void paint(Graphics g){
		
		g.drawImage(MainCanvas.screen, 0, 170, null);
		g.setColor(Color.ORANGE);
		g.setFont(MainCanvas.font2);
		if(finderHover) g.setColor(Color.YELLOW.brighter());
		g.drawString(finder, ARROWX - 310, 130);
		g.setFont(NewContactBuilder.font2);
		if(contactInfo != null){
			g.setColor(Color.GREEN);
			contactInfo.paint(g);
		}else{
			for(int i = minSpin; i < maxSpin; i++){
				
				if(!hoverData[i]) g.setColor(Color.GREEN);
				else g.setColor(Color.YELLOW);
				int x = (int)hitboxes2.get(i).getX();
				int y = (int)hitboxes2.get(i).getY() + 15;
				FriendContact f = Agenda.fContacts.get(i);
				String nameSurname = f.getName() + " " + f.getSurnames();
				if(nameSurname.length() > 53) nameSurname = nameSurname.substring(0, 50) + "...";
				if(y >= CONTACT_Y && y < Y_VISIBLE)
					g.drawString(nameSurname, x, y);

			}
			g.drawImage(MainCanvas.arrowUp1, (int)imgHitboxes[0].getX(), (int)imgHitboxes[0].getY(), null);
			g.drawImage(MainCanvas.arrowDown1, (int)imgHitboxes[1].getX(), (int)imgHitboxes[1].getY(), null);
			for(int i = 0; i < imgHitboxes.length; i++){
				if(hoverImg[i]) initBlink(i,g);
			}
		}
		
		
	}

	public void spinContacts(int spin){
		
		boolean bottomBoundsExceded = hitboxes2.get(hitboxes2.size() - 1).getY() > Y_VISIBLE - BOX_HEIGHT;
		boolean topBoundsExceded = hitboxes2.get(0).getY() < CONTACT_Y;
		if(moreThan7){
			bottomBoundsExceded = hitboxes2.get(6).getY() > Y_VISIBLE - BOX_HEIGHT;
			topBoundsExceded = hitboxes2.get(hitboxes2.size() - 7).getY() < CONTACT_Y;
			if(hitboxes2.get(minSpin).getY() <= CONTACT_Y && spin < 0){ //HIT2 x HIT1
				maxSpin++;
				minSpin++;
				if(maxSpin >= hitboxes2.size()){
					maxSpin = hitboxes2.size();
					minSpin = maxSpin - 7;
				}

			}else if(hitboxes2.get(maxSpin - 1).getY() >= Y_VISIBLE - BOX_HEIGHT && spin > 0){
				minSpin--;
				maxSpin--;
				if(minSpin <= 0){
					minSpin = 0;
					maxSpin = 7;
				}
			}

		}
		if((!bottomBoundsExceded && spin > 0) || (!topBoundsExceded && spin < 0)){
			if(spin > 0){
				hitboxes2.get(maxSpin - 1).setLocation(CONTACT_X,(int)hitboxes2.get(maxSpin - 1).getY()+ spin);
				for(int i = maxSpin - 1; i > minSpin; i--){
					follow(hitboxes2.get(i),hitboxes2.get(i - 1), -40);
				}
			}else if(spin < 0){
				hitboxes2.get(minSpin).setLocation(CONTACT_X,(int)hitboxes2.get(minSpin).getY()+ spin);
				for(int i = minSpin; i < maxSpin - 1; i++){
					follow(hitboxes2.get(i),hitboxes2.get(i + 1),40);
				}
			}
				
		}

	}

	private void follow(Rectangle head, Rectangle tail,int space){
		tail.setLocation(CONTACT_X, (int)head.getY() + space);
	}
	public void configHitboxes(){
		ready = false;
		for(int i = 0; i < Agenda.fContacts.size(); i++){
			int yPos = 0;
			if(i < 7){
				
				if(i == 0){
					yPos = CONTACT_Y;
				}else{
					yPos = (int)hitboxes2.get(i - 1).getY() + 40;
				}
			}else{
				yPos = Y_VISIBLE ;
			}
			FriendContact f = Agenda.fContacts.get(i);
			String nameSurname = f.getName() + " " + f.getSurnames();
			int width = nameSurname.length() * 7;
			if(width > 375) width = 375;
			hitboxes2.put(i, new Rectangle(CONTACT_X,yPos,width, BOX_HEIGHT));
		}
		for(int i = 0; i < imgHitboxes.length; i++){
			imgHitboxes[i] = new Rectangle(ARROWX, ARROWY * (i+1),MainCanvas.arrowUp1.getWidth(), MainCanvas.arrowUp1.getHeight());
		}
		ready = true;
	}
	
	private void initBlink(int mode,Graphics g){
		
		if(mode == 0){
			g.drawImage(MainCanvas.arrowUp2, (int)imgHitboxes[mode].getX(), (int)imgHitboxes[mode].getY(), null);
		}else{
			g.drawImage(MainCanvas.arrowDown2, (int)imgHitboxes[mode].getX(), (int)imgHitboxes[mode].getY(), null);
		}
	}
	
	public void setHoverData(Point p){
		
		for(int i = minSpin; i < maxSpin; i++){
			hoverData[i] = hitboxes2.get(i).contains(p)&& hitboxes2.get(i).getY() >= CONTACT_Y - 15 && hitboxes2.get(i).getY() <= Y_VISIBLE;
		}
		for(int i = 0; i < imgHitboxes.length; i++){
			hoverImg[i] = imgHitboxes[i].contains(p);
		}
		finderHover = finderHitbox.contains(p);
	}

	public void quickFind(){
		String search = (String)JOptionPane.showInputDialog(null, 
				"Escribe el nombre del contacto: ", 
				"Búsqueda rápida", 
				3, MainCanvas.popUp, null, savedSearch);
		if(search != null){
			savedSearch = search;
			int coincidence = checkCoincidence(search, 0);
			if(coincidence >= 0){
				findedContactToScreen(coincidence);
			}else{
				coincidence = checkCoincidence(search, 1);
				if(coincidence >= 0){
					findedContactToScreen(coincidence);
				}else{
					coincidence = checkCoincidence(search, 2);
					if(coincidence >= 0){
						findedContactToScreen(coincidence);
					}else{
						JOptionPane.showMessageDialog(null, "No hay coincidencias");
					}
					
				}
			}
		}
		
	}
	
	private int checkCoincidence(String match, int mode){
		
		for(int i = 0; i < Agenda.fContacts.size(); i++){
			String toCheck = "";
			if(mode == 0) toCheck = Agenda.fContacts.get(i).getName();
			else if(mode == 1) toCheck = Agenda.fContacts.get(i).getSurnames();
			else toCheck = Agenda.fContacts.get(i).getName() + " " + Agenda.fContacts.get(i).getSurnames();
			if(mode == 0 || mode == 1){
				if(toCheck.toLowerCase().startsWith(match.toLowerCase())){
					return i;
				}
			}else{
				if(toCheck.toLowerCase().contains(match.toLowerCase())){
					return i;
				}
			}
			
		}
		return -1;
	}
	private void findedContactToScreen(int position){
		if(Agenda.fContacts.size() > 7){
			minSpin = position;
			maxSpin = minSpin + 7;
			if(maxSpin> Agenda.fContacts.size()){
				maxSpin = Agenda.fContacts.size();
				minSpin = maxSpin - 7;

			}

			refreshScreen();
		}else{
			JOptionPane.showMessageDialog(null, "Búsqueda rápida habilitada a partir de 8 contactos.");
		}

		
	}
	
	private void refreshScreen(){
		hitboxes2.get(minSpin).setLocation(CONTACT_X, CONTACT_Y + 5);
		if(moreThan7){
			hitboxes2.get(6).setLocation(CONTACT_X, Y_VISIBLE - BOX_HEIGHT);
			hitboxes2.get(hitboxes2.size() - 7).setLocation(CONTACT_X, CONTACT_Y);
		}
		for(int i = minSpin; i < maxSpin - 1; i++){
			follow(hitboxes2.get(i),hitboxes2.get(i + 1),40);
		}


		JOptionPane.showMessageDialog(null, "Contacto encontrado");
	}
	
	

	public boolean[] getHoverImg() {
		return hoverImg;
	}
	
	public void setHoverImg(boolean[] hoverImg) {
		this.hoverImg = hoverImg;
	}

	public Rectangle[] getImgHitboxes() {
		return imgHitboxes;
	}
	public void setImgHitboxes(Rectangle[] imgHitboxes) {
		this.imgHitboxes = imgHitboxes;
	}
	public boolean isFinderHover() {
		return finderHover;
	}
	public void setFinderHover(boolean finderHover) {
		this.finderHover = finderHover;
	}
	public int getMinSpin() {
		return minSpin;
	}
	public void setMinSpin(int minSpin) {
		this.minSpin = minSpin;
	}
	public int getMaxSpin() {
		return maxSpin;
	}
	public void setMaxSpin(int maxSpin) {
		this.maxSpin = maxSpin;
	}
	public Map<Integer, Rectangle> getHitboxes2() {
		return hitboxes2;
	}
	public void setHitboxes2(Map<Integer, Rectangle> hitboxes2) {
		this.hitboxes2 = hitboxes2;
	}
	public boolean[] getHoverData() {
		return hoverData;
	}
	public void setHoverData(boolean[] hoverData) {
		this.hoverData = hoverData;
	}
	public ContactInfoScreen getContactInfo() {
		return contactInfo;
	}
	public void setContactInfo(ContactInfoScreen contactInfo) {
		this.contactInfo = contactInfo;
	}

}
