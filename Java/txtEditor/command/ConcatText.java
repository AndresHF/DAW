package command;

import javax.swing.JOptionPane;

import controller.Utilities;
import view.OutputPanel;
import view.TextPanePanel;

public class ConcatText extends ButtonPattern{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConcatText(String title, String command, String header) {
		super(title, command, header);
		
	}

	@Override
	public void execute(String input) {
		int maxPos = TextPanePanel.area.getText().length();
		input = JOptionPane.showInputDialog("Select the String to concat:");
		int pos = -1;
		boolean success = false;
		while(pos < 0 || pos > maxPos){
			try{
				pos = Integer.parseInt(JOptionPane.showInputDialog("Select position between 0 and " + maxPos+":"));
				if(pos < 0) JOptionPane.showMessageDialog(null, "Position must be greater than 0...");
				else if(pos > maxPos) JOptionPane.showMessageDialog(null, "Position must be less than " + maxPos+"...");
				success = pos >= 0 && pos <= maxPos;
			}catch(NumberFormatException e){
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "ERROR: Wrong input");
				break;
			}
			
		}
		if(success){
			TextPanePanel.area.setText(Utilities.concatText(TextPanePanel.area.getText(), input, pos));
			OutputPanel.outputArea.setText(title+"Text concatened successfully");
		}

	}

}
