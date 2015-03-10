import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {

	private static final int MIN_VALUE = 0;
	private static final int MAX_VALUE = 99;
	private static final int NOT_SEEN = MIN_VALUE - 1;

	public static void main(String[] args) throws IOException {

		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(args[0]))) {

			String line;
			while ((line = bufferedReader.readLine()) != null) {

				String[] integersAsStrings = line.split("\\s");

				int[] integers = parseIntegers(integersAsStrings);
				System.out.println(findCycle(integers));

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

	private static String findCycle(int[] integers) {

		int[] seen = new int[MAX_VALUE + 1];
		Arrays.fill(seen, NOT_SEEN);

		String cycle = "";
		for (int i = 0; i < integers.length; i++) {
			if (seen[integers[i]] == NOT_SEEN) {
				seen[integers[i]] = i;
			} else {
				cycle = getCycle(integers, seen[integers[i]], i - 1);
				break;
			}
		}

		return cycle;
	}

	private static String getCycle(int[] integers, int startCycle, int endCycle) {
		StringBuilder cycle = new StringBuilder();
		for (int i = startCycle; i <= endCycle; i++) {
			cycle.append(integers[i]).append(' ');
		}
		cycle.setLength(cycle.length() - 1);
		return cycle.toString();
	}

}
