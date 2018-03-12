package Day11;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import Day11.Axis3.Vector;

public class main {
	static Scanner input=null;
	static List<Integer> movementHistory = new ArrayList<Integer>();

	public static void main(String[] args) {
		Axis3 initialPosition = new Axis3();
		Axis3 currentPosition = new Axis3();
		Axis3.initializeAxialCoords();
		try {
			input = new Scanner(new File("src/Day11/input.txt"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		input.useDelimiter(",");
		while (input.hasNext()) {
			String coord = (String) input.next();
			Axis3.Vector v = Axis3.axialCoord.get(coord);
			currentPosition.applyVector(v);
			movementHistory.add(Axis3.distance(initialPosition, currentPosition));
		}
		
		System.out.println("Start: "+initialPosition.toString());
		System.out.println("End: "+currentPosition.toString());
		System.out.println("Distance: "+Axis3.distance(initialPosition, currentPosition));
		System.out.println("Max distance traveled ever: "+ Collections.max(movementHistory));
	}

}
