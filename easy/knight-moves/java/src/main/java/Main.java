import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Main {
	
	public static void main(String[] args) throws IOException {
		
		try(BufferedReader bufferedReader = new BufferedReader(new FileReader(args[0]))) {
			
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				
				int column = convertColumnToInteger(line.charAt(0));
				int row = Character.getNumericValue(line.charAt(1));
				
				List<String> possiblePositions = getPossiblePositions(row, column);
				System.out.println(formatPossiblePositions(possiblePositions));
				
			}
			
		}
		
	}

	private static int convertColumnToInteger(char columnAsChar) {
		return Character.getNumericValue(columnAsChar - '0');
	}
	
	private static char convertIntegerToColumn(int columnAsInt) {
		return (char) (columnAsInt - 1 + 'a');
	}
	
	private static List<String> getPossiblePositions(int row, int column) {
		
		List<String> possiblePositions = new LinkedList<>();
		
		if (column - 2 > 0) {
			if (row - 1 > 0) possiblePositions.add(convertIntegerToColumn(column - 2) + Integer.toString(row - 1));
			if (row + 1 < 9) possiblePositions.add(convertIntegerToColumn(column - 2) + Integer.toString(row + 1));
		}
		
		if (row - 2 > 0 && column - 1 > 0) possiblePositions.add(convertIntegerToColumn(column - 1) + Integer.toString(row - 2));
		if (row + 2 < 9 && column - 1 > 0) possiblePositions.add(convertIntegerToColumn(column - 1) + Integer.toString(row + 2));
		
		if (row - 2 > 0 && column + 1 < 9) possiblePositions.add(convertIntegerToColumn(column + 1) + Integer.toString(row - 2));
		if (row + 2 < 9 && column + 1 < 9) possiblePositions.add(convertIntegerToColumn(column + 1) + Integer.toString(row + 2));
		
		if (column + 2 < 9) {
			if (row - 1 > 0) possiblePositions.add(convertIntegerToColumn(column + 2) + Integer.toString(row - 1));
			if (row + 1 < 9) possiblePositions.add(convertIntegerToColumn(column + 2) + Integer.toString(row + 1));
		}
		
		return possiblePositions;
	}
	
	private static String formatPossiblePositions(List<String> possiblePositions) {
		StringBuilder stringBuilder = new StringBuilder();
		Iterator<String> iterator = possiblePositions.iterator();
		if (iterator.hasNext()) {
			stringBuilder.append(iterator.next());
			while (iterator.hasNext()) {
				stringBuilder.append(' ').append(iterator.next());
			}
		}
		return stringBuilder.toString();
	}
	
}
