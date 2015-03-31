import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
	
	private static class Point {
		
		private final int x;
		private final int y;
		
		private Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public double calculateDistanceTo(Point that) {
			return Math.sqrt(Math.pow(this.x - that.x, 2) + Math.pow(this.y - that.y, 2));
		}
		
		public static Point parsePoint(String pointAsString) {
			int x = Character.getNumericValue(pointAsString.charAt(1));
			int y = Character.getNumericValue(pointAsString.charAt(3));
			return new Point(x, y);
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		
		try(BufferedReader bufferedReader = new BufferedReader(new FileReader(args[0]))) {
			
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				
				List<Point> points = parsePoints(line.split(", "));
				System.out.println(isSquare(points));
				
			}
			
		}
		
	}

	private static List<Point> parsePoints(String[] pointsAsStrings) {
		List<Point> points = new ArrayList<>(4);
		for (String pointAsString : pointsAsStrings) {
			points.add(Point.parsePoint(pointAsString));
		}
		return points;
	}
	
	private static boolean isSquare(List<Point> points) {
		
		Point point1 = points.get(0);
		Point point2 = points.get(1);
		Point point3 = points.get(2);
		Point point4 = points.get(3);
		
		double distanceBetweenPoint1AndPoint2 = point1.calculateDistanceTo(point2);
		double distanceBetweenPoint1AndPoint3 = point1.calculateDistanceTo(point3);
		double distanceBetweenPoint1AndPoint4 = point1.calculateDistanceTo(point4);
		
		return testAdjacentPointsAndOppositePoint(point2, point3, point4, distanceBetweenPoint1AndPoint2, distanceBetweenPoint1AndPoint3, distanceBetweenPoint1AndPoint4) ||
			   testAdjacentPointsAndOppositePoint(point2, point4, point3, distanceBetweenPoint1AndPoint2, distanceBetweenPoint1AndPoint4, distanceBetweenPoint1AndPoint3) ||
			   testAdjacentPointsAndOppositePoint(point3, point4, point2, distanceBetweenPoint1AndPoint3, distanceBetweenPoint1AndPoint4, distanceBetweenPoint1AndPoint2);
	}
	
	private static boolean testAdjacentPointsAndOppositePoint(Point adjacentPoint1, Point adjacentPoint2, Point oppositePoint, double distanceBetweenReferenceAndAdjacentPoint1, double distanceBetweenReferenceAndAdjacentPoint2, double distanceBetweenReferenceAndOppositePoint) {
		
		if (distanceBetweenReferenceAndAdjacentPoint1 == distanceBetweenReferenceAndAdjacentPoint2 && distanceBetweenReferenceAndOppositePoint > distanceBetweenReferenceAndAdjacentPoint1) {
			
			double distanceBetweenAdjacentPoint1AndOppositePoint = adjacentPoint1.calculateDistanceTo(oppositePoint);
			double distanceBetweenAdjacentPoint2AndOppositePoint = adjacentPoint2.calculateDistanceTo(oppositePoint);
			
			if (distanceBetweenAdjacentPoint1AndOppositePoint == distanceBetweenAdjacentPoint2AndOppositePoint && distanceBetweenAdjacentPoint1AndOppositePoint == distanceBetweenReferenceAndAdjacentPoint1) {
				double distanceBetweenAdjacentPoints = adjacentPoint1.calculateDistanceTo(adjacentPoint2);
				return (distanceBetweenAdjacentPoints == distanceBetweenReferenceAndOppositePoint);
			} else {
				return false;
			}
			
		} else {
			return false;
		}
		
	}
	
}