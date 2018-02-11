package controller;

import javax.swing.JTextArea;

public class Utilities {
	public static String separators = "!¡¿?,.;:[]{}<>/*%+@=";

	public Utilities() {

	}

	public static void printPosiciones(String st, char c) {

		for (int i = -1; i < st.length(); i++) {
			if (i + 1 < st.length()) {
				System.out.println(st.indexOf(c, i + 1));
				i = st.indexOf(c, i + 1);
				if (i == -1)
					break;
			}

		}
	}

	public static int countVowels(String s) {
		int vowels = 0;
		String stVowels = "aeiou";
		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j < stVowels.length(); j++) {
				if (Character.toLowerCase(s.charAt(i)) == stVowels.charAt(j)) {
					vowels++;
					break;
				}
			}
		}
		return vowels;
	}

	public static int[] countEachVowel(String s) {

		int[] vowels = new int[5];
		String stVowels = "aeiou";
		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j < stVowels.length(); j++) {
				if (Character.toLowerCase(s.charAt(i)) == stVowels.charAt(j)) {
					vowels[j]++;
					break;
				}
			}
		}
		return vowels;
	}

	public static void printVowelArr(int[] arr, JTextArea area) {

		char[] vowels = { 'A', 'E', 'I', 'O', 'U' };
		int counter = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > 0) {
				area.setText(area.getText() +(vowels[i] + ": " + arr[i]) + "\n");
				counter += arr[i];
			}
		}
		area.setText(area.getText() +("TOTAL: " + counter + " vowels."));
	}

	public static int[] countAlphabet(String s) {
		
		String dicc = "abcdefghijklmnopqrstuvwxyz";
		int[] alphabet = new int[dicc.length()];
		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j < dicc.length(); j++) {
				if (Character.toLowerCase(s.charAt(i)) == dicc.charAt(j)) {
					alphabet[j]++;
					break;
				}
			}
		}
		return alphabet;
	}

	public static void printAlphabetArr(int[] arr, JTextArea area) {

		int counter = 0;
		for (int i = 0; i < arr.length; i++) {
			
			area.setText(area.getText() + (char) (i + 65) + ": " + arr[i] + "\n");
			counter += arr[i];
		}
		area.setText(area.getText() + "TOTAL: " + counter + " letters.");
	}

	public static int countSpecificWord(String s, String match) {

		int counter = 0;
		String[] words = trimAll(s, separators);
		
		for(int i = 0; i < words.length; i++){
			if(words[i].equalsIgnoreCase(match)) counter ++;
		}
		
		return counter;
	}
	
	private static String[] trimAll(String s, String separators){
		for(int i = 0; i < separators.length(); i++){
			s = s.replace(separators.charAt(i), ' ');
			s = s.trim();
		}
		s = s.replaceAll("\\s{2,}", " ");
		s = s.replace('\t', ' ');
		s.replaceAll("[\\p{Cc}\\p{Cf}\\p{Co}\\p{Cn}]\\p{C}", "?");
		return s.split(" ");
	}
	
	public static void printResult(String pattern, int result, JTextArea area){
		
		area.setText(String.format(pattern,result));
	}
	
	public static int countWords(String s){
		return trimAll(s, separators).length;
		
	}

	public static String firstToUpperCase(String s) throws Exception{
		String processed = firstUpper(s.substring(0,1));
		s = s.substring(1);
		boolean readyToUpper = false;
		int pivot = 0;
		
		for(int i = 0; i < s.length(); i++){
			if(s.charAt(i) == '.') readyToUpper = true;
			if(readyToUpper){
				pivot = i;
				if(containSeparators(s.charAt(i),separators)){
					processed += firstUpper(s.substring(pivot, i+1));
					readyToUpper = !readyToUpper;
				}else{
					processed += s.charAt(i);
				}
			}else{
				processed += s.charAt(i);
			}	
		}
		return processed;
	}
	
	private static boolean containSeparators(char c, String separators){
		separators += " ";
		int nonPrintable = c;
		int sepCounter = 0;
		for(int j = 0; j < separators.length(); j++){
			if(c == separators.charAt(j) || nonPrintable <= 31){
				sepCounter ++;
			}
		}
		return sepCounter == 0;
	}
	
	private static String firstUpper(String s){
		return s.substring(0,1).toUpperCase() + s.substring(1);
	}
	
	public static int timesSubSequence(String s, String sequence){

		int counter = 0;
		int index = 0;
		s = " " + s;
		while(s.indexOf(sequence,index) > 0){
			index = s.indexOf(sequence,index) + sequence.length() - 1;
			counter++;
		}
		
		return counter;
	}
	
	public static String removeWord(String s, String word){
		
		int index = 0;
		s = " " + s;
		while(s.indexOf(word, index) > 0){
			s = s.substring(0, s.indexOf(word)) + s.substring(s.indexOf(word)+word.length());
		}
		s = s.substring(1);
		return s;
	}
	
	public static String concatText(String s, String text, int position){
		String concat = s.substring(0, position) + " " + text + " " + s.substring(position, s.length());
		if(concat.charAt(0) == ' ') concat = concat.substring(1);
		return concat;
	}

	public static String reverseStr(String s){
		
		String reversed = "";
		for(int i = s.length() - 1; i >= 0; i--){
			reversed += s.charAt(i);
		}
		return reversed;
	}
	
	public static String mirrorStr(String s){
		return s + reverseStr(s).substring(1);
	}
	

	
	public static String upperCaseWord(String input, String upper){
		return input.replaceAll("(?i)"+upper, upper.toUpperCase());
	}


	public static String encryptText(String input, int keyCode){
		
		String encrypted = "";

		for(int i = 0; i < input.length(); i++){
			int code = input.charAt(i) - keyCode;
			encrypted += (char) code;
		}
		
		return encrypted;
	}
	public static String decodeText(String input, int keyCode){
		
		String decoded = "";
		for(int i = 0; i < input.length(); i++){
			int code = input.charAt(i) + keyCode;
			decoded += (char) code;
		}
		return decoded;
	}
	
	public static String longestWord(String input) throws Exception{
		String[] words = trimAll(input, separators);
		int record = 0;
		int pos = 0;

		for(int i = 0; i < words.length; i++){
			if(words[i].length() > record){
				record = words[i].length();
				pos = i;
			} 
		}

		if(singleLongest(words,record)) return words[pos];
		else return "Multiple words with longest length.\n\nTotal characters: " + words[pos].length() + " for all the longest words.";
	}
	
	private static boolean singleLongest(String[] words, int record){
		
		int counter = 0;
		for(int i = 0; i < words.length; i++){
			if(words[i].length() == record) counter ++;
			for(int j = i + 1; j < words.length; j++){
				if(words[i].equalsIgnoreCase(words[j])) words[j] = "";
			}
			
		}
		return counter == 1;
	}
}
