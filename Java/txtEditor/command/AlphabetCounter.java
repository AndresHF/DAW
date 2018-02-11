package command;

import controller.Utilities;
import view.OutputPanel;

public class AlphabetCounter extends ButtonPattern{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AlphabetCounter(String title, String command, String header) {
		super(title, command, header);

	}

	@Override
	public void execute(String input) {
		OutputPanel.outputArea.setText(title);
		Utilities.printAlphabetArr(Utilities.countAlphabet(input), OutputPanel.outputArea);
		
	}

}
