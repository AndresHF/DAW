package components;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JOptionPane;

import view.Frame;
import view.MainCanvas;

public class NewContactBuilder {
	
	private final int BOX_HEIGHT = 20;
	private final int NO_SELECTED_X = 80;
	private final int SELECTED_X = 10;
	public static String name = "Selecciona nombre";
	public static String surnames = "Selecciona apellidos";
	public static String phoneNumber = "Selecciona teléfono fijo";
	public static String movileNumber = "Selecciona teléfono móvil";
	public static String address = "Selecciona dirección";
	public static String email = "Selecciona e-mail";
	private String[] data = new String[6];
	private String[] titles = {name, surnames, phoneNumber, movileNumber, address, email};
	private Rectangle[] hitboxes = {new Rectangle(NO_SELECTED_X,200,name.length() * 10, BOX_HEIGHT), 
									new Rectangle(NO_SELECTED_X,240,surnames.length() * 10, BOX_HEIGHT), 
									new Rectangle(NO_SELECTED_X,280,phoneNumber.length() * 10, BOX_HEIGHT),
								    new Rectangle(NO_SELECTED_X,320,movileNumber.length() * 10, BOX_HEIGHT), 
								    new Rectangle(NO_SELECTED_X,360,address.length() * 10, BOX_HEIGHT), 
								    new Rectangle(NO_SELECTED_X,400,email.length() * 10, BOX_HEIGHT)};
	private boolean[] hoverData = {false, false, false, false, false, false};
	
	public static Font font = new Font("Consolas", Font.PLAIN, 18);
	public static Font font2 = new Font("Consolas", Font.PLAIN, 12);
	public static Font font3 = new Font("Consolas", Font.PLAIN, 9);
	private int position = -1;
	
	public NewContactBuilder(){

		configInitialData(name,surnames, phoneNumber, movileNumber, address, email);
		configHitboxes();
	}
	public NewContactBuilder(String name, String surnames, String phoneNumber, String movileNumber, String address, String email, int position){
		this.position = position;
		configInitialData(name,surnames, phoneNumber, movileNumber, address, email);
		configHitboxes();
	}
	public String toString(){
		return name +
			   surnames +
			   phoneNumber +
			   movileNumber+
			   address + 
			   email;
		
	}
	
	public void paint(Graphics g){
		//g.setFont(font);

		for(int i = 0; i < data.length; i++){
			if(!hoverData[i]) g.setColor(Color.GREEN.darker());
			else{
				g.setColor(Color.YELLOW);
				g.setFont(MainCanvas.font2);
				g.drawString(titles[i], Frame.WIDTH / 2 - titles[i].length() * 8, 170);
				g.setColor(Color.GREEN);
			}
			int x = (int)hitboxes[i].getX();
			int y = (int)hitboxes[i].getY() + 15;
			if(data[i].length() > 65 && data[i].length() < 105) g.setFont(font2);
			else if(data[i].length() > 105) g.setFont(font3);
			else g.setFont(font);
			g.drawString(data[i], x, y);
		}
	}
	
	public void setHoverData(Point p){
		
		for(int i = 0; i < hoverData.length; i++){
			hoverData[i] = hitboxes[i].contains(p);
		}
	}
	
	public void launchDataSet(){
		
		for(int i = 0; i < data.length; i++){
			if(hoverData[i]){
				try{
					String newData = (String)JOptionPane.showInputDialog(null, 
							"Introduzca "+titles[i].substring(11)+":", 
							titles[i], 
							3, MainCanvas.popUp, null, data[i]);
					if(newData != null){
						if(!newData.startsWith("Selecciona") && newData.length() > 0){
							if(newData.length() > 154){
								JOptionPane.showMessageDialog(null, "Campo demasiado largo, no puedes exceder los 154 caracteres.");
							}else{
								data[i] = newData;
								hitboxes[i].setBounds(SELECTED_X, (int)hitboxes[i].getY(), data[i].length() *10, BOX_HEIGHT);
							}
							
						}else{
							JOptionPane.showMessageDialog(null, "No puedes introducir el campo vacío");
						}
					}
				}catch(NullPointerException e){
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Acción cancelada...");
				}
				
			}
		}
	}
	public String[] getData() {
		return data;
	}
	public void setData(String[] data) {
		this.data = data;
	}
	private void configHitboxes(){
		for(int i = 0; i < hitboxes.length; i++){
			int yPos = 0;
			if(i == 0){
				yPos = 200;
			}else{
				yPos = (int)hitboxes[i - 1].getY() + 40;
			}
			
			hitboxes[i] = new Rectangle(NO_SELECTED_X,yPos,data[i].length() * 10, BOX_HEIGHT);
		}
	}
	private void configInitialData(String name, String surnames, String phoneNumber, String movileNumber, String address, String email){
		data[0] = name;
		data[1] = surnames;
		data[2] = phoneNumber;
		data[3] = movileNumber;
		data[4] = address;
		data[5] = email;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
}
