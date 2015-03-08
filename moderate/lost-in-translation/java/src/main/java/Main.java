import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
	
	private static final String ENCODED_PHRASE_1 = "rbc vjnmkf kd yxyqci na rbc zjkfoscdd ew rbc ujllmcp";
	private static final String ENCODED_PHRASE_2 = "tc rbkso rbyr ejp mysljylc kd kxveddknmc re jsicpdrysi";
	private static final String ENCODED_PHRASE_3 = "de kr kd eoya kw aej icfkici re zjkr";
	
	private static final String DECODED_PHRASE_1 = "the public is amazed by the quickness of the juggler";
	private static final String DECODED_PHRASE_2 = "we think that our language is impossible to understand";
	private static final String DECODED_PHRASE_3 = "so it is okay if you decided to quit";
	
	private static final Map<String, String> EXAMPLES = new HashMap<String, String>();
	static {
		EXAMPLES.put(ENCODED_PHRASE_1, DECODED_PHRASE_1);
		EXAMPLES.put(ENCODED_PHRASE_2, DECODED_PHRASE_2);
		EXAMPLES.put(ENCODED_PHRASE_3, DECODED_PHRASE_3);
	}
	
	private static final char[] CODEL_MAPPING = new char['z' - 'a' + 1];
	static {
		CODEL_MAPPING['n' - 'a'] = 'b';
		CODEL_MAPPING['u' - 'a'] = 'j';
		CODEL_MAPPING['g' - 'a'] = 'v';
		populateCodelMapping();
		ensureCodelMappingCompleteness();
	}
	
	public static void main(String[] args) throws IOException {
		
		try(BufferedReader bufferedReader = new BufferedReader(new FileReader(args[0]))) {
			
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				System.out.println(decodePhrase(line));
			}
			
		}
		
	}
	
	private static void populateCodelMapping() {
		for (Map.Entry<String, String> entry : EXAMPLES.entrySet()) {
			String encodedString = entry.getKey().replaceAll("\\s", "");
			String decodedString = entry.getValue().replaceAll("\\s", "");
			for (int i = 0; i < encodedString.length(); i++) {
				CODEL_MAPPING[encodedString.charAt(i) - 'a'] = decodedString.charAt(i);
			}
		}
	}
	
	private static void ensureCodelMappingCompleteness() {
		int missingMappingIndex = verifyCodelMapping();
		if (missingMappingIndex != -1) {
			fixMissingMapping(missingMappingIndex);
		}
	}

	private static int verifyCodelMapping() {
		int missingMappingIndex = -1;
		for (int i = 0; i < CODEL_MAPPING.length; i++) {
			if (CODEL_MAPPING[i] == '\u0000') {
				if (missingMappingIndex == -1) {
					missingMappingIndex = i;
				} else {
					throw new IllegalStateException("There are more than one missing mappings!");
				}
			}
		}
		return missingMappingIndex;
	}
	
	private static void fixMissingMapping(int missingMappingIndex) {
		
		char[] allCharacters = new char['z' - 'a' + 1];
		for (int i = 0; i < allCharacters.length; i++) {
			allCharacters[i] = (char) ('a' + i);
		}
		
		for (int i = 0; i < CODEL_MAPPING.length; i++) {
			if (i != missingMappingIndex) {
				allCharacters[CODEL_MAPPING[i] - 'a'] = '\u0000';
			}
		}
		
		for (int i = 0; i < allCharacters.length; i++) {
			if (allCharacters[i] != '\u0000') {
				CODEL_MAPPING[missingMappingIndex] = allCharacters[i];
				break;
			}
		}
		
	}
	
	private static String decodePhrase(String encodedPhrase) {
		StringBuilder decodedPhrase = new StringBuilder();
		for (int i = 0; i < encodedPhrase.length(); i++) {
			if (encodedPhrase.charAt(i) != ' ') {
				decodedPhrase.append(CODEL_MAPPING[encodedPhrase.charAt(i) - 'a']);
			} else {
				decodedPhrase.append(encodedPhrase.charAt(i));
			}
		}
		return decodedPhrase.toString();
	}
	
}
