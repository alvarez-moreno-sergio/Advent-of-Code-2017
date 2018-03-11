package Day6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Scanner;

public class main {
	enum type {
		min,
		max
	};
	static int numberOfBlocks = 0;
	static Scanner input = null;
	static Integer[] blocks = null;
	static List<Integer> initialBlocks = new ArrayList<Integer>();
	static List<String> history = new ArrayList<String>();
	public static void main(String[] args) {
		initializeBlocks();
		
		int numberOfLoops = 0;
		int actualPosition = 0;
		String mssgPart1 = "";
		String mssgPart2 = "";
		String repeatedKey = "";
		boolean completed = false;
		System.out.println(generateCurrentKey());
		int loopNumberExistingMomentOnHistory = 0;
		while (!completed) {
			saveThisMomentOnHistory();
			doRound(actualPosition);
			numberOfLoops++;
			System.out.println("========");
			System.out.println(generateCurrentKey());
			System.out.println("========");
			
			if (!mssgPart1.equals("")) {
				loopNumberExistingMomentOnHistory++;
			}
			if (numberOfLoops > 1 && generateCurrentKey().equals(repeatedKey)) {
				mssgPart2 += "Number of loops until repeated Keys: "+ loopNumberExistingMomentOnHistory;
			}			
			if (mssgPart1.equals("") && history.contains(generateCurrentKey())) {
				mssgPart1 += "Number of loops until an existing moment in history: "+ numberOfLoops;
				repeatedKey = generateCurrentKey();
			}
			
			if (!mssgPart1.equals("") && !mssgPart2.equals("")) {
				completed = true;
			}
		}
		System.out.println(mssgPart1);
		System.out.println(mssgPart2);
	}
	
	private static void doRound(int actualPosition) {
		actualPosition = getBlockWithMaxValue(blocks);
		int valueToBalance = getMaxValueFromIntegerArray(blocks);
		blocks[actualPosition] = 0;
		
		for (int i = 1; valueToBalance > 0; i++) {
			blocks[wrapPositionAroundIntegerArray(actualPosition+i, blocks)] +=1;
			valueToBalance--;
		}

//		System.out.println("===============");
	}

	private static void saveThisMomentOnHistory() {
		String generatedKey = generateCurrentKey();
		history.add(generatedKey);
	}

	private static String generateCurrentKey() {
		String key = "";
		for (Integer w : blocks) {
			key += (w+",");
		}
		key = key.substring(0, key.length()-1);
		return key;
	}

	private static int getBlockWithMaxValue(Integer[] array) {
		int maxValue = 0;
		int currentBlockWithMaxValue = -1;
		for (int i = 0; i < array.length; i++) {
			if (array[i] > maxValue) {
				maxValue = array[i];
				currentBlockWithMaxValue = i;
			}
		}
		return currentBlockWithMaxValue; 
	}

	private static void initializeBlocks() {
		try {
			input = new Scanner(new File("src/Day6/input.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		input.useDelimiter("	");
		while (input.hasNextInt()) {
			numberOfBlocks++;
			int i = input.nextInt();
			initialBlocks.add(i);
		}
	    
		blocks = initialBlocks.toArray(new Integer[initialBlocks.size()]); 
		initialBlocks = null;
	}
	
	private static int wrapPositionAroundIntegerArray(int position, Integer[] collection) {
		return position%collection.length;
	}
	
	private static int getMaxValueFromIntegerArray(Integer[] collection) {
		return Collections.max(Arrays.asList(blocks));
	}
}
