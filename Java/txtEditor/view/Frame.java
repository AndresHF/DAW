package view;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import controller.Controller;

public class Frame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static Toolkit tool = Toolkit.getDefaultToolkit();
	private static Dimension d = tool.getScreenSize();
	public static final int WIDTH = d.width;
	public static final int HEIGHT = d.height;
	public static Controller controller;
	private MainPanel panel;
	private Image icon;
	
	public Frame(){
		
		setBounds(0, 0, WIDTH, HEIGHT - 50);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("JaGUaR´s Text Editor");
		setResizable(true);
		try{
			icon = ImageIO.read(new File("img/icon.png"));
		}catch(IOException e){
			e.printStackTrace();
		}
		setIconImage(icon);
		controller = new Controller(this);
		panel = new MainPanel();
		add(panel);
	}

	public MainPanel getPanel() {
		return panel;
	}

	public void setPanel(MainPanel panel) {
		this.panel = panel;
	}

}
