package framePanel;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class AppGridPanel extends JPanel{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int ROWS = 4;
	private final int COL = 3;

	private AppFrame frame;
	private JButton butt1,butt2,butt3,butt4,butt5,butt6,butt7,butt8,butt9,butt0,buttComma, buttEraser;
	private GridLayout myGrid;
	public AppGridPanel(AppFrame frame){
		this.frame = frame;
		
		myGrid = new GridLayout(ROWS, COL);
		myGrid.setHgap(5);
		myGrid.setVgap(5);
		setLayout(myGrid);
		setVisible(true);
		putButton(butt1, "1");
		putButton(butt2, "2");
		putButton(butt3, "3");
		putButton(butt4, "4");
		putButton(butt5, "5");
		putButton(butt6, "6");
		putButton(butt7, "7");
		putButton(butt8, "8");
		putButton(butt9, "9");
		putButton(butt0, "0");
		putButton(buttComma, ".");
		putButton(buttEraser, "<");

		
	}
	
	
	private void putButton(JButton button, String title){
		
		button = new JButton(title);
		button.setFont(AppFrame.myFont);
		button.addActionListener((ActionListener)frame.getListener());
		this.add(button);
		
	}

}
