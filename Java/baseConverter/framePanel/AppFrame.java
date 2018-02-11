package framePanel;


import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import listeners.ConverterListener;


public class AppFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 390;
	public static final int HEIGHT = 350;
	private AppBorderPanel border;
	private Toolkit tool = Toolkit.getDefaultToolkit();
	private Dimension screenSize = tool.getScreenSize();
	private int screenWidth = (int) screenSize.getWidth();
	private int screenHeight = (int) screenSize.getHeight();
	private ConverterListener listener;
	public static Font myFont= new Font("monospaced",Font.BOLD, 25);
	private Image icono;
	
	public AppFrame(){
		
		setTitle("Base Converter");
		setBounds(screenWidth / 3, screenHeight / 4, WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		try {
			icono = ImageIO.read(new File("icono/icono2.png"));
		} catch (IOException e) {
			System.out.println("Imagen no encontrada");
			e.printStackTrace();
		}
		
		setIconImage(icono);
		listener = new ConverterListener(this);
		border = new AppBorderPanel(this);
		
		add(border);
	}

	public ConverterListener getListener() {
		return listener;
	}

	public AppBorderPanel getBorder() {
		return border;
	}


}
