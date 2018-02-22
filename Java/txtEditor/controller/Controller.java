package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import command.ButtonPattern;
import view.Frame;
import view.OutputPanel;
import view.TextPanePanel;
import view.TriggerPanel;

import javax.swing.JColorChooser;


public class Controller implements ActionListener, DocumentListener {

	private Frame frame;
	private boolean saveDone = false;
	private Style style;
	private StyledDocument doc;
	private String actualPath = System.getProperty("user.home") + "\\Desktop";
	public Controller(Frame frame) {
		this.frame = frame;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (Character.isDigit(command.charAt(0)))
			TextPanePanel.fontSize = Integer.parseInt(command);
		else if (command.equals("Arial") || command.equals("Verdana") || command.equals("Times New Roman")
				|| command.equals("Consolas"))
			TextPanePanel.fontType = command;
		else if (command.equals("New") || command.equals("Exit"))
			saveProtocol(command);
		else if (command.equals("Load"))
			loadTXT();
		else if (command.equals("Save"))
			saveTXT();
		else if (command.equals("Pick color"))
			changeTextColor(JColorChooser.showDialog(frame, "Choose your color", Color.BLACK));
		
		for(ButtonPattern c: TriggerPanel.buttons){
			if(command.equals(c.getCommand())){
				c.execute(TextPanePanel.area.getText());
			}
		}
	}

	@Override
	public void changedUpdate(DocumentEvent e) {}


	@Override
	public void insertUpdate(DocumentEvent e) {
		OutputPanel.updateDocCounter(e.getDocument().getLength(), Utilities.countWords(TextPanePanel.area.getText()));
	}


	@Override
	public void removeUpdate(DocumentEvent e) {
		OutputPanel.updateDocCounter(e.getDocument().getLength(), Utilities.countWords(TextPanePanel.area.getText()));
	}

	private void loadTXT() {

		JFileChooser chooser = new JFileChooser(actualPath);
		chooser.setApproveButtonText("Load");
		int returnval = chooser.showDialog(frame, null);
		File file = null;

		if (returnval == JFileChooser.APPROVE_OPTION) {
			file = chooser.getSelectedFile();
			actualPath = file.getAbsolutePath();
		} else {
			JOptionPane.showMessageDialog(frame, "Operation cancelled");
		}
		String str;
		if (file != null) {
			if(!file.getName().endsWith(".txt")){
				JOptionPane.showMessageDialog(null, "Sorry, file must be .txt");
			}else{
				try {
					str = new String(Files.readAllBytes(file.toPath()),Charset.forName("Cp1252"));
					resetTextPane();
					TextPanePanel.area.setText(str);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(frame, "ERROR: Not valid file");
				}
			}

		} else {
			return;
		}

	}

	private void saveTXT() {
		BufferedWriter bWriter = null;
		JFileChooser chooser = new JFileChooser(actualPath);
		chooser.setApproveButtonText("Save");
		String fileName = "";
		File file;

		if (chooser.showDialog(frame, null) == JFileChooser.APPROVE_OPTION) {

			fileName = chooser.getSelectedFile() + "";
			if (!fileName.endsWith(".txt"))
				fileName += ".txt";
			file = new File(fileName);

		} else {
			JOptionPane.showMessageDialog(frame, "Operation cancelled");
			return;
		}
		try {
			if (file != null) {

				if (Files.isRegularFile(Paths.get(file.getAbsolutePath()))) {

					int answer = askUser("Do you want to override file?");

					if (affirmativeAnswer(answer)) {
						writeFile(bWriter, file);
						saveDone = true;
					} else if (negativeAnswer(answer)) {
						saveTXT();
						saveDone = true;
					} else
						JOptionPane.showMessageDialog(frame, "Operation cancelled");

				} else {
					writeFile(bWriter, file);
					saveDone = true;
				}

			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(frame, "ERROR: file not saved");
			e.printStackTrace();
		}

	}

	private void writeFile(BufferedWriter bWriter, File file) throws IOException {
		bWriter = new BufferedWriter(new FileWriter(file));
		actualPath = file.getAbsolutePath();
		TextPanePanel.area.write(bWriter);
		bWriter.close();
	}

	private void saveProtocol(String mode) {
		if (!isEmptyArea()) {
			String question = "";
			if (mode == "New")
				question = "Do you want to save file?";
			else if (mode == "Exit")
				question = "Do you want to save file before exit?";
			int answer = askUser(question);

			if (affirmativeAnswer(answer)) {
				saveTXT();
				if (saveDone) {
					if (mode == "New") {
						resetTextPane();
						saveDone = false;
					} else {
						JOptionPane.showMessageDialog(frame, "See you soon!!");
						frame.dispose();

					}

				}
			} else if (negativeAnswer(answer)) {
				if (mode == "Exit")
					frame.dispose();
				else if (mode == "New") {
					resetTextPane();
				}
			}

			else
				JOptionPane.showMessageDialog(frame, "Operation cancelled");
		} else {
			if (mode == "Exit") {
				JOptionPane.showMessageDialog(frame, "See you soon!!");
				frame.dispose();
			}
		}
	}

	private void changeTextColor(Color c) {

		if (c != null) {
			System.out.println(TextPanePanel.area.getSelectedText() != null);
			doc = TextPanePanel.area.getStyledDocument();
			style = TextPanePanel.area.addStyle("newStyle", null);
			StyleConstants.setForeground(style, c);
			StyleConstants.setFontFamily(style, TextPanePanel.fontType);
			StyleConstants.setFontSize(style, TextPanePanel.fontSize);

			try {
				if (TextPanePanel.area.getSelectedText() != null) {

					int offset = TextPanePanel.area.getSelectionStart();
					int selectedLength = TextPanePanel.area.getSelectedText().length();
					String selection = TextPanePanel.area.getSelectedText();
					doc.remove(offset, selectedLength);
					doc.insertString(offset, selection, style);

				} else {
					int position = TextPanePanel.area.getCaretPosition();
					doc.insertString(position, " ", style);
					
				}
			} catch (BadLocationException e1) {
				e1.printStackTrace();
			}
		}
	}

	private boolean isEmptyArea() {
		return TextPanePanel.area.getText().length() == 0;
	}

	private void resetTextPane() {
		changeTextColor(Color.BLACK);
		TextPanePanel.area.setText("");
		StyleConstants.setFontFamily(style, "Arial");
		StyleConstants.setFontSize(style, 16);
		try {
			doc.insertString(0, " ", style);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private int askUser(String question) {
		return JOptionPane.showConfirmDialog(frame, question);
	}

	private boolean affirmativeAnswer(int answer) {
		return answer == JOptionPane.OK_OPTION;
	}

	private boolean negativeAnswer(int answer) {
		return answer == JOptionPane.NO_OPTION;
	}
}
