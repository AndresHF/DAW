package view;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import command.NewContact;
import command.FindContact;
import command.ModifyContact;
import command.RemoveContact;
import components.Agenda;
import controller.Controller;
import controller.ContactControler;

public class Frame extends JFrame{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Toolkit tool = Toolkit.getDefaultToolkit();
	private Dimension dim = tool.getScreenSize();
	private final int XPOS = (int) (dim.getWidth() /4);
	private final int YPOS = (int) (dim.getHeight() / 10);
	public static final int WIDTH = 800;
	public static final int HEIGHT = 630;
	private Image icon;
	

	public static MainCanvas canvas;
	private Controller controller;
	private ContactControler newController;
	
	public Frame(){
			
		try {
			icon = ImageIO.read(new File("img/frameIcon.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setBounds(XPOS, YPOS, WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("JaGUaR´s Agenda");
		setIconImage(icon);
		setResizable(false);
		canvas = new MainCanvas();
		controller = new Controller(canvas);
		newController = new ContactControler((NewContact)Agenda.buttons.get(0),
						     (FindContact)Agenda.buttons.get(1),
						     (ModifyContact)Agenda.buttons.get(2),
						     (RemoveContact)Agenda.buttons.get(3));
		
		canvas.addMouseMotionListener(controller);
		canvas.addMouseListener(controller);
		canvas.addMouseMotionListener(newController);
		canvas.addMouseListener(newController);
		add(canvas);
		controller.start();
	}
}
