package command;

import javax.swing.JButton;

import view.Frame;

public abstract class ButtonPattern extends JButton implements Command{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String title;
	private String command;
	private String header;
	public ButtonPattern(String title, String command, String header){
		this.title = title;
		this.command = command;
		this.header = header;
		this.setText(header);
		this.addActionListener(Frame.controller);
		this.setActionCommand(command);
	}
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}

}
