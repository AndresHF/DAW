package command;

import javax.swing.JOptionPane;

import controller.Utilities;
import view.OutputPanel;
import view.TextPanePanel;

public class DecodeTxt extends ButtonPattern{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DecodeTxt(String title, String command, String header) {
		super(title, command, header);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(String input) {
		try{
			int key = Integer.parseInt(JOptionPane.showInputDialog("Insert the key to decode:"));
			int[] encodedText =  EncodeTxt.encodedText;
			String decoded = "";
			for(int i = 0; i< encodedText.length; i++){
				decoded += (char)encodedText[i];
			}
			TextPanePanel.area.setText(Utilities.decodeText(decoded, key));
			OutputPanel.outputArea.setText(title+"Text successfully decoded");
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "ERROR: Wrong input or you didn´t encode your text.");
		}
		
	

	}

}
