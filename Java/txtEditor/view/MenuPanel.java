package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;

public class MenuPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JMenuBar bar;
	private JMenu file, fonts, size, style, align, color;
	private JMenuItem[] fileItems = new JMenuItem[4];
	private JMenuItem[] fontItems = new JMenuItem[4];
	private JMenuItem[] fontSizes = new JMenuItem[5];
	private JMenuItem[] fontStyles = new JMenuItem[3];
	private JMenuItem[] fontAlign = new JMenuItem[4];
	private JMenuItem[] fontColors = new JMenuItem[1];
	private JMenuItem fileSave, fileLoad, fileExit, fileNew, arial, verdana, consolas, times, one, two, three, four, five, bold, italic;
	private JMenuItem underline, left, right, justified, center, colors;
	
	private final Font BAR_FONT = new Font("Consolas", Font.PLAIN, 12);
	
	private final Dimension BAR_DIMENSION = new Dimension(10, 1);
	
	public MenuPanel(){
		
		setLayout(new FlowLayout(FlowLayout.LEFT));
		setBackground(Color.DARK_GRAY);
		//loadImageArr(colors);
		
		bar = new JMenuBar();
		bar.add(Box.createRigidArea(BAR_DIMENSION));
		
		//FILE ITEMS
		fileNew = new JMenuItem("New");
		fileSave = new JMenuItem("Save");
		fileLoad = new JMenuItem("Load");
		fileExit = new JMenuItem("Exit");
		fileItems[0] = fileNew;
		fileItems[1] = fileSave;
		fileItems[2] = fileLoad;
		fileItems[3] = fileExit;
		
		//FONT ITEMS
		arial = new JMenuItem("Arial");
		verdana = new JMenuItem("Verdana");
		consolas = new JMenuItem("Consolas");
		times = new JMenuItem("Times New Roman");
		fontItems[0] = arial;
		fontItems[1] = verdana;
		fontItems[2] = consolas;
		fontItems[3] = times;
		
		//FONT SIZE ITEMS
		one = new JMenuItem("12");
		two = new JMenuItem("16");
		three = new JMenuItem("24");
		four = new JMenuItem("36");
		five = new JMenuItem("48");
		fontSizes[0] = one;
		fontSizes[1] = two;
		fontSizes[2] = three;
		fontSizes[3] = four;
		fontSizes[4] = five;
		
		//FONT STYLE ITEMS
		bold = new JMenuItem("Bold");
		bold.addActionListener(new StyledEditorKit.BoldAction());
		italic = new JMenuItem("Italic");
		italic.addActionListener(new StyledEditorKit.ItalicAction());
		underline = new JMenuItem("Underline");
		underline.addActionListener(new StyledEditorKit.UnderlineAction());
		fontStyles[0] = bold;
		fontStyles[1] = italic;
		fontStyles[2] = underline;

		//FONT COLOR ITEMS
		colors = new JMenuItem("Pick color");
		fontColors[0] = colors;
		
		//FONT ALIGN
		left = new JMenuItem("Left");
		left.addActionListener(new StyledEditorKit.AlignmentAction("", StyleConstants.ALIGN_LEFT));
		right = new JMenuItem("Rigth");
		right.addActionListener(new StyledEditorKit.AlignmentAction("", StyleConstants.ALIGN_RIGHT));
		justified = new JMenuItem("Justified");
		justified.addActionListener(new StyledEditorKit.AlignmentAction("", StyleConstants.ALIGN_JUSTIFIED));
		center = new JMenuItem("Center");
		center.addActionListener(new StyledEditorKit.AlignmentAction("", StyleConstants.ALIGN_CENTER));
		fontAlign[0] = left;
		fontAlign[1] = right;
		fontAlign[2] = justified;
		fontAlign[3] = center;

		newMenu(file,"File", BAR_FONT, fileItems);
		newMenu(fonts,"Fonts", BAR_FONT, fontItems);
		newMenu(size,"Font size",BAR_FONT, fontSizes);
		newMenu(style,"Font style", BAR_FONT, fontStyles);
		newMenu(color,"Font color", BAR_FONT, fontColors);
		newMenu(align,"Font align",BAR_FONT, fontAlign);
		add(bar);
		
	}

	private void newMenu(JMenu menu, String title, Font font, JMenuItem[] items){
		
		menu = new JMenu(title);
		menu.setFont(font);
		
		for(JMenuItem item : items){
			menu.add(item);
			if(item != items[items.length - 1]) menu.addSeparator();
			
			if(title.equals("Font size")){
				item.addActionListener(new StyledEditorKit.FontSizeAction("",Integer.parseInt(item.getText())));
			}else if(title.equals("Fonts")){
				item.addActionListener(new StyledEditorKit.FontFamilyAction("", item.getText()));
			}
			item.addActionListener(Frame.controller);
		}
		
		bar.add(Box.createHorizontalStrut(10));
		bar.add(menu);
		bar.add(Box.createHorizontalStrut(10));
	}
	


}










