package command;

import javax.swing.JOptionPane;

import controller.Utilities;
import view.OutputPanel;
import view.TextPanePanel;

public class EncodeTxt extends ButtonPattern{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int [] encodedText;
	public EncodeTxt(String title, String command, String header) {
		super(title, command, header);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(String input) {
		encodedText = new int[input.length()];
		try{
			int key = Integer.parseInt(JOptionPane.showInputDialog("<html><body>Set your key number to encode text.<br> "
					+ "IMPORTANT<br>--------------------------------------------<br>"
					+ "1: Take note of your key, you will need it to decode the text.<br>"
					+ "2: The key must be an integer number.<br>"
					+ "3: Once you encode the text the key is lost after closing the program, decode your text before saving file.</body></html>"));
			char[] encoded = Utilities.encryptText(input, key).toCharArray();
			for(int i = 0; i < encoded.length; i++){
					encodedText[i] = encoded[i];
			}
			TextPanePanel.area.setText(Utilities.encryptText(input, key));
			OutputPanel.outputArea.setText(title+"Text successfully encoded");
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "ERROR: Wrong input");
		}	
	}

	public static int[] getEncodedText() {
		return encodedText;
	}


}
