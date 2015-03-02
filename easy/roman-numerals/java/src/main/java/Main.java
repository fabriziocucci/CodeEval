import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
	
	private static final int[] NUMBERS = new int[] { 0, 1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000 };
	
	private static final Map<Integer, String> NUMBER_TO_ROMAN = new HashMap<>();
	static {
		NUMBER_TO_ROMAN.put(0, "");
		NUMBER_TO_ROMAN.put(1, "I");
		NUMBER_TO_ROMAN.put(4, "IV");
		NUMBER_TO_ROMAN.put(5, "V");
		NUMBER_TO_ROMAN.put(9, "IX");
		NUMBER_TO_ROMAN.put(10, "X");
		NUMBER_TO_ROMAN.put(40, "XL");
		NUMBER_TO_ROMAN.put(50, "L");
		NUMBER_TO_ROMAN.put(90, "XC");
		NUMBER_TO_ROMAN.put(100, "C");
		NUMBER_TO_ROMAN.put(400, "CD");
		NUMBER_TO_ROMAN.put(500, "D");
		NUMBER_TO_ROMAN.put(900, "CM");
		NUMBER_TO_ROMAN.put(1000, "M");
	}
	
	public static void main(String[] args) throws IOException {
		
		try(BufferedReader bufferedReader = new BufferedReader(new FileReader(args[0]))) {
			
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				
				String numberAsString = line.trim();
				char[] numberAsCharArray = numberAsString.toCharArray();
				
				StringBuilder romanNumber = new StringBuilder();
				
				int digit, number, numberIndex;
				for (int i = 0, n = numberAsCharArray.length; i < n; i++) {
					
					digit = Character.getNumericValue(numberAsCharArray[i]);
					number = digit * (int) Math.pow(10, n - i - 1);
				
					do {
						numberIndex = Arrays.binarySearch(NUMBERS, number);
						if (numberIndex < 0) {
							romanNumber.append(NUMBER_TO_ROMAN.get(NUMBERS[-numberIndex-2]));
							number -= NUMBERS[-numberIndex-2];
						} else {
							romanNumber.append(NUMBER_TO_ROMAN.get(NUMBERS[numberIndex]));
							number -= NUMBERS[numberIndex];
						}
					} while (number > 0);
					
				}
				
				System.out.println(romanNumber.toString());
				
			}
			
		}
		
	}
	
}
