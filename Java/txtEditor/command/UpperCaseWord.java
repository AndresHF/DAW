package command;

import javax.swing.JOptionPane;

import controller.Utilities;
import view.OutputPanel;
import view.TextPanePanel;

public class UpperCaseWord extends ButtonPattern{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UpperCaseWord(String title, String command, String header) {
		super(title, command, header);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(String input) {
		String upper = JOptionPane.showInputDialog("Set the word to upper case");
		OutputPanel.outputArea.setText(this.title +"<"+ upper +"> has been modified");
		if(upper != null){
			TextPanePanel.area.setText(Utilities.upperCaseWord(input, upper));
		}else{
			JOptionPane.showMessageDialog(null, "Opción cancelada");
		}
	}
}
