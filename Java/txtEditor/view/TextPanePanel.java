package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.Document;

public class TextPanePanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static JTextPane area;
	public static Document doc;
	private JScrollPane scroll;
	public static final Font DEFAULT_FONT = new Font("Arial",Font.PLAIN, 16);
	private final int AREA_TOP_OFFSET = 10;
	private final int AREA_WIDTH_OFFSET = 60;
	public static int fontSize = 16;
	public static int fontStyle = Font.PLAIN;
	public static String fontType = "Arial";
	
	public TextPanePanel(){
		setLayout(null);
		setSize(Frame.WIDTH/2, Frame.HEIGHT);
		
		area = new JTextPane();
		
		area.setFont(DEFAULT_FONT);
		setBackground(Color.LIGHT_GRAY);
		area.setMargin(new Insets(20,50,25,50));

		doc = area.getDocument();
		doc.addDocumentListener(Frame.controller);
		area.setDocument(doc);
		
		
		scroll = new JScrollPane(area);
		scroll.setBounds(Frame.WIDTH / 10, AREA_TOP_OFFSET, this.getWidth() - AREA_WIDTH_OFFSET, this.getHeight() - AREA_TOP_OFFSET * 15);
		add(scroll);
		
	}

}