import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {

		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(args[0]))) {

			String line;
			while ((line = bufferedReader.readLine()) != null) {

				String[] input = line.split(";");
				int days = Integer.parseInt(input[0]);
				int[] gainsAndLosses = parseGainsAndLosses(input[1]);

				int maxRangeSum = calculateMaxRangeSum(days, gainsAndLosses);
				System.out.println(maxRangeSum);

			}

		}

	}

	private static int[] parseGainsAndLosses(String gainsAndLossesAsString) {
		String[] gainsAndLossesAsArray = gainsAndLossesAsString.split("\\s");
		int[] gainsAndLosses = new int[gainsAndLossesAsArray.length];
		for (int i = 0; i < gainsAndLossesAsArray.length; i++) {
			gainsAndLosses[i] = Integer.parseInt(gainsAndLossesAsArray[i]);
		}
		return gainsAndLosses;
	}

	private static int calculateMaxRangeSum(int days, int[] gainsAndLosses) {
		int maxRangeSum = 0, sum = 0;
		for (int i = 0; i < gainsAndLosses.length - days + 1; i++) {
			for (int j = 0; j < days; j++) {
				sum += gainsAndLosses[i + j];
			}
			if (sum > maxRangeSum) {
				maxRangeSum = sum;
			}
			sum = 0;
		}
		return maxRangeSum;
	}

}
