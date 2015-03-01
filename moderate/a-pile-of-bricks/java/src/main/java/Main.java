import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.MatchResult;

public class Main {

	private static class Point2d {

		private final int x;
		private final int y;
		
		private Point2d(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
	}
	
	private static class Point3d {

		private final int x;
		private final int y;
		private final int z;
		
		private Point3d(int x, int y,  int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}
		
	}
	
	private static class Hole {

		private final int width;
		private final int height;
		private final int[] sortedDimensions;
		
		private Hole(Point2d oppositeVertex1, Point2d oppositeVertex2) {
			this.width = Math.abs(oppositeVertex1.x - oppositeVertex2.x);
			this.height = Math.abs(oppositeVertex1.y - oppositeVertex2.y);
			this.sortedDimensions = new int[] { Math.min(width, height), Math.max(width, height) };
		}		
		
		public boolean canPassThrough(Brick brick) {
			return sortedDimensions[0] >= brick.sortedDimensions[0] && sortedDimensions[1] >= brick.sortedDimensions[1];
		}
		
		public List<Brick> filterBricksThatPassThrough(List<Brick> bricks) {
			List<Brick> bricksWhichPassThrough = new LinkedList<Brick>();
			for (Brick brick : bricks) {
				if (canPassThrough(brick)) {
					bricksWhichPassThrough.add(brick);
				}
			}
			return bricksWhichPassThrough;
		}
		
	}
	
	private static class Brick implements Comparable<Brick> {
		
		private final int index;
		
		private final int width;
		private final int height;
		private final int depth;
		private final int[] sortedDimensions;
		
		private Brick(int index, Point3d oppositeVertex1, Point3d oppositeVertex2) {
			this.index = index;
			this.width = Math.abs(oppositeVertex1.x - oppositeVertex2.x);
			this.height = Math.abs(oppositeVertex1.y - oppositeVertex2.y);
			this.depth = Math.abs(oppositeVertex1.z - oppositeVertex2.z);
			this.sortedDimensions = new int[] { width, height, depth };
			Arrays.sort(sortedDimensions);
		}

		@Override
		public int compareTo(Brick that) {
			return Integer.compare(this.index, that.index);
		}
		
	}
	
	public static void main(String[] args) throws IOException {

		try (BufferedReader buffer = new BufferedReader(new FileReader(args[0]))) {

			String line;
			while ((line = buffer.readLine()) != null) {
				
				String[] arguments = line.trim().split("\\|");
				
				String holeAsString = arguments[0];
				String bricksAsString = arguments[1];
				
				Hole hole = parseHole(holeAsString);
				List<Brick> bricks = parseBricks(bricksAsString);
				
				List<Brick> bricksThatPassThrough = hole.filterBricksThatPassThrough(bricks);
				if (bricksThatPassThrough.isEmpty()) {
					System.out.println("-");
				} else {
					Collections.sort(bricksThatPassThrough);
					System.out.println(joinBricksIndexes(bricksThatPassThrough));
				}
				
			}

		}

	}

	private static Hole parseHole(String holeAsString) {
		String[] holeVerteces = holeAsString.split("\\s");
		Point2d oppositVertex1 = parseHoleVertex(holeVerteces[0]);
		Point2d oppositVertex2 = parseHoleVertex(holeVerteces[1]);
		return new Hole(oppositVertex1, oppositVertex2);
	}
	
	private static Point2d parseHoleVertex(String holeVertexAsString) {
		try (Scanner scanner = new Scanner(holeVertexAsString)) {
			scanner.findInLine("\\[(-?\\d+),(-?\\d+)\\]");
			MatchResult match = scanner.match();
			int x = Integer.parseInt(match.group(1));
			int y = Integer.parseInt(match.group(2));
			return new Point2d(x, y);
		}
	}
	
	private static List<Brick> parseBricks(String bricksAsString) {
		String[] bricksAsArray = bricksAsString.split(";");
		List<Brick> bricksAsList = new ArrayList<Brick>(bricksAsArray.length);
		for (String brickAsString : bricksAsArray) {
			bricksAsList.add(parseBrick(brickAsString));
		}
		return bricksAsList;
	}

	private static Brick parseBrick(String brickAsString) {
		try (Scanner scanner = new Scanner(brickAsString)) {
			scanner.findInLine("\\((-?\\d+)\\s(\\[.+\\])\\s(\\[.+\\])\\)");
			MatchResult matchResult = scanner.match();
			int index = Integer.parseInt(matchResult.group(1));
			Point3d oppositVertex1 = parseBrickVertex(matchResult.group(2));
			Point3d oppositVertex2 = parseBrickVertex(matchResult.group(3));
			return new Brick(index, oppositVertex1, oppositVertex2);
		}
	}
	
	private static Point3d parseBrickVertex(String brickVertexAsString) {
		try (Scanner scanner = new Scanner(brickVertexAsString)) {
			scanner.findInLine("\\[(-?\\d+),(-?\\d+),(-?\\d+)\\]");
			MatchResult matchResult = scanner.match();
			int x = Integer.parseInt(matchResult.group(1));
			int y = Integer.parseInt(matchResult.group(2));
			int z = Integer.parseInt(matchResult.group(3));
			return new Point3d(x, y, z);
		}
	}
	
	private static String joinBricksIndexes(List<Brick> bricks) {
		Iterator<Brick> iterator = bricks.iterator();
		StringBuilder stringBuilder = new StringBuilder();
		if (iterator.hasNext()) {
			stringBuilder.append(iterator.next().index);
			while (iterator.hasNext()) {
				stringBuilder.append(",").append(iterator.next().index);
			}
		}
		return stringBuilder.toString();
	}
	
}
