package Day4;

import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner("src/Day4/input.txt");
		File file = new File(scanner.nextLine());
		
		int maxValueEver = 0;

        try {
        	scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int validPassphrases = 0;
		while (scanner.hasNext()) {
			String line = (String) scanner.nextLine();
			System.out.println("============================");
			System.out.println(line);
			List<String> passwords = new ArrayList<String>();
			
			String[] words = line.split(" ");
			boolean isCurrentPassphraseValid = true;
			for (String currentWord : words) {
				currentWord = currentWord.trim();
				System.out.println("---"+currentWord);
				isCurrentPassphraseValid = true;
				if (!passwords.contains(currentWord)) {
					passwords.add(currentWord);
				}
				else {
					isCurrentPassphraseValid = false;
					break;
				}
			}
			validPassphrases += isCurrentPassphraseValid?1:0;
		}
		
		System.out.println("Number of valid passphrases: " + validPassphrases);

	}

}
