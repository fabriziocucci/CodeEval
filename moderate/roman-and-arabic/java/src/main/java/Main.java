import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
	
	private static final int[] ROMAN_TO_DECIMAL = new int['Z' - 'A' + 1];
	static {
		ROMAN_TO_DECIMAL[getIndexFromChar('I')] = 1;
		ROMAN_TO_DECIMAL[getIndexFromChar('V')] = 5;
		ROMAN_TO_DECIMAL[getIndexFromChar('X')] = 10;
		ROMAN_TO_DECIMAL[getIndexFromChar('L')] = 50;
		ROMAN_TO_DECIMAL[getIndexFromChar('C')] = 100;
		ROMAN_TO_DECIMAL[getIndexFromChar('D')] = 500;
		ROMAN_TO_DECIMAL[getIndexFromChar('M')] = 1000;
	}
	
	public static void main(String[] args) throws IOException {
		
		try(BufferedReader bufferedReader = new BufferedReader(new FileReader(args[0]))) {
			
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				System.out.println(convertAromaticNumberToDecimalValue(line));
			}
			
		}
		
	}

	private static int convertAromaticNumberToDecimalValue(String aromatic) {
		
		int decimal = 0;
		
		int currentArabic, currentRoman, previousRoman = 0;
		for (int i = aromatic.length() - 1; i >= 0; i -= 2) {
			currentRoman = ROMAN_TO_DECIMAL[getIndexFromChar(aromatic.charAt(i))];
			currentArabic = Character.getNumericValue(aromatic.charAt(i - 1));
			if (previousRoman > currentRoman) {
				decimal -= currentArabic * currentRoman;
			} else {
				decimal += currentArabic * currentRoman;
			}
			previousRoman = currentRoman;
		}
		
		return decimal;
	}
	
	private static int getIndexFromChar(char character) {
		return character - 'A';
	}
	
}
