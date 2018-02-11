package command;

import javax.swing.JOptionPane;

import controller.Utilities;
import view.OutputPanel;
import view.TextPanePanel;

public class TimesSubSequence extends ButtonPattern{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TimesSubSequence(String title, String command, String header) {
		super(title, command, header);
		
	}

	@Override
	public void execute(String input) {
		input = JOptionPane.showInputDialog("Set the subsequence to count:");
		String pattern = "I have found <" + input + "> %1d times.";
		int result = Utilities.timesSubSequence(TextPanePanel.area.getText(), input);
		if(input.equals(" ")){
			OutputPanel.outputArea.setText(title + "Blank spaces itselfs are not atmitted as subsequence...");
		}else if(input.length() == 0){
			OutputPanel.outputArea.setText(title + "Don´t try to count THE NOTHING, it´s impossible (and boring)...");
		}else{
			Utilities.printResult(title + pattern, result, OutputPanel.outputArea);
		}
		
		
	}

}
