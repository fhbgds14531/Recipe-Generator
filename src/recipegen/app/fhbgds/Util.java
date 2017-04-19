package recipegen.app.fhbgds;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;

public class Util {

	static SecureRandom rand = new SecureRandom();

	public static Object getRandomObjectFromList(List<Object> list) {
		return list.get(rand.nextInt(list.size()));
	}
	
	public static String getRandomTimeLong(){
		int hours = rand.nextInt(3) + 1;
		String time = "" + hours;
		if(rand.nextBoolean()){
			time += " and 1/2";
		}
		if(time.contentEquals("1")){
			time += " hour";
		}else{
			time += " hours";
		}
		return time;
	}

	public static int[] toFraction(BigDecimal x) {
		String[] parts = x.toString().split("\\.");
		BigDecimal denominator = BigDecimal.TEN.pow(parts[1].length());
		BigDecimal numerator = (new BigDecimal(parts[0]).multiply(denominator)).add(new BigDecimal(parts[1]));
		return reduceFraction(numerator.intValue(), denominator.intValue());
	}

	static int[] reduceFraction(int numerator, int denominator) {
		int gcd = BigInteger.valueOf(numerator).gcd(BigInteger.valueOf(denominator)).intValue(); // Greatest Common Denominator
		int[] rf = { numerator / gcd, denominator / gcd };
		return rf;
	}
	
	public static String capitalizeFirstLetter(String s){
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}
	
	public static String capitalizeFirstLetterOfEveryWord(String s){
		String[] sa = s.split(" ");
		String finalString = "";
		for(String s1 : sa){
			s1 = s1.substring(0, 1).toUpperCase() + s1.substring(1);
			finalString += s1 + " ";
		}
		return finalString.trim();
	}
}
