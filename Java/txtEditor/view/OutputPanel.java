package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class OutputPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static JTextArea docCounter;
	public static JTextArea outputArea;
	private final Font DEFAULT_FONT = new Font("Consolas", Font.BOLD, 16);
	private BorderLayout border;

	public OutputPanel(){
		border = new BorderLayout(10,10);
		border.setHgap(10);
		border.setVgap(10);
		setLayout(border);
		setBackground(Color.LIGHT_GRAY);
		docCounter = new JTextArea(2,1);
		updateDocCounter(0, 0);
		docCounter.setEnabled(false);
		docCounter.setFont(DEFAULT_FONT);
		docCounter.setBackground(Color.BLACK);
		docCounter.setForeground(Color.WHITE);
		
		outputArea = new JTextArea("");
		outputArea.setFont(DEFAULT_FONT);
		outputArea.setWrapStyleWord(true);
		outputArea.setLineWrap(true);
		outputArea.setBackground(Color.BLACK);
		outputArea.setForeground(Color.WHITE);
		add(new JScrollPane(outputArea));
		
		add(docCounter, BorderLayout.NORTH);
		add(outputArea,BorderLayout.CENTER);
	}
	
	public static void updateDocCounter(int charNum, int wordNum){
		String pattern = "\n Actual characters: %05d  \n\n Actual words: %05d\n";
		docCounter.setText(String.format(pattern, charNum, wordNum));
		
	}
}
