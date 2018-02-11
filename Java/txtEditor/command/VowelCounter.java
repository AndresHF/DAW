package command;

import controller.Utilities;
import view.OutputPanel;

public class VowelCounter extends ButtonPattern{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VowelCounter(String title, String command, String header) {
		super(title, command, header);

	}

	@Override
	public void execute(String input) {
		OutputPanel.outputArea.setText(title);
		Utilities.printVowelArr(Utilities.countEachVowel(input), OutputPanel.outputArea);
	}

}
