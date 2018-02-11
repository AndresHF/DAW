package command;

import javax.swing.JOptionPane;

import controller.Utilities;
import view.OutputPanel;
import view.TextPanePanel;

public class MirrorReverse extends ButtonPattern{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MirrorReverse(String title, String command, String header) {
		super(title, command, header);
	}

	@Override
	public void execute(String input) {
		
		int mode = -1;
		String menu = "<html><body>Choose your option:<br>1.Reverse text<br>2.Mirror text</body></html>";
		boolean success = false;
		
		
		while(mode < 1 || mode > 2){
			try{
				mode = Integer.parseInt(JOptionPane.showInputDialog(menu));
				if(mode < 1 || mode > 2) JOptionPane.showMessageDialog(null, "Wrong selection. Must be 1 or 2...");
				else success = true;
			}catch(NumberFormatException e){
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "ERROR: wrong input...");
				break;
			}
		}
		
		if(success){
			if(mode == 1){
				TextPanePanel.area.setText(Utilities.reverseStr(input));
				OutputPanel.outputArea.setText(title+"Text reversed successfully");
			}else if(mode == 2){
				TextPanePanel.area.setText(Utilities.mirrorStr(input));
				OutputPanel.outputArea.setText(title+"Text mirrored successfully");
			}
		}
		
	}

}
