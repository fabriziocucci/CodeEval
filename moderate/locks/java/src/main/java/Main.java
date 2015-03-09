import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
	
	private static final boolean LOCKED = true;
	
	public static void main(String[] args) throws IOException {
		
		try(BufferedReader bufferedReader = new BufferedReader(new FileReader(args[0]))) {
			
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				
				String[] arguments = line.trim().split("\\s");
				int N = Integer.parseInt(arguments[0]);
				int M = Integer.parseInt(arguments[1]);
				
				System.out.println(calculateDoorsLeftUnlocked(N, M));
				
			}
			
		}
		
	}

	private static int calculateDoorsLeftUnlocked(int numberOfDoors, int numberOfIterations) {
		
		boolean[] doors = new boolean[numberOfDoors];
		
		for (int i = 0; i < numberOfIterations - 1; i++) {
			firstBeat(doors);
			secondBeat(doors);
		}
		
		lastIteration(doors);
		
		return countDoorsLeftUnlocked(doors);
	}

	private static void firstBeat(boolean[] doors) {
		for (int i = 1; i < doors.length; i = i + 2) {
			doors[i] = LOCKED;
		}
	}
	
	private static void secondBeat(boolean[] doors) {
		for (int i = 2; i < doors.length; i = i + 3) {
			doors[i] = !doors[i];
		}
	}
	
	private static void lastIteration(boolean[] doors) {
		doors[doors.length - 1] = !doors[doors.length - 1];
	}
	
	private static int countDoorsLeftUnlocked(boolean[] doors) {
		int doorsLeftUnlocked = 0;
		for (int i = 0; i < doors.length; i++) {
			if (!doors[i]) {
				doorsLeftUnlocked++;
			}
		}
		return doorsLeftUnlocked;
	}
	
}
