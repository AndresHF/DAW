package converter;

import java.util.ArrayList;

public class Converter {

	private String number;
	private String baseA;
	private String baseB;
	private String fractional = "";

	
	public Converter(String number, String baseA, String baseB) {
		this.number = number;
		this.baseA = baseA;
		this.baseB = baseB;

	}
	
	public String changeToBaseN() {
		
		if(!baseA.equals("10") && !baseB.equals("10")) number = changeToBase10();
		
		if(baseB.equals("10")) return changeToBase10();
		 
		ArrayList<Integer> moduleResult = getModuleArray(number, Integer.parseInt(baseB));
		
		return reverseResult(moduleResult);
	}
	
	private String changeToBase10() {

		int[]num = getNumberSecuence(number);
		num = reverseArray(num);

		return getBase10Value(num, Double.parseDouble((baseA)));
	}


	
	private String formatNumber(){
		for(int i = 0; i < number.length(); i++){
			if(number.charAt(i) == '.'){
				number = number.substring(0, i);
				break;
			}
		}
		return number;
	}
	
	private int[] getNumberSecuence(String number){
		
		int[] numSecuence = new int[number.length()];
		
		for (int i = 0; i < numSecuence.length; i++) {
			if(number.charAt(i) == '.'){
				fractional = number.substring(i + 1, number.length());
				break;
			}
			if (number.charAt(i) == 'A' || 
				number.charAt(i) == 'B' || 
				number.charAt(i) == 'C' || 
				number.charAt(i) == 'D' || 
				number.charAt(i) == 'E' || 
				number.charAt(i) == 'F') numSecuence[i] = (int) number.charAt(i) - 55;
				
			else numSecuence[i] = Integer.parseInt(""+number.charAt(i));
			
		}
		return numSecuence;
	}
	
	private int[] reverseArray(int[] array){
		
		int pivot = array.length - 1;
		for(int i = 0; i < array.length; i++){
			int temp = array[i];
			array[i] = array[pivot];
			array[pivot] = temp;
			pivot --;
			if(pivot <= i || pivot == 1) break;
		}

		return array;
	}
	
	private String getBase10Value(int[] num, double base){
		
		int value = 0;
		for(int i = 0; i < num.length; i++){
			value += num[i] * Math.pow((double) base, i);
		}
		return "" + value;
	}
	
	private ArrayList<Integer> getModuleArray(String number, int base){
		ArrayList<Integer> result = new ArrayList<Integer>();
		int num = Integer.parseInt(number);
		if(num < base) 
			result.add(num);
		while(num >= base){
			result.add(num % base);
			num /= base;
			if(num < base) result.add(num);
		}

		return result;
	}
	private String reverseResult(ArrayList<Integer> result){
		
		String finalResult = "";
		for(int i = result.size() - 1; i >= 0; i--){
			
			if(result.get(i) >= 10 && result.get(i) <= 15) 
				finalResult += (char) (result.get(i) + 55);
			
			else 
				finalResult += result.get(i);
		
		}
		
		return finalResult;
	}
	
	public boolean findError(String number, String baseA, String baseB){
		int[] numsToCheck = getNumberSecuence(number);
		this.number = formatNumber();
		if(baseA.equals(baseB)) return true;
		for(int i : numsToCheck){
			if(i > Integer.parseInt(baseA) - 1) return true;
		}
		return false;
	}

	public String getFractional() {
		return fractional;
	}
	
}




