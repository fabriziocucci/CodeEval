import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;
import java.util.regex.MatchResult;

public class Main {
	
	private static class Point {
		
		private final BigDecimal x;
		private final BigDecimal y;
		
		private Point(BigDecimal x, BigDecimal y) {
			this.x = x;
			this.y = y;
		}
		
	}
	
	private static class Circle {
		
		private final Point center;
		private final BigDecimal radius;
		
		private Circle(Point center, BigDecimal radius) {
			this.center = center;
			this.radius = radius;
		}
		
		public boolean isInside(Point point) {
			return point.x.subtract(center.x).pow(2).add(point.y.subtract(center.y).pow(2)).compareTo(radius.pow(2)) < 0;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		
		try(BufferedReader bufferedReader = new BufferedReader(new FileReader(args[0]))) {
			
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				String[] arguments = line.trim().split("; ");
				
				String centerAsString = arguments[0];
				String radiusAsString = arguments[1];
				String pointAsString = arguments[2];
				
				Circle circle = parseCircle(centerAsString, radiusAsString);
				Point point = parsePoint(pointAsString);
				
				System.out.println(circle.isInside(point));
				
			}
			
		}
		
	}
	
	private static Circle parseCircle(String centerAsString, String radiusAsString) {
		Point center = parseCenter(centerAsString);
		BigDecimal radius = parseRadius(radiusAsString);
		return new Circle(center, radius);
	}
	
	private static Point parseCenter(String centerAsString) {
		try (Scanner scanner = new Scanner(centerAsString)) {
			scanner.findInLine("Center: \\((-?\\d+(?:\\.\\d{1,2})?), (-?\\d+(?:\\.\\d{1,2})?)\\)");
			MatchResult result = scanner.match();
			BigDecimal x = new BigDecimal(result.group(1));
			BigDecimal y = new BigDecimal(result.group(2));
			return new Point(x, y);
		}
	}
	
	private static BigDecimal parseRadius(String radiusAsString) {
		try (Scanner scanner = new Scanner(radiusAsString)) {
			scanner.findInLine("Radius: (-?\\d+(?:\\.\\d{1,2})?)");
			MatchResult result = scanner.match();
			return new BigDecimal(result.group(1));
		}
	}
	
	private static Point parsePoint(String pointAsString) {
		try (Scanner scanner = new Scanner(pointAsString)) {
			scanner.findInLine("Point: \\((-?\\d+(?:\\.\\d{1,2})?), (-?\\d+(?:\\.\\d{1,2})?)\\)");
			MatchResult result = scanner.match();
			BigDecimal x = new BigDecimal(result.group(1));
			BigDecimal y = new BigDecimal(result.group(2));
			return new Point(x, y);
		}
	}
	
}
