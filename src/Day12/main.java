package Day12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.imageio.spi.RegisterableService;

public class main {

	static Scanner input = null;
	static Map<Integer, List<Integer>> programsRelations = new HashMap<Integer, List<Integer>>();
	static List<Integer> history = new ArrayList<Integer>();
	static int idProgram2Count;
	static int levelsOfCommunication = 0;
	static int totalGroups = 0;
	static List<Integer> programsCounted = new ArrayList<Integer>();

	public static void main(String[] args) {
		try {
			input = new Scanner(new File("src/Day12/input.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		populateCollection();
//		double inicio = System.currentTimeMillis();
		countAcrossLevelsOfCommunication(idProgram2Count);
//		double fin = System.currentTimeMillis();
//		System.out.println("Time ms: "+ (fin-inicio));
		
		int levels = levelsOfCommunication;
		programsCounted = new ArrayList<Integer>();
		int mapLength = programsRelations.size();
		for (int i = 0; i < mapLength; i++) {
			if (!programsCounted.contains(i)) {
				totalGroups++;
				history = new ArrayList<Integer>();
				countAcrossLevelsOfCommunication(i);
			}
		}
		
		System.out.println(idProgram2Count+" can communicate with n programs: "+levels);
		System.out.println("There are n total of groups: "+ totalGroups);
	}
	
	static void countAcrossLevelsOfCommunication(Integer p) {
		levelsOfCommunication++;
		if (!history.contains(p)) {
			history.add(p);
		}
		programsCounted.add(p);
		if (programsRelations.containsKey(p)) {
			List<Integer> related = programsRelations.get(p);
			for (Integer i : related) {
				if (!history.contains(i)) {
					countAcrossLevelsOfCommunication(i);
				}
			}
		}
	}

	private static void populateCollection() {
		while (input.hasNextLine()) {
			String line = (String) input.nextLine();
			String[] split = line.split(" <-> ");

			String program = split[0];
			String[] relatedP = split[1].split(", ");

			updateListFromMap(Integer.parseInt(program),relatedP);	
		}
		System.out.println(programsRelations.toString());
		System.out.println("=======================================");
	}

	static void updateListFromMap(Integer k, String[] relations) {
		updateListFromMap(k, k);
		for (String program : relations) {
			Integer p = Integer.parseInt(program);
			updateListFromMap(k, p);
		}
	}

	static void updateListFromMap(Integer k, Integer v) {
		List<Integer> relations = null;
		relations = programsRelations.get(k);
		if (relations == null) {
			relations = new ArrayList<Integer>();
		}
		
		if (!relations.contains(v)) {
			relations.add(v);
			programsRelations.put(k, relations);
		}
	}

}
