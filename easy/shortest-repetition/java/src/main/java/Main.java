import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    
    public static void main(String[] args) throws IOException {
		
		try(BufferedReader bufferedReader = new BufferedReader(new FileReader(args[0]))) {
			
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				
			    System.out.println(findSmallestPeriod(line));
				
			}
			
		}
		
	}
    
    private static int findSmallestPeriod(String string) {
        for (int i = 1; i < string.length(); i++) {
            if (string.length() % i == 0) {
                String substring = string.substring(0, i);
                boolean isRepetition = string.matches("(" + substring + ")+");
                if (isRepetition) {
                    return i;
                }
            }
        }
        return string.length();
    }
    
}
