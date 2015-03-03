import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
	
	public static void main(String[] args) throws IOException {
		
		try(BufferedReader bufferedReader = new BufferedReader(new FileReader(args[0]))) {
			
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				
				String[] matrixElements = line.trim().split("\\s");
				int n = (int) Math.sqrt(matrixElements.length);
				
				char[][] matrix = buildMatrix(matrixElements, n);
				char[][] rotatedMatrix = rotateMatrix(matrix, n);
				printRotatedMatrix(rotatedMatrix, n);
				
			}
			
		}
		
	}
	
	private static char[][] buildMatrix(String[] matrixElements, int n) {
		char[][] matrix = new char[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				matrix[i][j] = matrixElements[(i * n) + j].charAt(0);
			}
		}
		return matrix;
	}
	
	private static char[][] rotateMatrix(char[][] matrix, int n) {
		char[][] rotatedMatrix = new char[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				rotatedMatrix[i][j] = matrix[n - j - 1][i];
			}
		}
		return rotatedMatrix;
	}
	
	private static void printRotatedMatrix(char[][] rotatedMatrix, int n) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(rotatedMatrix[i][j]);
				if (i != n - 1 || j != n - 1) {
					System.out.print(' ');
				}
			}
		}
		System.out.println();
	}
	
}
