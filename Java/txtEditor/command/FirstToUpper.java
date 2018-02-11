package command;

import javax.swing.JOptionPane;

import controller.Utilities;
import view.TextPanePanel;

public class FirstToUpper extends ButtonPattern{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FirstToUpper(String title, String command, String header) {
		super(title, command, header);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(String input) {
		
		try {
			TextPanePanel.area.setText(Utilities.firstToUpperCase(input));
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Your text is empty!!");
		}
		
	}

}
