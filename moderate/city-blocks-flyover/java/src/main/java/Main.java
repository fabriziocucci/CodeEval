import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;

public class Main {
	
	private static class Block {
		
		private final double x;
		private final double y;
		private final int width;
		private final int height;
		
		private Block(double x, double y, int width, int height) {
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
		}
		
		public double getSlopeOfTopLeftPoint() {
			return (x != 0) ? y / x : Double.POSITIVE_INFINITY;
		}
		
		public double getSlopeOfBottomRightPoint() {
			return (x + width != 0) ? (y - height) / (x + width) : Double.POSITIVE_INFINITY;
		}
		
	}
	
	private static class HelicopterPath {
		
		private final double destinationPointX;
		private final double destinationPointY;
		
		private HelicopterPath(double destinationPointX, double destinationPointY) {
			this.destinationPointX = destinationPointX;
			this.destinationPointY = destinationPointY;
		}
		
		public double getSlope() {
			return (destinationPointX != 0) ? destinationPointY / destinationPointX : Double.POSITIVE_INFINITY;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		
		try(BufferedReader bufferedReader = new BufferedReader(new FileReader(args[0]))) {
			
			String testcase;
			while ((testcase = bufferedReader.readLine()) != null) {
				
				String[] streetsAndAvenues = testcase.split("\\s");
				
				int[] streets = parseStreets(streetsAndAvenues[0]);
				int[] avenues = parseAvenues(streetsAndAvenues[1]);
				
				Collection<Block> blocks = getBlocks(streets, avenues);
				HelicopterPath helicopterPath = getHelicopterPath(streets, avenues);
				
				System.out.println(countBlocksTheHelicopterHasFlownOver(helicopterPath, blocks));
				
			}
			
		}
		
	}
	
	private static int[] parseStreets(String streetsAsString) {
		String[] streetsAsArray = streetsAsString.substring(1, streetsAsString.length() - 1).split(",");
		int[] streets = new int[streetsAsArray.length];
		for (int i = 0; i < streetsAsArray.length; i++) {
			streets[i] = Integer.parseInt(streetsAsArray[i]);
		}
		return streets;
	}
	
	private static int[] parseAvenues(String avenuesAsString) {
		String[] avenuesAsArray = avenuesAsString.substring(1, avenuesAsString.length() - 1).split(",");
		int[] avenues = new int[avenuesAsArray.length];
		for (int i = 0; i < avenuesAsArray.length; i++) {
			avenues[i] = Integer.parseInt(avenuesAsArray[i]);
		}
		return avenues;
	}
	
	private static Collection<Block> getBlocks(int[] streets, int[] avenues) {
		Collection<Block> blocks = new LinkedList<Block>();
		for (int i = 0; i < streets.length - 1; i++) {
			for (int j = 0; j < avenues.length - 1; j++) {
				blocks.add(new Block(streets[i], avenues[j + 1], streets[i + 1] - streets[i], avenues[j + 1] - avenues[j]));
			}
		}
		return blocks;
	}
	
	private static HelicopterPath getHelicopterPath(int[] streets, int[] avenues) {
		return new HelicopterPath(streets[streets.length - 1], avenues[avenues.length - 1]);
	}
	
	private static int countBlocksTheHelicopterHasFlownOver(HelicopterPath helicopterPath, Collection<Block> blocks) {
		int blocksTheHelicopterHasFlownOver = 0;
		for (Block block : blocks) {
			if (helicopterPath.getSlope() < block.getSlopeOfTopLeftPoint() && helicopterPath.getSlope() > block.getSlopeOfBottomRightPoint()) {
				blocksTheHelicopterHasFlownOver++;
			}
		}
		return blocksTheHelicopterHasFlownOver;
	}
	
}
