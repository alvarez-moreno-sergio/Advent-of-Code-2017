package Day2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.ReadOnlyFileSystemException;

public class main {
	
	static FileReader inputText = null;
	static int checksum = 0;
	static Integer minimumValue = null;
	static Integer maximumValue = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        
		inputText = readFile("src/Day2/input.txt");
		if (inputText != null) {
			BufferedReader reader = new BufferedReader(inputText);
			String line = "";
			try {
				line = reader.readLine();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				line = "";
			}
			
			do {
				System.out.println(line);
				String[] columnas = line.split("\t");
				
				for (String number : columnas) {
					Integer intNumber = 0;
					try {
						intNumber = Integer.parseInt(number);
					} catch (Exception e) {
						intNumber = null;
					}
					
					if (intNumber != null) {
						updateMinimumValue(minimumValue, intNumber);
						updateMaximumValue(maximumValue, intNumber);
					}
				}
				
				updateChecksum();
				resetRangeValues();
				try {
					line = reader.readLine();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					line = "";
				}
			} while (line != null && !line.equals(""));
		}
        
		System.out.println("Result: "+checksum);
	}
	
	static FileReader readFile(String path) {
		FileReader result=null;
		try {
			result = new FileReader(path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("No file detected. Returning null.");
			e.printStackTrace();
		}
		return result;
	}
	
	static void updateMinimumValue(Integer currentMinimumValue, int actualValue) {
		if (currentMinimumValue == null || actualValue < currentMinimumValue) {
			minimumValue = actualValue;
		}
	}
	static void updateMaximumValue(Integer currentMaximumValue, int actualValue) {
		if (currentMaximumValue == null || actualValue > currentMaximumValue) {
			maximumValue = actualValue;
		}
	}
	static void updateChecksum() {
		int diff = 0;
		diff = maximumValue - minimumValue;
		checksum += diff;
	}
	static void resetRangeValues() {
		System.out.println("minimumValue: "+minimumValue);
		System.out.println("maximumValue: "+maximumValue);
		minimumValue = null;
		maximumValue = null;
	}
}
