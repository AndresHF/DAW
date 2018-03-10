package reapasoEva2;

import java.util.Arrays;

public class StaticArray {

	public static String ordena(String input){

		String sorted = "";
		Object[] result = Arrays.stream(input.split(" ")).map(i -> new StringBuilder().append(sort(i.toCharArray())).append(' ').toString()).toArray();
		for(Object sort : result){
			sorted += sort.toString();
		}
		return sorted;
	}
	
	private static char[] sort(char[] input){

		for(int i = 0; i < input.length; i++){
			for(int j = i + 1; j < input.length; j++){
				
				if(input[i] > input[j] && (input[j] != ',' && input[i] != ',')){
					char aux = input[i];
					input[i] = input[j];
					input[j] = aux;
				}
			}
		}
		return input;
	}
	
}












