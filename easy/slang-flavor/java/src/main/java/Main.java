import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Main {
    
    private static final char DOT = '.';
    private static final char QUESTION_MARK = '?';
    private static final char EXLAMATION_MARK = '!';
    
    private static final String[] SLANG_PHRASES = new String[] {
        ", yeah!",
        ", this is crazy, I tell ya.",
        ", can U believe this?",
        ", eh?",
        ", aw yea.",
        ", yo.",
        "? No way!",
        ". Awesome!"
    };
    
    private static boolean shouldBeReplaced = false;
    
    private static int currentSlangIndex = SLANG_PHRASES.length - 1;
    
    public static void main(String[] args) throws IOException {
		
		try(BufferedReader bufferedReader = new BufferedReader(new FileReader(args[0]))) {
			
			String formalText;
			while ((formalText = bufferedReader.readLine()) != null) {
				System.out.println(replaceNonConsecutiveEndPunctuationMarks(formalText));			
			}
			
		}
		
	}
	
    private static String replaceNonConsecutiveEndPunctuationMarks(String formalText) {
    	StringBuilder informalText = new StringBuilder();
    	int currentIndex = 0, nextEndPunctuationMarkToReplace = -1;
		while ((nextEndPunctuationMarkToReplace = findNextEndPunctuationMarkToReplace(formalText, currentIndex)) != -1) {
			informalText.append(formalText.substring(currentIndex, nextEndPunctuationMarkToReplace));
		    informalText.append(getNextSlangPhrase());
		    currentIndex = nextEndPunctuationMarkToReplace + 1;
		}
		informalText.append(formalText.substring(currentIndex));
		return informalText.toString();
    }
    
    private static int findNextEndPunctuationMarkToReplace(String phrase, int currentIndex) {
	    for (int i = currentIndex; i < phrase.length(); i++) {
	        char currentChar = phrase.charAt(i);
	        if (isEndPunctuationMark(currentChar)) {
	            if (shouldBeReplaced) {
	            	shouldBeReplaced = false;
	                return i;
	            } else {
	            	shouldBeReplaced = true;
	            }
	        }
	    }
	    return -1;
	}
    
    private static boolean isEndPunctuationMark(char character) {
	    return (character == DOT || character == EXLAMATION_MARK || character == QUESTION_MARK);
	}
    
    private static String getNextSlangPhrase() {
	    return SLANG_PHRASES[++currentSlangIndex % SLANG_PHRASES.length];
	}
    
}
