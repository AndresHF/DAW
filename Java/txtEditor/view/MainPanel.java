package view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

public class MainPanel extends JPanel{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private TextPanePanel txtArea;
	private MenuPanel menu;
	private OutputPanel output;
	private TriggerPanel trigger;
	private BorderLayout layout;
	
	
	public MainPanel(){
		layout = new BorderLayout(20,0);
		
		setLayout(layout);
		setBackground(Color.LIGHT_GRAY);
		txtArea = new TextPanePanel();
		menu = new MenuPanel();
		output = new OutputPanel();
		trigger = new TriggerPanel();
		
		add(txtArea, BorderLayout.CENTER);
		add(menu, BorderLayout.NORTH);
		add(output, BorderLayout.EAST);
		add(trigger, BorderLayout.WEST);
	}


	public TextPanePanel getTxtArea() {
		return txtArea;
	}



	public void setTxtArea(TextPanePanel txtArea) {
		this.txtArea = txtArea;
	}



	public MenuPanel getMenu() {
		return menu;
	}



	public void setMenu(MenuPanel menu) {
		this.menu = menu;
	}
	
}
