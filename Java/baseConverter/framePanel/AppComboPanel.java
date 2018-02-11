package framePanel;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class AppComboPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<String> comboA, comboB;
	private String[] comboItems = new String[15];
	private AppFrame frame;
	private JButton buttA, buttB, buttC, buttD, buttE, buttF;

	public AppComboPanel(AppFrame frame){

		this.frame = frame;
		putButton(buttA, "A");
		putButton(buttB, "B");
		putButton(buttC, "C");
		putButton(buttD, "D");
		putButton(buttE, "E");
		putButton(buttF, "F");
		
		
		initComboItems();

		comboA = new JComboBox<String>(comboItems);
		comboA.setSize(100, 30);
		comboB = new JComboBox<String>(comboItems);
		
		add(comboA);
		add(comboB);
	}

	
	private void initComboItems(){
		for(int i = 0; i < comboItems.length; i++){
			comboItems[i] = ""+(i + 2); 
		}
	}
	private void putButton(JButton button, String title){
		
		button = new JButton(title);
		button.addActionListener((ActionListener)frame.getListener());
		this.add(button);
		
	}


	public JComboBox<String> getComboA() {
		return comboA;
	}


	public JComboBox<String> getComboB() {
		return comboB;
	}

}
