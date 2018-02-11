package listeners;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import converter.Converter;
import converter.FractionalConverter;
import framePanel.AppFrame;

public class ConverterListener implements ActionListener{

	private AppFrame frame;
	private boolean afterResult = false;
	
	public ConverterListener(AppFrame frame){
		
		this.frame = frame;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		String command = e.getActionCommand();
		frame.getBorder().getConsole().setForeground(Color.BLACK);
		
		if(frame.getBorder().getConsole().getText().equals("0") && 
		   !command.equals("<")) frame.getBorder().getConsole().setText("");
		
		
		if(command.equals("1")) setConsoleText(command);
		else if(command.equals("2")) setConsoleText(command);
		else if(command.equals("3")) setConsoleText(command);
		else if(command.equals("4")) setConsoleText(command);
		else if(command.equals("5")) setConsoleText(command);
		else if(command.equals("6")) setConsoleText(command);
		else if(command.equals("7")) setConsoleText(command);
		else if(command.equals("8")) setConsoleText(command);
		else if(command.equals("9")) setConsoleText(command);
		else if(command.equals("0")) setConsoleText(command);
		else if(command.equals("A")) setConsoleText(command);
		else if(command.equals("B")) setConsoleText(command);
		else if(command.equals("C")) setConsoleText(command);
		else if(command.equals("D")) setConsoleText(command);
		else if(command.equals("E")) setConsoleText(command);
		else if(command.equals("F")) setConsoleText(command);
		else if(command.equals(".")) setConsoleText(command);
		else if(command.equals("<")) eraseText();
		else if(command.equals("Z")) setToZero();
		else if(command.equals("=")){
			afterResult = true;
			String number = frame.getBorder().getConsole().getText();
			String baseA = frame.getBorder().getCombo().getComboA().getSelectedItem().toString();
			String baseB = frame.getBorder().getCombo().getComboB().getSelectedItem().toString();
			
			Converter c = new Converter(number, baseA, baseB);
			
			if(c.findError(number, baseA, baseB)) 
				frame.getBorder().getConsole().setForeground(Color.RED);
			else{
				if(c.getFractional().length() > 0){
					FractionalConverter f = new FractionalConverter(c.getFractional(), baseA, baseB);
					
					frame.getBorder().getConsole().setForeground(Color.BLACK);
					setConsoleText(c.changeToBaseN() + "." + f.changeFNToBaseN());
					
				}else{
					frame.getBorder().getConsole().setForeground(Color.BLACK);
					setConsoleText(c.changeToBaseN());
				}
			
			} 
		}
		
		if(frame.getBorder().getConsole().getText().equals("")) 
			frame.getBorder().getConsole().setText("0");
		
	}

	
	private void setConsoleText(String text){
		if(!afterResult)
			frame.getBorder().getConsole().setText(frame.getBorder().getConsole().getText()+text);
		else
			frame.getBorder().getConsole().setText(text);
			
		if(frame.getBorder().getConsole().getText().charAt(0) == '.') 
			frame.getBorder().getConsole().setText("0.");
		
		afterResult = false;
	}
	private void eraseText(){
		frame.getBorder().getConsole().setText
		(frame.getBorder().getConsole().getText().substring
		(0, frame.getBorder().getConsole().getText().length() - 1));
	}
	private void setToZero(){
		frame.getBorder().getConsole().setText("0");
	}
}




