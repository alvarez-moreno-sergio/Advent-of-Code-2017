package Day10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Currency;
import java.util.List;
import java.util.Scanner;

import javax.swing.text.Position;

public class main {
	
	static final int circularListSize = 256;
	static String[] circularList = new String[circularListSize];
	static int actualPosition = 0;
	static int skipSize = 0;
	static Scanner scanner = null;
	static int resultPart1 = 0;
	
	static List<Integer> firstRoundList = new ArrayList<Integer>();
	static int resultIndex = 0;
	static final double sqrtFromCircularListLength = Math.sqrt(circularListSize);
	static int[] denseHash = new int[(int) sqrtFromCircularListLength];
	static String[] hexDenseHash = new String[(int) sqrtFromCircularListLength];
	
	public static void main(String[] args) {
		try {
			scanner = new Scanner(new File("src/Day10/input.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		populateCircularList();
		scanner.useDelimiter("");
		doRound(null);		
		doNextRounds(63);
		
		getDenseHash();
		String s = "";
		for (int i : denseHash) {
			s+=i+",";
			
		}
		System.out.println("dense hash: "+s);
		getHexDenseHash();
		
		showHashedResult();
	}

	private static void showHashedResult() {
		String result = "";
		for (String hex : hexDenseHash) {
			result += hex;
		}
		
		System.out.println("Result hash: "+result);
	}

	private static void getHexDenseHash() {
		for (int i = 0; i < denseHash.length; i++) {
			String hexValue = Integer.toHexString(denseHash[i]);
			hexValue = hexValue.length()==1?
					"0"+hexValue:
						hexValue;
			hexDenseHash[i] = hexValue;
		}	
	}

	private static void getDenseHash() {
		int currentResult = 0;
		for (int i = 0; i < sqrtFromCircularListLength; i++) {
			currentResult = Integer.parseInt(circularList[(int) (sqrtFromCircularListLength*i+0)])  ^
							Integer.parseInt(circularList[(int) (sqrtFromCircularListLength*i+1)])  ^
							Integer.parseInt(circularList[(int) (sqrtFromCircularListLength*i+2)])  ^
							Integer.parseInt(circularList[(int) (sqrtFromCircularListLength*i+3)])  ^
							Integer.parseInt(circularList[(int) (sqrtFromCircularListLength*i+4)])  ^
							Integer.parseInt(circularList[(int) (sqrtFromCircularListLength*i+5)])  ^
							Integer.parseInt(circularList[(int) (sqrtFromCircularListLength*i+6)])  ^
							Integer.parseInt(circularList[(int) (sqrtFromCircularListLength*i+7)])  ^
							Integer.parseInt(circularList[(int) (sqrtFromCircularListLength*i+8)])  ^
							Integer.parseInt(circularList[(int) (sqrtFromCircularListLength*i+9)])  ^
							Integer.parseInt(circularList[(int) (sqrtFromCircularListLength*i+10)]) ^
							Integer.parseInt(circularList[(int) (sqrtFromCircularListLength*i+11)]) ^
							Integer.parseInt(circularList[(int) (sqrtFromCircularListLength*i+12)]) ^
							Integer.parseInt(circularList[(int) (sqrtFromCircularListLength*i+13)]) ^
							Integer.parseInt(circularList[(int) (sqrtFromCircularListLength*i+14)]) ^
							Integer.parseInt(circularList[(int) (sqrtFromCircularListLength*i+15)]);
			denseHash[i] = currentResult;
		}
	}

	private static void doNextRounds(int limit) {
		for (int i = 0; i < limit; i++) {
			doRound(firstRoundList);	
		}
	}

	static List<Integer> doRound(List<Integer> input) {
		List<Integer> result= new ArrayList<Integer>();
		
		if (input == null) {
			while (scanner.hasNext()) {
				String character = scanner.next();
				int asciiChar = characterToASCII(character.charAt(0));
				tieOneNumber(asciiChar, true);			
			}
			doRoundOnlyWithSuffixCharacters();
		}
		else {
			for (int number : input) {
				tieOneNumber(number, false);
			}
		}
		
		showCircularList();
		
		int a = Integer.parseInt(circularList[0]);
		int b = Integer.parseInt(circularList[1]);
		resultPart1 = a * b;
		System.out.println("resultPart1: "+a+"*"+b +" = "+resultPart1);
		System.out.println("actualPosition: "+actualPosition);
		System.out.println("skipSize: "+skipSize);
		
		return result;
	}

	private static void doRoundOnlyWithSuffixCharacters() {
		int[] suffixCharacters = {17, 31, 73, 47, 23};
		for (int i : suffixCharacters) {
			tieOneNumber(i, true);			
		}
	}

	private static int characterToASCII(char character) {
		int result = (int) character;
		return result;
	}

	private static void tieOneNumber(int number, boolean modifyFirstRoundList) {
		reversePortionOfArray(circularList, number);
		
		if (modifyFirstRoundList) {
			firstRoundList.add(resultIndex,number);
		}
		actualPosition+=skipSize+number;
		skipSize++;
		resultIndex++;
	}

	private static void reversePortionOfArray(String[] array, int number) {
		String[] subArray = new String[number];
		for (int i = 0; i < number; i++) {
			int wrappedPosition = (actualPosition+i)%circularList.length;
			subArray[i] = circularList[wrappedPosition];
		}
		
		Collections.reverse(Arrays.asList(subArray)); // now it is reversed
		for (int i = 0; i < subArray.length; i++) {
			int wrappedPosition = (actualPosition+i)%circularList.length;
			circularList[wrappedPosition] = subArray[i];
		}
	}

	private static void populateCircularList() {
		String result = "";
		for (int i = 0; i < circularList.length; i++) {
			circularList[i] = String.valueOf(i);
			result+=circularList[i]+",";
		}
		System.out.println(result.substring(0, result.length()-1));
		
	}
	
	static void showCircularList() {
		System.out.println("=============++=======");
		String result = "";
		for (int i = 0; i < circularList.length; i++) {
			result+=circularList[i]+"|";
		}
		System.out.println(result);
	}

}
