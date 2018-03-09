package Day3;

import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Map;

public class main {
	static Map<Point,Integer> spiralMap = new HashMap<Point,Integer>();
	static enum directions {
		right,
		up,
		left,
		down
	}
	static directions startDirection = directions.right;
	static Map<directions,Integer> directionsValue = new HashMap<directions,Integer>();
	static Map<Integer,directions> valueDirections = new HashMap<Integer,directions>();
	static int nDirections = 4;
	
	static int horizontalCoord = 0;
	static int verticalCoord = 0;
	static int distanceFromCenter = 1;
	
	static int input = 289326;
	static Point inputPoint = null;
	static int minimumValue = input;
	
	public static void main(String[] args) {
		
		int result = 0;
		
		initializeMaps();
		spiralMap = generate2DSpiralMap(input);
		
		showSpiralLog();		
		
		long startmilliseconds = System.currentTimeMillis();
		result = calculeNOTManhattanDistance(input);
		long endmilliseconds = System.currentTimeMillis();
		System.out.println("Result = "+result+" in "+(endmilliseconds-startmilliseconds));
		
		startmilliseconds = System.currentTimeMillis();
		int result2 = calculateREALManhattanDistance(input);
		endmilliseconds = System.currentTimeMillis();

		System.out.println("Manhattan Result = "+result2+" in "+(endmilliseconds-startmilliseconds));
	}

	private static void showSpiralLog() {
		System.out.println("-- Y,X --");
		for (Map.Entry<Point, Integer> entry : spiralMap.entrySet())
		{
		    System.out.println(entry.getKey().toString() + " ==> " + entry.getValue());
		}
	}

	static void initializeMaps() {
		directionsValue.put(directions.right,1);
		directionsValue.put(directions.up,2);
		directionsValue.put(directions.left,3);
		directionsValue.put(directions.down,4);
		
		valueDirections.put(1,directions.right);
		valueDirections.put(2,directions.up);
		valueDirections.put(3,directions.left);
		valueDirections.put(4,directions.down);
	}
	
	static Map<Point,Integer> generate2DSpiralMap(int maxNumberToReach){
		Map<Point,Integer> result = new HashMap<Point, Integer>();
		int lastNumber = 0;
		
		Point lastCoord = new Point(horizontalCoord,verticalCoord);
		
		directions currentDirection = startDirection;
		Point currentCoord = new Point(horizontalCoord,verticalCoord);
		result.put(currentCoord, ++lastNumber);

		while (lastNumber <= maxNumberToReach-1) {
			//System.out.println(lastNumber);
			updateCoordFromDirections(currentDirection);
			if (Math.abs(horizontalCoord) > distanceFromCenter || Math.abs(verticalCoord) > distanceFromCenter) {
				int currentDirectionValue = directionsValue.get(currentDirection);
				currentDirection = getNextDirection(currentDirection,currentDirectionValue);
				
				verticalCoord = lastCoord.y;
				horizontalCoord = lastCoord.x;
				
				updateCoordFromDirections(currentDirection);
			}
			
			currentCoord = new Point(horizontalCoord,verticalCoord);
			result.put(currentCoord, ++lastNumber);
			lastCoord = new Point(horizontalCoord,verticalCoord);
		}
		
		inputPoint = lastCoord;
		return result;
	}
	
	private static directions getNextDirection(directions currentDirection, int currentDirectionValue) {
		// Use the next direction to populate the array
		currentDirection = valueDirections.get(currentDirectionValue+1);
		if (currentDirection == null) {
			currentDirection = startDirection;
			distanceFromCenter++;
		}
	
		return currentDirection;
	}

	static void updateCoordFromDirections(directions currentDirection) {
		switch (currentDirection) {
		case right:
			horizontalCoord += 1;
			break;
		case up:
			verticalCoord += 1;
			break;
		case left:
			horizontalCoord += -1;
			break;
		case down:
			verticalCoord += -1;
			break;
		}
	}
	
	private static int calculeNOTManhattanDistance(int input) {
		System.out.println("Input found: "+inputPoint+" ==> "+input);
		System.out.println("======================");
		
		int result = 0;
		Point currentPosition = inputPoint;
		
		while (minimumValue > 1) {
			Point upPosition = new Point(currentPosition.x, currentPosition.y+1);
			Point downPosition = new Point(currentPosition.x, currentPosition.y-1);
			Point leftPosition = new Point(currentPosition.x-1, currentPosition.y);
			Point rightPosition = new Point(currentPosition.x+1, currentPosition.y);
			Point[] possiblePoints = {upPosition, downPosition, leftPosition, rightPosition};
			
			currentPosition = getPositionWithMinimumValue(possiblePoints);
			result++;
		}

		return result;
	}
	
	private static int calculateREALManhattanDistance(int input) {
		int result = 0;
		
		Point start = new Point(0,0);
		Point end = inputPoint;
		result = Point.getManhattanDistance(start, end);
		return result;
	}

	private static Point getPositionWithMinimumValue(Point[] possiblePoints) {
		Point result = null;
		for (Point point : possiblePoints) {
			Integer actualPositionValue = spiralMap.get(point);
			
			System.out.println("Point: "+point.toString() + "// Value: "+spiralMap.get(point));
			if (actualPositionValue != null && actualPositionValue < minimumValue) {
				minimumValue = spiralMap.get(point);
				
				result = point;
			}
		}
		return result;
	}
}
