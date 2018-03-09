package Day7;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.text.StyledEditorKit.ForegroundAction;

public class main {

	static FileReader inputText = null;
	static BufferedReader reader = null;
	static Set<String> prgrmsWithChilds = new HashSet<String>();
	static Set<String> childPrgrms = new HashSet<String>();

	public static void main(String[] args) {
		long startDate = System.currentTimeMillis();
		// TODO Auto-generated method stub
		inputText = readFile("src/Day7/inputDay7.txt");
		if (inputText != null) {
			reader = new BufferedReader(inputText);
			String line = "";
			line = readNextLine(line);
			
			do {
//				System.out.println(line);
				String[] splitedLine = {};
				if (line.contains(" -> ")) {
					splitedLine = line.split(" -> ");
					String splitedLeft = splitedLine[0];
					String splitedRight = splitedLine[1];
					
					splitedLeft = splitedLeft.split(" \\(")[0];
					prgrmsWithChilds.add(splitedLeft);
					
					String[] csv = splitedRight.split(", ");
					for (String prgm : csv) {
						childPrgrms.add(prgm);
					}
				}
				
				line = readNextLine(line);
			} while(line != null && !line.equals(""));
			
			
//			showSets();
			
			String bottomProgram = searchV(childPrgrms,prgrmsWithChilds);
			System.out.println("Result = "+bottomProgram);
			long endDate = System.currentTimeMillis();
			
			System.out.println("et = "+(endDate-startDate));
			
		}
	}
	
	private static void showSets() {
		// TODO Auto-generated method stub
		System.out.println("================\nprgrmsWithChilds");
		for (String string : prgrmsWithChilds) {
			System.out.println(string);
			
		}
		System.out.println("================");
		System.out.println("================\nchildPrgrms");
		for (String string : childPrgrms) {
			System.out.println(string);
			
		}
		System.out.println("================");
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
	
	static String readNextLine(String line) {
		try {
			line = reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return line;
	}
	
	static String searchV(Set searchInto, Set searchFrom) {
		String result = "";
		for (Object entry : searchFrom) {
			String prgrm = (String)entry;
			prgrm = prgrm.trim();
			
//			System.out.println("prgm="+prgrm);
			if (!searchInto.contains(prgrm)) {
				result = prgrm;
				System.out.println(prgrm+" not found.");
				break;
			}
		}
		
		return result;
	}

}
