package command;

import javax.swing.JOptionPane;

import controller.Utilities;
import view.OutputPanel;
import view.TextPanePanel;

public class CountSpecificWord extends ButtonPattern{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CountSpecificWord(String title, String command, String header) {
		super(title, command, header);
	}

	@Override
	public void execute(String input) {
		input = JOptionPane.showInputDialog("Type a word to count:");
		OutputPanel.outputArea.setText(title);
		String pattern = "I have found <"+input+"> %1d times.";
		Utilities.printResult(title + pattern,Utilities.countSpecificWord(TextPanePanel.area.getText(), input),OutputPanel.outputArea);
		
	}

}
