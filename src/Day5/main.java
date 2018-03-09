package Day5;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class main {
	
	static List<Integer> maze = new ArrayList<Integer>();
	static int mazeLength = 0;
	static int numberOfJumps = 0;
	static int currentPosition = 0;
	static boolean continueInMaze = true;

	public static void main(String[] args) {
		Scanner scanner = new Scanner("src/Day5/input.txt");
		File file = new File(scanner.nextLine());
		
        try {
        	scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        populateMaze(scanner);
        while (continueInMaze) {
			int jumps = maze.get(currentPosition);
			makeJumps(jumps, currentPosition);
			
			exitMaze();
		}
	}
	
	static void populateMaze(Scanner scanner) {
		while (scanner.hasNextLine()) {
			String line = (String) scanner.nextLine();
			maze.add(Integer.parseInt(line));
			System.out.println(line);
		}
		
		mazeLength = maze.size();
	}
	
	static void makeJumps(int jumps, int index) {
		updateMazeOffset(jumps, index);
		numberOfJumps++;
		currentPosition+=jumps;
	}
	
	static void updateMazeOffset(int jumps, int index) {
//		maze.set(index, jumps+1);
		maze.set(index, 
				maze.get(index) >= 3?
						maze.get(index)-1:
							maze.get(index)+1
		);
	}

	static void exitMaze() {
		if (currentPosition >= mazeLength) {
			continueInMaze = false;
			System.out.println("Exited at index "+currentPosition);
			System.out.println("Exited in "+numberOfJumps+" total steps");
		}
	}

}
