package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import command.*;

public class TriggerPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private GridLayout grid;
	public static ArrayList<ButtonPattern> buttons;
	private VowelCounter vowelCounter;
	private AlphabetCounter alphabetCounter;
	private CountSpecificWord wordCounter;
	private TimesSubSequence subSequence;
	private RemoveWord removeWord;
	private ConcatText concatText;
	private MirrorReverse mirrorReverse;
	private UpperCaseWord upperWord;
	private FirstToUpper firstUpper;
	private EncodeTxt encodeText;
	private DecodeTxt decodeText;
	private LongestWord longestWord;
	
	public TriggerPanel(){
		
		buttons = new ArrayList<ButtonPattern>();
		
		grid = new GridLayout(10,2);
		setLayout(grid);
		setBackground(Color.LIGHT_GRAY);
		
		vowelCounter = new VowelCounter("Vowels counted\n---------------------------", "vowelCounter","Vowels");
		add(vowelCounter);
		
		alphabetCounter = new AlphabetCounter("Letters counted\n---------------------------", "alphabetCounter", "Letters");
		add(alphabetCounter);
		
		wordCounter = new CountSpecificWord("Specific word\n---------------------------","wordCounter","Word");
		add(wordCounter);
		
		subSequence = new TimesSubSequence("Subsequences to find\n---------------------------", "subSequence","Sequence");
		add(subSequence);
		
		removeWord = new RemoveWord("Remove word\n---------------------------","removeWord","Remove word");
		add(removeWord);
		
		concatText = new ConcatText("Text concated\n---------------------------","concatText","Concat text");
		add(concatText);
		
		mirrorReverse = new MirrorReverse("Mirror-Reverse text\n---------------------------","mirrorReverse","Reverse text");
		add(mirrorReverse);
		
		upperWord = new UpperCaseWord("Word to upper case\n---------------------------", "upperWord", "Upper word");
		add(upperWord);
		
		firstUpper = new FirstToUpper("First character to upper case\n---------------------------", "firstUpper", "First upper");
		add(firstUpper);
		
		longestWord = new LongestWord("Longest word\n---------------------------", "longestWord", "Longest word");
		add(longestWord);
		
		encodeText = new EncodeTxt("Text encoder\n---------------------------", "encodeText", "Encode text");
		add(encodeText);
		
		decodeText = new DecodeTxt("Text decoder\n---------------------------", "decodeText", "Decode text");
		add(decodeText);
		

		
		buttons.add(vowelCounter);
		buttons.add(alphabetCounter);
		buttons.add(wordCounter);
		buttons.add(subSequence);
		buttons.add(removeWord);
		buttons.add(concatText);
		buttons.add(mirrorReverse);
		buttons.add(upperWord);
		buttons.add(firstUpper);
		buttons.add(encodeText);
		buttons.add(decodeText);
		buttons.add(longestWord);
	}


}








