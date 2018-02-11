package converter;

import java.util.ArrayList;

public class FractionalConverter {
	
	private final int MAX_DECIMALS = 5;
	private String number;
	private String baseA;
	private String baseB;
	
	public FractionalConverter(String number, String baseA, String baseB){
		this.number = number;
		this.baseA = baseA;
		this.baseB = baseB;
	}
	public String changeFNToBaseN(){
		
		int[] secuence = new int[number.length()];
		secuence = getFractionalSecuence(number);
		double base10Fractional = getBase10Fractional(secuence, Integer.parseInt(baseA));
		
		if(!baseA.equals("10") && !baseB.equals("10")){ 
			number = translateFractionalToBaseN(
					 getBaseNFractionalArray(base10Fractional, Integer.parseInt(baseB)));
		}
		if(baseB.equals("10")){
			number = "" + base10Fractional;
			return number.substring(2, number.length());
		} 
		if(baseA.equals("10")){
			double value = Double.parseDouble(number) / Math.pow(10, number.length());
			number = translateFractionalToBaseN(
					 getBaseNFractionalArray(value, Integer.parseInt(baseB)));
		}
		return number;
	}
	
	public int[] getFractionalSecuence(String number){
		
		int[] numSecuence = new int[number.length()];
		for (int i = 0; i < numSecuence.length; i++) {

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
	
	public double getBase10Fractional(int[] num, int base){
		
		double value = 0;
		for(int i = 0; i < num.length; i++) {
			value += num[i] * Math.pow((double) base, ((i + 1) * -1));
		}
		return value;
	}
	
	public ArrayList<Integer> getBaseNFractionalArray(double value, int base){
		
		ArrayList<Integer> fractionalValues = new ArrayList<Integer>();
		
		for(int i = 0; i <= MAX_DECIMALS; i++) {
			int digit = (int) (value * Integer.parseInt(baseB));
			fractionalValues.add(digit);
			value = (value * base) - digit;
			if(value == 0.0) break;
		}

		return fractionalValues;
	}
	
	public String translateFractionalToBaseN(ArrayList<Integer> fractionalValues){
		
		String finalFractionalResult = "";
		
		for(int i = 0; i < fractionalValues.size(); i++) {
			if(fractionalValues.get(i) >= 10 && fractionalValues.get(i) <= 15) 
				finalFractionalResult += (char) (fractionalValues.get(i) + 55);
			else 
				finalFractionalResult += fractionalValues.get(i);
		
		}
		
		return finalFractionalResult;
	}

}
