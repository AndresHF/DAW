package command;

import javax.swing.JOptionPane;

import controller.Utilities;
import view.OutputPanel;
import view.TextPanePanel;

public class RemoveWord extends ButtonPattern{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RemoveWord(String title, String command, String header) {
		super(title, command, header);
	}

	@Override
	public void execute(String input) {
		input = JOptionPane.showInputDialog("Select the word or subsequence to remove:");
		if(input.length() == 0 || TextPanePanel.area.getText().length() == 0){
			OutputPanel.outputArea.setText(title +"Nothing can not be erased. Deep philosophical disquisition.");
		}else if(input.equals(" ")){
			OutputPanel.outputArea.setText(title +"I can´t remove blank spaces from text. That´s why .trim() exist...");
		}else{
			OutputPanel.outputArea.setText(title +"<" + input +">" + " was removed from text.");
			TextPanePanel.area.setText(Utilities.removeWord(TextPanePanel.area.getText(), input));
		}

		
	}

}
