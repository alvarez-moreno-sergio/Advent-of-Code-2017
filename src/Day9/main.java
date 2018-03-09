package Day9;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		Map<Integer,Integer> elementsInlevels =  new HashMap<Integer, Integer>();
		int resultInPoints = 0;
		Scanner input = new Scanner("src/Day9/input.txt");
		File file = new File(input.next());
		
		try {
			input = new Scanner(file);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		List<Character> stack = new ArrayList<Character>();
		int currentLevel=0;
		boolean inGarbage = false;
		boolean ignoreNextCharacter = false;
		int nCharactersToIgnore = 1;
		int charactersIgnored = 0;
		int nCharactersInGarbage = 0;
		input.useDelimiter("");
		while (input.hasNext()) {
			Character character = input.next().toCharArray()[0];
			System.out.println(character);
			
			if (!inGarbage) {
				switch (character) {
				case '{':
					stack.add('{');
					currentLevel+=1;
					break;
				case '}':
					stack.remove(stack.size()-1);
					elementsInlevels.put(currentLevel, elementsInlevels.get(currentLevel)==null?1:elementsInlevels.get(currentLevel)+1);
					currentLevel-=1;
					break;
				case '<':
					inGarbage = true;
					break;
				default:
					break;
				}
			}
			else if (!ignoreNextCharacter || (ignoreNextCharacter && charactersIgnored == 1)) {
				charactersIgnored = 0;
				ignoreNextCharacter = false;
				switch (character) {
				case '!':
					ignoreNextCharacter = true;
					break;
				case '>':
					inGarbage = false;
					break;
				default:
					nCharactersInGarbage++;
					break;
				}
			}
			else {
				charactersIgnored++;
			}
		}
		
		System.out.println(elementsInlevels.toString());
		String result="";
		for (int i = 1; i <= elementsInlevels.size(); i++) {
			int currentResult = resultInPoints;
			currentResult += i*elementsInlevels.get(i);
			resultInPoints = currentResult;
			result = getTabulation(i)+"Level "+i+" = "+i+"*"+elementsInlevels.get(i)+" = "+currentResult + " --> "+resultInPoints;
			System.out.println(result);
		}
		
		System.out.println("Resultado 1: "+resultInPoints);
		System.out.println("Resultado 2: "+nCharactersInGarbage);
	}

	private static String getTabulation(int i) {
		String result = "";
		for (int j = 0; j < i-1; j++) {
			result+="-";
		}
		return result;
	}

}
