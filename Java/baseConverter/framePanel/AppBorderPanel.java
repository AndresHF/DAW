package framePanel;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class AppBorderPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AppGridPanel grid;
	private AppComboPanel combo;
	private AppFrame frame;
	private JLabel console;
	private JButton convert, zero;

	private BorderLayout myBorder = new BorderLayout();
	
	public AppBorderPanel(AppFrame frame){
		this.frame = frame;
		myBorder.setVgap(20);
		myBorder.setHgap(5);
		setLayout(myBorder);
		grid = new AppGridPanel(this.frame);
		this.add(grid, BorderLayout.CENTER);
		
		combo = new AppComboPanel(this.frame);
		this.add(combo, BorderLayout.SOUTH);
		
		console = new JLabel("0");
		console.setHorizontalAlignment(SwingConstants.RIGHT);
		console.setFont(AppFrame.myFont);
		console.setSize(Frame.WIDTH, 150);
		this.add(console, BorderLayout.NORTH);
		
		convert = new JButton("=");
		convert.setFont(AppFrame.myFont);
		convert.addActionListener(frame.getListener());
		add(convert, BorderLayout.EAST);
		
		zero = new JButton("Z");
		zero.setFont(AppFrame.myFont);
		zero.addActionListener(frame.getListener());
		add(zero, BorderLayout.WEST);
	}

	public AppGridPanel getGrid() {
		return grid;
	}

	public AppComboPanel getCombo() {
		return combo;
	}

	public JLabel getConsole() {
		return console;
	}

}
