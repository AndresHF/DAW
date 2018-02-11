package command;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public abstract class ButtonPattern implements Command{

	protected String title;
	protected boolean active = false;
	private boolean activated = false;
	protected boolean secuenceFinished = false;
	protected BufferedImage image, activeImage;
	protected Rectangle hitbox;
	protected final int WH;
	protected final int POSX;
	protected final int POSY;
	protected int posX;
	protected int posY;
	private int offset;
	protected int blinkImage = 0;
	protected String textEffect = "";
	protected AgendaDevice agendaDevice;
	
	public ButtonPattern(String path,String path2, String title, int posX, int posY, AgendaDevice agendaDevice){

		this.title = title;
		this.offset = 1;
		try {
			image = ImageIO.read(new File(path));
			activeImage = ImageIO.read(new File(path2));
		} catch (IOException e) {
			e.printStackTrace();
		}
		WH = image.getWidth();
		hitbox = new Rectangle(posX, posY, WH, WH);
		this.POSX = (int) hitbox.getX();
		this.POSY = (int) hitbox.getY();
		this.posX = POSX;
		this.posY = POSY;
		this.agendaDevice = agendaDevice;
	}

	public void paintButton(Graphics g){
		
		if(!isActivated()){
			if(active){
				this.blinkImage ++;
				if(blinkImage <= 50) g.drawImage(activeImage, posX, posY, null);
				else g.drawImage(image, posX, posY, null);
				if(blinkImage > 100) blinkImage = 0;
				g.setColor(Color.YELLOW.brighter());
				updateText();
			}else{
				this.blinkImage = 0;
				g.drawImage(image, posX, posY, null);
				g.setColor(Color.YELLOW.darker());
				resetText();
			}
			drawSt(WH-30,WH/3,g,textEffect);
		}else{
			paintActivatedButton(g);
		}
	}

	public void drawSt(int offset1, int offset2, Graphics g,String text){
		g.drawString(text, posX + offset1, posY + offset2);
	}
	public void paintActivatedButton(Graphics g){
		g.drawImage(activeImage, posX, posY, null);
		g.setColor(Color.YELLOW.brighter());
		drawSt(WH-30,WH/3,g, title);
	}
	public void updateText(){
		this.textEffect = title.substring(0, offset);
		if(offset >= title.length()){
			offset = title.length();
		}else{
			offset++;
		}
	}
	
	public void resetText(){
		this.textEffect = title.substring(0, offset);
		if(offset > 0){
			offset --;
		}else{
			offset = 0;
		}
	}
	
	public void setToMainPosition(){
		boolean yFinished = posY <= 0;
		boolean xFinished = posX > (WH / 2);

		if(!yFinished) posY -= 3;
		else if(!xFinished) posX += 3;
		else if(yFinished && xFinished) this.secuenceFinished = true;
	}
	
	public void initVanish(){
		this.hitbox.setLocation(20, 650);
		this.posX -= 2;
		if(posX < -150 && !isActivated()){
			this.secuenceFinished = true;
		}
	}
	public void resetPos(){
		this.posX = POSX;
		this.posY = POSY;
		this.hitbox.setLocation(posX, posY);
	}
	public void isHover(Point p){
		active = hitbox.contains(p);
	}

	public void setHitBoxOutOfBounds(){
		this.hitbox.setLocation(-200, 0);
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isSecuenceFinished() {
		return secuenceFinished;
	}

	public void setSecuenceFinished(boolean secuenceFinished) {
		this.secuenceFinished = secuenceFinished;
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}
}
