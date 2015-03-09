import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
	
	public static void main(String[] args) throws IOException {
		
		try(BufferedReader bufferedReader = new BufferedReader(new FileReader(args[0]))) {
			
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				
				String[] arguments = line.trim().split(",");
				
				int[] integers = parseIntegers(arguments);
				System.out.println(countWaysToZeroSum(integers));
				
			}
			
		}
		
	}

	private static int[] parseIntegers(String[] integersAsStrings) {
		int[] integers = new int[integersAsStrings.length];
		for (int i = 0; i < integersAsStrings.length; i++) {
			integers[i] = Integer.parseInt(integersAsStrings[i]);
		}
		return integers;
	}
	
	private static int countWaysToZeroSum(int[] integers) {
		int count = 0;
		for (int i = 0; i < integers.length; i++) {
			for (int j = i + 1; j < integers.length; j++) {
				for (int k = j + 1; k < integers.length; k++) {
					for (int l = k + 1; l < integers.length; l++) {
						if (integers[i] + integers[j] + integers[k] + integers[l] == 0) {
							count++;
						}
					}
				}
			}
		}
		return count;
	}
	
}
