import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
	
	private static final int LETTERS_COUNT = 'z' - 'a' + 1;
	private static final char INVALID_CHARACTER = ' ';
	private static final String NO_MISSING_LETTERS = "NULL";
	
	public static void main(String[] args) throws IOException {
		
		try(BufferedReader bufferedReader = new BufferedReader(new FileReader(args[0]))) {
			
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				System.out.println(analyzePotentialPangram(line));
			}
			
		}
		
	}

	private static String analyzePotentialPangram(String phrase) {
		boolean[] letters = findLetters(phrase);
		return findMissingLetters(letters);
	}

	private static boolean[] findLetters(String phrase) {
		boolean[] seen = new boolean[LETTERS_COUNT];
		for (int i = 0; i < phrase.length(); i++) {
			char normalizedChar = normalizeChar(phrase.charAt(i));
			if (normalizedChar != INVALID_CHARACTER) {
				seen[getIndexFromChar(normalizedChar)] = true;
			}
		}
		return seen;
	}

	private static String findMissingLetters(boolean[] letters) {
		StringBuilder missingLetters = new StringBuilder();
		for (int i = 0; i < letters.length; i++) {
			if (!letters[i]) {
				missingLetters.append(getCharFromIndex(i));
			}
		}
		return (missingLetters.length() != 0) ? missingLetters.toString() : NO_MISSING_LETTERS;
	}
	
	private static char normalizeChar(char character) {
		if (character >= 'a' && character <= 'z') {
			return character;
		} else if (character >= 'A' && character <= 'Z') {
			return (char) (character + ('a' - 'A'));
		} else {
			return INVALID_CHARACTER;
		}
	}
	
	private static int getIndexFromChar(char character) {
		return character - 'a';
	}
	
	private static char getCharFromIndex(int index) {
		return (char) (index + 'a');
	}
	
}
