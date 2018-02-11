package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import javax.swing.JPanel;

import components.Agenda;

public class MainCanvas extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image background;
	private Image background2;
	public static Font font = new Font("Lucida Calligraphy",Font.PLAIN, 36);
	public static Font font2 = new Font("Lucida Calligraphy",Font.PLAIN, 30);
	public static Agenda agenda;
	private ImageIcon icon;
	private ImageIcon icon2;
	public static ImageIcon popUp;
	public static int posX;
	public static boolean optionActivated = false;
	public static BufferedImage arrowUp1;
	public static BufferedImage arrowUp2;
	public static BufferedImage arrowDown1;
	public static BufferedImage arrowDown2;
	public static BufferedImage screen;
	
	public MainCanvas(){
		
		icon = new ImageIcon("img/globe.gif");
		icon2 = new ImageIcon("img/code.gif");
		popUp = new ImageIcon("img/newIcon.ico");
		background = icon.getImage();
		background2 = icon2.getImage();
		setFont(font);
		agenda = new Agenda();
		posX = 120;
		loadImage();
		
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.clearRect(0, 0, Frame.WIDTH, Frame.HEIGHT);
		g2.setColor(Color.GRAY);
		g2.fillRect(0, 0, Frame.WIDTH, Frame.HEIGHT);
		if(!optionActivated) g2.drawImage(background, posX, 0, null);
		else{
			g2.drawImage(background2, posX, 0, null);
		}
		
		agenda.paintAgenda(g);
		if(Agenda.work || Agenda.doctor){
			g2.setColor(Color.RED);
			g2.drawString("AGENDA DESACTIVADA", 150, 200);
		}
		
	}
	
	private void loadImage(){
		try{
			arrowUp1 = ImageIO.read(new File("img/up1.png"));
			arrowUp2 = ImageIO.read(new File("img/up2.png"));
			arrowDown1 = ImageIO.read(new File("img/down1.png"));
			arrowDown2 = ImageIO.read(new File("img/down2.png"));
			screen = ImageIO.read(new File("img/tes2t.png"));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void resetAll(){
		optionActivated = false;
		posX = 120;
	}
}
