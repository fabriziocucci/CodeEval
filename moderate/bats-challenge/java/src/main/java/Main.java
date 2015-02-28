import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {

	private static int MIN_DISTANCE_FROM_BATS_AND_BUILDINGS = 6;
	
	public static void main(String[] args) throws IOException {
		
		try (BufferedReader buffer = new BufferedReader(new FileReader(args[0]))) {
			
			String line;
			while ((line = buffer.readLine()) != null) {
				String[] arguments = line.trim().split("\\s");
				
				int wireLength = Integer.parseInt(arguments[0]);
				int minBatsDistance = Integer.parseInt(arguments[1]);
				int batsCount = Integer.parseInt(arguments[2]);
				
				int[] batsIndexes = new int[batsCount];
				for (int i = 0; i < batsCount; i++) {
					batsIndexes[i] = Integer.parseInt(arguments[3 + i]);
				}
				
				Arrays.sort(batsIndexes);
				
				int firstAvailableIndex = MIN_DISTANCE_FROM_BATS_AND_BUILDINGS;
				int lastAvailableIndex = wireLength - MIN_DISTANCE_FROM_BATS_AND_BUILDINGS;
				
				int additionalBatsCount = 0;
				
				if (batsCount > 0) {
					additionalBatsCount += countAdditionalBatsBetweenFirstAvailableIndexAndFirstBat(firstAvailableIndex, batsIndexes[0], minBatsDistance);
					additionalBatsCount += countAdditionalBatsBetweenBats(batsIndexes, minBatsDistance);
					additionalBatsCount += countAdditionalBatsBetweenLastBatAndLastAvailableIndex(batsIndexes[batsCount - 1], lastAvailableIndex, minBatsDistance);
				} else {
					additionalBatsCount += countAdditionalBatsBetweenFirstAvailableIndexAndLastAvailableIndex(firstAvailableIndex, lastAvailableIndex, minBatsDistance);
				}
				
				System.out.println(additionalBatsCount);
			}
			
		}
		
	}
	
	private static int countAdditionalBatsBetweenFirstAvailableIndexAndLastAvailableIndex(int firstAvailableSpace, int lastAvailableSpace, int minBatsDistance) {
		return ( (lastAvailableSpace - firstAvailableSpace) / minBatsDistance ) + 1;
	}
	
	private static int countAdditionalBatsBetweenFirstAvailableIndexAndFirstBat(int firstAvailableSpace, int firstBatIndex, int minBatsDistance) {
		return (firstBatIndex - firstAvailableSpace) / minBatsDistance;
	}
	
	private static int countAdditionalBatsBetweenLastBatAndLastAvailableIndex(int lastBatIndex, int lastAvailableSpace, int minBatsDistance) {
		return (lastAvailableSpace - lastBatIndex) / minBatsDistance;
	}
	
	private static int countAdditionalBatsBetweenBats(int[] batsIndexes, int minBatsDistance) {
		
		int additionalBatsBetweenBats = 0;
		
		for (int i = 0; i < batsIndexes.length - 1; i++) {
			int batIndex1 = batsIndexes[i];
			int batIndex2 = batsIndexes[i + 1];
			if (batIndex2 - batIndex1 - 1 > 0) {
				additionalBatsBetweenBats += ((batIndex2 - batIndex1) / minBatsDistance) - 1;
			}
		}
		
		return additionalBatsBetweenBats;
	}
	
}
