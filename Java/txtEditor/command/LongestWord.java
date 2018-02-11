package command;

import javax.swing.JOptionPane;

import controller.Utilities;
import view.OutputPanel;

public class LongestWord extends ButtonPattern{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LongestWord(String title, String command, String header) {
		super(title, command, header);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(String input) {
		try {
			String longest = Utilities.longestWord(input);
			if(!longest.startsWith("Multiple"))
			Utilities.printResult(title+"The longest word is: <"+ longest +"> with %02d characters.", longest.length(), OutputPanel.outputArea);
			else OutputPanel.outputArea.setText(title+longest);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "ERROR: Your text is empty");
		}
		
	}

}
