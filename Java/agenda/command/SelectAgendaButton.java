package command;

import java.awt.Color;
import java.awt.Graphics;

public abstract class SelectAgendaButton extends ButtonPattern{

	public SelectAgendaButton(String path, String path2, String title, int posX, int posY, AgendaDevice agendaDevice) {
		super(path, path2, title, posX, posY, agendaDevice);
	}

	
	
	public void paintActivatedButton(Graphics g){
		g.drawImage(activeImage, 630, 10, null);
	}
	public void drawSt(int offset1, int offset2, Graphics g,String text){
		g.setColor(Color.ORANGE);
		g.drawString(text, posX - offset1, posY + offset2 + 150);
	}

}
