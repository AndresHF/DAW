package components;

import java.awt.Graphics;

public class ContactInfoScreen {
	
	private String info;
	
	private FriendContact fContact;
	private int posY = ContactFinder.CONTACT_Y;
	private int timer = 0;
	private int effect = 0;
	private int linebreakOffset = 40;
	private boolean switch1 = false;
	private boolean switch2 = false;
	public ContactInfoScreen(FriendContact fContact){

		this.fContact = fContact;
		info = fContact.toString();
		
	}
	//FALTAN 2 CONSTRUCTORES MÁS wContact y dContact
	
	public void paint(Graphics g){
		
		posY = ContactFinder.CONTACT_Y;
		
		for(String data : info.split("\n")){
			if(data.length() > 55){
				if(effect < data.length()){
					if(effect < 55){
						g.drawString(data.substring(0, effect), ContactFinder.CONTACT_X - 20, posY);
					}else{
						g.drawString(data.substring(0, 55), ContactFinder.CONTACT_X - 20, posY);
						posY += linebreakOffset / 2;
						if(effect < 110){
							g.drawString(data.substring(55, effect), ContactFinder.CONTACT_X - 20, posY);
						}else{
							g.drawString(data.substring(55, 110), ContactFinder.CONTACT_X - 20, posY);
							posY += linebreakOffset / 2;
							g.drawString(data.substring(110, effect), ContactFinder.CONTACT_X - 20, posY);
						}
						
					}
				}else{
					if(data.length() < 110){
						g.drawString(data.substring(0, 55), ContactFinder.CONTACT_X - 20, posY);
						posY += linebreakOffset / 2;
						g.drawString(data.substring(55), ContactFinder.CONTACT_X - 20, posY);
					}else{
						g.drawString(data.substring(0, 55), ContactFinder.CONTACT_X - 20, posY);
						posY += linebreakOffset / 2;
						g.drawString(data.substring(55, 110), ContactFinder.CONTACT_X - 20, posY);
						posY += linebreakOffset / 2;
						g.drawString(data.substring(110), ContactFinder.CONTACT_X - 20, posY);
					}
					
				}
			}else{
				//g.setColor(Color.YELLOW);
				if(effect < data.length()){
					g.drawString(data.substring(0,effect), ContactFinder.CONTACT_X - 20, posY);
				}else{
					g.drawString(data,ContactFinder.CONTACT_X - 20, posY);
				}
			}
			if(timer <= 3301){
				timer++;
			}
			if(timer % 20 == 0){
				effect++;
			}
			posY += linebreakOffset;
		
			if(posY > ContactFinder.Y_VISIBLE && !switch1){
				linebreakOffset = 29;
				switch1 = true;
			}
			if(posY > ContactFinder.Y_VISIBLE + 40 && !switch2){
				linebreakOffset = 25;
				switch2 = true;
			}
		}

		
	}
	


}
