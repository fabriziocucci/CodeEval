import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
	
	private static final String VALID_SUDOKU_ANSWER = "True";
	private static final String INVALID_SUDOKU_ANSWER = "False";
	
	public static void main(String[] args) throws IOException {
		
		try(BufferedReader bufferedReader = new BufferedReader(new FileReader(args[0]))) {
			
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				
				String[] arguments = line.split(";");
				
				int sudokuSize = Integer.parseInt(arguments[0]);
				int[][] sudokuElements = parseSudokuElements(arguments[1].split(","), sudokuSize);
				
				System.out.println(isSudokuValid(sudokuSize, sudokuElements) ? VALID_SUDOKU_ANSWER : INVALID_SUDOKU_ANSWER);
				
			}
			
		}
		
	}

	private static int[][] parseSudokuElements(String[] sudokuElementsAsStrings, int sudokuSize) {
		int[][] sudokuElements = new int[sudokuSize][sudokuSize];
		for (int i = 0; i < sudokuSize; i++) {
			for (int j = 0; j < sudokuSize; j++) {
				sudokuElements[i][j] = Integer.parseInt(sudokuElementsAsStrings[j + (i * sudokuSize)]);
			}
		}
		return sudokuElements;
	}
	
	private static boolean isSudokuValid(int sudokuSize, int[][] sudokuElements) {
		return areRowsValid(sudokuSize, sudokuElements) && areColumnsValid(sudokuSize, sudokuElements) && areGridsValid(sudokuSize, sudokuElements);
	}

	private static boolean areRowsValid(int sudokuSize, int[][] sudokuElements) {
		
		for (int i = 0; i < sudokuElements.length; i++) {
			
			boolean[] numbers = new boolean[sudokuSize];
			for (int j = 0; j < sudokuElements.length; j++) {
				if (numbers[sudokuElements[i][j] - 1]) {
					return false;
				} else {
					numbers[sudokuElements[i][j] - 1] = true;
				}
			}
			
		}
		
		return true;
	}
	
	private static boolean areColumnsValid(int sudokuSize, int[][] sudokuElements) {

		for (int i = 0; i < sudokuElements.length; i++) {
			
			boolean[] numbers = new boolean[sudokuSize];
			for (int j = 0; j < sudokuElements.length; j++) {
				if (numbers[sudokuElements[j][i] - 1]) {
					return false;
				} else {
					numbers[sudokuElements[j][i] - 1] = true;
				}
			}
			
		}
		
		return true;
		
	}

	private static boolean areGridsValid(int sudokuSize, int[][] sudokuElements) {
		
		int gridCount = (int) Math.sqrt(sudokuSize);
		
		for (int i = 0; i < gridCount; i++) {			
			for (int j = 0; j < gridCount; j++) {
				if (!isGridValid(sudokuSize, sudokuElements, i, j)) {
					return false;
				}
			}
		}
		
		return true;
	}

	private static boolean isGridValid(int sudokuSize, int[][] sudokuElements, int gridRow, int gridColumn) {
		
		int gridSize = (int) Math.sqrt(sudokuSize);
		
		boolean[] numbers = new boolean[sudokuSize];
		for (int i = gridSize * gridRow; i < (gridSize * gridRow) + gridSize; i++) {
			for (int j = gridSize * gridColumn; j < (gridSize * gridColumn) + gridSize; j++) {
				if (numbers[sudokuElements[i][j] - 1]) {
					return false;
				} else {
					numbers[sudokuElements[i][j] - 1] = true;
				}
			}
		}
		
		return true;
	}
	
}
